<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Article Editor"/>
</c:import>


<script type="text/javascript" src="/assets/js/articleEditor.js"></script>
<link rel="stylesheet" href="/assets/css/articleEditor.css">
<link rel="stylesheet" href="/assets/css/articles.css">
<script src="/assets/js/session.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="/CustCMenu/style.css"> -->

<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v10.0" nonce="ltP1TjqV"></script>

<div>
<!-- checkSubmitOption -->
        <form method="post" id="articleSubForm">
		    <div style="display: table; width: 100%; padding: 10px; color: white;">
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="title">Enter The Title Of the Article:</label><br>
		                <input type="text" id="title" placeholder="Enter Your Title here..." name="title" f-Name="Title" value="" class="articleInput" style="padding: 5px; width: fit-content;" required="required">
		            </div>
		        </div>
		        <br>
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="title">Enter The Name Of the Author:</label><br>
		                <input type="text" id="noa" name="noa" value="${fullname}"  f-Name="Author Name" disabled class="articleInput" style="padding: 5px; background-color: rgba(#000000, 1); width: fit-content;" required="required">
		            </div>
		        </div>
		        <br>
		        <div style="display: table-row">
		            <div style="display: table-cell">
		                <label for="short-desc">Please Provide A Brief Description About The Article (To be Shown During Preview) :</label><br>
		                <textarea id="short-desc" name="short-desc" class="articleInput" rows="1" placeholder="A Brief Description"></textarea>
		            </div>
		        </div>
		    </div>
		    <div style="background-color: rgba(200,200,200,0.5); width: 100%; position: relative;" id="menu-bar">
				<div style="width: 100%; display: inline-block;">		    
				    <button class="context-menu__link" title="bold" data-action="txt-bold" data-target="comments" onclick="formatText(this, 'bold')" id="text-bold-main" style="padding: 10px; display: inline;"><i class="fa fa-bold"></i></button>
					<div class="vertical-seperator"></div>
			      	<button class="context-menu__link" title="italics" data-action="txt-italic" data-target="comments" onclick="formatText(this, 'italic')" id="text-italic-main" style="padding: 10px; display: inline;"><i class="fa fa-italic"></i></button>
			      	<div class="vertical-seperator"></div>
			      	<button class="context-menu__link" title="underline" data-action="txt-underline" data-target="comments" onclick="formatText(this, 'underline')" id="text-underline-main" style="padding: 10px; display: inline;"><i class="fa fa-underline"></i></button>
			      	<div id="textEditor" style="display: none;">
			      		<div class="vertical-seperator"></div>
				      	<button class="context-menu__link" title="Left Align" data-action="t-left" id="t-align-left" onclick="formatText(this, 'left')" style="padding: 10px; display: inline;"><i class="fa fa-align-left"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Center Align" data-action="t-center" id="t-align-center" onclick="formatText(this, 'center')" style="padding: 10px; display: inline;"><i class="fa fa-align-center"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Justify Content" data-action="t-justify" id="t-align-justify" onclick="formatText(this, 'justify')" style="padding: 10px; display: inline;"><i class="fa fa-align-justify"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Right Align" data-action="t-right" id="t-align-right" onclick="formatText(this, 'right')" style="padding: 10px; display: inline;"><i class="fa fa-align-right"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Create Ordered List" data-action="t-o-list" id="t-o-list" onclick="formatText(this, 'o-list')" style="padding: 10px; display: inline;"><i class="fa fa-list-ol"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Create Unordered List" data-action="t-un-list" id="t-un-list" onclick="formatText(this, 'un-list')" style="padding: 10px; display: inline;"><i class="fa fa-list-ul"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Indent Content" data-action="t-indent" id="t-indent" onclick="formatText(this, 'indent')" style="padding: 10px; display: inline;"><i class="fa fa fa-indent"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Outdent Content" data-action="t-dedent" id="t-dedent" onclick="formatText(this, 'dedent')" style="padding: 10px; display: inline;"><i class="fa fa-dedent"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Create Link" data-action="t-link" id="t-link" onclick="formatText(this, 'link')" style="padding: 10px; display: inline;"><i class="fa fa-link"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<button class="context-menu__link" title="Remove Link" data-action="t-unlink" id="t-unlink" onclick="formatText(this, 'unlink')" style="padding: 10px; display: inline;"><i class="fa fa-unlink"></i></button>
			      	  	<div class="vertical-seperator"></div>
			      	  	<select id="font-type" title="Font Type" data-action="font-type" oninput="formatText(this, 'font-type')">
			      	  		<option value="p">Paragraph</option>
			      	  		<option value="h1">Heading 1</option>
			      	  		<option value="h2">Heading 2</option>
			      	  		<option value="h3">Heading 3</option>
			      	  		<option value="h4">Heading 4</option>
			      	  		<option value="h5">Heading 5</option>
			      	  		<option value="h6">Heading 6</option>
			      	  	</select>
			      	</div>
			      	<div id="quotesEditor" style="display: none;">
			      		<button class="context-menu__link" title="Left Aligned Quote" onclick="formatQuote(this,'q-left')" id="q-align-left-main" style="padding: 10px; display: inline;"><i class="fa fa-align-left"></i></button>
			      	  	<button class="context-menu__link" title="Center Aligned Quote" onclick="formatQuote(this, 'q-center')" id="q-align-center-main" style="padding: 10px; display: inline;"><i class="fa fa-align-center"></i></button>
			      	  	<button class="context-menu__link" title="Justified Quote" onclick="formatQuote(this, 'q-justify')" id="q-align-justify-main" style="padding: 10px; display: inline;"><i class="fa fa-align-justify"></i></button>
			      	  	<button class="context-menu__link" title="Right Aligned Quote" onclick="formatQuote(this, 'q-right')" id="q-align-right-main" style="padding: 10px; display: inline;"><i class="fa fa-align-right"></i></button>
			      	  	<button class="context-menu__link" title="Highlighted Quote" id="q-highlight-main" onclick="formatQuote(this, 'q-hQuote')" style="padding: 10px; display: inline;"><i class="fa fa-header"></i>ighlight Quote</button>
			      	</div>
		      	  	<div class="vertical-seperator"></div>
		      	  	<select id="font-family" title="Font Family" data-action="font-family" oninput="formatText(this, 'font-family')">
		      	  		<option value="Arial">Arial</option>
		      	  		<option value="Brush Script MT">Brush Script MT</option>
		      	  		<option value="Copperplate">Copperplate</option>
		      	  		<option value="Courier New">Courier New</option>
		      	  		<option value="Garamond">Garamond</option>
		      	  		<option value="Georgia">Georgia</option>
		      	  		<option value="Helvetica">Helvetica</option>
		      	  		<option value="Lucida Console">Lucida Console</option>
		      	  		<option value="Lucida Handwriting">Lucida Handwriting</option>
		      	  		<option value="Monaco">Monaco</option>
		      	  		<option value="Papyrus">Papyrus</option>
		      	  		<option value="Tahoma">Tahoma</option>
		      	  		<option value="Times New Roman">Times New Roman</option>
		      	  		<option value="Trebuchet MS">Trebuchet MS</option>
		      	  		<option value="Verdana">Verdana</option>
		      	  	</select>
		      	  	<div class="vertical-seperator"></div>
		      	  	<select id="font-size" title="Font Size" data-action="font-size" oninput="formatText(this, 'font-size')">
		      	  		<c:forEach var="idx" begin="2" end="24" step="2">
		      	  			<option value="${idx}">${idx}</option>
		      	  		</c:forEach>		      	  		
		      	  		<option value="26" selected>26</option>
		      	  		<c:forEach var="idx" begin="28" end="60" step="2">
		      	  			<option value="${idx}">${idx}</option>
		      	  		</c:forEach>	
		      	  	</select>
			      	<div class="vertical-seperator"></div>
		      	  	<input type="color" id="favcolor" title="Font Color" name="t-color" data-action="t-color" id="t-color" oninput="formatText(this, 'font-color')" value="#000000">
		      	  	<div class="vertical-seperator"></div>
		      	  	<button class="context-menu__link" title="Undo" data-action="t-undo" id="t-undo" onclick="formatText(this, 'undo')" style="padding: 10px; display: inline;"><i class="fa fa-undo"></i></button>
		      	  	<div class="vertical-seperator"></div>
		      	  	<button class="context-menu__link" title="Redo" data-action="t-redo" id="t-redo" onclick="formatText(this, 'redo')" style="padding: 10px; display: inline;"><i class="fa fa-redo"></i></button>
		    	</div>
		    </div>
		    <br>
		    <br>
		    <hr class ="seperator-white">
		    <div id="container" style=" padding:10px; color: white;" onkeydown="checkCtrlKeyDown(event)" onkeyup="checkCtrlKeyUp(event)">
		    	<div contenteditable="true" id="0" class="editor editor-1" data-id="0" data-type="text" onfocusin="FocusInEvent(this)" onkeyup="checkFontData(event);" onclick="checkFontData(event);" ></div>
		    </div>
		    
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
		    <div  align="right" style="padding: 10px;">Total Word Count is: <span id="tot_word_count">0</span></div>
		    <div  align="center" style="padding: 10px;">
			    <button class="btn btn-info" type="button" formaction="/membersdashboard/previewForm" formtarget="_blank" onclick="previewForm()">Preview</button>
	            <button class="btn btn-success" style="width: fit-content; margin: auto" type="button" onclick="submitForm()">Submit</button>
            </div>
            <div  align="center" style="padding: 10px;">
            	<button class="btn btn-danger" align="center" type="button" onclick="clearAricle()">Clear Form</button>
            </div>
		    <div id="container1"></div>
		    <input type="hidden" id="submissionContent" name="submissionContent" value="">
		    <input type="hidden" id="dsub" name="dsub" value="">
		    <!-- <input type="hidden" id="paran" name="paran" value="">
		    this.form.submit()
		    <input type="hidden" id="imgn" name="imgn" value="">
		    <input type="hidden" id="youn" name="youn" value="">
		    <input type="hidden" id="quoten" name="quoten" value="">
		    <input type="hidden" id="parac" name="parac" value="">
		    <input type="hidden" id="imgc" name="imgc" value="">
		    <input type="hidden" id="youc" name="youc" value="">
		    <input type="hidden" id="quotec" name="quotec" value="">
		    <input type="hidden" id="tot" name="tot" value="">
		    <input type="hidden" id="dsub" name="dsub" value="">
		    <input type="hidden" id="test" name="test" value="https://www.youtube.com/embed/0jNvJU52LvU"> -->
        </form>
</div>
<br>


<b><a href="<%= request.getContextPath() %>/authenticate/logout" align="center">Logout</a></b>
<div id="flash-message"></div>
  <nav id="context-menu" class="context-menu">
    <ul class="context-menu__items">
      <li class="context-menu__item">
        <a href="#" class="context-menu__link" data-action="Delete"><i class="fa fa-trash"></i>Delete</a>
      </li>
      <div id="imgProp">
      	  <hr>
	      <li class="context-menu__item">
	        <a href="#" class="context-menu__link" data-action="left"><i class="fa fa-edit"></i> Align left</a>
	      </li>
      	  <hr>
	      <li class="context-menu__item">
	        <a href="#" class="context-menu__link" data-action="center"><i class="fa fa-edit"></i> Align Center</a>
	      </li>
      	  <hr>
	      <li class="context-menu__item">
	        <a href="#" class="context-menu__link" data-action="right"><i class="fa fa-edit"></i> Align Right</a>
	      </li>
      </div>
      <div id="textProp">
      	  <hr>
      	  <li class="context-menu__item" style="text-align: center;">
      	  	<p style="color: black;">Format Text</p>
	      	  	<a href="#" class="context-menu__link" data-action="t-head1" id="t-header1" style="padding: 10px; display: inline;"><i class="fa fa-header" style="font-size:x-large;"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="t-head2" id="t-header2" style="padding: 10px; display: inline;"><i class="fa fa-header" style="font-size:large;"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="t-normal" id="t-normal" style="padding: 10px; display: inline;"><i class="fa fa-font""></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="txt-bold" id="text-bold" style="padding: 10px; display: inline;"><i class="fa fa-bold"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="txt-italic" id="text-italic" style="padding: 10px; display: inline;"><i class="fa fa-italic"></i></a>
	        <!-- <a href="#" class="context-menu__link" data-action="left"><i class="fa fa-edit"></i> Align left</a> -->
	      </li>
      </div>
      <div id="quotesProp">
      	  <hr>
      	  <li class="context-menu__item" style="text-align: center;">
      	  	<p style="color: black;">Normal Quotes</p>
	      	  	<a href="#" class="context-menu__link" data-action="q-left" id="q-align-left" style="padding: 10px; display: inline;"><i class="fa fa-align-left"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="q-center" id="q-align-center" style="padding: 10px; display: inline;"><i class="fa fa-align-center"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="q-justify" id="q-align-justify" style="padding: 10px; display: inline;"><i class="fa fa-align-justify"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="q-right" id="q-align-right" style="padding: 10px; display: inline;"><i class="fa fa-align-right"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="txt-bold" id="q-bold" style="padding: 10px; display: inline;"><i class="fa fa-bold"></i></a>
	      	  	<a href="#" class="context-menu__link" data-action="txt-italic" id="q-italic" style="padding: 10px; display: inline;"><i class="fa fa-italic"></i></a>
	        <!-- <a href="#" class="context-menu__link" data-action="left"><i class="fa fa-edit"></i> Align left</a> -->
	      </li>
      	  <hr>
	      <li class="context-menu__item">
	        <a href="#" class="context-menu__link" id="q-highlight" data-action="q-hQuote"><i class="fa fa-header"></i>ighlight Quote</a>
	      </li>
      </div>
      <!-- <li class="context-menu__item">
        <a href="#" class="context-menu__link" data-action="Delete"><i class="fa fa-times"></i> Delete Task</a>
      </li> -->
    </ul>
  </nav>
  <script src="/CustCMenu/main.js"></script>
<c:import url="/include/footer.jsp" />