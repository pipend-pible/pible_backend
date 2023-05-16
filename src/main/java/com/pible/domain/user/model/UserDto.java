package com.pible.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String nickName;
    private String verifyAgeYn;
    private String emailCheckYn;
    private String privacyAgreeYn;
    private String usageYn;
    private String userState;
    private String bankAccountYn;
    private String profileMsg;
}
