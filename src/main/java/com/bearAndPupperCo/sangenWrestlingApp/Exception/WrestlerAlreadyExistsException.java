package com.bearAndPupperCo.sangenWrestlingApp.Exception;

public class WrestlerAlreadyExistsException extends RuntimeException {

    private final String errorCode;
    private final String userMessage;

    public WrestlerAlreadyExistsException(String message, String errorCode, String userMessage) {
        super(message);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
