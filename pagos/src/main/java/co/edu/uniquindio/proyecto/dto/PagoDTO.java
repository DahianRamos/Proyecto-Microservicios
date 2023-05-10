package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PagoDTO(String pagoId, double valorPago, String codigoCliente, LocalDateTime fechaPago, List<Long> codigoProductos ) {
}
