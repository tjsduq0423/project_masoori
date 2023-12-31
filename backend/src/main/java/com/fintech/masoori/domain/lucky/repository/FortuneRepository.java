package com.fintech.masoori.domain.lucky.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.lucky.entity.Fortune;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {
	long count();

	Page<Fortune> findAll(Pageable pageable);

	Fortune findDescriptioneByName(String name);

	List<Fortune> findAll();
}
