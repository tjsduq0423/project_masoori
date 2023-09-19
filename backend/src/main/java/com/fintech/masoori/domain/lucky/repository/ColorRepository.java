package com.fintech.masoori.domain.lucky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.lucky.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	Color findDescriptionByColor(String color);
}
