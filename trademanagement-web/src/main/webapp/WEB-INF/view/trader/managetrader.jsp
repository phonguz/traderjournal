<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head> 
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script>
<link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel="stylesheet">
<script  src="http://code.jquery.com/jquery-1.11.1.min.js"></script>   
<script  src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>  
<link href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.css" rel='stylesheet' type='text/css'>  
	<script type="text/javascript">
		/* function onDel(id){
			var agree=confirm("Are you sure to delete trader profile?");
			if(agree){
				document.getElementById("refId").value = id;
				$("#traderMng").attr("action","${pageContext.request.contextPath}/Trader/remove");
				$("#traderMng").submit();
			}else{
			    return false;
			}
		} */
		function onEdit(id){
			document.getElementById("refId").value = id;
			$("#traderMng").attr("action","${pageContext.request.contextPath}/Trader/updatetrader");
			document.getElementById("traderMng").submit();
		}
	</script>
</head>
<div class="container">
<c:if test="${not empty SUCCESS_MSG}">
	<div class="alert alert-success">${SUCCESS_MSG}</div>
</c:if>
  <div id="horizontalTab">
  	<ul class="resp-tabs-list">
    	<li  class="resp-tab-item resp-tab-active">Traders List</li>      
    </ul>
	<!-- <label class="resp-tab-item">Traders List </label> -->
	<a href="${pageContext.request.contextPath}/Trader/signup" class="btn_blue" style="float:right;">Create new trader</a>
	<div class="clr"></div>
    <div class="resp-tabs-container">        
       	<div class="starter-template resp-tab-content resp-tab-content-active">
          <div class="table-responsive">
          <form name="traderMng" id="traderMng" method="post">
            <table class="table table-bordered table-hover table-striped tablesorter tftable" id="userTable">
              <thead>
                <tr>
                  <th class="header" style="background-color:#3B5998">Username </th>
                  <th class="header" style="background-color:#3B5998">Firstname </th>
                  <th class="header" style="background-color:#3B5998">Lastname </th>
                  <!-- <th class="header" style="background-color:#3B5998"><label class="add_delete"><a href="#"><img src="/trademanagement-web/resources/images/add_white.png" alt=""></a><a class="delete_icon" href="#"><img src="/trademanagement-web/resources/images/delete_white.png" alt=""></a></label></th> -->
                  <th class="header" style="background-color:#3B5998"><label class="add_delete"><i class="fa fa-edit fa-2x"></i>&nbsp;&nbsp;<i class="fa fa-trash-o fa-2x"></i></label></th>
                </tr>
              </thead>
              <tbody>
              
              <c:forEach items="${tradersList}" var="traders">
	                <tr>
	                  <td class="blue"><c:out value="${traders.username}"/></td>
	                  <td><c:out value="${traders.firstname}"/></td>
	                  <td class="gray_table"><c:out value="${traders.lastname}"/></td>
	                  <td>
	                  	<label class="add_delete">
	                  		<%-- <a onclick="onEdit(${traders.id});" class="btn"><img src="/trademanagement-web/resources/images/edit.png" alt=""></a> --%>
	                  		<a onclick="onEdit(${traders.id});" class="btn" title="Edit User Profile"><i class="fa fa-edit fa-2x"></i></a>
	                  		<%-- <a class="delete_icon btn" onclick="onDel(${traders.id});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a> --%>
	                  		<a class="delete_icon btn" id="${traders.id},${traders.username}" title="Delete User Profile"><i class="fa fa-trash-o fa-2x"></i></a>
	                  	</label>
	                  </td>
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
				<!-- --------------------------------------------------------------- -->
          </div>
         
        </div>    
    </div>
  </div>
</div>
<script>
$('.delete_icon').click(function() {
	var ids = this.id.split(',');
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure to delete "'+ ids[1] +'" profile?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	document.getElementById("refId").value = ids[0];
				$("#traderMng").attr("action","${pageContext.request.contextPath}/Trader/remove");
				$("#traderMng").submit();
            }
        }]
    });
});
$(document).ready(function() {
	//window.setTimeout("loadPagination()",50);
	$('#userTable').dataTable( {
        "pagingType": "full_numbers"
    } );      
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
	   // });
	    
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
	 //   jQuery('.table-hidaction tbody tr').hover(function(){
	  //    jQuery(this).find('.table-action-hide a').animate({opacity: 1});
	   // },function(){
	    //  jQuery(this).find('.table-action-hide a').animate({opacity: 0});
	   // });
	// }
 </script>