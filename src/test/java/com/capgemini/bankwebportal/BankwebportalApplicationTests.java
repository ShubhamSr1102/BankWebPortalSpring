package com.capgemini.bankwebportal;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.capgemini.bankwebportal.controller.CustomerController;
import com.capgemini.bankwebportal.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankwebportalApplicationTests {

	@Autowired
	CustomerController customerController;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	/*@Autowired
	Model model;*/

	@Test
	public void loginTest() {
		Customer customer = new Customer();
		customer.setCustomerId(123);
		customer.setCustomerPassword("shubham");
		BindingResult bindingResult = new BeanPropertyBindingResult(customer, "customer");
		String result = customerController.customerLogin(request, session, customer, bindingResult);

		assertEquals(result, "redirect:/home");
	}

	@Test
	public void getHomePageTest() {
		Model model=new ExtendedModelMap() ;
		String result = customerController.getHomePage(model);
		
		assertEquals(result, "index");
	}

}
