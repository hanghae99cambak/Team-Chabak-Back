package com.sparta.hanghaechabak.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class BoardResponseDto {

    private Long id;
    private String author;
    private String content;
    private String location;
    private String image;

    public BoardResponseDto(Long id, String author, String content, String location) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.location = location;
    }
    @Builder
    public BoardResponseDto(Long id, String author, String content, String location, String image) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.location = location;
        this.image = image;
    }
}
