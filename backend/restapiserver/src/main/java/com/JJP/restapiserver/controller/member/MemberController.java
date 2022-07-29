package com.JJP.restapiserver.controller.member;

import com.JJP.restapiserver.domain.dto.MessageResponse;
import com.JJP.restapiserver.domain.dto.member.request.*;
import com.JJP.restapiserver.domain.dto.member.response.TokenRefreshResponse;
import com.JJP.restapiserver.domain.entity.member.RefreshToken;
import com.JJP.restapiserver.exception.TokenRefreshException;
import com.JJP.restapiserver.security.JwtUtils;
import com.JJP.restapiserver.service.member.MemberServiceImpl;
import com.JJP.restapiserver.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    // RereshToken 생성을 위한 Service
    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtUtils jwtUtils;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) { // 아이디, 비밀번호를 body에 담아 전송
        return memberService.login(loginRequest);
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println(signUpRequest.toString());
        return memberService.register(signUpRequest);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogoutRequest logoutRequest) {
        refreshTokenService.deleteByMemberId(logoutRequest.getId());
        return ResponseEntity.ok(new MessageResponse("Log out successful"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getMember)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                }).
                orElseThrow(() -> new TokenRefreshException(requestRefreshToken
                        , "Refresh token is not in database!"));
    }

    @PostMapping("/update/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable Long user_id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return memberService.update(user_id, updateUserRequest);
    }

    @PostMapping("/validity")
    public ResponseEntity<?> checkValidity(@Valid @RequestBody LoginRequest loginRequest) {
        return memberService.checkValidity(loginRequest);
    }

    @PostMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody LoginRequest loginRequest) {
        return memberService.updatePassword(loginRequest);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long user_id) {
        return memberService.findUser(user_id);
    }
}

