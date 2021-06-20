function displaySigninPage() { 
	let params = (new URL(document.location)).searchParams;
	var register_user = params.get("register_user");
	console.log("Display Sign Up Page => ",register_user);
	if(register_user == "true") {
		document.getElementById("signUpForm").style.display = "block";
		document.getElementById("loginForm").style.display = "none";
	}
}
window.onload = displaySigninPage;


function formValidator() {
	var pwd = document.getElementById("password-sub");
	var pwdre = document.getElementById("password-re-sub");
	if(pwd.value!="") {
		if(pwd.value != pwdre.value) {
			pwdre.classList.add('is-invalid');
			pwdre.classList.remove('is-valid');
			return false;
		} else {
			pwdre.classList.remove('is-invalid');
			pwdre.classList.add('is-valid');
			return true;
		}
	}
}

function validatePwd() {
	var error1 = false;
	var error2 = false;
	var error3 = false;
	var error4 = false;
	var pwd = document.getElementById("password-sub");
	var pwdre = document.getElementById("password-re-sub");
	var pwdl = document.getElementById("pwd-length"); 
	var pwds = document.getElementById("pwd-spChar"); 
	var pwdu = document.getElementById("pwd-uppercase"); 
	var pwdn = document.getElementById("pwd-number");
	
	if(pwd.value.length < 8) {
		error1 = true;
		pwdl.classList.add('invalid');
		pwdl.classList.remove('valid');
	} else {
		error1 = false;
		pwdl.classList.remove('invalid');
		pwdl.classList.add('valid');
	}
	
	if(!pwd.value.match(/[A-Z]/g)) {
		error2 = true;
		pwdu.classList.add('invalid');
		pwdu.classList.remove('valid');
	} else {
		error2 = false;
		pwdu.classList.remove('invalid');
		pwdu.classList.add('valid');
	}
	
	if(!pwd.value.match(/[0-9]/g)) {
		error3 = true;
		pwdn.classList.add('invalid');
		pwdn.classList.remove('valid');
	} else {
		error3 = false;
		pwdn.classList.remove('invalid');
		pwdn.classList.add('valid');
	}
	
	if(!pwd.value.match(/[@$!%*?&]/)) {
		error4 = true;
		pwds.classList.add('invalid');
		pwds.classList.remove('valid');
	} else {
		error4 = false;
		pwds.classList.remove('invalid');
		pwds.classList.add('valid');
	}
	
	if(error1 || error2 || error3 || error4) {
		pwd.classList.add('is-invalid');
		pwd.classList.remove('is-valid');
		if(pwdre.value!='') {
			this.formValidator();
		}
	} else {
		pwd.classList.remove('is-invalid');
		pwd.classList.add('is-valid');
		if(pwdre.value!='') {
			this.formValidator();
		}
	}
}

function changepwd(pwdBtn, toggle) {
	var pwd = document.getElementById(pwdBtn);
	var pwdt = document.getElementById(toggle);
	
	if(pwd.type == 'password') {
		pwd.type = 'text';
		pwdt.className = "fa fa-eye-slash";
	} else {
		pwd.type = 'password';
		pwdt.className = "fa fa-eye";
	}

	console.log(pwdBtn);
	console.log(pwd.value);
	
}
