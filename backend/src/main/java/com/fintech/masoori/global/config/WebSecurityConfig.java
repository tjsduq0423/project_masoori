package com.fintech.masoori.global.config;

import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.config.jwt.JwtAuthenticationFilter;
import com.fintech.masoori.global.config.jwt.JwtTokenProvider;
import com.fintech.masoori.global.config.jwt.TokenAccessDeniedHandler;
import com.fintech.masoori.global.oauth.exception.RestAuthenticationEntryPoint;
import com.fintech.masoori.global.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.fintech.masoori.global.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.fintech.masoori.global.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.fintech.masoori.global.oauth.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
	private static final String[] WHITE_LIST = {"/api/user/signup", "/api/user/email/signup",
		"/api/user/email/password", "/api/user/login", "/api/user/check", "/api/user/",
		"/api/user//email-duplication/check", "/api/user/email/check", "/api/lucky/fortune", "/api/lucky/color",  "/api/faq",
		"/swagger-ui/**", "/v3/**", "/api/rabbit/**"};
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, String> redisTemplate;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
	private final UserRepository userRepository;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable)
		            .httpBasic(AbstractHttpConfigurer::disable)
		            .formLogin(AbstractHttpConfigurer::disable)
		            .cors(c -> c.configurationSource(corsConfigurationSource()))
		            .sessionManagement(c -> c.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))

		            .authorizeHttpRequests(auth -> auth.requestMatchers(Stream.of(WHITE_LIST)
		                                                                      .map(AntPathRequestMatcher::antMatcher)
		                                                                      .toArray(AntPathRequestMatcher[]::new))
		                                               .permitAll()
		                                               .requestMatchers(AntPathRequestMatcher.antMatcher("/user/**"))
		                                               .hasAnyRole("USER")
		                                               .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**"))
		                                               .hasAnyRole("ADMIN")
		                                               .anyRequest()
		                                               .authenticated())

		            .oauth2Login(oauth2 -> oauth2.authorizationEndpoint(
			                                         config -> config.baseUri("/*/oauth2/authorization")
			                                                         .authorizationRequestRepository(
				                                                         oAuth2AuthorizationRequestBasedOnCookieRepository()))
		                                         .redirectionEndpoint(config -> config.baseUri("/*/oauth2/code/*"))
		                                         .userInfoEndpoint(
			                                         config -> config.userService(customOAuth2UserService))
		                                         .successHandler(oAuth2AuthenticationSuccessHandler())
		                                         .failureHandler(oAuth2AuthenticationFailureHandler()))

		            .exceptionHandling(c -> c.authenticationEntryPoint(new RestAuthenticationEntryPoint())
		                                     .accessDeniedHandler(tokenAccessDeniedHandler))
		            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate, userRepository),
			            UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	// 암호화에 필요한 PasswordEncoder Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 쿠키 기반 인가 정보  Repository
	// 인가 응답을 연계하고 검증.
	@Bean
	public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
		return new OAuth2AuthorizationRequestBasedOnCookieRepository();
	}

	// Oauth 인증 성공 핸들러
	@Bean
	public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
		return new OAuth2AuthenticationSuccessHandler(oAuth2AuthorizationRequestBasedOnCookieRepository(),
			jwtTokenProvider, redisTemplate);
	}

	// Oauth 인증 실패 핸들러
	@Bean
	public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
		return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
	}

	//CORS 허용 적용
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
