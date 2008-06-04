package traderjournal.model.hibernate;

// Generated 2008/06/04 10:16:42 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Trade generated by hbm2java
 */
public class Trade implements java.io.Serializable {

	private int id;
	private Account account;
	private Date openTradeDate;
	private Double openprice;
	private Date closeTradeDate;
	private Double closeprice;
	private Double stoploss;
	private Double tp;
	private Integer qty;
	private String instrument;
	private Double entrycoms;
	private Double exitcoms;
	private Double entryfee;
	private Double exitfee;
	private Double pl;
	private String reference;
	private Double carrycost;
	private Set<Tradeevent> tradeevents = new HashSet<Tradeevent>(0);

	public Trade() {
	}

	public Trade(int id, Account account) {
		this.id = id;
		this.account = account;
	}

	public Trade(int id, Account account, Date openTradeDate, Double openprice,
			Date closeTradeDate, Double closeprice, Double stoploss, Double tp,
			Integer qty, String instrument, Double entrycoms, Double exitcoms,
			Double entryfee, Double exitfee, Double pl, String reference,
			Double carrycost, Set<Tradeevent> tradeevents) {
		this.id = id;
		this.account = account;
		this.openTradeDate = openTradeDate;
		this.openprice = openprice;
		this.closeTradeDate = closeTradeDate;
		this.closeprice = closeprice;
		this.stoploss = stoploss;
		this.tp = tp;
		this.qty = qty;
		this.instrument = instrument;
		this.entrycoms = entrycoms;
		this.exitcoms = exitcoms;
		this.entryfee = entryfee;
		this.exitfee = exitfee;
		this.pl = pl;
		this.reference = reference;
		this.carrycost = carrycost;
		this.tradeevents = tradeevents;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getInstrument() {
		return this.instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
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

	public Set<Tradeevent> getTradeevents() {
		return this.tradeevents;
	}

	public void setTradeevents(Set<Tradeevent> tradeevents) {
		this.tradeevents = tradeevents;
	}

}
