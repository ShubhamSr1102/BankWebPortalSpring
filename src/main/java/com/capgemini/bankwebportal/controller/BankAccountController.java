package com.capgemini.bankwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.service.BankAccountService;
import com.capgemini.bankwebportal.service.CustomerService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.GET)
	public String fundTransfer(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		if (null == session.getAttribute("customer")) {

			return "error";
		} else {
			return "fundTransfer";
		}
	}

	@RequestMapping(value = "/fundTransferMethod", method = RequestMethod.POST)
	public String fundTransferMethod(HttpServletRequest request, HttpSession session, @RequestParam long fromAcc,
			@RequestParam long toAcc, @RequestParam double amount) {
		if (bankAccountService.fundTransfer(fromAcc, toAcc, amount)) {
			request.setAttribute("success", "true");
			request.getSession();
			Customer cust = (Customer) session.getAttribute("customer");
			Customer customer = customerService.updateSession(cust.getCustomerId());
			request.getSession().setAttribute("customer", customer);
		}
		return "fundTransfer";
	}
}
