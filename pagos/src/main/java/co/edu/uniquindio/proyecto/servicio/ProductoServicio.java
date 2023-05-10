package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.model.Producto;
import co.edu.uniquindio.proyecto.repo.ProductoRepo;
import co.edu.uniquindio.proyecto.servicio.excepciones.ProductoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoServicio {

    private final ProductoUtils productoUtils;
    private final ProductoRepo productoRepo;

    public void delete(long id) {
        productoUtils.getProducto(id);
        productoRepo.deleteById(id);
    }

    public Producto findById(Long id) {
        return productoRepo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));
    }

    public List<ProductoDTO> findAll() {
        return productoRepo.findAll().stream().map(producto -> productoUtils.convertirProductoDTO(producto)).collect(Collectors.toList());
    }

    public Producto save(Producto producto) {
        return productoRepo.save(producto);
    }

    public Producto update(Long id, Producto producto) {
        productoRepo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));
        return productoRepo.save(producto);
    }
}
