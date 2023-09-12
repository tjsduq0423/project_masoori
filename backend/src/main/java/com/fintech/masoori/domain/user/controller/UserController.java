package com.fintech.masoori.domain.user.controller;

import java.util.Optional;

import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.masoori.domain.user.dto.SendEmailReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.service.UserService;
import com.fintech.masoori.global.error.ErrorCode;
import com.fintech.masoori.global.error.ErrorResponse;
import com.fintech.masoori.global.redis.RedisService;
import com.fintech.masoori.global.util.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
@Tag(name = "user", description = "회원 API")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final RedisService redisService;

    @Operation(summary = "회원가입 시 이메일 인증 API")
    @PostMapping("/email/signup")
    public ResponseEntity<?> sendSignupEmailCode(
            @Parameter(description = "회원 이메일", required = true)
            @RequestBody SendEmailReq sendEmailReq) {
        String email = sendEmailReq.getEmail();
        Optional<User> findUser = userService.findByEmail(email);
        // 사용자 존재 => 중복 => 가입 안됨
        if (findUser.isPresent()) {
            ErrorResponse response = ErrorResponse.of(ErrorCode.EMAIL_DUPLICATION);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
        }
        return sendEmail(email);
    }

    @Operation(summary = "비밀번호 재설정 시 이메일 인증 API")
    @PostMapping("/email/password")
    public ResponseEntity<?> sendPasswordEmailCode(
            @Parameter(description = "회원 이메일", required = true)
            @RequestBody SendEmailReq sendEmailReq) {
        String email = sendEmailReq.getEmail();
        Optional<User> findUser = userService.findByEmail(email);
        // 사용자 존재x => 없는 사용자
        if (!findUser.isPresent()) {
            ErrorResponse response = ErrorResponse.of(ErrorCode.USER_NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
        }
        return sendEmail(email);
    }

    public ResponseEntity<?> sendEmail(String email) {
        // 인증코드 redis 서버에 저장
        String code = emailService.createCode(8);
        redisService.setEmailCode(email, code);
        try {
            emailService.sendEmail(email, code);
        } catch (MessagingException e) {
            log.debug("이메일 전송 실패", e);
            redisService.deleteEmailCode(email);
            ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증코드 검증 API")
    @PostMapping("/email/check")
    public ResponseEntity<?> verifyEmailCode(
            @Parameter(description = "회원 이메일, 인증 코드", required = true)
            @RequestBody EmailCheckReq emailCheckReq) {
        String inputCode = emailCheckReq.getCode();
        String savedCode = redisService.getEmailCode(emailCheckReq.getEmail());
        if (!inputCode.equals(savedCode)) {
            ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_AUTH_CODE);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
        }
        return ResponseEntity.ok().build();
    }


}
