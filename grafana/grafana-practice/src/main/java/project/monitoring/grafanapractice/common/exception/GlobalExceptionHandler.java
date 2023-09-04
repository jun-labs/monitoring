package project.monitoring.grafanapractice.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.monitoring.grafanapractice.common.exception.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> resolveDomainException(
        DomainException exception
    ) {
        ErrorResponse payload = new ErrorResponse(exception.getErrorCodeAndMessage());
        return ResponseEntity.status(payload.getCode())
            .body(payload);
    }
}
