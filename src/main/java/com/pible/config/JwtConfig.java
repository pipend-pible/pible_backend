package com.pible.config;

import com.pible.config.sercurity.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtUtils JwtUtils(@Value("${security.jwt.key}") String textKey) {
        return new JwtUtils(textKey);
    }
}
