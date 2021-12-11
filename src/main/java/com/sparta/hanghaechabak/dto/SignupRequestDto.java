package com.sparta.hanghaechabak.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "아이디는 필수 입니다")
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[0-9a-z]+$" , message = "아이디는 영문, 숫자만 입력할 수 있습니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입니다")
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{3,10}$", message = "비밀번호는 3~10자리, 영문자, 숫자를 섞어야합니다.")
    private String password;

    @Email
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
