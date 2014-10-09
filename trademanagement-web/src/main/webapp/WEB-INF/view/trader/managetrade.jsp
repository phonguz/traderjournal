<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script>
<link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>   
<script src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>  
<link href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.css" rel='stylesheet' type='text/css'>
<head>
<script type="text/javascript">
		/* function onDel(id){
			var agree=confirm("Are you sure to delete trade ?");
			if(agree){
				document.getElementById("refId").value = id;
				$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/remove");
				$("#tradeMng").submit();
			}else{
			    return false;
			}
		} */
		function onEdit(id){
			document.getElementById("refId").value = id;
			$("#tradeMng").attr("method","POST");
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/gettrade");
			document.getElementById("tradeMng").submit();
		}
		function onEvent(id){
			document.getElementById("refId").value = id;
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/addevent");
			$("#tradeMng").submit();
		}
		function onEventView(id){
			document.getElementById("refId").value = id;
			$("#tradeMng").attr("method","POST");
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/eventAttachment");
			$("#tradeMng").submit();
		}
		function printReport(id){
			document.getElementById("refId").value = id;
			//$("#tradeMng").attr("method","GET");
			//$("#tradeMng").attr("action","${pageContext.request.contextPath}/report/reportPDFTrade");
			window.open('${pageContext.request.contextPath}/report/reportPDFTrade?refId='+id, '_blank');
			//$("#tradeMng").submit();
		 
			 
		}
	</script>
</head>
<div class="container tab-pane active">
	<c:if test="${not empty SUCCESS_MSG}">
		<div class="alert alert-success">${SUCCESS_MSG}</div>
	</c:if>
	<div id="horizontalTab">
		<ul class="resp-tabs-list">
			<li  class="resp-tab-item resp-tab-active">Trades List</li>
		</ul>
		<!-- <label class="resp-tab-item">Trades List </label> -->
		<a href="${pageContext.request.contextPath}/trade/create" class="btn_blue" style="float: right;">Create new trade</a>
		<div class="clr"></div>
		<div class="resp-tabs-container">
			<div class="starter-template resp-tab-content resp-tab-content-active">
				<div class="table-responsive">
					<form name="tradeMng" id="tradeMng"
						action="${pageContext.request.contextPath}/trade/gettrade"
						method="post">
						<!-- <table
							class="table table-bordered table-hover table-striped tablesorter datatable tftable"
							id="userTable"> -->
							<table class="table table-bordered table-hover table-striped tablesorter tftable" id="userTable">
							<thead>
								<tr>
									<c:if test="${traderType=='ADMIN'}">
										<th class="header" style="background-color: #3B5998">Trader
										</th>
									</c:if>
									<th class="header" style="background-color: #3B5998">Account
									</th>
									<th class="header" style="background-color: #3B5998">Instrument
									</th>
									<th class="header" style="background-color: #3B5998">Qty.
									</th>
									<th class="header" style="background-color: #3B5998">Open
										price</th>
									<th class="header" style="background-color: #3B5998">Close
										price</th>
									<th class="header" style="background-color: #3B5998">Stop
										loss</th>
									<th class="header" style="background-color: #3B5998">Open
										date</th>
									<th class="header" style="background-color: #3B5998">Close
										date</th>
									<!-- <th class="header" style="background-color:#3B5998"><label class="add_delete"><a href="#"><img src="/trademanagement-web/resources/images/add_white.png" alt=""></a><a class="delete_icon" href="#"><img src="/trademanagement-web/resources/images/delete_white.png" alt="delete_icon"></a></label></th> -->
									<th class="header" style="background-color: #3B5998"><label
										class="add_delete"><i class="fa fa-edit fa-2x"></i>&nbsp;&nbsp;<i
											class="fa fa-trash-o fa-2x"></i></label></th>
									<th class="header" style="background-color: #3B5998;"><div style="text-align: center;">Event</div></th>
									<th class="header" style="background-color: #3B5998;"></th>
									<!-- <th class="header" style="background-color: #3B5998;"></th> -->
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${tradeList}" var="trades">
									<tr>
										<c:if test="${traderType=='ADMIN'}">
											<td><c:out value="${trades.getTrader().getFirstname()}" /></td>
										</c:if>
										<td class="gray_table"><c:out
												value="${trades.getAccounts().getName()}" /></td>
										<td><c:out value="${trades.getInstrument().getName()}" /></td>
										<td class="gray_table"><c:out value="${trades.quantity}" /></td>
										<td><c:out value="${trades.openprice}" /></td>
										<td class="gray_table"><c:out
												value="${trades.closeprice}" /></td>
										<td><c:out value="${trades.stoploss}" /></td>
										<fmt:formatDate value="${trades.opentradedate}"
											pattern="MM/dd/yyyy" var="opendate" />
										<td class="gray_table"><c:out value="${opendate}" /></td>
										<fmt:formatDate value="${trades.closetradedate}"
											pattern="MM/dd/yyyy" var="closedate" />
										<td><c:out value="${closedate}" /></td>
										<td>
											<label class="add_delete"> <%-- <a onclick="onEdit(${trades.id});" class="btn"><img src="/trademanagement-web/resources/images/edit.png" alt=""></a> --%>
												<a onclick="onEdit(${trades.id});" class="btn" title="Edit Trade"><i class="fa fa-edit fa-2x"></i></a> 
												<%-- <a class="delete_icon btn" onclick="onDel(${trades.id});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a> --%>
												<a class="delete_icon btn" title="Delete Trade" id="${trades.id}"><i class="fa fa-trash-o fa-2x"></i></a>
											</label></td>
										<td class="gray_table" style="width: 64px !important"><a onclick="onEvent(${trades.id});" class="btn"
											title="Add new Trade Event" style="padding-left: 2px; padding-right: 2px;"><i class="fa fa-plus fa-2x"></i></a>
											<a onclick="onEventView(${trades.id});" class="btn"
											title="View Events" style="padding-left: 2px; padding-right: 2px;"><i class="fa fa-tasks fa-2x"></i></a>
										</td>
										<%-- <td class="gray_table"><a onclick="onEventView(${trades.id});" class="btn"
											title="View"><i class="fa fa-tasks fa-2x"></i></a></td> --%>
										<td><a onclick="printReport(${trades.id});" class="btn"
											title="Generate Report in PDF"><i class="fa fa-bar-chart-o fa-2x"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" id="refId" name="refId">
					</form>
					<!-- --------------------------------------------------------------- -->
					<!-- <div id="pager" style="position: none;">
					<form>
						<div style="padding: 10px 0px; width: 360px; margin: 0px auto;">
							<div style="float: left; margin-right: 10px;">
								<input type="button" class="first btn btn-primary" value="First">
								<input type="button" class="prev btn btn-primary" value="Prev">
							</div>
							<div style="float: left; margin-right: 10px;">
								<label class="pagedisplay"
									style="float: left; margin-right: 10px;"></label> <input
									type="button" class="next btn btn-primary" value="Next">
								<input type="button" class="last btn btn-primary" value="Last">
							</div>
							<div style="float: left;">
								<select class="pagesize" style="width: 60px">
									<option selected="selected" value="5">5</option>
									<option value="10">10</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="40">40</option>
								</select>
							</div>
							<div style="clear: both"></div>
						</div>
					</form>
				</div> -->
				<!-- <div>
					<form action="">					
					<table width="70%" style="border: 3px;background: rgb(243, 244, 248);"><tr><td>
					    <table id="example" class="display" cellspacing="0" width="100%">
					        <thead>
					            <tr>
					                <th>Trader</th>
					                <th>Account</th>
					                <th>Instrument</th>
					                <th>Quantity</th>
					                <th>Open price</th>
					                <th>Close price</th>
					            </tr>
					        </thead>       
						</table>
					 </td></tr></table>
					</form>
				</div> -->
					<!-- --------------------------------- ---------------------------- -->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$('.delete_icon').click(function() {
	var ids = this.id;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure you want to delete this trade?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	document.getElementById("refId").value = ids;
				$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/remove");
				$("#tradeMng").submit();
            }
        }]
    });
});
$(document).ready(function() {
	//window.setTimeout("loadPagination()",50);    
	 $('#userTable').dataTable({
	       "pagingType": "full_numbers"
	 });
});

 //function loadPagination()
 //{

//	 jQuery('#table1').dataTable();
	//   jQuery('#userTable').dataTable({
	  //    "sPaginationType": "full_numbers"
	   // });
	   // jQuery("select").chosen({
	     // 'min-width': '100px',
	     // 'white-space': 'nowrap',
	     // disable_search_threshold: 10
	    //});
	    
	   /*  // Delete row in a table
	    jQuery('.delete-row').click(function(){
	      var c = confirm("Continue delete?");
	      if(c)
	        jQuery(this).closest('tr').fadeOut(function(){
	          jQuery(this).remove();
	        });
	        
	        return false;
	    }); */
	    
	    // Show aciton upon row hover
	  //  jQuery('.table-hidaction tbody tr').hover(function(){
	    //  jQuery(this).find('.table-action-hide a').animate({opacity: 1});
	   // },function(){
	     // jQuery(this).find('.table-action-hide a').animate({opacity: 0});
	   // });
	// }
 </script>