package com.pible.common.exception;

import com.pible.common.enums.ResponseCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final ResponseCode responseCode;
    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
