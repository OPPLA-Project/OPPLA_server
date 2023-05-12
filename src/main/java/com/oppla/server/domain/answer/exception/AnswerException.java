package com.oppla.server.domain.answer.exception;

import com.oppla.server.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class AnswerException extends ApplicationException {
    protected AnswerException(int errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
