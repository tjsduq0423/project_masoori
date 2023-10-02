package com.fintech.masoori.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	// example
	// EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
	// LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid");

	// User
	EMAIL_DUPLICATION(409, "U001", "Email is Duplicated"),
	USER_NOT_FOUND(400, "U002", "User Not Found"),
	INVALID_AUTH_CODE(400, "U003", "Invalid Auth Code"),
	REDIS_NOT_CONNECTED(400, "U004", "Redis Not Connected"),
	PASSWORD_IS_NOT_MATCHED(400, "U005", "Password is not Matched"),
	PROVIDER_IS_NOT_MATCHED(400, "U006", "Provider is not Matched"),
	// Common
	INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
	METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
	ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
	INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
	INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
	HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
	QUERY_TIMEOUT(400, "C007", "Query Timeout"),
	CAN_CREATE(400, "C008", "Can Create Card"),
	ALREADY_IN_PROGRESS(400, "C009", "Already In Process");


	private final String code;
	private final String message;
	private final int status;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

}
