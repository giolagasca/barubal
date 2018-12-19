package com.accenture.tcf.bars.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="request_id")
	private int requestID;
	@Column(name="billing_cycle")
	private int billingCycle;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	
	public Request() {}

	public Request(int billingCycle, Date startDate, Date endDate) {
		setBillingCycle(billingCycle);
		setStartDate(startDate);
		setEndDate(endDate);
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

	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", billingCycle=" + billingCycle + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
}
