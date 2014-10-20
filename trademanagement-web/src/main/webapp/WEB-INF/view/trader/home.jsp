<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container">
	<spring:hasBindErrors name="tradeBean">
		<c:if test="${not empty errors.globalError}">
			<div class="alert alert-danger">${errors.globalError.defaultMessage}</div>
		</c:if>
	</spring:hasBindErrors>
	<c:if test="${not empty SUCCESS_MSG}">
		<div class="alert alert-success">${SUCCESS_MSG}</div>
	</c:if>
  <div id="horizontalTab">
    <ul class="resp-tabs-list">
      <li>Create  Trade</li>
      <!-- <li>Create  Account</li>
      <li>Tab-3</li> -->
    </ul>
    <div class="resp-tabs-container">
      <div>
        <div class="tab_view_most">
          <!--<div class="row">
            <div class="col-xs-12"> <a href="#" class="create_trade">Create Trade</a> <a href="#" class="create_trade">Create  Account</a>
              <div class="clearfix"></div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="search_box">
                <div class="col-md-5">
                  <input type="text" value="" name="" class="box_466px" placeholder="Trade Search">
                </div>
                <div class="col-md-3">
                  <input type="text" value="" name="" class="box_214px" placeholder="Start Date :">
                </div>
                <div class="col-md-3">
                  <input type="text" value="" name="" class="box_250px" placeholder="END Date : ">
                </div>
                <div class="col-md-1"> <a href="#" class="search_btn">Search</a></div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>-->
          
          <div class="form row">
            <div class="form_lft col-md-7">
              <form:form role="form" id="tradeForm" action="${pageContext.request.contextPath}/trade/save" modelAttribute="tradeBean" method="post">
                <div class="form_row">
	                <label class="name_txt">Account</label>
	                <form:select class="select_name" id="accountsId" path="accountsId" required="required">
						<form:option value="Select Account" >Select Account</form:option>
                    	<c:forEach items="${accountsList}" var="accounts">
							<form:option value="${accounts.id}" title="${accounts.balance}" id="${accounts.percentRisk}">${accounts.name}</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="accountsId" element="div" cssClass="alert alert-danger"></form:errors>
	                <div class="clearfix"></div>
                </div>
                <div class="form_row" id="balanceDiv" style="display: none;">
                	<label class="name_txt">Balance</label>
                	<input class="add_name" disabled="disabled" type="text" id="balance" value="">
                </div>
                <div class="form_row mar_last">
                  <label class="name_txt">Instrument</label>
                  <form:select class="select_name" id="instrumentId" path="instrumentId" onchange="disableName(this);" required="required">
                    <form:option value="Select Instrument">Select Instrument</form:option>
                    <c:forEach items="${instrumentList}" var="instrument">
							<form:option value="${instrument.id}">${instrument.name}</form:option>
						</c:forEach>
                  </form:select>
                  <form:errors path="instrumentId" element="div" cssClass="alert alert-danger"></form:errors>
                  <div class="clearfix"></div>
                </div>
                <div class="or">Or</div>
                <div class="clearfix"></div>
                <div class="form_row">
                	<form:label for="instrumentName" class="name_txt" path="instrumentName">Instument Name</form:label>
					<form:input type="text" class="add_name" id="instrumentNameId" name="instrumentName" path="instrumentName" onfocus="setInst();"></form:input>
			        <form:errors path="instrumentName" element="div" cssClass="alert alert-danger"></form:errors>
                	<c:if test="${not empty instrumentException}">
                		<div class="alert alert-danger">${instrumentException}</div>
                	</c:if>
                	<div class="clearfix"></div>
                </div>
                <div class="form_row">
	                <label class="name_txt">Trade type</label>
	                <form:select class="select_name" id="tradetype" path="tradetype" required="required">
						<form:option value="Select Trade type" >Select Trade type</form:option>                    	
						<form:option value="0">BUY</form:option>
						<form:option value="1">SALE</form:option>
					</form:select>
					<form:errors path="tradetype" element="div" cssClass="alert alert-danger selectTrade"></form:errors>
	                <div class="clearfix"></div>
                </div>
                <div class="form_row">
                	<form:label for="openprice" class="name_txt" path="openprice">Open Price</form:label>
					<form:input type="text" class="add_name" id="openprice" name="openprice" path="openprice" required="required"></form:input>
			        <form:errors path="openprice" element="div" cssClass="alert alert-danger"></form:errors>
                  	<div class="clearfix"></div>
                </div>
                <div class="form_row">
                	<form:label for="stoploss" class="name_txt" path="stoploss">Stop Loss</form:label>
					<form:input type="text" class="add_name" id="stoploss" name="stoploss" path="stoploss" required="required"></form:input>
			        <form:errors path="stoploss" element="div" cssClass="alert alert-danger"></form:errors>
                  	<div class="clearfix"></div>
                </div>
                <div class="form_row">
                	<form:label for="targetprice" class="name_txt" path="targetprice">Target Price</form:label>
					<form:input type="text" class="add_name" id="targetprice" name="targetprice" path="targetprice" required="required"></form:input>
			        <form:errors path="targetprice" element="div" cssClass="alert alert-danger"></form:errors>
                  	<div class="clearfix"></div>
                </div>
                <div id="sugg_quantity" class="form_row" style="display:none;">
                	<label class="name_txt">Suggested Quantity</label>
                	<input class="form-control add_name" disabled="disabled" type="text" id="sug_quantity" value="">                  				        
                  	<div class="clearfix"></div>
                </div>
                <div class="form_row">
                  	<form:label for="quantity" class="name_txt" path="quantity">Quantity</form:label>
					<form:input type="text" class="add_name" id="quantity" name="quantity" path="quantity" required="required"></form:input>
			        <form:errors path="quantity" element="div" cssClass="alert alert-danger"></form:errors>
                  	<div class="clearfix"></div>
                </div>
                <div class="form_row">
                	<form:label for="closeprice" class="name_txt" path="closeprice">Closing Price</form:label>
					<form:input type="text" class="add_name" id="closeprice" name="closeprice" path="closeprice"></form:input>
			        <form:errors path="closeprice" element="div" cssClass="alert alert-danger"></form:errors>
                  	<div class="clearfix"></div>
                </div>
                <div class="form_row">
                	<form:label for="opentradedate" class="name_txt" path="opentradedate">Open Date</form:label>
			      	<div class="input-group date" id="datePicker1">
			    		<form:input type="text" class="add_name add_date" id="opentradedate" name="opentradedate" path="opentradedate" required="required" readonly="true"></form:input>
			    		<span class="input-group-addon"><img src='<spring:url value="/resources/images/date.png"/>' alt=""></span>
			    	</div>
			    	<form:errors path="opentradedate" element="div" cssClass="alert alert-danger"></form:errors>
			    	<div class="clearfix"></div>
    			</div>
                <div class="form_row">
                	<form:label for="closetradedate" class="name_txt" path="closetradedate">Close Date</form:label>
                  	<div class="input-group date" id="datePicker2">
                  		<form:input type="text" class="add_name add_date" id="closetradedate" name="closetradedate" path="closetradedate" required="required" readonly="true"></form:input>
			    		<span class="input-group-addon"><img src='<spring:url value="/resources/images/date.png"/>' alt=""></span>
			    	</div>
			    	<form:errors path="closetradedate" element="div" cssClass="alert alert-danger"></form:errors>
                  <div class="clearfix"></div>
                </div>
                <input type="hidden" name="newInst" id="newInst" value="${newInst}">
                <input type="hidden" name="isUpdate" value="${isUpdate}">
                <c:if test="${empty isUpdate}">
					<div class="button_back">
						<button onclick="saveTrade();" class="btn_blue">save trade</button>
						<a href="${pageContext.request.contextPath}/trade" class="btn_blue"> cancel</a>
					</div>
				</c:if>
				<c:if test="${isUpdate}">
					<div class="button_back">
						<button onclick="saveTrade();" class="btn_blue">update trade</button>
						<!-- <a href="#" class="btn_blue" data-toggle="modal" data-target="#myModal">update trade</a> -->
						<a href="${pageContext.request.contextPath}/trade" class="btn_blue"> cancel</a>
					</div>
				</c:if>
                
                <div class="clearfix"></div>
                
                	<%-- <!-- ----Pop UP ---- -->			
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					  <div class="modal-dialog">
					   <div class="modal-content">
					      <div class="modal-header" style="background: #428bca; color:#fff;">
					        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color:#fff;opacity:1" onclick="clearForm();">&times;</button>
					        <h4 class="risk modal-title" id="myModalLabel">Event detail</h4>
					      </div>
					      <div class="modal-body">
					      	<div class="form_row">
						        <form:label for="description" class="name_txt" path="description">Event description : </form:label>
								<form:textarea class="form-control add_name" id="description" name="description" path="description" rows="5" cols="30" data-toggle="popover" data-content="Enter description" data-placement="left"></form:textarea>
						        <form:errors path="description" element="div" cssClass="alert alert-danger err"></form:errors>
			                  	<div class="clearfix"></div>
		                  	</div> 
		                  	<div class="form_row">
						        <form:label for="newvalue" class="name_txt" path="newvalue">New value : </form:label>
								<form:input type="text" class="form-control add_name" id="newvalue" name="newvalue" path="newvalue" data-toggle="popover" data-content="Enter new value" data-placement="left"></form:input>
						        <form:errors path="newvalue" element="div" cssClass="alert alert-danger err"></form:errors>
			                  	<div class="clearfix"></div>
		                  	</div>
							<div class="form_row">
								<label class="name_txt">Event type : </label>
								<form:select class="form-control" id="eventtypeid" path="eventtypeid" required="required" data-toggle="popover" data-content="Select event type" data-placement="left">
									<form:option value="0">Select event type</form:option>
										<c:forEach items="${eventTypeList}" var="eventtype">
											<form:option value="${eventtype.id}">${eventtype.name}</form:option>
										</c:forEach>
								</form:select>
								<form:errors path="eventtypeid" element="div" cssClass="alert alert-danger err"></form:errors>
								<div class="clearfix"></div>
							</div>
							<div class="form_row">
			                	<form:label for="eventdate" class="name_txt" path="eventdate">Event Date : </form:label>
			                  	<div class="input-group date" id="datePicker3">
			                  		<form:input type="text" class="form-control add_date" id="eventdate" name="eventdate" path="eventdate" readonly="true" data-toggle="popover" data-content="Select event date" data-placement="left"></form:input>
						    		<span class="input-group-addon"><img src='<spring:url value="/resources/images/date.png"/>' alt=""></span>
						    	</div>
						    	<form:errors path="eventdate" element="div" cssClass="alert alert-danger err"></form:errors>
			                  <div class="clearfix"></div>
			                </div>
						  </div>
					      <div class="modal-footer">
					        <button class="btn btn-default" data-dismiss="modal" onclick="clearForm();">Close</button>
					        <a href="#" class="btn btn-primary" onclick="updateTrade();">Save changes</a>
					      </div>
					    </div>
					  </div> 
					</div>
					<!-- --------------- -->	 --%>
              </form:form>
            </div>
            <div class="form_rgt col-md-5">
              <div class="risk"> Risk </div>
              <div class="risk_percantage">0 %</div>
            </div>
            <div class="clearfix"></div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#accountsId').focus();
		 $('#datePicker1').datepicker({			 	
		        orientation: "auto left",
		        autoclose: true,
		        todayHighlight: true
		        });
		 
		 $('#datePicker2').datepicker({
		        orientation: "auto left",
		        autoclose: true,
		        todayHighlight: true
		        });
		 $('#datePicker3').datepicker({
		        orientation: "auto left",
		        autoclose: true,
		        todayHighlight: true
		        });
	});
	$(window).load(function(){
		var isupdatemode = '${isUpdate}';
		var validFailed = '${validFail}';
		if(validFailed =='true'){
			dispBalance();
			calSuggesteQua();
			calculateRisk();
		}else if(isupdatemode){
			dispBalance();
			calSuggesteQua();
			calculateRisk();
		}
		var pattern = /^\d+$/;
		if(pattern.test($("#instrumentId").val())){
			$("#instrumentNameId").attr("disabled", "disabled");
		}
		var eventError = '${desc}';
		if(eventError=='true'){
			$('#myModal').modal('show');
		}
	});
	function disableName(id){
		var select = document.getElementById("instrumentId");
	    var selectedString = select.options[select.selectedIndex].value;
	    var pattern = /^\d+$/;
	    if(pattern.test(selectedString)){
	    	$("#newInst").val("false");
	    	$("#instrumentNameId").attr("disabled", "disabled");
	    	$("#instrumentName").val("");
	    }else{
	    	$("#newInst").val("true");
	    	$("#instrumentNameId").removeAttr("disabled"); 
	    }
	}
	function setInst(){
		$("#newInst").val("true");
	}
	$("#stoploss").focus(function(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var opprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
		var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
		if(!valBalanceRX.test(opprice)){
			if($('#openerror').length==0){
				$('#openprice').after("<p id='openerror' style='color: red; float: right; width: 322px;'>Invalid open price</p>");
			}
			$("#openprice").val("");
			$("#openerror").fadeOut(5000);
		}else if(valBalanceRX.test(stoploss)){
			calSuggesteQua();
		}else{
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}
	});	
	
	$("#openprice").change(function(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var opprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
		var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
		if(!valBalanceRX.test(opprice)){
			if($('#openerror').length==0){
				$('#openprice').after("<p id='openerror' style='color: red; float: right; width: 322px;'>Invalid open price</p>");
			}
			$("#openprice").val("");
			$("#openerror").fadeOut(5000);
		}else if(valBalanceRX.test(stoploss)){
			calSuggesteQua();
		}else{
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}
	});	
	
	$('#stoploss').change(function(){		
		calSuggesteQua();
	});
	$('#quantity').focus(function(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var openprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
		var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
		if(!valBalanceRX.test(openprice)){
			if($('#openerror').length==0){
				$('#openprice').after("<p id='openerror' style='color: red; float: right; width: 322px;'>Invalid open price</p>");
			}
			$("#openprice").val("");
			$("#openprice").focus();
			$("#openerror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}else if(!valBalanceRX.test(stoploss)){
			if($('#stlosserror').length==0){
				$('#stoploss').after("<p id='stlosserror' style='color: red; float: right; width: 322px;'>Invalid stop loss</p>");
			}
			$("#stoploss").val("");
			$("#stoploss").focus();
			$("#stlosserror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}else{
			calSuggesteQua();
		}
	});
	$('#closeprice').focus(function(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var valQtyRX = new RegExp('^[0-9]+$');
		var openprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
		var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
		var qty = $("#quantity").val().replace(/^\s+|\s+$/g,"");
		if(!valBalanceRX.test(openprice)){
			if($('#openerror').length==0){			
				$('#openprice').after("<p id='openerror' style='color: red; float: right; width: 322px;'>Invalid open price</p>");
			}
			$("#openprice").val("");
			$("#openprice").focus();
			$("#openerror").fadeOut(5000);			
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}else if(!valBalanceRX.test(stoploss)){
			if($('#stlosserror').length==0){
				$('#stoploss').after("<p id='stlosserror' style='color: red; float: right; width: 322px;'>Invalid stop loss</p>");
			}
			$("#stoploss").val("");
			$("#stoploss").focus();
			$("#stlosserror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
		}else if(!valQtyRX.test(qty)){
			if($('#quantityerror').length==0){
				$('#quantity').after("<p id='quantityerror' style='color: red; float: right; width: 322px;'>Invalid quantity</p>");
			}
			$("#quantity").val("");
			$("#quantity").focus();
			$("#quantityerror").fadeOut(5000);
			/* $("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val(""); */
		}else{
			calculateRisk();
		}
	});
	function calSuggesteQua(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var openprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
		var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
		if(!valBalanceRX.test(openprice)){
			if($('#openerror').length==0){			
				$('#openprice').after("<p id='openerror' style='color: red; float: right; width: 322px;'>Invalid open price</p>");
			}
			$("#openprice").val("");
			$("#openprice").focus();
			$("#openerror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
			return false;
		}else if(!valBalanceRX.test(stoploss)){
			if($('#stlosserror').length==0){
				$('#stoploss').after("<p id='stlosserror' style='color: red; float: right; width: 322px;'>Invalid stop loss</p>");
			}
			$("#stoploss").val("");
			$("#stoploss").focus();
			$("#stlosserror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
			return false;
		}else if(parseInt(stoploss) > parseInt(openprice)){
			$('#stoploss').after("<p id='stlosserror' style='color: red; float: right; width: 322px;'>Stop loss not more than open price</p>");
			$("#stoploss").val("");
			$("#stoploss").focus();
			//$("#stlosserror").fadeOut(5000);
			$("#sugg_quantity").attr("style","display:none");
			$("#sug_quantity").val("");
			return false;
		}else{
			if($('#stlosserror').length>0){
				$("#stlosserror").fadeOut(3000);
			}
			var diff = openprice-stoploss;
			var accountid =  $('#accountsId :selected').attr("title");
			if(!valBalanceRX.test(parseInt(accountid))){
				if($('#accounterror').length==0){
					$('#accountsId').after("<p id='accounterror' style='color: red; float: right; width: 322px;'>Select account first</p>");
				}
				//$("#accounterror").fadeOut(5000);							
				$('#accountsId').focus();
				return false;
			}else{
				if($('#accounterror').length>0){
					$("#accounterror").fadeOut(3000);
				}
				var balance = parseInt($('#accountsId :selected').attr("title"));
				var risk =  parseInt($('#accountsId :selected').attr("id"));			
				var avgBal = ((balance*risk)/100);
				$("#sugg_quantity").attr("style","display:block");
				$("#sug_quantity").val(Math.round(avgBal/diff));
				return true;
			}
		}
	}
	$('#accountsId').change(function() {
		dispBalance();
	});	
	function dispBalance(){
		var validString = new RegExp('^[0-9]+$');		
		var desc =  $('#accountsId :selected').attr("title"); /* desc validation */
		if(!validString.test(desc)){
			if(desc == undefined) {
				$("#balanceDiv").attr("style","display:none");
				$("#balance").val();
			}else{
				$("#balanceDiv").attr("style","display:block");
				$("#balance").val(desc);
			}
		}else{
			$("#balanceDiv").attr("style","display:none");
			$("#balance").val();
		}
	}
	$("#quantity").change(function() {
		if(calSuggesteQua()){
			calculateRisk();
		}else{
			alert("error calculate suggested quantity");
			return false;
		}
	});
	function calculateRisk(){
		var valBalanceRX = new RegExp('^[0-9]\.+$');				
		var balance =  $('#accountsId :selected').attr("title");
		if(!valBalanceRX.test(parseInt(balance))){
			if($('#accounterror').length==0){
				$('#accountsId').after("<p id='accounterror' style='color: red; float: right; width: 322px;'>Select account first</p>");
			}				
			//$("#accounterror").fadeOut(5000);
		}
		var valQtyRX = new RegExp('^[0-9]+$');
		var quantity = $("#quantity").val().replace(/^\s+|\s+$/g,"");		
		if(!valQtyRX.test(parseInt(quantity))){
			if($('#accounterror').length>0){
				$("#accounterror").fadeOut(3000);
			}
			if($('#quantityerror').length==0){
				$('#quantity').after("<p id='quantityerror' style='color: red; float: right; width: 322px;'>Invalid quantity</p>");
			}
			$("#quantity").val("");
			$("#quantityerror").fadeOut(5000);
		}else{
			var openprice = $("#openprice").val().replace(/^\s+|\s+$/g,"");
			var stoploss = $("#stoploss").val().replace(/^\s+|\s+$/g,"");
			var riskpercent = (openprice-stoploss)*quantity/balance;
			$(".risk_percantage").html(Math.round(riskpercent) + " %");
		}
	}
	$('#tradeForm').submit(function() {
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var valIdRX = new RegExp('^[0-9]+$');
		var validString = new RegExp('^[^\"]+$');
		var valDateRX = /^\d{1,2}\/\d{1,2}\/\d{4}$/; /* date validation */
		var balance =  $('#accountsId :selected').attr("title");
		var instrument =  $('#instrumentId :selected').attr("value");
		var tradeType =  $('#tradetype :selected').attr("value");
		var instrumentname =  $('#instrumentNameId').val().replace(/^\s+|\s+$/g,"");
		var opDate = $("#opentradedate").val().replace(/^\s+|\s+$/g,"");
		var clDate = $("#closetradedate").val().replace(/^\s+|\s+$/g,"");
		//alert("inst test : " + valIdRX.test(instrument) + " : name test : " + validString.test(instrumentname));
		if(!valBalanceRX.test(parseInt(balance))){
			if($('#accounterror').length==0){
				$('#accountsId').after("<p id='accounterror' style='color: red; float: right; width: 322px;'>Select account first</p>");
			}
			$('#accountsId').focus();
			//$("#accounterror").fadeOut(5000);
			return false;
		}/* else if((valBalanceRX.test(instrument) && (!validString.test(instrumentname))) || (!valBalanceRX.test(instrument) && (validString.test(instrumentname)))){ */
		else if(!valIdRX.test(instrument) && (!validString.test(instrumentname))){
			if($('#accounterror').length>0){
				$("#accounterror").fadeOut(3000);
			}
			if($('#instrumenterror').length==0){
				//alert("inside form submit");
				$('#instrumentId').after("<p id='instrumenterror' style='color: red; float: right; width: 322px;'>Either select instrument or enter new one</p>");
			}
			$('#instrumentId').focus();
			//$("#instrumenterror").fadeOut(5000);
			return false;
		}/* else if(!valBalanceRX.test(instrument) && (validString.test(instrumentname))){
			$('#instrumentId').after("<p id='instrumenterror' style='color: red; float: right; width: 322px;'>Either select instrument or enter new one</p>");				
			$('#instrumentId').focus();
			$("#instrumenterror").fadeOut(5000);
			return false;
		} */
		else if(!valIdRX.test(tradeType)){
			if($('#instrumenterror').length>0){
				$("#instrumenterror").fadeOut(3000);
			}
			if($('#tradetypeerror').length==0){
				$('#tradetype').after("<p id='tradetypeerror' style='color: red; float: right; width: 322px;'>Select trade type</p>");
			}
			$('#tradetype').focus();
			//$("#tradetypeerror").fadeOut(5000);
			return false;
		}else if(!valDateRX.test(opDate)){
			if($('#tradetypeerror').length>0){
				$("#tradetypeerror").fadeOut(3000);
			}
			if($('#opdateerror').length==0){
				$('#opentradedate').after("<p id='opdateerror' style='color: red; float: right; width: 322px;'>Select open date</p>");
			}
			$('#opentradedate').focus();
			//$("#opdateerror").fadeOut(5000);
			return false;
		}/* else if(!valDateRX.test(clDate)){
			if($('#cldateerror').length==0){
				$('#closetradedate').after("<p id='cldateerror' style='color: red; float: right; width: 322px;'>Select close date</p>");
			}
			$('#closetradedate').focus();
			//$("#cldateerror").fadeOut(5000);
			return false;
		} */
	});
	function updateTrade(){
		//var validString = new RegExp('^[^\"]+$');
		//var valBalanceRX = new RegExp('^[0-9]+$'); /* balance validation */
	//	var valeveTypeRX = new RegExp('^[1-9]+$'); /* event type validation */
	//	var desc = $("#description").val().replace(/^\s+|\s+$/g,""); /* desc validation */
		//var newVal = $("#newvalue").val().replace(/^\s+|\s+$/g,"");
	//	var eveType = $("#eventtypeid").val().replace(/^\s+|\s+$/g,"");
	//	var eveDate = $("#eventdate").val().replace(/^\s+|\s+$/g,"");
	//	var valDateRX = /^\d{1,2}\/\d{1,2}\/\d{4}$/; /* date validation */
		/* if(!validString.test(desc)){
			$("#newvalue").removeAttr("style");
			$("#newvalue").popover('destroy');
			$("#eventtypeid").removeAttr("style");
			$("#eventtypeid").popover('destroy');
			$("#eventdate").removeAttr("style");
			$("#eventdate").popover('destroy');
			$("#description").focus();
			$("#description").attr("style","border-color:red");
			$('#description').popover('toggle');
		}else if(!valBalanceRX.test(newVal)){
			$("#description").removeAttr("style");
			$("#description").popover('destroy');
			$("#eventtypeid").removeAttr("style");
			$("#eventtypeid").popover('destroy');
			$("#eventdate").removeAttr("style");
			$("#eventdate").popover('destroy');
			$("#newvalue").focus();
			$("#newvalue").attr("style","border-color:red");
			$('#newvalue').popover('toggle');
		}else if(!valeveTypeRX.test(eveType)){ 
			$("#description").removeAttr("style");
			$("#description").popover('destroy');
			$("#newvalue").removeAttr("style");
			$("#newvalue").popover('destroy');
			$("#eventdate").removeAttr("style");
			$("#eventdate").popover('destroy');
			$("#eventtypeid").focus();
			$("#eventtypeid").attr("style","border-color:red");
			$("#eventtypeid").popover('toggle');
		}else if(!valDateRX.test(eveDate)){
			$("#description").removeAttr("style");
			$("#description").popover('destroy');
			$("#newvalue").removeAttr("style");
			$("#newvalue").popover('destroy');
			$("#eventtypeid").removeAttr("style");
			$("#eventtypeid").popover('destroy');
			$("#eventdate").focus();
			$("#eventdate").attr("style","border-color:red");
			$("#eventdate").popover('toggle');
		}else{ 
			$("#description").removeAttr("style");
			$("#description").popover('destroy');
			$("#newvalue").removeAttr("style");
			$("#newvalue").popover('destroy');
			$("#eventtypeid").removeAttr("style");
			$("#eventtypeid").popover('destroy');
			$("#eventdate").removeAttr("style");
			$("#eventdate").popover('destroy');*/
			//saveTrade();
		//}
		/* $("#eventdate").val(); */		
	}
	 function clearForm(){
		$("div").remove(".err");
		$("#description").removeAttr("style");
		$("#description").popover('destroy');
		$("#newvalue").removeAttr("style");
		$("#newvalue").popover('destroy');
		$("#eventtypeid").removeAttr("style");
		$("#eventtypeid").popover('destroy');
		$("#eventdate").removeAttr("style");
		$("#eventdate").popover('destroy');
	}
</script>