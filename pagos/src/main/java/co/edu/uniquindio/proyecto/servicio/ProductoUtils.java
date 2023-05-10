package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.model.Producto;
import co.edu.uniquindio.proyecto.repo.ProductoRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.ProductoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductoUtils {

    private final ProductoRepo productoRepo;

    public Producto getProducto(long id) {
        return productoRepo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("El Producto no existe"));
    }

    public ProductoDTO convertirProductoDTO(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre());
    }
}
