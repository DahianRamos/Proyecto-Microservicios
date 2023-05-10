package co.edu.uniquindio.proyecto.servicio.excepciones;

public class PagoNoEncontradoException extends RuntimeException{
    public PagoNoEncontradoException(String mensaje) { super(mensaje);
    }
}
