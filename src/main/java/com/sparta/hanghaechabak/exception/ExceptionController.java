package com.sparta.hanghaechabak.exception;

import com.sparta.hanghaechabak.exception.ErrorUtils.CustomException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ErrorNotFoundBoardException.class)
    public ErrorResponse BoardException(CustomException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND)
                .message(ex.getErrorCode().getMessage())
                .status(ex.getErrorCode().getStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ErrorNotFoundUserException.class)
    public ErrorResponse UserException(CustomException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND)
                .message(ex.getErrorCode().getMessage())
                .status(ex.getErrorCode().getStatus())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
