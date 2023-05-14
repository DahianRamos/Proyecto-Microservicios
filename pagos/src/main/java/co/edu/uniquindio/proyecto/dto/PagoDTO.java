package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.model.DetallePago;
import co.edu.uniquindio.proyecto.model.TipoPago;

import java.time.LocalDateTime;
import java.util.List;

public record PagoDTO(
                      double valorPago,
                      String codigoCliente,
                      TipoPago tipoPago,
                      List<DetallePagoDTO> detallePagos ) {
}
