<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-dialog" >
	<div class="modal-content">
		<div class="modal-header" style="position: relative;">
			 <button type="button" style="position: absolute; right: 12px; top: 6px;" class="model-title close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">Password Reminder</h4>
		</div>
		<div class="modal-body">
			<div id="errorBox" style="font: bold; color: red;">${loginFailMsg}</div>
			<c:choose>
				<c:when test="${done eq true}">
					<div style="color: #569ffd;" id="forgotPasswordMsg">${msg}</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label>Username<span style="color: red">*</span></label>
						<input type="text" id="usernameForgotPass" class="form-control" name="usernameForgotPass" required />
					</div>
					<div style="color: #FF0000;" id="forgotPasswordMsg">${msg}</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="modal-footer">
			<c:choose>
				<c:when test="${done eq true}">
					<a class="btn btn-primary btn-lg btn-block" href="${pageContext.request.contextPath}" >OK</a>
				</c:when>
				<c:otherwise>
					<button class="btn btn-primary btn-lg btn-block" id="forgotPasswordBtn" onclick="forgotPasswordAction()"	type="submit">Reset Password</button>
					<div class="form-group" style="padding-top: 10px;float: right;">
						<span style="color: red;">* Mandatory Field</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
