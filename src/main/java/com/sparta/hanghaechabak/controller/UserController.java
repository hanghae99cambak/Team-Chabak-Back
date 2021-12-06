package com.sparta.hanghaechabak.controller;

import com.sparta.hanghaechabak.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"User"})
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/user/signup")
    public void userSignUp(
    ) {
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/user/login")
    public void userLogin(
    ) {
    }

    @ApiOperation(value = "로그아웃")
    @GetMapping("/logout")
    public void userLogout(
    ) {
    }

    @ApiOperation(value = "닉네임 중복확인")
    @PostMapping("/user/nickname/duplicate")
    public void userNicknameDuplicate(
    ) {
    }

}
