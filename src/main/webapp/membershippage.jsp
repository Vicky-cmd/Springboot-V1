<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Home"/>
</c:import>
<script type="text/javascript" src="/assets/js/homepage.js"></script>
<script src="/assets/js/session.js" type="text/javascript"></script>

<br>
<%! HttpSession newSession = null; %>
<% 
	newSession = request.getSession();
	if(newSession!=null && newSession.getAttribute("username")!=null && !newSession.getAttribute("username").toString().isEmpty()) {
		out.print("<h3 style='margin: auto; width: fit-content;'>Welcome " + newSession.getAttribute("fullname") + "!</h3><br><br><br>");
	}

%>

<c:import url="/include/homeinfo.jsp"></c:import>
<br>
<c:import url="/include/subfooterHome.jsp"></c:import>
<c:import url="/include/footer.jsp" />