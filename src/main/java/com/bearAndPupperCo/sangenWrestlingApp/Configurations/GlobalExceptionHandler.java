package com.bearAndPupperCo.sangenWrestlingApp.Configurations;

import com.bearAndPupperCo.sangenWrestlingApp.Error.CustomError;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.JwtCookieNotFoundException;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError<String>> handleGenericException(Exception ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError("An error occurred", request, HttpStatus.INTERNAL_SERVER_ERROR, ex,
                "An error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrestlerAlreadyExistsException.class)
    public ResponseEntity<CustomError<String>> handleWrestlerAlreadyExistsException(WrestlerAlreadyExistsException ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError(ex.getMessage(), request, HttpStatus.CONFLICT, ex, ex.getUserMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtCookieNotFoundException.class)
    public ResponseEntity<?> handleJwtCookieNotFoundException(JwtCookieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT cookie not found");
    }

    private <T extends Throwable> CustomError<String> createCustomError(
            String customMessage, WebRequest request, HttpStatus httpStatus, T ex, String userMessage) {
        return new CustomError<>(
                ex.getMessage(),
                request.getDescription(false),
                httpStatus.value(),
                userMessage
        );
    }

    private <T extends Throwable> void logError(T ex) {
        // Log the stack trace using the logger
        // You can customize the logging level and format based on your needs
        logger.error("An exception occurred:", ex);
    }
}
