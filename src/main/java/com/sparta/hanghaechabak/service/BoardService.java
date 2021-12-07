package com.sparta.hanghaechabak.service;

import com.sparta.hanghaechabak.dto.request.BoardRequestDto;
import com.sparta.hanghaechabak.dto.response.BoardResponseDto;
import com.sparta.hanghaechabak.exception.ErrorNotFoundBoardException;
import com.sparta.hanghaechabak.exception.ErrorNotFoundUserException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;
import com.sparta.hanghaechabak.model.Board;
import com.sparta.hanghaechabak.model.User;
import com.sparta.hanghaechabak.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto savePost(BoardRequestDto boardRequestDto, User user) {
        Board post = Board.builder()
                .author(user.getNickname())
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .image(boardRequestDto.getImage())
                .user(user)
                .build();
        boardRepository.save(post);

        return  BoardResponseDto
                .builder()
                .id(post.getId())
                .author(post.getAuthor())
                .location(post.getLocation())
                .content(post.getContent())
                .image(post.getImage())
                .build();
    }

    public BoardResponseDto modify(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board modifyBoard = boardRepository.findById(boardId).orElseThrow(() ->  new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!modifyBoard.getUser().getId().equals(user.getId())) throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_MODIFY);

        Board newUpdateBoard = modifyBoard.builder()
                .id(boardId)
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .image(boardRequestDto.getImage())
                .user(user)
                .build();


        return BoardResponseDto.builder()
                .id(newUpdateBoard.getId())
                .image(newUpdateBoard.getImage())
                .content(newUpdateBoard.getContent())
                .location(newUpdateBoard.getLocation())
                .author(newUpdateBoard.getAuthor())
                .build();

    }

    public BoardResponseDto findOne(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));

        return BoardResponseDto.builder()
                .id(board.getId())
                .location(board.getLocation())
                .image(board.getImage())
                .content(board.getContent())
                .author(board.getAuthor())
                .build();
    }

    public Long deletePost(Long boardId, Long nowLoginUserId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!board.getUser().getId().equals(nowLoginUserId)) throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_DELETE);
        boardRepository.deleteById(boardId);
        return boardId;
    }

    public Page<Board> findAllPaging(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
