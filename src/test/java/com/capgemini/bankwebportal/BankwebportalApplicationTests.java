package com.capgemini.bankwebportal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.capgemini.bankwebportal.controller.CustomerController;
import com.capgemini.bankwebportal.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankwebportalApplicationTests {

	Customer customer;
	@Autowired
	CustomerController customerController;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	BindingResult bindingResult;

	@Test
	public void loginTest() {
		Customer customer=new Customer();
		customer.setCustomerId(123);
		customer.setCustomerPassword("shubham");
		String result=customerController.customerLogin(request, session, customer, bindingResult);
		/*assertEquals("redirect:/home",result);
		assert*/
		
	}

}
