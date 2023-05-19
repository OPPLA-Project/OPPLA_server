package com.oppla.server.domain.auth.exception;

import org.springframework.http.HttpStatus;

public class OtherSnsTypeException extends AuthException {
    private static final int ERROR_CODE = 2002;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "해당 소셜 계정으로 가입하지 않으셨습니다.";

    public OtherSnsTypeException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
