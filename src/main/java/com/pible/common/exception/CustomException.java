package com.pible.common.exception;

import com.pible.common.enums.ResponseCode;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }
}
