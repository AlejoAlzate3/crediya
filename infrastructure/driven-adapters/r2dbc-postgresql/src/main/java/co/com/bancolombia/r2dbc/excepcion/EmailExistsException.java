package co.com.bancolombia.r2dbc.excepcion;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String message) {
        super(message);
    }
}
