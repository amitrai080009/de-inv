<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<!-- Auto popuate Search -->


<!-- auto populate search -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	type="text/javascript"></script>
<style type="text/css">
.suggestions {
	border: 1px solid #ccc;
	position: absolute;
	z-index: 999;
	color: black;
	width: 403px;
	background-color: #fff;
}

.suggestions div {
	padding: 10px;
	cursor: pointer;
}

.suggestions div:hover {
	background-color: #f0f0f0;
	color: green;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#search-box')
								.on(
										'input',
										function() {
											var query = $(this).val();
											if (query.length > 2) {
												$
														.ajax({
															url : 'searchProducts',
															type : 'GET',
															data : {
																query : query
															},
															success : function(
																	data) {
																var suggestions = $('#suggestions');
																suggestions
																		.empty();
																$
																		.each(
																				data,
																				function(
																						index,
																						product) {
																					suggestions
																							.append('<div onclick="selectProduct(\''
																									+ product.name
																									+ '\')">'
																									+ product.name
																									+ '</div>');
																				});
															}
														});
											} else {
												$('#suggestions').empty();
											}
										});
					});

	function selectProduct(name) {
		$('#search-box').val(name);
		$('#suggestions').empty();
	}
</script>

<!-- Cascading dropdown -->

 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#country').change(function(){
                var countryId = $(this).val();
                $.ajax({
                    url: 'getStates',
                    type: 'POST',
                    data: {countryId: countryId},
                    success: function(data) {
                        var states = $('#state');
                        states.empty();
                        states.append('<option value="">Select School</option>');
                        $.each(data, function(index, state) {
                            states.append('<option value="'+state.id+'">'+state.name+'</option>');
                        });
                    }
                });
            });

            $('#state').change(function(){
                var stateId = $(this).val();
                $.ajax({
                    url: 'getCities',
                    type: 'POST',
                    data: {stateId: stateId},
                    success: function(data) {
                        var cities = $('#city');
                        cities.empty();
                        cities.append('<option value="">Select Student Name</option>');
                        $.each(data, function(index, city) {
                            cities.append('<option value="'+city.id+'">'+city.name+' - '+city.UDISE+'</option>');
                        });
                    }
                });
            });
        });
    </script>

<!-- Cascading dropdown -->

<!-- generating dynamic form fields -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <script>
        $(document).ready(function () {
            // Initialize suggestion for search-box
            $("#form-container").on("focus", ".search-box", function () {
                $(this).autocomplete({
                    source: ["suggestion1", "suggestion2", "suggestion3"] // Example suggestions
                });
            });

            // Initialize dropdown for student_name
            $("#form-container").on("change", ".student-name", function () {
                // Your code to handle dropdown change event
            });
        });

        function addFormRow() {
            var formRow = $("#form-row").clone();
            formRow.find(".search-box").val(""); // Clear value of search-box
            formRow.find(".student-name").val(""); // Clear value of student-name
            $("#form-container").append(formRow);
        }
    </script>
    
<!-- generating dynamic form fields -->

<!-- Display alert message of message -->
<script>
        // Use Struts2 tag to retrieve the message
        var message = '<s:property value="msg"/>';
        
        // Check if message is not empty and display it as an alert
        if (message) {
            alert(message);
            
            // Redirect to another action (replace with your action URL)
            window.location.href = '<s:url action="de_issue_item_action"/>';
        }
    </script>
<!-- Display alert message of message -->

<%-- <script>
	function submitForm() {
		document.getElementById('myForm').submit();
	}	
</script> --%>

<!-- Submit Form without Page load -->

<!-- Submit Form without Page load -->


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

					<!-- menu profile quick info -->

					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->

					<jsp:include page="dehome_sidebarmenu.jsp" />

					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->

					<!-- /menu footer buttons -->
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

							<div class="row x_title">
								<div class="col-md-6">
									<h3>
										<small>Item Issue Report</small>
									</h3>
									<strong><s:property value="msg" /></strong>
								</div>

							</div>

							<div id="form-container">
							<form action="issue_item_report.action" method="get" id="myForm">
								<div class="form-row">
									
									<div class="col-md-6 mb-3">
<!-- 										<label for="validationServer02">CC Name</label> 
 -->									
										
										<select id="country" name="cc_name"
											class="form-control" aria-label="Default select example">
											<option value="" selected="selected">Please select CC Name</option>
											<s:iterator value="countries">
											<option value="<s:property value="id" />"><s:property value="name" /></option>
											</s:iterator>
										</select>
										
										<div class="valid-feedback">Looks good!</div>
									</div>
									<div class="col-md-6 mb-3">
<!-- 										<label for="validationServerUsername">Select School Name</label>
 -->										<div class="input-group">
											<select class="form-control" id="state" name="school_name"
												aria-label="Default select example">
												<option>Please select School Name</option>
												<option value="" ></option>
												
											</select>
										</div>
									</div>
								</div>
								
								
       								 <!-- Initial form row -->
								<div class="form-row" id="form-row">
									
									
									<%-- <div class="col-md-6 mb-3">
										<label for="validationServer05">Student Name</label> <select  id="city" name="student_name"
											class="form-control" aria-label="Default select example">
											<option>Student Name</option>
											<option value="">Student Name -
												UDIN No.</option>
										</select>
									</div> --%>
									
									<div class="col-md-6 mb-3 custom-dropdown">
<%-- 										<label for="validationServer04">STD/CLASS</label>  --%>
											<select name="std"
											class="form-control" aria-label="Default select example">
											<option value="TABLET" selected="selected">Select Standard</option>
											<option value="7">7th STD</option>
											<option value="8">8th STD</option>
										</select>

										<div class="invalid-feedback">Brand Name</div>
									</div>
									
									<div class="col-md-6 mb-3">
										<!-- <label for="validationServer05">Student Name</label> --> 
										<input type="submit" class="form-control btn btn-round btn-info" value="Generate Report">
									</div>
									
								</div>
								
								<!-- <div style="align-content: center;">
									<input type="submit" class="btn btn-primary" value="Generate Report">
								-->
								
								<strong><s:property value="msg" /></strong>
 								</form>
 								</div>
						</div>
						<br>
					</div>
					<br>
				</div>
				<!-- /top tiles -->

				<div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							

							<div class="col-md-12 col-sm-12 ">
								<!-- Item Issue Master summary report will be displayed here -->

								<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Issue Date</th>
											<th>IMEI/ Serial no.</th>
											<th>CC Name</th>
											<th>School Name</th>
											<th>Student Name</th>
											<th>UDISE</th>
											<th>STD</th>
											<th>Document</th>
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
												<td><s:property value="date" /></td>
												<td><s:property value="imei" /></td>
												<td><s:property value="cc_name" /></td>
												<td><s:property value="school_name" /></td>
												<td><s:property value="student_name" /></td>
												<td><s:property value="udise" /></td>
												<td><s:property value="desc" /></td>
												<td><a target="_blank"
													href="<s:property value="fileFileName"/>"><i
														class="fas fa-file-pdf-o"></i>Download</a></td>
												
											</tr>
										</s:iterator>

									</tbody>
								</table>


								<!-- Item Issue Master summary report will be displayed here -->
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
	<div></div>

	<!-- jQuery -->
	<script src="./vendors/jquery/dist/jquery.min.js"
		type="text/javascript"></script>
	<!-- Bootstrap -->
	<script src="./vendors/bootstrap/dist/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>
	<!-- FastClick -->
	<script src="./vendors/fastclick/lib/fastclick.js"
		type="text/javascript"></script>
	<!-- NProgress -->
	<script src="./vendors/nprogress/nprogress.js" type="text/javascript"></script>
	<!-- Chart.js -->
	<script src="./vendors/Chart.js/dist/Chart.min.js"
		type="text/javascript"></script>
	<!-- gauge.js -->
	<script src="./vendors/gauge.js/dist/gauge.min.js"
		type="text/javascript"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="./vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"
		type="text/javascript"></script>
	<!-- iCheck -->
	<script src="./vendors/iCheck/icheck.min.js" type="text/javascript"></script>
	<!-- Skycons -->
	<script src="./vendors/skycons/skycons.js" type="text/javascript"></script>
	<!-- Flot -->
	<script src="./vendors/Flot/jquery.flot.js" type="text/javascript"></script>
	<script src="./vendors/Flot/jquery.flot.pie.js" type="text/javascript"></script>
	<script src="./vendors/Flot/jquery.flot.time.js" type="text/javascript"></script>
	<script src="./vendors/Flot/jquery.flot.stack.js"
		type="text/javascript"></script>
	<script src="./vendors/Flot/jquery.flot.resize.js"
		type="text/javascript"></script>
	<!-- Flot plugins -->
	<script src="./vendors/flot.orderbars/js/jquery.flot.orderBars.js"
		type="text/javascript"></script>
	<script src="./vendors/flot-spline/js/jquery.flot.spline.min.js"
		type="text/javascript"></script>
	<script src="./vendors/flot.curvedlines/curvedLines.js"
		type="text/javascript"></script>
	<!-- DateJS -->
	<script src="./vendors/DateJS/build/date.js" type="text/javascript"></script>
	<!-- JQVMap -->
	<script src="./vendors/jqvmap/dist/jquery.vmap.js"
		type="text/javascript"></script>
	<script src="./vendors/jqvmap/dist/maps/jquery.vmap.world.js"
		type="text/javascript"></script>
	<script src="./vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"
		type="text/javascript"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="./vendors/moment/min/moment.min.js" type="text/javascript"></script>
	<script src="./vendors/bootstrap-daterangepicker/daterangepicker.js"
		type="text/javascript"></script>

	<!-- Datatables -->
	<script src="./vendors/datatables.net/js/jquery.dataTables.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-buttons/js/dataTables.buttons.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"
		type="text/javascript"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.flash.min.js"
		type="text/javascript"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.html5.min.js"
		type="text/javascript"></script>
	<script src="./vendors/datatables.net-buttons/js/buttons.print.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-responsive/js/dataTables.responsive.min.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"
		type="text/javascript"></script>
	<script
		src="./vendors/datatables.net-scroller/js/dataTables.scroller.min.js"
		type="text/javascript"></script>
	<script src="./vendors/jszip/dist/jszip.min.js" type="text/javascript"></script>
	<script src="./vendors/pdfmake/build/pdfmake.min.js"
		type="text/javascript"></script>
	<script src="./vendors/pdfmake/build/vfs_fonts.js"
		type="text/javascript"></script>

	<!-- Custom Theme Scripts -->
	<script src="./build/js/custom.js" type="text/javascript"></script>

</body>
</html>
