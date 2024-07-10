<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/AIF_Logo.jpg" type="image/ico" />

<title>DeepShaala Search Item Page</title>

<!-- Bootstrap -->
<link href="cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

<link href="./vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="./vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="./vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="./vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link
	href="./vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet">
<!-- JQVMap -->
<link href="./vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link href="./vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Datatables -->

<link
	href="./vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="./vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="./vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="./vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="./vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="./build/css/custom.min.css" rel="stylesheet">

<!-- java script code to fetch the details when entering iMEI no. in textbox -->

<script>
	function submitForm() {
		document.getElementById('myForm').submit();
	}	
</script>


<!-- java script code to fetch the details when entering iMEI no. in textbox -->

<!-- script to set the selected date as today automatically -->

<script>
    // Get today's date
    var today = new Date();

    // Format date as dd mm yyyy
    var formattedDate = today.getDate() + ' ' + (today.getMonth() + 1) + ' ' + today.getFullYear();

    // Set the value of the date input to today's date
    document.getElementById("dateInput").value = formattedDate;
  </script>
  
  <!-- script to set the selected date as today automatically -->
  
  <!-- Display alert message of message -->
<script>
        // Use Struts2 tag to retrieve the message
        var message = '<s:property value="msg"/>';
        
        // Check if message is not empty and display it as an alert
        if (message) {
            alert(message);
            
            // Redirect to another action (replace with your action URL)
            window.location.href = '<s:url action="de_return_item"/>';
        }
    </script>
<!-- Display alert message of message -->


</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="" class="site_title"><i class="fa fa-bar-chart"></i>
							<span>DE Inventory</span></a>
					</div>

					<div class="clearfix"></div>
					<br />

					<!-- sidebar menu -->

					<jsp:include page="dehome_sidebarmenu.jsp" />

					<!-- /sidebar menu -->
		
				</div>
			</div>

			<!-- top navigation -->

			<jsp:include page="dehome_topnav.jsp" />

			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->

				<div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							<div class="row x_title" style="background-color:#f0f5f1; color: black;">
								<div class="col-md-6">
									<h3>
										<small>Search Item by IMEI No.</small>
									</h3>
									<strong><s:property value="msg" /></strong>
								</div>

							</div>

							<form id="myForm" action="fetchIssueTransaction.action" method="get">
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<label for="validationServer01"></label> <input
											type="text" class="form-control"
											id="idimei" name="idimei" placeholder="Serial No. / IMEI"> <!-- onchange="submitForm()" -->							
										
									<!-- <input type="submit" value="search record" class="btn btn-info"> -->
								
									</div>
									
									<div class="col-md-4 mb-3" style="padding-top: 18px;">
									<label for="validationServer01"></label>
										<input type="button" class="btn btn-primary"
											id="btn" name="btn" onclick="submitForm()" value="Search">								
										
									<!-- <input type="submit" value="search record" class="btn btn-info"> -->
								
									</div>
									
							<!-- Make IMEI textbox autofocus scripts -->
									<script>
										window.onload = function() {
											document.getElementById('idimei').focus();
										};
									</script>
							<!-- Make IMEI textbox autofocus scripts -->

								</div>
							</form>	
								
								<div class="form-row" id="myDiv">
									<div style="font-size: 15px;"><strong><b>Item Issue Details:-</b></strong></div>
									
									<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Issue Date</th>
											<th>IMEI</th>
											<th>CC Name</th>
											<th>School Name</th>
											<th>Student Name</th>
											<th>UDise</th>
											<th>Brand</th>
											<th>Issue Doc</th>
											<!-- <th>Issued by</th> -->

										</tr>
									</thead>


									<tbody>
									
									<%
										int i = 1;
										%>
										<s:iterator value="itemList">
										<tr>
											<td>
													<%
													out.print(i);
													i++;
													%>
												</td>
												
											<td><s:property value="date"/></td>
											<td><s:property value="imei" /></td>
											<td><s:property value="cc_name" /></td>
											<td><s:property value="school_name" /></td>
											<td><s:property value="student_name" /></td>
											<td><s:property value="udise" /></td>
											<td><s:property value="brand" /></td>
											<td><a href="<s:property value="fileFileName" />" target=""><i
													class="fa fa-file"></i></a></td>
											<!-- <td></td> -->
										</tr>
										</s:iterator>
										</tbody>
										</table>
									
								</div>
							<!-- second from starts from here -->	
							
							<hr/>
							<div class="form-row" id="myDiv">
									<div style="font-size: 15px;"><strong><b>Transaction details:-</b></strong></div>
									
									<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Date</th>
											<th>Device</th>
											<th>Serial/IMEI no.</th>
											<th>Last Transaction</th>
											
											<th>From</th>
											<th>To</th>
											<th>Document IN/OUT</th>
											<!-- <th>Transaction by</th> -->
											<th>Transaction Date/Time</th>

										</tr>
									</thead>


									<tbody>
									
									<%
										int j = 1;
										%>
										<s:iterator value="listT">
										<tr>
											<td>
													<%
													out.print(j);
													j++;
													%>
												</td>
												
											<td><s:property value="date"/></td>
											<td><s:property value="device" /></td>
											<td><s:property value="IMEI" /></td>
											<td><s:property value="type" /></td>
											
											<td><s:property value="from" /></td>
											<td><s:property value="to" /></td>
											<td><a href="<s:property value="filepath" />" target=""><i
													class="fa fa-file"></i></a></td>
											<!-- <td></td> -->
											<td><s:property value="publish_date" /></td>
										</tr>
										</s:iterator>
										</tbody>
										</table>
									
								</div>
							
							
							</div>
						<br>
					</div>
					<br>
				</div>
				<hr style="height: 2px;">
				<br>
				<!-- /top tiles -->

								<br />
		</div>
		</div>
		<!-- /page content -->

		<!-- footer content -->
		<footer>
			<div class="pull-right">
				<a></a>
			</div>
			<div class="clearfix"></div>
		</footer>
		<!-- /footer content -->
	</div>
	</div>

	<!-- jQuery -->
	<script src="./vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="./vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="./vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="./vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="./vendors/Chart.js/dist/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="./vendors/gauge.js/dist/gauge.min.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="./vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="./vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="./vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="./vendors/Flot/jquery.flot.js"></script>
	<script src="./vendors/Flot/jquery.flot.pie.js"></script>
	<script src="./vendors/Flot/jquery.flot.time.js"></script>
	<script src="./vendors/Flot/jquery.flot.stack.js"></script>
	<script src="./vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="./vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="./vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="./vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="./vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="./vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="./vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="./vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="./vendors/moment/min/moment.min.js"></script>
	<script src="./vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Datatables -->
	<script src="./vendors/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="./vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="./vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="./vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
	<script
		src="./vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
	<script
		src="./vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
	<script
		src="./vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="./vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
	<script
		src="./vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
	<script src="./vendors/jszip/dist/jszip.min.js"></script>
	<script src="./vendors/pdfmake/build/pdfmake.min.js"></script>
	<script src="./vendors/pdfmake/build/vfs_fonts.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="./build/js/custom.js"></script>

</body>
</html>
