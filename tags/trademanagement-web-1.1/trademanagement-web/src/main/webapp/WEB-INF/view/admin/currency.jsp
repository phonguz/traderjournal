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
						action="${pageContext.request.contextPath}/admin/saveCurrency" name="currencyForm" id="currencyForm">
						
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
							<label class="sr-only" for="Currency Name">Currency Name</label>
							 <input type="text" class="form-control" id="ccyName" name="ccyName" placeholder="Enter Currency Name" title="Enter only Character of length 3 to 25" required>
							 <input type="hidden" id="ccyId" name="ccyId">
						</div>
						<button type="submit" class="btn btn-primary" name="save" id="save">Save</button>
						<button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</button>
						<a href="/trademanagement-web/"><button type="button" class="btn btn-default">Cancel</button></a>
						<button type="reset" class="btn btn-default" value="Reset" onclick="clearCurrency();">Reset</button>
					</form>
				</div>
				<div class="boxs smrgbtm">
					<table width="30%" cellpadding="0" cellspacing="0" border="0" class="datatable" id="currencyTable" style="margin: 10px 0 20px 0;">
						<tr>
							<th>Currency Name</th>
							<th><i class="fa fa-edit fa-2x"></i></th>
							<th><i class="fa fa-trash-o fa-2x"></i></th>
						</tr>
						<c:forEach var="currencyList" items="${currencyList}">
							<tr>
								<td class="wrapword" name="currencyName" id="currencyName" value="${currencyList.name}" style="word-break:break-all;">${currencyList.name}</td>
								<td><a id="editCurrency" name="editCurrency" onclick="editCurrency('${currencyList.id}','${currencyList.name}');" style="cursor: pointer;" title="Edit Currency"><i class="fa fa-edit fa-2x"></i></a>
								<input type="hidden" value="${currencyList.id}" name="currencyId" id="currencyId"></td>
								<td><a class="delete_icon btn" name="${currencyList.name}" id="${currencyList.id}" style="cursor: pointer;" title="Delete Currency"><i class="fa fa-trash-o fa-2x"></i></a></td>
							</tr>
						</c:forEach>
						<c:if test="${empty currencyList}">
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
	var ids = this.id;
	var name = this.name;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure want to delete "'+name+'" from currency?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	$("#ccyId").val(ids);
        		$("#currencyForm").attr("action","${pageContext.request.contextPath}/admin/deleteCurrency");
        		$("#currencyForm").submit();
            }
        }]
    });
});
function editCurrency(id, name){
	$("#ccyName").val(name);
	$("#ccyId").val(id);
	$("#update").css("display","");
	$("#save").css("display","none");
	$("#currencyForm").attr("action","${pageContext.request.contextPath}/admin/updateCurrency");
}

/* function deleteCurrency(id, name){
	if(confirm("Are you sure want to delete "+name+" from currency?")){
		$("#ccyId").val(id);
		$("#currencyForm").attr("action","${pageContext.request.contextPath}/admin/deleteCurrency");
		$("#currencyForm").submit();
	}
} */
function clearCurrency(){
	$("#ccyName").val('');
	$("#ccyId").val('');
	$("#update").css("display","none");
	$("#save").css("display","");
	$("#currencyForm").attr("action","${pageContext.request.contextPath}/admin/saveCurrency");	
}
$(document).ready(function(){
	$(".success").fadeOut(3000);
});
</script>
<style>
.error{

}
</style>