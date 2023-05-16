package com.oppla.server.domain.answer.exception;

import org.springframework.http.HttpStatus;

public class AnswerNotFoundException extends AnswerException{
    private static final int ERROR_CODE = 2001;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "존재하지 않는 답변입니다.";

    public AnswerNotFoundException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
