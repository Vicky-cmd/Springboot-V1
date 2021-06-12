<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<c:import url="/include/header.jsp" >
<c:param name="title" value="Forgot Password"/>
</c:import>
<script type="text/javascript" src="/assets/js/loginOp.js"></script>

<c:url var="reqNewPwd" value="/authenticate/forgotPassword">
</c:url>


<div class="container .bg-transparent justify-content-center">
	<div class="row">
		<div class="col-sm"></div>
			<div class="col-sm login" style="padding: 20px;">
				
				<form:form action="${reqNewPwd}" method="post" modelAttribute="usr">
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
					<div class="form-group text-center">
						<form:button type="submit" id="signup-submit" onclick="return formValidator()" class="btn btn-success">Submit</form:button>
						<form:button type="reset" class="btn ">Cancel</form:button>
					</div>
				</form:form>
			</div>
		<div class="col-sm"></div>
	</div>
</div>


<c:import url="/include/footer.jsp" />