
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="com.upmscl.service.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="java.sql.PreparedStatement"%>

<% 
response.setHeader("X-XSS-Protection", "1; mode=block");
//response.setHeader("Content-Security-Policy", "default-src https: data: 'unsafe-eval' 'unsafe-inline'");
response.setHeader("Content-Security-Policy", "default-src * self blob: data: gap:; style-src * self 'unsafe-inline' blob: data: gap:; script-src * 'self' 'unsafe-eval' 'unsafe-inline' blob: data: gap:; object-src * 'self' blob: data: gap:; img-src * self 'unsafe-inline' blob: data: gap:; connect-src self * 'unsafe-inline' blob: data: gap:; frame-src * self blob: data: gap:;");
//Comment the below line:   
// response.addHeader( "X-FRAME-OPTIONS", "SAMEORIGIN" );
%>

<html lang="en">
<head>
<meta name="UPMSC" content="" />
<meta charset="utf-8">
<meta name="Title" content="UPMSC" />
<meta name="description"
	content="Find government aid for health care services, medical drugs needs, vaccine distribution for people.">
<meta property="description" content="" />
<meta name="Author" content="UPMSC" />
<meta name="p:domain_verify" content="" />
<meta name="keywords"
	content="Government health services,UP medicine supplies,drug supply">
<meta name="Identifier" content="http://www.gandhi.gov.in/" />
<meta name="Classification" content="" />
<meta name="keyphrase" content="" />
<meta name="abstract" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" sizes="32x32"
	href="img/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="img/favicon-16x16.png">
<title>Uttar Pradesh Medical Supplies Corporation Limited</title>
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/modern-business.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="css/lightslider.css" />
<script src="js/fontawesome-all.js"></script>

<!-- Loading icon import css and javascript -->
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
<!-- Loading icon import css and javascript -->

<!-- start Mixpanel -->
<script type="text/javascript">
(function(e,a){if(!a.__SV){var b=window;try{var c,l,i,j=b.location,g=j.hash;c=function(a,b){return(l=a.match(RegExp(b+"=([^&]*)")))?l[1]:null};g&&c(g,"state")&&(i=JSON.parse(decodeURIComponent(c(g,"state"))),"mpeditor"===i.action&&(b.sessionStorage.setItem("_mpcehash",g),history.replaceState(i.desiredHash||"",e.title,j.pathname+j.search)))}catch(m){}var k,h;window.mixpanel=a;a._i=[];a.init=function(b,c,f){function e(b,a){var c=a.split(".");2==c.length&&(b=b[c[0]],a=c[1]);b[a]=function(){b.push([a].concat(Array.prototype.slice.call(arguments,
0)))}}var d=a;"undefined"!==typeof f?d=a[f]=[]:f="mixpanel";d.people=d.people||[];d.toString=function(b){var a="mixpanel";"mixpanel"!==f&&(a+="."+f);b||(a+=" (stub)");return a};d.people.toString=function(){return d.toString(1)+".people (stub)"};k="disable time_event track track_pageview track_links track_forms register register_once alias unregister identify name_tag set_config reset opt_in_tracking opt_out_tracking has_opted_in_tracking has_opted_out_tracking clear_opt_in_out_tracking people.set people.set_once people.unset people.increment people.append people.union people.track_charge people.clear_charges people.delete_user".split(" ");
for(h=0;h<k.length;h++)e(d,k[h]);a._i.push([b,c,f])};a.__SV=1.2;b=e.createElement("script");b.type="text/javascript";b.async=!0;b.src="undefined"!==typeof MIXPANEL_CUSTOM_LIB_URL?MIXPANEL_CUSTOM_LIB_URL:"file:"===e.location.protocol&&"//cdn4.mxpnl.com/libs/mixpanel-2-latest.min.js".match(/^\/\//)?"https://cdn4.mxpnl.com/libs/mixpanel-2-latest.min.js":"//cdn4.mxpnl.com/libs/mixpanel-2-latest.min.js";c=e.getElementsByTagName("script")[0];c.parentNode.insertBefore(b,c)}})(document,window.mixpanel||[]);
mixpanel.init("f4b6139f0843cf80365fb2c146b66730");</script>
<!-- end Mixpanel -->

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-120581033-1"></script>
<script>
 window.dataLayer = window.dataLayer || [];
 function gtag(){dataLayer.push(arguments);}
 gtag('js', new Date());

 gtag('config', 'UA-120581033-1');
    </script>

<style>
.dropbtn {
	padding: 16px;
	font-size: 16px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	min-width: 160px;
	z-index: 1;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>

<script type="text/javascript">
function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.HORIZONTAL}, 'google_translate_element');
}
</script>

<script type="text/javascript"
	src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
</head>
<body>

	<div id="loader" class="center"></div>
	<script src="js/header.js"></script>
	<header>
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<!-- Slide One - Set the background image for this slide in the line below -->
				<div class="carousel-item active carousel-height"
					style="background-image: url('./img/slider.jpg')" alt=""></div>
				<!-- Slide Two - Set the background image for this slide in the line below -->
				<div class="carousel-item carousel-height"
					style="background-image: url('./img/slider2.png')" alt=""></div>
				<!-- Slide Three - Set the background image for this slide in the line below -->
				<div class="carousel-item carousel-height"
					style="background-image: url('./img/slider3.jpg')" alt=""></div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<div class="h-100 color-card">
			<div class="col-6 col-lg-6 img-style">
				<a href="http://upcmo.up.nic.in/" target="_blank"> <img
					src="img/Yogi_Adityanath_CMUP.jpg" class="img-responsive" alt="">
					<h6 class="name-style">Shri Yogi Adityanath</h6>
					<p class="positsn-style">Hon`ble Chief Minister Government of
						UP</p>
				</a>
			</div>
			<div class="col-6 col-lg-6 img-style">
				<a href="http://up-health.in/hi/" target="_blank"> <img
					src="img/Brajesh_Pathak_DeputyCM.jpg" class="img-responsive" alt="">
					<h6 class="name-style">Shri Brajesh Pathak</h6>
					<p class="positsn-style">Hon`ble Health Minister Government of
						UP</p>
				</a>
			</div>
		</div>
	</header>
	<div class="container">
		<!-- Marketing Icons Section -->
		<div class="row six-buttons">
			<div class="col-lg-4 marg-bottom">
				<a id="firstLinkId"
					href="https://updvdms.dcservices.in/HISUtilities/dashboard/dashBoardACTION.cnt?groupId=MjQ=&dashboardFor=RFdI&hospitalCode=998&seatId=10001&isGlobal=1&isPreview=0"
					target="_blank">
					<div class="impo-link-bg"
						style="color: #028ac8; background-color: #17b0e3">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/impl1.png" class="img-responsive img-3"
									id="firstLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="firstTitleId">Drugs and Vaccine Distribution
										Management System</span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 marg-bottom">
				<a id="secondLinkId"
					href="https://updvdms.dcservices.in/HISUtilities/dashboard/dashBoardACTION.cnt?groupId=MjI=&dashboardFor=RFdI&hospitalCode=998&seatId=10001&isGlobal=1&isPreview=0"
					target="_blank">
					<div class="impo-link-bg"
						style="color: #028ac8; background-color: #67c996;">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/2.png" class="img-responsive img-3"
									id="secondLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="secondTitleId">Rate Contract Central</span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 marg-bottom">
				<a id="thirdLinkId"
					href="https://updvdms.dcservices.in/HISUtilities/dashboard/dashBoardACTION.cnt?groupId=MjE=&dashboardFor=RFdI&hospitalCode=998&seatId=10001&isGlobal=1&isPreview=0"
					target="_blank">
					<div class="impo-link-bg bg-1"
						style="color: #028ac8; background-color: #d66986">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/3.png" class="img-responsive img-3"
									id="thirdLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="thirdTitleId">Central PO Detail</span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>

		<div class="row">

			<div class="col-lg-4 marg-bottom">

				<a id="fifthLinkId"
					href="https://updvdms.dcservices.in/HISUtilities/dashboard/dashBoardACTION.cnt?groupId=MjA=&dashboardFor=RFdI&hospitalCode=998&seatId=10001&isGlobal=1&isPreview=0"
					target="_blank">
					<div class="impo-link-bg1 bg-2"
						style="color: #028ac8; background-color: #d5e37b">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/1.png" class="img-responsive img-3"
									id="fifthLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="fifthTitleId">Life Saving Drugs</span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 marg-bottom">
				<a id="sixthLinkId"
					href="https://updvdms.dcservices.in/HISUtilities/dashboard/dashBoardACTION.cnt?groupId=MTg=&dashboardFor=RFdI&hospitalCode=998&seatId=10001&isGlobal=1&isPreview=0"
					target="https://updvdms.dcservices.in/IMCS/hissso/loginLogin.action">
					<div class="impo-link-bg1 bg-3"
						style="color: #028ac8; background-color: #f59740">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/impl2.png" class="img-responsive img-3"
									id="sixthLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="sixthTitleId">State Stock Details</span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 marg-bottom">
				<a id="fourthLinkId" href="https://forms.gle/dgZewrkbef7EkMPk6"
					target="_blank">
					<div class="impo-link-bg1"
						style="color: #028ac8; background-color: #8791ff">
						<div class="row">
							<div class="col-4 col-lg-4">
								<img src="img/msme3.png" height="60px" width="60px"
									class="img-responsive img-3" id="fourthLogoId" alt="">
							</div>
							<div class="col-8 col-lg-8 flex-content">
								<h6 class="impo-link-head">
									<span id="fourthTitleId">Suppliers MSME Status<img
										src="img/new.gif" alt=""></span>
								</h6>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<!-- Page Content -->
	<div class="container main-sec">
		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-4 mb-4">
				<div class="card1 h-100">
					<div class="card-body1 left_buttons">
						<ul>
							<li><a
								href="https://updvdms.dcservices.in/IMCS/hissso/loginLogin.action"
								target="_blank"><img src="img/warehouse.png" alt="">Warehouse
									Login</a></li>
							<li><a
								href="login.action"
								target="_blank"><img src="img/supplier.png" alt="">Website
									Login</a></li>
							<li><a href="./img/pdf/Grievance redressal.pdf"
								target="_blank"><img src="img/new.gif" alt="">Grievance
									Redressal System</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-lg-8 mb-6">
				<div class="card1 h-100">
					<h4 class="card-header  styl-text">Welcome to our Corporation</h4>
					<div class="card-body1">
						<h2 class="color-text">ABOUT UTTAR PRADESH MEDICAL SUPPLIES
							CORPORATION LIMITED</h2>
						<p class="card-text">Government of Uttar Pradesh is committed
							to provide timely and effective Health Care Services to the
							people of Uttar Pradesh. A majority of the poor people of Uttar
							Pradesh usually depend on Public/Government Health Care Delivery
							Systems to address their preventative and curative health needs.
							Optimal availability of good quality drugs procured at
							competitive prices, quality provision of health related services
							and proper construction and maintenance of health facilities are
							of paramount importance for better Health Care Delivery. In such
							a scenario, the financing and supply of drugs, services, etc. for
							government health services has become one of the key concerns for
							GoUP.</p>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->
		<div class="row tenders">
			<div class="col-lg-9">
				<div style="display: flex;">
					<div
						style="float: left; background-color: #3c85c9; width: 120px; margin: 0px; padding: 10px;">
						<h4 style="background-color: #3c85c9; color: white; height: 100%;">TENDERS</h4>
					</div>
					<div
						style="float: right; margin-left: 0%; color: red; padding-top: 20px; font-weight: bolder;">
						For Corrigendum/Results visit Tenders Page <strong><a
							href="tenderdetails">Click Here</a></strong>
					</div>
					<div
						style="float: right; margin-left: 0%; color: red; padding-top: 20px; font-weight: bolder;">
						UP E-Tender Website :<a target="_blank"
							href="https://etender.up.nic.in/nicgep/app"> Click Here</a>
					</div>
				</div>
				<hr class="impo-bottom-border">
				<div class="row padding-table col-md-18" id="ManageWebTendertable">
					<table class="table table-bordered">
						<thead style="width: 100%">
							<tr style="width: 100%">
								<th style="width: 5%">SNo.</th>
								<th style="width: 10%">Tender No.</th>
								<th style="width: 22%">Description</th>
								<!-- <th style="width: 8%">Category</th> -->
								<th style="width: 10%">Closing_Date</th>
								<th style="width: 8%">Tender Document</th>
								<th style="width: 15%">Corrigendum/ Results</th>
							</tr>
						</thead>
						<tbody style="width: 100%">
							<tr>
								<td colspan="3" style="width: 100%"></td>
							</tr>

							<!-- Tender List and corrigemdum is being prited here -->

							<%int i = 1;%>
							<s:iterator value="tenderList" var="tender">
								<tr>
									<td style="width: 5%"><%out.print(i);i++;%></td>
									<td style="width: 10%"><s:property value="tender_no" /></td>
									<td style="width: 22%"><s:property value="title" /></td>
									<td style="width: 10%"><s:property value="tender_end" /></td>
									<td style="width: 8%"><a target="_blank"
										href="<s:property value="fileFileName"/>"><i
											class="fas fa-file-pdf"></i>Download</a></td>

									<td style="width: 15%; padding-top: 0px; padding-bottom: 0px;">
										<s:iterator value="amendList" var="amend">
											<s:if test="#tender.id == #amend.id">

												<!-- Tender Amend Data here -->
												<a target="_blank" href='<s:property value="fileFileName"/>'
													style="color: blue; text-decoration: underline;"><s:property
														value="title" /> <strong>,</strong></a>
												<br />
											</s:if>
										</s:iterator>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-lg-3 mb-3">
				<h4 class="card-header3">CIRCULAR / ANNOUNCEMENT</h4>
				<div class="h-1001 color-card1" style="overflow: auto;">
					<marquee behavior="scroll" scrollamount="3" direction="up"
						onmouseover="this.stop();" onmouseout="this.start();" class="m14"
						id="dynamicCircularAnnouncement">
						<div class="card-body">
							<p class="card-text">
							<ul style="">
								<s:iterator value="circularList">
									<li><a target="_blank"
										href='<s:property value="fileFileName"/>'><s:property
												value="title" /></a>
										<hr class="border_bottm"></li>
								</s:iterator>
							</ul>

						</div>
						<hr class="border_bottm">
					</marquee>
				</div>
			</div>
		</div>
	</div>
	<div class="logo-slider">
		<div class="container client_logo">
			<div class="item">
				<ul id="content-slider" class="content-slider">
					<li><a href="https://janaushadhi.gov.in/index.aspx"
						target="_blank"> <img src="img/client/bhartiya.png" alt="" />
					</a></li>
					<li><a href="http://up.gov.in" target="_blank"> <img
							src="img/client/gov.png" alt="" />
					</a></li>
					<li><a href="" target="_blank"> <img
							src="img/client/rsby.png" alt="" />
					</a></li>
					<li><a href="http://uphssp.org.in/" target="_blank"> <img
							src="img/client/uphssc.png" alt="" />
					</a></li>
				</ul>
			</div>
		</div>
	</div>
	
<!-- Footer -->
	<footer class="py-5 bg-dark footer_tags">
		<div class="container">
			<div class="col-md-6">
				<p class="m-0 text-white">Site content owned, maintained and
					updated by Uttar Pradesh Medical Supplies Corporation, Government
					of Uttar Pradesh, Lucknow, India</p>
				<hr />
				<p class="tag_name">
					<span><a href="img/pdf/copyright policy.pdf" target="_blank">Coypright
							Policy</a></span><span><a href="img/pdf/disclaimer policy.pdf"
						target="_blank">Disclaimer Policy</a></span><span><a
						href="img/pdf/Hyperlinking Policy.pdf" target="_blank">Hyperlinking
							Policy</a></span><span><a href="img/pdf/TERMS OF USE.pdf"
						target="_blank">Terms Of Use</a></span><span><a
						href="sitemap.html">Sitemap</a></span>
				</p>
			</div>
			<div class="col-md-6 t-a-r">
				<p class="m-0 text-white">
					Address- SUDA Bhawan 7/23 sector -7 Gomti nagar extension<br />Lucknow
					PIN:226002
				</p>
				<hr />
				<p class="t-r tag_name">
					<span> Powered By:</span> <a href="" target="_blank"> IT Cell
						UPMSCL</a>
				</p>
			</div>

		</div>
	</footer>
	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="js/lightslider.js"></script>
	<script>
        $(document).ready(function () {
            $("#content-slider").lightSlider({
                loop: true,
                keyPress: true
            });
            $('#image-gallery').lightSlider({
                gallery: true,
                item: 4,
                thumbItem: 9,
                slideMargin: 0,
                speed: 100,
                auto: false,
                loop: true,
  
                onSliderLoad: function () {
                    $('#image-gallery').removeClass('cS-hidden');
                }
            });
        });
    </script>

	<script src="Content/Script/Constant.js"></script>
	<script src="Content/Script/request.js"></script>
	<script src="Content/Script/Utility.js"></script>
	<script src="Admin/Script/webIndex.js"></script>

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