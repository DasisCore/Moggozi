package com.JJP.restapiserver.controller;


import com.JJP.restapiserver.dto.UserDto;
import com.JJP.restapiserver.entity.User;
import com.JJP.restapiserver.service.UserService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/user")
@NoArgsConstructor
public class RegistrationRestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String Success = "SUCCESS"; // 성공 응답
    private static final String Fail = "FAIL"; // 실패 응답

    @Autowired
    private UserService userService;

//    @Autowired
    //private SecurityUserService securityUserService;

//    @Autowired
//    private MessageSource messages;

//    @Autowired
//    private JavaMailSender mailSender;

//    @Autowired
//    private ApplicationEventPublisher eventPublisher;

    // 회원 가입
    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        logger.debug("정보를 입력 받아 유저 생성 중: {}", userDto);
        final User registeredUser = userService.registerUser(userDto);
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser));
        if(registeredUser != null) {
            return new ResponseEntity<String>(Success, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(Fail, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
//
//    // 패스워드 초기화
//    @PostMapping("resetPassword")
//    public ReponseEntity<String> resetPassword(@RequestParam("email") final String email) {
//        final User user = userService.findUserByEmail(email);
//        if(user != null) {
//            final String token = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForUser(user, token);
//            mailSender.send(constructResetTokenEmail(token, user));
//        } else {
//            return new ResponseEntity<String>(Fail, HttpStatus.SERVICE_UNAVAILABLE);
//        }
//    }
//
//    // 패스워드 저장
//    @PostMapping("savePassword")
//    public ResponseEntity<String> savePassword(@Valid @RequestBody PasswordDto passwordDto) {
//        final String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());
//
//    }


}
