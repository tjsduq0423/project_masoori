package com.fintech.masoori.global.oauth.exception;

public class TokenValidFailedException extends RuntimeException {

	public TokenValidFailedException() {
		super("Token is not valid");
	}

	private TokenValidFailedException(String message) {
		super(message);
	}
}
