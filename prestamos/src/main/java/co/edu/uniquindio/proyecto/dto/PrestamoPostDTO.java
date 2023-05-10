package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoPostDTO(String clienteID, List<String> isbnLibros, LocalDateTime fechaDevolucion) {
}
