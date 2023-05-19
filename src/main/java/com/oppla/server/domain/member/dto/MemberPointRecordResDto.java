package com.oppla.server.domain.member.dto;

import com.oppla.server.domain.member.enums.PointRecordDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPointRecordResDto {
    @Schema(description = "Point 변경 사유")
    private PointRecordDesc description;
    @Schema(description = "변경된 Point")
    private Integer deal_point;
    @Schema(description = "잔여 Point")
    private Integer rest_point;
}
