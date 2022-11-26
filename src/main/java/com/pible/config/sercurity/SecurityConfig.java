package com.pible.config.sercurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${security.ignore.url}")
    private String[] ignored;

    /**
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private AuthChecker authChecker;

     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .defaultSuccessUrl("/index")
        //        .successHandler(loginSuccessHandler)
        //        .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests().antMatchers(ignored).permitAll();
        //        .and()
        //        .authorizeRequests().antMatchers("/admin/**").hasAuthority(RoleType.ADMIN.getType())
        //        .anyRequest().authenticated()
        //        .and()
        //        .addFilterBefore(new CustomAuthFilter(authChecker, ignored), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
//        http.cors().configurationSource();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
