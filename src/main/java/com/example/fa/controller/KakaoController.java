package com.example.fa.controller;

import com.example.fa.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;

    @Value("${kakao.kakao_url}")
    private String kakao_url;

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_url}")
    private String redirect_url;


    @GetMapping("/kakao/login")
    public void getKakaoUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reqUrl = kakao_url+ "?client_id="
                + client_id + "&redirect_uri="
                + redirect_url + "&response_type=code";
        response.sendRedirect(reqUrl);
    }

    @ResponseBody
    @GetMapping("/kakao/callback")
    public void  kakaoCallback(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoService.kakaoLogin(code);
    }


}
