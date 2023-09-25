package com.fintech.masoori.global.oauth.exception;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		// https://velog.io/@coastby/SpringSecurity-filter-%EB%82%B4%EC%97%90%EC%84%9C-%EB%B0%9C%EC%83%9D%ED%95%9C-%EC%98%88%EC%99%B8-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0
		log.info("Responding with unauthorized error. Message := {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getLocalizedMessage());
	}
}

