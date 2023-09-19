package com.fintech.masoori.domain.credit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.credit.entity.CreditCard;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	CreditCard save(CreditCard creditCard);

	CreditCardUser save(CreditCardUser creditCardUser);

	CreditCard findCreditCardById(Long id);
}
