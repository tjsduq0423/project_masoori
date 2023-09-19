package com.fintech.masoori.domain.user.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback
class UserServiceImplTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	EntityManager em;

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
	public void signUpTest() {
	}
	@Test
	public void loginTest() {
	}
	// 로컬 로그인 검증
	// 유저 정보 불러오기 검증
	@Test
	public void getUserInfoTest() {
	}
	// 로그아웃 검증 생략... 쿠키 값 지우는거라 생략좀 하겠습니다....
}
