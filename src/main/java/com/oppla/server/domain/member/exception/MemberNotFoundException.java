package com.oppla.server.domain.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {
    private static final int ERROR_CODE = 2002;
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String MESSAGE = "존재하지 않는 멤버입니다.";

    public MemberNotFoundException() {
        super(ERROR_CODE, STATUS, MESSAGE);
    }
}
