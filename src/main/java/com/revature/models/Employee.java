package com.revature.models;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;





@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="employees")
	@Column(name = "Employee_ID")
	private int employeeId;
	@Column(name = "First_Name")
	private String firstName;
	@Column(name = "Last_Name")
	private String lastName;
	@Column(name = "User_Name")
	private String userName;
	@Column(name = "Password")
	private String password;
	@Column(name = "Is_Manager")
	private boolean isManager;
	@OneToMany(fetch = FetchType.EAGER )
	@JoinColumn(name = "Employee_Id")
	private Collection<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	
	
	

	public Collection<Reimbursement> getReimbursements() {
		return reimbursements;
	}


	public void setReimbursements(Collection<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(int employeeId, String firstName, String lastName, String userName, String password,
			boolean isManager) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isManager = isManager;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isManager() {
		return isManager;
	}


	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	
	

}
