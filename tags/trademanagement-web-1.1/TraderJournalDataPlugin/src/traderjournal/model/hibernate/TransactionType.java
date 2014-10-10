package traderjournal.model.hibernate;

// Generated 2009/09/11 03:55:57 by Hibernate Tools 3.2.5.Beta

import java.util.HashSet;
import java.util.Set;

/**
 * TransactionType generated by hbm2java
 */
public class TransactionType implements java.io.Serializable {

	private int id;
	private String type;
	private Set transactions = new HashSet(0);

	public TransactionType() {
	}

	public TransactionType(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public TransactionType(int id, String type, Set transactions) {
		this.id = id;
		this.type = type;
		this.transactions = transactions;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set transactions) {
		this.transactions = transactions;
	}

}