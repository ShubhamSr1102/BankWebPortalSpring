package com.capgemini.bankwebportal.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String customerLogin(HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		request = (HttpServletRequest) request;
		if (null == request.getCookies()) {
			return "enableCookie";
		} else {
			customer = customerService.authenticate(customer);

			if (customer.getCustomerName() != null) {
				request.getSession(false);
				session.setAttribute("customer", customer);
				return "redirect:/home";
			}
			return "index";
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		Customer cust = (Customer) session.getAttribute("customer");
		Customer customer;
		try {
			customer = customerService.updateSession(cust.getCustomerId());
		} catch (SQLException e) {
			customer = null;
			e.printStackTrace();
		}
		request.getSession().setAttribute("customer", customer);
		return "home";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editProfile(Model model, HttpServletRequest request, HttpSession session) {
		model.addAttribute("customer", session.getAttribute("customer"));
		request.getSession(false);
		if (null == session.getAttribute("customer")) {
			return "error";
		} else {
			return "edit";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		session.invalidate();
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@RequestMapping(value = "/updatePasswordMethod", method = RequestMethod.POST)
	public String updatePasswordMethod(HttpServletRequest request, HttpSession session,
			@RequestParam String oldPassword, @RequestParam String newPassword) {
		request.getSession(false);
		Customer customer = (Customer) (session.getAttribute("customer"));
		// customer.setCustomerPassword(oldPassword);
		if (oldPassword.equals(customer.getCustomerPassword())) {
			if (customerService.updatePassword(customer, oldPassword, newPassword)) {
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
	public String updatePassword(HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		if (null == session.getAttribute("customer")) {
			return "error";
		} else {
			return "changePassword";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Customer customer, HttpServletRequest request, HttpSession session) {
		try {
			customer = customerService.updateProfile(customer);
		} catch (SQLException e) {
			customer = null;
			e.printStackTrace();
		} finally {
			if (customer != null) {
				request.getSession().setAttribute("customer", customer);
				return "redirect:/home";
			} else {
				request.setAttribute("profileupdate", "false");
				return "edit";
			}
		}
	}
}
