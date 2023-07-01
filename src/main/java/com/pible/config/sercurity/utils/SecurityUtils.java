package com.pible.config.sercurity.utils;

import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.config.sercurity.model.PibleUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtils {

    public static Long getUserId() {
        PibleUser user = (PibleUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(user.getUserId()).orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_USER_ID));
    }
}
