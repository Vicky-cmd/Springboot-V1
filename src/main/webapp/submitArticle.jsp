<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Login Page"/>
</c:import>

<script src="/assets/js/session.js" type="text/javascript"></script>


<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v10.0" nonce="yhmicgTq"></script>
<link rel="stylesheet" href="/assets/css/articleEditor.css">
<script>
function callParent() {
	window.close();
	window.opener.submitForm();
}
</script>

${article}
<p align="center"><button type='submit' width="50%"><font size="+2" onclick="callParent()"><b>Do You want to Submit the article?</b></font></button></p>

