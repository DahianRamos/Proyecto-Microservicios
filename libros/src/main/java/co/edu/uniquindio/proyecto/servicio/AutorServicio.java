package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.DTO.AutorDTO;
import co.edu.uniquindio.proyecto.model.Autor;
import co.edu.uniquindio.proyecto.repo.AutorRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AutorServicio {
    private final AutorUtils autorUtils;

    private final AutorRepo autorRepo;

    public void delete(long id) {
        autorUtils.getAutor(id);
        autorRepo.deleteById(id);
    }

    public Autor findById(Long id) {
        return autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("AutorController not found"));
    }

    public List<AutorDTO> findAll() {
        return autorRepo.findAll().stream().map(author -> autorUtils.convertirAutorDTO(author)).collect(Collectors.toList());
    }

    public Autor save(Autor autor) {
        return autorRepo.save(autor);
    }

    public Autor update(Long id, Autor autor) {
        autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("AutorController not found"));
        return autorRepo.save(autor);
    }
}
