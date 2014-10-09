package traderjournal.model.entities;

// Generated 2009/09/11 03:55:57 by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import traderjournal.model.RequestFactoryUtilsJpa;

/**
 * Instrument generated by hbm2java
 */
@Entity
public class Instrument implements java.io.Serializable , Comparable<Instrument>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = -1;
	
	
	@ManyToOne
	@JoinColumn(name="base_ccy_id")
	private Ccy ccy;
	private String name;
	@Column(name="value_per_point")
	private Double valuePerPoint;
	
	@OneToMany(mappedBy="instrument", fetch=FetchType.LAZY,  cascade = {CascadeType.PERSIST,CascadeType.MERGE }) 
	private List<Trade> trades = new ArrayList<Trade>();



	public Instrument() {
	}

	public Instrument(long id) {
		this.id = id;
	}

	public Instrument(long id, Ccy ccy, String name, Double valuePerPoint,
			List<Trade> trades) {
		this.id = id;
		this.ccy = ccy;
		this.name = name;
		this.valuePerPoint = valuePerPoint;
		this.trades = trades;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public List<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	@Override
	public int compareTo(Instrument arg0) {
		return this.getName().compareToIgnoreCase(arg0.getName());
	}

	public static List<Instrument> findAll() {
		return RequestFactoryUtilsJpa.findAll(Instrument.class);
	}

}
