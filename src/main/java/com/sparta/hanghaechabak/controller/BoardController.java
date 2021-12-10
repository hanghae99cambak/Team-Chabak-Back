package com.sparta.hanghaechabak.controller;

import com.sparta.hanghaechabak.dto.request.BoardRequestDto;
import com.sparta.hanghaechabak.dto.response.BoardResponseDto;
import com.sparta.hanghaechabak.security.UserDetailsImpl;
import com.sparta.hanghaechabak.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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
    //@PostMapping(value = "/api/board", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @PostMapping(value = "/api/board", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE },  headers = ("content-type=multipart/form-data;boundary=032a1ab685934650abbe059cb45d6ff3"))
    public BoardResponseDto boardUpload(
//            @AuthenticationPrincipal UserDetailsImpl userDetails,
//            @RequestPart(value = "boardRequestDto") @Valid BoardRequestDto boardRequestDto,
              @RequestParam("multipartFile") MultipartFile multipartFile
           // MultipartFile multipartFile
    ) throws IOException {
//        return boardService.savePost(boardRequestDto,userDetails.getUser(), multipartFile);
        System.out.println(multipartFile);
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile.getName());
        return null;
    }



    @ApiOperation(value = "게시글 수정")
    @PutMapping("/api/board/detail/{id}")
    public BoardResponseDto boardUpdate(
            @PathVariable Long id,
            @RequestPart(value = "boardRequestDto") BoardRequestDto boardRequestDto,
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
