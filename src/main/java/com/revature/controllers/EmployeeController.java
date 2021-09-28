package com.revature.controllers;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.services.EmployeeServices;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class EmployeeController {

	private static EmployeeServices employeeServices = new EmployeeServices();
	private static Employee emp = new Employee();;
	private static EmployeeDAO empDao = new EmployeeDAO();

	public static void login(Context ctx) {
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		

		try {
			emp = employeeServices.loginValidator(username, password);
			
			
			ctx.cookieStore("id", emp.getEmployeeId());
			
			if (emp.isManager()) {
				
				ctx.redirect("/form/manager.html");
			} else if (!emp.isManager()) {
				ctx.redirect("/form/employee.html");
			} else {
				ctx.redirect("login.html");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void getAllEmployees(Context ctx) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			employees = empDao.getAllEmployees();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ctx.json(employees);

	}

	public static void getEmployee(Context ctx) {
		Employee emp = new Employee();
		
		int id = ctx.cookieStore("id");
		System.out.println(id);
		try {
			emp = empDao.getEmployeeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(emp.getUserName());
		ctx.json(emp);
	}

}
