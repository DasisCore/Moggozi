package com.JJP.restapiserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // 회원가입
    @GetMapping("/register")
    public ResponseEntity<String> userRegister() {
        return ResponseEntity.ok("hello");
    }
}
