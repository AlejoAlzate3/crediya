package co.com.bancolombia.r2dbc.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Map<String, String>> handleEmailExists(EmailExistsException ex) {
        return Mono.just(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return Mono.just(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<Map<String, String>> handleGeneric(Exception ex) {
        return Mono.just(Map.of("error", "Ocurrió un error inesperado"));
    }
}
