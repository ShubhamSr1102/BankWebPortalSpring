package com.capgemini.bankwebportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankwebportal.exceptions.AccountNotFound;
import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.model.Customer;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(value = AccountNotFound.class)
	public String handlheError(HttpServletRequest request, AccountNotFound exception, Model model) {
		System.out.println(exception);
		
		request.setAttribute("name", "true");
		//request.setAttribute("error", exception);
		System.out.println(exception.getCause());
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@ExceptionHandler(value = InsufficientAccountBalanceException.class)
	public String handlheErrorf(HttpServletRequest request, InsufficientAccountBalanceException exception) {
		System.out.println(exception);
		request.setAttribute("success", false);
		request.setAttribute("error", exception);
		System.out.println(exception.getCause());
		return "success";
	}
}
