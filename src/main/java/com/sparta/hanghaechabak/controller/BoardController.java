package com.sparta.hanghaechabak.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@Api(tags = {"Board"})
public class BoardController {

    @ApiOperation(value = "게시글 등록")
    @PostMapping("/api/board")
    public void boardUpload(
    ) {
    }

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/api/board/detail/{id}")
    public void boardUpdate(
    ) {
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/api/board")
    public void boardGetList(
    ) {
    }


    @ApiOperation(value = "게시글 상세조회")
    @GetMapping("/api/board/detail/{id}")
    public void boardGetDetail(
    ) {
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping ("/api/board/detail/{id}")
    public void boardDelete(
    ) {
    }
}
