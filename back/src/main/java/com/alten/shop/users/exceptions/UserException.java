package com.alten.shop.users.exceptions;

import com.alten.shop.commons.exceptions.ApplicationResponseStatusException;

public class UserException extends ApplicationResponseStatusException {
	private UserErros errors;

	public UserException(UserErros errors) {
		super(errors.status, errors.name(), errors.reason, errors.loginMode);
		this.errors = errors;
	}

	public UserException(UserErros errors, Throwable cause) {
		super(errors.status, errors.name(), errors.reason, cause);
		this.errors = errors;
	}

	public UserErros getErrors() {
		return errors;
	}
}
