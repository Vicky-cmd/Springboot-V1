<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<c:import url="/include/header.jsp" >
<c:param name="title" value="Reset Password"/>
</c:import>
<script type="text/javascript" src="/assets/js/loginOp.js"></script>
<script src="/assets/js/session.js" type="text/javascript"></script>

<c:url var="pwdResetUrl" value="/authenticate/updatePassword">
	<c:param name="userId" value="<%= request.getAttribute(\"userId\").toString() %>"></c:param>
	<c:param name="authToken" value="<%= request.getAttribute(\"authToken\").toString() %>"></c:param>
</c:url>

<div class="container .bg-transparent justify-content-center">
	<div class="row">
		<div class="col-sm"></div>
		<c:choose>
			<c:if test="<%= !(request.getAttribute(\"errorMsg\") != null && !request.getAttribute(\"errorMsg\").toString().isEmpty()) %>">
		
				<div class="col-sm login" style="padding: 20px;">
					<div id="confirmPwd" style="padding: 10px">
					<form:form action="${pwdResetUrl}" method="post" id="signupForm" modelAttribute="usr">
						<div class="form-group">
							<label for="password-sub" class="font-weight-bold form-label">Password</label><br>
							<!-- <input type="password" id="password-sub1" name="password1"  class="form-control" oninput="return validatePwd()" pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" required="required" placeholder="Password"> -->
							<div class="input-group">
								<form:password path="password" id="password-sub" name="password"  class="form-control" oninput="return validatePwd()" pattern="^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" required="required" placeholder="Password"/>
								<div class="input-group-append">
									<span class="input-group-text " id="pwd-text"> <span id="pwd-sub-toggle" onclick='changepwd("password-sub", "pwd-sub-toggle")' class="fa fa-fw fa-eye field-icon toggle-password"></span></span>
								</div>
							</div>
							<form:errors path="password" class="invalid"/>
						</div>
						<div class="form-group">	
							<p id="test" class="invalid-feedback is-invalid"></p>
							<p  id="pwd-spChar" class="valid">Must contain atlease 1 special character</p>
							<p  id="pwd-uppercase" class="valid">Password must contain atleast 1 upper character</p>
							<p  id="pwd-number" class="valid">Must contain atleast 1 Number</p>
							<p  id="pwd-length" class="valid">Must be atleast 8 characters long</p>
						</div>
						<div class="form-group">
							<label for="password-re-sub" class="font-weight-bold form-label">Retype Password</label><br>
							<div class="input-group">
								<input type="password" id="password-re-sub" oninput="return formValidator()" name="password-re" class="form-control" required="required" placeholder="Retype Password">
								<div class="input-group-append">
										<span class="input-group-text "> <span id="pwd-sub-toggle-re" onclick='changepwd("password-re-sub", "pwd-sub-toggle-re")' class="fa fa-fw fa-eye field-icon toggle-password"></span></span>
								</div>
							</div>
							<div id="cPwdInvalid" class="invalid-feedback">Passwords Don't Match!</div><br>
						</div>
						<div class="form-group text-center">
							<form:button type="submit" id="signup-submit" onclick="return formValidator()" class="btn btn-success">Submit</form:button>
							<form:button type="reset" class="btn ">Cancel</form:button>
						</div>
					</form:form>
					</div>
				</div>
			</c:if>
			<c:if test="<%= request.getAttribute(\"errorMsg\") != null && !request.getAttribute(\"errorMsg\").toString().isEmpty() %>">
				<h2><% out.print(request.getAttribute("errorMsg")); %></h2>
			</c:if>
		</c:choose>
		<div class="col-sm"></div>
	</div>
</div>

<c:import url="/include/footer.jsp" />