<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UPMSCL</title>
<link rel="stylesheet" href="./css/home_style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" href="./css/style_form.css">

<script type="text/javascript">
	function disableBackButton() {
		window.history.forward();
	}
	disableBackButton();
	window.onload = disableBackButton();
	window.onpageshow = function(evt) {
		if (evt.persisted)
			disableBackButton();
	};
	window.onunload = function() {
		void (0);
	};
</script>
</head>
<body>
	<header>
		<nav>
			<div class="navbar">
				<div class="top_nav">
					<div class="top_nav1">
						<a href="home">UPMSCL</a>
					</div>
					<div class="top_nav2">
						<h3>
							Welcome: <i><s:property value="%{#session['loggedUserName']}" /></i>
						</h3>
						<a href="logout" style=""> <i class="icon-switch2"></i>Logout
						</a>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<main>

		<article class="side_menu">
			<div class="side_bar">
				<div class="side1">
					<div class="side side2_img">
						<img src="img/logo_home.png" alt="">
					</div>
					<s:include value="admin-sidebar.jsp" />
				</div>
			</div>
		</article>
		<div class="main_sec">
			<div class="section1">
				<main class="demo-page-content">
					<section>
						<div class="href-target" id="input-types"></div>
						<h1>Add Tender Details</h1>

						<form method="post" action="upload-tender"
							enctype="multipart/form-data">
							<div>

								<div class="nice-form-group">
									<ul>
										<li><strong><label>Tender Number</label></strong></li>
									</ul>
									<input type="text" name="tender_no" size="100"
										placeholder="Tender Number" required>

								</div>
								<div class="nice-form-group">
									<ul>
										<li><strong><label>Description</label></strong></li>
									</ul>
									<input type="text" name="title" size="100"
										placeholder="Description" required>

								</div>
								<div class="nice-form-group">
									<ul>
										<li><strong><label>Tender Start Date</label></strong></li>
									</ul>
									<input name="tender_start" class="datepicker"
										placeholder="Start Date (mm/dd/yyyy)" type="date"
										autocomplete="off" required />

								</div>
								<div class="nice-form-group">
									<ul>
										<li><strong><label>Tender Start Date</label></strong></li>
									</ul>
									<input name="tender_end" class="datepicker"
										placeholder="End Date (mm/dd/yyyy)" type="date"
										autocomplete="off" required />

								</div>
								<div class="nice-form-group">
									<ul>
										<li><strong><label>Tender Category</label></strong></li>
									</ul>
									<select name="tender_cat" id="cat"
										style="text-transform: uppercase" required>
										<option value="">Select Tender Category</option>
										<option value="Equipment">Equipment</option>
										<option value="Drugs">Drugs</option>
										<option value="Consumables">Consumables</option>
										<option value="Other">Other</option>
									</select>
								</div>


								<div class="nice-form-group" id="imgPreviewDivRefresh">
									<input type="file" name="file" required
										value="Choose Tender Document" />

								</div>
								<div class="nice-form-group">
									<input type="submit" name="upload" value="Upload" id="submit">
									<strong><s:property value="msg" /></strong>
								</div>
							</div>

						</form>

					</section>
				</main>
			</div>

		</div>
	</main>
</body>
</html>