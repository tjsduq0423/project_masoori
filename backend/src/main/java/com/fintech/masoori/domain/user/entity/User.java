package com.fintech.masoori.domain.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fintech.masoori.domain.analytics.entity.MonthlySpendingAnalytics;
import com.fintech.masoori.domain.card.entity.Card;
import com.fintech.masoori.domain.credit.entity.CreditCardUser;
import com.fintech.masoori.domain.deal.entity.Deal;
import com.fintech.masoori.domain.lucky.entity.FortuneUser;
import com.fintech.masoori.global.oauth.ProviderType;
import com.fintech.masoori.global.util.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", length = 80, nullable = false)
	private String email;

	@Column(name = "name", length = 25)
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "card_image")
	private String cardImage;

	@Column(name = "phone_number", length = 25)
	private String phoneNumber;

	@Column(name = "is_authenticated")
	@Builder.Default
	private Boolean isAuthenticated = false; // 사용자 휴대폰 인증 여부

	@Column(name = "sms_alarm")
	@Builder.Default
	private Boolean smsAlarm = false; // SMS 알림 연동 여부

	@Column(name = "card_generation")
	@Builder.Default
	private Boolean cardGeneration = false; // 소비카드 생성 연동 여부

	@Column(name = "roles")
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "provider_type", length = 20, nullable = false)
	private ProviderType providerType;

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<CreditCardUser> creditCardUsers = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<Card> cardList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<FortuneUser> fortuneUserList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<MonthlySpendingAnalytics> monthlySpendingAnalyticsList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<Deal> dealList = new ArrayList<>();

	public void updatePassword(String password) {
		this.password = password;
	}

	public void updateCardImage(String cardImage) {
		this.cardImage = cardImage;
	}

	public void addDealInfo(Deal deal) {
		dealList.add(deal);
		deal.setUser(this);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void addFortuneUser(FortuneUser fortuneUser) {
		fortuneUserList.add(fortuneUser);
		fortuneUser.setUser(this);
	}
}
