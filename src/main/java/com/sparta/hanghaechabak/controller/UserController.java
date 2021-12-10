package com.sparta.hanghaechabak.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.hanghaechabak.dto.HeaderDto;
import com.sparta.hanghaechabak.dto.LoginRequestDto;
import com.sparta.hanghaechabak.dto.SignupRequestDto;
import com.sparta.hanghaechabak.jwt.JwtTokenProvider;
import com.sparta.hanghaechabak.service.KakaoUserService;
import com.sparta.hanghaechabak.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
//    private final JwtTokenProvider jwtTokenProvider;


    @ApiOperation(value = "회원 가입 요청 처리")
    @PostMapping("/user/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
    }


    //로그인 성공
    @ApiOperation(value = "기본 로그인")
    @PostMapping("/user/login")
    public List<Map<String, String>> userLogin(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @PostMapping("/user/nickname/duplicate")
    public Boolean nicknameDuplicate(@RequestBody String nickname) {
        return userService.nicknameDuplicate(nickname);
    }


//    @GetMapping("/oauth/callback/kakao")
//    public List<Map<String, String>> kakaoLogin(@RequestParam String code, HttpServletResponse response) {
//
//        //authorizeCode : 카카오 서버로 부터 받은 인가 코드
//        String token = "";
//        try {
//            token = kakaoUserService.kakaoLogin(code);
//        } catch (Exception ex) {
//            throw new IllegalArgumentException("카카오 로그인 실패하였습니다");
//        }
//        Map<String, String> tokens = new HashMap<>();
//        List<Map<String, String>> all = new ArrayList<>();
//
//        response.setHeader("X-AUTH-TOKEN", token);
//
//        //header에 cookie 저장도 해주고
//        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
//
//        tokens.put("token", token);
//        all.add(tokens);
//
//        return all;
//    }

    @ApiOperation(value = "kakao_callback")
    @GetMapping("/oauth/callback/kakao")
    public HeaderDto kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        System.out.println(code);
        return kakaoUserService.kakaoLogin(code);
    }

}