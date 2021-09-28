package com.revature.controllers;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

import io.javalin.http.Context;

public class ReimbursementController {
	
	private static ReimbursementDAO remDAO = new ReimbursementDAO();
	
	public static void createReimbursement(Context ctx) {
		Reimbursement rem = new Reimbursement();
		rem.setEmployeeId(ctx.cookieStore("id"));
		
		rem.setReimbursementType(ctx.formParam("reimbursementType"));
		rem.setAmount(Double.parseDouble(ctx.formParam("reimbursementAmount")));
		rem.setApprovalStatus("Pending");
		
		try {
			remDAO.insertReimbursement(rem);
			ctx.redirect("/form/employee.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void statusUpdate(Context ctx) {
		Reimbursement rem = new Reimbursement();
		String[] split = ctx.body().split("\"");
		
		rem.setApprovalStatus(split[7]);
		rem.setReimbursementId(Integer.parseInt(split[3]));
		String check = rem.getApprovalStatus();
		if(check.equals("Approved") || check.equals("Denied")) {
		try {
			remDAO.updateReimbursement(rem);
//			ctx.redirect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	}

}
