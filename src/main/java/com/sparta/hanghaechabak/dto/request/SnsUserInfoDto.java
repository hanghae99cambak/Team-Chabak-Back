package com.sparta.hanghaechabak.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SnsUserInfoDto {

    private Long id;

    private String nickname;

    private String email;

    private String profile;
}
