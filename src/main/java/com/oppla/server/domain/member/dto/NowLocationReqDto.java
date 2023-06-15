package com.oppla.server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NowLocationReqDto {
    @Schema(description = "변경할 위도 값")
    private Long latitude;
    @Schema(description = "변경할 경도 값")
    private Long longitude;
}
