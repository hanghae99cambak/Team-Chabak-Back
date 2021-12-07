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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto savePost(BoardRequestDto boardRequestDto, User user) {
        if (user != null) throw new ErrorNotFoundUserException(ErrorCode.NOT_LOGIN);
        Board post = Board.builder()
                .author(user.getNickname())
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .user(user)
                .build();
        boardRepository.save(post);

        return  BoardResponseDto
                .builder()
                .id(post.getId())
                .author(post.getAuthor())
                .location(post.getLocation())
                .content(post.getContent())
                .build();
    }

    @Transactional
    public BoardResponseDto modify(Long boardId, BoardRequestDto boardRequestDto, User user) {
        Board modifyBoard = boardRepository.findById(boardId).orElseThrow(() ->  new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!modifyBoard.getUser().getId().equals(user.getId())) throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_MODIFY);

        Board newUpdateBoard = modifyBoard.builder()
                .id(boardId)
                .author(user.getNickname())
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .user(user)
                .build();


        return BoardResponseDto.builder()
                .id(newUpdateBoard.getId())
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

    public Page<BoardResponseDto>findAllPaging(int nowPage, int count) {
        PageRequest pageRequest =  PageRequest.of(nowPage, count,Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Board> page = boardRepository.findAll(pageRequest);
        return page.map(boardInfo -> new BoardResponseDto(boardInfo.getId(), boardInfo.getAuthor(), boardInfo.getContent(), boardInfo.getLocation()));
    }
}
