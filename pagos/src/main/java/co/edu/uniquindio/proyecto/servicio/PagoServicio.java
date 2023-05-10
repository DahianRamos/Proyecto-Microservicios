package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.model.Pago;
import co.edu.uniquindio.proyecto.model.Producto;
import co.edu.uniquindio.proyecto.repo.PagoRepo;
import co.edu.uniquindio.proyecto.repo.ProductoRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.PagoNoEncontradoException;
import co.edu.uniquindio.proyecto.servicio.excepciones.ProductoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PagoServicio {
    private final RestTemplate restTemplate;
    private final PagoRepo pagoRepo;
    private final ProductoRepo productoRepo;

    public Pago save(PagoDTO pago){

        Optional<Pago> guardado = pagoRepo.findById(pago.pagoId());

        if(guardado.isPresent()){
            throw new RuntimeException("El Pago con el codigo"+pago.pagoId()+" ya existe");
        }

        return pagoRepo.save( convertir(pago) );
    }
    public void delete(String pagoId) {
        pagoRepo.deleteById(pagoId);
    }
    public Pago findById(String codigo){
        return pagoRepo.findById(codigo).orElseThrow(()-> new PagoNoEncontradoException("EL PAGO NO HA SIDO ENCONTRADO"));
    }

    public List<Pago> findAll(){
        return pagoRepo.findAll();
    }

    public Pago update(PagoDTO pago){
        return pagoRepo.save( convertir(pago) );
    }

    private Pago convertir(PagoDTO pago) throws ProductoNoEncontradoException {

        List<Producto> productos = productoRepo.findAllById(pago.codigoProductos());

        List<Long> idsExistentes = productos.stream().map(Producto::getId).toList();

        if(productos.size()!=pago.codigoProductos().size()){

            String noEncontrados = pago.codigoProductos()
                    .stream()
                    .filter(id -> !idsExistentes.contains(id))
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            throw new ProductoNoEncontradoException("Los Productos "+noEncontrados+" no existen");

        }

        Pago nuevo = Pago.builder()
                .codigo(pago.pagoId())
                .valorPago(pago.valorPago())
                .codigoCliente(pago.codigoCliente())
                .fechaPago(pago.fechaPago())
                .codigoProductos(idsExistentes)
                .build();

        return nuevo;
    }
    public PagoIdDTO validarListaPagos(List<String> listaLibros) {
        List<Pago> listaPagosExistentes = pagoRepo.findAllById(listaLibros);
        boolean result = true;

        //result = false;
        List<String> idsExistentes = listaPagosExistentes.stream().map(Pago::getCodigo).toList();
        List<String> noEncontrados = listaLibros
                .stream()
                .filter(id -> !idsExistentes.contains(id))
                .map(Object::toString)
                .collect(Collectors.toList());

        PagoIdDTO respuesta = new PagoIdDTO(idsExistentes, noEncontrados);

        return respuesta;
    }

    private ClienteGetDTO findClienteByCodigo(String codigoCliente){
        try {

            Respuesta<ClienteGetDTO> respuesta = restTemplate.exchange(
                    "http://CLIENTE-SERVICE/api/cliente/" + codigoCliente,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Respuesta<ClienteGetDTO>>() {}).getBody();

            return respuesta.getDato();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Hubo un error recuperando la información del cliente");
        }
    }

    public List<Pago> findByCodigoCliente(String codigoCliente){

        ClienteGetDTO cliente = findClienteByCodigo(codigoCliente);
        log.info("cliente {}", cliente);

        List<PagoQueryDTO> lista = pagoRepo.findByCodigoCliente(codigoCliente);
        List<Pago> respuesta = new ArrayList<>();

        for(PagoQueryDTO q : lista){
            if(respuesta.stream().noneMatch(r -> r.getCodigo() == q.getPagoID())){
                ArrayList<Long> productos = new ArrayList<>();
                productos.add(q.getCodigoProducto());
                respuesta.add( new Pago(q.getPagoID(), q.getValorPago(), q.getClienteID(), q.getFechaPago(),q.getTipoPago(), productos ) );
            }else{
                respuesta.stream().findAny().get().getCodigoProductos().add( q.getCodigoProducto() );
            }
        }

        return new ArrayList<>();

    }
}
