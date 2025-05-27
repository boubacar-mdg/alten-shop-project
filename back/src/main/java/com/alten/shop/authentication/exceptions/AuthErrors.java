package com.alten.shop.authentication.exceptions;

import org.springframework.http.HttpStatus;

import com.alten.shop.authentication.enums.LoginMode;


public enum AuthErrors {

	USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "", "Un compte est déjà associé à l'email renseigné"),
	INVALID_EMAIL(HttpStatus.BAD_REQUEST, "", "L'email renseigné n'est pas valide"),
	INVALID_CREDIDENTIALS(HttpStatus.BAD_REQUEST, "", "Email ou mot de passe invalide"),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "", "Un compte avec cet email est introuvable"),
	NO_SESSION(HttpStatus.UNAUTHORIZED, "", "Votre session a expiré ou n'est pas valide"),
	NO_SESSION_IMPLICIT(HttpStatus.UNAUTHORIZED, "", "Votre session a expiré ou n'est pas valide", LoginMode.IMPLICIT),
	EMPTY_FIELDS(HttpStatus.BAD_REQUEST, "", "Merci de remplir tous les champs");

	public final HttpStatus status;
	public final String code;
	public final String reason;
	public final LoginMode loginMode;

	private  AuthErrors(HttpStatus status, String code, String reason) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = null;
	}

	private  AuthErrors(HttpStatus status, String code, String reason, LoginMode loginMode) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = loginMode;
	}

}
