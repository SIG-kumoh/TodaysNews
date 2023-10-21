package com.sig.todaysnews.controller;


import com.sig.todaysnews.dto.LoginDto;
import com.sig.todaysnews.dto.TokenDto;
import com.sig.todaysnews.redis.RedisService;
import com.sig.todaysnews.security.TokenProvider;
import com.sig.todaysnews.security.filter.JwtFilter;
import com.sig.todaysnews.security.util.AuthenticationUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;


    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createReFreshToken(authentication);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(tokenProvider.getRefreshTokenValidityInSeconds())
                .build();

        redisService.addRefreshTokenByRedis(
                loginDto.getUsername(),
                refreshToken,
                Duration.ofMillis(tokenProvider.getRefreshTokenValidityInSeconds())
        );

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        httpHeaders.add(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(ServletResponse servletResponse) {
        redisService.deleteRefreshTokenByRedis(AuthenticationUtil.getCurrentUsername().get());
        ((HttpServletResponse) servletResponse).setHeader(JwtFilter.AUTHORIZATION_HEADER, "logout");
    }
}