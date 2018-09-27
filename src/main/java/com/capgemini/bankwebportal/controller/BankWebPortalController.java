package com.capgemini.bankwebportal.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.bankwebportal.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankwebportal.exceptions.NegativeAmountException;
import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.service.impl.BankAccountServiceImpl;
import com.capgemini.bankwebportal.service.impl.CustomerServiceImpl;

@Controller
public class BankWebPortalController {

	@Autowired
	private BankAccountServiceImpl bankAccountServiceImpl;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String customerLogin(@ModelAttribute Customer customer) {
		request = (HttpServletRequest) request;
		if (null == request.getCookies()) {
			return "enableCookie";
		} else {
			try {
				System.out.println(customer);
				customer = customerServiceImpl.authenticate(customer);
			} catch (SQLException | NumberFormatException | EmptyResultDataAccessException e) {
				request.setAttribute("name", "true");
				System.out.println(customer);
				customer = null;
			} finally {
				System.out.println(customer);
				if (customer != null) {
					request.getSession(false);
					session.setAttribute("customer", customer);
					return "redirect:/home";
				}
				else
					return "index";
			}
			
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage() {
		request.getSession(false);
		Customer cust = (Customer) session.getAttribute("customer");
		Customer customer = customerServiceImpl.updateSession(cust.getCustomerId());
		request.getSession().setAttribute("customer", customer);
		return "home";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editProfile(Model model) {
		model.addAttribute("customer",session.getAttribute("customer"));
		request.getSession(false);
		if (null == session.getAttribute("customer")) {
			return "error";
		} else {
			return "edit";
		}
	}

	@RequestMapping(value = "/fundTransferMethod", method = RequestMethod.POST)
	public String fundTransferMethod() {
		long from = Long.parseLong(request.getParameter("fromAcc"));
		long to = Long.parseLong(request.getParameter("toAcc"));
		double amount = Double.parseDouble(request.getParameter("amount"));

		try {
			if (bankAccountServiceImpl.fundTransfer(from, to, amount)) {
				request.setAttribute("success", "true");
				request.getSession();
				Customer cust = (Customer) session.getAttribute("customer");
				Customer customer = customerServiceImpl.updateSession(cust.getCustomerId());
				request.getSession().setAttribute("customer", customer);
			}

		} catch (SQLException | InsufficientAccountBalanceException | NegativeAmountException | EmptyResultDataAccessException e) {
			if (e instanceof NegativeAmountException) {
				request.setAttribute("negativeamount", "true");
			} else if (e instanceof InsufficientAccountBalanceException) {
				request.setAttribute("insufficientbalance", "true");
			} else {
				request.setAttribute("accountnotfound", "true");
			}
		}
		return "fundTransfer";
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.GET)
	public String fundTransfer() {
		request.getSession(false);
		if (null == session.getAttribute("customer")) {

			return "error";
		} else {
			return "fundTransfer";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		request.getSession(false);
		session.invalidate();
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@RequestMapping(value = "/updatePasswordMethod", method = RequestMethod.POST)
	public String updatePasswordMethod() {
		request.getSession(false);
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		Customer customer = (Customer) (session.getAttribute("customer"));
		// customer.setCustomerPassword(oldPassword);
		if (oldPassword.equals(customer.getCustomerPassword())) {
			if (customerServiceImpl.updatePassword(customer, oldPassword, newPassword)) {
				return "redirect:/home";
			} else {
				request.setAttribute("passwordnotchanged", "true");
				return "changePassword";
			}
		} else {
			request.setAttribute("oldpassword", "false");
			return "changePassword";
		}
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword() {
		request.getSession(false);
		if (null == session.getAttribute("customer")) {
			return "error";
		} else {
			return "changePassword";
		}
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Customer customer) {
		customer = customerServiceImpl.updateProfile(customer);
		if (customer != null) {
			request.getSession().setAttribute("customer", customer);
			return "redirect:/home";
		} else {
			request.setAttribute("profileupdate", "false");
			return "edit";
		}
	}
}
