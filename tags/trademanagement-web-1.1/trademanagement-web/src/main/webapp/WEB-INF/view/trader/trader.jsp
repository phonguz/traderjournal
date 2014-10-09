<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container">
    <div class="resp-tabs-container">
        <div class="tab_view_most">
          <div class="form row">
            <div class="form_lft col-md-7">
            	<form:form role="form" id="traderForm" action="${pageContext.request.contextPath}/Trader/save" modelAttribute="traderBean" method="post">
				        <spring:hasBindErrors name="traderBean">
							<c:if test="${not empty errors.globalError}">
								<div class="alert alert-danger">${errors.globalError.defaultMessage}</div>
							</c:if>
						</spring:hasBindErrors>
						<c:if test="${not empty SUCCESS_MSG}">
							<div class="alert alert-success">${SUCCESS_MSG}</div>
						</c:if>
	                <div class="form_row">
		                <form:label for="username" class="name_txt" path="username">Username <font color="red">*</font></form:label>
						<form:input type="email" class="add_name" id="username" name="username" path="username" required="required" ></form:input>
			        	<form:errors path="username" element="div" cssClass="alert alert-danger"></form:errors>
			        </div>
			        <div class="form_row">	
			        	<form:label for="firstname" class="name_txt" path="firstname">Firstname <font color="red">*</font></form:label>
						<form:input type="text" class="add_name" id="firstname" name="firstname" path="firstname" required="required"/>
						<form:errors path="firstname" element="div" cssClass="alert alert-danger"></form:errors>
					</div>
					<div class="form_row">	
						<form:label for="lastname" class="name_txt" path="lastname">Lastname <font color="red">*</font></form:label>
						<form:input type="text" class="add_name" id="lastname" name="lastname" path="lastname" required="required"/>
						<form:errors path="lastname" element="div" cssClass="alert alert-danger"></form:errors>
					</div>									
					<c:if test="${traderBean.isUpdateMode()}">
						<div class="form_row">     
					        <form:label for="oldPassword" class="name_txt" path="oldPassword">Old password <font color="red">*</font></form:label>
							<form:password class="add_name" id="oldPassword" name="oldPassword" path="oldPassword"></form:password>
							<form:errors path="oldPassword" element="div" cssClass="alert alert-danger"></form:errors>
						</div>
					</c:if>
					<div class="form_row" id="passDiv">	
				        <form:label for="password" class="name_txt" path="password">Password <font color="red">*</font></form:label>
						<form:password class="add_name" id="password" name="password" path="password" pattern='(?=.*[0-9]{2})(?=.*[a-zA-Z]{2})(?!.*[!@#$%&*()_+=|<>?{}.:;"~`])[a-zA-Z0-9]{8,}' 
									title="Password must have a minimum of 8 characters, include at least 2 numbers and 2 letters."></form:password>
						<form:errors path="password" element="div" cssClass="alert alert-danger"></form:errors>
				   	</div>
					<div class="form_row">     
				        <form:label for="confirmPassword" class="name_txt" path="confirmPassword">Confirm Password <font color="red">*</font></form:label>
						<form:password class="add_name" id="confirmPassword" name="confirmPassword" path="confirmPassword" pattern='(?=.*[0-9]{2})(?=.*[a-zA-Z]{2})(?!.*[!@#$%&*()_+=|<>?{}.:;"~`])[a-zA-Z0-9]{8,}' 
									title="Password must have a minimum of 8 characters, include at least 2 numbers and 2 letters."></form:password>
						<form:errors path="confirmPassword" element="div" cssClass="alert alert-danger"></form:errors>
					</div>	
	                <div class="button_back"> <button class="btn_blue">Save</button><a href="${pageContext.request.contextPath}/Trader/gettraders" class="btn_blue"> cancel</a> </div>
	                <div class="clearfix"></div>
	                <form:input type="hidden" id="updateMode" name="updateMode" path="updateMode"/>
	                <font color="red">(*) </font> Mark field required
	            </form:form>
            </div>
          </div>
    	</div>
	</div>
</div>
<script type="text/javascript">
	$(window).load(function(){
		$("#username").focus();
		var mode = ${traderBean.isUpdateMode()};
		if(!mode){
			//alert("mode : " + mode);
			$("#username").val("");
			//$("#lastname").val(null);
			$("#oldPassword").val("");
			$("#password").val("");
			$("#confirmPassword").val("");
		}else{
			$("#oldPassword").val("");
			$("#password").val("");
			$("#confirmPassword").val("");
		}
	});
	function checkLength(){
		/* if($("#username").val().length < 1){
			alert("username : " + $("#username").val());
		}else if($("#password").val().length <=5){
			$("#passDiv").append("<div id='jError' class='alert alert-danger'>Minimum 6 character required</div>");
		}else{
			$("#jError").remove(); */
			$("#traderForm").attr("action","${pageContext.request.contextPath}/Trader/save");
			$("#traderForm").submit();
		/* } */
	}
</script>