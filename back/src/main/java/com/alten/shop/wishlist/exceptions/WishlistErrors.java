package com.alten.shop.wishlist.exceptions;

import org.springframework.http.HttpStatus;

import com.alten.shop.authentication.enums.LoginMode;


public enum WishlistErrors {

	ALREADY_IN_WISHLIST(HttpStatus.INTERNAL_SERVER_ERROR, "", "Ce produit est déjà dans votre liste d'envie"),
	NOT_FOUND_IN_WISHLIST(HttpStatus.INTERNAL_SERVER_ERROR, "", "Ce produit est introuvable dans votre liste d'envie");

	public final HttpStatus status;
	public final String code;
	public final String reason;
	public final LoginMode loginMode;

	private  WishlistErrors(HttpStatus status, String code, String reason) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = null;
	}

	private  WishlistErrors(HttpStatus status, String code, String reason, LoginMode loginMode) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = loginMode;
	}

}
