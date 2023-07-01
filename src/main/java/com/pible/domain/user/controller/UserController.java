package com.pible.domain.user.controller;

import com.pible.common.Constants;
import com.pible.common.annotations.SignUpChecker;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.user.model.UserDto;
import com.pible.domain.user.model.VerifyCodeDto;
import com.pible.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/duplicate/nick-name/{nickName}")
    public boolean checkDuplicateNickName(@PathVariable String nickName) {
        return userService.checkDuplicateNickName(nickName);
    }

    @GetMapping("/duplicate/user-name/{username}")
    public boolean checkDuplicateUserName(@PathVariable(name = "username") String userName) {
        return userService.checkDuplicateUserName(userName);
    }

    @GetMapping("/sign-up/token/{username}")
    public String generateSignUpToken(@PathVariable(name = "username") String userName) {
        return userService.generateSignUpToken(userName);
    }

    @SignUpChecker
    @GetMapping("/sign-up/verify-code/{username}")
    public boolean generateEmailAuthStr(HttpServletRequest request, @PathVariable(name = "username") String userName) {
        checkUserName(request, userName);
        userService.generateEmailAuthStr(userName);
        return true;
    }

    @SignUpChecker
    @PostMapping("/sign-up/verify")
    public boolean verifyEmailAuthStr(HttpServletRequest request, @RequestBody @Valid VerifyCodeDto verifyCodeDto) {
        checkUserName(request, verifyCodeDto.getEmail());
        return userService.verifyEmailAuthStr(verifyCodeDto);
    }

    @SignUpChecker
    @PostMapping("/sign-up")
    public void signUp(HttpServletRequest request, @RequestBody UserDto userDto) {
        checkUserName(request, userDto.getUsername());
        userService.signUp(userDto);
    }

    private void checkUserName(HttpServletRequest request, String userName) {
        if(!userName.equals(request.getAttribute(Constants.USER_NAME))) {
            throw new BusinessException(ResponseCode.NOT_SECURED_SIGN_UP);
        }
    }
}
