var i = 0, j = 0, k = 0, l = 0, num = 0, tot = 1;
function addFields() {
    //var number = document.getElementById("member").value;
    var container = document.getElementById("container");
    //for (i=0;i<number;i++)
    {
        num = i + 1;
        var numstr = num.toString(10);
        //alert(numstr);
        var str = "Field";
        var val = str + numstr;
        //var val = 10; 
        //container.appendChild(document.createTextNode("Field Member " + (i + 1)));
        container.appendChild(document.createElement("br"));
        container.appendChild(document.createElement("br"));
        var input = document.createElement("textarea");
        //input.type = "text";
        input.rows = 1;
        input.cols = 100;
        input.addEventListener("keydown", keydownFotTextAreafunction);
		input.addEventListener("keyup", keyupTextAreafunction);
        input.name = val;
        input.className = "articleInput";
        input.id = tot;
        input.placeholder = "You can Type Your Text Here";
        input.required = "1";
        input.autocomplete = "on";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));
        i++;
    }
    document.getElementById("paran").value = document.getElementById("paran").value + (tot % 10).toString(10);
    document.getElementById("parac").value = i;

    var container1 = document.getElementById("container1");
    container1.appendChild(document.createElement("br"));
    var input = document.createElement("input");
    input.type = "hidden";
    var temp = "parh";
    var temp1 = i.toString(10);
    temp = temp + temp1;
    input.name = temp;
    input.className = "articleInput";
    input.value = tot;
    container1.appendChild(input);
    document.getElementById("tot").value = tot;
    tot++;
}

function addimgFields() {
    //var number = document.getElementById("member").value;
    var container = document.getElementById("container");
    //for (i=0;i<number;i++)
    {
        num = j + 1;
        var numstr = num.toString(10);
        //alert(numstr);
        var str = "Img";
        var val = str + numstr;
        //var val = 10; 
        //container.appendChild(document.createTextNode("Image Member " + (j + 1)));
        container.appendChild(document.createElement("br"));
        container.appendChild(document.createElement("br"));
        var input = document.createElement("input");
        input.type = "url";
        //input.rows = 10;
        //input.cols = 100;
        input.name = val;
        input.id = tot;
        input.className = "articleInput";
        input.placeholder = "Enter Your Image Url Here";
        input.required = "1";
        input.autocomplete = "on";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));

        /*
        var input1 = document.createElement("input");
        input1.type = "text";
        //input.rows = 10;
        //input.cols = 100;
        input1.name = "Label for" + val;
        //input.id = tot;
        container.appendChild(input1);
        container.appendChild(document.createElement("br"));
        */
        j++;
    }
    document.getElementById("imgn").value = document.getElementById("imgn").value + (tot % 10).toString(10);
    document.getElementById("imgc").value = j;

    var container1 = document.getElementById("container1");
    container1.appendChild(document.createElement("br"));
    var input = document.createElement("input");
    input.type = "hidden";
    //input.rows = 10;
    //input.cols = 100;
    var temp = "imgh";
    var temp1 = j.toString(10);
    temp = temp + temp1;
    input.name = temp;
    input.className = "articleInput";
    input.value = tot;
    //input.id = tot;
    container1.appendChild(input);
    document.getElementById("tot").value = tot;
    tot++;
}

function addyvidFields() {
    //var number = document.getElementById("member").value;
    var container = document.getElementById("container");
    //for (i=0;i<number;i++)
    {
        num = k + 1;
        var numstr = num.toString(10);
        //alert(numstr);
        var str = "You_Link";
        var val = str + numstr;
        //var val = 10; 
        //container.appendChild(document.createTextNode("Youtube Member " + (k + 1)));
        container.appendChild(document.createElement("br"));
        container.appendChild(document.createElement("br"));
        var input = document.createElement("input");
        input.type = "url";
        //input.rows = 10;
        //input.cols = 100;
        input.name = val;
        input.id = tot;
        input.className = "articleInput";
        input.placeholder = "Enter Your video Url here";
        input.required = "1";
        input.autocomplete = "on";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));
        k++;
    }
    document.getElementById("youn").value = document.getElementById("youn").value + (tot % 10).toString(10);
    document.getElementById("youc").value = k;

    var container1 = document.getElementById("container1");
    container1.appendChild(document.createElement("br"));
    var input = document.createElement("input");
    input.type = "hidden";
    //input.rows = 10;
    //input.cols = 100;
    var temp = "youh";
    var temp1 = k.toString(10);
    temp = temp + temp1;
    input.name = temp;
    input.value = tot;
    input.className = "articleInput";
    //input.id = tot;
    container1.appendChild(input);
    document.getElementById("tot").value = tot;
    tot++;
}

function addqFields() {
    //var number = document.getElementById("member").value;
    var container = document.getElementById("container");
    //for (i=0;i<number;i++)
    {
        num = l + 1;
        var numstr = num.toString(10);
        //alert(numstr);
        var str = "Quote";
        var val = str + numstr;
        //var val = 10; 
        //container.appendChild(document.createTextNode("Quote Member " + (l + 1)));
        container.appendChild(document.createElement("br"));
        container.appendChild(document.createElement("br"));
        var input = document.createElement("textarea");
        //input.type = "url";
        input.rows = 1;
        input.cols = 100;
        input.name = val;
        input.addEventListener("keydown", keydownFotTextAreafunction);
		input.addEventListener("keyup", keyupTextAreafunction);
        input.id = tot;
        input.className = "articleInput";
        input.placeholder = "Enter Your Quotes Data Here in this Quotes Section";
        input.required = "1";
        input.autocomplete = "on";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));
        l++;
    }
    document.getElementById("quoten").value = document.getElementById("quoten").value + (tot % 10).toString(10);
    document.getElementById("quotec").value = l;

    var container1 = document.getElementById("container1");
    container1.appendChild(document.createElement("br"));
    var input = document.createElement("input");
    input.type = "hidden";
    //input.rows = 10;
    //input.cols = 100;
    var temp = "quoh";
    var temp1 = l.toString(10);
    temp = temp + temp1;
    input.name = temp;
    input.value = tot;
    input.className = "articleInput";
    //input.id = tot;
    container1.appendChild(input);
    document.getElementById("tot").value = tot;
    tot++;
}

function colsize() {
    if (window.innerWidth < 615) {
        var input = document.getElementsByTagName("textarea");
        for (a = 0; a < num; a++) {
            input[a].cols = "50";
        }
    }
    else if (window.innerWidth < 800) {
        var input = document.getElementsByTagName("textarea");
        for (a = 0; a < num; a++) {
            input[a].cols = "80";
        }
    }
    else {
        var input = document.getElementsByTagName("textarea");
        for (a = 0; a < num; a++) {
            input[a].cols = "100";
        }
    }
    if (num != 0);
    //{ alert(num + window.innerWidth); }
}
setInterval('colsize()', 100);

function setsubdate() {
    document.getElementById("dsub").value = new Date();
    alert(document.getElementById("dsub").value);
}


window.submitForm = function() {
	var formelements = document.getElementById("articleSubForm");
	formelements.setAttribute("action","/membersdashboard/submitForm");
	formelements.setAttribute("target","_self");
	
	formelements.submit();
}

window.cancelSubmit = function() {
	alert("called Parent!");
}

function previewForm() {
	alert("Opening Preview!");
    document.getElementById("dsub").value = new Date();
	var formelements = document.getElementById("articleSubForm");
	formelements.setAttribute("action","/membersdashboard/previewForm");
	formelements.setAttribute("target","bugsme");
	exportwindow = window.open("", "bugsme", "toolbar=0,scrollbars=0,statusbar=0,menubar=0, width=window.width,height=window.height,resizable=yes");
	
	formelements.submit();
}


function clearAricle() {
	var title = document.getElementById("title");
	var tot = document.getElementById("tot");
	if((title.value !== null && title.value !== '') || (tot.value !== null && tot.value !== '')) { 
		var result = confirm("Do You Really want to discard the Article? \n(Action Cannot be undone) ");
		if(result==true) {
			location.reload();
		}
	}
}

window.onload = function() {
	//var mainComments = document.getElementById("comments");
	const textAreas = document.getElementsByTagName('textarea');
	for(let i=0; i < textAreas.length; i++) {
		textAreas[i].addEventListener("keydown", keydownFotTextAreafunction);
		textAreas[i].addEventListener("keyup", keyupTextAreafunction);
	}
	
}

function keydownFotTextAreafunction (e) {	
	var curPos1 = $(this).prop("selectionStart");
    if (e.code === "Enter") {  
        var currow = this.rows;
        //alert(currow);
        this.rows = currow + 1;
    } else if (e.code === "Backspace" || e.code === "Delete") {
    	if(curPos1>0) {
    		var charAtPrevPos = this.value.charAt(curPos1-1);
    		
    		if(charAtPrevPos === '\n') {
    			//alert(charAtPrevPos);
	    		var currow = this.rows;
		        //alert(currow);
		        this.rows = currow - 1;
    		}
    	}
    	
    }
}

function keyupTextAreafunction (e) {
		var curPos1 = $(this).prop("selectionStart");
		if (e.code === "Backspace" || e.code === "Delete") {
			if(this.value === '') {
				this.rows = 1;
			}
		}
	}

function saveComments(commObj, parent, commIds) {

	console.log("Inside SaveComments");
	var articleId = document.getElementById("article_id").value;
	var commmmentBoxId = "comments";
	if(commIds !== null && commIds !== '') {
		commmmentBoxId = commIds;
	}
	var comment_content = document.getElementById(commmmentBoxId).value;
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
				document.getElementById(commmmentBoxId).value = "";
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
	output += "<textarea rows=\"1\" class=\"commentsBar\" id=\"comments_" + comment_id + "\" name=\"comments_" + comment_id + "\" class=\"commentsBar\" placeholder=\"Type Your Comments here...\"></textarea>";
	output += "<div style=\"padding: 10px\"><button class=\"btn btn-warning\" style=\"margin: 10px\" id=\"addComment_" + comment_id + "\" name=\"addComment_" + comment_id + "\" onclick=\"saveComments(this, '" + comment_id + "', 'comments_" + comment_id + "')\">Add Comment</button>";
	output += "<button class=\"btn btn-light\" style=\"margin: 10px\" id=\"clearComment_" + comment_id + "\" name=\"clearComment_" + comment_id + "\" onclick='commentsDisplayCtrl(\"replyTo_" + comment_id + "\", \"replyToBtn_" + comment_id + "\", 2);'>Cancel</button>";
	output += "</div></div>";
	output += "<br><div id=\"child-comments_" + comment_id +"\">";
	output += "</div></div>";
	return output;
}