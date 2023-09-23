package com.fintech.masoori.domain.lucky.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.lucky.dto.FortuneListRes;
import com.fintech.masoori.domain.lucky.dto.FortuneRes;
import com.fintech.masoori.domain.lucky.dto.UserFortuneRes;
import com.fintech.masoori.domain.lucky.entity.Fortune;
import com.fintech.masoori.domain.lucky.entity.FortuneUser;
import com.fintech.masoori.domain.lucky.repository.FortuneRepository;
import com.fintech.masoori.domain.lucky.repository.FortuneUserRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.oauth.ProviderType;
import com.fintech.masoori.global.redis.RedisService;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Rollback
@Slf4j
class FortuneServiceImplTest {

	@Autowired
	FortuneService fortuneService;
	@Autowired
	FortuneRepository fortuneRepository;
	@Autowired
	FortuneUserService fortuneUserService;
	@Autowired
	FortuneUserRepository fortuneUserRepository;
	@Autowired
	RedisService redisService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EntityManager em;

	/**
	 * 로그인한 유저가 오늘 처음으로 금전뽑기를 하는 경우
	 */
	@Test
	void 로그인_처음_뽑기() {
		User user = User.builder().email("test@gmail.com").providerType(ProviderType.LOCAL).build();
		userRepository.save(user);
		Fortune fortune1 = Fortune.builder().name("1번 카드").imagePath("이미지 경로1").description("1번 설명").build();
		Fortune fortune2 = Fortune.builder().name("2번 카드").imagePath("이미지 경로2").description("2번 설명").build();
		Fortune fortune3 = Fortune.builder().name("3번 카드").imagePath("이미지 경로3").description("3번 설명").build();
		Fortune fortune4 = Fortune.builder().name("4번 카드").imagePath("이미지 경로4").description("4번 설명").build();
		Fortune fortune5 = Fortune.builder().name("5번 카드").imagePath("이미지 경로5").description("5번 설명").build();
		fortuneRepository.save(fortune1);
		fortuneRepository.save(fortune2);
		fortuneRepository.save(fortune3);
		fortuneRepository.save(fortune4);
		fortuneRepository.save(fortune5);
		if (redisService.getUserFortune(user.getEmail()) == null) {
			FortuneRes response = fortuneService.selectOneFortune(user.getEmail());
			log.info("Fortune : {}", response);
			assertThat(redisService.getUserFortune(user.getEmail()).equals(response.getName()));
			redisService.deleteUserFortune(user.getEmail());
			assertThat(redisService.getUserFortune(user.getEmail()) == null);
		} else {
			redisService.deleteUserFortune(user.getEmail());
			fail("이미 Redis에 유저가 존재합니다.");
		}
	}

	/**
	 * 로그인한 유저가 오늘 이미 금전운 뽑기를 진행한 경우
	 */
	@Test
	void 로그인_중복_뽑기() {
		User user = User.builder().email("test@gmail.com").providerType(ProviderType.LOCAL).build();
		userRepository.save(user);
		Fortune fortune1 = Fortune.builder().name("1번 카드").imagePath("이미지 경로1").description("1번 설명").build();
		Fortune fortune2 = Fortune.builder().name("2번 카드").imagePath("이미지 경로2").description("2번 설명").build();
		Fortune fortune3 = Fortune.builder().name("3번 카드").imagePath("이미지 경로3").description("3번 설명").build();
		Fortune fortune4 = Fortune.builder().name("4번 카드").imagePath("이미지 경로4").description("4번 설명").build();
		Fortune fortune5 = Fortune.builder().name("5번 카드").imagePath("이미지 경로5").description("5번 설명").build();
		fortuneRepository.save(fortune1);
		fortuneRepository.save(fortune2);
		fortuneRepository.save(fortune3);
		fortuneRepository.save(fortune4);
		fortuneRepository.save(fortune5);
		FortuneRes response = fortuneService.selectOneFortune(user.getEmail());
		if (redisService.getUserFortune(user.getEmail()).equals(response.getName())) {
			String name = redisService.getUserFortune(user.getEmail());
			Fortune findFortune = fortuneRepository.findDescriptioneByName(name);
			log.info("Fortune : {}", findFortune);
			assertThat(name.equals(response.getName()));
			assertThat(name.equals(findFortune.getName()));
			redisService.deleteUserFortune(user.getEmail());
			assertThat(redisService.getUserFortune(user.getEmail()) == null);
		} else {
			redisService.deleteUserFortune(user.getEmail());
			fail("redis에 저장되어 있는 Fortune name과 유저가 뽑은 Fortune의 name이 일치하지 않습니다.");
		}
	}

	/**
	 * 비로그인 유저가 금전운 뽑기를 하는 경우
	 */
	@Test
	void 비로그인_처음_뽑기() {
		Fortune fortune1 = Fortune.builder().name("1번 카드").imagePath("이미지 경로1").description("1번 설명").build();
		Fortune fortune2 = Fortune.builder().name("2번 카드").imagePath("이미지 경로2").description("2번 설명").build();
		Fortune fortune3 = Fortune.builder().name("3번 카드").imagePath("이미지 경로3").description("3번 설명").build();
		Fortune fortune4 = Fortune.builder().name("4번 카드").imagePath("이미지 경로4").description("4번 설명").build();
		Fortune fortune5 = Fortune.builder().name("5번 카드").imagePath("이미지 경로5").description("5번 설명").build();
		fortuneRepository.save(fortune1);
		fortuneRepository.save(fortune2);
		fortuneRepository.save(fortune3);
		fortuneRepository.save(fortune4);
		fortuneRepository.save(fortune5);
		String email = "";
		assertThat(email.equals(""));
		FortuneRes response = fortuneService.selectOneFortune(email);
		log.info("Non-Login User Fortune(\"\") : {}", response);
	}

	/**
	 * fortune 전체 조회
	 */
	@Test
	void 전체조회() {
		Fortune fortune1 = Fortune.builder().name("1번 카드").imagePath("이미지 경로1").description("1번 설명").build();
		Fortune fortune2 = Fortune.builder().name("2번 카드").imagePath("이미지 경로2").description("2번 설명").build();
		Fortune fortune3 = Fortune.builder().name("3번 카드").imagePath("이미지 경로3").description("3번 설명").build();
		Fortune fortune4 = Fortune.builder().name("4번 카드").imagePath("이미지 경로4").description("4번 설명").build();
		Fortune fortune5 = Fortune.builder().name("5번 카드").imagePath("이미지 경로5").description("5번 설명").build();
		fortuneRepository.save(fortune1);
		fortuneRepository.save(fortune2);
		fortuneRepository.save(fortune3);
		fortuneRepository.save(fortune4);
		fortuneRepository.save(fortune5);
		FortuneListRes response = fortuneService.selectAllFortune();
		log.info("FortuneList : {}", response);
		assertThat(response.getFortuneList().size() == fortuneRepository.count());
	}

	/**
	 * User가 가지고 있는 Fortune 전체 조회
	 */
	@Test
	void 유저_금전운_조회() {
		List<Fortune> fortuneList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			fortuneList.add(Fortune.builder().name(i + "번 카드").imagePath("이미지 경로" + i).description(i + "번 설명").build());
		}
		fortuneRepository.saveAll(fortuneList);

		User user = User.builder().email("test@gmail.com").providerType(ProviderType.LOCAL).build();
		// userRepository.save(user);
		em.persist(user);

		List<FortuneUser> fortuneUserList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			FortuneUser fortuneUser = FortuneUser.builder().user(user).fortune(fortuneList.get(i)).build();
			em.persist(fortuneUser);
			fortuneUserList.add(fortuneUser);
		}
		// fortuneUserRepository.saveAll(fortuneUserList);
		em.flush();

		UserFortuneRes fortuneRes = fortuneUserService.selectAllUserFortune(user.getEmail());
		log.info("User's Fortune : {}", fortuneRes);
		assertThat(fortuneRes.getFortuneList().size() == 3);
		List<FortuneUser> allByUserId = fortuneUserRepository.findAllByUserId(user.getId());
		assertThat(allByUserId.size() == 3);
		for (FortuneUser fortuneUser : allByUserId) {
			assertThat(fortuneUser.getUser().getId().equals(user.getId()));
		}
		for (int i = 0; i < allByUserId.size(); i++) {
			assertThat(allByUserId.get(i).getFortune().getName().equals(fortuneList.get(i).getName()));
		}
	}

}
