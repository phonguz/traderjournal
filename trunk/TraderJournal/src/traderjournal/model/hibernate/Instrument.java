package traderjournal.model.hibernate;

// Generated 2008/06/12 11:43:13 by Hibernate Tools 3.2.2.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Instrument generated by hbm2java
 */
public class Instrument extends net.sf.hibernate4gwt.pojo.gwt.LazyGwtPojo
		implements java.io.Serializable {

	private int id;
	private Ccy ccy;
	private String name;
	private Double valuePerPoint;
	private Set<Trade> trades = new HashSet<Trade>(0);

	public Instrument() {
	}

	public Instrument(int id) {
		this.id = id;
	}

	public Instrument(int id, Ccy ccy, String name, Double valuePerPoint,
			Set<Trade> trades) {
		this.id = id;
		this.ccy = ccy;
		this.name = name;
		this.valuePerPoint = valuePerPoint;
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

	public Double getValuePerPoint() {
		return this.valuePerPoint;
	}

	public void setValuePerPoint(Double valuePerPoint) {
		this.valuePerPoint = valuePerPoint;
	}

	public Set<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

}