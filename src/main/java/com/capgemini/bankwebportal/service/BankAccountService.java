package com.capgemini.bankwebportal.service;

import com.capgemini.bankwebportal.exceptions.AccountNotFoundException;
import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.exceptions.NegativeAmountException;

public interface BankAccountService {

	public double getBalance(long accountId)throws AccountNotFoundException;

	public double withdraw(long accountId, double amount);

	public double deposit(long accountId, double amount);

	public boolean fundTransfer(long fromAcc, long toAcc, double amount)
			throws InsufficientAccountBalanceException, NegativeAmountException, AccountNotFoundException;
}
