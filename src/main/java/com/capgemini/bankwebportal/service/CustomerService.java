package com.capgemini.bankwebportal.service;

import com.capgemini.bankwebportal.exceptions.AccountNotFoundException;
import com.capgemini.bankwebportal.model.Customer;

public interface CustomerService {

	public Customer authenticate(Customer customer) throws AccountNotFoundException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
	public Customer updateSession(long customerId); 

}
