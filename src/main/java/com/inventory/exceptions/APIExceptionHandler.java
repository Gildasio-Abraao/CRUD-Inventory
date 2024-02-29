package com.inventory.exceptions;

import com.inventory.utils.ArgumentsErrorParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Map;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(DocumentNotFoundException exception, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ExceptionResponse(
                            HttpStatus.NOT_FOUND.value(),
                            exception.getMessage(),
                            request.getDescription(false)
                        )
                );
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ExceptionResponse> handleInternalServerException(InternalServerException exception, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ExceptionResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                exception.getMessage(),
                                request.getDescription(false)
                        )
                );
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArgumentsErrorParser argumentsErrorParser = new ArgumentsErrorParser();
        Map<String, String> invalidFields = argumentsErrorParser.getFields(exception.getBindingResult().getAllErrors());

        return ResponseEntity
                .status(status.value())
                .body(
                        new ArgumentNotValidException(
                                status.value(),
                                "Failed to validate some fields!",
                                invalidFields,
                                request.getDescription(false)
                        )
                );
    }
}
