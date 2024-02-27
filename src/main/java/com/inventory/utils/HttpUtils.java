package com.inventory.utils;

import java.util.HashMap;

public class HttpUtils {
    private final String message;

    public HttpUtils(String message) {
        this.message = message;
    }

    public HashMap<String, String> setBodyResponse() {
        HashMap<String, String> response = new HashMap<String, String>();
        response.put("message", message);

        return response;
    }
}
