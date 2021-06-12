<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<c:import url="/include/header.jsp" >
<c:param name="title" value="Admins Home"/>
</c:import>

<form:form action="/adminsdashboard/addUser" method="post" modelAttribute="usr">
	<div class="login" style="width: fit-content; padding: 10px; margin: auto;">
		<table border="0" align="center">
		<col width="40%">
		<col width="60%">
			<tr>
				<td><label class="font-weight-bold" for="id">User Id</label></td>
				<td>
					<div class="form-group">
						<form:input path="id" type="number" name="id" class="form-control"  placeholder="ID" min="1" disabled="true"/>
					</div>		
				</td>
			</tr>
			<tr>
				<td><label class="font-weight-bold">Username</label></td>
				<td>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="username-text-login">@</span>
						</div>
						<form:input path="username" type="email" id="username" name="username" class="form-control" disabled="true" required="required" placeholder="Email ID" />
					</div>
					<form:errors path="username" class="invalid"/>
				</div>
				
				</td>
			</tr>
			<tr>
				<td><label class="font-weight-bold" for="fullname">Fullname</label></td>
				<td>
					<div class="form-group">
						<form:input path="fullname" type="text" id="fullname" name="fullname" placeholder="Fullname" class="form-control" disabled="true"/>
					</div>
				</td>
			</tr>
			<tr>
				<td><label class="font-weight-bold">User Type</label></td>
				<td>
					<div class="form-group">
						<form:select path="isAdmin" class="form-control">
							<form:option value="Y" label="ADMIN"></form:option>
							<form:option value="N" label="USER"></form:option>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td><label class="font-weight-bold">Account Type</label></td>
				<td>
					<div class="form-group">
						<form:select path="isOauthAccount" class="form-control" disabled="true">
							<form:option value="N" label="Normal Account"></form:option>
							<form:option value="Y" label="Oauth Account"></form:option>
						</form:select>
					</div>	
				</td>
			</tr>
			<tr>
				<td><label class="font-weight-bold">Account Status</label></td>
				<td>
					<div class="form-group">
						<form:select path="isAccountActive" class="form-control" disabled="false">
							<form:option value="Y" label="Active"></form:option>
							<form:option value="N" label="Locked"></form:option>
						</form:select>
					</div>		
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="form-group" style="width: fit-content; margin: auto; padding: 10px;">
						<button class="btn btn-success" type="submit">Submit</button>
						<button class="btn btn-danger" type="reset" onclick="location.href='/adminsdashboard/displayUsers'">Cancel</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
</form:form>

<c:import url="/include/footer.jsp" />