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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    public BoardResponseDto modify(
            Long boardId,
            BoardRequestDto boardRequestDto,
            User user,
            MultipartFile multipartFile
    ) throws IOException {

        Board modifyBoard = boardRepository.findById(boardId).orElseThrow(() ->  new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!modifyBoard.getUser().getId().equals(user.getId())) throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_MODIFY);


        Board newUpdateBoard = modifyBoard.builder()
                .id(boardId)
                .content(boardRequestDto.getContent())
                .location(boardRequestDto.getLocation())
                .nickname(boardRequestDto.getNickname())
                .user(user)
                .build();

        if(multipartFile != null) {
            String imageUrl = s3Uploader.upload(multipartFile, imageDirName);

            Board updateBoard = Board.builder()
                    .id(boardId)
                    .nickname(modifyBoard.getNickname())
                    .image(imageUrl)
                    .location(modifyBoard.getLocation())
                    .content(modifyBoard.getContent())
                    .user(user)
                    .build();

            boardRepository.save(updateBoard);

            return BoardResponseDto.builder()
                    .id(updateBoard.getId())
                    .image(updateBoard.getImage())
                    .location(updateBoard.getLocation())
                    .content(updateBoard.getContent())
                    .nickname(updateBoard.getNickname())
                    .build();

         /*   newUpdateBoard.builder()
                    .image(imageUrl)
                    .build();*/
        } else {
            newUpdateBoard.builder()
                    .image(modifyBoard.getImage())
                    .build();
        }

        System.out.println(newUpdateBoard.getImage());
        boardRepository.save(newUpdateBoard);
        return BoardResponseDto.builder()
                .id(newUpdateBoard.getId())
                .image(newUpdateBoard.getImage())
                .content(newUpdateBoard.getContent())
                .location(newUpdateBoard.getLocation())
                .nickname(newUpdateBoard.getNickname())
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

    public Long deletePost(Long boardId, Long nowLoginUserId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ErrorNotFoundBoardException(ErrorCode.ERROR_BOARD_ID));
        if (!board.getUser().getId().equals(nowLoginUserId)) throw new ErrorNotFoundUserException(ErrorCode.ERROR_NOTMATCH_DELETE);
        boardRepository.deleteById(boardId);
        return boardId;
    }


    public Page<BoardResponseDto> findAllPaging(int nowPage) {
        PageRequest pageRequest = PageRequest.of(nowPage, 4, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Board> all = boardRepository.findAll(pageRequest);

        return all.map( b -> new BoardResponseDto(b.getId(),b.getNickname(),b.getContent(),b.getLocation(),b.getImage()));
    }
}
