package com.fintech.masoori.domain.credit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.masoori.domain.credit.dto.CreditCardRes;
import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.credit.exception.InvalidIDException;
import com.fintech.masoori.domain.credit.repository.CreditCardRepository;
import com.fintech.masoori.domain.credit.repository.CreditCardUserRepository;
import com.fintech.masoori.domain.user.entity.User;
import com.fintech.masoori.domain.user.repository.UserRepository;
import com.fintech.masoori.global.util.CalcDate;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
	private final UserRepository userRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardUserRepository creditCardUserRepository;

	@Override
	public CreditCardRes selectAll(String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		List<CreditCardRes.CreditCard> userCreditCardList = user.getCreditCardUsers().stream().map(e -> {
			CreditCard creditCard = e.getCreditCard();
			String reason = e.getReason();
			CreditCardRes.CreditCard creditCard1 = new CreditCardRes.CreditCard(creditCard);
			creditCard1.setReason(reason);
			return creditCard1;
		}).collect(Collectors.toList());

		return new CreditCardRes(userCreditCardList);
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

		return CreditCardRes.builder()
							.creditCardList(creditCardResList)
							.build();
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
	public void save(CreditCard creditCard) {
		creditCardRepository.save(creditCard);
	}
}
