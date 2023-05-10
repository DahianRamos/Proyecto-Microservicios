package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.DTO.AutorDTO;
import co.edu.uniquindio.proyecto.DTO.Respuesta;
import co.edu.uniquindio.proyecto.model.Autor;
import co.edu.uniquindio.proyecto.servicio.AutorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autor")
@AllArgsConstructor
public class AutorController {
    private final AutorServicio autorServicio;

    @DeleteMapping("/{autorId}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable long autorId) {
        autorServicio.delete(autorId);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El autor se elimino correctamente"));
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<AutorDTO>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autores encontrados", autorServicio.findAll()));
    }

    @GetMapping("/{autorId}")
    public ResponseEntity<Respuesta<Autor>> findById(@PathVariable Long autorId) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autor encontrado", autorServicio.findById(autorId)));
    }

    @PostMapping
    public ResponseEntity<Respuesta<Autor>> save(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autor creado correctamente", autorServicio.save(autor)));
    }

    @PutMapping("/{autorId}")
    public ResponseEntity<Respuesta<Autor>> update(@PathVariable Long autorId, @RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El Autor se actualizo correctamente", autorServicio.update(autorId, autor)));
    }



}
