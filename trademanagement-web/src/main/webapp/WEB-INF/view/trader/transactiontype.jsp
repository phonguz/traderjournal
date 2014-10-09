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
					
					<form class="form-inline" role="form" method="post"
						action="${pageContext.request.contextPath}/Trader/saveTransactionType" name="transactionTypeForm" id="transactionTypeForm">
						
						<div class="errorDiv" style="width: 37%;">
							<c:if test="${not empty error}">
								<label class="error">${error}</label>
							</c:if>
						</div>
						<c:if test="${not empty success}">
							<div class="success" style="width: 37%;">${success}</div>
						</c:if>
						<div class="clr"></div>
						
						<div class="form-group">
							<label class="sr-only" for="Transaction Type">Transaction Type</label>
							<br>Name <input type="text" class="form-control" id="transactionType" name="transactionType" placeholder="Enter Transaction Type" title="Enter Alphanumeric of length 1 to 200" required><br>
							 Action<input type="text" class="form-control" id="transactionTypeAction" name="transactionTypeAction" placeholder="+ or -" title="Enter + or -" required>
							 <input type="hidden" id="transactionTypeId" name="transactionTypeId">
						</div>
						<br>
						<button type="submit" class="btn btn-primary" name="save" id="save">Save</button>
						<button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</button>
						<a href="/trademanagement-web/"><button type="button" class="btn btn-default">Cancel</button></a>
						<button type="reset" class="btn btn-default" value="Reset" onclick="clearTransactionType();">Reset</button>
					</form>
				</div>
				<div class="boxs smrgbtm">
					<table width="30%" cellpadding="0" cellspacing="0" border="0" class="datatable" id="transactionTypeTable" style="margin: 10px 0 20px 0;">
						<tr>
							<th>Transaction Type</th>
							<th>Action</th>
							<th><i class="fa fa-edit fa-2x"></i></th>
							<th><i class="fa fa-trash-o fa-2x"></i></th>
						</tr>
						<c:forEach var="list" items="${transactionTypeList}">
							<tr>
								<td class="wrapword" name="transactiontypeName" id="transactiontypeName" value="${list.type}" style="word-break:break-all;">${list.type}</td>
								<td class="wrapword" name="transactiontypeAction" id="transactiontypeAction" value="${list.action}" style="word-break:break-all;">${list.action}</td>
								<td><a id="editTransactionType" name="editTransactionType" onclick="editTransactionType('${list.id}','${list.type}','${list.action}');" style="cursor: pointer;" title="Edit Transaction Type"><i class="fa fa-edit fa-2x"></i></a>
								<input type="hidden" value="${list.id}" name="transactionId" id="transactionId"></td>
								<td><a class="delete_icon btn" id="${list.id},${list.type}" style="cursor: pointer;" title="Delete Transaction Type"><i class="fa fa-trash-o fa-2x"></i></a></td>
							</tr>
						</c:forEach>
						<c:if test="${empty transactionTypeList}">
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
        message: 'Are you sure want to delete "'+ids[1]+'" from Transaction type?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	$("#transactionTypeId").val(ids[0]);
        		$("#transactionTypeForm").attr("action","${pageContext.request.contextPath}/Trader/deleteTransactionType");
        		$("#transactionTypeForm").submit();
            }
        }]
    });
});
function editTransactionType(id, name,action){
	$("#transactionType").val(name);
	$("#transactionTypeAction").val(action);
	$("#transactionTypeId").val(id);
	$("#update").css("display","");
	$("#save").css("display","none");
	$("#transactionTypeForm").attr("action","${pageContext.request.contextPath}/Trader/updateTransactionType");
}

/* function deleteTransactionType(id, name){
	if(confirm("Are you sure want to delete "+name+" from Transaction type?")){
		$("#transactionTypeId").val(id);
		$("#transactionTypeForm").attr("action","${pageContext.request.contextPath}/Trader/deleteTransactionType");
		$("#transactionTypeForm").submit();
	}
} */
function clearTransactionType(){
	$("#transactionType").val('');
	$("#transactionTypeId").val('');
	$("#update").css("display","none");
	$("#save").css("display","");
	$("#transactionTypeForm").attr("action","${pageContext.request.contextPath}/Trader/saveTransactionType");	
}
$(document).ready(function(){
	$(".success").fadeOut(3000);
}); 
</script>