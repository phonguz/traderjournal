package traderjournal.model.entities;

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

/**
 * Trade generated by hbm2java
 */
@Entity
public class Trade implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = -1;
	

	@ManyToOne
	@JoinColumn(name="instrument_id")
	private Instrument instrument;

	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="open_trade_date")
	private Date openTradeDate;
	private Double openprice;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="close_trade_date")
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
	
	@OneToMany(mappedBy="trade", fetch=FetchType.EAGER, cascade = {CascadeType.ALL }) 
	private List<Tradeevent> tradeevents = new ArrayList<Tradeevent>();

	public Trade() {
	}

	public Trade(long id,  Account account) {
		this.id = id;
		
		this.account = account;
	}

	public Trade(long id,  Instrument instrument, Account account,
			Date openTradeDate, Double openprice, Date closeTradeDate,
			Double closeprice, Double stoploss, Double tp, Double entrycoms,
			Double exitcoms, Double entryfee, Double exitfee, Double pl,
			String reference, Double carrycost, Double qty,  List<Tradeevent>  tradeevents) {
		this.id = id;
		
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

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public  List<Tradeevent>  getTradeevents() {
		return this.tradeevents;
	}

	public void setTradeevents( List<Tradeevent>  tradeevents) {
		this.tradeevents = tradeevents;
	}

	

}
