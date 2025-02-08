package microservice.authenticationservice.com.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import microservice.authenticationservice.com.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> constraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<String>> responseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getReason())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> exception(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse<String>> malformedJwtException(MalformedJwtException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<String>> signatureException(SignatureException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> ExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.<String>builder()
                .data(null)
                .message(e.getMessage())
                .build());
    }
}
