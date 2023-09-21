package com.fintech.masoori.domain.lucky.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "fortune", uniqueConstraints = {
	@UniqueConstraint(name = "uc_fortune_fortune_name", columnNames = {"fortune_name"})})
@ToString(of = {"id", "name", "imagePath", "description"})
public class Fortune {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lucky_id")
	private Long id;

	@Column(name = "fortune_name")
	private String name;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "description")
	private String description;
}
