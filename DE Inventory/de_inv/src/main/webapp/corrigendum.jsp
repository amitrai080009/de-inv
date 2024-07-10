<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="com.upmscl.service.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<link href="../Content/DataTables-1.10.7/css/jquery.dataTables.css"
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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css"
	rel="stylesheet" />
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
<link href="./Content/css/bootstrap-datepicker3.css" rel="stylesheet" />

<!-- font awesome Starts here -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Pagination table ends here -->
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
		$(".datepicker").datepicker();
	});
</script>

<!-- Date Picker Scripts Ends here -->


<script type="text/javascript" language="javascript">
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
	<div class="navbar navbar-default header-highlight">
		<div class="navbar-header login_logo">
			<a class="navbar-brand" href="index.html"> </a> <a
				class="navbar-brand"> <span
				style="color: #fff; text-transform: uppercase; letter-spacing: 1px; font-size: 22px;">UPMSC</span></a>
			<ul class="nav navbar-nav visible-xs-block">
				<li><a data-toggle="collapse" data-target="#navbar-mobile"><i
						class="icon-tree5"></i></a>dfdfdfddf</li>ssdddddddffffff
				<li><a class="sidebar-mobile-main-toggle"><i
						class="icon-paragraph-justify3"></i>vvvvv</a></li>ffffffff
			</ul>
		</div>
		<div class="navbar-collapse collapse" id="navbar-mobile">
			<ul class="nav navbar-nav">
				<li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i
						class="icon-paragraph-justify3"></i>Manage Tender Details</a></li>
			</ul>
			<div class="navbar-right">
				Welcome <i><s:property value="%{#session['loggedUserName']}" /></i> <a href="logout"
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
							<form method="post" action="upload-corrigendum"
								enctype="multipart/form-data">
								<div class="panel-body">
									<div class="col-md-12">
										<div class="panel panel-flat">
											<div class="panel-body">
												<div class="row">
													<div class="row">
														<div class="row">
															<label for="cat"
																style="font-family: Calibri; font-size: 18px">Select
																Tender to Amend:</label> <select name="corrigendum.id" id="cat"
																style="width: 200px; height: 40px; text-transform: uppercase"
																required>
																<option value="">Select Tender to Amend</option>
																<s:iterator value="beanList">
																	<option value="<s:property value="id" />">
																		<s:property value="tender_no" />
																	</option>
																</s:iterator>
															</select>
														</div>
													</div>
												</div>
												<div class="input-field col s3">
													<input name="corrigendum.title" type="text"
														autocomplete="off" value="" placeholder="Amendment Title"
														required />
												</div>
												<div class="row" id="imgPreviewDivRefresh">
													<input type="file" name="corrigendum.file" required />
												</div>
												<div class="col-md-12">
													<input type="submit" name="upload"
														value="Upload Corrigendum"
														class="btn btn-primary pull-left" id="submit">
													<s:property value="corrigendum.msg" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
							<div class="row">
								<div class="row padding-table" id="ManageWebTendertable">
								</div>
							</div>
						</div>
						<script src="Script/manageTender.js"></script>
					</div>
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
	<script src="./Content/Script/DateFormat.js"></script>
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
</body>
</html>

