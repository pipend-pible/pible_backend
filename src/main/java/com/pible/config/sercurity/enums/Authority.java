package com.pible.config.sercurity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {
    USER("01"),
    ADMIN("99");

    private final String authCd;
}
