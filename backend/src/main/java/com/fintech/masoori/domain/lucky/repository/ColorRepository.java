package com.fintech.masoori.domain.lucky.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.lucky.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	long count();

	Page<Color> findAll(Pageable pageable);

	Color findByColorName(String colorName);
}
