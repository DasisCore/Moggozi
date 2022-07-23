package com.JJP.restapiserver.controller;

<<<<<<< HEAD
import com.JJP.restapiserver.dto.UsersDto;
import com.JJP.restapiserver.entity.Users;
import com.JJP.restapiserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> b45e4837381461b69922b29797d63c71370d4836

@RestController
@RequestMapping("/user")
public class UserController {
<<<<<<< HEAD
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDto> signup(@Valid @RequestBody UsersDto userDto) {
            return ResponseEntity.ok(userService.register(userDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UsersDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UsersDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
=======

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> userRegister() {
        return ResponseEntity.ok("hello");
>>>>>>> b45e4837381461b69922b29797d63c71370d4836
    }
}
