package com.sparta.hanghaechabak.repository;

import com.sparta.hanghaechabak.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
