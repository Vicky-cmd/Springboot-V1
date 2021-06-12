<%@page import="com.infotrends.in.SpringbootV1.data.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Admin Home"/>
</c:import>

<script src="/assets/js/session.js" type="text/javascript"></script>

<style>

.carouselCustomcss {

width: 50%;
margin: auto;
padding: 20px;
align-content: center;
align-items: center;
}

</style>

<%

	String action = "";

	if(request.getAttribute("action")!=null) {
		action = request.getAttribute("action").toString();
	}
	String paginationUrl = "";
	if(action!=null) {
		List<Users> usersLst = (List<Users>) request.getAttribute("usersResultsLst");
		if(action.equalsIgnoreCase("SearchResults")) {
			
			out.print("<h1 class=\"carouselCustomcss\">Users</h1>");
			if(usersLst!=null && usersLst.size()>0) {
				out.print("<div class=\"carouselCustomcss login\"><ul><div class=\"container\"><div class=\"row\">");
				out.println("<div class=\"col-sm\">");
				for(int i=0; i<(usersLst.size()/2)+1; i++) {
			    	out.print("<li>");
			    	out.print("<a href=\"/userInfo?userId=" + usersLst.get(i).getId() + "\">" + usersLst.get(i).getFullname() + "</a><br>");
			    	out.print("<p>" + usersLst.get(i).getUsername() + "</p>");
			    	int taggedCommCount = 0;
				    if(usersLst.get(i).getCommentsHistory()!=null) {
				    	taggedCommCount = usersLst.get(i).getCommentsHistory().size();
				    }
				    out.print("<i class=\"fas fa-comment-alt\" ></i><span>  "+ taggedCommCount +"</span>");
			    	out.print("</li>");
				}
				out.println("</div>");
				out.println("<div class=\"col-sm\">");
				for(int i=(usersLst.size()/2)+1; i<usersLst.size(); i++) {
			    	out.print("<li><a href=\"/userInfo?userId=" + usersLst.get(i).getId() + "\">" + usersLst.get(i).getFullname() + "</a></li>");
				}
				out.println("</div>");
			    out.print("</div></div></ul></div>");
			}
			out.print("<br>");
			out.print("<hr>");
			out.print("<br>");
			out.print("<h1 class=\"carouselCustomcss\">Articles</h1>");
			
		}
	}

%>


<c:import url="/include/subfooterHome.jsp"></c:import>
<c:import url="/include/footer.jsp" />