package com.capgemini.bankwebportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankwebportal.exceptions.AccountNotFoundException;
import com.capgemini.bankwebportal.exceptions.DepositAccountNotFoundException;
import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.exceptions.NegativeAmountException;
import com.capgemini.bankwebportal.repository.BankAccountRepository;
import com.capgemini.bankwebportal.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Override
	public double getBalance(long accountId) throws DepositAccountNotFoundException {
		try {
			return bankAccountRepository.getBalance(accountId);
		} catch (DataAccessException e) {
			DepositAccountNotFoundException depositAccountNotFoundException = new DepositAccountNotFoundException("User does not exist!");
			depositAccountNotFoundException.initCause(e);
			throw depositAccountNotFoundException;
		}
	}

	@Override
	public double withdraw(long accountId, double amount) {
		double accountBalance = getBalance(accountId);
		bankAccountRepository.updateBalance(accountId, accountBalance - amount);
		return accountBalance - amount;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double accountBalance = getBalance(accountId);
		bankAccountRepository.updateBalance(accountId, accountBalance + amount);
		return accountBalance + amount;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount)
			throws InsufficientAccountBalanceException, NegativeAmountException, AccountNotFoundException {
		double accountBalanceFrom = getBalance(fromAcc);

		if (accountBalanceFrom < amount)
			throw new InsufficientAccountBalanceException("There isn't sufficient balance in your account!");
		else if (amount < 0)
			throw new NegativeAmountException("The amount cannot be negative!");
		else {
			deposit(toAcc, amount);
			withdraw(fromAcc, amount);
			return true;
		}
	}

}
