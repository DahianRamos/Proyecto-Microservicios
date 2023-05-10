package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.model.TipoPago;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagoQueryDTO {

    @EqualsAndHashCode.Include
    private String pagoID;
    private double valorPago;
    private String clienteID;
    private LocalDateTime fechaPago;
    private TipoPago tipoPago;
    private Long codigoProducto;


}