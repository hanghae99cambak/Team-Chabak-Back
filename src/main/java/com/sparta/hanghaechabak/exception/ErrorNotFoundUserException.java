package com.sparta.hanghaechabak.exception;

import com.sparta.hanghaechabak.exception.ErrorUtils.CustomException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;

public class ErrorNotFoundUserException extends CustomException {

    public ErrorNotFoundUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
