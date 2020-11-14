package com.itau.taskcontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessRuleException(String message) {
		super(message);
	}

	public BusinessRuleException(String message, Throwable t) {
		super(message, t);
	}
}