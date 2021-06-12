<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="UserInfo"/>
</c:import>
<link rel="stylesheet" href="/assets/css/userInfo.css">
<script src="/assets/js/session.js" type="text/javascript"></script>

<%! HttpSession newSession = null; %>

<c:set var="user" value="${users}" scope="page"></c:set>

<div class="userInfoMain">
	<c:choose>
		<c:when test=" ${userPic!=null} ">
			
		</c:when>
		<c:when test="${usrInitials!=null}">
			<div class="defuserPic">
				<div>${usrInitials}</div>
			</div>
		</c:when>
	</c:choose>
	<br>
	<div><h2><b>${users.fullname}</b></h2></div>
	

	<br>
	<hr class="seperator-white">
	<br>
	<% String a_id = "article_"; int count = 0; %>
	<div class="align-items-center">
		<div><h3 style="font-family: fantasy; font-weight: bold; font-stretch: ultra-expanded;">Contributions:</h3></div>
		<c:forEach var="article" items="${user.subArticles}">
		
			<button class="btn btn-outline-info w-100" type="button" data-toggle="collapse" data-target="#<%= a_id+count%>" aria-expanded="false" aria-controls="#<%= a_id+count%>"> <h4 style="font-weight: bolder; font-family: fantasy; font-stretch: ultra-expanded; align-self: center;">${article.articleName}</h4> </button>
			<div class="row">
			  <div class="col">
			    <div class="collapse multi-collapse" style="color: black;" id="<%= a_id+count%>">
			      <div class="card card-body" style=" border-radius: 0 0 20px 20px;">
			        <div>
			        	<h6>${article.submissionTime}</h6>
			        </div>
			        <div>
				        ${article.content}
				    </div>
				    <div>
				    	<a href="/membersdashboard/displayArticle?articleId=${article.article_id}">Click here</a> to go to the main Article.
				    </div>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<% count++; %>
		</c:forEach>
	</div>
</div>
<b><a href="<%= request.getContextPath() %>/authenticate/logout" align="center">Logout</a></b>
<c:import url="/include/footer.jsp" />