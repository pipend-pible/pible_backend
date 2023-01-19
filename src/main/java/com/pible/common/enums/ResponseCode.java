package com.pible.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(0, "성공했습니다."),
    FAIL(900, "예상치못한 이유로 실패했습니다."),
    NO_DATA(901, "필수 데이터가 없습니다.");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
