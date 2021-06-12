<%@page import="java.util.List"%>
<%@page import="com.infotrends.in.SpringbootV1.data.Comments"%>
<%@page import="com.infotrends.in.SpringbootV1.data.Articles"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Articles Page"/>
</c:import>
<link rel="stylesheet" href="/assets/css/userInfo.css">
<link rel="stylesheet" href="/assets/css/articles.css">
<script type="text/javascript" src="/assets/js/articles.js"></script>
<script src="/assets/js/session.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		
		sessionOnload();
		//setCommentsPropOnLoad();
		
	}
</script>


<%! HttpSession newSession = null; %>

<%!

String processSubComment(HttpServletRequest request, HttpServletResponse resp, Comments currComments) {
	
	//Articles curArticle = (Articles) request.getAttribute("article");
	
	String response = "";
	String comment_id = String.valueOf(currComments.getId());
	if(currComments.getParent()!=null) {
		response += "<div class=\"sub-comment-main\">";
	} else {
		response += "<div class=\"comment-main\">";
	}
	response += "<a href=\"/userInfo?userId="+ currComments.getUser().getId() +"\">"+ currComments.getUser().getFullname() + "</a>";
	response += "<div class=\"comment-date\">" + currComments.getComment_date() + "</div>";
	response += "<br>";
	if(currComments.getParent()!=null) {
		response += "<div class=\"sub-comment-body\">" + currComments.getContent();
	} else {
		response += "<div class=\"comment-body\">" + currComments.getContent();
	}
	response += "</div>";
	response += "<br>";
	response += "<button class=\"btn btn-link\" id=\"replyToBtn_" + comment_id + "\"  onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 1);'>Reply</button>";
	response += "<br>";
	response += "<div id=\"replyTo_" + comment_id + "\" style=\"display: none;\">";
	
	response += "<div style=\"padding: 10px; background-color: rgba(200, 200, 200, 0.2) ; width: 100%; border-radius: 10px; margin-left: 10px;\" class=\"context-menu__item\">";
	response += "<div style=\"padding: 10px\" id=\"Toolbar_comments_" + comment_id + "\"> <button class=\"context-menu__link\" title=\"bold\" data-action=\"txt-bold\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'bold')\" name=\"text-bold\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-bold\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"italics\" data-action=\"txt-italic\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'italic')\" name=\"text-italic\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-italic\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"underline\" data-action=\"txt-underline\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'underline')\" name=\"text-underline\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-underline\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Left Align\" data-action=\"t-left\" data-target=\"comments_" + comment_id + "\" name=\"t-align-left\" onclick=\"formatText(this, 'left')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-left\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Center Align\" data-action=\"t-center\" data-target=\"comments_" + comment_id + "\" name=\"t-align-center\" onclick=\"formatText(this, 'center')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-center\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Justify Content\" data-action=\"t-justify\" data-target=\"comments_" + comment_id + "\" name=\"t-align-justify\" onclick=\"formatText(this, 'justify')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-justify\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Right Align\" data-action=\"t-right\" data-target=\"comments_" + comment_id + "\" name=\"t-align-right\" onclick=\"formatText(this, 'right')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-right\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Ordered List\" data-action=\"t-o-list\" data-target=\"comments_" + comment_id + "\" name=\"t-o-list\" onclick=\"formatText(this, 'o-list')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-list-ol\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Unordered List\" data-action=\"t-un-list\" data-target=\"comments_" + comment_id + "\" name=\"t-un-list\" onclick=\"formatText(this, 'un-list')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-list-ul\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Indent Content\" data-action=\"t-indent\" data-target=\"comments_" + comment_id + "\" name=\"t-indent\" onclick=\"formatText(this, 'indent')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa fa-indent\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Outdent Content\" data-action=\"t-dedent\" data-target=\"comments_" + comment_id + "\" name=\"t-dedent\" onclick=\"formatText(this, 'dedent')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-dedent\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Link\" data-action=\"t-link\" data-target=\"comments_" + comment_id + "\" name=\"t-link\" onclick=\"formatText(this, 'link')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-link\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Remove Link\" data-action=\"t-unlink\" data-target=\"comments_" + comment_id + "\" name=\"t-unlink\" onclick=\"formatText(this, 'unlink')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-unlink\"></i></button> <div class=\"vertical-seperator\"></div> <input type=\"color\" title=\"Font Color\" name=\"t-color\" data-action=\"t-color\" data-target=\"comments_" + comment_id + "\" name=\"t-color\" oninput=\"formatText(this, 'font-color')\" value=\"#000000\"> <div class=\"vertical-seperator\"></div> <select name=\"font-type\" title=\"Font Type\" data-action=\"font-type\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-type')\"> <option value=\"p\">Paragraph</option> <option value=\"h1\">Heading 1</option> <option value=\"h2\">Heading 2</option> <option value=\"h3\">Heading 3</option> <option value=\"h4\">Heading 4</option> <option value=\"h5\">Heading 5</option> <option value=\"h6\">Heading 6</option> </select> <div class=\"vertical-seperator\"></div> <select name=\"font-family\" title=\"Font Family\" data-action=\"font-family\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-family')\"> <option value=\"Arial\">Arial</option> <option value=\"Brush Script MT\">Brush Script MT</option> <option value=\"Copperplate\">Copperplate</option> <option value=\"Courier New\">Courier New</option> <option value=\"Garamond\">Garamond</option> <option value=\"Georgia\">Georgia</option> <option value=\"Helvetica\">Helvetica</option> <option value=\"Lucida Console\">LucidnameConsole</option> <option value=\"Lucida Handwriting\">Lucida Handwriting</option> <option value=\"Monaco\">Monaco</option> <option value=\"Papyrus\">Papyrus</option> <option value=\"Tahoma\">Tahoma</option> <option value=\"Times New Roman\">Times New Roman</option> <option value=\"Trebuchet MS\">Trebuchet MS</option> <option value=\"Verdana\">Verdana</option> </select> <div class=\"vertical-seperator\"></div> <select name=\"font-size\" title=\"Font Size\" data-action=\"font-size\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-size')\">";
	for(int i=2; i<25; i=i+2) {
		response += "<option value=\"" + i + "\">" + i + "</option>";
	}
	response += " </c:forEach> <option value=\"26\" selected>26</option>";
	for(int i=28; i<=60; i=i+2) {
		response += "<option value=\"" + i + "\">" + i + "</option>";
	}
	response += " </select> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Undo\" data-target=\"comments_" + comment_id + "\" data-action=\"t-undo\" name=\"t-undo\" onclick=\"formatText(this, 'undo')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-undo\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Redo\" data-target=\"comments_" + comment_id + "\" data-action=\"t-redo\" name=\"t-redo\" onclick=\"formatText(this, 'redo')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-redo\"></i></button> </div>";
	response += "<div contenteditable=\"true\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\"  style=\"width: 100%; padding: 5px;\" toolbar_target=\"Toolbar_comments_" + comment_id + "\" onfocusin=\"FocusInEvent(this)\" onkeyup=\"checkFontData(this, event);\" onclick=\"checkFontData(this, event);\"></div>";
	response += "</div>";
	
	//response += "<textarea rows=\"1\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\" placeholder=\"Type Your Comments here...\"></textarea>";
	response += "<div style=\"padding: 10px\"><button class=\"btn btn-warning\" style=\"margin: 10px\" id=\"addComment_" + comment_id + "\" name=\"addComment_" + comment_id + "\" onclick=\"saveComments(this, '" + comment_id + "', 'comments_" + comment_id + "')\">Add Comment</button>";
	response += "<button class=\"btn btn-light\" style=\"margin: 10px\" id=\"clearComment_" + comment_id + "\" name=\"clearComment_" + comment_id + "\" onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 2);'>Cancel</button>";
	response += "</div></div>";
	response += "<br><div id=\"child-comments_" + comment_id +"\">";
	
	List<Comments> childComments = currComments.getChildsLst();
	
	if(childComments!=null && childComments.size()>0) {
		for(Comments childComm:childComments) {
			response += processSubComment(request, resp, childComm);
		}
	}
	
	response += "</div></div>";
	
	return response;
	
}

%>


<c:url var="authorId" value="/userInfo">
	<c:param name="userId" value="${article.authorInfo.id}"></c:param>
</c:url>

<br>
<br>
<div class="articlesPage">
	<div class="article-title">
		<h1>${article.articleName}</h1>
	</div>
	<div class="">By <a href="${authorId}">${article.authorInfo.fullname}</a></div>
	<br>
	<div>
		${article.content}
	</div>
	<br>
	<div>Submitted On ${article.submissionTime}</div>
	<br>
	<br>
	<div>
		<h4>Add Comment:</h4>
		<div style="padding: 10px; background-color: rgba(200, 200, 200, 0.2) ; width: 100%; border-radius: 10px; margin-left: 10px;" class="context-menu__item"  onkeydown="checkCtrlKeyDown(event)" onkeyup="checkCtrlKeyUp(event)">
			<div style="padding: 10px" id="Toolbar_comments">
				<button class="context-menu__link" title="bold" data-action="txt-bold" data-target="comments" onclick="formatText(this, 'bold')" name="text-bold" style="padding: 10px; display: inline;"><i class="fa fa-bold"></i></button>
				<div class="vertical-seperator"></div>
		      	<button class="context-menu__link" title="italics" data-action="txt-italic" data-target="comments" onclick="formatText(this, 'italic')" name="text-italic" style="padding: 10px; display: inline;"><i class="fa fa-italic"></i></button>
		      	<div class="vertical-seperator"></div>
		      	<button class="context-menu__link" title="underline" data-action="txt-underline" data-target="comments" onclick="formatText(this, 'underline')" name="text-underline" style="padding: 10px; display: inline;"><i class="fa fa-underline"></i></button>
		      	<div class="vertical-seperator"></div>
		      	<button class="context-menu__link" title="Left Align" data-action="t-left" data-target="comments" name="t-align-left" onclick="formatText(this, 'left')" style="padding: 10px; display: inline;"><i class="fa fa-align-left"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Center Align" data-action="t-center" data-target="comments" name="t-align-center" onclick="formatText(this, 'center')" style="padding: 10px; display: inline;"><i class="fa fa-align-center"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Justify Content" data-action="t-justify" data-target="comments" name="t-align-justify" onclick="formatText(this, 'justify')" style="padding: 10px; display: inline;"><i class="fa fa-align-justify"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Right Align" data-action="t-right" data-target="comments" name="t-align-right" onclick="formatText(this, 'right')" style="padding: 10px; display: inline;"><i class="fa fa-align-right"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Create Ordered List" data-action="t-o-list" data-target="comments" name="t-o-list" onclick="formatText(this, 'o-list')" style="padding: 10px; display: inline;"><i class="fa fa-list-ol"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Create Unordered List" data-action="t-un-list" data-target="comments" name="t-un-list" onclick="formatText(this, 'un-list')" style="padding: 10px; display: inline;"><i class="fa fa-list-ul"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Indent Content" data-action="t-indent" data-target="comments" name="t-indent" onclick="formatText(this, 'indent')" style="padding: 10px; display: inline;"><i class="fa fa fa-indent"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Outdent Content" data-action="t-dedent" data-target="comments" name="t-dedent" onclick="formatText(this, 'dedent')" style="padding: 10px; display: inline;"><i class="fa fa-dedent"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Create Link" data-action="t-link" data-target="comments" name="t-link" onclick="formatText(this, 'link')" style="padding: 10px; display: inline;"><i class="fa fa-link"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Remove Link" data-action="t-unlink" data-target="comments" name="t-unlink" onclick="formatText(this, 'unlink')" style="padding: 10px; display: inline;"><i class="fa fa-unlink"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<input type="color" title="Font Color" name="t-color" data-action="t-color" data-target="comments" name="t-color" oninput="formatText(this, 'font-color')" value="#000000">
	      	  	<div class="vertical-seperator"></div>
	      	  	<select name="font-type" title="Font Type" data-action="font-type" data-target="comments" oninput="formatText(this, 'font-type')">
	      	  		<option value="p">Paragraph</option>
	      	  		<option value="h1">Heading 1</option>
	      	  		<option value="h2">Heading 2</option>
	      	  		<option value="h3">Heading 3</option>
	      	  		<option value="h4">Heading 4</option>
	      	  		<option value="h5">Heading 5</option>
	      	  		<option value="h6">Heading 6</option>
	      	  	</select>
	      	  	<div class="vertical-seperator"></div>
	      	  	<select name="font-family" title="Font Family" data-action="font-family" data-target="comments" oninput="formatText(this, 'font-family')">
	      	  		<option value="Arial">Arial</option>
	      	  		<option value="Brush Script MT">Brush Script MT</option>
	      	  		<option value="Copperplate">Copperplate</option>
	      	  		<option value="Courier New">Courier New</option>
	      	  		<option value="Garamond">Garamond</option>
	      	  		<option value="Georgia">Georgia</option>
	      	  		<option value="Helvetica">Helvetica</option>
	      	  		<option value="Lucida Console">LucidnameConsole</option>
	      	  		<option value="Lucida Handwriting">Lucida Handwriting</option>
	      	  		<option value="Monaco">Monaco</option>
	      	  		<option value="Papyrus">Papyrus</option>
	      	  		<option value="Tahoma">Tahoma</option>
	      	  		<option value="Times New Roman">Times New Roman</option>
	      	  		<option value="Trebuchet MS">Trebuchet MS</option>
	      	  		<option value="Verdana">Verdana</option>
	      	  	</select>
	      	  	<div class="vertical-seperator"></div>
	      	  	<select name="font-size" title="Font Size" data-action="font-size" data-target="comments" oninput="formatText(this, 'font-size')">
	      	  		<c:forEach var="idx" begin="2" end="24" step="2">
	      	  			<option value="${idx}">${idx}</option>
	      	  		</c:forEach>		      	  		
	      	  		<option value="26" selected>26</option>
	      	  		<c:forEach var="idx" begin="28" end="60" step="2">
	      	  			<option value="${idx}">${idx}</option>
	      	  		</c:forEach>	
	      	  	</select>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Undo" data-target="comments" data-action="t-undo" name="t-undo" onclick="formatText(this, 'undo')" style="padding: 10px; display: inline;"><i class="fa fa-undo"></i></button>
	      	  	<div class="vertical-seperator"></div>
	      	  	<button class="context-menu__link" title="Redo" data-target="comments" data-action="t-redo" name="t-redo" onclick="formatText(this, 'redo')" style="padding: 10px; display: inline;"><i class="fa fa-redo"></i></button>
		    </div>
			<div class="commentsBar" style="width: 100%; padding: 5px;" contenteditable="true" name="comments" id="comments" toolbar_target='Toolbar_comments' onfocusin="FocusInEvent(this)" onkeyup="checkFontData(this, event);" onclick="checkFontData(this, event);"></div>
		</div>
		<!-- <textarea class="commentsBar" placeholder="Type Your Comments here..." style="width: 100%;" rows="1" id="comments" name="comments"></textarea> -->
		<div style="padding-top: 10px">
			<button class="btn btn-warning" style="margin: 10px" id="addComment" name="addComment" onclick="saveComments(this, null, null)">Add Comment</button>
			<button class="btn btn-light" style="margin: 10px" id="clearComment" name="clearComment">Clear</button>
		</div>
	</div>
</div>
<br>
<br>

<div id="commentsSection" class="commentsSection">
	<div><h4>Comments:</h4></div><br>
	<div id="mainCommentsLst"  onkeydown="checkCtrlKeyDown(event)" onkeyup="checkCtrlKeyUp(event)">
	<%
				Articles currArticle = (Articles) request.getAttribute("article");
				List<Comments> commLst = currArticle.getTagged_comments();
				String output = "";
				for(Comments currComment: commLst) {
					if(!(currComment!=null && currComment.getParent()!=null && currComment.getParent().getId()>-1)) {
						output += processSubComment(request, response, currComment);

						
						
						/* String comment_id = String.valueOf(currComment.getId());
						output += "<div class=\"comment-main\">";
						output += "<a href=\"/userInfo?userId="+ currComment.getUser().getId() +"\">"+ currComment.getUser().getFullname() + "</a>";
						output += "<div class=\"comment-date\">" + currComment.getComment_date() + "</div>";
						output += "<br>";
						output += "<div class=\"comment-body\">" + currComment.getContent();
							//${mainComment.content}
						output += "</div>";
						output += "<br>";
						output += "<button class=\"btn btn-link\" id=\"replyToBtn_" + comment_id + "\"  onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 1);'>Reply</button>";
						output += "<br>";
						output += "<div id=\"replyTo_" + comment_id + "\" style=\"display: none;\">";
						output += "<textarea rows=\"1\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\" placeholder=\"Type Your Comments here...\"></textarea>";
						output += "<div style=\"padding: 10px\"><button class=\"btn btn-warning\" style=\"margin: 10px\" id=\"addComment_" + comment_id + "\" name=\"addComment_" + comment_id + "\" onclick=\"saveComments(this, '" + comment_id + "', 'comments_" + comment_id + "')\">Add Comment</button>";
						output += "<button class=\"btn btn-light\" style=\"margin: 10px\" id=\"clearComment_" + comment_id + "\" name=\"clearComment_" + comment_id + "\" onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 2);'>Cancel</button>";
						output += "</div></div>";
						output += "<br><div id=\"child-comments_" + comment_id +"\">";
						
						List<Comments> childComments = currComment.getChildsLst();
						
						if(childComments!=null && childComments.size()>0) {
							for(Comments childComm:childComments) {
								output += processSubComment(request, response, childComm);
							}
						}
						
						output += "</div></div>"; */
						
						
						
						
						output += "<br><hr class=\"seperator-white\" style=\"width: 85%;\"><br>";
					}
				}
				out.println(output);
		%>
		</div>
</div>

<br>
<br>

<div class="link">
	<div class="link__items">
		<input type="url" >
	</div>
</div>

<input type="hidden" id="article_id" value="${article.article_id}">
<c:import url="/include/footer.jsp" />