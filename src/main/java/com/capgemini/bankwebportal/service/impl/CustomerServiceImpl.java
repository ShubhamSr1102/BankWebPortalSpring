package com.capgemini.bankwebportal.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankwebportal.exceptions.AccountNotFound;
import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.repository.CustomerRepository;
import com.capgemini.bankwebportal.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authenticate(Customer customer) throws AccountNotFound {
		try {
			return customerRepository.authenticate(customer);
		} catch (DataAccessException e) {
			AccountNotFound accountNotFound = new AccountNotFound("User does not exist!");
			accountNotFound.initCause(e);
			throw accountNotFound;
		}

	}

	@Override
	public Customer updateProfile(Customer customer) throws SQLException {
		return customerRepository.updateProfile(customer);
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
	}

	@Override
	public Customer updateSession(long customerId) throws SQLException {
		return customerRepository.updateSession(customerId);
	}

}
