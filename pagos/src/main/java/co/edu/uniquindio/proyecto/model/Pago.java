package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Pago implements Serializable {
    @Id
    private String codigo;

    private double valorPago;
    private String codigoCliente;
    @Column(nullable = false)
    private LocalDateTime fechaPago;
    @Column(nullable = false)
    private TipoPago tipoPago;

    @ElementCollection
    private List<Long> codigoProductos;


}
