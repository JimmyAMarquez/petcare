package com.franklin.model.util;

public enum RequestType {
    GET("GET"),PUT("PUT"),POST("POST"),DELETE("DELETE");
    private final String name;
    private RequestType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
