package com.capgemini.bankwebportal.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.capgemini.bankwebportal.model.Customer;

public interface CustomerRepository {

	public Customer authenticate(Customer customer)throws DataAccessException;

	public Customer updateProfile(Customer customer) throws SQLException;

	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);

	public Customer updateSession(long customerId) throws SQLException;
}
