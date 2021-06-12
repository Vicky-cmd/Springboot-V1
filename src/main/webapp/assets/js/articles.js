var i = 0, j = 0, k = 0, l = 0, num = 0, tot = 1;
var selectedItemId;

function saveComments(commObj, parent, commIds) {

	console.log("Inside SaveComments");
	var articleId = document.getElementById("article_id").value;
	var commmmentBoxId = "comments";
	if(commIds !== null && commIds !== '') {
		commmmentBoxId = commIds;
	}
	var comment_content = document.getElementById(commmmentBoxId).innerHTML;
	if(comment_content !== null && comment_content !== '') {
		var xhttp = new XMLHttpRequest();
		var jsonReq = {};
		jsonReq.article_id = articleId;
		jsonReq.content = comment_content;
		if(parent !== null && parent !== '') {
			jsonReq.parentCommentId = parent;
		}
		console.log(jsonReq);
		xhttp.open("POST", "/saveComments");
		xhttp.send(JSON.stringify(jsonReq));
		console.log("Sent Request!");
		xhttp.addEventListener("load", function (event) {
	    	console.log(this);
	    	console.log(this.responseText);
	    	var jsonResp = JSON.parse(this.responseText);
	    	if(jsonResp.status !== null && jsonResp.status === '200') {
	    		console.log("Received Success Response");
		    	var isChildComment = "N";
		    	var displayDIvId = "mainCommentsLst";
		    	console.log(jsonResp.status);
		    	console.log(jsonResp.hasOwnProperty('parentId'));
		    	var output = "";
		    	if(jsonResp.hasOwnProperty('parentId') && jsonResp.parentId !== '') {
		    		console.log("Found Parent Id");
					isChildComment = "Y";
					displayDIvId = "child-comments_" + jsonResp.parentId;
					commentsDisplayCtrl("replyTo_" + jsonResp.parentId, "replyToBtn_" + jsonResp.parentId, 2);
				} else {
					output += "<br><hr class=\"seperator-white\" style=\"width: 85%;\"><br>";
				}
				console.log("Comments will be displayed inside " + displayDIvId);
				output = addComments(jsonResp.userId, jsonResp.fullname, jsonResp.commentsId, new Date(), jsonResp.content, isChildComment) + output;
				console.log(output);
				var displayCommentsDiv = document.getElementById(displayDIvId);
				displayCommentsDiv.innerHTML = displayCommentsDiv.innerHTML +  output;
				document.getElementById(commmmentBoxId).innerHTML = "";
				document.getElementById(commmmentBoxId).rows = 1;
				if(!jsonResp.hasOwnProperty('parentId')) {
					window.scrollTo(0, document.body.scrollHeight);
				}
				var newtxtArea = document.getElementById("comments_" + jsonResp.commentsId);
				newtxtArea.addEventListener("keydown", keydownFotTextAreafunction);
				newtxtArea.addEventListener("keyup", keyupTextAreafunction);
			} else if(jsonResp.errorInfo !== null && jsonResp.errorInfo !== '') {
				alert(jsonResp.errorInfo);
			}
		});
		//console.log(xhttp.responseText);
	}
}

function commentsDisplayCtrl(replyTo, replyToBtn, action) {
	//alert("Hello");
	var v1 = document.getElementById(replyTo);
	var v2 = document.getElementById(replyToBtn);
	if(action===1) {
		v1.style.display = "block"
		v2.style.display = "none"
	} else if(action===2) {
		v2.style.display = "block"
		v1.style.display = "none"
	}
}

function addComments(usr_id, fullname, comment_id, comment_date, content, ischildcomment) {

	var output = ''; 
	if(ischildcomment === 'Y') {
		output += "<div class=\"sub-comment-main\">";
	} else {
		output += "<div class=\"comment-main\">";
	}
	output += "<a href=\"/userInfo?userId="+ usr_id +"\">"+ fullname + "</a>";
	output += "<div class=\"comment-date\">" + comment_date + "</div>";
	output += "<br>";
	if(ischildcomment === 'Y') {
		output += "<div class=\"sub-comment-body\">" + content;
	} else {
		output += "<div class=\"comment-body\">" + content;
	}
	output += "</div>";
	output += "<br>";
	output += "<button class=\"btn btn-link\" id=\"replyToBtn_" + comment_id + "\"  onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 1);'>Reply</button>";
	output += "<br>";
	output += "<div id=\"replyTo_" + comment_id + "\" style=\"display: none;\">";
	
	output += "<div style=\"padding: 10px; background-color: rgba(200, 200, 200, 0.2) ; width: 100%; border-radius: 10px; margin-left: 10px;\" class=\"context-menu__item\">";
	output += "<div style=\"padding: 10px\" id=\"Toolbar_comments_" + comment_id + "\"> <button class=\"context-menu__link\" title=\"bold\" data-action=\"txt-bold\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'bold')\" name=\"text-bold\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-bold\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"italics\" data-action=\"txt-italic\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'italic')\" name=\"text-italic\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-italic\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"underline\" data-action=\"txt-underline\" data-target=\"comments_" + comment_id + "\" onclick=\"formatText(this, 'underline')\" name=\"text-underline\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-underline\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Left Align\" data-action=\"t-left\" data-target=\"comments_" + comment_id + "\" name=\"t-align-left\" onclick=\"formatText(this, 'left')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-left\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Center Align\" data-action=\"t-center\" data-target=\"comments_" + comment_id + "\" name=\"t-align-center\" onclick=\"formatText(this, 'center')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-center\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Justify Content\" data-action=\"t-justify\" data-target=\"comments_" + comment_id + "\" name=\"t-align-justify\" onclick=\"formatText(this, 'justify')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-justify\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Right Align\" data-action=\"t-right\" data-target=\"comments_" + comment_id + "\" name=\"t-align-right\" onclick=\"formatText(this, 'right')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-align-right\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Ordered List\" data-action=\"t-o-list\" data-target=\"comments_" + comment_id + "\" name=\"t-o-list\" onclick=\"formatText(this, 'o-list')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-list-ol\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Unordered List\" data-action=\"t-un-list\" data-target=\"comments_" + comment_id + "\" name=\"t-un-list\" onclick=\"formatText(this, 'un-list')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-list-ul\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Indent Content\" data-action=\"t-indent\" data-target=\"comments_" + comment_id + "\" name=\"t-indent\" onclick=\"formatText(this, 'indent')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa fa-indent\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Outdent Content\" data-action=\"t-dedent\" data-target=\"comments_" + comment_id + "\" name=\"t-dedent\" onclick=\"formatText(this, 'dedent')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-dedent\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Create Link\" data-action=\"t-link\" data-target=\"comments_" + comment_id + "\" name=\"t-link\" onclick=\"formatText(this, 'link')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-link\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Remove Link\" data-action=\"t-unlink\" data-target=\"comments_" + comment_id + "\" name=\"t-unlink\" onclick=\"formatText(this, 'unlink')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-unlink\"></i></button> <div class=\"vertical-seperator\"></div> <input type=\"color\" title=\"Font Color\" name=\"t-color\" data-action=\"t-color\" data-target=\"comments_" + comment_id + "\" name=\"t-color\" oninput=\"formatText(this, 'font-color')\" value=\"#000000\"> <div class=\"vertical-seperator\"></div> <select name=\"font-type\" title=\"Font Type\" data-action=\"font-type\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-type')\"> <option value=\"p\">Paragraph</option> <option value=\"h1\">Heading 1</option> <option value=\"h2\">Heading 2</option> <option value=\"h3\">Heading 3</option> <option value=\"h4\">Heading 4</option> <option value=\"h5\">Heading 5</option> <option value=\"h6\">Heading 6</option> </select> <div class=\"vertical-seperator\"></div> <select name=\"font-family\" title=\"Font Family\" data-action=\"font-family\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-family')\"> <option value=\"Arial\">Arial</option> <option value=\"Brush Script MT\">Brush Script MT</option> <option value=\"Copperplate\">Copperplate</option> <option value=\"Courier New\">Courier New</option> <option value=\"Garamond\">Garamond</option> <option value=\"Georgia\">Georgia</option> <option value=\"Helvetica\">Helvetica</option> <option value=\"Lucida Console\">LucidnameConsole</option> <option value=\"Lucida Handwriting\">Lucida Handwriting</option> <option value=\"Monaco\">Monaco</option> <option value=\"Papyrus\">Papyrus</option> <option value=\"Tahoma\">Tahoma</option> <option value=\"Times New Roman\">Times New Roman</option> <option value=\"Trebuchet MS\">Trebuchet MS</option> <option value=\"Verdana\">Verdana</option> </select> <div class=\"vertical-seperator\"></div> <select name=\"font-size\" title=\"Font Size\" data-action=\"font-size\" data-target=\"comments_" + comment_id + "\" oninput=\"formatText(this, 'font-size')\">";
	
	for(let i=2; i<25; i=i+2) {
		output += "<option value=\"" + i + "\">" + i + "</option>";
	}
	output += " </c:forEach> <option value=\"26\" selected>26</option>";
	for(let i=28; i<=60; i=i+2) {
		output += "<option value=\"" + i + "\">" + i + "</option>";
	}
	
	output += " </select> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Undo\" data-target=\"comments_" + comment_id + "\" data-action=\"t-undo\" name=\"t-undo\" onclick=\"formatText(this, 'undo')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-undo\"></i></button> <div class=\"vertical-seperator\"></div> <button class=\"context-menu__link\" title=\"Redo\" data-target=\"comments_" + comment_id + "\" data-action=\"t-redo\" name=\"t-redo\" onclick=\"formatText(this, 'redo')\" style=\"padding: 10px; display: inline;\"><i class=\"fa fa-redo\"></i></button> </div>";
	output += "<div contenteditable=\"true\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\"  style=\"width: 100%; padding: 5px;\" toolbar_target=\"Toolbar_comments_" + comment_id + "\" onfocusin=\"FocusInEvent(this)\" onkeyup=\"checkFontData(this, event);\" onclick=\"checkFontData(this, event);\"></div>";
	output += "</div>";
	//output += "<textarea rows=\"1\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\" placeholder=\"Type Your Comments here...\"></textarea>";
	
	output += "<div style=\"padding: 10px\"><button class=\"btn btn-warning\" style=\"margin: 10px\" id=\"addComment_" + comment_id + "\" name=\"addComment_" + comment_id + "\" onclick=\"saveComments(this, '" + comment_id + "', 'comments_" + comment_id + "')\">Add Comment</button>";
	output += "<button class=\"btn btn-light\" style=\"margin: 10px\" id=\"clearComment_" + comment_id + "\" name=\"clearComment_" + comment_id + "\" onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 2);'>Cancel</button>";
	output += "</div></div>";
	output += "<br><div id=\"child-comments_" + comment_id +"\">";
	output += "</div></div>";
	return output;
}


function formatText(e, action) {


	var target = e.getAttribute("data-target"),
		targetEle = document.getElementById(target);
	
	if((typeof selectedItemId === 'undefined') || (typeof selectedItemId.id === 'undefined')) {
		return false;
	}
	
	console.log(target);
	console.log(selectedItemId.id);
	if(target !== selectedItemId.id) {
		return false;
	}
	
	
	var command = "",
		actionValue = "";
	if(action === "bold") {
		command = "bold";
	} else if(action === "italic") {
		command = "italic";
	} else if(action === "underline") {
		command = "underline";
	} else if(action === "left") {
		command = "justifyLeft";
	} else if(action === "right") {
		command = "justifyRight";
	} else if(action === "center") {
		command = "justifyCenter";
	} else if(action === "justify") {
		command = "justifyFull";
	} else if(action === "o-list") {
		command = "insertOrderedList";
	} else if(action === "un-list") {
		command = "insertUnorderedList";
	} else if(action === "indent") {
		command = "indent";
	} else if(action === "dedent") {
		command = "outdent";
	} else if(action === "font-type") {
		var selValue = e.value;
		command = "formatBlock";
		actionValue = selValue;
	} else if(action === "font-color") {
		var selValue = e.value;
		command = "foreColor";
		actionValue = selValue;
	} else if(action === "redo") {
		command = "redo";
	} else if(action === "undo") {
		command = "undo";
	} else if(action === "link") {
		var str = prompt("Please Enter the URL");
		if(str === '') {
			return false;
		}
		command = "createLink";
		actionValue = str;
	} else if(action === "unlink") {
		command = "unlink";
	} else if(action === "font-size") {
		command = "fontSize";
		actionValue = e.value/7;
	} else if(action === "font-family") {
		command = "fontName";
		actionValue = e.value;
	} 
	document.execCommand(command, null, actionValue);
	
	
	console.log("Calling Font Data!");
	checkFontData(targetEle, '');
}

function makeCo(e) {
	var targetId = e.getAttribute("data-target");
	var target = document.getElementById(targetId);
	document.execCommand('bold');
}


function FocusInEvent(e) {
	/*var dataId = e.getAttribute("data-id"),
			curEle = document.getElementById(dataId);*/
	//console.log(e);
	//console.log(e.getAttribute("data-type"));
	/*var textEditor = document.getElementById("textEditor");
	var quoteEditor = document.getElementById("quotesEditor");	
	if(e.getAttribute("data-type") === "text") {
		textEditor.style.display = "inline-block";
		quoteEditor.style.display = "none";
	} else if(e.getAttribute("data-type") === "quote") {
		textEditor.style.display = "none";
		quoteEditor.style.display = "inline-block";
	} else {
		textEditor.style.display = "none";
		quoteEditor.style.display = "none";
	}*/
	
	selectedItemId = e;
	
	
}

function FocusOutEvent(e) {
	var dataId = e.getAttribute("data-id"),
		curEle = document.getElementById(dataId);
	textEditor.style.display = "none";
}

function formatQuote(curEle, action) {

	if(typeof selectedQuoteId === 'undefined') {
		return false;
	}
	var activeEle = selectedQuoteId;
	var qClasses = ['editor-quotes', 'editor-quotes-right', 'editor-quotes-center', 'editor-quotes-justify', 'editor-quotes-highlight'];
	for(let i=0; i<qClasses.length; i++) {
		if(activeEle.classList.contains(qClasses[i])) {
			activeEle.classList.remove(qClasses[i]);
		}
	}
	if(action === 'q-left') {
		activeEle.classList.add('editor-quotes');
	} else if(action === 'q-right') {
		activeEle.classList.add('editor-quotes-right');
	} else if(action === 'q-center') {
		activeEle.classList.add('editor-quotes-center');
	} else if(action === 'q-justify') {
		activeEle.classList.add('editor-quotes-justify');
	} else if(action === 'q-hQuote') {
		activeEle.classList.add('editor-quotes-highlight');
	}
}


var ctrlKeyPressed = false;

function checkCtrlKeyDown(e) {
	if(e.ctrlKey) {
		ctrlKeyPressed = true;
		console.log("ctrlKeyPressed -- " + ctrlKeyPressed);
	}
}


function checkCtrlKeyUp(e) {
	if((e.keyCode === 17)) {
		ctrlKeyPressed = false;
		console.log("ctrlKeyPressed  - " + ctrlKeyPressed);
	}
}


function checkFontData(element, e) {

	console.log("checkFontData");
	console.log(ctrlKeyPressed + " "  + e.keyCode);
	var target_toolbar_id = element.getAttribute("toolbar_target"),
		target_toolbar = document.getElementById(target_toolbar_id);
	
	var bold = target_toolbar.querySelector('button[name="text-bold"]');
	var italic = target_toolbar.querySelector('button[name="text-italic"]');
	var underline = target_toolbar.querySelector('button[name="text-underline"]');
	var tlink = target_toolbar.querySelector('button[name="t-link"]');
	
	if(e.keyCode === 17) {
		console.log("Returning ctrl");
		return false;
	} 
	
	if(ctrlKeyPressed && e.keyCode === 66) {
		console.log("Inside Method 1");
		if(checkselection('bold')) {
			console.log("curr Sel -- Bold");
			bold.className = "context-menu__link-selected context-menu__link";
		} else {
			console.log("curr Sel -- Not Bold");
			bold.classList.remove("context-menu__link-selected");
		}
	} else {
		console.log("Inside Method 2");
		if(checkselection('bold')) {
			console.log("curr Sel -- Bold");
			bold.className = "context-menu__link-selected context-menu__link";
		} else {
			console.log("curr Sel -- Not Bold");
			bold.classList.remove("context-menu__link-selected");
		}
	}
	
	/* Check For Italic Text */
	if(ctrlKeyPressed && e.keyCode === 73) {
		//console.log("Inside Method 1");
		if(checkselection('italic')) {
			//console.log("curr Sel -- italic");
			italic.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not italic");
			italic.classList.remove("context-menu__link-selected");
		}
	} else {
		//console.log("Inside Method 2");
		if(checkselection('italic')) {
			//console.log("curr Sel -- italic");
			italic.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not italic");
			italic.classList.remove("context-menu__link-selected");
		}
	}
	
	/* Check For Underlined Text */
	if(ctrlKeyPressed && e.keyCode === 85) {
		//console.log("Inside Method 1");
		if(checkselection('underline')) {
			//console.log("curr Sel -- underlined");
			underline.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not underlined");
			underline.classList.remove("context-menu__link-selected");
		}
	} else {
		//console.log("Inside Method 2");
		if(checkselection('underline')) {
			//console.log("curr Sel -- underlined");
			underline.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not underlined");
			underline.classList.remove("context-menu__link-selected");
		}
	}
	
	
}

  
  function checkselection(criteria) {
    var check = false;
    if (document.queryCommandState) {
        check = document.queryCommandState(criteria);
    }
    return check;
  }
  