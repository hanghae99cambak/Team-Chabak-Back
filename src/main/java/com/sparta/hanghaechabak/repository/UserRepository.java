package com.sparta.hanghaechabak.repository;

import com.sparta.hanghaechabak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    // KakaoId 사용자 조회
    Optional<User> findByKakaoId(Long kakaoId);
    // Email 주소가 같은 사용자 조회
    Optional<User> findByEmail(String email);
}