package com.alten.shop.products.exceptions;

import com.alten.shop.commons.exceptions.ApplicationResponseStatusException;

public class ProductException extends ApplicationResponseStatusException {
	private ProductErrors errors;

	public ProductException(ProductErrors errors) {
		super(errors.status, errors.name(), errors.reason, errors.loginMode);
		this.errors = errors;
	}

	public ProductException(ProductErrors errors, Throwable cause) {
		super(errors.status, errors.name(), errors.reason, cause);
		this.errors = errors;
	}

	public ProductErrors getErrors() {
		return errors;
	}
}
