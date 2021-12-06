package com.sparta.hanghaechabak.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardRequestDto {
    private String location;
    private String content;
}
