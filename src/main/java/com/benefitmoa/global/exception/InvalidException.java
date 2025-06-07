package com.benefitmoa.global.exception;

public class InvalidException extends CustomException {

    public InvalidException() {
        super(ErrorCode.INVALID_PARAM);
    }

    public InvalidException(String detailMessage) {
        super(ErrorCode.INVALID_PARAM, detailMessage);
    }
}
