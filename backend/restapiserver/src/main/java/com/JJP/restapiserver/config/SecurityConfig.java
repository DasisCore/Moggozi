package com.JJP.restapiserver.config;

import com.JJP.restapiserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // Spring Security를 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService; // 유저 정보 가져오기

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "h2-console/**");
    }

    @Override
    // http 관련 인증 설정 가능
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable().headers().frameOptions().disable()// h2-console 화면 사용하기 위함
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/user").permitAll() // 누구나 접근 가능
                .antMatchers("/").hasRole("USER") // USER, ADMIN 만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 접근 가능
                .anyRequest().authenticated() // 나머지는 권한이 있기만 하면 접근 가능
                .and()
                .formLogin() // 로그인에 대한 설정
                .loginPage("/user/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공시 연결되는 주소
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/user/login") // 로그아웃 성공시 연결되는 주소
                .invalidateHttpSession(true) // 로그아웃시 저장해 둔 세션 제거
        ;
    }

    @Override
    // 로그인 시 필요한 정보를 가져오기
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // 유저 정보는 userService 에서 가져온다
                .passwordEncoder(new BCryptPasswordEncoder()); // 패스워드 인코더는 passwordEncoder(BCrypt 사용)
    }
}
