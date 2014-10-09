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
						action="${pageContext.request.contextPath}/Trader/saveEventType" name="eventTypeForm" id="eventTypeForm">
						
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
							<label class="sr-only" for="Currency Name">Event Type</label>
							 <input type="text" class="form-control" id="eventTypeName" name="eventTypeName" placeholder="Enter Event Type" title="Enter Alphanumeric of length 3 to 200" required>
							 <input type="hidden" id="eventId" name="eventId">
						</div>
						<button type="submit" class="btn btn-primary" name="save" id="save">Save</button>
						<button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</button>
						<a href="/trademanagement-web/"><button type="button" class="btn btn-default">Cancel</button></a>
						<button type="reset" class="btn btn-default" value="Reset" onclick="clearEventType();">Reset</button>
					</form>
				</div>
				<div class="boxs smrgbtm">
					<table width="30%" cellpadding="0" cellspacing="0" border="0" class="datatable" id="eventTypeTable" style="margin: 10px 0 20px 0;">
						<tr>
							<th>Event Type</th>
							<th><i class="fa fa-edit fa-2x"></i></th>
							<th><i class="fa fa-trash-o fa-2x"></i></th>
						</tr>
						<c:forEach var="list" items="${tradeEventTypeList}">
							<tr>
								<td class="wrapword" name="eventtypeName" id="eventtypeName" value="${list.name}" style="word-break:break-all;">${list.name}</td>
								<td><a id="editEventType" name="editEventType" onclick="editEventType('${list.id}','${list.name}');" style="cursor: pointer;" title="Edit Event Type"><i class="fa fa-edit fa-2x"></i></a>
								<input type="hidden" value="${list.id}" name="eventtypeId" id="eventtypeId"></td>
								<td><a class="delete_icon btn" id="${list.id},${list.name}" style="cursor: pointer;" title="Delete Event Type"><i class="fa fa-trash-o fa-2x"></i></a></td>
							</tr>
						</c:forEach>
						<c:if test="${empty tradeEventTypeList}">
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
        message: 'Are you sure want to delete "'+ids[1]+'" from Trade Event type?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	$("#eventId").val(ids[0]);
        		$("#eventTypeForm").attr("action","${pageContext.request.contextPath}/Trader/deleteEventType");
        		$("#eventTypeForm").submit();
            }
        }]
    });
});
function editEventType(id, name){
	$("#eventTypeName").val(name);
	$("#eventId").val(id);
	$("#update").css("display","");
	$("#save").css("display","none");
	$("#eventTypeForm").attr("action","${pageContext.request.contextPath}/Trader/updateEventType");
}

/* function deleteEventType(id, name){
	if(confirm("Are you sure want to delete "+name+" from Trade Event type?")){
		$("#eventId").val(id);
		$("#eventTypeForm").attr("action","${pageContext.request.contextPath}/Trader/deleteEventType");
		$("#eventTypeForm").submit();
	}
} */
function clearEventType(){
	$("#eventTypeName").val('');
	$("#eventId").val('');
	$("#update").css("display","none");
	$("#save").css("display","");
	$("#eventTypeForm").attr("action","${pageContext.request.contextPath}/Trader/saveEventType");	
}
$(document).ready(function(){
	$(".success").fadeOut(3000);
}); 
</script>