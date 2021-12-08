package com.sparta.hanghaechabak.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.hanghaechabak.dto.SignupRequestDto;
import com.sparta.hanghaechabak.service.KakaoUserService;
import com.sparta.hanghaechabak.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @ApiOperation(value = "회원 로그인 페이지")
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @ApiOperation(value = "회원 가입 페이지")
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @ApiOperation(value = "회원 가입 요청 처리")
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        System.out.println(requestDto.getAdminToken());
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }

    @ApiOperation(value = "kakao_callback")
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}