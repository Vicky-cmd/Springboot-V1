<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Registered Users"/>
</c:import>


<script src="/assets/js/session.js" type="text/javascript"></script>
<style>
th, td {
	padding: 10px;
	width: fit-content;
}

a {
	color: #4126c1;
}
</style>
<h2 align="center">Registered Users</h2>
<table border="0" align="center" class="login" style="padding: 10px; width: 50%; margin: auto;">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Fullname</th>
        <th>User Type</th>
        <th>Account Type</th>
        <th>Account Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${Users}" var="lists">
       <tr>
	       <td>${lists.id}</td>
	       <td>${lists.username}</td>
	       <td>${lists.fullname}</td>
	       <c:choose>
		       <c:when test="${lists.isAdmin == 'Y'}">
		       		<td>ADMIN</td>
		       </c:when>
		       <c:otherwise>
		       		<td>USER</td>
		       </c:otherwise>
	       </c:choose>
	       <c:choose>
		       <c:when test="${lists.isOauthAccount == 'Y'}">
		       		<td>Oauth Account</td>
		       </c:when>
		       <c:otherwise>
		       		<td>Normal Account</td>
		       </c:otherwise>
	       </c:choose>
	       <c:choose>
		       <c:when test="${lists.isAccountActive == 'Y'}">
		       		<td>Active</td>
		       </c:when>
		       <c:otherwise>
		       		<td>Locked</td>
		       </c:otherwise>
	       </c:choose>
	       <td><a align="center" href="/adminsdashboard/updateUserData?userId=${lists.id}">Update User Data</a>
	       <br><a href="#">Delete User</a></td>
	       
       </tr>
	</c:forEach>
</table>

<c:import url="/include/footer.jsp" />