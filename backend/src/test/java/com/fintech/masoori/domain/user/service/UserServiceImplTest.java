package com.fintech.masoori.domain.user.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.exception.EmailMessagingException;
import com.fintech.masoori.domain.user.exception.SmsMessagingException;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.redis.RedisService;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback()
class UserServiceImplTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RedisService redisService;
	@Autowired
	EmailService emailService;
	@Autowired
	SmsService smsService;
	@Autowired
	EntityManager em;

	@Test
	public void sendEmail() {
		User user = User.builder().email("songsoy95@gmail.com").build();
		userRepository.save(user);
		User findUser = userRepository.findByEmail(user.getEmail()).get();
		String email = findUser.getEmail();
		// 인증코드 redis 서버에 저장
		String code = emailService.createCode(8);
		redisService.setEmailCode(email, code);
		try {
			emailService.sendEmail(email, code);
		} catch (MessagingException e) {
			redisService.deleteEmailCode(email);
			throw new EmailMessagingException("Failed To Send Email");
		}
		assertThat(redisService.getEmailCode(email)).isEqualTo(code);
	}

	@Test
	public void updateInfo() {
		User user = User.builder().email("ssafy@gmail.com").build();
		userRepository.save(user);
		User findUser = userRepository.findByEmail(user.getEmail()).get();

		String name = "홍길동";
		String phoneNumber = "01051548989";
		userRepository.updateInfo(findUser.getEmail(), name, phoneNumber);
		em.flush();
		em.clear();

		User updatedUser = userRepository.findByEmail(findUser.getEmail()).get();
		assertThat(updatedUser.getName()).isEqualTo(name);
		assertThat(updatedUser.getPhoneNumber()).isEqualTo(phoneNumber);
	}

	@Test
	public void sendSms() {
		User user = User.builder().email("ssafy@gmail.com").phoneNumber("01051548989").build();
		userRepository.save(user);
		User findUser = userRepository.findByEmail(user.getEmail()).get();

		String phoneNumber = findUser.getPhoneNumber();
		String code = smsService.createCode(6);
		redisService.setSmsCode(phoneNumber, code);

		try {
			smsService.sendSms(phoneNumber, code);
		} catch (CoolsmsException e) {
			redisService.deleteSmsCode(phoneNumber);
			throw new SmsMessagingException("Failed To Send Email");
		}
		assertThat(redisService.getSmsCode(phoneNumber)).isEqualTo(code);
	}

	@Test
	public void findByEmail() {
		User user = User.builder().email("ssafy@gmail.com").build();
		userRepository.save(user);
		User savedUser = userRepository.findByEmail(user.getEmail()).get();

		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}

	@Test
	public void updateIntegration() {
		User user = User.builder().email("ssafy@gmail.com").smsAlarm(false).cardGeneration(false).build();
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
		User user = User.builder().email("ssafy@gmail.com").smsAlarm(false).build();
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
		User user = User.builder().email("ssafy@gmail.com").cardGeneration(false).build();
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