package com.oppla.server.domain.question.exception;

import org.springframework.http.HttpStatus;

public class QuestionNotFoundException extends QuestionException{
    private static final int ERROR_CODE = 2000;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "존재하지 않는 질문입니다.";


    public QuestionNotFoundException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
