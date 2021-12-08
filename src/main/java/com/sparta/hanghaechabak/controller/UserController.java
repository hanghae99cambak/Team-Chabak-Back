package com.sparta.hanghaechabak.controller;

import com.amazonaws.services.s3.model.JSONOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sparta.hanghaechabak.dto.SignupRequestDto;
import com.sparta.hanghaechabak.service.KakaoUserService;
import com.sparta.hanghaechabak.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }


    // 회원 가입 요청 처리
    @ApiOperation(value = "회원 가입 요청 처리")
    @PostMapping("/user/signup")
    public void registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }

    @ApiOperation(value = "kakao_callback")
    @GetMapping("/user/kakao/callback")
    public void kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
    }


    @PostMapping("/user/nickname/duplicate")
    public Boolean nicknameDuplicate(@RequestBody String nickname) {
        return userService.nicknameDuplicate(nickname);
    }

}