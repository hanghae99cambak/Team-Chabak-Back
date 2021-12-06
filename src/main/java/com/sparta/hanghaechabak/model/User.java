package com.sparta.hanghaechabak.model;

import com.sparta.hanghaechabak.utils.Timestamped;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter // get 함수를 일괄적으로 만들어줍니다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String kakaoId;


}
