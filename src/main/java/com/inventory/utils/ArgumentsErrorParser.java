package com.inventory.utils;

import com.mongodb.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentsErrorParser {
    public Map<String, String> getFields(@NonNull List<ObjectError> errorList) {
        Map<String, String> errors = new HashMap<String, String>();

        errorList.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
