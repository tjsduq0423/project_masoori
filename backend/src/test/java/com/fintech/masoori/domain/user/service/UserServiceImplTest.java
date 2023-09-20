package com.fintech.masoori.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.implementation.bind.annotation.Super;

import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.user.UserRole;
import com.fintech.masoori.domain.user.dto.InfoRes;
import com.fintech.masoori.domain.user.dto.SignUpReq;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.EmailDuplicationException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.oauth.ProviderType;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SpringBootTest
@Transactional
@Rollback
class UserServiceImplTest {
	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected UserService userService;
	@Autowired
	protected EntityManager em;

	@Test
	public void findByEmail() {
		User user = User.builder().email("songsoy95@gmail.com").password("123").build();

		User savedUser = userRepository.save(user);
		User findUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(savedUser.getEmail()).isEqualTo(findUser.getEmail());
		System.out.println("findUser.getEmail() = " + findUser.getEmail());
	}

	@Test
	public void updateSmsAlarm() {
		User user = User.builder().email("ssafy@gmail.com").password("123").smsAlarm(false).build();
		userRepository.save(user);

		User savedUser = userRepository.findByEmail(user.getEmail()).get();
		assertThat(savedUser.getSmsAlarm()).isEqualTo(false);
		userRepository.updateSmsAlarm(savedUser.getEmail());
		em.flush();
		em.clear();
		User updatedUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(updatedUser.getSmsAlarm()).isEqualTo(true);
	}

	@Test
	public void updateCardGeneration() {
		User user = User.builder().email("ssafy@gmail.com").password("123").cardGeneration(false).build();
		userRepository.save(user);

		User savedUser = userRepository.findByEmail(user.getEmail()).get();
		assertThat(savedUser.getCardGeneration()).isEqualTo(false);
		userRepository.updateCardGeneration(savedUser.getEmail());
		em.flush();
		em.clear();
		User updatedUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(updatedUser.getCardGeneration()).isEqualTo(true);
	}

	@Test
	public void updateIntegration() {
		User user = User.builder()
		                .email("ssafy@gmail.com")
		                .password("123")
		                .smsAlarm(false)
		                .cardGeneration(false)
		                .build();
		userRepository.save(user);

		User savedUser = userRepository.findByEmail(user.getEmail()).get();
		assertThat(savedUser.getSmsAlarm()).isEqualTo(false);
		assertThat(savedUser.getCardGeneration()).isEqualTo(false);
		userRepository.updateIntegration(savedUser.getEmail());
		em.flush();
		em.clear();
		User updatedUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(updatedUser.getSmsAlarm()).isEqualTo(true);
		assertThat(updatedUser.getCardGeneration()).isEqualTo(true);
	}

	// 회원가입 검증
	@Test
	@DisplayName("회원가입 테스트 - 회원가입 성공 여부 테스트")
	public void signUpTest() {
		//given
		SignUpReq testReq = new SignUpReq("test", "test");
		//when
		userService.signUp(testReq);
		//then
		User test = userRepository.findUserByEmail("test");
		assertThat(test.getEmail()).isEqualTo(testReq.getEmail());
	}

	@Test
	@DisplayName("회원가입 테스트 - 회원가입 실패 여부 테스트")
	public void signUpFailTest() {
		//given
		User user = User.builder().email("test").password("test").providerType(ProviderType.GOOGLE).build();
		userRepository.save(user);
		//when
		SignUpReq testReq = new SignUpReq("test", "test");
		//then
		assertThrows(EmailDuplicationException.class, () -> {
			userService.signUp(testReq);
		});
	}

	@Test
	// 유저 정보 불러오기 검증
	public void getUserInfoTest() {
		//given
		User user = User.builder()
		                .name("테스트네임")
		                .providerType(ProviderType.LOCAL)
		                .cardGeneration(true)
		                .cardImage("카드이미지경로.png")
		                .email("test@naver.com")
		                .isAuthenticated(true)
		                .password("testPw")
		                .phoneNumber("010-7793-5311")
		                .roles(Collections.singletonList(UserRole.ROLE_USER.name()))
		                .smsAlarm(true)
		                .build();

		em.persist(user);
		List<Deal> dealList = new ArrayList<>();
		Integer sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += 10000 * i;
			Deal deal = Deal.builder()
			                .amount(10000 * i)
			                .content("음식 구매" + i)
			                .dealPlaceName("청풍반점" + i)
			                .date(LocalDateTime.now())
			                .build();
			user.addDealInfo(deal);
			em.persist(deal);
			em.flush();
			dealList.add(deal);
		}
		//when
		InfoRes userInfo = userService.getUserInfo(user.getEmail());
		//then
		assertThat(userInfo.getIsPaymentInfoLinked()).isEqualTo(user.getIsAuthenticated());
		assertThat(userInfo.getImagePath()).isEqualTo(user.getCardImage());

		assertThat(userInfo.getDailySpending()).isEqualTo(sum);
		assertThat(userInfo.getWeeklySpending()).isEqualTo(sum);
		assertThat(userInfo.getMonthlySpending()).isEqualTo(sum);
	}
	// 로그아웃 검증 생략... 쿠키 값 지우는거라 생략좀 하겠습니다....
}
