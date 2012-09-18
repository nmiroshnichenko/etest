package ru.hh.etest.actions;

import javax.servlet.http.HttpServletRequest;

public class ApplicantAction {
	private String message;
	private String action;
	private String server;
	private int count;

	public ApplicantAction(HttpServletRequest request) {
        message = "";
		action = request.getParameter("action");
		server = request.getParameter("server");
		count = Integer.parseInt(request.getParameter("count"));
		doApplicantAction();
	}

	public String getMessage() {
		return this.message;
	}

	private void doApplicantAction() {
		String[] taskArgs = {server};

		if(count < 1 || count > 100){
			count = 1;
		}
		// execute task and get result text
		Invoker invoker = new Invoker();
		this.message = "<p>Результат:</p>\n";
		this.message = this.message
			+ "| # | Роль | Логин | Пароль |<br />\n";
		this.message = (this.message 
			+ invoker.getResultText(action, taskArgs, count));
	}
}
