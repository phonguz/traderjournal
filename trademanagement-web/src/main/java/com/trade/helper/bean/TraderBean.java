package com.trade.helper.bean;

public class TraderBean {

	private String id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String confirmPassword;
	private String lastUpdtDate;
	private String updateBy;
	private String oldPassword;
	private boolean updateMode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getLastUpdtDate() {
		return lastUpdtDate;
	}
	public void setLastUpdtDate(String lastUpdtDate) {
		this.lastUpdtDate = lastUpdtDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isUpdateMode() {
		return updateMode;
	}
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}	
}
