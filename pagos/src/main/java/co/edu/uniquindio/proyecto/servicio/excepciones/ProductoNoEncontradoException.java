package co.edu.uniquindio.proyecto.servicio.excepciones;

public class ProductoNoEncontradoException extends RuntimeException{
    public ProductoNoEncontradoException(String mensaje) { super(mensaje);
    }
}
