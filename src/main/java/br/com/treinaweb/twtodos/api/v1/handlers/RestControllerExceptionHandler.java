package br.com.treinaweb.twtodos.api.v1.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.treinaweb.twtodos.api.v1.dtos.ErrorResponse;
import br.com.treinaweb.twtodos.api.v1.dtos.ValidationErrorResponse;
import br.com.treinaweb.twtodos.core.exceptions.ModelNotFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(
        ModelNotFoundException exception,
        WebRequest request
    ) {
        var status = HttpStatus.NOT_FOUND;
        var body = ErrorResponse.builder()
            .path(getPath(request))
            .cause(exception.getClass().getSimpleName())
            .error(status.getReasonPhrase())
            .status(status.value())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(status).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        var body = ValidationErrorResponse.builder()
            .path(getPath(request))
            .cause(ex.getClass().getSimpleName())
            .error(status.getReasonPhrase())
            .status(status.value())
            .message("Validation error")
            .timestamp(LocalDateTime.now())
            .errors(convertFieldErrorsToMap(ex.getBindingResult().getFieldErrors()))
            .build();
        return ResponseEntity.status(status).body(body);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    private Map<String, List<String>> convertFieldErrorsToMap(List<FieldError> fieldErrors) {
        var errors = new HashMap<String, List<String>>();
        for (FieldError fieldError : fieldErrors) {
            var fieldName = fieldError.getField();
            var errorMessage = fieldError.getDefaultMessage();
            if (errors.containsKey(fieldName)) {
                errors.get(fieldName).add(errorMessage);
            } else {
                errors.put(fieldName, new ArrayList<>(List.of(errorMessage)));
            }
        }
        return errors;
    }

}
