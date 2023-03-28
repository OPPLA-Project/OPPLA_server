package com.oppla.server.global.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseDataResponse<T> extends BaseResponse {
    private T result;

    @Builder
    public BaseDataResponse (T result) {
        this.result = result;
    }
}
