package com.benefitmoa.global.response;

import com.benefitmoa.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {
    private boolean success;
    private int status;
    private String code;
    private String message;
    private T data;

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, 200, "SUCCESS", "요청이 성공적으로 처리되었습니다.", data);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult<>(true, 200, "SUCCESS", message, data);
    }

    public static <T> ApiResult<T> error(ErrorCode errorCode) {
        return new ApiResult<>(false, errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> ApiResult<T> error(ErrorCode errorCode, String customMessage) {
        return new ApiResult<>(false, errorCode.getStatus(), errorCode.getCode(), customMessage, null);
    }
}
