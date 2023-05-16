package com.pible.config.sercurity.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public UserAuthenticationToken(PibleUser user) {
        super(user, user.getPassword(), user.getAuthorities());
    }
}
