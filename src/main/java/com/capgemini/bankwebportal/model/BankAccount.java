package com.capgemini.bankwebportal.model;

import java.util.Objects;

public class BankAccount {

	private long accountId;
	private String accountType;
	private double balance;
	public BankAccount(long accountId, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
	}
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getAccountId() {
		return accountId;
	}
	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance + "]";
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass()!= obj.getClass())
			return false;
		
		BankAccount account = (BankAccount) obj;
		return this.accountId==account.accountId;
	}
}
