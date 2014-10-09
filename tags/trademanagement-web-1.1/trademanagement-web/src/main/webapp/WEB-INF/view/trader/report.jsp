<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="container">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="boxs smrgbtm">
					<div class="clr"></div>
    				<div class="resp-tabs-container">        
       				<div class="starter-template resp-tab-content resp-tab-content-active">
          			<div class="table-responsive">
					<form id="individual" target="_blank" method="GET" onsubmit="return sharedId();">
					<div class="errorDiv"></div>
					<table id="sharedList">
				 	
						<tr><td><input name="radioButton" id="r1" type="radio"  onclick="checkRadio(this);" checked="checked"/><label style="color:#428bca">Trader Account</label></td>
					   <c:if test="${traderType=='USER'}">
						<tr><td><input name="radioButton" id="r3" type="radio" onclick="checkRadio(this);"/><label style="color:#428bca">Include Shared Accounts</label></td></tr>
						</c:if>
						<c:if test="${traderType=='ADMIN'}">
						<tr><td><input name="radioButton" id="r3" type="radio" onclick="checkRadio(this);"/><label style="color:#428bca">All Trader Accounts</label></td></tr>
						</c:if>
						
						<tr><td><input name="radioButton" id="r2" type="radio"  onclick="checkRadio(this);"/><label style="color:#428bca">Specific Trade Accounts</label></td></tr>
						<tr id="chList"></tr>
						<tr><td><input  type="submit" name="submit"></td></tr>
						</table>
					</form>	
					</div>
					</div>
					</div>				 
				</div>
			</div>
		</div>
</div>
 <script type="text/javascript">
 $('#select-all').click(function(event) {   
	    if(this.checked) {
	        // Iterate each checkbox
	        $(':checkbox').each(function() {
	            this.checked = true;                        
	        });
	    }
	    else{
	    	$(':checkbox').each(function() {
	            this.checked = false;                        
	        });
		}
});
/* function generateReport(){
	var id=$('input:checked').attr('id');
	alert($('input:checked').attr('id'));
	if(id=="r2"){
		var sharedIds = document.getElementsByName("sharedid");
		var ids=0;
		for(var i=0;i<sharedIds.length;i++){
			if(sharedIds[i].checked){
				ids++;
			}
		}
		if(ids == 0){
			alert("Please Select.");
			return false;
		}
} */
	
function checkRadio(th){
	var id = th.id;
	if(id=="r2"){
		$("#chList").show();
		$(':checkbox').each(function() {
            this.checked = false;                        
        });
	}else if(id=="r1"){
		$("#chList").hide();
		$(':checkbox').each(function() {
            this.checked = false;                        
        });
	}else if(id=="r3"){
		$("#chList").hide();
		}
}
function sharedId(){
  
	var r1=false;
	var r2=false;
	var r3=false;
	if(document.getElementById("r1").checked)
		{
		r1=true;
		
		}
	if(document.getElementById("r2").checked)
	{
		 var sharedIds = document.getElementsByName("sharedid");
		var ids=0;
		for(var i=0;i<sharedIds.length;i++){
			if(sharedIds[i].checked){
				ids++;
			}
		}
		if(ids == 0){
			if($('.error').length==0){
				$('.errorDiv').append('<label class="error">Please select at least one Trader Account</label>');
			}
			return false;
		}	
		else{  
			r2=true;
		 }
	}
	if(document.getElementById("r3").checked)
	{
	r3=true;
	
	}
	if(r1==true)
		{
		$("#individual").attr("action","<%=request.getContextPath() %>/report/reportPDFAll");
		}
	else if(r2==true)
		{
		$("#individual").attr("action","<%=request.getContextPath() %>/report/reportIndividual");
		}
	else if(r3==true)
		{
        $(':checkbox').each(function() {
            this.checked = true;                        
        });
		$("#individual").attr("action","<%=request.getContextPath() %>/report/reportIndividual");
		}
		
}
$(document).ready(function() {
	$.ajax({
	    url: "${pageContext.request.contextPath}/report/reportPDFParts",
	    type: 'POST',
	    dataType: 'json',
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(dataJson) {
	    	if(dataJson.length==0){
	    		$('#individual').append('<span style="list-style: none outside none; margin: 0; padding: 0 14px 14px; display: inline-block;"> No attachments available.</span>');
	    	}else{
		    	for(var i=0; i<dataJson.length; i++){
					$('#chList').append('<tr><td><INPUT TYPE="checkbox" NAME="sharedid" VALUE="'+dataJson[i].id+'"/>' + dataJson[i].firstName +' ('+dataJson[i].accountName +')</td></tr>');
					//$('#chList').append(dataJson[i].firstName +" ("+ dataJson[i].accountName +")</td></tr>");
		    	}
		    	//$('#sharedList').append('<hr>');
	    	}
	    },
	    error:function(data,status,er) {
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
	$("#chList").hide();
	
});
</script>