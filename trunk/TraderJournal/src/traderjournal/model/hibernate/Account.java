package traderjournal.model.hibernate;

// Generated 2008/06/12 11:43:13 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Account generated by hbm2java
 */
public class Account extends net.sf.hibernate4gwt.pojo.gwt.LazyGwtPojo
		implements java.io.Serializable {

	private int id;
	private Ccy ccy;
	private String name;
	private double balance;
	private Set<Trade> trades = new HashSet<Trade>(0);

	public Account() {
	}

	public Account(int id, Ccy ccy, String name, double balance) {
		this.id = id;
		this.ccy = ccy;
		this.name = name;
		this.balance = balance;
	}

	public Account(int id, Ccy ccy, String name, double balance,
			Set<Trade> trades) {
		this.id = id;
		this.ccy = ccy;
		this.name = name;
		this.balance = balance;
		this.trades = trades;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ccy getCcy() {
		return this.ccy;
	}

	public void setCcy(Ccy ccy) {
		this.ccy = ccy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

}
