package com.sparta.hanghaechabak.repository;

import com.sparta.hanghaechabak.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
//    List<Board> findAllByIdOrderByDesc();
}
