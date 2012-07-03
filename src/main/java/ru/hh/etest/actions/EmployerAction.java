package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

import ru.hh.etest.employer.createEmployer;

public class EmployerAction {
	private String message;
	private String action;
	private String server;

	public EmployerAction(HttpServletRequest request) {
		action = request.getParameter("action");
		server = request.getParameter("server");
		if(action.equals("createEmployer")){
			message = new createEmployer(server).getMessage();
		}	
	}

	public String getMessage() {
		return message;
		
	}
}
