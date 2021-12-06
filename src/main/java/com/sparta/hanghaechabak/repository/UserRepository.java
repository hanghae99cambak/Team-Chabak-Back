package com.sparta.hanghaechabak.repository;

import com.sparta.hanghaechabak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}