package com.sparta.hanghaechabak.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoardRequestDto {

    private String location;

    @NotBlank(message = "내용란에 글을 작성해주세요")
    @Size(min = 1, max = 100)
    private String content;

    private String image;
}
