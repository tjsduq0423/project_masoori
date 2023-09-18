package com.fintech.masoori.domain.faq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.faq.entity.Faq;

public interface FaqRepository extends JpaRepository<Faq, Long> {
	public List<Faq> findAll();
}
