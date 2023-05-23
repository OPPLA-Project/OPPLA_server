package com.oppla.server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfilePatchReqDto {
    @Schema(description = "변경할 nickname")
    private String nickname;
    @Schema(description = "변경할 한줄 소개")
    private String intro;
}
