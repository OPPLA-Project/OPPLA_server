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
public class MemberNicknameDuplResDto {
    @Schema(description = "nickname 중복여부")
    private Boolean duplicationStatus;
}
