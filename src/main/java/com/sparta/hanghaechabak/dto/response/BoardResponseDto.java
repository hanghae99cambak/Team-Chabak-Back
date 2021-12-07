package com.sparta.hanghaechabak.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String author;
    private String content;
    private String location;
    private String image;
}
