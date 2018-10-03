package com.capgemini.bankwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.service.BankAccountService;
import com.capgemini.bankwebportal.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.GET)
	public String fundTransfer( @SessionAttribute("customer") Customer customer) {
		if (null == customer.getCustomerName()) {
			return "error";
		} else {
			return "fundTransfer";
		}
	}

	@ModelAttribute("customer")
	@RequestMapping(value = "/fundTransferMethod", method = RequestMethod.POST)
	public ModelAndView fundTransferMethod(HttpServletRequest request, @SessionAttribute("customer") Customer customer, @RequestParam long fromAcc,
			@RequestParam long toAcc, @RequestParam double amount) {
		ModelAndView modelAndView = new ModelAndView();
		if (bankAccountService.fundTransfer(fromAcc, toAcc, amount)) {
			request.setAttribute("success", "true");
		    customer = customerService.updateSession(customer.getCustomerId());
			modelAndView.addObject("customer", customer);
		}
		modelAndView.setViewName("fundTransfer");
		return modelAndView;
	}
}
