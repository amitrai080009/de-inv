
	// Function to hash password using SHA-256
	function hashPassword(password) {
		return CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex);
	}

	// Function to submit the form after hashing the password
	function submitForm() {
		var password = document.getElementById('password').value;
		//var username = document.getElementById('username').value;
		
		var hashedPassword = hashPassword(password);
		//var hashedUsername = hashPassword(username);
		
		document.getElementById('password').value = hashedPassword;
		//document.getElementById('username').value = hashedUsername;
		return true; // Allow the form to submit
	}

	// Add event listener to the form submission
	document.getElementById('loginForm').addEventListener('submit', submitForm);


// Disable cut copy paste
	function disableCopy() {
		alert("You cannot perform Copy");
		return false;
	}

	function disablePaste() {
		alert("You cannot perform Paste");
		return false;
	}

	function disableCut() {
		alert("You cannot perform Cut");
		return false;
	}

	function disableContextMenu() {
		alert("You cannot perform right click via mouse as well as keyboard");
		return false;
	}

//Generating captcha
	function generateNewCaptcha() {
		document.getElementById("capImg").setAttribute("src", "");
		document.getElementById("capImg").setAttribute("src",
				"loadCaptcha?date=" + new Date());
	}
