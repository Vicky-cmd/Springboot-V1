function displayTimeoutWarning() {

	alert("Your Session is going to expire in few minutes. Kindly Save your work!");

}

function reloadPageWithSessionTimeout() {
	location.reload();
}

window.onload = sessionOnload;

function sessionOnload() {
	//alert("Test Message!");
	window.setTimeout(displayTimeoutWarning, (8000 * 1000));
	window.setTimeout(reloadPageWithSessionTimeout, (10000 * 1000));
}