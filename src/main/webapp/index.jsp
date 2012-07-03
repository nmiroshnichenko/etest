<%@page import="java.util.Enumeration"%>
<%@ page import="java.io.IOException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Let's do it, baby!</title>
</head>
<body>

<%!String userType;%>
<%!String isEmployer = "";%>
<%!String isApplicant = "";%>
<%!String isBackoffice = "";%>
<%String userType = request.getParameter("type");
if(userType!=null){
	if (userType.equals("employer")) {
		isEmployer = "checked";
	} else if (userType.equals("applicant")) {
		isApplicant = "checked";
	} else if (userType.equals("backoffice")) {
		isBackoffice = "checked";
	}
}
%>

<form action="index.jsp" method="get" enctype="text/plain" target="_self">
	<h4>Тип пользователя:</h4>
	<label> 
		<input type="radio" name="type" value="employer" <%=isEmployer%> /> 
		Работодатель
	</label>
	<p></p>
	<label>
		<input type="radio" name="type" value="applicant" <%=isApplicant%> /> 
		Соискатель
	</label>
	<p></p>
	<label> 
		<input type="radio" name="type" value="backoffice" <%=isBackoffice%> /> 
		Бэкофис
	</label>
	<p></p>
	<button type="submit">Показать</button>
</form>

<% if(userType!=null){ %>
	<jsp:include page="actionsForm.jsp" flush="true">
		<jsp:param name="userType" value="<%=userType%>" />
	</jsp:include>
<%}%>

</body>
</html>