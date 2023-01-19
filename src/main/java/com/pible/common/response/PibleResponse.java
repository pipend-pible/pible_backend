package com.pible.common.response;

import com.pible.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PibleResponse {
    private Integer code;
    private String message;
    private Object body;

    public PibleResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public PibleResponse(ResponseCode responseCode, Object body) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.body = body;
    }
}
