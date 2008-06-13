package traderjournal.model.hibernate;

// Generated 2008/06/12 11:43:13 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tradeevent generated by hbm2java
 */
public class Tradeevent extends net.sf.hibernate4gwt.pojo.gwt.LazyGwtPojo
		implements java.io.Serializable {

	private int id;
	private Tradeeventtype tradeeventtype;
	private Trade trade;
	private Date eventDate;
	private String description;
	private Integer eventorder;
	private Double newValue;
	private Set<Tradeeventimage> tradeeventimages = new HashSet<Tradeeventimage>(
			0);

	public Tradeevent() {
	}

	public Tradeevent(int id, Tradeeventtype tradeeventtype, Trade trade) {
		this.id = id;
		this.tradeeventtype = tradeeventtype;
		this.trade = trade;
	}

	public Tradeevent(int id, Tradeeventtype tradeeventtype, Trade trade,
			Date eventDate, String description, Integer eventorder,
			Double newValue, Set<Tradeeventimage> tradeeventimages) {
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

	public Set<Tradeeventimage> getTradeeventimages() {
		return this.tradeeventimages;
	}

	public void setTradeeventimages(Set<Tradeeventimage> tradeeventimages) {
		this.tradeeventimages = tradeeventimages;
	}

}