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

public class NowLocationResDto {
    @Schema(description = "현재 위치가 변경된 멤버의 Id")
    private Long memberId;
    @Schema(description = "변경된 위도 값")
    private Long latitude;
    @Schema(description = "변경된 경도 값")
    private Long longitude;
}
