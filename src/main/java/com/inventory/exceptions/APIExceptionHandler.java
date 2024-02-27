package com.inventory.exceptions;

import com.inventory.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DocumentNotFoundException.class)
    public final ResponseEntity<HashMap<String, String>> handleNotFoundException(DocumentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpUtils(exception.getMessage()).setBodyResponse());
    }

    @ExceptionHandler(GenericException.class)
    public final ResponseEntity<HashMap<String, String>> handleGenericException(GenericException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpUtils(exception.getMessage()).setBodyResponse());
    }

    @ExceptionHandler(ArgumentNotValidException.class)
    public final ResponseEntity<HashMap<String, String>> handleMethodArgumentNotValidException(ArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpUtils(exception.getMessage()).setBodyResponse());
    }
}
