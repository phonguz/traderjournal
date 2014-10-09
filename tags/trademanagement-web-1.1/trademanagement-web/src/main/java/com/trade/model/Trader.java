package com.trade.model;

// Generated Jul 2, 2014 6:03:35 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Trader generated by hbm2java
 */
@Entity
@Table(name = "trader", catalog = "trademgmt")
public class Trader implements java.io.Serializable {

	private Integer id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private byte tmppassword;
	private Date lastupdtdate;
	private Integer updatedby;
	private String type;
	private Set<Trade> trades = new HashSet<Trade>(0);
	private Set<Accounts> accountses = new HashSet<Accounts>(0);

	public Trader() {
	}

	public Trader(String type) {
		this.type = type;
	}

	public Trader(String username, String firstname, String lastname,
			String password, Date lastupdtdate, Integer updatedby, String type,
			Set<Trade> trades, Set<Accounts> accountses) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.lastupdtdate = lastupdtdate;
		this.updatedby = updatedby;
		this.type = type;
		this.trades = trades;
		this.accountses = accountses;
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

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "firstname", length = 45)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 45)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Column(name = "type", nullable = false, length = 15)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trader")
	public Set<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trader")
	public Set<Accounts> getAccountses() {
		return this.accountses;
	}

	public void setAccountses(Set<Accounts> accountses) {
		this.accountses = accountses;
	}

	/**
	 * @return the tmppassword
	 */
	public byte getTmppassword() {
		return tmppassword;
	}

	/**
	 * @param tmppassword the tmppassword to set
	 */
	public void setTmppassword(byte tmppassword) {
		this.tmppassword = tmppassword;
	}

}
