package com.benefitmoa.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessages {
    EMAIL_INVALID("이메일 형식이 올바르지 않습니다."),
    PASSWORD_TOO_SHORT("비밀번호는 최소 8자 이상이어야 합니다."),
    NAME_REQUIRED("이름은 필수 항목입니다."),
    NICKNAME_REQUIRED("닉네임은 필수 항목입니다."),
    PHONE_REQUIRED("전화번호는 필수 항목입니다."),
    PHONE_INVALID("전화번호 형식이 올바르지 않습니다."),
    EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
    EMAIL_OR_PASSWORD_INVALID("이메일 또는 비밀번호가 올바르지 않습니다."),
    ;

    private final String message;
}
