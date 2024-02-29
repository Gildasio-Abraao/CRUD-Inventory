package com.inventory.exceptions;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {
    private final int status;
    private final String message;
    private final String details;

    public ExceptionResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
