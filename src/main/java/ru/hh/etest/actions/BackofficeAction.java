package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

public class BackofficeAction {
	private String message;
	private String action;
	private String server;

	public BackofficeAction(HttpServletRequest request) {
		action = request.getParameter("action");
		server = request.getParameter("server");
		
	}

	public String getMessage() {
		return message;
	}

}
