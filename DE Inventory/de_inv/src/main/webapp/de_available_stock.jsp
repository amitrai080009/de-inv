<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.de.service.*"%>
<%@page import="com.de.dao.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/AIF_Logo.jpg" type="image/ico" />

<title>DeepShaala Available Stock Page</title>

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
					<!-- <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/AIF_Logo.jpg" alt=".." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>John Doe</h2>
              </div>
            </div> -->
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->

					<jsp:include page="dehome_sidebarmenu.jsp" />

					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					
					<!-- Large modal -->
					<!-- Button trigger modal -->
					
					<!-- Button to trigger modal -->

<!-- Large modal -->
                  

                  <!-- Small modal -->
					

					<!-- Button to Open the Modal -->
					

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
					<div class="tile_count">
						<div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #e6f5f1; border-left: px solid; border-top: px solid; border-bottom: px solid; width: 500px; text-overflow: inherit; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Total Available Stock</span>
							<div class="count blue"><a href="#" class="count blue"><s:iterator value="stockList"><s:property value="total_stock"/></s:iterator></a> </div>
							<!-- ///////////////////////////////////////////////////////////////////////////// -->
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList4">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							<!-- ///////////////////////////////////////////////////////////////////////////// -->
							
							<hr>
							
							 <span class="count_bottom"><i class="blue"><i
									class="fa fa-sort-desc"></i> Stock Register</i> </span>
									
									
						</div>
						
					 
						<div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #d3ebdc; border-right: px solid; border-top: px solid; border-bottom: px solid; width: 500px; text-overflow: inherit; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Working/ New Condition</span>
							<div class="count blue"><a href="#" class="count blue" style="color: #4bb35c;"><s:property value="itemList.size()" /></a></div>
							<%-- /////////////////////////////////////////////////////////////////////////////--%>
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList2">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div> 
							
							<%-- ////////////////////////////////////////////////////////////////////////////////--%>							<hr>
							
							
							<span class="count_bottom"><i class="blue"><i
									class="fa fa-sort-desc"></i>Usable</i></span>
						</div>
						
						<div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #ffdddb; border-left: px solid; border-top: px solid; border-bottom: px solid; width: 500px; text-overflow: inherit; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Faulty/ Returned Stock</span>
							<div class="count red"><a class="count red" href="#"><s:property value="itemList2.size()" /></a></div>
							
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList3">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							
							<hr>
							
							<span class="count_bottom"><i class="red"><i
									class="fa fa-sort-asc"></i>Faulty Stock </i></span>
						</div>
						<%-- <div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #ffdddb; border-left: px solid; border-top: px solid; border-bottom: px solid; width: 500px; text-overflow: inherit; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Dead Stock</span>
							<div class="count red"><a class="count red" href="de_issue_item_official"><s:property value="officialList.size()" /></a></div>
							
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList5">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							<hr>
							
							<span class="count_bottom"><i class="red"><i
									class="fa fa-sort-asc"></i>Non-Repairable </i> </span>
						</div> --%>
						
						
						
						<div class="col-md-6 col-sm-2 ">
							
								
							<div class="x_content" style="background-color: ; overflow: scroll; height: 200px;">
								

								<table id="" class="table display border responsive table-striped table-bordered">
									<thead>
										<tr>
											<th>Brand Name</th>
											<th>Status</th>
											<th>Conditional Remarks</th>
											<th>Item Count</th>
										</tr>
									</thead>
									<tbody>
										<%-- <s:iterator value="brandConditionItems" var="item"
											status="status">
											<tr>

												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].brand">

														<s:set var="rowSpan" value="#status.count" />

														<s:property value="#item.brand" />

													</s:if></td>

												<td><s:property value="#item.current_status" /></td>
												<td><s:property value="#item.item_count" /></td>
											</tr>
										</s:iterator> --%>


										<s:iterator value="brandConditionItems" var="item" status="status">
											<tr>
												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].brand">
														<s:property value="#item.brand" />
													</s:if></td>
												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].current_status">
														
														
														<s:property value="#item.current_status" />
													</s:if></td>
												<td><s:property value="#item.status_remarks" /></td>
												<td><s:property value="#item.item_count" /></td>
											</tr>
										</s:iterator>




									</tbody>
								</table>

							</div>
					</div>
					
					</div>
				</div>
				<!-- /top tiles -->
				
				<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
			<!-- Summary boxes here -->
			
			<div class="row">


					<%-- <div class="col-md-6 col-sm-3 ">
						<div class="x_panel tile fixed_height_320" style="background-color: ; overflow: scroll;">
							<div class="x_title">
								<h2>Available Stock with Condition</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-expanded="false"><i
											class="fa fa-wrench"></i></a>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<a class="dropdown-item" href="#">Settings 1</a> <a
												class="dropdown-item" href="#">Settings 2</a>
										</div></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content" style="background-color: ;">
								

								<table id="" class="table display border responsive table-striped table-bordered">
									<thead>
										<tr>
											<th>Brand Name</th>
											<th>Status</th>
											<th>Conditional Remarks</th>
											<th>Item Count</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandConditionItems" var="item"
											status="status">
											<tr>

												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].brand">

														<s:set var="rowSpan" value="#status.count" />

														<s:property value="#item.brand" />

													</s:if></td>

												<td><s:property value="#item.current_status" /></td>
												<td><s:property value="#item.item_count" /></td>
											</tr>
										</s:iterator>


										<s:iterator value="brandConditionItems" var="item" status="status">
											<tr>
												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].brand">
														<s:property value="#item.brand" />
													</s:if></td>
												<td rowspan="<s:property value='#rowSpan' />"><s:if
														test="#status.first || #item.brand != brandConditionItems[#status.index - 1].current_status">
														
														
														<s:property value="#item.current_status" />
													</s:if></td>
												<td><s:property value="#item.status_remarks" /></td>
												<td><s:property value="#item.item_count" /></td>
											</tr>
										</s:iterator>




									</tbody>
								</table>

							</div>
						</div>
					</div> --%>
					
					
					
					
					<%-- <div class="col-md-3 col-sm-3 ">
						<div class="x_panel tile fixed_height_320" style="background-color: ;">
							<div class="x_title">
								<h2>Faulty Stock with Conditional Remarks</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-expanded="false"><i
											class="fa fa-wrench"></i></a>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<a class="dropdown-item" href="#">Settings 1</a> <a
												class="dropdown-item" href="#">Settings 2</a>
										</div></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content" style="background-color: ;">
								

								<table class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>Brand Name</th>
											<th>Conditional Remarks</th>
											<th>Item Count</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandConditionItems1" var="item"
											status="status">
											<tr>

												<td ><s:property value="#item.brand" /></td>
												<td><s:property value="#item.status_remarks" /></td>
												<td><s:property value="#item.item_count" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
						</div>
					</div> --%>
					

					<!-- <div class="col-md-4 col-sm-4 ">
						<div class="x_panel tile fixed_height_320 overflow_hidden">
							<div class="x_title">
								<h2>Device Details</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-expanded="false"><i
											class="fa fa-wrench"></i></a>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<a class="dropdown-item" href="#">Settings 1</a> <a
												class="dropdown-item" href="#">Settings 2</a>
										</div></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<table class="" style="width: 100%">
									<tr>
										<th style="width: 37%;">
											<p></p>
										</th>
										<th>
											<div class="col-lg-7 col-md-7 col-sm-7 ">
												<p class="">Device</p>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-5 ">
												<p class="">Count</p>
											</div>
										</th>
									</tr>
									<tr>
										<td>
											<canvas class="canvasDoughnut" height="140" width="140"
												style="margin: 15px 10px 10px 0"></canvas>
										</td>
										<td>
											<table class="tile_info">
												<tr>
													<td>
														<p>
															<i class="fa fa-square blue"></i>IRA
														</p>
													</td>
													<td>1200</td>
												</tr>
												<tr>
													<td>
														<p>
															<i class="fa fa-square green"></i>IKALL
														</p>
													</td>
													<td>1200</td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div> -->


					
			
			
			<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
			<!-- Summary Boxes here -->
			


<!-- Item Issue to school details table data starts from here............... -->
				
				
				<!-- Item Issue to school details table data Ends here............... -->
				
				
				<!-- Item issued to official list starts from here -->
				
				
				<!-- Item issued to official end here -->
				
				
				<!-- Item issued to official list starts from here -->
				
				<div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							<div class="row x_title" style="background-color:#f5efe6;">
								<div class="col-md-12">
										<div style="align-content: center; text-align: ; background-color: ; color: ; font-weight: bold; border-width: 2px; border-color: black;">
										<h3><small> <strong>Available Stock</strong></small></h3>
										</div>
								</div>
								
							</div>

							<div class="col-md-12 col-sm-12 ">
								<!-- Item Issue Master summary report will be displayed here -->

								<table id="datatable-fixed-header"
									class="table display border responsive table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Date</th>
											<th>Serial No.</th>
											<th>Item Name</th>
											<th>Brand</th>
											<th>Received From</th>
											<th>Sender Name</th>
											<th>Reference no.</th>
											<th>Warranty Upto</th>
											<th>Current Status</th>
											<th>Status Remarks</th>
											<th>Remarks</th>
										</tr>
									</thead>


									<tbody>

										<%
										int k = 1;
										%>
										<s:iterator value="itemInStockList">

											<tr>
												<td>
													<%
													out.print(k);
													k++;
													%>
												</td>
												<td><s:property value="date" /></td>
												<td><a href="fetchIssueTransaction.action?idimei=<s:property value="IMEI" />"><s:property value="IMEI" /></a></td>
												
												<td><s:property value="item_name" /></td>
												<td><s:property value="brand" /></td>
												<td><s:property value="received_from" /></td>
												<td><s:property value="particular_name" /></td>
												<td><s:property value="ref_no" /></td>
												<td><s:property value="warranty_to" /></td>
												<td><s:property value="current_status" /></td>
												<td><s:property value="status_remarks" />
												</td>
												<td><s:property value="remarks" /></td>
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
				
				<!-- Item issued to official end here -->
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
	
	
	<!-- Modal -->

<!--  Modal  -->
  <!-- Bootstrap JavaScript and dependencies -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <!-- Script to show modal without fade on page load -->
  <script>
    $(document).ready(function(){
      $('#exampleModal').modal({
        backdrop: 'static', // This prevents closing the modal by clicking outside of it
        show: true
      });
    });
  </script>

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
