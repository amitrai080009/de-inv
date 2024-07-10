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

<title>DeepShaala Login Home Page</title>

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

<link href="./css/active.css" rel="stylesheet">
 
 <!-- table heatmap style -->


<!--  custom scrollbar -->
<style>
/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background: ##ffffff; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
  background: #e6edec; 
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #555; 
}
</style>


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
                        states.append('<option value="">School Name (ALL)</option>');
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

<!-- Chart Js Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script> <!-- Include DataLabels plugin -->

<style>
        /* Use CSS to set the size of the canvas */
        #categoryChart {
            width: 200px; /* Change width here */
            height: 200px; /* Change height here */
        }
    </style>
    
    <!--  -->

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
<!-- ////////////////////////////////////////////TOP FOrm Control //////////////////////////////////////////////////////////////////////////////////// -->
					<div class="col-md-12 col-sm-12 ">

						<form action="dehome_filter_action" method="get"
							enctype="multipart/form-data">
							<div class="form-row">
							<%-- <div class="col-md-3 mb-3">
									<!-- actual form field -->
									<select id="brandFilter" class="form-control display compact">
										<option value="">Select BRAND</option>
										<option value="option2">Option 2</option>
										<option value="option3">Option 3</option>
									</select>
								</div> --%>
								
								<div class="col-md-3 mb-3">
									<!-- actual form field -->
									<select id="country" name="cc_name" class="form-control">
										<option value="">CC Name (ALL)</option>
										<s:iterator value="countries">
										<option value="<s:property value="id" />"><s:property value="name" /></option>
										</s:iterator>
									</select>
								</div>

								<div class="col-md-3 mb-3">
									<!-- actual form field -->
									<select class="form-control" id="state" name="school_name"
										aria-label="Default select example">
<!-- 										<option>Select School Name</option>
 -->										<option value="">School Name (ALL)</option>
 											<option value="<s:property value="school_id" />"><s:property value="school_name" /></option>
									</select>
								</div>

								<div class="col-md-2 mb-3">
									<!-- actual form field -->
									<!-- <input type="date" class="form-control" name="year" value="Year"> -->
									<input type="submit" value="Search" class="form-control btn btn-round btn-info">	
									
								</div>


							</div>
						</form>
					</div>
					
<!-- ////////////////////////////////////////////TOP FOrm Control //////////////////////////////////////////////////////////////////////////////////// -->

					<div class="tile_count">
						<%-- <div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #e6f5f1; border-left: px solid; border-top: px solid; border-bottom: px solid; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Total Stock IN</span>
							<div class="count blue"><a href="de_stock_action" class="count blue"><s:iterator value="stockList"><s:property value="total_stock"/></s:iterator></a> </div>
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							<hr>
							
							 <span class="count_bottom"><i class="blue"><i
									class="fa fa-sort-desc"></i> Stock Register</i> </span>
									
									
						</div> --%>
						
					 
						<!-- <div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #e6f5f1; border-right: px solid; border-top: px solid; 
						border-bottom: px solid; height: 200px; width: 500px; padding-top: 10px; padding-bottom: 10px;"> -->
							<%-- <s:property value="Filter_Flag" /> --%>
								<%-- <s:if test="%{#attr.Filter_Flag != 'true'}"> --%>
							
							<div class="col-md-3 col-sm-3 tile_stats_count mr-4" style="background-color: #e6f5f1; 
							border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.5); 
							height: 200px; width: 200px; padding: 10px; margin: 10px auto;">
							
								<span class="count_top"><i class="fa fa-bar-chart"></i>
									<strong>Total Items in Stock</strong></span>
								<div class="count blue"><a href="uploaditem" class="count blue"><s:property value="itemList.size()" /></a></div>
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
								<hr>
								
								
								<span class="count_bottom"><i class="blue"><i
										class="fa fa-sort-desc"></i>Item Master </i></span>
							</div>
							
<%-- 							</s:if>
 --%>								


						<s:if test="%{#session['loggedUserName'] == 'admin'}">
						
						<div class="col-md-3 col-sm-3  tile_stats_count mr-4" style="background-color: #ffdddb;
						 border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.5); 
						height: 200px; width: 200px; padding: 10px; margin: 10px auto;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								<strong>Issued/  Active</strong> &nbsp;&nbsp; <i class="fa fa-circle green shadow"></i></span>
								
								<!-- <div class="ring-container">
   
								</div> -->

								<s:if test="%{#attr.Filter_Flag != 'true'}">

									<div class="count red">
										<a class="count red" href="de_issue_item_action"> <s:property
												value="%{itemList2_active.size() + officialList.size()}" /></a>
									</div>

								</s:if>
								<s:else>
									<div class="count red">
										<a class="count red" href="de_issue_item_action"> <s:property
												value="%{itemList2_active.size()}" /></a>
									</div>
								
								</s:else>

								<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList3_active">
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
									class="fa fa-sort-asc"></i>School and Officials</i></span>
						</div>
						
						
						</s:if>
						<s:else>
							<!-- Handle case where loggedUserName is null or empty -->
					    
					
						
						<div class="col-md-3 col-sm-3  tile_stats_count mr-4" style="background-color: #ffdddb; 
						border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.5); 
						height: 200px; width: 200px; padding: 10px; margin: 10px auto;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								<strong>Issued </strong></span>
							<div class="count red"><a class="count red" href="de_issue_item_action"><s:property value="%{itemList2.size() + officialList.size()}" /></a></div>
							
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
									class="fa fa-sort-asc"></i>School and Officials</i></span>
						</div>
						
						</s:else>
						
						<%-- <div class="col-md-2 col-sm-3  tile_stats_count" style="background-color: #ffdddb; border-left: px solid; border-top: px solid; border-bottom: px solid; height: 190px;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Issued to Officials</span>
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
									class="fa fa-sort-asc"></i>Officials </i> Issued</span>
						</div> --%>
						
<%-- 						<s:property value="Filter_Flag" />
 --%>								
<%--  <s:if test="%{#attr.Filter_Flag != 'true'}">
 --%>						
						<div class="col-md-3 col-sm-3  tile_stats_count mr-4" style="background-color: #d3ebdc; 
						border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.5); 
						height: 200px; width: 200px; padding: 10px; margin: 10px auto;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								<strong>IN Office / Available</strong></span>
							<div class="count green">
							<a href="de_available_stock.action" style="color: #4bb35c;"><s:property value="%{itemList_available.size()}" /></a>
							</div>
							
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
							<hr>
							
							<span class="count_bottom"><i class="green"><i
									class="fa fa-sort-asc"></i>Available </i> </span>
						</div> 
						
<%-- 						</s:if>
 --%>						
						<div class="col-md-3 col-sm-3  tile_stats_count mr-4" style="background-color: #f5f4d0; 
						border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.5); 
						height: 200px; width: 200px; padding: 10px; margin: 10px auto;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								<strong>IN Transaction/ Repair</strong></span>
							<div class="count black">
							<a class="count black" href="de_inrepair_stock.action"> 
									<s:if
										test="%{#attr.Filter_Flag != 'true'}">
										<s:iterator value="itemList3">
											<s:property value="id" />
										</s:iterator>
									</s:if> <s:else>
										<s:property value="itemList3.size()" />

									</s:else> <%-- <s:property value="`%{itemList2.size() + officialList.size()}" /> --%></a>
							</div>
							
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList6">
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
									class="fa fa-sort-asc"></i>IN Transaction/ Repair </i></span>
						</div>

					</div>
				</div>
				<!-- /top tiles -->

<!-- page content starts here CHARTS AND GRAPH DATA VISUALIZATPONS-->
<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

				<%-- <div class="row">
					<div class="col-md-8 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>School-wise Students Vs Tablet Count</h2>
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

								<div id="mainb" style="height: 350px;overflow: scroll;">
								<!-- Here the actual chart being Generating -->
								<img hidden="" src="<s:url action='generateColumnChart'/>" />
								<img src="<s:url value='/Jfreecharts/columnChart.png'/>" />
								<!-- Here the actual chart being Generating -->
								
								</div>

							</div>
						</div>
					</div>

					<div class="col-md-4 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>Brand Count</h2>
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

								<div id="echart_mini_pie" style="height: 350px;overflow: scroll;">
								<!-- Pie chart using Jfreecharts -->
   							 <img src="<s:property value="request.contextPath" />/Jfreecharts/pieChart.png" alt="Pie Chart" />
   							 
   							 <!-- Here the actual chart being Generating -->
								<img hidden="" src="<s:url action='generatePieChart'/>" />
								<img src="<s:url value='/Jfreecharts/pieChart.png'/>" />
								<!-- Here the actual chart being Generating -->
   							 
								</div>

							</div>
						</div>
					</div>
					</div>
					
					 --%>
					
					
					<div class="row">
					<div class="col-md-8 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>School-wise Students Vs Tablet Count</h2>
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

								<div id="mainb" style="height: 350px; overflow: auto;">
								<!-- Here the actual chart being Generating -->
								    <canvas id="myChart" width="100%" height="100%"></canvas>

								<!--chart js Here the actual chart being Generating -->

									<script>
										// Prepare the data for Chart.js
										var schoolNames = [];
										var studentsCount = [];
										var issuedLaptops = [];

										<s:iterator value="schoolNames" var="schoolName">
										schoolNames
												.push('<s:property value="#schoolName"/>');
										</s:iterator>

										<s:iterator value="studentsCount" var="studentCount">
										studentsCount
												.push('<s:property value="#studentCount"/>');
										</s:iterator>

										<s:iterator value="issuedLaptops" var="issuedLaptop">
										issuedLaptops
												.push('<s:property value="#issuedLaptop"/>');
										</s:iterator>

										// Configuration for the chart
										const data = {
											labels : schoolNames,
											datasets : [
													{
														label : 'Students Count',
														data : studentsCount,
														backgroundColor : '#176f9c',//'rgba(54, 162, 235, 0.2',//'#3883a8',//'#2bacd6',// // background color for student cout bar// '#2bacd6', //
														borderColor : '#176f9c', //'rgba(54, 162, 235, 1)',
														borderWidth : 1,
														 barThickness: 40,                           // Custom bar width
										                    hoverBackgroundColor: 'rgba(54, 162, 235, 0.3)', // Custom hover color
										                    hoverBorderColor: 'rgba(255, 99, 132, 1)'   // Custom hover border color
													},
													{
														label : 'Issued Tablets',
														data : issuedLaptops,
														backgroundColor : 'rgba(54, 162, 235, 0.2',//'60ebbc',//'rgba(255, 99, 132, 0.2)',
														borderColor : 'rgba(75, 192, 192, 1)',
														borderWidth : 1,
														barThickness: 40,                           // Custom bar width
										                hoverBackgroundColor: 'rgba(54, 162, 235, 0.3)', // Custom hover color
										                hoverBorderColor: 'rgba(255, 99, 132, 1)'   // Custom hover border color
													} ]
										};

										 // Custom shadow plugin
								        const shadowPlugin = {
								            id: 'shadow',
								            beforeDatasetsDraw: function(chart) {
								                const ctx = chart.ctx;
								                chart.data.datasets.forEach((dataset, i) => {
								                    const meta = chart.getDatasetMeta(i);
								                    meta.data.forEach((bar, index) => {
								                        ctx.save();
								                        ctx.shadowColor = 'rgba(0, 0, 0, 0.3)';
								                        ctx.shadowBlur = 10;
								                        ctx.shadowOffsetX = 4;
								                        ctx.shadowOffsetY = 4;
								                        ctx.fillStyle = dataset.backgroundColor;
								                        ctx.fillRect(bar.x - bar.width / 2, bar.y, bar.width, bar.base - bar.y);
								                        ctx.restore();
								                    });
								                });
								            }
								        };

								       /*  const config = {
								            type: 'bar',
								            data: data,
								            options: {
								                scales: {
								                    y: {
								                        beginAtZero: true
								                    }
								                }
								            },
								            plugins: [shadowPlugin]
								        }; */
								        
								        const config = {
								                type: 'bar',
								                data: data,
								                options: {
								                    responsive: true,
								                    maintainAspectRatio: false, // This is necessary to allow CSS resizing
													//grid lines removed
													
													scales: {
							                    x: {
							                        beginAtZero: true,
							                
							                        grid: {
							                            display: false // Remove gridlines on x-axis
							                        }
							                    
							                },
							                y: {
						                        grid: {
						                            display: false // Remove gridlines on y-axis
						                        }
						                    }
						                },
													
													//grid lines removed

								                    
								                    plugins: {
								                        legend: {
								                            position: 'bottom',
								                        },
								                        tooltip: {
								                            enabled: true
								                        },
								                        datalabels: {
								                            anchor: 'end',
								                            align: 'top',
								                            formatter: (value) => {
								                                return value;
								                            },
								                            color: '#000', // Change the color of the labels
								                            font: {
								                                weight: '',
								                                size: 11 // Change the size of the labels
								                            }
								                        }
								                    }
								                },
								                
								                plugins: [ChartDataLabels, shadowPlugin] // Register the DataLabels plugin
								            };
								        
								        
								        
										// Render the chart
										const myChart = new Chart(document
												.getElementById('myChart'),
												config);
									</script>

								</div>

							</div>
						</div>
					</div>

					<div class="col-md-4 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>Brand Count</h2>
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

								<div id="echart_mini_pie" style="height: 350px;overflow: scroll;">
								<!-- Pie chart using Jfreecharts -->
   							 <%-- <img src="<s:property value="request.contextPath" />/Jfreecharts/pieChart.png" alt="Pie Chart" />
   							  --%>
   							 <!-- Here the actual chart being Generating -->
							 <canvas id="categoryChart" width="350" height="200"></canvas>

									<script>
							        var categories = [];
							        var counts = [];
							
							        <s:iterator value="categories" var="category">
							            categories.push('<s:property value="#category"/>');
							        </s:iterator>
							        
							        <s:iterator value="counts" var="count">
							            counts.push(<s:property value="#count"/>);
							        </s:iterator>
							
							        console.log('Categories:', categories);  // Debugging statement
							        console.log('Counts:', counts);  // Debugging statement
							
							        const data1 = {
							            labels: categories,
							            datasets: [{
							                label: 'Brand Count',
							                data: counts,
							                backgroundColor: [
							                	'#176f9c',  //'rgba(255, 99, 132, 0.2)',
							                    'rgba(54, 162, 235, 0.2',//'#3883a8',//'rgba(54, 162, 235, 0.2', //#2bacd6
							                    'rgba(255, 206, 86, 0.2)',
							                    'rgba(75, 192, 192, 0.2)',
							                    'rgba(153, 102, 255, 0.2)',
							                    'rgba(255, 159, 64, 0.2)'
							                ],
							                borderColor: [
							                    'rgba(255, 99, 132, 1)',
							                    'rgba(54, 162, 235, 1)',
							                    'rgba(255, 206, 86, 1)',
							                    'rgba(75, 192, 192, 1)',
							                    'rgba(153, 102, 255, 1)',
							                    'rgba(255, 159, 64, 1)'
							                ],
							                borderWidth: 1,
							                radius: '70%' // Adjust the radius size here
							            }]
							        };
							
							        const config1 = {
							            type: 'pie',
							            data: data1,
							            options: {
							                responsive: true,
							                maintainAspectRatio: true, // This is necessary to allow CSS resizing

							                plugins: {
							                    legend: {
							                        position: 'bottom',
							                    },
							                    tooltip: {
							                        enabled: true
							                    },
							                    
							                    datalabels: {
							                        formatter: (value, context) => {
							                            return context.chart.data.labels[context.dataIndex] + ': ' + value;
							                        },
							                        color: '#000', // Change the color of the labels
							                        font: {
							                            weight: '',
							                            size: 11 // Change the size of the labels
							                        }
							                }
							            }
							            },
							            plugins: [ChartDataLabels, shadowPlugin] // Register the DataLabels plugin
							        };
							
							        const categoryChart = new Chart(
							            document.getElementById('categoryChart'),
							            config1
							        );
							    </script>

									<!-- Here the actual chart being Generating -->
   							 
								</div>

							</div>
						</div>
					</div>
					</div>

					<!-- page content starts here CHART AND GRAPH VISUALIZATIONS-->
<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

	<%-- <div class="row">
					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>CC-wise Item Issued Vs Item In-Repair (Data)</h2>
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

								<div id="mainb" style="height: 350px; overflow: scroll;">
								<!-- Here the actual chart being Generating -->
								<img hidden="" src="<s:url action='generateColumnChart'/>" />
								<img src="<s:url value='/Jfreecharts/columnChart.png'/>" />
								<!-- Here the actual chart being Generating -->

									<table id="" class="table display compact nowrap table-striped"
										style="width: 100%; align-content: center; align-items: center;">
										<thead>
											<tr>
												<th>Sr. no.</th>
												<th>CC Name</th>
												<th>Item Brand</th>
												<th>Issued Items</th>
												<th>In Repair</th>

											</tr>
										</thead>
										<tbody>
										<s:iterator value="itemList2_ccwise">
											<tr>
												<td><s:property value="id"/></td>
												<td><s:property value="cc_name"/></td>
												<td><s:property value="brand"/></td>
												<td><s:property value="issued_item_count"/></td>
												<td><s:property value="items_in_transaction_count"/></td>
											</tr>
										</s:iterator>
										</tbody>
									</table>


								</div>

							</div>
						</div>
					</div>

					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>CC-wise Item Issued Vs Item In-Repair (Chart)</h2>
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

								<div id="echart_mini_pie" style="height: 350px;overflow: scroll;">
								<!-- Pie chart using Jfreecharts -->
   							 <img src="<s:property value="request.contextPath" />/Jfreecharts/pieChart.png" alt="Pie Chart" />
   							 
   							 <!-- Here the actual chart being Generating -->
								<img hidden="" src="<s:url action='generateBarChart'/>" />
								<img src="<s:url value='/Jfreecharts/barChart.png'/>" />
								<!-- Here the actual chart being Generating -->
   							 
								</div>

							</div>
						</div>
					</div>
					</div> --%>


<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

	<div class="row">
					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>CC-wise Item Issued Vs Item In-Repair (Data)</h2>
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

								<div id="mainb" style="height: 350px; overflow: scroll;">
								<!-- Here the actual chart being Generating -->
								<%-- <img hidden="" src="<s:url action='generateColumnChart'/>" />
								<img src="<s:url value='/Jfreecharts/columnChart.png'/>" />
								<!-- Here the actual chart being Generating --> --%>

									<table id="" class="table display compact wrap table-striped"
										style="width: 100%; align-content: center; align-items: center;">
										<thead>
											<tr>
												<th>Sr. no.</th>
												<th>CC Name</th>
												<s:if test="%{#attr.Filter_Flag == 'true'}">
												<th>School Name</th>
												</s:if>
												<th>Item Brand</th>
												<th>Issued Items</th>
												<th>In Repair</th>

											</tr>
										</thead>
										<tbody>
										<s:iterator value="itemList2_ccwise">
											<tr>
												<td><s:property value="id"/></td>
												<td><s:property value="cc_name"/></td>
												<s:if test="%{#attr.Filter_Flag == 'true'}">
												<td><s:property value="school_name"/></td>
												</s:if>
												<td><s:property value="brand"/></td>
												<td><s:property value="issued_item_count"/></td>
												<td><s:property value="items_in_transaction_count"/></td>
											</tr>
										</s:iterator>
										</tbody>
									</table>


								</div>

							</div>
						</div>
					</div>

					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>CC-wise Item Issued Vs Item In-Repair (Chart)</h2>
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

								<div id="echart_mini_pie" style="height: 100%; width: 100% ;overflow: scroll;">
								<!-- Pie chart using Jfreecharts -->
   							 <%-- <img src="<s:property value="request.contextPath" />/Jfreecharts/pieChart.png" alt="Pie Chart" />
   							  --%>
   							 <!-- Here the actual chart being Generating -->
								 <canvas id="horizontalBarChart" width="600" height="354"></canvas> <!-- Removed width and height attributes -->

								<!-- Here the actual chart being Generating -->

									<script>
							        var ccNames = [];
							        var issuedItemCounts = [];
							        var itemInTransactionCounts = [];
							
							        <s:iterator value="ccNames" var="ccName">
							            ccNames.push('<s:property value="#ccName"/>');
							        </s:iterator>
							        
							        <s:iterator value="issuedItemCounts" var="issuedItemCount">
							            issuedItemCounts.push(<s:property value="#issuedItemCount"/>);
							        </s:iterator>
							
							        <s:iterator value="itemInTransactionCounts" var="itemInTransactionCount">
							            itemInTransactionCounts.push(<s:property value="#itemInTransactionCount"/>);
							        </s:iterator>
							
							        console.log('CC Names:', ccNames);  // Debugging statement
							        console.log('Issued Item Counts:', issuedItemCounts);  // Debugging statement
							        console.log('Item In Transaction Counts:', itemInTransactionCounts);  // Debugging statement
							        
							        // Determine the maximum value in the datasets to set the x-axis max value
							        var maxIssuedItemCount = Math.max(...issuedItemCounts);
							        var maxItemInTransactionCount = Math.max(...itemInTransactionCounts);
							        var maxValue = Math.max(maxIssuedItemCount, maxItemInTransactionCount);

							
							        const data2 = {
							            labels: ccNames,
							            datasets: [
							                {
							                    label: 'Issued Items',
							                    data: issuedItemCounts,
							                    backgroundColor: '#176f9c',//'rgba(75, 192, 192, 0.2)',//'60ebbc',// 'rgba(75, 192, 192, 0.2)', //'#2bacd6',
							                    borderColor: 'rgba(75, 192, 192, 1)',
							                    borderWidth: 1,
							                    barThickness: 25 // Adjust bar thickness

							                },
							                {
							                    label: 'Items in Transaction',
							                    data: itemInTransactionCounts,
							                    backgroundColor: 'rgba(54, 162, 235, 0.2)',//'rgba(153, 102, 255, 0.2)', //'rgba(255, 99, 132, 0.2)',//
							                    borderColor: 'rgba(153, 102, 255, 1)',
							                    borderWidth: 1,
							                    barThickness: 25 // Adjust bar thickness

							                }
							            ]
							        };
							
							        const config2 = {
							            type: 'bar',
							            data: data2,
							            options: {
							                indexAxis: 'y', // This makes the bars horizontal
							                responsive: true,
							                maintainAspectRatio: true, // This is necessary to allow CSS resizing
							                
							                //grid lines removed
							                scales: {
							                    x: {
							                        beginAtZero: true,
							                        max: maxValue * 1.1, // Add some padding to ensure labels are visible
							                        grid: {
							                            display: false // Remove gridlines on x-axis
							                        }
							                    
							                },
							                y: {
						                        grid: {
						                            display: false // Remove gridlines on y-axis
						                        }
						                    }
						                },
						                
						                //grid lines removed
						                
							                plugins: {
							                    legend: {
							                        position: 'bottom',
							                    },
							                    tooltip: {
							                        enabled: true
							                    },
							                    datalabels: {
							                        anchor: 'end',
							                        align: 'right',
							                        formatter: (value) => {
							                            return value;
							                        },
							                        color: '#000', // Change the color of the labels
							                        font: {
							                            weight: '',
							                            size: 11 // Change the size of the labels
							                        }
							                    }
							                },
							                barPercentage: 0.1, // Adjust the percentage of the category width each bar should occupy
							                categoryPercentage: 0.1 // Adjust the percentage of the category width

							            },
							            plugins: [ChartDataLabels] // Register the DataLabels plugin
							        };
							
							        const horizontalBarChart = new Chart(
							            document.getElementById('horizontalBarChart'),
							            config2
							        );
							    </script>
   							 
								</div>

							</div>
						</div>
					</div>
					</div>


<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->






					<!-- Item Issue to school details table data starts from here............... -->
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="dashboard_graph">

								<div class="row x_title" style="background-color: #f0f5f1;">
									<div class="col-md-12">
										<div
											style="align-content: center; text-align:; background-color:; color:; font-weight: bold; border-width: 2px; border-color: black;">
											<h3>
												<small><strong>Issue Report</strong> - School</small>
											</h3>
										</div>
									</div>

								</div>

								<div class="col-md-12 col-sm-12 ">
									<!-- Stock Master summary report will be displayed here -->

									<table id="datatable" class="table display compact table-striped" style="width: 100%">
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
												<th>Issue Challan</th>
												<th>PCF Document</th>
												<th>Status</th>
											</tr>
										</thead>


										<tbody>

											<%
										int i = 1;
										%>
											<s:iterator value="itemList2">

												<tr>
													<td>
														<%
													out.print(i);
													i++;
													%>
													</td>
													<td><s:property value="date" /></td>
													<td><a
														href="fetchIssueTransaction.action?idimei=<s:property value="imei" />"><s:property
																value="imei" /></a></td>
													<td><s:property value="cc_name" /></td>
													<td><s:property value="school_name" /></td>
													<td><s:property value="student_name" /></td>
													<td><s:property value="udise" /></td>
													<td><s:property value="desc" /></td>
													<td><a target="_blank"
														href="<s:property value="fileFileName"/>"><i
															class="fas fa-file-pdf-o"></i>Download</a></td>

													<td><a target="_blank"
														href="<s:property value="fileFileName_pcf"/>"><i
															class="fas fa-file-pdf-o"></i>
														<s:property value="fileFileName_pcf" /></a></td>
													
														<td><s:property value="remarks" /></td>
														
												</tr>
											</s:iterator>

										</tbody>
									</table>


									<!-- Stock Master summary report will be displayed here -->
								</div>

							</div>
						</div>

					</div>

					<!-- Item Issue to school details table data Ends here............... -->


					<!-- Item issued to official list starts from here -->

					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="dashboard_graph">

								<div class="row x_title" style="background-color: #e6f1f5;">
									<div class="col-md-12">
										<div
											style="align-content: center; text-align:; background-color:; color:; font-weight: bold; border-width: 2px; border-color: black;">
											<h3>
												<small><strong>Issue Report </strong>- Official</small>
											</h3>
										</div>
									</div>

								</div>

								<div class="col-md-12 col-sm-12 ">
									<!-- Item Issue Master summary report will be displayed here -->

									<table id="datatable" class="table" style="width: 100%">
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
										int j = 1;
										%>
											<s:iterator value="officialList2">

												<tr>
													<td>
														<%
													out.print(j);
													j++;
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

					<!-- Item issued to official end here -->


					<!-- Item issued to official list starts from here -->

					<%-- <div class="row">
					<div class="col-md-12 col-sm-12 ">
						<div class="dashboard_graph">

							<div class="row x_title" style="background-color:#f5efe6;">
								<div class="col-md-12">
										<div style="align-content: center; text-align: ; background-color: ; color: ; font-weight: bold; border-width: 2px; border-color: black;">
										<h3><small>Tablet Details - <strong>Available in Stock</strong></small></h3>
										</div>
								</div>
								
							</div>

							<div class="col-md-12 col-sm-12 ">
								<!-- Item Issue Master summary report will be displayed here -->

								<table id="datatable-fixed-header"
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Date</th>
											<th>IMEI/ Serial no.</th>
											<th>Item Name</th>
											<th>Particular Name</th>
											<th>Reference no.</th>
											<th>Warranty Upto</th>
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
												<td><s:property value="received_from" /></td>
												<td><s:property value="ref_no" /></td>
												<td><s:property value="warranty_to" /></td>
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

				</div> --%>

					<!-- Item issued to official end here -->


					<!-- <div class="row">


					<div class="col-md-4 col-sm-4 ">
						<div class="x_panel tile fixed_height_320">
							<div class="x_title">
								<h2>Issued Details</h2>
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
								<h4>Designation wise details</h4>
								
						<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>Designation</th>
											<th>Count</th>
											
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Amit Kumar</td>
											<td>1</td>
											
										</tr>
										<tr>
											<td>2</td>
											<td>Student</td>
											<td>2000</td>
											
										</tr>
										<tr>
											<td>3</td>
											<td>IT Assistant</td>
											<td>2</td>
											
										</tr>
										<tr>
											<td>4</td>
											<td>IT Officer</td>
											<td>1</td>
											
										</tr>
										
										<tr>
											<td>5</td>
											<td>Project Manager</td>
											<td>1</td>
											
										</tr>
										</tbody>
										</table>
				
							</div>
						</div>
					</div>

					<div class="col-md-4 col-sm-4 ">
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
					</div>


					<div class="col-md-4 col-sm-4 ">
						<div class="x_panel tile fixed_height_320">
							<div class="x_title">
								<h2>Other Details</h2>
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
								<div class="dashboard-widget-content">
									
									<table id=""
									class="table table-striped table-bordered" style="width: 100%">
									<thead>
										<tr>
											<th>Sr. no.</th>
											<th>CC Name</th>
											<th>School Name</th>
											<th>Tabs Issued</th>
											
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Amit</td>
											<td>Some School</td>
											<td>200</td>
											
										</tr>
										<tr>
											<td>2</td>
											<td>vinod</td>
											<td>Some School 2</td>
											<td>300</td>
											
										</tr>
										<tr>
											<td>3</td>
											<td>ravi</td>
											<td>Some School 3</td>
											<td>500</td>
											
										</tr>
										<tr>
											<td>4</td>
											<td>kishore</td>
											<td>Some School 5</td>
											<td>400</td>
											
										</tr>
										
										
										</tbody>
										</table>
				

									
								</div>
							</div>
						</div>
					</div>

				</div> -->


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
  <%-- <div class="modal" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 800px;">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">DE Inventory Management</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          
          <div class="col-md-3 col-sm-3  tile_stats_count" style="background-color: #e6f5f1; border-left: px solid; border-top: px solid; border-bottom: px solid">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Total Stock IN</span>
							<div class="count blue" style="font-size: large; font-weight: bolder; max-height: 45px;"><a href="de_stock_action" class="count blue"><s:iterator value="stockList"><s:property value="total_stock"/></s:iterator></a> </div>
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							
							
									
									
						</div>
						
						<div class="col-md-3 col-sm-3  tile_stats_count" style="background-color: #ffdddb; border-left: px solid; border-top: px solid; border-bottom: px solid">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								Issued</span>
							<div class="count red" style="font-size: large; font-weight: bolder; max-height: 45px;"><a class="count red" href="de_issue_item_action"><s:property value="%{itemList2.size() + officialList.size()}" /></a></div>
							
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
							
							
							
						</div>
						
						<div class="col-md-3 col-sm-3  tile_stats_count" style="background-color: #d3ebdc; border: px solid;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								IN Office / Available</span>
							<div class="count green" style="font-size: large; font-weight: bolder; max-height: 45px;"><s:property value="%{itemList.size() - itemList2.size() - officialList.size()}" /></div>
							
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
							
							
						</div>
						
						<div class="col-md-3 col-sm-2  tile_stats_count" style="background-color: #f5f4d0; border: px solid;">
							<span class="count_top"><i class="fa fa-bar-chart"></i>
								IN Repair</span>
							<div class="count black" style="font-size: large; font-weight: bolder; max-height: 45px;"><s:property value="itemList3.size()" /></div>
							
							<div class="">
								<table>
									<thead>
										<tr>
											<th>Brand - </th>
											<th>Total</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="brandQuantitiesList6">
											<tr>
												<td><s:property value="brandName" /> - </td>
												<td><s:property value="totalQuantity" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>

							</div>
							
							
									
						</div>
						
						
						<div><hr><br></div>
						
						<table id="datatable"
									class="table table-striped table-bordered" style="width: 100%">
						<thead>
						<tr>
							<td>Particuars</td>
							<td>Count</td>
						</tr>
						</thead>
						
						<tbody>
						<tr>
							<td>Total no of Transaction Today - </td>
							<td>-</td>
						
						</tr>
						<tr>
							<td>Total no of Devices Issued - </td>
							<td>-</td>
						
						</tr>
						<tr>
							<td>Total no of Devices Returned - </td>
							<td>-</td>
						
						</tr>
						
						<tr>
							<td>Total no of Sent to Service Center - </td>
							<td>-</td>
						
						</tr>
						<tr>
							<td>Total no of Received from Service Center - </td>
							<td>-</td>
						
						</tr>
						
						</tbody>
						
						</table>
						
						
          
        </div>
        <div class="modal-footer">
<!--           <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
 -->          <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div> --%>

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
