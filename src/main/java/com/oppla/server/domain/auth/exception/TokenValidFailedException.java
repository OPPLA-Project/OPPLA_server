package com.oppla.server.domain.auth.exception;

import org.springframework.http.HttpStatus;

public class TokenValidFailedException extends AuthException {
    private static final int ERROR_CODE = 2001;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "Access Token이 유효하지 않습니다.";

    public TokenValidFailedException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
