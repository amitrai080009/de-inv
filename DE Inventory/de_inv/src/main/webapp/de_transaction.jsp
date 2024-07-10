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

<title>DeepShaala Transaction Page</title>

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
        var message = '<s:property value="message"/>';
        
        // Check if message is not empty and display it as an alert
        if (message) {
            alert(message);
            
            // Redirect to another action (replace with your action URL)
            window.location.href = '<s:url action="de_transaction"/>';
        }
    </script>
<!-- Display alert message of message -->

<!-- Change the options in the dropdown based on the last transaction type and we can not repeat the same actions multiple times -->

<script>
    function changeOptions() {
    	    	
        var type = document.getElementById("last_transaction").value;
        var secondDropdown = document.getElementById("second-dropdown");

        // Clear existing options
        secondDropdown.innerHTML = '';

        // Depending on the value of type, populate options
        if (type === 'IN_FOR_REPAIR') {
            var optionA = document.createElement("option");
            option.text = "OUT_SERVICE_CENTER";
            option.value = "OUT_SERVICE_CENTER";
            secondDropdown.add(optionA);

            var optionB = document.createElement("option");
            option.text = "OUT_SCHOOL_RE_ISSUED";
            option.value = "OUT_SCHOOL_RE_ISSUED";
            secondDropdown.add(optionB);
        } else if (type === 'OUT_SERVICE_CENTER') {
            var option = document.createElement("option");
            option.text = "IN_FROM_SERVICE_CENTER";
            option.value = "IN_FROM_SERVICE_CENTER";
            secondDropdown.add(option);
        } else if (type === 'IN_FROM_SERVICE_CENTER') {
            var option = document.createElement("option");
            option.text = "OUT_SCHOOL_RE_ISSUED";
            option.value = "OUT_SCHOOL_RE_ISSUED";
            secondDropdown.add(option);
        } else if (type === 'OUT_SCHOOL_RE_ISSUED') {
            var option = document.createElement("option");
            option.text = "IN_FOR_REPAIR";
            option.value = "IN_FOR_REPAIR";
            secondDropdown.add(option);
        }
        
    }
</script>

<!-- Change the options in the dropdown based on the last transaction type and we can not repeat the same actions multiple times -->

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
										<small>Add New Transaction</small>
									</h3>
									<strong><s:property value="msg" /></strong>
								</div>

							</div>

							<form id="myForm" action="fetchTransaction.action" method="get">
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<label for="validationServer01">Serial No. / IMEI</label> <input
											type="text" class="form-control"
											id="idimei" name="idimei" placeholder="IMEI" onchange="submitForm()">								
										
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
								
								<div class="form-row">
									<strong>Last Transaction:-</strong>
									
									<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Date</th>
											
											<th>Serial/IMEI no.</th>
											<th>Last Transaction</th>
											<th>From</th>
											<th>To</th>
											<th>STD</th>
											<th>Document IN/OUT</th>

										</tr>
									</thead>


									<tbody>
									
									<%
										int i = 1;
										%>
										<s:if test="record != null">
										<tr>
											<td>
													<%
													out.print(i);
													i++;
													%>
												</td>
												
											<td><s:property value="record.date"/></td>
											
											<td><s:property value="record.IMEI" /></td>
											<td><s:property value="record.type" /></td>
											<td><s:property value="record.from" /></td>
											<td><s:property value="record.to" /></td>
											<td><s:property value="record.device" /></td>
											<td><a href="<s:property value="record.filepath" />" target=""><i
													class="fa fa-file"></i></a></td>
										</tr>
										</s:if>
										</tbody>
										</table>
									
								</div>
							<!-- second from starts from here -->	
							
								<form action="addTransaction.action" method="post" enctype="multipart/form-data">
								
								<input type="hidden" name="IMEI" value="<s:property value="record.IMEI" />">
								<input type="hidden" name="device" value="<s:property value="record.device" />">
								<input type="hidden" name="from" value="<s:property value="record.to" />">
								<input type="hidden" name="to" id="textInput">
								
								<input type="hidden" name="last_transaction" value="<s:property value="record.type" />" id="last_transaction"  oninput="changeOptions()">
								
								<div class="form-row">
								<div class="col-md-4 mb-3">
										<label for="validationServer05">Transaction Type</label> 
										
										<s:if test="record.type == 'null'">
										<select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
										</select>
										</s:if>
										
										<s:if test="record.type == 'IN_FOR_REPAIR'">
										
										 <select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
											<option value="OUT_SERVICE_CENTER">OUT_SERVICE_CENTER</option>
											<option value="OUT_SCHOOL_RE_ISSUED">OUT_SCHOOL_RE_ISSUED</option>
										</select>
										
										</s:if>
										
										<s:if test="record.type == 'OUT_SERVICE_CENTER'">
										
										 <select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
											<option value="IN_FROM_SERVICE_CENTER">IN_FROM_SERVICE_CENTER</option>
										</select>
										
										</s:if>
										
										<s:if test="record.type == 'IN_FROM_SERVICE_CENTER'">
										
										 <select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
											 <option value="OUT_SCHOOL_RE_ISSUED">OUT_SCHOOL_RE_ISSUED</option>
										</select>
										</s:if>
										
										<s:if test="record.type == 'OUT_SCHOOL_RE_ISSUED'">
										
										 <select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
											 <option value="IN_FOR_REPAIR">IN_FOR_REPAIR</option>
										</select>
										
										</s:if>
										
										<s:if test="record.type == 'NEW_ISSUED_TO_SCHOOL'">
										
										 <select
											class="form-control" aria-label="Default select example" name="type" id="selectBox">
											<option selected="selected">Transaction Type</option>
											 <option value="IN_FOR_REPAIR">IN_FOR_REPAIR</option>
										</select>
										
										</s:if>

										<%-- <select id="second-dropdown" name="type" class="form-control" onchange="changeOptions()">
											<!-- Options will be dynamically populated based on the selection in the first dropdown -->
										</select> --%>

									</div>
									
									<!-- script to change the input text value based on the above dropdown selected -->
									
									<script>
									    // Get the select element
									    var selectBox = document.getElementById("selectBox");
									
									    // Get the input textbox element
									    var inputText = document.getElementById("textInput");
									
									    // Add event listener to the select element
									    selectBox.addEventListener("change", function() {
									      // Update the value of the input textbox based on the selected option
									      if (selectBox.value === "IN_FOR_REPAIR") {
									        inputText.value = "AIF Office Amreli";
									      } else if (selectBox.value === "OUT_SERVICE_CENTER") {
									        inputText.value = "Service Center";
									      } else if (selectBox.value === "IN_FROM_SERVICE_CENTER") {
									        inputText.value = "AIF Office Amreli";
									      }else if (selectBox.value === "OUT_SCHOOL_RE_ISSUED") {
									        inputText.value = "Re-Issued to School";
									      }
									    });
									  </script>
									
									
									<div class="col-md-4 mb-3">
										<label for="validationServer03">From (Particular Name)</label> <input
											type="text" class="form-control"
											id="from" name="name_from" placeholder="Issueing Person Name (Optional)">
									</div>
									<div class="col-md-4 mb-3">
										<label for="validationServer04">To (Particular Name)</label> <input
											type="text" class="form-control"
											id="to" name="name_to" placeholder="Receiving Person Name (Optional)">
									</div>
									
									<div class="col-md-4 mb-3">
										<label for="validationServer01">Transaction Date</label> 
										
										<input id="dateInput" name="date" class="date-picker form-control"
											placeholder="dd-mm-yyyy" type="date" required="required"
											type="text" onfocus="this.type='date'"
											onmouseover="this.type='date'" onclick="this.type='date'"
											onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
										<%-- <script>
											function timeFunctionLong(input) {
												setTimeout(function() {
													input.type = 'text';
												}, 60000);
											}
										</script> --%>

										<div class="valid-feedback">Looks good!</div>
									</div>
									
									<s:if test="record.type == 'NEW_ISSUED_TO_SCHOOL' || record.type == 'OUT_SCHOOL_RE_ISSUED'">
									
									
									
									<div class="col-md-2 mb-3">
										<label for="validationServer04">Item Current Status</label> 
										
										<select name="current_status" class="form-control" id="current_status"> 
											<option value="WORKING">WORKING</option>
											<option value="NOT-WORKING" selected="selected">NOT-WORKING</option>
											
										</select>


									</div>
									
									<div class="col-md-2 mb-3">
										<label for="validationServer04">Item Condition</label> 
										
										<select name="status_remarks" class="form-control" id="status_remarks"> 
											<option value="SOFTWARE-ISSUE">SOFTWARE-ISSUE</option>
											<option value="DISPLAY-TOUCH-PROBLEM">DISPLAY-TOUCH-PROBLEM</option>
											<option value="ACCESSORIES-RELATED-ISSUE">ACCESSORIES-RELATED-ISSUE</option>
											<option value="OTHER">OTHER</option>
											
										</select>


									</div>
									
									<div class="col-md-4 mb-3">
										<label for="validationServer04">Remarks/ Observations</label> 
										
										<input type="text" class="form-control" name="remarks" placeholder="Remarks/Observations">


									</div>
									
									</s:if>
									
								</div>

								<div class="form-group">
									<div class="form-check">
										<input class="form-check-input is-invalid" type="file"
											value="" id="invalidCheck3" name="file" required>
										<div class="invalid-feedback"></div>
									</div>
									
									
								</div>
								<br> <br> <br>
								<s:if test="record.type == 'RETURN_IN_STOCK'">
								<button class="btn btn-primary" type="submit" disabled="disabled">Add New
									Transaction</button><a href="de_issue_item_action.action" class="btn btn-round btn-info">Go to Issue Page</a>
									</s:if>
									<s:else>
									<button class="btn btn-primary" type="submit">Add New
									Transaction</button>
									</s:else>
							</form>
						</div>
						<br>
					</div>
					<br>
				</div>
				
				<!-- /top tiles -->

				<div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							<div class="row x_title">
								<div class="col-md-6">
									<h3>
										<small>Transaction Details</small>
									</h3>
								</div>
								<div class="col-md-6">
									<div id="reportrange" class="pull-right"
										style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
										<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> <span>December
											30, 2014 - January 28, 2015</span> <b class="caret"></b>
									</div>
								</div>
							</div>

							<div class="col-md-12 col-sm-12 ">
								<!-- Stock Master summary report will be displayed here -->

								<table id="datatable"
									class="table" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Date</th>
											
											<th>Serial/IMEI no.</th>
											<th>Status</th>
											<th>From</th>
											<th>To</th>
											<th>STD</th>
											<th>Document IN/OUT</th>

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
													out.print(i);
													i++;
													%>
											</td>
											<td><s:property value="date" /></td>
											
											<td><s:property value="IMEI" /></td>
											<td><s:property value="type" /></td>
											<td><s:property value="from" /></td>
											<td><s:property value="to" /></td>
											<td><s:property value="device" /></td>
											<td><a href="<s:property value="fileFileName"/>" target=""><i
													class="fa fa-file"></i></a></td>
										</tr>
										</s:iterator>
									</tbody>
								</table>


								<!-- Stock Master summary report will be displayed here -->
							</div>

							<div class="clearfix"></div>
						</div>
					</div>

				</div>
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
