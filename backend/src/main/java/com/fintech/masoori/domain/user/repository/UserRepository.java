package com.fintech.masoori.domain.user.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintech.masoori.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);

	Optional<User> findByEmail(String email);

	User save(User newUser);

	List<User> findUsersByCardGenerationAndIsAuthenticated(Boolean cardGeneration, Boolean isAuthentication);

	List<User> findUsersByIsAuthenticated(Boolean isAuthentication);

	@Modifying
	@Query("UPDATE User u SET u.phoneNumber = :phoneNumber, u.name =:name WHERE u.email = :email")
	void updateInfo(@Param("email") String email, @Param("name") String name, @Param("phoneNumber") String phoneNumber);

	@Modifying
	@Query("UPDATE User u SET u.smsAlarm = CASE WHEN u.smsAlarm = true THEN false ELSE true END WHERE u.email = :email")
	void updateSmsAlarm(@Param("email") String email);

	@Modifying
	@Query("UPDATE User u SET u.cardGeneration = CASE WHEN u.cardGeneration = true THEN false ELSE true END WHERE u.email = :email")
	void updateCardGeneration(@Param("email") String email);

	@Modifying
	@Query("UPDATE User u SET u.smsAlarm = true, u.cardGeneration = true WHERE u.email = :email")
	void updateIntegration(@Param("email") String email);

	@Query("SELECT sum(d.amount) FROM Deal d WHERE d.user.email = :email AND d.date >= :startDate AND d.date <= :endDate")
	Integer getAmountSumByPeriod(@Param("email") String email, @Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate);

	@Modifying
	@Query("UPDATE User u SET u.monthlySpendingGoal = :monthlySpendingGoal WHERE u.email = :email")
	void updateMonthlySpendingGoal(@Param("email") String email, @Param("monthlySpendingGoal") Integer monthlySpendingGoal);

	@Modifying
	@Query("UPDATE User u SET u.cardImage = :imagePath WHERE u.email = :email")
	void updateUserProfileImage(@Param("imagePath") String imagePath, @Param("email") String email);

	@Modifying
	@Query("UPDATE User u SET u.isAuthenticated = true WHERE u.email = :email")
	void updateAuthentication(@Param("email") String email);

}
