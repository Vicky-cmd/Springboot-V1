<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<c:import url="/include/header.jsp" >
<c:param name="title" value="Add User"/>
</c:import>
<script src="/assets/js/session.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/js/loginOp.js"></script>


<c:url var="signUpUrl" value="/adminsdashboard/addNewUser">
</c:url>
<div class="container .bg-transparent justify-content-center">
	<div class="row">
		<div class="col-sm"></div>
		<div class="col-sm login" style="padding: 10px;">
			<div id="signUpForm">
				<div class="container">
					<div class="row">
						<div class="col font-weight-bold" align="left" style="padding: 10px">Add New User</div>
					</div>
				</div>
				<div style="margin: 10px">
					<form:form action="${signUpUrl}" method="post" id="signupForm" modelAttribute="usr">
						<form:errors path = "*" class = "errorblock" element = "div" />
						<div class="form-group">
							<label for="fullname-sub" class="font-weight-bold form-label">Fullname</label><br>
							<form:input path="fullname" type="text" id="fullname-sub" name="fullname" class="form-control" required="required" placeholder="Enter Fullname" />
							<form:errors path="fullname" class="invalid"/>
						</div>
						<div class="form-group">
							<label for="username-sub" class="font-weight-bold form-label">Username</label><br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text" id="username-text">@</span>
								</div>
								<form:input path="username" type="text" id="username-sub" name="username" aria-describedby="username-text" class="form-control" required="required" placeholder="Email ID" />
							</div>
							<form:errors path="username" class="invalid"/>
						</div>
						<div class="form-group">
							<label class="font-weight-bold" for="user-type-sub">User Type</label>
								<form:select path="isAdmin" class="form-control">
								<form:option value="Y" label="ADMIN"></form:option>
								<form:option value="N" label="USER"></form:option>
							</form:select>
						</div>
						<div class="form-group">
							<label class="font-weight-bold" id="acc-type-sub">Account Type</label>
							<form:select path="isOauthAccount" id="acc-type-sub" class="form-control" disabled="true">
								<form:option value="N" label="Normal Account"></form:option>
								<form:option value="Y" label="Oauth Account"></form:option>
							</form:select>
						</div>
						<div class="form-group">
							<label class="font-weight-bold">Account Status</label>
							<form:select path="isAccountActive" class="form-control" disabled="true">
								<form:option value="N" label="Locked"></form:option>
								<form:option value="Y" label="Active"></form:option>
							</form:select>
						</div>
						<div class="form-group">
							<div>The Account will be Activated on Setting up the Password.<br>The activation link will be sent via mail.</div>
						</div>
						<div class="form-group text-center">
							<form:button type="submit" id="signup-submit" onclick="return formValidator()" class="btn btn-success">Submit</form:button>
							<form:button type="reset" class="btn ">Cancel</form:button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm"></div>
	</div>
</div>

<b><a href="<%= request.getContextPath() %>/authenticate/logout" align="center">Logout</a></b>
<c:import url="/include/footer.jsp" />