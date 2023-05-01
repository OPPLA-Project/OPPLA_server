package com.oppla.server.domain.question.exception;

import org.springframework.http.HttpStatus;

public class NotFoundQuestionException extends QuestionException{
    private static final int ERROR_CODE = 3000;
    private static final String MESSAGE = "존재하지 않는 질문입니다.";
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public NotFoundQuestionException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
