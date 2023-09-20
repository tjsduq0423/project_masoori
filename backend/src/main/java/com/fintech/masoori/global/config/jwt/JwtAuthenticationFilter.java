package com.fintech.masoori.global.config.jwt;

import static com.fintech.masoori.global.config.jwt.JwtTokenProvider.*;
import static com.fintech.masoori.global.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.util.CookieUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TYPE = "Bearer";
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, String> redisTemplate;
	private final UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		ServletException,
		IOException {
		// Request Header 에서 JWT 토큰 추출 - token null => 로그아웃
		String token = resolveToken(request);
		if (token == null) {
			chain.doFilter(request, response);
			return;
		}

		// 토큰 검증 -> 유효한 경우 : 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
		String validateResult = jwtTokenProvider.validateToken(token);
		if (Objects.equals(validateResult, "vaild")) {
			setSecurityContextHolder(token);
		}

		if (Objects.equals(validateResult, "isExpired")) {
			Optional<String> cookie = CookieUtil.getCookie(request, REFRESH_TOKEN).map(Cookie::getValue);

			if (cookie.isEmpty()) {
				chain.doFilter(request, response);
				return;
			}

			String refreshTokenFromCookie = cookie.get();
			if (!jwtTokenProvider.getIsExpired(refreshTokenFromCookie)) {
				chain.doFilter(request, response);
				return;
			}

			Claims claims = jwtTokenProvider.parseClaims(token);
			String email = claims.getSubject();
			String role = claims.get(AUTHORITIES_KEY, String.class);

			String refreshTokenFromRedis = redisTemplate.opsForValue().get("RT" + email);
			if (refreshTokenFromRedis == null) {
				chain.doFilter(request, response);
				return;
			}

			if (!Objects.equals(refreshTokenFromRedis, refreshTokenFromCookie)) {
				redisTemplate.opsForValue().getOperations().delete("RT" + email);
				chain.doFilter(request, response);
				return;
			}

			// 토큰 생성. RTR
			TokenInfo tokenInfo = jwtTokenProvider.createToken(email, role);
			// from Redis 기존 토큰 burn 그리고 새로 생성후 cookie 에 추가
			redisTemplate.opsForValue().getOperations().delete("RT" + email);
			redisTemplate.opsForValue()
			             .set("RT" + email, tokenInfo.getRefreshToken(), tokenInfo.getExpireTime(),
				             TimeUnit.MILLISECONDS);

			// refresh Token -> Http only 쿠키.
			CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
			CookieUtil.addCookie(response, REFRESH_TOKEN, tokenInfo.getRefreshToken(),
				JwtTokenProvider.getRefreshTokenExpireTimeCookie());
			response.setHeader("x-access-token", tokenInfo.getAccessToken());
			setSecurityContextHolder(token);
		}
		chain.doFilter(request, response);
	}

	private void setSecurityContextHolder(String token) {
		Authentication authentication = jwtTokenProvider.getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	// Request Header 에서 토큰 정보 추출
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
			return bearerToken.substring(7);
		}
		return null;
	}

}

