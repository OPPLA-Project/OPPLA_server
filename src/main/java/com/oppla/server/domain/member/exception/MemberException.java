package com.oppla.server.domain.member.exception;

import com.oppla.server.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class MemberException extends ApplicationException {
    protected MemberException(int errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
