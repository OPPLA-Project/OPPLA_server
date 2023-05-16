package com.oppla.server.domain.auth.dto;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthToken {
    @Getter
    private final String token;
    private final Key key;

    public AuthToken(Long userId, Role roleType, Long expiry, Key key) {
        System.out.println("토큰 발급 시도");
        this.token = createJwt(userId, roleType.getCode(), expiry, key);
        this.key = key;
        System.out.println("토큰 발급 성공 : " + token);
    }

    public String createJwt(Long userId, String role, Long expiry, Key key) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiry);
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", userId);
        payloads.put("role", role);

        String appToken = Jwts.builder()
                .setSubject(userId.toString())
                .setHeaderParam("type","jwt")
                .setClaims(payloads)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println(appToken);
        return appToken;
    }

    public boolean validate() {
        return this.isvalidtoken(token);
    }

    public boolean isvalidtoken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(token);

            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
