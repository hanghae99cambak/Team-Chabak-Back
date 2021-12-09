package com.sparta.hanghaechabak.controller;

import com.amazonaws.services.s3.model.JSONOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sparta.hanghaechabak.config.JwtTokenProvider;
import com.sparta.hanghaechabak.dto.LoginRequestDto;
import com.sparta.hanghaechabak.dto.SignupRequestDto;
import com.sparta.hanghaechabak.model.User;
import com.sparta.hanghaechabak.repository.UserRepository;
import com.sparta.hanghaechabak.service.KakaoUserService;
import com.sparta.hanghaechabak.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @ApiOperation(value = "회원 가입 요청 처리")
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }


    //로그인 성공
    @ApiOperation(value= "기본 로그인")
    @PostMapping("/user/login")
    public List<Map<String,String>> userLogin(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
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