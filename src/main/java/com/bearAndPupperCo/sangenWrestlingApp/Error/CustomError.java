package com.bearAndPupperCo.sangenWrestlingApp.Error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomError<T> {

    private final String cause;
    private final String path;
    private final int httpStatus;
    private final LocalDateTime timestamp;
    private final String userMessage;

    public CustomError(String cause, String path, int httpStatus,
                       String userMessage ) {
        this.cause = cause;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getPath() {
        return path;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getCause() {
        return cause;
    }

    public static void main(String[] args) {
        // Example usage
        CustomError<String> error = new CustomError<>(
                "Custom error message", "/api/someEndpoint", 500,
                "An error occurred");
        System.out.println(error);
    }
}
