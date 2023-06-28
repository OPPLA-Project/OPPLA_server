package com.oppla.server.global.jwt;

import com.oppla.server.domain.auth.dto.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("로그인 시도");

        if (authorizationHeader != null) {
            System.out.println("app token 존재 확인");
            authorizationHeader = authorizationHeader.replaceAll("Bearer ", "");
            /*if(Pattern.matches("Bearer ", authorizationHeader)) {
                authorizationHeader = authorizationHeader.replaceAll("Bearer ", "");
                System.out.println("if문 내 : " + authorizationHeader);
            }*/

            AuthToken token = tokenProvider.convertAuthToken(authorizationHeader);
            if (token.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(authorizationHeader);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
        System.out.println("필터링 성공");
    }
}
