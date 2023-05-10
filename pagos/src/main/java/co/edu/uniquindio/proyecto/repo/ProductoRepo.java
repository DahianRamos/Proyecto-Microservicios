package co.edu.uniquindio.proyecto.repo;

import co.edu.uniquindio.proyecto.model.Pago;
import co.edu.uniquindio.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
}
