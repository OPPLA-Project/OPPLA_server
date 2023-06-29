package com.oppla.server.domain.member.dto;

import com.oppla.server.domain.member.enums.PointRecordDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPointRecordResDto {
    @Schema(description = "Point 변경 사유")
    private PointRecordDesc description;
    @Schema(description = "변경된 Point")
    private Integer dealPoint;
    @Schema(description = "변경 후 잔여 Point")
    private Integer restPoint;
    @Schema(description = "Point 사용 시점")
    private LocalDateTime createdAt;
}
