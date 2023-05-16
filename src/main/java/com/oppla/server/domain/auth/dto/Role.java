package com.oppla.server.domain.auth.dto;

import lombok.Getter;

@Getter
public enum Role {
    USER("USER", "일반 유저 권한"),
    ADMIN("ADMIN", "관리자 권한"),
    NONE("NONE", "권한 없음");

    private final String code;
    private final String name;

    Role(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
