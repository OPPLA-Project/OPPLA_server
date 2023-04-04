package com.oppla.server.domain.question.exception;

import com.oppla.server.global.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class QuestionException extends ApplicationException {
    public QuestionException(int errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
