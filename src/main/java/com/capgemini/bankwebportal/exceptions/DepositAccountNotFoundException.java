package com.capgemini.bankwebportal.exceptions;

public class DepositAccountNotFoundException extends RuntimeException {

	public DepositAccountNotFoundException(String message) {
		super(message);
	}
}
