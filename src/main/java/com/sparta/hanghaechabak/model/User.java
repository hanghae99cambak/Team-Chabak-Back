package com.sparta.hanghaechabak.model;

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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    public User(String nickname, String password, String email, UserRoleEnum role) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = null;
    }

    public User(String nickname, String password, String email, UserRoleEnum role, Long kakaoId) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }
}