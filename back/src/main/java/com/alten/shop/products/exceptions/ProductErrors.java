package com.alten.shop.products.exceptions;

import org.springframework.http.HttpStatus;

import com.alten.shop.authentication.enums.LoginMode;


public enum ProductErrors {

	PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "", "Le produit demandé est introuvable"),
	OUTOFSTOCK(HttpStatus.BAD_REQUEST, "", "Aucun stock disponible pour ce produit"),
	UNSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "", "Stock insuffisant"),
	UNAUTHORIZED_PRODUCT_MANAGEMENT(HttpStatus.BAD_REQUEST, "", "Vous n'avez les droits pour gérer les produits");

	public final HttpStatus status;
	public final String code;
	public final String reason;
	public final LoginMode loginMode;

	private  ProductErrors(HttpStatus status, String code, String reason) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = null;
	}

	private  ProductErrors(HttpStatus status, String code, String reason, LoginMode loginMode) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.loginMode = loginMode;
	}

}
