package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@Data

public class DetallePago implements Serializable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    //private String codigo = UUID.randomUUID().toString();
    @ToString.Exclude
    private double precioProducto;
    @ToString.Exclude
    private Long idProducto;
    @ToString.Exclude
    private Integer unidades;

    @Builder
    public DetallePago(double precioProducto,  Long idProducto, Integer unidades) {
        this.precioProducto = precioProducto;
        this.idProducto = idProducto;
        this.unidades = unidades;
    }
}
