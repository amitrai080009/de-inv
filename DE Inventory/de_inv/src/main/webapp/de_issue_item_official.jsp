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

<title>DeepShaala Issue Item to Officials</title>

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


<!-- Generate Other textbox if other option selected in official type dropdown box -->

<script>
function showTextBox() {
    var dropdown = document.getElementById("myDropdown");
    var textbox = document.getElementById("myTextbox");
    if (dropdown.value === "Other") {
        textbox.style.display = "block";
    } else {
        textbox.style.display = "none";
    }
}
</script>

<!-- Generate Other textbox if other option selected in official type dropdown box -->


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
										<small>Tablet/Item Issue Form to Officials and Others</small>
									</h3>
									<strong><s:property value="msg" /></strong>
								</div>

							</div>

							<form action="de_issue_official.action" method="post" enctype="multipart/form-data">
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<label for="validationServer01">Issue Date</label> 
										
										<input id="date" name="date" class="date-picker form-control"
											placeholder="dd-mm-yyyy" type="date" required="required"
											type="text" onfocus="this.type='date'"
											onmouseover="this.type='date'" onclick="this.type='date'"
											onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
										<script>
											function timeFunctionLong(input) {
												setTimeout(function() {
													input.type = 'text';
												}, 60000);
											}
										</script>

										<div class="valid-feedback">Looks good!</div>
									</div>
									<div class="col-md-4 mb-3">
										<label for="validationServer02">Name of the Official</label> 
									
										
										<input type="text" name="official_name" placeholder="Official Name" required="required" class="form-control">
										
										<div class="valid-feedback">Looks good!</div>
									</div>
									<div class="col-md-4 mb-3">
										<label for="validationServerUsername">Official Designation</label>
										<div class="input-group">
											<input type="text" name="designation" placeholder="Official Designation" required="required" class="form-control">
										</div>
									</div>
								</div>
								
								<div id="form-container">
       								 <!-- Initial form row -->
								<div class="form-row" id="form-row">
									<div class="col-md-4 mb-2">
										<label for="validationServer03">IMEI/ Serial No.</label> <input
											type="text" id="search-box" name="imei" class="form-control"
											placeholder="Search IMEI/Serial no...">
										<div id="suggestions" class="suggestions"></div>
									</div>
									<div class="col-md-4 mb-2">
										<label for="validationServer04">Official Type</label> <select name="type" id="myDropdown" 
											class="form-control" aria-label="Default select example" onchange="showTextBox()">
											<option>Type</option>
											<option value="AIF Employee" selected="selected">AIF Employee</option>
											<option value="Government Official">Government Official</option>
											<option value="School Teacher">Teacher</option>
											<option value="Other">Other</option>
										</select>

											<div id="myTextbox" style="display: none;">
												<label for="otherOption">Enter your choice:</label> 
												<input type="text" id="otherOption" name="type_other" class="form-control">
											</div>

											<div class="invalid-feedback">Brand Name</div>
									</div>
									<div class="col-md-4 mb-2">
									<label for="validationServerUsername">Official EMAIL/Emp ID</label>
										<div class="input-group">
										<input type="text" name="official_id_no" placeholder="Official Email/Emp ID no." required="required" class="form-control">
										</div>
									
									</div>
								</div>
								</div>
							
						<!-- button to generate dynamic fields -->		
								<hr>
								
								<div class="form-group">
									<div class="form-check">
										<input class="form-check-input is-invalid" type="file" id="file" name="file"
											value="" id="invalidCheck3" required>
										<div class="invalid-feedback"></div>
									</div>
								</div>
								<br> <br>
								
								<input type="submit" class="btn btn-primary" value="Issue item to School/ Student">
								<strong><s:property value="msg" /></strong>
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
										<small>Tab/Item Issue Details</small>
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
								<!-- Item Issue Master summary report will be displayed here -->

								<table id="datatable"
									class="table" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Issue Date</th>
											<th>IMEI/ Serial no.</th>
											<th>Official Name</th>
											<th>Designation</th>
											<th>Official Type</th>
											<th>Official Email/ ID no.</th>
											<th>Type Other</th>
											<th>Document</th>
										</tr>
									</thead>


									<tbody>

										<%
										int i = 1;
										%>
										<s:iterator value="officialList">

											<tr>
												<td>
													<%
													out.print(i);
													i++;
													%>
												</td>
												<td><s:property value="date" /></td>
												<td><s:property value="imei" /></td>
												<td><s:property value="official_name" /></td>
												<td><s:property value="designation" /></td>
												<td><s:property value="type" /></td>
												<td><s:property value="official_id_no" /></td>
												<td><s:property value="type_other" /></td>
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
