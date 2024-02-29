package com.inventory.utils;

import java.io.Serializable;
import java.util.HashMap;

public class HttpResponse implements Serializable {
    private final int code;
    private final String message;

    public HttpResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
