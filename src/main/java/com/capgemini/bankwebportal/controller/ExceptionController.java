package com.capgemini.bankwebportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankwebportal.exceptions.AccountNotFoundException;
import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.exceptions.NegativeAmountException;
import com.capgemini.bankwebportal.model.Customer;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = AccountNotFoundException.class)
	public String AccountNotFoundException(HttpServletRequest request, AccountNotFoundException exception,
			Model model) {
		System.out.println(exception);

		request.setAttribute("name", "true");
		request.setAttribute("accountnotfound", "true");
		System.out.println(exception.getCause());
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@ExceptionHandler(value = InsufficientAccountBalanceException.class)
	public String InsufficientAccountBalanceException(HttpServletRequest request,
			InsufficientAccountBalanceException exception) {
		System.out.println(exception);
		request.setAttribute("insufficientbalance", "true");
		System.out.println(exception.getCause());
		return "success";
	}

	@ExceptionHandler(value = NegativeAmountException.class)
	public String NegativeAmountException(HttpServletRequest request, NegativeAmountException exception) {
		System.out.println(exception);
		request.setAttribute("negativeamount", "true");
		System.out.println(exception.getCause());
		return "edit";
	}
}
