package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.CredencialesInvalidas;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.LimiteCompradores;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.TokenNoValido;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.UsuarioYaExiste;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
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

        return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "errors", Map.of("global", globalErrors, "fields", fieldErrors)
        ));
    }

    @ExceptionHandler(TokenNoValido.class)
    public ResponseEntity<Object> handleTokenNoValido(TokenNoValido ex) {
        return buildDefaultException(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidas.class)
    public ResponseEntity<Object> handleCredencialesInvalidas(CredencialesInvalidas ex) {
        return buildDefaultException(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(UsuarioYaExiste.class)
    public ResponseEntity<Object> handleUsuarioYaExiste(UsuarioYaExiste ex) {
        return buildDefaultException(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(LimiteCompradores.class)
    public ResponseEntity<Object> handleLimiteCompradores(LimiteCompradores ex) {
        return buildDefaultException(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        return buildDefaultException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Object> buildDefaultException(HttpStatus status, String message) {
        return ResponseEntity.status(status.value()).body(Map.of(
                "code", status.value(),
                "status", status.getReasonPhrase(),
                "error", message
        ));
    }
}
