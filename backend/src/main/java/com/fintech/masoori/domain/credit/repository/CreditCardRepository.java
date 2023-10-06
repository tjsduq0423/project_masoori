package com.fintech.masoori.domain.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.credit.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	CreditCard findCreditCardById(Long id);

}
