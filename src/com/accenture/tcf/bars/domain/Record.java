package com.accenture.tcf.bars.domain;

import java.util.Date;

public class Record {
	private int billingCycle;
	private Date startDate;
	private Date endDate;
	private String accountName;
	private String firstName;
	private String lastName;
	private double amount;
	
	public Record() {}
	
	public Record(int billingCycle, Date startDate, Date endDate, String accountName, String firstName, String lastName,
			double amount) {
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountName = accountName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
	}
	
	public Record(Request request) {
		setBillingCycle(request.getBillingCycle());
		setStartDate(request.getStartDate());
		setEndDate(request.getEndDate());
	}

	
	public int getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Record [billingCycle=" + billingCycle + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", accountName=" + accountName + ", firstName=" + firstName + ", lastName=" + lastName + ", amount="
				+ amount + "]";
	}
	
	
}
