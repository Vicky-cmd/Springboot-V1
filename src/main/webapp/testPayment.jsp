<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/header.jsp" >
<c:param name="title" value="Home"/>
</c:import>
<script type="text/javascript" src="/assets/js/homepage.js"></script>
<script src="/assets/js/session.js" type="text/javascript"></script>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
	function completePayment(e) {
		var ele = e;
		var paymentEditField = document.getElementById("testPayment");
		var amount = paymentEditField.value;
		var userId = document.getElementById("userIdForPayment").value;
		var cartId = document.getElementById("cartIdForPayment").value;
		var xhttp = new XMLHttpRequest();
		var jsonReq = {};
		jsonReq.amount = amount;
		jsonReq.userId = userId;
		jsonReq.cartId = cartId;
		console.log(jsonReq);
		console.log(JSON.stringify(jsonReq));
		xhttp.open("POST", "/payments/createOrder");
		xhttp.send(JSON.stringify(jsonReq));
		console.log("Sent Request!");
		xhttp.addEventListener("load", function (event) {
	    	console.log(this);
	    	console.log(this.responseText);
	    	var jsonResp = JSON.parse(this.responseText);
	    	if(jsonResp.status !== null && jsonResp.status === 'Success') {
	    		console.log(amount);
	    		var options = {
	    			    "key": "rzp_test_1OnHyQf62f3JNR", // Enter the Key ID generated from the Dashboard
	    			    "amount": amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
	    			    "currency": "INR",
	    			    "name": "InfoTrends Corp",
	    			    "description": "Test Transaction",
	    			    "image": "https://example.com/your_logo",
	    			    "order_id": jsonResp.id, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
	    			    "callback_url": "http://localhost:8081/payments/redirectURL",
	    			    "prefill": {
	    			        "name": "Gaurav Kumar",
	    			        "email": "gaurav.kumar@example.com",
	    			        "contact": "9999999999"
	    			    },
	    			    "notes": {
	    			        "address": "Razorpay Corporate Office"
	    			    },
	    			    "theme": {
	    			        "color": "#3399cc"
	    			    }
	    			};
	    			var rzp1 = new Razorpay(options);
	    			rzp1.open();
	    		    ele.preventDefault();
	    	}
		});
    }
</script>
<br>
<%! HttpSession newSession = null; %>
<% 
	newSession = request.getSession();
	if(newSession!=null && newSession.getAttribute("username")!=null && !newSession.getAttribute("username").toString().isEmpty()) {
		out.print("<h3 style='margin: auto; width: fit-content;'>Welcome " + newSession.getAttribute("fullname") + "!</h3><br><br><br>");
	}

%>

<div align="center">
	<input type="text" value="1" id="userIdForPayment" disabled>
	<input type="text" value="1" id="cartIdForPayment" disabled>
	<br><br>
	<input type="number" id="testPayment" name="testPayment"/>
	<button type="button" onclick="completePayment()">Pay</button>
</div>
<c:import url="/include/footer.jsp" />