package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Respuesta <T>{

    private String mensaje;
    private T dato;

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
    }
}
