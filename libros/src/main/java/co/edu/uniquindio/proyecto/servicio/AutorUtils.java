package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.DTO.AutorDTO;
import co.edu.uniquindio.proyecto.model.Autor;
import co.edu.uniquindio.proyecto.repo.AutorRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AutorUtils {
    private final AutorRepo autorRepo;

    public Autor getAutor(long id) {
        return autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("El Autor no existe"));
    }

    public AutorDTO convertirAutorDTO(Autor autor) {
        return new AutorDTO(autor.getId(), autor.getNombre());
    }
}
