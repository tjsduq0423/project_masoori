package com.fintech.masoori.domain.user.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.EmailMessagingException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.redis.RedisService;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@Transactional
@Rollback()
@RequiredArgsConstructor
class UserServiceImplTest {

	@Autowired UserRepository userRepository;
	@Autowired EmailService emailService;
	@Autowired RedisService redisService;
	@Autowired EntityManager em;

	@Test
	public void sendEmail(String email) {
		User user = User.builder().email("songsoy95@gmail.com").build();
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


	@Test
	public void findByEmail() {
		User user = User.builder().email("ssafy@gmail.com").password("123").build();

		User savedUser = userRepository.save(user);
		User findUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(savedUser.getEmail()).isEqualTo(findUser.getEmail());
		System.out.println("findUser.getEmail() = " + findUser.getEmail());
	}

	@Test
	public void updateIntegration() {
		User user = User.builder().email("ssafy@gmail.com").password("123").smsAlarm(false).cardGeneration(false).build();
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





}