<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="com.upmscl.service.*"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UPMSCL</title>
<link rel="stylesheet" href="css/home_style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

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
						<a href="#">
							<h3>
								<a href="home">UPMSCL</a>
							</h3>
						</a>
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

			<div class="section0">
				<h3>
					<i class="fa-solid fa-house"></i> UPMSCL Dashboard
				</h3>
			</div>

			<div class="section1">
				<div class="box box1">
					<div class="box1_head">Lorem</div>
					<div class="box_cont box1_cont">
						<i class="fa-solid fa-circle-right"></i> 100%
					</div>
				</div>
				<div class="box box2">
					<div class="box2_head">Lorem</div>
					<div class="box_cont box2_cont">
						<i class="fa-solid fa-circle-right"></i> 100%
					</div>
				</div>
				<div class="box box3">
					<div class="box3_head">Lorem</div>
					<div class="box_cont box3_cont">
						<i class="fa-solid fa-circle-right"></i> 100%
					</div>
				</div>
				<div class="box box4">
					<div class="box4_head">Lorem</div>
					<div class="box_cont box4_cont">
						<i class="fa-solid fa-circle-right"></i> 100%
					</div>
				</div>
			</div>

			<div class="section2">

				<div class="section2_a">
					<h3>Tender Details</h3>
					<table>
						<tr>
							<th>Sr.no.</th>
							<th>Tender no.</th>
							<th>Description</th>
							<th>Category</th>
							<th>Tender Start</th>
							<th>Tender End</th>
							<th>Document</th>
							<th>Edit</th>
						</tr>
						<%
						int i = 1;
						%>
						<s:iterator value="tenderList">
							<tr>
								<td style="width: 5%">
									<%
									out.print(i);
									i++;
									%>
								</td>
								<td style="width: 13%"><s:property value="tender_no" /></td>
								<td style="width: 25%"><s:property value="title" /></td>
								<td style="width: 12%"><s:property value="tender_cat" /></td>
								<td style="width: 12%"><s:property value="tender_start" /></td>
								<td style="width: 12%"><s:property value="tender_end" /></td>
								<td style="width: 8%"><a target="_blank"
									href="<s:property value="fileFileName"/>"><i
										class="fas fa-file-pdf"></i></a></td>
								<td style="width: 8%">
									<form name="form" method="POST" action="get-tender">
										<input type="hidden" name="id"
											value="<s:property value="id"/>"> <input
											type="submit" name="submit" onsubmit="updateInfo()"
											value="Edit" class="btn-primary">
									</form>
								</td>

							</tr>
						</s:iterator>

					</table>
				</div>


				<div class="section2_b">
					<h3>User Profile</h3>
					<div class="profile">
						<img src="img/bot_img.jpg">
						<h4>name - lorem ipsum</h4>
						<p>designation - lorem</p>
					</div>
				</div>

			</div>

		</div>

	</main>
</body>
</html>