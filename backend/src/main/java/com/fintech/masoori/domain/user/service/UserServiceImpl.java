package com.fintech.masoori.domain.user.service;

import static com.fintech.masoori.global.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.deal.service.DealService;
import com.fintech.masoori.domain.user.UserRole;
import com.fintech.masoori.domain.user.dto.EmailCheckReq;
import com.fintech.masoori.domain.user.dto.InfoRes;
import com.fintech.masoori.domain.user.dto.LoginReq;
import com.fintech.masoori.domain.user.dto.LoginRes;
import com.fintech.masoori.domain.user.dto.SendSmsReq;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.dto.SmsCheckReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.EmailDuplicationException;
import com.fintech.masoori.domain.user.exception.EmailMessagingException;
import com.fintech.masoori.domain.user.exception.InvalidAuthCodeException;
import com.fintech.masoori.domain.user.exception.ProviderIsNotMatchedException;
import com.fintech.masoori.domain.user.exception.SmsMessagingException;
import com.fintech.masoori.domain.user.exception.UserNotFoundException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.config.jwt.JwtTokenProvider;
import com.fintech.masoori.global.config.jwt.TokenInfo;
import com.fintech.masoori.global.oauth.ProviderType;
import com.fintech.masoori.global.redis.RedisService;
import com.fintech.masoori.global.util.CookieUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	private final RedisTemplate redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RedisService redisService;
	private final EmailService emailService;
	private final SmsService smsService;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public boolean checkEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public boolean checkOAuthAccount(String email) {
		User user = userRepository.findUserByEmail(email);
		return user.getProviderType() != ProviderType.LOCAL;
	}

	@Override
	@Transactional
	public void signUp(SignUpReq signUpReq) {
		if (checkEmail(signUpReq.getEmail())) {
			throw new EmailDuplicationException("Email is Duplicated");
		}
		User newUser = User.builder()
		                   .email(signUpReq.getEmail())
		                   .password(passwordEncoder.encode(signUpReq.getPassword()))
		                   .roles(Collections.singletonList(UserRole.ROLE_USER.name()))
		                   .providerType(ProviderType.LOCAL)
		                   .build();
		userRepository.save(newUser);
	}

	@Override
	public LoginRes login(LoginReq loginReq) {
		Optional<User> byEmail = userRepository.findByEmail(loginReq.getEmail());
		if (byEmail.isEmpty())
			throw new UserNotFoundException("User is Not Found");
		User user = byEmail.get();
		if (user.getProviderType() != ProviderType.LOCAL)
			throw new ProviderIsNotMatchedException("Provider is Not Matched");

		UsernamePasswordAuthenticationToken authenticationToken = loginReq.toAuthentication();

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		TokenInfo tokenInfo = jwtTokenProvider.createToken(authentication);

		redisTemplate.opsForValue()
		             .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getExpireTime(),
			             TimeUnit.MILLISECONDS);
		LoginRes loginRes = new LoginRes(tokenInfo.getAccessToken());
		return loginRes;
	}

	@Override
	public InfoRes getUserInfo(String email) {
		User user = userRepository.findUserByEmail(email);
		InfoRes infoRes = InfoRes.builder()
		                         .imagePath(user.getCardImage())
		                         .isPaymentInfoLinked(user.getIsAuthenticated())
		                         .kakaoAlarm(user.getSmsAlarm())
		                         .dailySpending(getDailySpending(user))
		                         .monthlySpending(getMonthlySpending(user))
		                         .weeklySpending(getWeeklySpending(user))
		                         .build();
		return infoRes;
	}

	public Integer getMonthlySpending(User user) {
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().getYear() == LocalDate.now().getYear()
			           && e.getDate().getMonth() == LocalDate.now().getMonth())
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}

	public Integer getWeeklySpending(User user) {
		LocalDate now = LocalDate.now();
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().getYear() == now.getYear()
			           && e.getDate().get(WeekFields.ISO.weekOfWeekBasedYear()) == now.get(
			           WeekFields.ISO.weekOfWeekBasedYear()))
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}

	public Integer getDailySpending(User user) {
		LocalDate now = LocalDate.now();
		return user.getDealList()
		           .stream()
		           .filter(e -> e.getDate().isEqual(ChronoLocalDateTime.from(now)))
		           .map(Deal::getAmount)
		           .reduce(0, Integer::sum);
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Optional<String> cookie = CookieUtil.getCookie(request, REFRESH_TOKEN).map(Cookie::getValue);
		// 쿠키 없으면 걍 로그아웃.
		if (cookie.isEmpty()) {
			return;
		}
		String refreshTokenFromCookie = cookie.get();
		String userEmail = jwtTokenProvider.parseClaims(refreshTokenFromCookie).getSubject();
		// 쿠키 삭제
		CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
		// redis 토큰 삭제
		redisTemplate.delete("RT" + userEmail);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> findUser = userRepository.findByEmail(email);
		return findUser;
	}

	@Override
	public void sendSignupEmailCode(String email) {
		Optional<User> findUser = findByEmail(email);
		// 사용자 존재 => 중복 => 가입 안됨
		if (findUser.isPresent())
			throw new EmailDuplicationException("Email is Duplicated");
		sendEmail(email);
	}

	@Override
	public void sendPasswordEmailCode(String email) {
		Optional<User> findUser = findByEmail(email);
		// 사용자 존재x => 없는 사용자
		if (!findUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		sendEmail(email);
	}

	public void sendEmail(String email) {
		// 인증코드 redis 서버에 저장
		String code = emailService.createCode(8);
		redisService.setEmailCode(email, code);
		try {
			emailService.sendEmail(email, code);
		} catch (MessagingException e) {
			redisService.deleteEmailCode(email);
			throw new EmailMessagingException("Failed To Send Email");
		}
	}

	@Override
	public void verifyEmailCode(EmailCheckReq emailCheckReq) {
		String inputCode = emailCheckReq.getCode();
		String savedCode = redisService.getEmailCode(emailCheckReq.getEmail());
		if (!inputCode.equals(savedCode))
			throw new InvalidAuthCodeException("Invalid Auth Code");
	}

	@Override
	public void verifySmsCode(SmsCheckReq smsCheckReq) {
		String inputCode = smsCheckReq.getCode();
		String savedCode = redisService.getEmailCode(smsCheckReq.getPhoneNumber());
		if (!inputCode.equals(savedCode))
			throw new InvalidAuthCodeException("Invalid Auth Code");
	}

	@Override
	public void updateInfoAndSendSms(SendSmsReq sendSmsReq, User loginUser) {
		// 사용자 정보 업데이트
		String name = sendSmsReq.getName();
		String phoneNumber = sendSmsReq.getPhoneNumber();
		userRepository.updateInfo(loginUser.getEmail(), name, phoneNumber);
		// 인증코드 redis 서버에 저장 후 메시지 발송
		String code = smsService.createCode(6);
		redisService.setSmsCode(phoneNumber, code);
		try {
			smsService.sendSms(phoneNumber, code);
		} catch (CoolsmsException e) {
			redisService.deleteSmsCode(phoneNumber);
			throw new SmsMessagingException("Failed To Send Email");
		}
	}

	@Override
	public void updateIntegration(User loginUser) {
		userRepository.updateIntegration(loginUser.getEmail());
	}

	@Override
	public void updateSmsAlarm(User loginUser) {
		userRepository.updateSmsAlarm(loginUser.getEmail());
	}

	@Override
	public void updateCardGeneration(User loginUser) {
		userRepository.updateCardGeneration(loginUser.getEmail());
	}
}
