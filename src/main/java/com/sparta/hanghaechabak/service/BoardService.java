package com.sparta.hanghaechabak.service;

import com.sparta.hanghaechabak.dto.request.BoardRequestDto;
import com.sparta.hanghaechabak.dto.response.BoardResponseDto;
import com.sparta.hanghaechabak.exception.ErrorNotFoundBoardException;
import com.sparta.hanghaechabak.exception.ErrorNotFoundUserException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;
import com.sparta.hanghaechabak.model.Board;
import com.sparta.hanghaechabak.model.User;
import com.sparta.hanghaechabak.repository.BoardRepository;
import com.sparta.hanghaechabak.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final S3Uploader s3Uploader;

    private final String imageDirName = "static";

    @Transactional
    public BoardResponseDto savePost(
            BoardRequestDto boardRequestDto,
            User user,
            MultipartFile multipartFile
    ) throws IOException {

        if(multipartFile == null) {
            throw new NullPointerException("등록하려는 게시글에 이미지가 없습니다.");
        }
        String imageUrl = s3Uploader.upload(multipartFile, imageDirName);
        Board post = Board.builder()
                .nickname(user.getNickname())
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .image(imageUrl)
                .user(user)
                .build();
        boardRepository.save(post);

        return  BoardResponseDto
                .builder()
                .id(post.getId())
                .nickname(post.getNickname())
                .location(post.getLocation())
                .content(post.getContent())
                .image(post.getImage())
                .build();
    }


    @Transactional
    public BoardResponseDto modify(
            Long boardId,
            BoardRequestDto boardRequestDto,
            User user,
            MultipartFile multipartFile
    ) throws IOException {

        Board modifyBoard = boardRepository.findById(boardId).orElseThrow(() -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!modifyBoard.getUser().getId().equals(user.getId()))
            throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_MODIFY);

        BoardResponseDto responseDto = new BoardResponseDto();
        String imageUrl = "";

        if (multipartFile.getSize() != 0) {
            imageUrl = s3Uploader.upload(multipartFile, imageDirName);
            modifyBoard.updateImage(boardRequestDto, imageUrl);

        } else {
            modifyBoard.updateImage(boardRequestDto, imageUrl);
        }

        boardRepository.save(modifyBoard);
        return responseDto.builder()
                .id(modifyBoard.getId())
                .nickname(modifyBoard.getNickname())
                .location(modifyBoard.getLocation())
                .content(modifyBoard.getContent())
                .image(modifyBoard.getImage())
                .build();
    }

    public BoardResponseDto findOne(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));

        return BoardResponseDto.builder()
                .id(board.getId())
                .location(board.getLocation())
                .image(board.getImage())
                .content(board.getContent())
                .nickname(board.getNickname())
                .build();
    }

    @Transactional
    public Long deletePost(Long boardId, Long nowLoginUserId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(
                        () -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!board.getUser().getId().equals(nowLoginUserId)) {
            throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_DELETE);
        }
        boardRepository.deleteById(boardId);
        return boardId;
    }



    public List<BoardResponseDto> findAllPaging() {
        //PageRequest pageRequest = PageRequest.of(nowPage, 3, Sort.by(Sort.Direction.DESC,"createdAt"));
        List<Board> board = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        return board.stream().map(s-> new BoardResponseDto(s.getId(),s.getNickname(),s.getContent(),s.getLocation(),s.getImage())).collect(Collectors.toList());


    }
}
