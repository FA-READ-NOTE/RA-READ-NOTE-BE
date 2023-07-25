package com.example.fa.controller;

import com.example.fa.dto.UserLoginDto;
import com.example.fa.dto.UserSignUpDto;
import com.example.fa.entity.User;
import com.example.fa.service.KakaoService;
import com.example.fa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    // 테스트 용
    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().body("test");
    }

    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity signup(@Valid @RequestBody UserSignUpDto dto) throws Exception {
        User user = userService.signup(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("sucess", user);
        return ResponseEntity.ok().body(result);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto dto) {
        String jwtToken = userService.login(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-AUTH-TOKEN", jwtToken);

        log.info("headers : " + headers);

        return ResponseEntity.ok().headers(headers).body("login success");
    }


}
