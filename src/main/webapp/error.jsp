<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Forbidden"/>
</c:import>


<%! HttpSession newSession = null; %>
<% 
	newSession = request.getSession();
	if(newSession!=null && newSession.getAttribute("username")!=null && !newSession.getAttribute("username").toString().isEmpty()) {
		out.print("<h3>Hi " + newSession.getAttribute("fullname") + "!</h3><br><br><br>");
	}
	
%>
<div style="margin: auto; width: fit-content;">
	<div style=" margin: auto; width: fit-content;"><span style="font-size:100px;">&#128577;</span></div>
	<h4 style="font-style: italic; font-size: xx-large; font-weight: bolder; font-family: monospace; color: #ffffff;">Oops! Something Happened! :(</h4>
</div>

<c:import url="/include/footer.jsp" />