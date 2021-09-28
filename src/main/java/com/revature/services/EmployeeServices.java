package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;

import io.javalin.http.Context;

public class EmployeeServices {

	private EmployeeDAO emp = new EmployeeDAO();

	public Employee loginValidator(String username, String password) {
		Employee employee = new Employee();
		if (emp.loginValidation(username, password)) {
			employee = emp.getEmployee(username, password);

		}
		return employee;

	}
	
	public void newEmployee(Map<String, String> map) {
		Employee employee =  new Employee();
		employee.setFirstName(map.get("firstName"));
		employee.setLastName(map.get("lastName"));
		employee.setUserName(map.get("username"));
		employee.setPassword(map.get("password"));
		employee.setManager(false);
		
		emp.addEmployee(employee);
		
		}
	

	
	
}
