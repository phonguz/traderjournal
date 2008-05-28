package traderjournal.model.hibernate;

// Generated 2008/05/28 12:26:25 by Hibernate Tools 3.2.1.GA

/**
 * Transaction generated by hbm2java
 */
public class Transaction implements java.io.Serializable {

	private int id;
	private TransactionType transactionType;
	private double transactionValue;

	public Transaction() {
	}

	public Transaction(int id, TransactionType transactionType,
			double transactionValue) {
		this.id = id;
		this.transactionType = transactionType;
		this.transactionValue = transactionValue;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionValue() {
		return this.transactionValue;
	}

	public void setTransactionValue(double transactionValue) {
		this.transactionValue = transactionValue;
	}

}
