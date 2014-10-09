package com.trade.helper.bean;

public class AccountBean {

	private String id;
	private String name;
	private String balance;
	private String percentageRisk;
	private String lastUpdtDt;
	private String updatedBy;
	private String ccyId;
	private String transactionTypeId;
	private String transactionAmount;
	
	
	public AccountBean() {
		id="";
		name="";
		balance="";
		percentageRisk="";
		lastUpdtDt="";
		updatedBy="";
		ccyId="";
		transactionTypeId="";
		transactionAmount="";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPercentageRisk() {
		return percentageRisk;
	}

	public void setPercentageRisk(String percentageRisk) {
		this.percentageRisk = percentageRisk;
	}

	public String getLastUpdtDt() {
		return lastUpdtDt;
	}

	public void setLastUpdtDt(String lastUpdtDt) {
		this.lastUpdtDt = lastUpdtDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCcyId() {
		return ccyId;
	}

	public void setCcyId(String ccyId) {
		this.ccyId = ccyId;
	}

	public String getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


}
