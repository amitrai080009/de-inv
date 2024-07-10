
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.upmscl.dao.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.upmscl.dao.*"%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- Global stylesheets -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900"
	rel="stylesheet" type="text/css" />
<link href="./Content/DataTables-1.10.7/css/jquery.dataTables.css"
	rel="stylesheet" />
<link href="./Content/DataTables-1.10.7/css/buttons.dataTables.css"
	rel="stylesheet" />
<link
	href="./Content/DataTables-1.10.7/css/jquery.dataTables_themeroller.css"
	rel="stylesheet" />
<link href="./Content/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="./Content/css/core.css" rel="stylesheet" type="text/css" />
<link
	href="https://cdn.rawgit.com/chingyawhao/materialize-clockpicker/master/dist/css/materialize.clockpicker.css"
	rel="stylesheet" />
<link href="./Content/css/components.css" rel="stylesheet" />
<link href="./Content/css/colors.css" rel="stylesheet" type="text/css" />
<link href="./Content/css/Simply-Tag.css" rel="stylesheet" />
<link href="./Content/css/CustomStyle.css" rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="./Content/css/materialize.css" rel="stylesheet" />
<link href="./Content/css/icons/icomoon/styles.css" rel="stylesheet"
	type="text/css" />
<script src="./Content/js/jquery.min.js"></script>
<link href="./Content/css/sweetalert.css" rel="stylesheet" />
<link href="./Content/css/lightbox.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css" />
<link href="./Content/js/simditor-2.3.6/styles/simditor.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css" />
<link href="./Content/css/select2.min.css" rel="stylesheet" />


<!-- start here data tables pagination  -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<!-- ends here data tables pagination -->



<script src="js/fontawesome-all.js"></script>


<title>Manage Tender</title>

<!-- Date Picker Scripts Starts here -->
<link href="./Content/css/bootstrap-datepicker3.css" rel="stylesheet" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<style>
.datepicker {
	
}
</style>
<script>
   $(function() {
    $( ".datepicker" ).datepicker();
  });
  </script>

<!-- Date Picker Scripts Ends here -->


<script type="text/javascript" language="javascript">
function disableBackButton()
{
window.history.forward();
}
disableBackButton();
window.onload=disableBackButton();
window.onpageshow=function(evt) { if(evt.persisted) disableBackButton(); };
window.onunload=function() { void(0); };
</script>

</head>
<body>


	<div class="navbar navbar-default header-highlight">
		<div class="navbar-header login_logo">
			<a class="navbar-brand" href="index.html"> </a> <a
				class="navbar-brand"> <span
				style="color: #fff; text-transform: uppercase; letter-spacing: 1px; font-size: 22px;">UPMSC</span></a>

			<ul class="nav navbar-nav visible-xs-block">
				<li><a data-toggle="collapse" data-target="#navbar-mobile"><i
						class="icon-tree5"></i></a></li>
				<li><a class="sidebar-mobile-main-toggle"><i
						class="icon-paragraph-justify3"></i></a></li>
			</ul>

		</div>
		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav">
				<li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i
						class="icon-paragraph-justify3"></i>Manage Tender Details</a></li>
			</ul>
			<div class="navbar-right">
				Welcome <i>${sessionScope.username}</i> <a href="logout"
					style="padding-top: 15px; float: right;"> <i
					class="icon-switch2"></i>Logout
				</a>
			</div>

			<p id="page-header-container-text" class="page-header-container-text"></p>
		</div>

	</div>
	<!-- Page container -->
	<div class="page-container">
		<!-- Page content -->
		<div class="page-content">
			<!-- Main sidebar -->
			<div class="sidebar sidebar-main">
					<s:include value="admin-sidebar.jsp" />
			</div>
			<!-- /main sidebar -->
			<!-- Page container -->
			<div class="page-container">
				<!-- Page content -->

				<div class="page-content">
					<!-- Main content -->
					<div class="content-wrapper">



						<div class="panel panel-flat" id="frmManageTender">

							<div class="panel-body">


								<form method="post" action="managensq"
									enctype="multipart/form-data">
									<div class="col-md-12">
										<div class="panel panel-flat">
											<div class="panel-body">
												<div class="row">
													<div class="row">
														<div class="row">
															<div class="input-field col s3">
																<input type="text" name="particular" size="100"
																	placeholder="Particulars" required>

															</div>
															<div class="input-field col s3">
																<input type="text" name="firm_name" size="100"
																	placeholder="Firm Name" required>

															</div>
															<div class="input-field col s3">
																<input type="text" name="firm_address" size="100"
																	placeholder="Firm Address" required>

															</div>
															<div class="input-field col s3">
																<input type="text" name="nsq_reason" size="100"
																	placeholder="Reason for Blacklisting" required>

															</div>
															<div class="input-field col s3">
																<input type="text" name="nsq_orderno" size="100"
																	placeholder="Order Number" required>

															</div>
															
															
															<div class="input-field col s3">
																<input name="nsq_date" class="datepicker"
																	placeholder="NSQ Date (mm/dd/yyyy)" type="text"
																	autocomplete="off" required />

															</div>
															<div class="input-field col s3">
																<input type="text" name="nsq_duration" size="100"
																	placeholder="Duration of Blacklisting" required>

															</div>
															<div class="input-field col s3">
																<input type="text" name="nsq_remarks" size="100"
																	placeholder="Remarks if any" required>

															</div>

															</div>

															
														</div>

													
													<div class="row" id="imgPreviewDivRefresh">
														<input type="file" name="nsq_file" required />

													</div>
													<div class="col-md-12" style="color:red">
														<input type="submit" name="upload" value="Upload"
															class="btn btn-primary pull-left" id="submit">
														<strong><s:property value="msg" /></strong>

													</div>


												</div>

											</div>
										</div>


									</div>
								</form>

								<div class="row">




									<div class="row padding-table" id="ManageWebTendertable">





										<table class="table table-bordered" id="newsblog">
											<thead style="width: 100%; background-color: #b3d4fd">
												<tr style="width: 100%">
													<th style="width: 5%">SNo.</th>
													<th style="width: 13%">Tender No.</th>
													<th style="width: 25%">Description</th>
													<th style="width: 12%">Category</th>
													<th style="width: 12%">Tender_Start</th>
													<th style="width: 12%">Closing_Date</th>
													<th style="width: 10%">Document</th>
													<th style="width: 10%">Edit</th>
												</tr>
											</thead>
											<tbody style="width: 100%">

												<%
                                  int i=1;
                        try
                        {

                       	 	ResultSet rs = null;
                            DaoAdmin da = new DaoAdmin();
                            rs = da.treport();
                                
                                if(!rs.isBeforeFirst())
                                {
                                    %>
												<tr>
													<td colspan="3">
														<%out.print("No Files!"); %>
													</td>
												</tr>
												<%
                                }    
                                
                                while(rs.next())
                                {   
                            %>
												<tr>
													<td style="width: 5%">
														<% out.print(i);i++;  %>
													</td>
													<td style="width: 13%">
														<%out.print(rs.getString("tender_no")); %>
													</td>
													<td style="width: 25%">
														<%out.print(rs.getString("description")); %>
													</td>
													<td style="width: 12%">
														<%out.print(rs.getString("tender_cat")); %>
													</td>
													<td style="width: 12%">
														<%out.print(rs.getString("tender_start")); %>
													</td>
													<td style="width: 12%">
														<%out.print(rs.getString("tender_end")); %>
													</td>
													<td style="width: 8%"><a target="_blank"
														href="<%out.print(rs.getString("filepath"));%>"><i
															class="fas fa-file-pdf"></i></a></td>
													<td style="width: 8%">
														<form name="form" method="POST" action="updatetender">
															<input type="hidden" name="id"
																value="<%= rs.getInt("id") %>"> <input
																type="submit" name="submit" onsubmit="updateInfo()"
																value="Edit" class="btn-primary">
														</form>
													</td>

												</tr>
												<%
                                }
                            %>


												<%
                                rs.close();
                               
                        }catch(Exception e){e.printStackTrace();}    
                        
                    %>




											</tbody>
										</table>

									</div>




								</div>

							</div>

							<script src="Script/manageTender.js"></script>

						</div>

						<%
                         String msg = request.getParameter("msg");
                        if(msg!=null)
                            {
                           out.println("<script language='javascript'>alert('Tender Details Uploaded Successfully');");
                           out.println("location.replace('ManageTender.jsp')</script>");
                           out.println("<script language='javascript'>setTimeout(function () {location.assign(Quiz_login.jsp);}, 1)");

                            }
                        
                        %>

					</div>
					<!-- /main content -->

				</div>
				<!-- /page content -->

			</div>
			<!-- /page container -->


			<!-- /main content -->
		</div>
		<!-- Footer -->
		<div class="footer text-muted">
			&copy; 2018. <a href="#">UPMSC</a>
		</div>
		<!-- /footer -->
		<!-- /page content -->
	</div>
	<!-- /page container -->
	<!--JS files -->





	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="./Content/js/bootstrap.min.js"></script>
	<script src="./Content/js/uniform.min.js"></script>
	<script type="text/javascript" src="./Content/js/app.js"></script>
	<script src="./Content/Script/request.js"></script>
	<script src="./Content/Script/Constant.js"></script>
	<script src="./Content/js/jquery.lazyload.js"></script>
	<script src="./Content/js/lightbox.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
	<script src="./Content/js/select2.min.js"></script>
	<script src="./Content/Script/Utility.js"></script>



	<script src="./Content/DataTables-1.10.7/js/jquery.dataTables.js"></script>
	<script src="./Content/DataTables-1.10.7/js/dataTables.buttons.js"></script>
	<script src="./Content/DataTables-1.10.7/js/buttons.flash.js"></script>
	<script src="./Content/DataTables-1.10.7/js/buttons.html5.js"></script>
	<script src="./Content/DataTables-1.10.7/js/buttons.print.js"></script>
	<script src="./Content/DataTables-1.10.7/js/jszip.js"></script>
	<script src="./Content/DataTables-1.10.7/js/pdfmake.js"></script>
	<script src="./Content/DataTables-1.10.7/js/vfs_fonts.js"></script>


	<script src="../Content/js/sweetalert.min.js"></script>

	<script
		src="https://cdn.rawgit.com/fengyuanchen/cropper/v2.0.1/dist/cropper.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	<script src="./Content/js/tag-it.js"></script>
	<script src="./Content/js/loadingoverlay.js"></script>
	<script src="./Content/js/Simply-Tag.js"></script>

	<script src="./Content/js/simditor-2.3.6/scripts/module.js"></script>
	<script type="text/javascript"
		src="./Content/js/simditor-2.3.6/scripts/hotkeys.js"></script>

	<script type="text/javascript"
		src="./Content/js/simditor-2.3.6/scripts/simditor.js"></script>

	<script src="./Content/js/bootstrap-datepicker.min.js"></script>


	<script src="./Content/Script/xls.core.min.js"></script>
	<script src="./Content/Script/xlsx.core.min.js"></script>


	<!-- data tables pagination srats here -->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/datatables.min.js"></script>
	<script type="text/javascript">
        $(document).ready(function (){
            $("#newsblog").DataTable();
            
        });
        
        </script>
	<!-- data tables pagination ends here -->

</body>
</html>

