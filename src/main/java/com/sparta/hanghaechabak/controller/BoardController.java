package com.sparta.hanghaechabak.controller;

import com.sparta.hanghaechabak.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@Api(tags = {"Board"})
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

   /* @ApiOperation(value = "게시글 등록")
    @PostMapping("/api/board")
    public void boardUpload(@RequestBody @Valid BoardRequestDto boardRequestDto, ,@AuthenticationPrincipal CustomUserDetail userDetail) {
                            boardService.savePost(boardRequestDto, userDetail.getUser());
    }*/

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/api/board/detail/{id}")
    public void boardUpdate(@PathVariable Long id) {
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/api/board")
    public void boardGetList() {
    }


    @ApiOperation(value = "게시글 상세조회")
    @GetMapping("/api/board/detail/{id}")
    public void boardGetDetail() {
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/api/board/detail/{id}")
    public void boardDelete() {
    }
}
