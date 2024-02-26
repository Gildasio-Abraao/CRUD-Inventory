package com.inventory.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class HttpUtils {
    private final String message;

    public HttpUtils(String message) {
        this.message = message;
    }

    public HashMap<String, String> getResponse() {
        HashMap<String, String> response = new HashMap<String, String>();
        response.put("message", message);

        return response;
    }
}
