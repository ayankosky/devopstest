package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="reimbursement")
	@Column(name = "Reimbursement_ID")
	private int reimbursementId;
	@Column(name = "Employee_Id")
	private int employeeId;
	@Column(name = "Reimbursement_Type")
	private String reimbursementType;
	@Column(name = "Amount")
	private double amount;
	@Column(name = "Approval_Status")
	private String approvalStatus;
	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reimbursement(int reimbusementId, int employeeId, String reimbursementType, double amount,
			String approvalStatus) {
		super();
		this.reimbursementId = reimbusementId;
		this.employeeId = employeeId;
		this.reimbursementType = reimbursementType;
		this.amount = amount;
		this.approvalStatus = approvalStatus;
	}


	public int getReimbursementId() {
		return reimbursementId;
	}


	public void setReimbursementId(int reimbusementId) {
		this.reimbursementId = reimbusementId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getReimbursementType() {
		return reimbursementType;
	}


	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
	

}
