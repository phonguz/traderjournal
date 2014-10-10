package traderjournal.model.entities;

// Generated 2009/09/11 03:55:57 by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import traderjournal.model.RequestFactoryUtilsJpa;

/**
 * Tradeeventtype generated by hbm2java
 */
@Entity
public class Tradeeventtype implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = -1;
	private String name;
	
	@OneToMany(mappedBy="tradeeventtype", fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE }) 
	private List<Tradeevent> tradeevents = new ArrayList<Tradeevent>();

	public Tradeeventtype() {
	}

	public Tradeeventtype(long id) {
		this.id = id;
	}

	public Tradeeventtype(long id, String name, List<Tradeevent>  tradeevents) {
		this.id = id;
		this.name = name;
		this.tradeevents = tradeevents;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tradeevent>  getTradeevents() {
		return this.tradeevents;
	}

	public void setTradeevents(List<Tradeevent>  tradeevents) {
		this.tradeevents = tradeevents;
	}

	public static List<Tradeeventtype> findAll() {
		
		return RequestFactoryUtilsJpa.findAll(Tradeeventtype.class);
	}

}