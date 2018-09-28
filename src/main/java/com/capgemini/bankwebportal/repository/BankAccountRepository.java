package com.capgemini.bankwebportal.repository;

import java.sql.SQLException;

public interface BankAccountRepository {

	public double getBalance(long accountId);
	public boolean updateBalance(long accountId, double newBalance);
}
