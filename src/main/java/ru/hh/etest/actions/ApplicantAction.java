package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

import ru.hh.etest.applicant.CreateApplicant;

public class ApplicantAction {
	private String message;
	private String action;
	private String server;
	private int count;

	public ApplicantAction(HttpServletRequest request) {
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
				message = message + new CreateApplicant(server).getMessage();
			}
			
		}
		
	}
	public String getMessage() {
		return message;
	}
}
