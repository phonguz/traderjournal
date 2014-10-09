<!-- Fixed navbar -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--<a class="navbar-brand" href="#">Bootstrap theme</a>-->
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#"><span class="crm-ico"></span>CRM</a>
					<ul class="submenu">
						<li><a href="Company">Company</a></li>
						<li><a href="Guest">Guest</a></li>
						<li><a href="#">Dashboard</a></li>
					</ul></li>
				<li><a href="#"><span class="crm-ico"></span>ERP</a>
					<ul class="submenu">
						<li><a href="#">Booking</a></li>
						<li><a href="#">Service</a></li>
						<li><a href="HKeep">House-Keeping</a></li>
					</ul></li>
				<li><a href="#"><span class="fin-ico"></span>Finance</a>
					<ul class="submenu">
						<li><a href="#">Billing</a></li>
						<li><a href="#">Payment</a></li>
						<li><a href="#">Write-Off</a></li>
						<li><a href="#">Deposit</a></li>
						<li><a href="#">Return</a></li>
						<li><a href="#">Expense</a></li>
						<li><a href="#">Journal</a></li>
					</ul></li>
				<li><a href="#"><span class="com-ico"></span>Common</a>
					<ul class="submenu">
						<li><a href="Room">Room</a></li>
						<li><a href="#">Document</a></li>
						<li><a href="#">Report(Pre-set)</a></li>
					</ul></li>
					<c:if test="${userRole == 3}">
				<li><a href="#"><span class="set-ico"></span>Settings</a>
					<ul class="submenu">
						<li><a href="Settings">User Management</a></li>
						<li><a href="#">Global Setting</a></li>
						<li><a href="#">Chart Of Account</a></li>
						<li><a href="#">Data Import/Export</a></li>
						<li><a href="#">Field Management</a></li>
						<li><a href="#">SQL Query</a></li>
						<li><a href="#">Price Rule</a></li>
					</ul></li>
					</c:if>
				<li><a href="#"><span class="hel-ico"></span>Help</a></li>
				
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>