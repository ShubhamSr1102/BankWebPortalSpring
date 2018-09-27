package com.capgemini.bankwebportal.exceptions;

public class InsufficientAccountBalanceException extends Exception {

	public InsufficientAccountBalanceException(String message)
	{
		super(message);
	}
	
}
