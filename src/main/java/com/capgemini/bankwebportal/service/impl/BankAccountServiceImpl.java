package com.capgemini.bankwebportal.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankwebportal.exceptions.AccountNotFoundException;
import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.exceptions.NegativeAmountException;
import com.capgemini.bankwebportal.repository.impl.BankAccountRepositoryImpl;
import com.capgemini.bankwebportal.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountRepositoryImpl bankAccountRepositoryImpl;
	@Autowired
	BankAccountServiceImpl serviceobject;

	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		try {
			return bankAccountRepositoryImpl.getBalance(accountId);
		} catch (DataAccessException e) {
			AccountNotFoundException accountNotFound = new AccountNotFoundException("User does not exist!");
			accountNotFound.initCause(e);
			throw accountNotFound;
		}
	}

	@Override
	public double withdraw(long accountId, double amount) {
		double accountBalance = bankAccountRepositoryImpl.getBalance(accountId);
		bankAccountRepositoryImpl.updateBalance(accountId, accountBalance - amount);
		return accountBalance - amount;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double accountBalance = bankAccountRepositoryImpl.getBalance(accountId);
		bankAccountRepositoryImpl.updateBalance(accountId, accountBalance + amount);
		return accountBalance + amount;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount)
			throws InsufficientAccountBalanceException, NegativeAmountException {
		double accountBalanceFrom = bankAccountRepositoryImpl.getBalance(fromAcc);

		if (accountBalanceFrom < amount)
			throw new InsufficientAccountBalanceException("There isn't sufficient balance in your account!");
		else if (amount < 0)
			throw new NegativeAmountException("The amount cannot be negative!");
		else {
			serviceobject.deposit(toAcc, amount);
			serviceobject.withdraw(fromAcc, amount);
			return true;
		}
	}

}
