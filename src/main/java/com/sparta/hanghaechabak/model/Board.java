package com.sparta.hanghaechabak.model;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}