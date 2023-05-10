package co.edu.uniquindio.proyecto.repo;
import co.edu.uniquindio.proyecto.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends JpaRepository<Autor, Long> {
}
