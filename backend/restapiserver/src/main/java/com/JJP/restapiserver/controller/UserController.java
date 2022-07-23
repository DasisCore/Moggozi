package com.JJP.restapiserver.controller;

import com.JJP.restapiserver.dto.UsersDto;
import com.JJP.restapiserver.entity.Users;
import com.JJP.restapiserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
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
    }
}
