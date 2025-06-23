package com.benefitmoa.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_PARAM(HttpStatus.BAD_REQUEST, 40001, "INVALID_PARAM", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40100, "UNAUTHORIZED", "인증이 필요합니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 40401, "NOT_FOUND", "리소스를 찾을 수 없습니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "INTERNAL_ERROR", "서버 내부 오류가 발생했습니다."),
    ;


    private final HttpStatus httpStatus;
    private final int status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int status, String code, String message) {
        this.httpStatus = httpStatus;
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
