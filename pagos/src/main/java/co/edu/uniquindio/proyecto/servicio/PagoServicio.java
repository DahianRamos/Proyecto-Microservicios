package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.model.DetallePago;
import co.edu.uniquindio.proyecto.model.Pago;
import co.edu.uniquindio.proyecto.repo.PagoRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.PagoNoEncontradoException;
import co.edu.uniquindio.proyecto.servicio.excepciones.ProductoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PagoServicio {
    private final RestTemplate restTemplate;
    private final PagoRepo pagoRepo;

    public Pago save(PagoDTO pago){

//        Optional<Pago> guardado = pagoRepo.findById(pago.pagoId());
//
//        if(guardado.isPresent()){
//            throw new RuntimeException("El Pago con el codigo"+pago.pagoId()+" ya existe");
//        }

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

        List<DetallePago> detallePagos = pago.detallePagos().stream()
                .map(
                        detalle->new DetallePago(detalle.precioaProducto(),detalle.idProducto(), detalle.unidades())
                ).toList();

        //validarProductos(detallePagos);
        final double valorPago = calcularValorPago(detallePagos);



        Pago nuevo = Pago.builder()
                 //.codigo(UUID.randomUUID().toString())
                .valorPago(valorPago)
                .codigoCliente(pago.codigoCliente())
                .fechaPago(LocalDateTime.now())
                .detallePagos(detallePagos)
                .tipoPago(pago.tipoPago())
                .build();

        return nuevo;
    }


    public boolean validarProductos(List<DetallePago> detallePagos, List<Long> listaProductos) {
        for (DetallePago detalle : detallePagos) {
            if (!listaProductos.contains(detalle.getIdProducto())) {
                return false;
            }
        }
        return true;
    }

    private double calcularValorPago(List<DetallePago> detallePagos) {
        double total=0;
        for (DetallePago d: detallePagos) {
            total += d.getPrecioProducto() * d.getUnidades();
        }
        return total;
    }

    public PagoIdDTO validarListaPagos(List<String> listaPagos) {
        List<Pago> listaPagosExistentes = pagoRepo.findAllById(listaPagos);
        boolean result = true;

        //result = false;
        List<String> idsExistentes = listaPagosExistentes.stream().map(Pago::getCodigo).toList();
        List<String> noEncontrados = listaPagos
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

//    public List<Pago> findByCodigoCliente(String codigoCliente){
//
//        ClienteGetDTO cliente = findClienteByCodigo(codigoCliente);
//        log.info("cliente {}", cliente);
//
//        List<PagoQueryDTO> lista = pagoRepo.findByCodigoCliente(codigoCliente);
//        List<Pago> respuesta = new ArrayList<>();
//
//        for(PagoQueryDTO q : lista){
//            if(respuesta.stream().noneMatch(r -> r.getCodigo() == q.getPagoID())){
//                ArrayList<String> detallesPagos = new ArrayList<>();
//                detallesPagos.add(q.getCodigoDetalle());
//                respuesta.add( new Pago(q.getPagoID(), q.getValorPago(), q.getClienteID(), q.getFechaPago(),q.getTipoPago(),detallesPagos) );
//            }else{
//                respuesta.stream().findAny().get().getDetallePagos().add( q.getCodigoDetalle());
//            }
//        }
//
//        return new ArrayList<>();
//
//    }
}
