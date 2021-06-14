<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<c:import url="/include/header.jsp" >
<c:param name="title" value="Login Page"/>
</c:import>
<script type="text/javascript" src="/assets/js/loginOp.js"></script>

<h1>Login Screen</h1>


<c:url var="showSubmitPageUrl" value="/authenticate">
	<c:param name="register_user" value="true"></c:param>
	<c:param name="redirect" value="${param.redirect}"></c:param>
</c:url>

<c:url var="showLoginPageUrl" value="/authenticate">
	<c:param name="register_user" value="false"></c:param>
	<c:param name="redirect" value="${param.redirect}"></c:param>
</c:url>

<c:url var="loginUrl" value="/authenticate/login">
	<c:param name="redirect">${param.redirect}</c:param>
</c:url>

<c:url var="signUpUrl" value="/authenticate/signup">
	<c:param name="redirect">${param.redirect}</c:param>
</c:url>


<div class="container .bg-transparent justify-content-center">
	<div class="row">
		<div class="col-sm"></div>
		<div class="col-sm login" style="padding: 10px;">
			<div id="loginForm">
				<div class="container">
					<div class="row">
						<div class="col" align="left" style="padding: 10px">Sign In</div>
					</div>
				</div>
				<div style="margin: 10px">
					<form:form action="${loginUrl}" method="post" modelAttribute="usr">
						<form:errors path = "*" class = "errorblock" element = "div" />
						<form:input path="fullname" type="hidden" name="fullname" id="fullname" value="" />
						<div class="form-group">
							<label class="font-weight-bold">Username</label><br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text" id="username-text-login">@</span>
								</div>
								<form:input path="username" type="email" id="username" name="username" class="form-control" required="required" placeholder="Email ID" />
							</div>
							<form:errors path="username" class="invalid"/>
						</div>
						<div class="form-group">
							<label class="font-weight-bold">Password</label><br>
							<div class="input-group">
								<form:input path="password" type="password" id="password" name="password" class="form-control" required="required" placeholder="Password" />
								<div class="input-group-append">
										<span class="input-group-text " id="pwd-text-login"> <span id="pwd-log-toggle" onclick='changepwd("password", "pwd-log-toggle")' class="fa fa-fw fa-eye field-icon toggle-password"></span></span>
								</div>
							</div>
							<form:errors path="password" class="invalid"/>
						</div>
						<div class="form-group text-center">
							<button type="submit" class="btn btn-success">Submit</button>
							<button type="reset" class="btn ">Cancel</button>
						</div>
						<div class="form-group text-center">
							<p>Don't have an Account? <a href="${showSubmitPageUrl}">Sign Up</a>.</p>
						</div>
						<div class="form-group text-center">
							<p><a href="/authenticate/forgotPassword">Forgot Password?</a></p>
						</div>
					</form:form>
				</div>
			</div>
			<div id="signUpForm" style="display: none;">
				<div class="container">
					<div class="row">
						<div class="col font-weight-bold" align="left" style="padding: 10px">Sign Up</div>
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
						<div class="form-group text-center">
							<p>Already have an Account? <a href="${ showLoginPageUrl }">Sign In</a>.</p>
						</div>
					</form:form>
				</div>
			</div>
			<div id="other_sign_in_methods" class="text-center">
				<table style="width: 100%">
					<tr>
						<td style="width: 45%"><hr style="background-color: black;"></td>
						<td style="width: 10%; vertical-align: middle; text-align: center">OR</td>
						<td style="width: 45%"><hr style="background-color: black;"></td>
					</tr>
				</table>
				<h5 style="font-family: sans-serif; font-weight: bolder;">Continue with</h5>
				<button class="btn btn-danger" onclick="location.href='https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=https://infotrends-media.herokuapp.com/authenticate/google/requestToken/processResponse&response_type=code&client_id=293427061737-v7v5b0i75i8cnb9bc092354auth3qgmg.apps.googleusercontent.com&approval_prompt=force';" style="vertical-align: middle; text-align: center"><i class="fa fa-google" style="font-size:24px"></i>  <b style="font-size:18px">Google</b></button>
			</div>
			
		</div>
		<div class="col-sm"></div>
	</div>
</div>

<c:import url="/include/footer.jsp" />