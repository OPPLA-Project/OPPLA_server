package com.oppla.server.global.config;

import com.oppla.server.global.jwt.CustomAuthenticationEntryPoint;
import com.oppla.server.global.jwt.JwtAuthenticationFilter;
import com.oppla.server.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private static final String[] AUTH_URL = {

    };

    private static final String[] AUTH_USERLIST = {};

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.cors().disable();

        http.authorizeRequests()
                .anyRequest().permitAll(); //이것들을 제외하곤 인증

        http.headers().frameOptions().disable(); // h2-console 화면을 사용하기 위해 해당 옵션 disable

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .httpBasic().disable()
                .formLogin().disable()
                .logout()
                .logoutSuccessUrl("/");
    }*/

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.cors().disable();

        http.authorizeRequests().antMatchers(AUTH_URL).authenticated()
                        .anyRequest().permitAll();

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
