package com.oppla.server.global.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5000, "내부 서버 에러입니다."),
    DB_ACCESS_ERROR(5001, "DB 에러입니다.");

    private final int code;
    private final String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
