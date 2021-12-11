package com.sparta.hanghaechabak.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String email;
}
