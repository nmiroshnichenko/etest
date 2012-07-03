package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

import ru.hh.etest.applicant.CreateApplicant;

public class ApplicantAction {
	private String message;
	private String action;
	private String server;

	public ApplicantAction(HttpServletRequest request) {
		action = request.getParameter("action");
		server = request.getParameter("server");
		if(action.equals("createApplicant")){
			message = new CreateApplicant(server).getMessage();
			
		}
		
	}
	public String getMessage() {
		return message;
	}
}
