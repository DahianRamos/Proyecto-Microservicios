package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.Respuesta;
import co.edu.uniquindio.proyecto.model.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/producto")
@AllArgsConstructor
public class ProductoController {

    private final ProductoServicio productoServicio;
    @DeleteMapping("/{productoId}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable long productoId) {
        productoServicio.delete(productoId);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El producto se elimino correctamente"));
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<ProductoDTO>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autores encontrados", productoServicio.findAll()));
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Respuesta<Producto>> findById(@PathVariable Long productoId) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Producto encontrado", productoServicio.findById(productoId)));
    }

    @PostMapping
    public ResponseEntity<Respuesta<Producto>> save(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Producto creado correctamente", productoServicio.save(producto)));
    }

    @PutMapping("/{autorId}")
    public ResponseEntity<Respuesta<Producto>> update(@PathVariable Long productoId, @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El Autor se actualizo correctamente", productoServicio.update(productoId, producto)));
    }

}

