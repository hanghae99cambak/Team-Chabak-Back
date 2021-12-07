package com.sparta.hanghaechabak.exception;

import com.sparta.hanghaechabak.exception.ErrorUtils.CustomException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;

public class ErrorNotFoundBoardException extends CustomException {

    public ErrorNotFoundBoardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
