package com.pible.config.sercurity.provider;

import com.pible.config.sercurity.model.PibleUser;
import com.pible.config.sercurity.model.UserAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        PibleUser user = (PibleUser) userDetailsService.loadUserByUsername(userId);
        if (user == null) {
            throw new BadCredentialsException("유저 확인 불가");
        }

        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비말번호 불일치");
        }

        return new UserAuthenticationToken(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
