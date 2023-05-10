package co.edu.uniquindio.proyecto.servicio.excepciones;

public class PrestamoNoEncontradoException extends RuntimeException{

    public PrestamoNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
