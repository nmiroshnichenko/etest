package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

import ru.hh.etest.employer.createEmployer;

public class EmployerAction {
	private String message;
	private String action;
	private String server;
	private int count;

	public EmployerAction(HttpServletRequest request) {
		action = request.getParameter("action");
		server = request.getParameter("server");
		int countString = Integer.parseInt(request.getParameter("count"));
		if(countString > 0 && countString < 100){
			count = countString;
		}else{
			count = 1;
		}
		if(action.equals("createApplicant")){
			message = "Сделано! </p>";
			for(int i=0; i < count; i++){	
				message = message + new createEmployer(server).getMessage();
			}
			
		}
		
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
