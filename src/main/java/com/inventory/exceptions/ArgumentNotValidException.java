package com.inventory.exceptions;

import java.io.Serializable;
import java.util.Map;

public class ArgumentNotValidException implements Serializable {
    private final int status;
    private final String message;
    private final Map<String, String> invalidFields;
    private final String endpoint;

    public ArgumentNotValidException(int status, String message, Map<String, String> invalidFields, String endpoint) {
        this.status = status;
        this.message = message;
        this.invalidFields = invalidFields;
        this.endpoint = endpoint;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getInvalidFields() {
        return invalidFields;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
