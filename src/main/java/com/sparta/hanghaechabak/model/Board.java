package com.sparta.hanghaechabak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaechabak.dto.request.BoardRequestDto;
import com.sparta.hanghaechabak.utils.Timestamped;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String content;

    private String image;

    private int like_count;


    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public void updateImage(BoardRequestDto boardRequestDto, String imageUrl) {
        this.content = boardRequestDto.getContent();
        this.location = boardRequestDto.getLocation();
        if(imageUrl != "") {
            this.image = imageUrl;
        }
    }

}