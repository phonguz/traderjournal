package traderjournal.model.entities;

// Generated 2009/09/11 03:55:57 by Hibernate Tools 3.2.5.Beta

import java.util.HashSet;
import java.util.Set;

/**
 * Instrument generated by hbm2java
 */
public class Instrument implements java.io.Serializable , Comparable<Instrument>{

	private int id;
	private Ccy ccy;
	private String name;
	private Double valuePerPoint;
	private Set trades = new HashSet(0);

	public Instrument() {
	}

	public Instrument(int id) {
		this.id = id;
	}

	public Instrument(int id, Ccy ccy, String name, Double valuePerPoint,
			Set trades) {
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

	public Set getTrades() {
		return this.trades;
	}

	public void setTrades(Set trades) {
		this.trades = trades;
	}

	@Override
	public int compareTo(Instrument arg0) {
		return this.getName().compareToIgnoreCase(arg0.getName());
	}

}