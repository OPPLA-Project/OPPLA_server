package com.oppla.server.domain.auth.exception;

import com.oppla.server.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class AuthException extends ApplicationException {
    public AuthException(int errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
