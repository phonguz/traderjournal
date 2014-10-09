<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
	<script type="text/javascript">
		function onDel(id,obj){			
			var agree=confirm("Are you sure to remove attachment ?");
			if(agree){
				var refId=id;
				//document.getElementById("refId").value = id;
				//$("#tradeMng").attr("action","${pageContext.request.contextPath}/attach/removeAttachment");
				//$("#tradeMng").submit();
				$.ajax({
				    url: "${pageContext.request.contextPath}/attach/removeAttachment/"+refId,
				    type: 'POST',
				    dataType: 'json',
				    //data: refId,
				    success: function(dataJson) {
			    		$($(obj).parent()).remove();
				    },
				    error:function(data,status,er) {
				        alert("error: "+ data + " status: "+status+" er:"+er);
				    }
				});
			}else{
			    return false;
			}
		}
	</script>
</head>
<div class="container">
	<spring:hasBindErrors name="tradeBean">
		<c:if test="${not empty errors.globalError}">
			<div class="alert alert-danger">${errors.globalError.defaultMessage}</div>
		</c:if>
	</spring:hasBindErrors>
	<c:if test="${not empty SUCCESS_MSG}">
		<div class="alert alert-success">${SUCCESS_MSG}</div>
	</c:if>
<div>
<div><h5>Trade</h5></div>
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
  <div id="horizontalTab">
    <ul class="resp-tabs-list">
      <li>Trade Event</li>      
    </ul>
    <div class="resp-tabs-container">
        <div class="tab_view_most">
          <div class="form row">
            <div class="form_lft col-md-7">
              	<form:form role="form" id="tradeForm" modelAttribute="tradeBean" method="post" enctype="multipart/form-data">
	              	<form:input type="hidden" name="accountsId" path="accountsId"></form:input>
	            	<form:input type="hidden" name="instrumentId" path="instrumentId"></form:input>
	            	<form:input type="hidden" name="instrumentName" path="instrumentName"></form:input>
	            	<form:input type="hidden" name="quantity" path="quantity"></form:input>
	            	<form:input type="hidden" name="openprice" path="openprice"></form:input>
	            	<form:input type="hidden" name="stoploss" path="stoploss"></form:input>
	            	<form:input type="hidden" name="closeprice" path="closeprice"></form:input>
	            	<form:input type="hidden" name="opentradedate" path="opentradedate"></form:input>
	            	<form:input type="hidden" name="closetradedate" path="closetradedate"></form:input>
	                <div class="form_row">
						<form:label for="description" class="name_txt" path="description">Event description : </form:label>
						<form:textarea class="form-control add_name" id="description" name="description" path="description" rows="5" cols="30"></form:textarea>
						<form:errors path="description" element="div" cssClass="alert alert-danger err"></form:errors>
				        <div class="clearfix"></div>
			        </div> 
			        <div class="form_row">
						<form:label for="newvalue" class="name_txt" path="newvalue">New value : </form:label>
						<form:input type="text" class="form-control add_name" id="newvalue" name="newvalue" path="newvalue"></form:input>
						<form:errors path="newvalue" element="div" cssClass="alert alert-danger err"></form:errors>
				       	<div class="clearfix"></div>
			        </div>
					<div class="form_row">
						<label class="name_txt">Event type : </label>
						<form:select class="form-control" id="eventtypeid" path="eventtypeid">
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
				        	<form:input type="text" class="form-control add_date" id="eventdate" name="eventdate" path="eventdate" readonly="true"></form:input>
							<span class="input-group-addon"><img src='<spring:url value="/resources/images/date.png"/>' alt=""></span>
						</div>
						<form:errors path="eventdate" element="div" cssClass="alert alert-danger err"></form:errors>
				        <div class="clearfix"></div>
				        <br>
				        <div class="dropdown-header">Attachment :</div>
				        <div class="divider"></div>
				        <!-- <div style="width: 200px; height: 200px; background: grey; cursor: pointer;" id="pasteTarget" contenteditable='true' onpaste='handlepaste(this, event)'></div> -->
					</div>
					
					<!-- ---------------------------------------------- -->
					<%-- <div style="width: 500px; padding: 20px">
							<input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/attach/upload" multiple>
							<div id="dropzone" class="fade well">Drop files here</div>
								<div id="progress" class="progress">
								<div class="bar" style="width: 0%;"></div>
							</div>
							<div id="fileError">
							
							</div>
								<table id="uploaded-files" class="table">
								<tr>
									<th>File Name</th>
									<th>File Size</th>
									<th>File Type</th>
									<th>Status</th>
									<!-- <th>Download</th> -->
								</tr>
							</table>
					</div> --%>
					<!-- ---------------------------------------------- -->
					
					
						<div id="fileuploader">Upload</div>
						 <p><strong id="log-name">You can also paste images (ctrl+v) from your clipboard. Remember to save event after pasting</strong></p>
                    <ul id="log-box"> </ul>
                    <button id="clear-logs" type="button" class="btn_blue btn">Clear images</button>
                    


						
           
						
						<!-- <div id="eventsmessage"></div> -->
					<!-- ----------------------------------------------- -->
						<div>
				              <c:forEach var="attachlist" items="${attachments}">
					            		<ul class="imgList"></ul>
										<span style="list-style: none outside none; margin: 0; padding: 0 14px 14px; display: inline-block; float:left;">
											<img style="width: 80px; height: 50px;" alt="" src="${pageContext.request.contextPath}/attach/getImage/${attachlist.id}">
											<a class="btn" onclick="onDel(${attachlist.id},this);">&times;</a>
										</span>
							  </c:forEach>
							<%--  <c:if test="${empty attachments}">
							 	<tr>
							 		<td colspan="4"><label style="margin-left:100px;">No attachment available</label></td>
							 	</tr>
							 </c:if> --%>
            			</div>
            			<div class="clearfix"></div>
					<br>
					<input type="hidden" id="refId" name="refId" value="${refId}">
					<input type="hidden" id="createIn" name="createIn" value="${createIn}">
					<div class="form_row">
						<label class="name_txt">Attachment description :</label>
							<textarea class="form-control add_name" id="attachmentDesc" name="attachmentDesc" rows="5" cols="30" placeholder="Enter Attachment Description" title="Enter Alphanumeric">${attachmentDesc}</textarea>
					</div>
					
	                <%-- <div class="form-group">
						<label class="col-sm-5 control-label" for="Currency Name">Uploaded Image File</label>
						<div class="col-sm-3">
							<img style="width: 200px; height: 100px;" src="${pageContext.request.contextPath}/getImage/${attachmentId}" alt="No any Image uploaded">
						</div>
					</div> --%>
	                 <input type="hidden" name="newInst" id="newInst" value="${newInst}">
	                <%-- <input type="hidden" name="isUpdate" value="${isUpdate}"> --%>
					<div class="button_back">
						<a onclick="return saveEvent();" class="btn_blue btn">save event</a>
						<a onclick="return eventList();" class="btn_blue btn"> cancel</a>
					</div>				
	                <div class="clearfix"></div>
              </form:form>   
            </div>
            <%-- <div class="form_rgt col-md-5">
              <c:forEach var="attachlist" items="${attachments}">
	            	<div>
	            		<ul class="imgList"></ul>
						<span style="list-style: none outside none; margin: 0; padding: 0 14px 14px; display: inline-block;">
							<img style="width: 80px; height: 50px;" alt="" src="${pageContext.request.contextPath}/attach/getImage/${attachlist.id}">
						</span>
					</div>
			  </c:forEach>
			 <c:if test="${empty attachments}">
			 	<tr>
			 		<td colspan="4"><label style="margin-left:100px;">No attachment available</label></td>
			 	</tr>
			 </c:if>
            </div> --%>                                     
          </div>
          
          
         <!-- -------------------------------------- -->          
          <%-- <div class="table-responsive">
          <form name="tradeMng" id="tradeMng" method="post">
            <table class="table table-bordered table-hover table-striped datatable" id="userTable">
              <thead>
                <tr>
                  <th class="header" style="background-color:#3B5998">Description <i class="fa fa-sort"></i></th>
                  <th class="header" style="background-color:#3B5998">Date <i class="fa fa-sort"></i></th>
                  <th class="header" style="background-color:#3B5998">New value <i class="fa fa-sort"></i></th>
                  <th class="header" style="background-color:#3B5998">Event type <i class="fa fa-sort"></i></th>                  
                  <th class="header" style="background-color:#3B5998">
                  	<label class="add_delete">
                  		
                  	</label>
                  </th>
                </tr>
              </thead>
              <tbody>
              
              <c:forEach items="${eventList}" var="events">
	                <tr>
	                  <td class="gray_table"><c:out value="${events.description}"/></td>
	                  <fmt:formatDate value="${events.eventdate}" pattern="MM/dd/yyyy" var="eventDate" />
	                  <td class="gray_table"><c:out value="${eventDate}"/></td>
	                  <td><c:out value="${events.newvalue}"/></td>
	                  <td class="gray_table"><c:out value="${events.getTradeeventtype().getName()}"/></td>
	                  <td>
	                  	<label class="add_delete">
	                  		<a href="#" onclick="onEdit(${trades.id});">View</a>                			
	                  		<a href="#" onclick="onDel(${trades.id});"><img src="/trademanagement-web/resources/images/delete.png" alt=""></a>
                  			<input type="hidden" id="refId" name="refId">
	                  	</label>
	                  </td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
            </form>             
          </div> --%>
          <!-- -------------------------------------- --> 
        </div>
    </div>
  </div>
</div>
<!-- <link href="http://hayageek.github.io/jQuery-Upload-File/uploadfile.min.css" rel="stylesheet"> -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
<%-- <script src='<spring:url value="/resources/js/assests/jquery.ui.widget.js"/>'></script> --%>
<%-- <script src='<spring:url value="/resources/js/assests/jquery.iframe-transport.js"/>'></script> --%>
<%-- <script src='<spring:url value="/resources/js/assests/jquery.fileupload.js"/>'></script> --%>
<!-- <script src='https://github.com/blueimp/jQuery-File-Upload/blob/master/js/jquery.fileupload.js'></script> -->
<%-- <script src='<spring:url value="/resources/js/assests/jquery.ui.widget.js"/>'></script>
<script src='<spring:url value="/resources/js/assests/jquery.iframe-transport.js"/>'></script>
<script src='<spring:url value="/resources/js/assests/jquery.fileupload.js"/>'></script>
<script src='<spring:url value="/resources/js/assests/jquery.fileupload-process.js"/>'></script>
<script src='<spring:url value="/resources/js/assests/jquery.fileupload-image.js"/>'></script>
<script src='<spring:url value="/resources/js/assests/jquery.fileupload-validate.js"/>'></script>


<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>

<link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/assests/style.css"/>' /> --%>
<%-- <link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/assests/jquery.fileupload.css"/>' /> --%>
<script src='<spring:url value="/resources/js/assests/jquery.uploadfile.min.js"/>'></script>
<link rel="stylesheet" href='<spring:url value="/resources/css/assests/uploadfile.min.css"/>'>
<%-- <link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/assests/dropzone.css"/>' /> --%>
<%-- <link type="text/css" rel="stylesheet" href='<spring:url value="/resources/css/assests/bootstrap.css"/>' /> --%>
<script type="text/javascript">

$(document).ready(function(){	

    document.body.onpaste = function (e) {
        var items = e.clipboardData.items;
        for (var i = 0; i < items.length; ++i) {

            if (items[i].kind == 'file' &&
                items[i].type.indexOf('image/') !== -1) {

                var blob = items[i].getAsFile();
                window.URL = window.URL || window.webkitURL;
                var blobUrl = window.URL.createObjectURL(blob);

                var img = document.createElement('img');
                img.src = blobUrl;
                var logBox = document.getElementById('log-box');
                logBox.appendChild(img);
                alert("image added");
                var xhr = new XMLHttpRequest();
             
            	 xhr.upload.onprogress = function(e) {
            	        var percentComplete = (e.loaded / e.total) * 100;
            	        console.log("Uploaded: " + percentComplete);
            	    };

            	    xhr.onload = function() {
            	        if (xhr.status == 200) {
            	            alert("Sucess! Upload completed");
            	        } else {
            	            alert("Error! Upload failed");
            	        }
            	    };
            	   var formData = new FormData();
            	   //formData.append("myfile", blobUrl);
            	   formData.append("myfile", blob);
            	   
            	xhr.open("POST","${pageContext.request.contextPath}/attach/uploadfile",true);
            	xhr.send(formData);
            	alert("sent");
            }

        }
    }

    var btn = document.getElementById('clear-logs')
    btn.addEventListener('click', function (e) {
        clearLog();
    });
    
    var uplbtn = document.getElementById('upload-pasted-images')
    uplbtn.addEventListener('click', function (e) {
    	 var xhr = new XMLHttpRequest();
    	url =  "${pageContext.request.contextPath}/attach/uploadfile";
    	xhr.send(file);
        
    });
    
});


function clearLog() {
    var node = document.getElementById('log-box');
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}  

	$(document).ready(function(){	
		 $('#datePicker3').datepicker({
		        orientation: "auto left",
		        autoclose: true,
		        todayHighlight: true
		        });
	});	  
	
	$("#fileuploader").uploadFile({
		url:"${pageContext.request.contextPath}/attach/uploadfile",
		multiple:true,
		allowedTypes:"jpg,jpeg,png,gif",
		fileName:"myfile",
		showDelete: true,		
		
		onSuccess:function(files,data,xhr){
			/*var i=0;
			 $.each(data, function () {
				$("#eventsmessage").append(
            			$('<tr/>')
            			.append($('<td/>').text(data[i].fileName))
            			.append($('<td/>').text(data[i].fileSize))
            			.append($('<td/>').text(data[i].fileType))
            			.append($('<td/>').text(data[i].status))
            			//.append($('<td/>').html("<a href='${pageContext.request.contextPath}/attach/get/"+index+"'>Click</a>"))
            			);
				i++;
			}); */
			//$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Success for: "+JSON.stringify(data));
		},
		 deleteCallback: function (data, pd) {
			var fileName = pd.filename[0].childNodes[0].data;
			var splitFileName = fileName.split(").");
		      for (var i = 0; i < data.length; i++) {
		    	 if(data[i].oldName.trim()===splitFileName[1].trim()){
		    		 $.post("${pageContext.request.contextPath}/attach/removeUploaded", {op: "delete",name: data[i].fileName},
				     function (resp,textStatus, jqXHR) {
			         	pd.statusbar.hide(); //You choice.
				      });
		    	 }
		         /* $.post("${pageContext.request.contextPath}/attach/removeUploaded", {op: "delete",name: splitFileName[1]},
		             function (resp,textStatus, jqXHR) {
		                 //Show Message	
		                 //if(resp==true){
		                	 pd.statusbar.hide(); //You choice.
		                 //}
		             }); */
		     }
		     //pd.statusbar.hide(); //You choice.
		},
	});
	
 	
	function saveEvent(){
		var validString = new RegExp('^[^\"]+$');
		var desc = $("#description").val().replace(/^\s+|\s+$/g,"");
		var valBalanceRX = new RegExp('^[0-9]\.+$');
		var newval = $("#newvalue").val();
		var floatTest= /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/;
		var valeveTypeRX = new RegExp('^[1-9]+$');
		var eventType =  parseInt($('#eventtypeid :selected').attr("value"));		
		var eveDate = $("#eventdate").val().replace(/^\s+|\s+$/g,"");
		var valDateRX = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
		if(!validString.test(desc)){
			$('#description').after("<p id='descerror' style='color: red; float: right; width: 322px;'>Enter description</p>");	
			$('#description').focus();
			$("#descerror").fadeOut(5000);
			return false;
		}else if(newval != null && /\S/.test(newval) && !floatTest.test(newval)){
			$('#newvalue').after("<p id='newvalueerror' style='color: red; float: right; width: 322px;'>Enter new value</p>");				
			$('#newvalue').focus();
			$("#newvalueerror").fadeOut(5000);
			return false;
		}else if(!valeveTypeRX.test(eventType)){
			$('#eventtypeid').after("<p id='eventTypeerror' style='color: red; float: right; width: 322px;'>Select event type</p>");				
			$('#eventtypeid').focus();
			$("#eventTypeerror").fadeOut(5000);
			return false;
		}else if(!valDateRX.test(eveDate)){
			$('#eventdate').after("<p id='dateerror' style='color: red; float: right; width: 322px;'>Select open date</p>");				
			$('#eventdate').focus();
			$("#dateerror").fadeOut(5000);
			return false;
		}else{
			$("#tradeForm").attr("method","POST");
			$("#tradeForm").attr("action","${pageContext.request.contextPath}/trade/updateevent");
			$("#tradeForm").submit();
		}
	}
	function eventList(){
		$("#tradeForm").attr("method","POST");
		$("#tradeForm").attr("action","${pageContext.request.contextPath}/trade/eventAttachment");
		$("#tradeForm").submit();
		
	}	
	//function handlepaste(elem,e){	
		//var items = e.clipboardData.items;
		//var itms = (e.clipboardData || e.originalEvent.clipboardData).types;
		//var itms = (e.clipboardData).type.indexOf("image") === 0;
		//var items = (e.clipboardData  || e.originalEvent.clipboardData).items;
		//alert("file : " + e.clipboardData.types);
	//	alert("Items : " + JSON.stringify(items) + " : " + items);
		//if (items){
		//	alert("inside : " + items.length);
            //for (var i = 0; i < items.length; i++) {
                //if (items.type.indexOf("image") !== -1) {
                //    var blob = items.getAsFile();
                 //   var URLObj = window.URL || window.webkitURL;
                 //   var source = URLObj.createObjectURL(blob);
                //    alert("source : " + source);
                //    paste_createImage(source);
             //       }
            //    }
         //   }
        // If we can't handle clipboard data directly (Firefox),
        // we need to read what was pasted from the contenteditable element        
		/* if (e.clipboardData.getData.indexOf("image") === 0) {
		      alert("its image");
		}else{
			
		} */
		/* if (e && e.clipboardData && e.clipboardData.getData) {// Webkit - get data from clipboard, put into editdiv, cleanup, then cancel event
	        if (/text\/plain/.test(e.clipboardData.types)) {
	            elem.innerHTML = e.clipboardData.getData('text/plain');
	            alert("its an image");
	        }else{
	        	alert("invalid attachment");
	        	this.innerHTML = "";
	        }
		}*/
	//}
    /* function paste_check_Input(){
	    var child = pasteCatcher.childNodes[0];
	    pasteCatcher.innerHTML = "";
	    if (child){
	        if (cild.tagName === "IMG"){
	            paste_createImage(child.src);
	            }
	        }
	}
	function paste_createImage(source){
	    var pastedImage = new Image();
	    pastedImage.onload = function() {
	    	alert("pastedImage : " + pastedImage);
	        //ctx.drawImage(pastedImage, 0, 0);
	    }
	    pastedImage.src = source;
	} */
</script>
<!-- <link rel="stylesheet" type="text/css" media="all" href="http://strd6.com/wp-content/themes/twentyeleven/style.css" /> -->
<!-- script type='text/javascript' src='http://strd6.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script-->
