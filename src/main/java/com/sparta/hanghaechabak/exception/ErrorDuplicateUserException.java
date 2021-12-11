package com.sparta.hanghaechabak.exception;

import com.sparta.hanghaechabak.exception.ErrorUtils.CustomException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;

public class ErrorDuplicateUserException extends CustomException {
    public ErrorDuplicateUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
