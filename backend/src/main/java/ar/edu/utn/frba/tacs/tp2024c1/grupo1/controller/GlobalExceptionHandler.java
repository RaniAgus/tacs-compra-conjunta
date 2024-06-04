package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.exception.*;
import com.mongodb.MongoCommandException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.util.MongoDbErrorCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        Map<String, List<String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())
                ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "code", HttpStatus.BAD_REQUEST.value(),
                "status", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "errors", Map.of("global", globalErrors, "fields", fieldErrors)
        ));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException ex) {
        return buildDefaultException(HttpStatus.BAD_REQUEST, "Invalid file");
    }

    @ExceptionHandler({TokenNoValido.class, CredencialesInvalidas.class})
    public ResponseEntity<Object> handleUnauthorized(Exception ex) {
        return buildDefaultException(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleForbidden(Exception ex) {
        return buildDefaultException(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNotFoundException(NoSuchElementException ex) {
        return buildDefaultException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({
            ArticuloFinalizadoException.class,
            CompradorInvalidoException.class,
            CupoArticuloExcedidoException.class,
            LimiteCompradores.class,
            UsuarioYaExiste.class,
    })
    public ResponseEntity<Object> handleConflict(Exception ex) {
        return buildDefaultException(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        if (ex.getCause() instanceof MongoCommandException e
            && MongoDbErrorCodes.isDataIntegrityViolationCode(e.getErrorCode())) {
            return buildDefaultException(HttpStatus.CONFLICT, "El recurso fue modificado. Por favor, intente nuevamente");
        }
        return handleException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Internal server error: {}", ex.getMessage(), ex);
        return buildDefaultException(HttpStatus.INTERNAL_SERVER_ERROR, "Algo salió mal");
    }

    private ResponseEntity<Object> buildDefaultException(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of(
                "code", status.value(),
                "status", status.getReasonPhrase(),
                "error", message
        ));
    }
}
