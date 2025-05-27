package com.alten.shop.wishlist.exceptions;

import com.alten.shop.commons.exceptions.ApplicationResponseStatusException;

public class WishlistException extends ApplicationResponseStatusException {
	private WishlistErrors errors;

	public WishlistException(WishlistErrors errors) {
		super(errors.status, errors.name(), errors.reason, errors.loginMode);
		this.errors = errors;
	}

	public WishlistException(WishlistErrors errors, Throwable cause) {
		super(errors.status, errors.name(), errors.reason, cause);
		this.errors = errors;
	}

	public WishlistErrors getErrors() {
		return errors;
	}
}
