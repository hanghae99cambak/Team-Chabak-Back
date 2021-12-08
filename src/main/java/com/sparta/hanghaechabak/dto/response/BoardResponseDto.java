package com.sparta.hanghaechabak.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardResponseDto {

    private Long id;
    private String nickname;
    private String content;
    private String location;
    private String image;


}
