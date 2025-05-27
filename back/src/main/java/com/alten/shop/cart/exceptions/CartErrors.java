package com.alten.shop.cart.exceptions;

import org.springframework.http.HttpStatus;

import com.alten.shop.authentication.enums.LoginMode;


public enum CartErrors {

	ADD_TO_CART_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "", "Une erreur est survenue lors de l'ajout au panier"),
	CART_ITEM_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "", "Introuvale dans le panier"),
	CART_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "", "Le panier est introuvable");

	public final HttpStatus status;
	public final String code;
	public final String reason;
	public final LoginMode loginMode;

	private  CartErrors(HttpStatus status, String code, String reason) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = null;
	}

	private  CartErrors(HttpStatus status, String code, String reason, LoginMode loginMode) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = loginMode;
	}

}
