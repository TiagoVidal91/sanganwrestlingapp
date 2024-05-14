package com.bearAndPupperCo.sangenWrestlingApp.Configurations;

import com.bearAndPupperCo.sangenWrestlingApp.Error.CustomError;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.JwtCookieNotFoundException;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrestlerAlreadyExistsException;
import com.bearAndPupperCo.sangenWrestlingApp.Exception.WrongParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError<String>> handleGenericException(Exception ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError(GENERAL_ERROR_MSG, request, HttpStatus.INTERNAL_SERVER_ERROR, ex,
                GENERAL_ERROR_MSG);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrestlerAlreadyExistsException.class)
    public ResponseEntity<CustomError<String>> handleWrestlerAlreadyExistsException(WrestlerAlreadyExistsException ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError(ex.getMessage(), request, HttpStatus.CONFLICT, ex, null);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtCookieNotFoundException.class)
    public ResponseEntity<?> handleJwtCookieNotFoundException(JwtCookieNotFoundException ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError(ex.getMessage(), request, HttpStatus.CONFLICT, ex, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JWT_COOKIE_NOT_FOUND_MSG);
    }

    @ExceptionHandler(WrongParamException.class)
    public ResponseEntity<?> handleWrongParamException(WrongParamException ex, WebRequest request) {
        logError(ex);
        CustomError<String> error = createCustomError(ex.getMessage(), request, HttpStatus.EXPECTATION_FAILED, ex, null);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(WRONG_PARAM_MSG);
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
