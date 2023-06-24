package com.pible.domain.user.service;

import com.pible.domain.user.model.UserDto;
import com.pible.domain.user.model.VerifyCodeDto;

public interface UserService {
    boolean checkDuplicateNickName(String nickName);
    boolean checkDuplicateUserName(String userName);
    String generateSignUpToken(String userName);
    String generateEmailAuthStr(String userName);
    boolean verifyEmailAuthStr(VerifyCodeDto verifyCodeDto);
    void signUp(UserDto userDto);
}
