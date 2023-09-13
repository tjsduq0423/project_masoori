package com.fintech.masoori.domain.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fintech.masoori.domain.credit.entity.CreditCardUser;
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

	@Column(name = "nickname")
	//	@Column(name = "nickname", length = 20, nullable = false)
	private String nickname;

	@Column(name = "password")
	private String password;

	@Column(name = "profile")
	private String profile;

	@Column(name = "roles")
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "provider_type", length = 20, nullable = false)
	private ProviderType providerType;

	@OneToMany(mappedBy = "user")
	private List<CreditCardUser> creditCardUsers = new ArrayList<>();

	public User(String email, String password) {
		this.email = email;
		this.password = password;
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

	public void updatePassword(String password) {
		this.password = password;
	}

	public void updateProfile(String profile) {
		this.profile = profile;
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}
}
