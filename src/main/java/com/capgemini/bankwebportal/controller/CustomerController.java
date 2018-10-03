package com.capgemini.bankwebportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.bankwebportal.model.Customer;
import com.capgemini.bankwebportal.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(Model model) {
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@ModelAttribute("customer")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView customerLogin(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		ModelAndView modelAndView =  new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("index");
			return modelAndView;
		}
		/*
		 * if (null == request.getCookies()) { return "enableCookie"; }
		 */
		customer = customerService.authenticate(customer);
		modelAndView.addObject("customer", customer);
		modelAndView.setViewName("redirect:/home");
		//model.addAttribute("customer", customer);
		return modelAndView;
	}

	@ModelAttribute("customer")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(@SessionAttribute("customer") Customer customer) {
		ModelAndView modelAndView =  new ModelAndView();
		customer = customerService.updateSession(customer.getCustomerId());
		modelAndView.addObject("customer", customer);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editProfile(Model model, @SessionAttribute("customer") Customer customer) {
		model.addAttribute("customer", customer);
		if (null == customer.getCustomerName()) {
			return "error";
		} else {
			return "edit";
		}
	}
	
	@ModelAttribute("customer")
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfileMethod(@ModelAttribute Customer customer) {
		ModelAndView modelAndView =  new ModelAndView();
		customer = customerService.updateProfile(customer);
		modelAndView.addObject("customer", customer);
		modelAndView.setViewName("redirect:/home");
		return modelAndView;

	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpSession session) {
		request.getSession(false);
		session.invalidate();
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword(@SessionAttribute("customer") Customer customer) {
		if (null == customer.getCustomerName()) {
			return "error";
		} else {
			return "changePassword";
		}
	}

	@ModelAttribute("customer")
	@RequestMapping(value = "/updatePasswordMethod", method = RequestMethod.POST)
	public ModelAndView updatePasswordMethod(HttpServletRequest request, @SessionAttribute("customer") Customer customer,@RequestParam String oldPassword, @RequestParam String newPassword) {
		// customer.setCustomerPassword(oldPassword);
		ModelAndView modelAndView =  new ModelAndView();
		if (oldPassword.equals(customer.getCustomerPassword())) {
			if (customerService.updatePassword(customer, oldPassword, newPassword)) {
				modelAndView.addObject("customer", customer);
				modelAndView.setViewName("redirect:/home");
				return modelAndView;
			} else {
				request.setAttribute("passwordnotchanged", "true");
				modelAndView.setViewName("changePassword");
				return modelAndView;
			}
		} else {
			request.setAttribute("oldpassword", "false");
			modelAndView.setViewName("changePassword");
			return modelAndView;
		}
	}


	
}
