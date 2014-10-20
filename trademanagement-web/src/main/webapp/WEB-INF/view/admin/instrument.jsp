<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script>
<link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel="stylesheet">
	<div class="container">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="boxs smrgbtm">
					
					<form class="form-horizontal" role="form" method="post"
						action="${pageContext.request.contextPath}/admin/saveInstrument" name="instrumentForm" id="instrumentForm">
						
						<div class="errorDiv" style="width: 30%;">
							<c:if test="${not empty error}">
								<label class="error">${error}</label>
							</c:if>
						</div>
						<c:if test="${not empty success}">
							<div class="success" style="width: 30%;">${success}</div>
						</c:if>
						<div class="clr"></div>


						<div class="form-group">
							<label class="col-sm-5 control-label">Instrument Name</label>
							<div class="col-sm-4">
								<input type="text" size="100" class="form-control" id="instrumentName" name="instrumentName" 
								placeholder="Enter Instrument Name" title="Enter Alphanumeric of length 3 to 25" required>
								<input type="hidden" id="instrumentId" name="instrumentId">
							</div>
						</div>
						<div class="form-group">
							<label for="currency" class="col-sm-5 control-label">Select Currency:</label>
							<div class="col-sm-4">
							<select class="form-control" id="ccyId" name="ccyId" required>
								<option selected value="">Select Currency</option>
								<c:forEach items="${currencyList}" var="ccy">
									<option value="${ccy.id}">${ccy.name}</option>
								</c:forEach>
							</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Google Code Id (Finance.google.com)</label>
							<div class="col-sm-4">
								<input type="text" size="100" class="form-control" id="google_code_id" name="google_code_id" 
								placeholder="Enter google code id" title="Enter Alphanumeric of length 3 to 20" required>
							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Google Exchange Id (Finance.google.com)</label>
							<div class="col-sm-4">
								<input type="text" size="100" class="form-control" id="google_exchange_id" name="google_exchange_id" 
								placeholder="Enter google exchange id" title="Enter Alphanumeric of length 3 to 20" required>
							
							</div>
						</div>
						<div class="form-group">
						<div class="col-sm-5"></div>
							<div class="col-sm-3">
								<button type="submit" class="btn btn-primary" name="save" id="save">Save</button>
								<button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</button>
								<a href="/trademanagement-web/"><button type="button" class="btn btn-default">Cancel</button></a>
								<button type="reset" class="btn btn-default" value="Reset" onclick="clearInstrument();">Reset</button>
							</div>
						</div>
					</form>
				</div>
				<div class="boxs smrgbtm">
					<table width="45%" cellpadding="0" cellspacing="0" border="0" class="datatable" id="instrumentTable" style="margin: 10px 0 20px 55px;">
						<tr>
							<th>Instrument Name</th>
							<th>Currency Name</th>
							<th>Google Code Id</th>
							<th>Google Exchange Id</th>
							<th>Last</th>
							<th><i class="fa fa-edit fa-2x"></i></th>
							<th><i class="fa fa-trash-o fa-2x"></i></th>
						</tr>
						<c:forEach var="instrument" items="${instrumentList}">
							<tr>
								<td class="wrapword" name="instrumentName" id="instrumentName" value="${instrument.name}">${instrument.name}</td>
								<td name="instrumentName" id="instrumentName" value="${instrument.ccy.name}">${instrument.ccy.name}</td>
								<td name="google_code_id" id="google_code_id" value="${instrument.google_code_id}">${instrument.google_code_id}</td>
								<td name="google_exchange_id" id="google_exchange_id" value="${instrument.google_exchange_id}">${instrument.google_exchange_id}</td>
								<td><a id="editInstrument" name="editInstrument"  onclick="editInstrument('${instrument.id}','${instrument.name}','${instrument.ccy.id}','${instrument.google_code_id}','${instrument.google_exchange_id} }');" style="cursor: pointer;" title="Edit Instrument"><i class="fa fa-edit fa-2x"></i></a>
								<input type="hidden" value="${instrument.id}" name="instrumentId" id="instrumentId"></td>
								<td><a class="delete_icon btn" style="cursor: pointer;" id="${instrument.id},${instrument.name}" title="Delete Instrument"><i class="fa fa-trash-o fa-2x"></i></a></td>
							</tr>
						</c:forEach>
						<c:if test="${empty instrumentList}">
							<tr>
								<td colspan="3"><label style="margin-left:100px;">No data available</label></td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
$('.delete_icon').click(function() {
	var ids = this.id.split(',');
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure want to delete "'+ids[1]+'" from Instrument?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	$("#instrumentId").val(ids[0]);
        		$("#instrumentForm").attr("action","${pageContext.request.contextPath}/admin/deleteInstrument");
        		$("#instrumentForm").submit();
            }
        }]
    });
});
function editInstrument(id, name, ccyId,googleCodeID,google_exchange_id){
	$("#instrumentName").val(name);
	$("#instrumentId").val(id);
	$("#ccyId").val(ccyId);
	$("#google_code_id").val(googleCodeID);
	$("#google_exchange_id").val(google_exchange_id);
	
	
	$("#update").css("display","");
	$("#save").css("display","none");
	$("#instrumentForm").attr("action","${pageContext.request.contextPath}/admin/updateInstrument");
}

/* function deleteInstrument(id, name){
	if(confirm("Are you sure want to delete "+name+" from Instrument?")){
		$("#instrumentId").val(id);
		$("#instrumentForm").attr("action","${pageContext.request.contextPath}/admin/deleteInstrument");
		$("#instrumentForm").submit();
	}
} */
function clearInstrument(){
	$("#instrumentName").val('');
	$("#instrumentId").val('');
	$("#ccyId").val('selected');
	$("#update").css("display","none");
	$("#save").css("display","");
	$("#currencyForm").attr("action","${pageContext.request.contextPath}/admin/saveInstrument");	
}
$(document).ready(function(){
	$(".success").fadeOut(3000);
});
</script>
<style>
	.errorDiv{
		width:30%;
		margin: 7px 7px 0px 195px !important;
	}
	.success{
		width:30%;		
		margin: 7px 7px 0px 195px !important;
	}
</style>