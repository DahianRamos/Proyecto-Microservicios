package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.dto.PagoDTO;
import co.edu.uniquindio.proyecto.dto.PagoIdDTO;
import co.edu.uniquindio.proyecto.dto.Respuesta;
import co.edu.uniquindio.proyecto.model.Pago;
import co.edu.uniquindio.proyecto.model.Producto;
import co.edu.uniquindio.proyecto.servicio.PagoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pago")
@AllArgsConstructor
public class PagoController {
    private final PagoServicio pagoServicio;

    @PostMapping
    public ResponseEntity<Respuesta<Pago>> save(@RequestBody PagoDTO pagoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Producto creado correctamente", pagoServicio.save(pagoDTO)) );
    }

//    @GetMapping("/cliente/{codigoCliente}")
//    public ResponseEntity<Respuesta<List<Pago>>> findByCodigoCliente(@PathVariable String codigoCliente){
//        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", pagoServicio.findByCodigoCliente(codigoCliente)));
//    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Pago>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", pagoServicio.findAll()));
    }

    @GetMapping("/{pagoId}")
    public ResponseEntity<Respuesta<Pago>> findAll(@PathVariable String pagoId){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", pagoServicio.findById(pagoId)) );
    }
    @DeleteMapping("/{pagoId}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable String pagoId) {
        pagoServicio.delete(pagoId);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Pago borrado correctamente"));
    }

    @PostMapping("/validarListaPagos")
    public ResponseEntity<Respuesta<PagoIdDTO>> validarListaPagos(@RequestBody List<String> listaPagos) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", pagoServicio.validarListaPagos(listaPagos)));
    }

    @PutMapping
    public ResponseEntity<Respuesta<Pago>> udpate(@RequestBody PagoDTO pagoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Libro actualizado correctamente", pagoServicio.update(pagoDTO)));
    }
}
