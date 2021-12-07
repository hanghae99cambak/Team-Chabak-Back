package com.sparta.hanghaechabak.exception.ErrorUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ERROR_BOARD_ID(404,"게시물이 존재하지 않습니다"),
    ERROR_USER_ID(404,"유저 정보가 존재하지 않습니다."),
    ERROR_NOTMATCH_DELETE(403,"본인이 작성한 게시물만 삭제 할 수 있습니다."),
    ERROR_NOTMATCH_MODIFY(403,"본인이 작성한 게시물만 수정 할 수 있습니다."),
    NOT_LOGIN(403,"로그인을 하지않으면 글 등록 할 수 없습니다.");

    private final int status;
    private final String message;


}
