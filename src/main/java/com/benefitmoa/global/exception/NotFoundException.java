package com.benefitmoa.global.exception;

public class NotFoundException extends CustomException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public NotFoundException(String detailMessage) {
        super(ErrorCode.NOT_FOUND, detailMessage);
    }

}
