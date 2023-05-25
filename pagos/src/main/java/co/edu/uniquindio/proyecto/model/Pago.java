package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
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
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo;

    private double valorPago;
    private String codigoCliente;
    @Column(nullable = false)
    private LocalDateTime fechaPago;
    @Column(nullable = false)
    private TipoPago tipoPago;
    @ElementCollection
    private List<DetallePago> detallePagos;


}
