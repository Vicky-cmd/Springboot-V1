var i = 0, j = 0, k = 0, l = 0, num = 0, tot = 1, selectedQuoteId, iseditSaved=true;
function addFields() {
    var container = document.getElementById("container");
    var input = document.createElement("div");
    input.contentEditable = "true";
    input.className = "editor editor-text-normal";
    input.id = tot;
    var att = document.createAttribute("data-id");
    att.value = tot;
    input.setAttributeNode(att); 
    att = document.createAttribute("data-type");
    att.value = "text";
    input.setAttributeNode(att);     
    att = document.createAttribute("onfocusin");
    att.value = "FocusInEvent(this)";
    input.setAttributeNode(att);
    att = document.createAttribute("onkeyup");
    att.value = "checkFontData(event);";
    input.setAttributeNode(att);
    att = document.createAttribute("onclick");
    att.value = "checkFontData(event);";
    input.setAttributeNode(att);    
    container.appendChild(input);
    
    
    tot++;
    
	var char = 0, sel; 
	input.focus();
	if (document.selection) {
	  sel = document.selection.createRange();
	  sel.moveStart('character', char);
	  sel.select();
	}
	else {
	   sel = window.getSelection();
	  sel.collapse(input.firstChild, char);
	}
}

function addFieldsWithImage(abovePos) {
    var container = document.getElementById("container");
    num = i + 1;
    var numstr = num.toString(10);
    var str = "Field";
    var val = str + numstr;
    var input = document.createElement("div");
    input.className = "editor img-editor-center";
    input.id = tot;
    var att = document.createAttribute("data-id");
    att.value = tot;
    input.setAttributeNode(att); 
    att = document.createAttribute("data-type");
    att.value = "media";
    input.setAttributeNode(att); 
    container.insertBefore(input, abovePos);
    i++;
    tot++;
    
	var char = 0, sel; 
	input.focus();
	if (document.selection) {
	  sel = document.selection.createRange();
	  sel.moveStart('character', char);
	  sel.select();
	}
	else {
	   sel = window.getSelection();
	  sel.collapse(input.firstChild, char);
	}
}

function addimgFields() {
    var container = document.getElementById("container");
    num = j ;
    var numstr = num.toString(10);
    var str = "Img";
    var val = str + numstr;
    var input = document.createElement("input");
    input.type = "file";
    input.accept="image/x-png,image/gif,image/jpeg"
    input.name = val;
    input.id = "ImgField_" + tot;
    input.className = "articleInput";
    input.style.width = "95%";
    var att = document.createAttribute("data-id");
    att.value = "ImgField_" + tot;
    input.setAttributeNode(att); 
    input.style.margin =  "auto";
    input.required = "1";
    input.autocomplete = "on";
    container.appendChild(input);
	input.addEventListener("input", uploadFileFunction);
    j++;
    tot++;
}

function addyvidFields() {
    var container = document.getElementById("container");
    var pdiv = document.createElement("div");
    pdiv.className = "editor img-editor-center";
    //pdiv.addEventListener("keyup", addCommentsForFB);
    var att = document.createAttribute("data-id");
    att.value = tot;
    pdiv.id = tot;
    pdiv.setAttributeNode(att);
    container.appendChild(pdiv);
    
    num = k + 1;
    var numstr = num.toString(10);
    var str = "You_Link";
    var val = str + numstr;
    var input = document.createElement("input");
    input.type = "url";
    input.name = val;
    input.id = "Field_" + tot;
    input.className = "articleInput";
    input.placeholder = "Enter Your video Url here";
    input.required = "1";
    var att = document.createAttribute("data-id");
    att.value = tot;
    input.setAttributeNode(att);
    input.autocomplete = "on";
	input.addEventListener("keydown", uploadVideoFunction);
    pdiv.appendChild(input);


	pdiv.appendChild(document.createElement("br"));
	pdiv.appendChild(document.createElement("br"));
	var input = document.createElement("textarea");
    input.className = "articleInput";
    input.id = "FB_" + tot;
    input.style.display = "none";
    input.maxLength = "100";
    input.rows = 1;
    input.placeholder = "Add Your Description here (100 words)";
	input.addEventListener("keyup", addCommentsForFB);
    var att = document.createAttribute("data-id");
    att.value = tot;
    input.setAttributeNode(att); 
    var att = document.createAttribute("parentRef");
    att.value = this;
    input.setAttributeNode(att); 
    pdiv.appendChild(input);
 //   k++;
    tot++;
}

function addqFields() {
    var container = document.getElementById("container");
    num = l + 1;
    var numstr = num.toString(10);
    var str = "Quote";
    var val = str + numstr;
    var input = document.createElement("div");
    input.contentEditable = "true";
    input.id = tot;
    input.className = "editor editor-quotes";
    var att = document.createAttribute("data-id");
    att.value = tot;
    input.setAttributeNode(att); 
    att = document.createAttribute("data-type");
    att.value = "quote";
    input.setAttributeNode(att);     
    att = document.createAttribute("onfocusin");
    att.value = "FocusInEvent(this)";
    input.setAttributeNode(att);
    att = document.createAttribute("onkeyup");
    att.value = "checkFontData(event);";
    input.setAttributeNode(att);
    att = document.createAttribute("onclick");
    att.value = "checkFontData(event);";
    input.setAttributeNode(att);
    container.appendChild(input);
    
    l++;
    tot++;
    
    input.innerHTML = ' ';
	var char = 0, sel; 
	input.focus();
	if (document.selection) {
	  sel = document.selection.createRange();
	  sel.moveStart('character', char);
	  sel.select();
	}
	else {
	   sel = window.getSelection();
	  sel.collapse(input.firstChild, char);
	}
}

function setsubdate() {
    document.getElementById("dsub").value = new Date();
    alert(document.getElementById("dsub").value);
}


window.submitForm = function() {
	alert("Submitting the Form!");
	var container = document.getElementById("container");   
	var subContent = document.getElementById("submissionContent");   
	subContent.value = container.innerHTML;
	var formelements = document.getElementById("articleSubForm");
	
	for(var i=0; i < formelements.elements.length; i++){
      if(formelements.elements[i].value === '' && formelements.elements[i].hasAttribute('required')){
        alert(formelements.elements[i].getAttribute("f-Name") + ' is a required Field!');
        return false;
      }
    }
    
	var subelements = container.getElementsByTagName("div"); //childNodes,
	wordsLen = 0,
	isEligible = false;
	console.log(subelements);
	for(let i=0; i< subelements.length; i++) {
		console.log(subelements[i]);
		var dtype = subelements[i].getAttribute("data-type");
		if(dtype === 'media') {
			isEligible = true;
		}
		else {
			wordsLen = wordsLen + get_text(subelements[i]).length;
		}
	}
	console.log("words Length " + wordsLen);
	if(!isEligible) {
		if(wordsLen<100) {
			alert("In Case Of A Plain Test Article, Word Length Must be atleast 100");
			return false;
		}
	}

	formelements.setAttribute("action","/membersdashboard/submitForm");
	formelements.setAttribute("target","_self");
	
	formelements.submit();
}

function get_text(el) {
    ret = "";
    var length = el.childNodes.length;
    for(var i = 0; i < length; i++) {
        var node = el.childNodes[i];
        if(node.nodeType != 8) {
            ret += node.nodeType != 1 ? node.nodeValue : get_text(node);
        }
    }
    return ret;
}


window.cancelSubmit = function() {
	alert("called Parent!");
}


function previewForm() {
	alert("Opening Preview!");
    document.getElementById("dsub").value = new Date();
    
	var container = document.getElementById("container");   
	var subContent = document.getElementById("submissionContent");   
	subContent.value = container.innerHTML;
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


function uploadVideoFunction(e) {

	if (e.code === "Enter") {  
		
		var enteredUrl = this.value;
		if(enteredUrl === '' ) {
			return;
		}
		console.log("Entered URL -> " + enteredUrl);
		var fbHostnames = ["facebook", "fb"];
		var youtubeHostnames = ["youtube"];
		
		var hostnamelst = new URL(enteredUrl).hostname.split(".")
		console.log(hostnamelst);
		
		var videoProvider = '';
		for(let i=0; i<hostnamelst.length; i++) {
			if(fbHostnames.includes(hostnamelst[i])) {
				videoProvider = 'facebook';
				break;
			} else if(youtubeHostnames.includes(hostnamelst[i])){
				videoProvider = 'youtube';
			}
		}
		
		console.log(videoProvider);
		var videoTag = "";
		if(videoProvider === "facebook") {
			var dataid = this.getAttribute("data-id");
			var desc = document.getElementById("FB_" + dataid);
			desc.style = "block";
			return;
			//videoTag = '<div class="fb-video" data-href="' + enteredUrl + '" data-width="500" data-show-text="false"><blockquote cite="' + enteredUrl + '" class="fb-xfbml-parse-ignore"><span contenteditable="true"><a href="' + enteredUrl + '">How to Share With Just Friends</a><p contenteditable="true">How to share with just friends.</p></span>Posted from <a href="https://www.facebook.com/facebookapp/">Facebook App</a> <span contenteditable="true">on Friday, 5 December 2014</span></blockquote></div>';
		} else if(videoProvider === "youtube") {
			
			var dataid = this.getAttribute("data-id");
			document.getElementById("FB_" + dataid).style = "none";
			
			var youtubeEmbedUrl = "https://www.youtube.com/embed/";
			var vid = enteredUrl.split('v=')[1];
			if(vid.indexOf('&') != -1) {
				vid = vid.substring(0, vid.indexOf('&'));
			}
			videoTag = "<iframe src=\"" + youtubeEmbedUrl + vid + "\" width=\"560\" height=\"315\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		}
		
		//var imgEle = document.getElementById("ImgField_01");
		
		var dataid = this.getAttribute("data-id");
		var parent = document.getElementById(dataid);  
	
		addFieldsWithImage(parent)
		console.log(videoTag)
    	var field = document.getElementById(tot - 1);
    	field.innerHTML = videoTag;
    	
    	console.log(this);
    	if(addNewTextField(this)) {
    		addFields();
    	}
    	parent.parentNode.removeChild(parent);
    	
	}
		return false;

}

function addNewTextField(curEle) {
    	console.log(curEle);
	var bool = false;
	var dataid = curEle.getAttribute("data-id");
	var parent = document.getElementById(dataid);     
	
	var container = document.getElementById("container");   
	var elements = container.getElementsByTagName("div");
	
	
	var previous = '', next = '';
	console.log("Parent ID " + parent.id);
	for(var i = 0; i < elements.length; i++) {

    	if(elements[i].id === parent.id) {
    		if(i>0) {
    			previous = elements[i - 1];
    		} else {
    			previous = null;
    		}
    		if(i<(elements.length-1)) {
     			next = elements[i + 1];
     		} else {
    			next = null;
    		}
    	}
    
	}


	console.log(next)
	if(next !== null && next !== '') {
		console.log(next.value)
		var dataId = next.getAttribute("data-id"),
			dataType = next.getAttribute("data-type");
		console.log(dataType);
		if(dataType !== 'text') {
			bool = true;
		}
	
	} else {
		bool = true;
	}
	
	return bool;
}

function addCommentsForFB(e) {

	if (e.code === "Enter") {
		var dataid = this.getAttribute("data-id");
		var parent = document.getElementById(dataid);
		
		var desc = this.value;
		
		var enteredUrl = document.getElementById("Field_" + dataid).value;		
		var videoTag = '<div class="fb-video" data-href="' + enteredUrl + '" data-width="500" data-show-text="false"><blockquote cite="' + enteredUrl + '" class="fb-xfbml-parse-ignore"><a href="' + enteredUrl + '">View On Facebook</a><p>' + desc + '</p>Posted from <a href="https://www.facebook.com/facebookapp/">Facebook App</a> on Friday, 5 December 2014</blockquote></div>';
		addFieldsWithImage(parent)
		console.log(videoTag)
		var field = document.getElementById(tot - 1);
		field.innerHTML = videoTag;
		if(addNewTextField(this)) {
			addFields()
		}
		parent.parentNode.removeChild(parent);
	}
}

function uploadFileFunction(e) {
	 	
 	console.log("Clicked on 'Enter'");
 	var inputEle = this;
 	var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
 	
 	var files = this.files;
 	console.log(this);
 	
 	var formData = new FormData();
 	for (var i = 0; i < files.length; i++) {
        var file = files[i];

        formData.append('image', file, file.name);
	}   
 	
 	console.log(formData);
 	
 	xmlhttp.open("POST", "/uploadImage", true);
 	//xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        	console.log("Received Response!");
            var data = xmlhttp.responseText;
            //document.getElementById("flash-message").innerHTML = inputEle;
            var jsonResp = JSON.parse(data);
            if(jsonResp.status === "200") {
            	//var imgEle = document.getElementById("ImgField_01");
            	//imgEle.parentNode.removeChild(imgEle);
            	console.log("This is the element!");
            	console.log(inputEle);
            	addFieldsWithImage(inputEle)
            	console.log("<img src=\"" + jsonResp.file_path + "\"></img>")
            	var field = document.getElementById(tot - 1);
            	field.innerHTML = "<img src=\"" + jsonResp.file_path + "\" style=\"width: 50%; margin: auto;\"></img>";
            	if(addNewTextField(inputEle)) {
            		addFields()
            	}
            	inputEle.parentNode.removeChild(inputEle);
            }
        }
    }
    xmlhttp.send(formData);
    
}


function formatText(e, action) {


	/*var target = e.getAttribute("data-target");
	
	if((typeof window.getSelection() === 'undefined') || (typeof window.getSelection().anchorNode.parentNode === 'undefined')) {
		return false;
	}
	
	
	if(target !== window.getSelection().anchorNode.parentNode.id) {
		return false;
	}*/
	
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
		var font_size = document.getElementById("font-size"),
			f_value = font_size.value;
		command = "fontSize";
		actionValue = f_value/7;
	} else if(action === "font-family") {
		var font_fam = document.getElementById("font-family"),
			f_value = font_fam.value;
		command = "fontName";
		actionValue = f_value;
	} 
	document.execCommand(command, null, actionValue);
	
	checkFontData('');
}


var startProductBarPos=-1;
window.onscroll=function(){
	if(typeof bar === 'undefined') {
		return;
	}
  var bar = document.getElementById('menu-bar');
  if(startProductBarPos<0)startProductBarPos=findPosY(bar);

  if(pageYOffset>startProductBarPos){
    bar.style.position='fixed';
    bar.style.top=0;
  }else{
    bar.style.position='relative';
  }

};

function findPosY(obj) {
  var curtop = 0;
  if (typeof (obj.offsetParent) != 'undefined' && obj.offsetParent) {
    while (obj.offsetParent) {
      curtop += obj.offsetTop;
      obj = obj.offsetParent;
    }
    curtop += obj.offsetTop;
  }
  else if (obj.y)
    curtop += obj.y;
  return curtop;
}


function FocusInEvent(e) {
	/*var dataId = e.getAttribute("data-id"),
			curEle = document.getElementById(dataId);*/
	//console.log(e);
	//console.log(e.getAttribute("data-type"));
	var textEditor = document.getElementById("textEditor");
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
	}
	
	selectedQuoteId = e;
			
	
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
	}
}


function checkCtrlKeyUp(e) {

	if(e.ctrlKey) {
		ctrlKeyPressed = false;
	}
}


function checkFontData(e) {

	
	var bold = document.getElementById("text-bold-main");
	var italic = document.getElementById("text-italic-main");
	var underline = document.getElementById("text-underline-main");
	var tlink = document.getElementById("t-link");
	
	if(ctrlKeyPressed && e.code === "KeyB") {
		//console.log("Inside Method 1");
		if(!checkselection('bold')) {
			//console.log("curr Sel -- Bold");
			bold.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not Bold");
			bold.classList.remove("context-menu__link-selected");
		}
	} else {
		//console.log("Inside Method 2");
		if(checkselection('bold')) {
			//console.log("curr Sel -- Bold");
			bold.className = "context-menu__link-selected context-menu__link";
		} else {
			//console.log("curr Sel -- Not Bold");
			bold.classList.remove("context-menu__link-selected");
		}
	}
	
	/* Check For Italic Text */
	if(ctrlKeyPressed && e.code === "KeyI") {
		//console.log("Inside Method 1");
		if(!checkselection('italic')) {
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
	if(ctrlKeyPressed && e.code === "KeyU") {
		//console.log("Inside Method 1");
		if(!checkselection('underline')) {
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
	
	
	getTotalWordLength();
}

  
  function checkselection(criteria) {
    var check = false;
    if (document.queryCommandState) {
        check = document.queryCommandState(criteria);
    }
    return check;
  }
  
function getTotalWordLength() {
	var container = document.getElementById("container");
	var subelements = container.getElementsByTagName("div"); 
		wordsLen = 0;
	for(let i=0; i< subelements.length; i++) {
		//console.log(subelements[i]);
		var dtype = subelements[i].getAttribute("data-type");
		if(!(dtype === 'media')) {
			wordsLen = wordsLen + get_text(subelements[i]).length;
		}
	}
	if(typeof wordsLen === 'undefined') {
		wordsLen = 0;
	}
	console.log(wordsLen);
	document.getElementById("tot_word_count").innerText = wordsLen;
}

