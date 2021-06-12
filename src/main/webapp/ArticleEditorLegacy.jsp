<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Article Editor"/>
</c:import>
<script type="text/javascript" src="/assets/js/articleEditorLegacy.js"></script>
<link rel="stylesheet" href="/assets/css/articleEditor.css">
<script src="/assets/js/session.js" type="text/javascript"></script>

<div>
<!-- checkSubmitOption -->
        <form method="post" id="articleSubForm">
		    <div style="display: table; width: 100%; padding: 10px; color: white;">
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="title">Enter The Title Of the Article:</label><br>
		                <input type="text" id="title" placeholder="Enter Your Title here..." name="title" value="" class="articleInput" style="padding: 5px; width: fit-content;">
		            </div>
		        </div>
		        <br>
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="title">Enter The Name Of the Author:</label><br>
		                <input type="text" id="noa" name="noa" value="${fullname}" disabled class="articleInput" style="padding: 5px; background-color: rgba(#000000, 1); width: fit-content;">
		            </div>
		        </div>
		        <br>
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="short-desc">Please Provide A Brief Description About The Article (To be Shown During Preview.):</label><br>
		                <textarea id="short-desc" name="short-desc" class="articleInput" rows="1" placeholder="A Brief Description"></textarea>
		            </div>
		        </div>
		    </div>
		    <div id="container" style=" padding:10px; color: white;"></div>
		    
		    <br><br>
		    <div style="width: 80%; margin: auto;">
		        <div class="d-flex align-items-center">
		            <div style="width: 50%; padding:10px;">
		                <button class="btn btn-dark" type="button" name="add" id="add" style="width: 100%; margin: auto" onclick="addFields()">Add a New para</button>
		            </div>
		            <div style="width: 50%; padding:10px;">
		                <button class="btn btn-dark" type="button" name="addimg" id="addimg" style="width: 100%; margin: auto" onclick="addimgFields()">Add a Image</button>
		            </div>
		        </div>
		        <div class="d-flex align-items-center">
		            <div style="width: 50%; padding:10px;">
		                <button class="btn btn-dark" type="button" name="addyvid" id="addyvid" style="width: 100%; margin: auto" onclick="addyvidFields()">Add a New Youtube Video</button>
		            </div>
		            <div style="width: 50%; padding:10px;">
		                <button class="btn btn-dark" type="button" name="addref" id="addref" style="width: 100%; margin: auto" onclick="addqFields()">Add a Quotes section</button>
		            </div>            
		        </div>
		    </div>
		    <div  align="center" style="padding: 10px;">
			    <button class="btn btn-info" type="button" formaction="/membersdashboard/v1/previewForm" formtarget="_blank" onclick="previewForm()">Preview</button>
	            <button class="btn btn-success" style="width: fit-content; margin: auto" type="submit" formaction="/membersdashboard/v1/submitForm" formtarget="_self" onclick="setsubdate()">Submit</button>
            </div>
            <div  align="center" style="padding: 10px;">
            	<button class="btn btn-danger" align="center" type="button" onclick="clearAricle()">Clear Form</button>
            </div>
		    <div id="container1"></div>
		    <input type="hidden" id="paran" name="paran" value="">
		    <input type="hidden" id="imgn" name="imgn" value="">
		    <input type="hidden" id="youn" name="youn" value="">
		    <input type="hidden" id="quoten" name="quoten" value="">
		    <input type="hidden" id="parac" name="parac" value="">
		    <input type="hidden" id="imgc" name="imgc" value="">
		    <input type="hidden" id="youc" name="youc" value="">
		    <input type="hidden" id="quotec" name="quotec" value="">
		    <input type="hidden" id="tot" name="tot" value="">
		    <input type="hidden" id="dsub" name="dsub" value="">
		    <input type="hidden" id="test" name="test" value="https://www.youtube.com/embed/0jNvJU52LvU">
        </form>
</div>
<br>
<b><a href="<%= request.getContextPath() %>/authenticate/logout" align="center">Logout</a></b>
<c:import url="/include/footer.jsp" />