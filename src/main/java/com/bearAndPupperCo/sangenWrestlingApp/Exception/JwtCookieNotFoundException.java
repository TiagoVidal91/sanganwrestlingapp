package com.bearAndPupperCo.sangenWrestlingApp.Exception;

public class JwtCookieNotFoundException extends RuntimeException {
    public JwtCookieNotFoundException(String message) {
        super(message);
    }
}
