package com.revature.endpoints;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import com.revature.services.*;

public class Endpoints {

	private static Javalin javalin;

	public static void init(Javalin app) {
		javalin = app;

		app.routes(() -> {
			path("/login", () -> {
				post(EmployeeController::login);
			});

		});

		app.routes(() -> {
			path("/form/manager", () -> {
				get(EmployeeController::getAllEmployees);
				post(ReimbursementController::createReimbursement);
				});
			});

		app.patch("/form/manager/updateForm", ReimbursementController::statusUpdate);

		app.routes(() -> {
			path("/form/employee", () -> {
				get(EmployeeController::getEmployee);
				post(ReimbursementController::createReimbursement);
			});

		});

	}
}
