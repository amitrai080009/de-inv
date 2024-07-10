<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.de.dao.*"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="com.upmscl.service.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/AIF_Logo.jpg" type="image/ico" />

<title>Add Stock</title>

<!-- Bootstrap -->
<link href="./vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="./vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="./vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="./vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-wysiwyg -->
<link href="./vendors/google-code-prettify/bin/prettify.min.css"
	rel="stylesheet">
<!-- Select2 -->
<link href="./vendors/select2/dist/css/select2.min.css" rel="stylesheet">
<!-- Switchery -->
<link href="./vendors/switchery/dist/switchery.min.css" rel="stylesheet">
<!-- starrr -->
<link href="./vendors/starrr/dist/starrr.css" rel="stylesheet">
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
				<div class="row" style="display: inline-block;">
					<!-- dashboard elements are shown here -->

					<!-- dashboard elements are shown here -->
				</div>
				<!-- /top tiles -->

				<div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							<div class="col-md-12 col-sm-12 ">
								<!-- Stock Master summary report will be displayed here -->
								<!-- Stock Entry form start here -->

								<div class="row">

									<div class="col-md-12 col-sm-12 ">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													Single Item Entry <small><strong><s:property
																value="msg" /></strong></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>

													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<br />

												<!-- FOrm is being start from here the add stock form starts from here -->
												<form method="post" action=""
													enctype="multipart/form-data">

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align">Date
															Of Birth <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input id="date" name="date"
																class="date-picker form-control"
																placeholder="dd-mm-yyyy" type="date"
																type="text" onfocus="this.type='date'"
																onmouseover="this.type='date'"
																onclick="this.type='date'" onblur="this.type='text'"
																onmouseout="timeFunctionLong(this)">
															<script>
																function timeFunctionLong(
																		input) {
																	setTimeout(
																			function() {
																				input.type = 'text';
																			},
																			60000);
																}
															</script>
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="first-name">Transaction Type <span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<select id="transaction_type" 
																class="form-control " name="transaction_type">
																<option value="in" selected="selected">IN</option>
																<option value="out">OUT</option>
															</select>

														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="first-name">Item Name <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<select id="item_name" 
																class="form-control " name="item_name">
																<option value="6" selected="selected">TABLET</option>
																<option value="6">TABLET-IRA</option>
																<option value="6">TABLET-IKALL</option>
																<option value="out">STEM</option>
															</select>
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="first-name">IMEI No. <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" name="IMEI" placeholder="Enter IMEI number" class="form-control">
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Item Condition <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" id="quantity" name="condition"
																 class="form-control" placeholder="Item Condition">
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Seller/Sender name & Address <span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" id="sender" name="sender"
																 class="form-control" placeholder="Sender Name & Address">
														</div>
													</div>


													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Receiver Name & Address<span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" id="receiver" name="current_location"
																 class="form-control" placeholder="AIF Office Amreli">
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Invoice/ Challan number<span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" id="challan_no" name="ref_no"
																class="form-control" placeholder="AIF/2024-25/001">
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="first-name">Warranty Sttaus <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<select id="" 
																class="form-control " name="warranty_status">
																<option value="" selected="selected">Select warranty status</option>
																<option value="InWarranty">In Warranty</option>
																<option value="6">Out of Warranty</option>
															</select>
														</div>
													</div>
													
													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align">Warranty From <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input id="date" name="warranty_from"
																class="date-picker form-control"
																placeholder="dd-mm-yyyy" type="date"
																type="text" onfocus="this.type='date'"
																onmouseover="this.type='date'"
																onclick="this.type='date'" onblur="this.type='text'"
																onmouseout="timeFunctionLong(this)">
															<script>
																function timeFunctionLong(
																		input) {
																	setTimeout(
																			function() {
																				input.type = 'text';
																			},
																			60000);
																}
															</script>
														</div>
													</div>
													
													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align">Warranty Upto <span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input id="date" name="warranty_to"
																class="date-picker form-control"
																placeholder="dd-mm-yyyy" type="date"
																type="text" onfocus="this.type='date'"
																onmouseover="this.type='date'"
																onclick="this.type='date'" onblur="this.type='text'"
																onmouseout="timeFunctionLong(this)">
															<script>
																function timeFunctionLong(
																		input) {
																	setTimeout(
																			function() {
																				input.type = 'text';
																			},
																			60000);
																}
															</script>
														</div>
													</div>
													
													
													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="">Upload Challan Copy<span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6">
															<input type="file" id="file" name="file"
																class="file-control">
														</div>
													</div>

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Remarks (If/Any)<span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<input type="text" id="remarks" name="remarks"
																class="form-control" placeholder="Remarks (if/any)">
														</div>
													</div>




													<div class="ln_solid"></div>
													<div class="item form-group">
														<div class="col-md-6 col-sm-6 offset-md-3">
															<input type="submit" name="submit"
																value="Add New Item Details" class="btn btn-success">
															<strong><s:property value="msg" /></strong>
														</div>
													</div>

												</form>

												<!-- ad stock form ends here -->
											</div>
										</div>
									</div>
								</div>
								<!-- Stock Entry form start here -->

<!--  Bulk items upload -->
								<div class="row">
									<div class="col-md-12 col-sm-12 ">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													Bulk Item Upload <small><strong><s:property
																value="msg" /></strong></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>

													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">
												<br />

												<!-- FOrm is being start from here the add stock form starts from here -->
												<form method="post" action="de_upload_item.action"
													enctype="multipart/form-data">

													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="last-name">Download the CSV(format) file and fill the data.<span
															class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<a class="btn btn-link" href="./images/add_item_master_csv_sample.csv">Download</a>
														</div>
													</div>
													
													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="">Upload Excel File<span class="dropzone">*</span>
														</label>
														<div class="col-md-6 col-sm-6">
															<input type="file" id="file" name="file"
																class="file-control">
														</div>
													</div>
													
													<div class="item form-group">
														<label
															class="col-form-label col-md-3 col-sm-3 label-align"
															for="">Upload Challan Copy<span class="">*</span>
														</label>
														<div class="col-md-6 col-sm-6">
															<input type="file" id="file2" name="file2"
																class="file-control">
														</div>
													</div>
													
													<div class="ln_solid"></div>
													<div class="item form-group">
														<div class="col-md-6 col-sm-6 offset-md-3">
															<input type="submit" name="submit"
																value="Upload bulk item details" class="btn btn-success">
															<strong><s:property value="msg" /></strong>
														</div>
													</div>

												</form>
<!--  bulk items details upload ends here -->
												<!-- ad stock form ends here -->
											</div>
										</div>
									</div>
								</div>

								<!-- Stock Report -->


								<div class="row">

									<div class="col-md-12 col-sm-12 ">
										<div class="x_panel">
											<div class="x_title">
												<h2>
													Item Entry Report<small><strong><s:property
																value="msg" /></strong></small>
												</h2>
												<ul class="nav navbar-right panel_toolbox">
													<li><a class="collapse-link"><i
															class="fa fa-chevron-up"></i></a></li>

													<li><a class="close-link"><i class="fa fa-close"></i></a>
													</li>
												</ul>
												<div class="clearfix"></div>
											</div>
											<div class="x_content">

												<table id="datatable"
													class="table"
													style="width: 100%">
													<thead>
														<tr>
															<th>Sr. no.</th>
															<th>Received Date</th>
															<th>Device Name</th>
															<th>Brand</th>
															<th>IMEI No.</th>
															<th>Sender Name & Address</th>
															<th>Ref. No.</th>
															<th>Document</th>
															<th>Remarks</th>
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
																<td><s:property value="item_name" /></td>
																<td><s:property value="brand" /></td>
																<td><s:property value="IMEI" /></td>
																<td><s:property value="received_from" /></td>
																<td><s:property value="ref_no" /></td>
																<td><a target="_blank"
																	href="<s:property value="filepath"/>"><i
																		class="fas fa-file-pdf-o"></i>Download</a></td>
																<td><s:property value="remarks" /></td>
															</tr>
														</s:iterator>
													</tbody>
												</table>


											</div>
										</div>
									</div>



									<
									<!-- Stock Report -->



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
	<!-- bootstrap-progressbar -->
	<script
		src="./vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="./vendors/iCheck/icheck.min.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="./vendors/moment/min/moment.min.js"></script>
	<script src="./vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap-wysiwyg -->
	<script src="./vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
	<script src="./vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
	<script src="./vendors/google-code-prettify/src/prettify.js"></script>
	<!-- jQuery Tags Input -->
	<script src="./vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
	<!-- Switchery -->
	<script src="./vendors/switchery/dist/switchery.min.js"></script>
	<!-- Select2 -->
	<script src="./vendors/select2/dist/js/select2.full.min.js"></script>
	<!-- Parsley -->
	<script src="./vendors/parsleyjs/dist/parsley.min.js"></script>
	<!-- Autosize -->
	<script src="./vendors/autosize/dist/autosize.min.js"></script>
	<!-- jQuery autocomplete -->
	<script
		src="./vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
	<!-- starrr -->
	<script src="./vendors/starrr/dist/starrr.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="./build/js/custom.min.js"></script>


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


</body>
</html>
