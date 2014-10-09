<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href='<spring:url value="/resources/images/favicon.ico"/>'>
    <title>Trade Management</title>
  </head>

  <body class="login_page"> --%>
  <div class="login_page">
<div class="navbar navbar-default navbar-static-top" >
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="<%=request.getContextPath() %>">Trade Management</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
              <a href="#" class="dropdown-toggle loing_icon" data-toggle="dropdown">John Penn II</a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    <div>
    	<c:if test="${not empty SUCCESS_MSG}">
			<div class="alert alert-success">${SUCCESS_MSG}</div>
		</c:if>
	</div>
    <div style="color: red;" align="center">${loginFailMsg}</div>
    <div class="container">
      <form class="form-signin panel panel-default" action="<%=request.getContextPath() %>/Login" method="post">
        <div class="panel-body">
	        <h2 class="form-signin-heading">
    		    <a class="login_link" href="<%=request.getContextPath() %>/Login">Login</a>
        		<a class="signup" href="<%=request.getContextPath() %>/Trader/signup">Sign up</a>
	    	    <div class="clearfix"></div>
    	    </h2>
	        <label>Email address</label>
    	    <input type="email" name="username" class="form-control input-sm" placeholder="Email" required autofocus>
        	<label>Password</label>
	        <input type="password" name="password" class="form-control input-sm" placeholder="Password" required>
	        <a style="margin-left: 5px;margin-top: 6px;display:block; cursor: pointer;" id="forgotPassword" onclick="$('#forgotPasswordMsg').html('');" class="mediumLink">forgot your password ?</a>
          <%--   <a class="forgot_password" href=""<%=request.getContextPath() %>/Login/forgotPassword">forgot your password ?</a> --%>
            <div class="clearfix"></div>
        </div>
		<div class="panel-footer">
            <label class="checkbox">
                <input class="uniform" type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="blackbtn" type="submit">Login</button>
		</div>
      </form>
<form style="background: none;">
	<div class="modal fade" id="forgotPass" role="dialog" aria-labelledby="basicModal" aria-hidden="true" tabindex="-1">
		<div class="modal-dialog">
	    	<div class="modal-content">
	    	
	        	<div class="modal-header" style="position: relative;">
	        		<button type="button" style="position: absolute; right: 12px; top: 6px;" class="model-title close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        		<h4 class="modal-title" id="myModalLabel">Password Reminder</h4>
	        	</div>
		    	<div class="modal-body">
		        	<div class="form-group">
						<label>Username <span style="color: red">*</span></label>
						<input type="text" id="usernameForgotPass" class="form-control" name="usernameForgotPass" required/>
					</div>
					<div id="forgotPasswordMsg"></div> 
		        </div>
		        <div class="modal-footer">
					<button class="btn btn-primary btn-lg btn-block" id="forgotPasswordBtn" onclick="forgotPasswordAction()" type="submit">Reset Password</button>
					<div class="form-group" style="padding-top: 10px;float: right;">
						<span style="color: red;">* Mandatory Field</span>
					</div>
		        </div>
		        
	    	</div>
		</div>
	</div>
</form>
    </div> <!-- /container -->
    </div>
 <!--  </body>
</html> -->
<script type="text/javascript">
 $(document).ready(function() {
	 $("#forgotPassword").click(function(){
		 $('#forgotPass').modal('toggle');
	});
 });
 function forgotPasswordAction(){

		if($('#usernameForgotPass').val()==""){
			$('#forgotUsernameMsg').hide();
		}else{
		$.ajax({
			url  : 'Login/forgotPassword?userName='+$('#usernameForgotPass').val(),
			type : 'GET',
			async: false,
			beforeSend : function (){
				$('#forgotPasswordBtn').attr("disabled", true);
				$('#forgotPasswordMsg').css("color","#569ffd");
				$('#forgotPasswordMsg').html("Please Wait...");
			},
			success : function(data) {
				$('#forgotPass').html(data);
				$('#forgotPasswordBtn').attr("disabled", false);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//console.log();
				$('#forgotPasswordBtn').attr("disabled", false);
			}
			
		});
		}
 }
 
 </script>