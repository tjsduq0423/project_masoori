package com.fintech.masoori.domain.credit.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.credit.repository.CreditCardRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback()
class CreditCardServiceImplTest {

	@Autowired
	CreditCardService creditCardService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EntityManager em;

	@Test
	void selectAll() {
		User user = User.builder().email("ssafy@gmail.com").build();
		em.persist(user);
		// for (int i = 0; i < 5; i++) {
		// 	CreditCard card =
		// 		em.persist(card);
		// }

		CreditCard card1 = CreditCard.builder().name("행복카드").company("현대카드").build();
		CreditCard card2 = CreditCard.builder().name("사랑카드").company("국민카드").build();
		CreditCard card3 = CreditCard.builder().name("나눔카드").company("농협카드").build();
		CreditCard card4 = CreditCard.builder().name("미소카드").company("우리카드").build();
		CreditCard card5 = CreditCard.builder().name("해피카드").company("신한카드").build();
		em.persist(card1);
		em.persist(card2);
		em.persist(card3);
		em.persist(card4);
		em.persist(card5);

		User findUser = userRepository.findUserByEmail(user.getEmail());
		CreditCardUser creditCardUser1 = CreditCardUser.builder().creditCard(card1).user(user).reason("잘 어울려용").build();
		CreditCardUser creditCardUser2 = CreditCardUser.builder().creditCard(card3).user(user).reason("써보세용").build();
		CreditCardUser creditCardUser3 = CreditCardUser.builder().creditCard(card5).user(user).reason("절약돼용").build();
		em.persist(creditCardUser1);
		em.persist(creditCardUser2);
		em.persist(creditCardUser3);

		// 유저의 크레딧카드에도 저장?

		em.flush();
		em.clear();

		// 리스트로 뽑아보기
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().size()).isEqualTo(3);
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().get(0).getName()).isEqualTo(card1.getName());
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().get(1).getName()).isEqualTo(card3.getName());
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().get(2).getCompany()).isEqualTo(card5.getCompany());
		System.out.println(
			"creditCardService.selectAll(findUser.getEmail()).get(1).getReason() = " + creditCardService.selectAll(
				findUser.getEmail()).getCreditCardList().get(1).getReason());
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().get(1).getReason()).isEqualTo(creditCardUser2.getReason());
		assertThat(creditCardService.selectAll(findUser.getEmail()).getCreditCardList().get(2).getReason()).isEqualTo(creditCardUser3.getReason());
	}

	@Test
	void selectOne() {
		CreditCard card1 = CreditCard.builder().name("행복카드").company("현대카드").build();
		CreditCard card2 = CreditCard.builder().name("사랑카드").company("국민카드").build();
		CreditCard card3 = CreditCard.builder().name("나눔카드").company("농협카드").build();
		em.persist(card1);
		em.persist(card2);
		em.persist(card3);

		em.flush();
		em.clear();

		CreditCard creditCard1 = creditCardService.selectOne(card1.getId());
		CreditCard creditCard2 = creditCardService.selectOne(card2.getId());

		assertThat(creditCard1.getName()).isEqualTo(card1.getName());
		assertThat(creditCard2.getCompany()).isEqualTo(card2.getCompany());
	}

}