package com.fintech.masoori.domain.credit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.dto.UserCreditCardRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.credit.exception.InvalidIDException;
import com.fintech.masoori.domain.credit.repository.CreditCardRepository;
import com.fintech.masoori.domain.credit.repository.CreditCardUserRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.rabbitMQ.dto.MonthlySpendingAndCreditcard;
import com.fintech.masoori.global.util.CalcDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {
	private final UserRepository userRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardUserRepository creditCardUserRepository;

	@Override
	public UserCreditCardRes selectAll(String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		List<UserCreditCardRes.UserCreditCard> userCreditCardList = user.getCreditCardUserList().stream().map(e -> {
			CreditCard creditCard = e.getCreditCard();
			return new UserCreditCardRes.UserCreditCard(creditCard);
		}).collect(Collectors.toList());
		return UserCreditCardRes.builder().userCreditCardList(userCreditCardList).build();
	}

	@Override
	public CreditCardRes selectMonth(String userEmail, LocalDateTime time) {
		User user = userRepository.findUserByEmail(userEmail);

		CalcDate.StartEndDate calcDate = CalcDate.calcDate(time, time);
		List<CreditCardUser> creditCardList = creditCardUserRepository.findCreditCardsByUserId(user.getId(),
			calcDate.getStartDate(), calcDate.getEndDate());

		List<CreditCardRes.CreditCard> creditCardResList = creditCardList.stream()
		                                                                 .map(
			                                                                 creditCardUser -> new CreditCardRes.CreditCard(
				                                                                 creditCardUser.getCreditCard()))
		                                                                 .collect(Collectors.toList());

		return CreditCardRes.builder().creditCardList(creditCardResList).build();
	}

	@Override
	public CreditCard selectOne(Long id) {
		CreditCard creditCard = creditCardRepository.findCreditCardById(id);
		if (creditCard == null) {
			throw new InvalidIDException("Id is not exist");
		}
		return creditCard;
	}

	@Override
	@Transactional
	public void saveRecommendedCreditCard(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard) {
		User serviceUser = userRepository.findById(monthlySpendingAndCreditcard.getUserId())
		                                 .orElseThrow(() -> new RuntimeException("User Not Found"));

		if(monthlySpendingAndCreditcard.getDate().isEmpty()) {
			log.debug("Date가 없습니다.");
			return;
		}
		if(monthlySpendingAndCreditcard.getCreditCardList().isEmpty()){
			log.info("추천된 카드가 없습니다.");
			return;
		}
		for (MonthlySpendingAndCreditcard.RecommendedCreditCard recommendedCreditCard : monthlySpendingAndCreditcard.getCreditCardList()) {
			CreditCard creditCard = creditCardRepository.findById(recommendedCreditCard.getCreditCardId())
			                                            .orElseThrow(
				                                            () -> new InvalidIDException("Card Id is Not exist"));


			String[] splitDate = monthlySpendingAndCreditcard.getDate().split("/");
			CreditCardUser creditCardUser = CreditCardUser.builder().reason(recommendedCreditCard.getReason()).year(Integer.parseInt(splitDate[0])).month(Integer.parseInt(splitDate[1])).build();

			creditCard.addCreditCardUser(creditCardUser);
			serviceUser.addCreditCardUser(creditCardUser);
			creditCardUserRepository.save(creditCardUser);

		}
	}

	@Override
	@Transactional
	public void updateRecommendedCreditCardCreatedDate(MonthlySpendingAndCreditcard monthlySpendingAndCreditcard) {
		String[] splitDate = monthlySpendingAndCreditcard.getDate().split("/");
		creditCardUserRepository.updateCreatedDate(
			LocalDateTime.of(
				Integer.parseInt(splitDate[0]),
				Integer.parseInt(splitDate[1]),
				Integer.parseInt(splitDate[2]),
				0,0),
			monthlySpendingAndCreditcard.getUserId(),
			Integer.parseInt(splitDate[0]),
			Integer.parseInt(splitDate[1]));
	}

}
