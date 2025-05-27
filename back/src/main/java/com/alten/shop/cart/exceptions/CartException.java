package com.alten.shop.cart.exceptions;

import com.alten.shop.commons.exceptions.ApplicationResponseStatusException;

public class CartException extends ApplicationResponseStatusException {
	private CartErrors errors;

	public CartException(CartErrors errors) {
		super(errors.status, errors.name(), errors.reason, errors.loginMode);
		this.errors = errors;
	}

	public CartException(CartErrors errors, Throwable cause) {
		super(errors.status, errors.name(), errors.reason, cause);
		this.errors = errors;
	}

	public CartErrors getErrors() {
		return errors;
	}
}
