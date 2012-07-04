<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="go" method="get" enctype="text/plain" target="_self">
	<h4>Что будем делать?</h4>
	<select name="action">
	<% pageContext.include(request.getParameter("userType") + "Actions.jsp"); %>
	</select>
	<p></p>
	<h4>Сколько?</h4>
	<p></p>
	<select name="count">
	<%for(int i=1; i<11; i++) out.println("<option value='" + i + "'>" + i + "</option>");%>
	</select>
	<p></p>
	<h4>Где будем делать?</h4>
	<select name="server">
	<% pageContext.include("serverList.jsp"); %>
	</select>
	<p></p>
	<input type="hidden" name="type" value="<%=request.getParameter("userType")%>" />
	<button type="submit">Поехали!</button>
</form>