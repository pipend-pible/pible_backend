package com.pible.common.exception;

public class JWTOmissionException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public JWTOmissionException(String message) {
        super(message);
    }
}
