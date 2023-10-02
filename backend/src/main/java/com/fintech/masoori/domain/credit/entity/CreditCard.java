package com.fintech.masoori.domain.credit.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "credit_card")
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_card_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "company")
	private String company;

	@Column(name = "domestic")
	private String domestic;

	@Column(name = "overseas")
	private String overseas;

	@Column(name = "conditions")
	private String condition;

	@Column(name = "brands")
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<String> brands = new ArrayList<>();

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "img_attr")
	private String imgAttr;

	@Column(name = "register_path", columnDefinition = "TEXT")
	private String registerPath;

	@OneToMany(mappedBy = "creditCard")
	@Builder.Default
	private List<CreditCardUser> creditCardUserList = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "benefits", joinColumns = @JoinColumn(name = "credit_card_id"))
	@Builder.Default
	private List<Benefit> benefits = new ArrayList<>();

	public void addCreditCardUser(CreditCardUser creditCardUser) {
		this.creditCardUserList.add(creditCardUser);
		creditCardUser.setCreditCard(this);
	}

}
