package com.oppla.server.domain.auth.dto;

import lombok.Data;

@Data
public class MemberInfoReqDto {
    private Long id;
    private String nickname;
    private String profileUrl;
    private String email;

    public MemberInfoReqDto(String nickname, String email, String profileUrl) {
        this.nickname = nickname;
        this.email = email;
        this.profileUrl = profileUrl;
    }
}
