package com.oppla.server.global.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message"})
public class BaseResponse {
    @Schema(defaultValue = "성공여부")
    private final Boolean isSuccess = true;

    @Schema(defaultValue = "상태 메세지")
    private final String message = "요청성공.";

    @Schema(defaultValue = "상태 코드")
    private final int code = 1000;

}
