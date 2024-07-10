
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.upmscl.service.*"%>
<%@page import="com.upmscl.dao.*"%>



<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="css/home_style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

<!-- Global stylesheets -->



<link href="./Content/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="./Content/css/core.css" rel="stylesheet" type="text/css" />

<link href="./Content/css/components.css" rel="stylesheet" />

<!-- start here data tables pagination  -->
<link
	href="https://cdn.datatables.net/2.0.2/css/dataTables.dataTables.css"
	rel="stylesheet" />


<!-- ends here data tables pagination -->

<title>Tender</title>

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
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
	<div class="navbar navbar-default header-highlight">
		<div class="navbar-header login_logo">
			<a class="navbar-brand" href="index.html"> </a> <a
				class="navbar-brand"> <span
				style="color: #fff; text-transform: uppercase; letter-spacing: 1px; font-size: 22px;">UPMSC</span></a>
		</div>
		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav">
				<li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i
						class="icon-paragraph-justify3"></i></a></li>
			</ul>
			<div class="navbar-right"
				style="padding-top: 15px; float: right; font-weight: bold;">
				Welcome: <a href="#" style="color: aqua;"><s:property
						value="%{#session['loggedUserName']}" /></a> <a href="logout"
					style="color: red;"> Logout</a>
			</div>

		</div>

	</div>
	<!-- Page container -->
	<div class="page-container">
		<!-- Page content -->

		<!-- Main sidebar -->
		<div class="sidebar sidebar-main">

			<s:include value="admin-sidebar.jsp" />

		</div>
		<!-- /main sidebar -->
		<!-- Page container -->

		<!-- Page content -->
		<main>


			<div class="main_sec">

				<div class="section0">
					<h3>
						<i class="fa-solid fa-house"></i> UPMSCL Dashboard
					</h3>
				</div>

				<div class="section1">
					<div class="box box1">
						<div class="box1_head">Tender</div>
						<div class="box_cont box1_cont">
							<i class="fa-solid fa-chart-line"></i> <s:property value="tenderList.size()" /><!-- Display List size -->
						</div>
					</div>
					<div class="box box2">
						<div class="box2_head">Circular</div>
						<div class="box_cont box2_cont">
							<i class="fa-solid fa-chart-simple"></i> <s:property value="circularList.size()" />
						</div>
					</div>
					<div class="box box3">
						<div class="box3_head">Corrigemndum</div>
						<div class="box_cont box3_cont">
							<i class="fa-solid fa-chart-bar"></i> <s:property value="amendList.size()" />
						</div>
					</div>
					<div class="box box4">
						<div class="box4_head">Blacklisting</div>
						<div class="box_cont box4_cont">
							<i class="fa-solid fa-chart-area"></i> <s:property value="blacklistingsList.size()" />
						</div>
					</div>
				</div>

				<div class="section2">

					<div class="section2_a">
						<h3>Tender Details</h3>
						<table id="example" class="display compact" style="width: 98%">
							<thead>
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
							</thead>
							<%
							int i = 1;
							%>

							<tbody>
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
							</tbody>


						</table>
					</div>


					<div class="section2_b" style=" padding: 10px;">
						<h3>Circular</h3>


						
								<s:iterator begin="0" end="6" value="circularList">
									<i class="fa-regular fa-circle-dot"></i> <a target="_blank" style="color: black;"
										href='<s:property value="fileFileName"/>'><s:property
												value="title" /></a>
									<hr style="padding: 0px;">

								</s:iterator>


					</div>

				</div>

			</div>

		</main>


		<!-- /page content -->
	</div>
	<!-- /page container -->
	<!-- /main content -->

	<!-- Footer -->
	<div class="footer text-muted">
		&copy; 2018. <a href="#">UPMSC</a>
	</div>
	<!-- /footer -->
	<!-- /page content -->

	<!-- /page container -->
	<!--JS files -->

	<script type="text/javascript" src="./Content/js/app.js"></script>
	<!-- Pagination -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>

	<script type="text/javascript">
		new DataTable('#example', {
			pagingType : 'simple_numbers'
		});
	</script>
</body>
</html>

