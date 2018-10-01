package com.capgemini.bankwebportal.exceptions;

public class NegativeAmountException extends RuntimeException {

	public NegativeAmountException(String message)
	{
		super(message);
	}
}
