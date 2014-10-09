<div class="container" style="width:30%;">
	<form action="<%=request.getContextPath() %>/Login/changePassword"
		method="post" name="ingestForm" id="ingestForm">
		<h4 class="modal-title" id="myModalLabel" style="color:#428bca;font-size: 20px; font-weight:normal; padding-bottom:20px; text-align: center;">Change Password</h4>

		<div id="errorBox" style="font: bold; color: red;">${loginFailMsg}</div>
		<div>
			<input type="hidden" id="user" name="user" value="${user}" />
		</div>
		<div class="form-group">
			<label>New Password</label> <input type="password" id="chPassword" name="chPassword" class="form-control" pattern='(?=.*[0-9]{2})(?=.*[a-zA-Z]{2})(?!.*[!@#$%&*()_+=|<>?{}.:;"~`])[a-zA-Z0-9]{8,}' 
									title="Password must have a minimum of 8 characters, include at least 2 numbers and 2 letters." required />
		</div>
		<div class="form-group">
			<label>Confirm Password</label> <input type="password" id="chConfirmpassword" name="chConfirmpassword" class="form-control"
				required />
		</div>

		<button class="btn btn-primary" id="changePasswordBtn" type="submit">Change	Password</button>
	</form>
</div>