package com.sparta.hanghaechabak.model;

import com.sparta.hanghaechabak.utils.Timestamped;
import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(unique = true)
    private Long kakaoId;

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
    }

    public User(String nickname, String password, String email, Long kakaoId) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }
}