package com.oppla.server.global.config;

import com.oppla.server.global.jwt.CustomAuthenticationEntryPoint;
import com.oppla.server.global.jwt.JwtAuthenticationFilter;
import com.oppla.server.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private static final String[] AUTH_URL = {

    };
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.cors().disable();

        http.authorizeHttpRequests((request) -> request
                .antMatchers(AUTH_URL).authenticated()
                .anyRequest().permitAll());

        http.headers().frameOptions().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout()
                .logoutSuccessUrl("/");

        return http.build();
    }
}
