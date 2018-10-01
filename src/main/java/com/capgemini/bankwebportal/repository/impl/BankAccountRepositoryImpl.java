package com.capgemini.bankwebportal.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankwebportal.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) throws DataAccessException {
		try {
			return jdbcTemplate.queryForObject("SELECT balance FROM accounts WHERE account_id = ?",
					new Object[] { accountId }, Double.class);
		} catch (DataAccessException e) {
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0 ", 1));
			throw e;
		}
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) throws DataAccessException {
		int count = jdbcTemplate.update("UPDATE accounts SET balance = ? WHERE account_id = ?",
				new Object[] { newBalance, accountId });
		return (count != 0) ? true : false;
	}
}
