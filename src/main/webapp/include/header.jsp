<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>${param.title}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, height=device-height">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="icon" type="logo.jpg" href="/assets/images/logo.jpg">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/3636773bbd.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="/assets/css/index.css">
	
</head>
<body>
	<%
		HttpSession newSession = request.getSession();
		Boolean checkLogin = newSession!=null && newSession.getAttribute("username")!=null && !newSession.getAttribute("username").toString().isEmpty(); 
	%>
	<div class="jumbotron jumbotron-fluid login" style="padding: 5px">
	
		<div class="d-flex justify-content-between">
			<div class="" align="center" style="padding: 10px; font-family: fantasy;">
				<a href="/" style="text-decoration: none; color: white"><h1>InfoTrends.in</h1></a>
			</div>
			<div class="" align="center" style="padding: 10px;">
				
				<%
					if(checkLogin){
						out.print("<a href=\"/authenticate/logout\" class=\"linkbutton\" style=\"right: 0; position: absolute;\">LOGOUT</a>");
					} else {
						out.print("<a href=\"/authenticate\" class=\"linkbutton\" style=\"right: 0; position: absolute;\">LOGIN</a>");				
					}
				%>
				
			</div>
		</div>

	</div>
	
	<nav class="navbar navbar-expand-md navbar-transparent navbar-topcss">
        <a href="/" class="navbar-brand"><img src="/assets/images/logo.jpg" style="height: 40px; width: 40px;"></a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"><i style="font-size:24px" class="fa">&#xf0c9;</i></span>
        </button>

        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
            <div class="navbar-nav">
                <a href="/" class="nav-item nav-link active">Home</a>
                <c:if test="<%= request.getSession().getAttribute(\"userId\")!=null %>">
                	<a href="/userInfo?userId=<%=request.getSession().getAttribute("userId")%>" class="nav-item nav-link">Profile</a>
                </c:if>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Articles</a>
                    <div class="dropdown-menu">
                        <a href="/membersdashboard/createNewArticle" class="dropdown-item">Create Article</a>
                        <a href="/about-me" class="dropdown-item">Creator's Info</a>
                    </div>
                </div>
                <c:if test="<%= request.getSession().getAttribute(\"isAdmin\")!=null && request.getSession().getAttribute(\"isAdmin\").toString().equalsIgnoreCase(\"Y\") %>">
	                <a href="/membersdashboard" class="nav-item nav-link">Members Home</a>
	                <div class="nav-item dropdown">
	                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Manage Users</a>
	                    <div class="dropdown-menu">
	                        <a href="/adminsdashboard/addNewUser" class="dropdown-item">Add New User</a>
	                        <a href="/adminsdashboard/displayUsers" class="dropdown-item">Display Users</a>
	                    </div>
	                </div>
                </c:if>
	            <a href="/retailApp" style="whiteSpace: nowrap" class="nav-item nav-link">
	                <i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;
	                Shopping App
	            </a>    
            </div>
            <c:url var="searchUrl" value="/search">
            	<c:param name="pgNo">1</c:param>
            </c:url>
            <form class="form-inline" action="${searchUrl}">
                <div class="input-group">                    
                    <input type="text" class="form-control search-bar" name="query" placeholder="Search">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-warning"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
            
        </div>
   	</nav>	
	
