package com.JJP.restapiserver.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TokenProvider, JwtFilter를 SecurityConfig에 적용할 때 사용할 JwtSecurityConfig
 * Security에 어떤 토큰을 사용하는지에 관한 설정 정보가 들어있다.
 * 즉, Security 로직에 추가할 필터에 대한 설정을 목적으로 하는 class
 */
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        // JwtFilter를 Security Logic에 등록한다.
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
