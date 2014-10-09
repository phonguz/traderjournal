<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href='<spring:url value="/resources/images/favicon.ico"/>'>
<title>Trader Journal</title>
<!-- Bootstrap core CSS -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>
<link href='<spring:url value="/resources/css/bootstrap.min.css"/>' rel="stylesheet">
<link rel="stylesheet" href='<spring:url value="/resources/css/uniform.default.css"/>' type="text/css" media="screen">
<link href='<spring:url value="/resources/css/common.css"/>' rel="stylesheet">
<link rel="stylesheet" href='<spring:url value="/resources/css/font-awesome.min.css"/>'>
<link rel="stylesheet" href='<spring:url value="/resources/css/jquery.datatables.css"/>'>
<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<meta prefix="og: http://ogp.me/ns#" property="og:title" content="Tabslet jQuery plugin" />
<meta prefix="og: http://ogp.me/ns#" property="og:type" content="website" />
<meta prefix="og: http://ogp.me/ns#" property="og:image" content="http://vdw.github.io/Tabslet/images/tabslet_logo_single_color_150.png" />
<meta prefix="og: http://ogp.me/ns#" property="og:url" content="http://vdw.github.io/Tabslet/" />
<link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/easy-responsive-tabs.css"/>' />
<link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/datepicker3.css"/>' />

<%-- <link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel='stylesheet'>
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script> --%>

<script src='<spring:url value="/resources/js/jquery-2.1.1.min.js"/>'></script>
<script src='<spring:url value="/resources/js/easyResponsiveTabs.js"/>' type="text/javascript"></script>
<script src='<spring:url value="/resources/js/bootstrap.min.js"/>'></script>
<%-- <script src='<spring:url value="/resources/js/jquery.tablesorter.js"/>'></script> --%>
<script src='<spring:url value="/resources/js/jquery.tablesorters.js"/>'></script>
<script src='<spring:url value="/resources/js/jquery.tablesorter.pager.js"/>'></script>
<%-- <script src='<spring:url value="/resources/js/tables.js"/>'></script> --%>
<script src='<spring:url value="/resources/js/jquery.uniform.js"/>' type="text/javascript" charset="utf-8"></script>
<script src='<spring:url value="/resources/js/bootstrap-datepicker.js"/>'></script>
<script src='<spring:url value="/resources/js/jquery.datatables.min.js"/>'></script>
<script>var tradeUrl = '${pageContext.request.contextPath}';</script>
<style type="text/css">
.demo {
	width: 980px;
	margin: 0px auto;
}
.demo h1 {
	margin:33px 0 25px;
}
.demo h3 {
	margin: 10px 0;
}
pre {
	background: #fff;
}
 @media only screen and (max-width: 780px) {
 .demo {
 margin: 5%;
 width: 90%;
}
 .how-use {
 float: left;
 width: 300px;
 display: none;
}
}
#tabInfo {
	display: none;
}

/* ---------- */

/* ------------ */
</style>
<script>
$(window).load(function(){
  $('#dvLoading').fadeOut(500);
});
</script> 
</head>
<body>
	  
	<!-- Tiles Attributes -->
		<tiles:insertAttribute name="header"/>
		 
		<tiles:insertAttribute name="defaultBody"/>
 
	 	<tiles:insertAttribute name="footer"/>
	 
	 <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	
	   
    <script type="text/javascript" charset="utf-8">
      $(function(){
        $("input.uniform, textarea.uniform, select.uniform, button.uniform").uniform();
      });
    </script>
</body>
</html>