package com.oppla.server.domain.auth.exception;

import org.springframework.http.HttpStatus;

public class NoAuthorizationTokenException extends AuthException {
    private static final int ERROR_CODE = 2003;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "AuthorizationToken이 존재하지 않습니다.";

    public NoAuthorizationTokenException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
