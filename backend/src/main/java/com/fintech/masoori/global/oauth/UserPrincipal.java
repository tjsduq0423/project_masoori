package com.fintech.masoori.global.oauth;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fintech.masoori.domain.user.UserRole;
import com.fintech.masoori.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails, OidcUser {
	private final String email;
	private final String nickname;
	private final String password;
	private final ProviderType providerType;
	private final UserRole userRole;
	private final Collection<GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public static UserPrincipal create(User user) {
		return new UserPrincipal(user.getEmail(), user.getNickname(), user.getPassword(), user.getProviderType(),
			UserRole.ROLE_USER, Collections.singletonList(new SimpleGrantedAuthority(UserRole.ROLE_USER.name())));
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	@Override
	public String getName() {
		return email;
	}

	@Override
	public String getUsername() {
		return nickname;
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

	@Override
	public Map<String, Object> getClaims() {
		return null;
	}

	@Override
	public OidcUserInfo getUserInfo() {
		return null;
	}

	@Override
	public OidcIdToken getIdToken() {
		return null;
	}
}
