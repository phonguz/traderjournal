package org.traderjournal.model.gen;

// Generated 06 Apr 2011 7:47:04 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.sf.gilead.pojo.gwt.LightEntity;

/**
 * Trade generated by hbm2java
 */
public class Trade extends LightEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Trader trader;
	private Instrument instrument;
	private Account account;
	private Date openTradeDate;
	private Double openprice;
	private Date closeTradeDate;
	private Double closeprice;
	private Double stoploss;
	private Double tp;
	private Double entrycoms;
	private Double exitcoms;
	private Double entryfee;
	private Double exitfee;
	private Double pl;
	private String reference;
	private Double carrycost;
	private Double qty;
	private Set<Tradeevent> tradeevents = new HashSet<Tradeevent>(0);

	public Trade() {
	}

	public Trade(Trader trader, Account account) {
		this.trader = trader;
		this.account = account;
	}

	public Trade(Trader trader, Instrument instrument, Account account,
			Date openTradeDate, Double openprice, Date closeTradeDate,
			Double closeprice, Double stoploss, Double tp, Double entrycoms,
			Double exitcoms, Double entryfee, Double exitfee, Double pl,
			String reference, Double carrycost, Double qty,
			Set<Tradeevent> tradeevents) {
		this.trader = trader;
		this.instrument = instrument;
		this.account = account;
		this.openTradeDate = openTradeDate;
		this.openprice = openprice;
		this.closeTradeDate = closeTradeDate;
		this.closeprice = closeprice;
		this.stoploss = stoploss;
		this.tp = tp;
		this.entrycoms = entrycoms;
		this.exitcoms = exitcoms;
		this.entryfee = entryfee;
		this.exitfee = exitfee;
		this.pl = pl;
		this.reference = reference;
		this.carrycost = carrycost;
		this.qty = qty;
		this.tradeevents = tradeevents;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public Instrument getInstrument() {
		return this.instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getOpenTradeDate() {
		return this.openTradeDate;
	}

	public void setOpenTradeDate(Date openTradeDate) {
		this.openTradeDate = openTradeDate;
	}

	public Double getOpenprice() {
		return this.openprice;
	}

	public void setOpenprice(Double openprice) {
		this.openprice = openprice;
	}

	public Date getCloseTradeDate() {
		return this.closeTradeDate;
	}

	public void setCloseTradeDate(Date closeTradeDate) {
		this.closeTradeDate = closeTradeDate;
	}

	public Double getCloseprice() {
		return this.closeprice;
	}

	public void setCloseprice(Double closeprice) {
		this.closeprice = closeprice;
	}

	public Double getStoploss() {
		return this.stoploss;
	}

	public void setStoploss(Double stoploss) {
		this.stoploss = stoploss;
	}

	public Double getTp() {
		return this.tp;
	}

	public void setTp(Double tp) {
		this.tp = tp;
	}

	public Double getEntrycoms() {
		return this.entrycoms;
	}

	public void setEntrycoms(Double entrycoms) {
		this.entrycoms = entrycoms;
	}

	public Double getExitcoms() {
		return this.exitcoms;
	}

	public void setExitcoms(Double exitcoms) {
		this.exitcoms = exitcoms;
	}

	public Double getEntryfee() {
		return this.entryfee;
	}

	public void setEntryfee(Double entryfee) {
		this.entryfee = entryfee;
	}

	public Double getExitfee() {
		return this.exitfee;
	}

	public void setExitfee(Double exitfee) {
		this.exitfee = exitfee;
	}

	public Double getPl() {
		return this.pl;
	}

	public void setPl(Double pl) {
		this.pl = pl;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getCarrycost() {
		return this.carrycost;
	}

	public void setCarrycost(Double carrycost) {
		this.carrycost = carrycost;
	}

	public Double getQty() {
		return this.qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Set<Tradeevent> getTradeevents() {
		return this.tradeevents;
	}

	public void setTradeevents(Set<Tradeevent> tradeevents) {
		this.tradeevents = tradeevents;
	}

}
