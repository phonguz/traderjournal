<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src='<spring:url value="/resources/js/bootstrap-dialog.js"/>'></script>
<link href='<spring:url value="/resources/css/bootstrap-dialog.min.css"/>' rel="stylesheet">
<script src='<spring:url value="/resources/js/jquery-ui-min-1.8.18.js"/>'></script>
<script src='<spring:url value="/resources/js/jquery-ui-min-1.7.2.js"/>'></script>
<link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/jquery-ui.css"/>' />
	<div class="container">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="boxs smrgbtm">
					
					<form:form class="form-horizontal" role="form" method="post"
						action="${pageContext.request.contextPath}/account/saveAccount" name="acoountForm" id="accountForm" modelAttribute="accountbean" onsubmit="return validateTransaction();">
						
						<div class="errorDiv">
							<c:if test="${not empty error}">
								<label class="error">${error}</label>
							</c:if>
						</div>
						<c:if test="${not empty success}">
							<div class="success">${success}</div>
						</c:if>
						<div class="clr"></div>

						<div class="form-group">
							<label class="col-sm-5 control-label">Account Name</label>
							<div class="col-sm-4">
								<form:input type="text" size="100" class="form-control" path="name" id="accountName" name="accountName" 
								placeholder="Enter account Name" title="Enter Alphanumeric of length 3 to 25" required="required" ></form:input>
								<form:errors path="name" element="div" cssClass="alert alert-danger"></form:errors>
								<form:input type="hidden" id="accountId" name="accountId" path="id" />  
							</div>
						</div>
						<div class="form-group">							
							<form:label class="col-sm-5 control-label" path="transactionTypeId">Transaction Type</form:label>
							<div class="col-sm-4">
								<form:select path="transactionTypeId" placeholder="Transaction Type" class="form-control" id="transactionTypeId" name="transactionTypeId" required="required" onchange="validateBalance()">
									<form:options items="${transactiontypeList}" itemLabel="type" itemValue="id"/>
								</form:select>
								<form:errors path="transactionTypeId" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">							
							<form:label class="col-sm-5 control-label" path="transactionAmount">Transaction Amount</form:label>
							<div class="col-sm-4">
								<form:input type="text" size="100" class="form-control" id="transactionAmount" name="transactionAmount" 
								placeholder="Enter Amount" path="transactionAmount" required="required" onkeyup="validateBalance()" />
								<form:errors path="transactionAmount" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">							
							<form:label class="col-sm-5 control-label" path="balance">Balance</form:label>
							<div class="col-sm-4">
								<form:input type="text" size="100" class="form-control" id="balance" name="balance" 
								placeholder="0" path="balance" readonly="true"/>
								<form:errors path="balance" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">							
							<form:label class="col-sm-5 control-label" path="percentageRisk">Risk (%)</form:label>
							<div class="col-sm-4">
								<form:input type="text" size="100" class="form-control" id="risk" name="risk" 
								placeholder="Enter Risk in %" path="percentageRisk" required="required" />
								<form:errors path="percentageRisk" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<form:label for="currency" class="col-sm-5 control-label" path="ccyId">Select Currency:</form:label>
							<div class="col-sm-4">
							<form:select class="form-control" id="ccyId" name="ccyId" path="ccyId" required="required">
								<form:option selected="selected" value="">Select Currency</form:option>
								<c:forEach items="${currencyList}" var="ccy">
									<form:option value="${ccy.id}">${ccy.name}</form:option>
								</c:forEach>
							</form:select>
							<form:errors path="ccyId" element="div" cssClass="alert alert-danger"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-5"></div>
							<div class="col-sm-3">
								<form:button type="submit" class="btn btn-primary" name="save" id="save">Save</form:button>
								<form:button type="submit" class="btn btn-primary" name="update" id="update" style="display: none;">Update</form:button>
								<a href="/trademanagement-web/"><form:button type="button" class="btn btn-default">Cancel</form:button></a>
								<form:button type="reset" class="btn btn-default" value="Reset" onclick="clearInstrument();">Reset</form:button>
							</div>
						</div> 
					</form:form>
				</div>
				<div class="boxs smrgbtm">
					<table width="100%" cellpadding="0" cellspacing="0" border="0" class="datatable" id="instrumentTable">
						<tr>
							<th>Trader</th>
							<th>Account Name</th>
							<th>Balance</th>
							<th>Risk (%)</th>
							<th>Currency Name</th>
							<th><i class="fa fa-edit fa-2x"></i></th>
							<th><i class="fa fa-trash-o fa-2x"></i></th>
							<c:if test="${sessionScope.traderType ne 'ADMIN'}">
								<th><i class="fa fa-share-square-o fa-2x"></i></th>
							</c:if>
						</tr>
						<c:forEach var="accounts" items="${accountsList}">
							<tr>
								<td class="wrapword" value="${accounts.name}">${accounts.getTrader().getFirstname()}</td>
								<td class="wrapword" name="${accounts.id}_accName" id="${accounts.id}_accName" value="${accounts.name}">${accounts.name}</td>
								<td name="${accounts.id}_balance" id="${accounts.id}_balance" value="${accounts.balance}">${accounts.balance}</td>
								<td name="${accounts.id}_risk" id="${accounts.id}_risk" value="${accounts.percentRisk}">${accounts.percentRisk}</td>
								<td name="${accounts.id}_ccyName" id="${accounts.id}_ccyName" value="${accounts.ccy.id}">${accounts.ccy.name}</td>
								<td><a id="editInstrument" name="editInstrument"  onclick="editInstrument('${accounts.id}');" style="cursor: pointer;" title="Edit Account"><i class="fa fa-edit fa-2x"></i></a>
								<input type="hidden" value="${accounts.id}" name="${accounts.id}_id" id="${accounts.id}_id"></td>
								<td><a style="cursor: pointer;" class="delete_icon btn" id="${accounts.id}" title="Delete Account"><i class="fa fa-trash-o fa-2x"></i></a></td>
								<c:if test="${sessionScope.traderType ne 'ADMIN'}">
									<td><a style="cursor: pointer;" href="#popupBasic" data-toggle="modal" data-target="#myModal" onclick="shareTraderAccount(${accounts.id})" title="Share Account with another User"><i class="fa fa-share-square-o fa-2x"></i></a></td>
								</c:if>
							</tr>
						</c:forEach>
						<c:if test="${empty accountsList}">
							<tr>
								<td colspan="6"><label style="margin-left:166px;">No data available</label></td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
</div>
<!-- Share Account Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Share Account</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
			<label style="margin-top: -2px;" class="col-sm-3 control-label">Search User</label>
			<div class="col-sm-1">
				<input style="width: 435px; margin-left: -40px; margin-top: -9px;" id="user-search" type="text" class="form-control"/>
				<input id="shared-trader-id" type="hidden" size="100" class="form-control"/>
				<input id="account-id" type="hidden" size="100" class="form-control"/>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button id="close-btn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="share-account" type="button" class="btn btn-primary">Share</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$('.delete_icon').click(function() {
	var ids = this.id;
	BootstrapDialog.show({
		type: BootstrapDialog.TYPE_DANGER,
		closable: false,
        title: 'DELETE',
        message: 'Are you sure want to delete "'+$("td#"+ids+"_accName").attr("value")+'" from Account?',
        buttons: [{
            label: 'Cancel',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }, {
            label: 'Delete',
            cssClass: 'btn-danger',
            action: function() {
            	$("#accountId").val($("input#"+ids+"_id").attr("value"));
        		$("#accountForm").attr("action","${pageContext.request.contextPath}/account/deleteAccount");
        		$("#accountForm").attr("onsubmit","");
        		$("#accountForm").submit();
            }
        }]
    });
});
var balance = null;
	
function editInstrument(id){
	$("#transactionAmount").val("");
	$("#accountName").val($("td#"+id+"_accName").attr("value"));
	$("#accountId").val($("input#"+id+"_id").attr("value"));
	$("#balance").val($("td#"+id+"_balance").attr("value"));
	$("#risk").val($("td#"+id+"_risk").attr("value"));
	$("#ccyId").val($("td#"+id+"_ccyName").attr("value"));
	$("#update").css("display","");
	$("#save").css("display","none");
	$("#accountForm").attr("action","${pageContext.request.contextPath}/account/updateAccount");
	balance = $("td#"+id+"_balance").attr("value");
}

/*  function deleteInstrument(id, name){
	if(confirm("Are you sure want to delete "+$("td#"+id+"_accName").attr("value")+" from Account?")){
		$("#accountId").val($("input#"+id+"_id").attr("value"));
		$("#accountForm").attr("action","${pageContext.request.contextPath}/account/deleteAccount");
		$("#accountForm").attr("onsubmit","");
		$("#accountForm").submit();
	}
} */
function clearInstrument(){
	$("#accountId").val('');
	$("#ccyId").val('selected');
	$("#update").css("display","none");
	$("#save").css("display","");
	$("#accountForm").attr("action","${pageContext.request.contextPath}/account/saveAccount");	
}

function validateBalance(){
	var transactionType = $("#transactionTypeId option:selected").text();
	var transactionAmount = $("#transactionAmount").val();
	var amount;
	var temp = false;	

	var numberRegExp = new RegExp('^[0-9.]+$');
	var isTransactionAmount = numberRegExp.test(transactionAmount);
	var isBalance = numberRegExp.test(balance);	
	
	if(!isTransactionAmount){
		$("#balance").val(balance);
	}else if(isTransactionAmount && isBalance){
		if(balance != ""){
			if(transactionType.toLowerCase() == "deposit"){
				$(".errorDiv").html("");
				amount = parseFloat(balance) + parseFloat(transactionAmount);
				$("#balance").val(amount);
				temp =  true;
			}else if(parseFloat(balance) < parseFloat(transactionAmount)){
				$(".errorDiv").html("");
				$(".errorDiv").append("<label class='error'>Balance can not be less than Transaction Amount for Withdrawal Transaction</label>");
				amount = parseFloat(balance) - parseFloat(transactionAmount);
				$("#balance").val(amount);
				temp = false;
			}else{
				amount = parseFloat(balance) - parseFloat(transactionAmount);
				$("#balance").val(amount);
				temp = true;
			} 
		}else{
			temp = false;
		}
		return temp;
	}
}

function validateTransaction(){
	var transactionType = $("#transactionTypeId option:selected").text();
	var transactionAmount = $("#transactionAmount").val();
	var amount;
	var temp = false;

	var numberRegExp = new RegExp('^[0-9.]+$');
	var isTransactionAmount = numberRegExp.test(transactionAmount);
	var isBalance = numberRegExp.test(balance);

	if(!isTransactionAmount){
		$("#balance").val(balance);
		$(".errorDiv").append("<label class='error'>Transaction Amount must be in numeric value</label>");
		return false;
	}else if(isTransactionAmount && isBalance){
		if(transactionAmount != "" && balance != "")
		{
				if(transactionType.toLowerCase() == "deposit")
				{
					$(".errorDiv").html("");
					amount = parseFloat(balance) + parseFloat(transactionAmount);
					$("#balance").val(amount);
					temp =  true;
				}else if(parseFloat(balance) < parseFloat(transactionAmount)){
					$(".errorDiv").html("");
					$(".errorDiv").append("<label class='error'>Balance can not be less than Transaction Amount for Withdrawal Transaction</label>");
					amount = parseFloat(balance) - parseFloat(transactionAmount);
					$("#balance").val(amount);
					temp = false;
				}else{
					amount = parseFloat(balance) - parseFloat(transactionAmount);
					$("#balance").val(amount);
					temp = true;
				} 
		}else{
			temp = false;
		}
		return temp;
	}
}

function shareTraderAccount(traderId){
	$("#account-id").val(traderId);
	$("#user-search").val("");
	return true;
}

$("#share-account").click(function(){
	$.ajax({
		url:"${pageContext.request.contextPath}/account/shareAccount",
		type: "POST",
		data:{
			sharedTraderId : $("#shared-trader-id").val(),
			accountId : $("#account-id").val() 
		},
		success:function(data){
			$("#close-btn").click();
			$( "#msg" ).remove();
			if(data == "success"){
				$("#accountForm").append("<div id='msg' class='success'>Successfully Share Account</div>");
				$("#msg").fadeIn(400).delay(4000).fadeOut(400);
			}else{
				$("#accountForm").append("<div id='msg' class='errorDiv'>Error In Share Account</div>");
				$("#msg").fadeIn(400).delay(4000).fadeOut(400);	
			}
		}
	});
});


$(document).ready(function(){
	var bal = $("#balance").val();
	if(bal == "" || bal == null){
		balance = "0";	
	}else{
		balance = $("#balance").val();	
	}
	
	$(".success").fadeOut(3000);
	if("${update}" == "true"){
		$("#update").css("display","");
		$("#save").css("display","none");
		$("#accountForm").attr("action","${pageContext.request.contextPath}/account/updateAccount");
	}

	$('#user-search').autocomplete({
		source: function (request, response) {
			$.ajax({
		          url: "${pageContext.request.contextPath}/account/userByLikeName",
		          dataType: "json",
		          data: {
		            uid: request.term,
		            accountId :$("#account-id").val()
		          },
		          success: function( data ) {
		            response( $.map( data, function( item ) {
		              return {
		            	label: item.firstName +" ("+item.userName+")",
		                value: item.firstName +" ("+item.userName+")",
		                id:item.id
		              }
		            }));
		          }
		       });
           /*  $.getJSON("${pageContext.request.contextPath}/account/userByLikeName", {
                uid: request.term
            }, response); */
        },
        select: function (event, ui) {
          	$("#shared-trader-id").val(ui.item.id);
            return true;
        }
	});
}); 
</script>
<style>
	.alert{
		padding:0px;
	}
	.errorDiv{
		width:40%;
		margin: 7px 7px 0px 195px !important;
	}
	.success{
		width:30%;		
		margin: 7px 7px 0px 195px !important;
	}
</style>