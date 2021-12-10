package com.sparta.hanghaechabak.controller;

import com.sparta.hanghaechabak.dto.request.BoardRequestDto;
import com.sparta.hanghaechabak.dto.response.BoardResponseDto;
import com.sparta.hanghaechabak.security.UserDetailsImpl;
import com.sparta.hanghaechabak.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = {"Board"})
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 등록")
    @PostMapping("/api/board")
    public BoardResponseDto boardUpload(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(value = "data") @Valid BoardRequestDto boardRequestDto,
            @RequestPart(value = "multipartFile") MultipartFile multipartFile
    ) throws IOException {
        return boardService.savePost(boardRequestDto,userDetails.getUser(), multipartFile);
    }


    @ApiOperation(value = "게시글 수정")
    @PutMapping("/api/board/detail/{id}")
    public BoardResponseDto boardUpdate(
            @PathVariable Long id,
            @RequestPart(value = "data") BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(required = false) MultipartFile multipartFile
    ) throws IOException {
        return boardService.modify(id,boardRequestDto,userDetails.getUser(), multipartFile);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/api/board")
    public List<BoardResponseDto> boardGetList(
    ) {
        return boardService.findAllPaging();
    }


    @ApiOperation(value = "게시글 상세조회")
    @GetMapping("/api/board/detail/{id}")
    public BoardResponseDto boardGetDetail(
            @PathVariable Long id
    ) {
        return boardService.findOne(id);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping ("/api/board/detail/{id}")
    public Long boardDelete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return boardService.deletePost(id,userDetails.getUser().getId());
    }

}
