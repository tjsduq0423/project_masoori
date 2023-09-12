package com.fintech.masoori.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceImplTest {

	@Autowired UserRepository userRepository;

	@Test
	public void findByEmail() {
		User user = new User("ssafy@gmail.com","1234");
		User savedUser = userRepository.save(user);
		User findUser = userRepository.findByEmail(savedUser.getEmail()).get();

		assertThat(savedUser.getEmail()).isEqualTo(findUser.getEmail());
		System.out.println("findUser.getEmail() = " + findUser.getEmail());
	}

}