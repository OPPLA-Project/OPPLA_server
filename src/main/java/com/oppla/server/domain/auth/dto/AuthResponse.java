package com.oppla.server.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
/*@AllArgsConstructor
@NoArgsConstructor*/
public class AuthResponse {
    private String appToken;
}
