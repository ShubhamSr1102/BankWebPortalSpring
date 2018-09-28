package com.capgemini.bankwebportal.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Customer {

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress
				+ ", customerDateOfBirth=" + customerDateOfBirth + ", account=" + account + "]";
	}
	
	//@Size(min=3, message="Customer ID should have atleast 3 characters")
	@NotNull(message="Customer ID cannot be blank")
	@Positive(message="Customer ID cannot be negative")
	private long customerId;
	private String customerName;
	@NotEmpty(message="Password cannot be blank")
	@Size(min=5, message="Password should have atleast 5 characters")
//	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String customerPassword;
	@Email
	private String customerEmail;
	private String customerAddress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate customerDateOfBirth;
	private BankAccount account;
	public Customer() {
		super();
	}
	public Customer(long customerId, String customerName, String customerPassword, String customerEmail,
			String customerAddress, LocalDate customerDateOfBirth, BankAccount account) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerDateOfBirth = customerDateOfBirth;
		this.account = account;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public LocalDate getCustomerDateOfBirth() {
		return customerDateOfBirth;
	}
	public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) {
		this.customerDateOfBirth = customerDateOfBirth;
	}
	public BankAccount getAccount() {
		return account;
	}
	
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	@Override
	public int hashCode() {
		return Objects.hash(account);
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass()!= obj.getClass())
			return false;
		
		Customer customer = (Customer) obj;
		return this.customerId==customer.customerId;
	}
}