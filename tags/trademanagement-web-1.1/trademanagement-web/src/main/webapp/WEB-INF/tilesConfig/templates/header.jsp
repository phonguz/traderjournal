<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container">
    <div class="navbar-header trade_header"> 

		<nav class="navbar navbar-default" role="navigation">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="<%=request.getContextPath() %>/trade">Trader Journal</a> 
		    </div>
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
		    
		        
		      <ul class="nav navbar-nav navbar-right">
		        <li class="dropdown"> 
		        	<c:if test="${not empty firstName}">
						<a href="#" class="dropdown-toggle loing_icon" data-toggle="dropdown">${firstName}</a>
					</c:if>
					<c:if test="${empty firstName}">
						<a class="login_link loing_icon" href="<%=request.getContextPath() %>/Login">Login</a>
					</c:if>        	
		          <ul class="dropdown-menu">
		          	<!-- <li class="dropdown-header">Manage</li> -->
		          <!-- 	<li class="divider"></li> --> 
		          	<li><a href="<%=request.getContextPath() %>/Trader/updateprofile"><i class="fa fa-user"></i>&nbsp; Profile</a></li> 
		            <li class="divider"></li>            
		            <li><a class="signup" href="<%=request.getContextPath() %>/Login/logout" ><i class="fa fa-sign-out"></i>&nbsp; Logout</a></li>
		          </ul>
		        </li>
		      </ul>
		    
				<ul class="nav navbar-nav">
					<li><a href="<%=request.getContextPath() %>/trade">Trades</a></li>
					<c:if test="${traderType=='ADMIN'}">
						<li><a href="<%=request.getContextPath() %>/Trader/gettraders">Traders</a></li>
						<li><a href="<%=request.getContextPath() %>/admin/currencyList">Currency</a></li>
						<li><a href="<%=request.getContextPath() %>/admin/instrumentList">Instruments</a></li>
						<li><a href="<%=request.getContextPath() %>/Trader/eventTypeList">Event Type</a></li>
						<li><a href="<%=request.getContextPath() %>/Trader/transactionTypeList">Transaction Type</a></li>
						<%-- <li><a href="<%=request.getContextPath() %>/report/reportJSP" class="btn">Report</a></li> --%>
						<%-- <li><a href="<%=request.getContextPath() %>/report/reportPDFAdmin" target="_blank">Report</a></li> 
 --%>					</c:if>
					<%-- <c:if test="${traderType=='USER'}"> --%>
					<li><a href="<%=request.getContextPath() %>/report/reportJSP" class="btn">Report</a></li>
					<%-- </c:if> --%>
					<li><a href="<%=request.getContextPath() %>/account/accountList">Accounts</a></li>
		      </ul>
		      <div class="clearfix"></div>       
		    </div>
		</nav>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
 