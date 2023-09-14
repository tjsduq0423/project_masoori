package com.fintech.masoori.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.masoori.domain.user.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);

	Optional<User> findByEmail(String email);

	User save(User newUser);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.phoneNumber = :phoneNumber, u.name =:name WHERE u.email = :email")
	void updateInfo(@Param("email") String email, @Param("name") String name, @Param("phoneNumber") String phoneNumber);
}
