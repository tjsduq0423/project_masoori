package com.fintech.masoori.domain.lucky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.lucky.entity.FortuneUser;

public interface FortuneUserRepository extends JpaRepository<FortuneUser, Long> {
	List<FortuneUser> findAllByUserId(long id);
}
