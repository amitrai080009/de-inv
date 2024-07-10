<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<% 
response.setHeader("X-XSS-Protection", "1; mode=block");
//response.setHeader("Content-Security-Policy", "default-src *; style-src 'self' 'unsafe-inline'; script-src 'self' 'unsafe-inline' 'unsafe-eval'");
response.setHeader("Content-Security-Policy", "default-src * self blob: data: gap:; style-src * self 'unsafe-inline' blob: data: gap:; script-src * 'self' 'unsafe-eval' 'unsafe-inline' blob: data: gap:; object-src * 'self' blob: data: gap:; img-src * self 'unsafe-inline' blob: data: gap:; connect-src self * 'unsafe-inline' blob: data: gap:; frame-src * self blob: data: gap:;");
//Comment the below line:   
response.addHeader( "X-FRAME-OPTIONS", "SAMEORIGIN" );
%>

<!DOCTYPE html>

<html lang="en">
<!-- BEGIN HEAD -->
<head>
<link href="./Content/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="./Content/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script defer
	src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<!-- Google reCaptcha script here -->

<link href="./css/login.css" rel="stylesheet" media="screen">
<script src="./js/login.js"></script>
<!-- password hashing script -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
<!--Disable cut copy paste for Username -->
<!-- Import CryptoJS library for hashing -->
<!--Disable cut copy paste for Username -->
<!-- Captcha front page script -->

<meta charset="UTF-8">
<title>Login</title>
</head>

<body>
	<div class="container">
		<div class="col-md-12 logo">
			<a href="#"><img src="./Content/img/AIF_Logo.jpg" alt="logo"></a>
		</div>
		<div class="col-md-12 box ">
			<h2>Login</h2>
		<form id="loginForm" action="login-action" method="post">
			<span id="lblMessage" style="color: red;"></span>
				<div class="login-form">
					<label for="username">UserName</label> <input name="username"
						type="text" id="username" placeholder="Username"
						required="required" autocomplete="off"
						oncopy="return disableCopy();" onpaste="return disablePaste();"
						oncut="return disableCut();"
						oncontextmenu="return disableContextMenu();" /><br /> <label
						for="password">Password</label><br /> <input name="password"
						type="password" id="password" placeholder="Password"
						required="required" autocomplete="off"
						oncopy="return disableCopy();" onpaste="return disablePaste();"
						oncut="return disableCut();"
						oncontextmenu="return disableContextMenu();" />

					<!-- Captcha in form applications -->
					<h6>Captcha:</h6>
					<input type="text" name="captcha" oncopy="return disableCopy();"
						onpaste="return disablePaste();" oncut="return disableCut();"
						oncontextmenu="return disableContextMenu();" /> <img id="capImg"
						src="loadCaptcha" />
					<button type="button" onclick="generateNewCaptcha();">Refresh</button>
				</div>
				<div>
					<input type="submit" name="login" value="Submit" id="submit"
						onclick="submitForm()" />
				</div>
				<div>
					
				</div>
			</form>
		</div>
		<div class="col-md-12 ">
			<h4>
				<s:property value="msg" />
			</h4>
			 <h4>
				<s:property value="username" />
			</h4> 
			<h4>
				<s:property value="password" />
			</h4>
		</div>
	</div>
</body>
</html>
