<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script>
<link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel='stylesheet'>
<script  src="http://code.jquery.com/jquery-1.11.1.min.js"></script>   
<script  src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>  
<link href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.css" rel='stylesheet' type='text/css'>
<head>
	<script type="text/javascript">		
		function onEdit(id,tradeid){
			document.getElementById("eventId").value = id;
			document.getElementById("refId").value = tradeid;
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/addevent");
			document.getElementById("tradeMng").submit();
		}
		function onCreateEvent(id){
			document.getElementById("eventId").value = null;
			document.getElementById("createIn").value = "true";
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/addevent");
			$("#tradeMng").submit();
		}
		/* function onEventView(id){
			document.getElementById("refId").value = id;
			$("#tradeMng").attr("method","GET");
			$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/eventAttachment");
			$("#tradeMng").submit();
		} */
	</script>
</head>

<div class="container tab-pane active">
<c:if test="${not empty SUCCESS_MSG}">
	<div class="alert alert-success">${SUCCESS_MSG}</div>
</c:if>
<div>
<div><h5>Trade</h5></div>
<div class="tradeDiv">
<table class="table table-bordered table-hover table-striped datatable">
		<thead>
			<tr>
				<c:if test="${traderType=='ADMIN'}">
					<th class="header" style="background-color: #3B5998">Trader</th>
				</c:if>
				<th class="header" style="background-color: #3B5998">Account</th>
				<th class="header" style="background-color: #3B5998">Instrument</th>
				<th class="header" style="background-color: #3B5998">Quantity</th>
				<th class="header" style="background-color: #3B5998">Open price</th>
				<th class="header" style="background-color: #3B5998">Close price</th>
				<th class="header" style="background-color: #3B5998">Stop loss</th>
				<th class="header" style="background-color: #3B5998">Open date</th>
				<th class="header" style="background-color: #3B5998">Close date</th>
				<!-- <th class="header" style="background-color:#3B5998"><label class="add_delete"><a href="#"><img src="/trademanagement-web/resources/images/add_white.png" alt=""></a><a class="delete_icon" href="#"><img src="/trademanagement-web/resources/images/delete_white.png" alt="delete_icon"></a></label></th> -->
				<!-- <th class="header" style="background-color: #3B5998"><label class="add_delete"><i class="fa fa-edit fa-2x"></i>&nbsp;&nbsp;<i class="fa fa-trash-o fa-2x"></i></label></th>
				<th class="header" style="background-color: #3B5998;"><div style="text-align: center;">Event</div></th>
				<th class="header" style="background-color: #3B5998;"></th> -->
				<!-- <th class="header" style="background-color: #3B5998;"></th> -->
			</tr>
		</thead>
		<tbody>
		<tr>
			<c:if test="${traderType=='ADMIN'}">
				<td><c:out value="${tradeObj.getTrader().getFirstname()}" /></td>
			</c:if>
			<td class="gray_table"><c:out value="${tradeObj.getAccounts().getName()}" /></td>
			<td><c:out value="${tradeObj.getInstrument().getName()}" /></td>
			<td class="gray_table"><c:out value="${tradeObj.quantity}" /></td>
			<td><c:out value="${tradeObj.openprice}" /></td>
			<td class="gray_table"><c:out value="${tradeObj.closeprice}" /></td>
			<td><c:out value="${tradeObj.stoploss}" /></td>
			<fmt:formatDate value="${tradeObj.opentradedate}" pattern="MM/dd/yyyy" var="opendate" />
			<td class="gray_table"><c:out value="${opendate}" /></td>
			<fmt:formatDate value="${tradeObj.closetradedate}" pattern="MM/dd/yyyy" var="closedate" />
			<td><c:out value="${closedate}" /></td>
			<%-- <td>
				<label class="add_delete"> <a onclick="onEdit(${trades.id});" class="btn"><img src="/trademanagement-web/resources/images/edit.png" alt=""></a>
					<a onclick="onEdit(${tradeObj.id});" class="btn" title="Edit Trade"><i class="fa fa-edit fa-2x"></i></a> <a class="delete_icon btn" onclick="onDel(${trades.id});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a>
					<a class="delete_icon btn" onclick="onDel(${tradeObj.id});" title="Delete Trade"><i class="fa fa-trash-o fa-2x"></i></a>
				</label>
			</td>
			<td class="gray_table" style="width: 64px !important"><a onclick="onEvent(${tradeObj.id});" class="btn" title="Add new Trade Event" style="padding-left: 2px; padding-right: 2px;"><i class="fa fa-plus fa-2x"></i></a>
				<a onclick="onEventView(${tradeObj.id});" class="btn" title="View Events" style="padding-left: 2px; padding-right: 2px;"><i class="fa fa-tasks fa-2x"></i></a>
			</td>
			<td class="gray_table"><a onclick="onEventView(${trades.id});" class="btn" title="View"><i class="fa fa-tasks fa-2x"></i></a></td>
			<td><a onclick="printReport(${tradeObj.id});" class="btn" title="Generate Report in PDF"><i class="fa fa-bar-chart-o fa-2x"></i></a></td> --%>
		</tr>
	</tbody>
</table>
</div>
</div>
  <div id="horizontalTab">
		<ul class="resp-tabs-list">
     	 <li class="resp-tab-item resp-tab-active">Trade Events</li>      
    	</ul>
    <label style="margin-top:8px; margin-left:10% !important;"><i class="fa fa-info-circle fa-2x"></i>   Click on description of event to see attachment.</label>
    <a class="btn_blue btn" style="float:right;" onclick="onCreateEvent();">Create new Event </a>
    <div class="clr"></div>
    <div class="resp-tabs-container">        
       	<div class="starter-template resp-tab-content resp-tab-content-active">
          <div class="">
			<div class="row">
			<div class="col-md-12" align="center">
				<div class="boxs smrgbtm">
				<!-- <div class="page-header">
					<h1>
						Trade Attachment<small>Create new Attachment</small>
					</h1>
				</div> -->
				<div class="table-responsive">
		          <form name="tradeMng" id="tradeMng" method="post">
		            <table class="table table-bordered table-hover table-striped datatable" id="attachTable">
		              <thead>
		                <tr>
		                  <th class="header" style="background-color:#3B5998">Description </th>
		                  <th class="header" style="background-color:#3B5998">Date </th>
		                  <th class="header" style="background-color:#3B5998">New value </th>
		                  <th class="header" style="background-color:#3B5998">Event type </th>
		                  <!-- <th class="header" style="background-color:#3B5998"><label class="add_delete"><a href="#"><img src="/trademanagement-web/resources/images/add_white.png" alt=""></a><a class="delete_icon" href="#"><img src="/trademanagement-web/resources/images/delete_white.png" alt="delete_icon"></a></label></th> -->
		                  <th class="header" style="background-color:#3B5998"><div style="text-align:center;"><i class="fa fa-edit fa-2x"></i>&nbsp;&nbsp;<i class="fa fa-trash-o fa-2x"></i></div></th>                 
		                  <!-- <th class="header" style="background-color:#3B5998"><div style="text-align:center;"><i class="fa fa-picture-o fa-2x"></i></div></th> -->
		                </tr>
		              </thead>
		              <tbody>
		              
		              <c:forEach items="${eventList}" var="events">
			                <tr>
			                  <td style="width:60%; text-align: left;">
			                  	<a onclick="sendAjax(${events.id});" class="btn" title="View Attachments"><c:out value="${events.description}"/></a>
			                  </td>
			                  <fmt:formatDate value="${events.eventdate}" pattern="MM/dd/yyyy" var="eventDate" />
			                  <td class="gray_table"><c:out value="${eventDate}"/></td>
			                  <td><c:out value="${events.newvalue}"/></td>
			                  <td class="gray_table"><c:out value="${events.getTradeeventtype().getName()}"/></td>
			                  <td>
			                  	<label class="add_delete">
			                  		<%-- <a onclick="onEdit(${events.id},${events.getTrade().getId()});" class="btn"><img src="/trademanagement-web/resources/images/edit.png" alt=""></a>                			
			                  		<a class="delete_icon btn" onclick="onDel(${events.id},${events.getTrade().getId()});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a> --%>
			                  		<a onclick="onEdit(${events.id},${events.getTrade().getId()});" class="btn" title="Edit Event"><i class="fa fa-edit fa-2x"></i></a>                			
			                  		<a class="delete_icon btn" title="Delete Event" name="${events.id}"><i class="fa fa-trash-o fa-2x"></i></a>
			                  	</label>
	                  		  </td>
			                  <%-- <td>
			                  		<a onclick="sendAjax(${events.id});" class="btn" title="View Attachments"><i class="fa fa-picture-o fa-2x"></i></a>
			                  		<a href="#" onclick="onDel(${trades.id});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a>
			                  </td> --%>
			                </tr>
		                </c:forEach>
		              </tbody>
		            </table>
		            <input type="hidden" id="eventId" name="eventId">
		            <input type="hidden" id="refId" name="refId" value="${refId}">
		            <input type="hidden" id="createIn" name="createIn">
		            </form>             
          		</div>

				<!-- Modal -->
				<!-- <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" onclick="clearForm();">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Attachments</h4>
							</div>
							<div class="modal-body">
								<ul class="imgList"></ul>
								
								
								<div id="carousel-generic" class="carousel slide" data-ride="carousel">
									  Indicators
									  <ol class="carousel-indicators">
									    <li data-target="#carousel-generic" data-slide-to="0" class="active"></li>
									    <li data-target="#carousel-generic" data-slide-to="1"></li>
									    <li data-target="#carousel-generic" data-slide-to="2"></li>
									  </ol>
									
									  Wrapper for slides
									  <div class="carousel-inner">
									    <div class="item active">
									      <img src="..." alt="...">
									      <div class="carousel-caption">
									        ...
									      </div>
									    </div>
									    <div class="item">
									      <img src="..." alt="...">
									      <div class="carousel-caption">
									        ...
									      </div>
									    </div>
									  </div>
									
									  Controls
									  <a class="custom-slide left carousel-control fa fa-chevron-left fa-2x" href="#carousel-generic" role="button" data-slide="prev">
									    <span class="fa fa-chevron-left fa-2x"></span>
									  </a>
									  <a class="custom-slide right carousel-control fa fa-chevron-right fa-2x" href="#carousel-generic" role="button" data-slide="next">
									    <span class="fa fa-chevron-right fa-2x"></span>
									  </a>
								</div>
								
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
							</div>
						</div>
					</div>
				</div> -->
				<%-- <form class="form-horizontal" role="form" method="post"
						action="${pageContext.request.contextPath}/Trader/saveEventAttachment" name="attachmentForm" id="attachmentForm" enctype="multipart/form-data">
						
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
							<label class="col-sm-5 control-label" for="Attachment Description">Attachment Description</label>
							<div class="col-sm-4">
								<textarea class="form-control" id="attachmentDesc" name="attachmentDesc" placeholder="Enter Attachment Description" title="Enter Alphanumeric" value="${description}" required>${description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label" for="Currency Name">Image File</label>
							<div class="col-sm-4">
								<input type="file" name="imageFile" id="imageFile">
							</div>
							
						</div>
						<c:if test="${update =='true'}">
							<div class="form-group">
								<label class="col-sm-5 control-label" for="Currency Name">Uploaded Image File</label>
								<div class="col-sm-3">
									<img style="width: 200px; height: 100px;" src="${pageContext.request.contextPath}/getImage/${attachmentId}" alt="No any Image uploaded">
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label for="" class="col-sm-5 control-label">Event type</label>
							<div class="col-sm-4">
							<select class="form-control" id="eventId" name="eventId" required="required">
								<option selected="selected" value="">Select event type</option>
								<c:forEach items="${tradeEventTypeList}" var="eventType">
									<option value="${eventType.id}">${eventType.name}</option>
								</c:forEach>
							</select>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-5"></div>
							<div class="col-sm-3">
								<button type="submit" class="btn btn-primary" name="save" id="save">Save</button>
								<button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</button>
								<a href="${pageContext.request.contextPath}"><button type="button" class="btn btn-default">Cancel</button></a>
								<!-- <button type="reset" class="btn btn-default" value="Reset">Reset</button> -->
								<a href="${pageContext.request.contextPath}/Trader/eventAttachment"><button type="button" class="btn btn-default" value="Reset">Reset</button></a>
							</div>
						</div> 
					</form> --%>
				</div>
			</div>
		</div>
		</div>
		</div>
		</div>
	</div>
	<!-- <div id="horizontalTab">
		<div class="resp-tabs-container">
			<div class="starter-template"> 
			<div id="dvLoading" style="display: block;"></div>-->
			<div id="carousel-generic" class="carousel slide" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <!-- <li data-target="#carousel-generic" data-slide-to="0" class="active"></li>
				    <li data-target="#carousel-generic" data-slide-to="1"></li>
				    <li data-target="#carousel-generic" data-slide-to="2"></li> -->
				  </ol>
				
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner">
				    <!-- <div class="item active">
				      <img src="..." alt="...">
				      <div class="carousel-caption">
				        ...
				      </div>
				    </div>
				    <div class="item">
				      <img src="..." alt="...">
				      <div class="carousel-caption">
				        ...
				      </div>
				    </div> -->
				  </div>
				
				  <!-- Controls -->
				  <!-- <a class="custom-slide left carousel-control fa fa-chevron-left fa-2x" href="#carousel-generic" role="button" data-slide="prev">
				    <span class="fa fa-chevron-left fa-2x"></span>
				  </a> -->
				  <!-- <a class="custom-slide right carousel-control fa fa-chevron-right fa-2x" href="#carousel-generic" role="button" data-slide="next">
				    <span class="fa fa-chevron-right fa-2x"></span>
				  </a> -->
				   <div id="carouselButtons">
				      <!-- <button id="playButton" type="button" class="btn btn-default btn-xs">
				          <span class="fa fa-play"></span>
				       </button>
				      <button id="pauseButton" type="button" class="btn btn-default btn-xs">
				          <span class="fa fa-pause"></span>
				      </button> -->
  					</div>
			</div>
			<!-- </div>
		</div>
	</div> -->
</div>
<script type="text/javascript">
$('.delete_icon').click(function() {
	var ids = this.name;
	var refIds = ${refId};
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure you want to delete this event?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	document.getElementById("eventId").value = ids;
				document.getElementById("refId").value = refIds;
				$("#tradeMng").attr("action","${pageContext.request.contextPath}/trade/removeevent");
				$("#tradeMng").submit();
            }
        }]
    });
});
/* ----------Pagination---------- */
$(document).ready(function() {
	$(".success").fadeOut(3000);
	if('${update}'=='true'){
		$("#update").css("display","");
		$("#save").css("display","none");
		$("#attachmentForm").attr("action","${pageContext.request.contextPath}/Trader/updateEventAttachment");
	}
	window.setTimeout("loadPagination()",50);
});
function sendAjax(id) {
	clearForm();
	var tempid= id;
	$.ajax({
	    url: "${pageContext.request.contextPath}/trade/getattachment/"+id,
	    type: 'POST',
	    async: false,
	    dataType: 'json',
	    data: tempid,
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(dataJson) {
	    	//$('#myModal').modal('show');
	    	if(dataJson.length==0){
	    		$('.carousel-inner').append('<div style="list-style: none outside none; margin: 0; padding: 0 14px 14px; display: inline-block;"> No attachments available.</div>');
	    	}else{
		    	for(var i=0; i<dataJson.length; i++){
		    		var url = '${pageContext.request.contextPath}/attach/getImage/'+dataJson[i].attachId;
					//$('.modal-body').append('<span style="list-style: none outside none; margin: 0; padding: 0 14px 14px; display: inline-block;"> <img style="width: 150px; height: 94px;" alt="" src="'+ url +'"></span>');
		    		if(i==0){
		    			$('.carousel-indicators').append('<li data-target="#carousel-generic" data-slide-to="'+i+'" class="active"></li>');
		    			$('.carousel-inner').append('<div class="item active"> <img alt="" src="'+ url +'"> </div>');
		    			$('.carousel-inner').after('<a class="custom-slide left carousel-control" href="#carousel-generic" role="button" data-slide="prev"><span class="icon-prev"></span></a>');
		    			$('.carousel-inner').after('<a class="custom-slide right carousel-control" href="#carousel-generic" role="button" data-slide="next"><span class="icon-next"></span></a>');
		    			//$('#carouselButtons').append('<button id="playButton" type="button" class="btn btn-default btn-xs"><span class="fa fa-play"></span></button>');
		    			//$('#carouselButtons').append('<button id="pauseButton" type="button" class="btn btn-default btn-xs"><span class="fa fa-pause"></span></button>');
		    		}else{
		    			$('.carousel-indicators').append('<li data-target="#carousel-generic" data-slide-to="'+i+'"></li>');
		    			$('.carousel-inner').append('<div class="item"> <img alt="" src="'+ url +'"> </div>');
		    		}
		    	}
	    	}
	    },
	    error:function(data,status,er) {
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}
function clearForm(){
	$(".carousel-inner div").remove();	
	$(".carousel-indicators li").remove();
	$("#carousel-generic a").remove();
	$('#carouselButtons button').remove();
}

function loadPagination(){
	 jQuery('#table1').dataTable();
	   jQuery('#attachTable').dataTable({
	      "sPaginationType": "full_numbers"
	   });
	    /* jQuery("select").chosen({
	      'min-width': '100px',
	      'white-space': 'nowrap',
	      disable_search_threshold: 10
	    }); */
	    
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
	    /* jQuery('.table-hidaction tbody tr').hover(function(){
	      jQuery(this).find('.table-action-hide a').animate({opacity: 1});
	    },function(){
	      jQuery(this).find('.table-action-hide a').animate({opacity: 0});
	    }); */
}
</script>