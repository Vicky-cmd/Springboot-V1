(function() {
  
  "use strict";

  //////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////
  //
  // H E L P E R    F U N C T I O N S
  //
  //////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////

  /**
   * Function to check if we clicked inside an element with a particular class
   * name.
   * 
   * @param {Object} e The event
   * @param {String} className The class name to check against
   * @return {Boolean}
   */
  function clickInsideElement( e, className ) {
    var el = e.srcElement || e.target;
    
    if ( el.classList.contains(className) ) {
      return el;
    } else {
      while ( el = el.parentNode ) {
        if ( el.classList && el.classList.contains(className) ) {
          return el;
        }
      }
    }

    return false;
  }

  /**
   * Get's exact position of event.
   * 
   * @param {Object} e The event passed in
   * @return {Object} Returns the x and y position
   */
  function getPosition(e) {
    var posx = 0;
    var posy = 0;

    if (!e) var e = window.event;
    
    if (e.pageX || e.pageY) {
      posx = e.pageX;
      posy = e.pageY;
    } else if (e.clientX || e.clientY) {
      posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
      posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
    }

    return {
      x: posx,
      y: posy
    }
  }

  //////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////
  //
  // C O R E    F U N C T I O N S
  //
  //////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////
  
  /**
   * Variables.
   */
  var contextMenuClassName = "context-menu";
  var contextMenuItemClassName = "context-menu__item";
  var contextMenuLinkClassName = "context-menu__link";
  var contextMenuActive = "context-menu--active";

  var taskItemClassName = "editor";
  var taskItemInContext;

  var clickCoords;
  var clickCoordsX;
  var clickCoordsY;

  var menu = document.querySelector("#context-menu");
  var menuItems = menu.querySelectorAll(".context-menu__item");
  var menuState = 0;
  var menuWidth;
  var menuHeight;
  var menuPosition;
  var menuPositionX;
  var menuPositionY;

  var windowWidth;
  var windowHeight;

  /**
   * Initialise our application's code.
   */
  function init() {
    contextListener();
    clickListener();
    keyupListener();
    resizeListener();
  }

  /**
   * Listens for contextmenu events.
   */
  function contextListener() {
    document.addEventListener( "contextmenu", function(e) {
      taskItemInContext = clickInsideElement( e, taskItemClassName );
		console.log("Making the element visible ---> " + taskItemInContext.getAttribute("data-type"));
	
		var imgEle = document.getElementById("imgProp");
		var quoteProp = document.getElementById("quotesProp");
		var textProp = document.getElementById("textProp");
		var dataId = taskItemInContext.getAttribute("data-id"),
			curEle = document.getElementById(dataId)	
		var bold, italics;
		if(taskItemInContext.getAttribute("data-type") === "media") {
			imgEle.style.display = "block";
			quoteProp.style.display = "none";
			textProp.style.display = "none";
		} else if(taskItemInContext.getAttribute("data-type") === "text") {
			imgEle.style.display = "none";
			quoteProp.style.display = "none";
			textProp.style.display = "block";
			bold = document.getElementById("text-bold");
			italics = document.getElementById("text-italic"); 
			
			
	    	if(curEle.classList.contains('editor-text-largest')) {
				document.getElementById("t-header1").className = "context-menu__link-selected context-menu__link";
				document.getElementById("t-header2").classList.remove("context-menu__link-selected");
				document.getElementById("t-normal").classList.remove("context-menu__link-selected");
	    	} else if(curEle.classList.contains('editor-text-larger')) {
				document.getElementById("t-header2").className = "context-menu__link-selected context-menu__link";
				document.getElementById("t-header1").classList.remove("context-menu__link-selected");
				document.getElementById("t-normal").classList.remove("context-menu__link-selected");
	    	} else {
	    		document.getElementById("t-normal").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("t-header1").classList.remove("context-menu__link-selected");
	    		document.getElementById("t-header2").classList.remove("context-menu__link-selected");
	    	}
			
			
		} else if(taskItemInContext.getAttribute("data-type") === "quote") {
			textProp.style.display = "none";
			quoteProp.style.display = "block";
			imgEle.style.display = "none"
			bold = document.getElementById("q-bold");
			italics = document.getElementById("q-italic");
			
			if(curEle.classList.contains('editor-quotes')) {
	    		document.getElementById("q-align-left").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("q-align-center").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-justify").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-right").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-highlight").classList.remove("context-menu__link-selected");
			} else if(curEle.classList.contains('editor-quotes-right')) {
	    		document.getElementById("q-align-right").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("q-align-center").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-justify").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-left").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-highlight").classList.remove("context-menu__link-selected");
			} else if(curEle.classList.contains('editor-quotes-center')) {
	    		document.getElementById("q-align-center").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("q-align-left").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-justify").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-right").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-highlight").classList.remove("context-menu__link-selected");
			} else if(curEle.classList.contains('editor-quotes-justify')) {
	    		document.getElementById("q-align-justify").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("q-align-center").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-left").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-right").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-highlight").classList.remove("context-menu__link-selected");
			} else if(curEle.classList.contains('editor-quotes-highlight')) {
	    		document.getElementById("q-highlight").className = "context-menu__link-selected context-menu__link";
	    		document.getElementById("q-align-center").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-left").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-right").classList.remove("context-menu__link-selected");
	    		document.getElementById("q-align-justify").classList.remove("context-menu__link-selected");
			}
			
		}
		
		if(taskItemInContext.getAttribute("data-type") === "text" || taskItemInContext.getAttribute("data-type") === "quote") {
			console.log(selectionIsBold());
			if(selectionIsBold()) {
				bold.className = "context-menu__link-selected context-menu__link";
			} else {
				bold.classList.remove("context-menu__link-selected");
			}
			console.log(selectionIsItalic());
			if(selectionIsItalic()) {
				italics.className = "context-menu__link-selected context-menu__link";
			} else {
				italics.classList.remove("context-menu__link-selected");
			}
		}
		
      if ( taskItemInContext ) {
        e.preventDefault();
        toggleMenuOn();
        positionMenu(e);
      } else {
        taskItemInContext = null;
        toggleMenuOff();
      }
    });
  }
  
  function selectionIsBold() {
    var isBold = false;
    if (document.queryCommandState) {
        isBold = document.queryCommandState("bold");
    }
    return isBold;
  }
  
  function selectionIsItalic() {
    var isItalic = false;
    if (document.queryCommandState) {
        isItalic = document.queryCommandState("italic");
    }
    return isItalic;
  }

  /**
   * Listens for click events.
   */
  function clickListener() {
    document.addEventListener( "click", function(e) {
      var clickeElIsLink = clickInsideElement( e, contextMenuLinkClassName );

      if ( clickeElIsLink ) {
        e.preventDefault();
        menuItemListener( clickeElIsLink );
      } else {
        var button = e.which || e.button;
        if ( button === 1 ) {
          toggleMenuOff();
        }
      }
    });
  }

  /**
   * Listens for keyup events.
   */
  function keyupListener() {
    window.onkeyup = function(e) {
      if ( e.keyCode === 27 ) {
        toggleMenuOff();
      }
    }
  }

  /**
   * Window resize event listener
   */
  function resizeListener() {
    window.onresize = function(e) {
      toggleMenuOff();
    };
  }

  /**
   * Turns the custom context menu on.
   */
  function toggleMenuOn() {
    if ( menuState !== 1 ) {
      menuState = 1;
      menu.classList.add( contextMenuActive );
    }
  }

  /**
   * Turns the custom context menu off.
   */
  function toggleMenuOff() {
    if ( menuState !== 0 ) {
      menuState = 0;
      menu.classList.remove( contextMenuActive );
    }
  }

  /**
   * Positions the menu properly.
   * 
   * @param {Object} e The event
   */
  function positionMenu(e) {
    clickCoords = getPosition(e);
    clickCoordsX = clickCoords.x;
    clickCoordsY = clickCoords.y;

    menuWidth = menu.offsetWidth + 4;
    menuHeight = menu.offsetHeight + 4;

    windowWidth = window.innerWidth;
    windowHeight = window.innerHeight;

    if ( (windowWidth - clickCoordsX) < menuWidth ) {
      menu.style.left = windowWidth - menuWidth + "px";
    } else {
      menu.style.left = clickCoordsX + "px";
    }

    if ( (windowHeight - clickCoordsY) < menuHeight ) {
      menu.style.top = windowHeight - menuHeight + "px";
    } else {
      menu.style.top = clickCoordsY + "px";
    }
  }

  /**
   * Dummy action function that logs an action when a menu item link is clicked
   * 
   * @param {HTMLElement} link The link that was clicked
   */
  function menuItemListener( link ) {
  	if(typeof taskItemInContext === 'undefined') {
  		return false;
  	}
    console.log( "Task ID - " + taskItemInContext.getAttribute("data-id") + ", Task action - " + link.getAttribute("data-action"));
    toggleMenuOff();
    var dataId = taskItemInContext.getAttribute("data-id"),
    	dAction = link.getAttribute("data-action");
    var imgEle = document.getElementById(dataId)	
/*    var container = document.getElementById("container");
    var previous, next;
    ;
    
    var elements = container.getElementsByTagName("div");
    
    for(var i = 0; i < elements.length; i++) {
    
    	if(elements[i].id === dataId) {
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
    
    }*/
    
    var mediaClasses = ['img-editor-left', 'img-editor-center', 'img-editor-right'];
    var qClasses = ['editor-quotes', 'editor-quotes-right', 'editor-quotes-center', 'editor-quotes-justify', 'editor-quotes-highlight'];
    var txtClasses = ['editor-text-largest', 'editor-text-larger', 'editor-text-normal'];
    
    if(dAction === "Delete") {	
	    imgEle.parentNode.removeChild(imgEle);
    } else if(dAction === "left") {
    	for(let i=0; i<mediaClasses.length; i++) {
    		if(imgEle.classList.contains(mediaClasses[i])) {
    			imgEle.classList.remove(mediaClasses[i]);
    		}
    	}
    	imgEle.classList.add('img-editor-left');
    	console.log("Aligning left")
    } else if(dAction === "center") {
	    for(let i=0; i<mediaClasses.length; i++) {
    		if(imgEle.classList.contains(mediaClasses[i])) {
    			imgEle.classList.remove(mediaClasses[i]);
    		}
    	}
    	imgEle.classList.add('img-editor-center');
    	console.log("Aligning center")
    } else if(dAction === "right") {
	    for(let i=0; i<mediaClasses.length; i++) {
    		if(imgEle.classList.contains(mediaClasses[i])) {
    			imgEle.classList.remove(mediaClasses[i]);
    		}
    	}
    	imgEle.classList.add('img-editor-right');
    	console.log("Aligning right")    
    }
     else if(dAction === "q-left") {
	    for(let i=0; i<qClasses.length; i++) {
    		if(imgEle.classList.contains(qClasses[i])) {
    			imgEle.classList.remove(qClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-quotes');
    } else if(dAction === "q-right") {
	    for(let i=0; i<qClasses.length; i++) {
    		if(imgEle.classList.contains(qClasses[i])) {
    			imgEle.classList.remove(qClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-quotes-right');
    } else if(dAction === "q-center") {
	    for(let i=0; i<qClasses.length; i++) {
    		if(imgEle.classList.contains(qClasses[i])) {
    			imgEle.classList.remove(qClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-quotes-center');
    } else if(dAction === "q-justify") {
	    for(let i=0; i<qClasses.length; i++) {
    		if(imgEle.classList.contains(qClasses[i])) {
    			imgEle.classList.remove(qClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-quotes-justify');
    } else if(dAction === "q-hQuote") {
	    for(let i=0; i<qClasses.length; i++) {
    		if(imgEle.classList.contains(qClasses[i])) {
    			imgEle.classList.remove(qClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-quotes-highlight');
    }
     else if(dAction === "t-head1") {
	    for(let i=0; i<txtClasses.length; i++) {
    		if(imgEle.classList.contains(txtClasses[i])) {
    			imgEle.classList.remove(txtClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-text-largest');
    } else if(dAction === "t-head2") {
	    for(let i=0; i<txtClasses.length; i++) {
    		if(imgEle.classList.contains(txtClasses[i])) {
    			imgEle.classList.remove(txtClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-text-larger');
    } else if(dAction === "t-normal") {
	    for(let i=0; i<txtClasses.length; i++) {
    		if(imgEle.classList.contains(txtClasses[i])) {
    			imgEle.classList.remove(txtClasses[i]);
    		}
    	}
    	imgEle.classList.add('editor-text-normal');
    } else if(dAction === "txt-bold") {
    	document.execCommand('bold');
    } else if(dAction === "txt-italic") {
    	document.execCommand('italic');
    }
    
  }

  /**
   * Run the app.
   */
  init();

})();