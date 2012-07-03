package ru.hh.etest.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.hh.etest.actions.ApplicantAction;
import ru.hh.etest.actions.BackofficeAction;
import ru.hh.etest.actions.EmployerAction;
public class Go extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String type;
	private String action;
	private String server;
	private String message;
       
	
    public Go() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		type = request.getParameter("type");
		if(type.equals("applicant")){
			message = new ApplicantAction(request).getMessage();
		}
		if(type.equals("employer")){
			message = new EmployerAction(request).getMessage();
		}
		if(type.equals("backoffice")){
			message = new BackofficeAction(request).getMessage();
		}
		RequestDispatcher finalPage = request.getRequestDispatcher("final.jsp?message=" + message);
		finalPage.forward(request, response);
	}


}
