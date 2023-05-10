package co.edu.uniquindio.proyecto.servicio.excepciones;

public class ClienteNoEncontradoException extends RuntimeException{

    public ClienteNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
