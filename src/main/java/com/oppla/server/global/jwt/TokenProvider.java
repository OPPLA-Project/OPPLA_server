package com.oppla.server.global.jwt;

import com.oppla.server.domain.auth.dto.AuthToken;
import com.oppla.server.domain.auth.dto.CustomMemberDetails;
import com.oppla.server.domain.auth.dto.Role;
import com.oppla.server.domain.auth.service.MemberDetailService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
@Component
public class TokenProvider {
    private Long expiry = 365L*(1000*60*60*24);
    private MemberDetailService memberDetailService;
    private final Key key;
    //@Value("${auth.jwt.tokenSecret}")
    private String sKey = "opplatokensecretkeydhvmffkxhzmstlzmfltzleocpdjfakskrlfdjdigodlwjdehauseho";

    @Autowired
    public TokenProvider(MemberDetailService memberDetailService) {
        byte[] keyBytes = Decoders.BASE64.decode(sKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.memberDetailService = memberDetailService;
    }

    public AuthToken createAppToken(Long userId, Role role) {
        return new AuthToken(userId, role, expiry, key);
    }


    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public Authentication getAuthentication(String token) {
        CustomMemberDetails memberDetails = (CustomMemberDetails) memberDetailService.loadUserByUsername(this.parseClaims(token));
        System.out.println(memberDetails.getMember().getEmail());

        return new UsernamePasswordAuthenticationToken(memberDetails.getMember(), "",memberDetails.getAuthorities());
    }

    public String parseClaims(String token) {
        Map<String, Object> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String userId = claims.get("userId").toString();

        return userId;
    }
}
