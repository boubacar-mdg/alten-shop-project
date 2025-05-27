package com.alten.shop.users.exceptions;

import org.springframework.http.HttpStatus;

import com.alten.shop.authentication.enums.LoginMode;


public enum UserErros {

	USER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "", "Utilisateur introuvable");

	public final HttpStatus status;
	public final String code;
	public final String reason;
	public final LoginMode loginMode;

	private  UserErros(HttpStatus status, String code, String reason) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = null;
	}

	private  UserErros(HttpStatus status, String code, String reason, LoginMode loginMode) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = loginMode;
	}

}
