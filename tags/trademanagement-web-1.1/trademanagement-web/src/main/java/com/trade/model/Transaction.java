package com.trade.model;

//Generated Jul 3, 2014 3:16:35 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Transaction generated by hbm2java
 */
@Entity
@Table(name = "transaction", catalog = "trademgmt")
public class Transaction implements java.io.Serializable {

	private Integer id;
	private Transactiontype transactiontype;
	private Accounts accounts;
	private BigDecimal transactionvalue;
	private Date lastupdtdate;
	private Integer updatedby;

	public Transaction() {
	}

	public Transaction(Transactiontype transactiontype, Accounts accounts) {
		this.transactiontype = transactiontype;
		this.accounts = accounts;
	}

	public Transaction(Transactiontype transactiontype, Accounts accounts,
			BigDecimal transactionvalue, Date lastupdtdate, Integer updatedby) {
		this.transactiontype = transactiontype;
		this.accounts = accounts;
		this.transactionvalue = transactionvalue;
		this.lastupdtdate = lastupdtdate;
		this.updatedby = updatedby;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transactiontype_id", nullable = false)
	public Transactiontype getTransactiontype() {
		return this.transactiontype;
	}

	public void setTransactiontype(Transactiontype transactiontype) {
		this.transactiontype = transactiontype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", nullable = false)
	public Accounts getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	@Column(name = "transactionvalue", precision = 7)
	public BigDecimal getTransactionvalue() {
		return this.transactionvalue;
	}

	public void setTransactionvalue(BigDecimal transactionvalue) {
		this.transactionvalue = transactionvalue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdtdate", length = 19)
	public Date getLastupdtdate() {
		return this.lastupdtdate;
	}

	public void setLastupdtdate(Date lastupdtdate) {
		this.lastupdtdate = lastupdtdate;
	}

	@Column(name = "updatedby")
	public Integer getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}

}
