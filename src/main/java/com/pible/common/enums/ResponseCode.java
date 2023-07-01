package com.pible.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(0, "성공했습니다."),
    FAIL(900, "예상치못한 이유로 실패했습니다."),
    NOT_EXIST_ESSENTIAL_DATA(902, "작업에 필요한 필수 정보가 없습니다."),
    NOT_CORRECT_ESSENTIAL_DATA(903, "작업에 필요한 데이터가 정확하지 않습니다."),
    NOT_EXIST_TOKEN(904, "토큰이 존재하지 않습니다."),
    NOT_INVALIDATED_SIGNATURE(905, "토큰 서명이 유효하지 않습니다."),
    MALFORMED_TOKEN(906, "토큰 형식이 올바르지 않습니다."),
    EXPIRED_TOKEN(907, "토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(908, "지원하지 않는 토큰유형입니다."),
    ERROR_JSON_PROCESSING(909, "JSON 데이터 매핑시 에러가 발생했습니다."),
    NOT_EXISTED_AUTHORITY(910, "회원의 권한정보가 없습니다."),
    NOT_EXISTED_USER_ID(911, "로그인한 회원의 아이디 정보가 없습니다."),
    NOT_AUTHORIZED_EMAIL(912, "이메일 인증이 되지 않았습니다."),
    DUPLICATED_USER(913, "중복된 회원입니다."),
    FAILED_UPLOAD_IMAGE(914, "이미지 업로드에 실패했습니다."),
    NOT_SECURED_SIGN_UP(915, "정상적이지 않은 회원가입 접근입니다."),
    NO_DATA(901, "데이터가 존재하지 않습니다.");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
