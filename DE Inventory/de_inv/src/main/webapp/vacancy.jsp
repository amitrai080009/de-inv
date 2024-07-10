
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.upmscl.dao.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.upmscl.dao.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.upmscl.service.*"%>

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
<link href="css/style_1.css" rel="stylesheet">
<link rel="stylesheet" href="css/lightslider.css" />
<script src="js/fontawesome-all.js"></script>


<!-- start here data tables pagination  -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<!-- ends here data tables pagination -->

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

	<!-- Page Content <div class="container gallery-page"> -->

	<!-- 	<div class="container-fluid"> -->
	<div class="container">
		<!-- Marketing Icons Section -->
		<!-- /.row -->
		<!-- <br> <br>
		<marquee direction="scroll">Portal for Uttar Pradesh Medical
			Supplies Corporation Limited (UPMSCL)</marquee>

		<br> <br> -->



		<h4>RECRUITMENT</h4>
		<hr class="impo-bottom-border">
		<div class="tablediv">
			<table class="table table-bordered border-primary"
				style="font-weight:;">
				<thead style="background-color: #f0f2f5; border: 2px; color: black;">
					<tr>
						<th>SNo.</th>
						<th>Title</th>
						<th style="width: 30%;">Appointment No.</th>
						<th style="width: 15%;">Valid From</th>
						<th style="width: 15%;">Valid to</th>
						<th>File</th>
						<th style="width: 25%">Last Updated</th>
					</tr>
				</thead>
				<tbody>

					<%
					int i = 1;
					%>
					<s:iterator value="vacancyList">
						<tr>
							<td>
								<%
								out.print(i);
								i++;
								%>
							</td>
							<td><s:property value="title" /></td>
							<td style="width: 30%;"><s:property value="refno" /></td>
							<td><s:property value="validfrom" /></td>
							<td><s:property value="validto" /></td>
							<td style="width: 10%"><a target="_blank"
										href="<s:property value="filepathfile"/>"><i
											class="fas fa-file-pdf"></i>Download</a></td>
							<td style="width: 25%"><s:property value="updatedate" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>

		</div>
	</div>

	<br>
	<br>

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
					PIN:226010
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
