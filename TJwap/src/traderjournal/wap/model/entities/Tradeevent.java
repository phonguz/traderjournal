package traderjournal.wap.model.entities;

// Generated 2009/09/11 03:55:57 by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Tradeevent implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="event_type_id")
	private Tradeeventtype tradeeventtype;
	
	@ManyToOne
	@JoinColumn(name="tradeid")
	private Trade trade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_date")
	private Date eventDate;
	private String description;
	private Integer eventorder;
	@Column(name="new_value")
	private Double newValue;
	
	@OneToMany(mappedBy="tradeevent", fetch=FetchType.LAZY, cascade = CascadeType.ALL) 
	private List<Tradeeventimage> tradeeventimages = new ArrayList<Tradeeventimage>();
	
	public Tradeevent() {
	}

	public Tradeevent(int id, Tradeeventtype tradeeventtype, Trade trade) {
		this.id = id;
		this.tradeeventtype = tradeeventtype;
		this.trade = trade;
	}

	public Tradeevent(int id, Tradeeventtype tradeeventtype, Trade trade,
			Date eventDate, String description, Integer eventorder,
			Double newValue, List<Tradeeventimage> tradeeventimages) {
		this.id = id;
		this.tradeeventtype = tradeeventtype;
		this.trade = trade;
		this.eventDate = eventDate;
		this.description = description;
		this.eventorder = eventorder;
		this.newValue = newValue;
		this.tradeeventimages = tradeeventimages;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tradeeventtype getTradeeventtype() {
		return this.tradeeventtype;
	}

	public void setTradeeventtype(Tradeeventtype tradeeventtype) {
		this.tradeeventtype = tradeeventtype;
	}

	public Trade getTrade() {
		return this.trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEventorder() {
		return this.eventorder;
	}

	public void setEventorder(Integer eventorder) {
		this.eventorder = eventorder;
	}

	public Double getNewValue() {
		return this.newValue;
	}

	public void setNewValue(Double newValue) {
		this.newValue = newValue;
	}

	public List<Tradeeventimage> getTradeeventimages() {
		return this.tradeeventimages;
	}

	public void setTradeeventimages(List<Tradeeventimage> tradeeventimages) {
		this.tradeeventimages = tradeeventimages;
	}

}
