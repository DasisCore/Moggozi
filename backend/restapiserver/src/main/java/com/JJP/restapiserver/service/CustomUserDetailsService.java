package com.JJP.restapiserver.service;

import com.JJP.restapiserver.entity.Users;
import com.JJP.restapiserver.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // DB에서 유저 정보 가져오기
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        return usersRepository.findOneWithAuthoritiesByEmail(email)
                .map(users -> createUser(email, users))
                .orElseThrow(() -> new UsernameNotFoundException(email + ": 등록된 사용자가 아닙니다.  "));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, Users user) {
        if(user.getUser_state() == 1) {
            throw new RuntimeException(email + " 활성화된 사용자가 아닙니다. ");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthoritySet().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
