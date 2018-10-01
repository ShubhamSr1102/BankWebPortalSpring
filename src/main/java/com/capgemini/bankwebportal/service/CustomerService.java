package com.capgemini.bankwebportal.service;

import java.sql.SQLException;

import com.capgemini.bankwebportal.exceptions.AccountNotFound;
import com.capgemini.bankwebportal.model.Customer;

public interface CustomerService {

	public Customer authenticate(Customer customer) throws AccountNotFound;
	public Customer updateProfile(Customer customer) throws SQLException;
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
	public Customer updateSession(long customerId) throws SQLException; 

}
