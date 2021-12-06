package com.sparta.hanghaechabak.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {

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

}
