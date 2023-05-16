package com.oppla.server.global.jwt;

import com.oppla.server.domain.auth.exception.NoAuthorizationTokenException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final static String HEADER_AUTHORIZATION = "Authorization";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (request.getHeader(HEADER_AUTHORIZATION) == null) {
            throw new NoAuthorizationTokenException();
        }
    }
}
