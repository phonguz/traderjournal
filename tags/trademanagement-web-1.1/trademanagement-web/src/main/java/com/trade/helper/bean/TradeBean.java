package com.trade.helper.bean;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.trade.model.Accounts;
import com.trade.model.Instrument;
import com.trade.model.Trader;

public class TradeBean {

	private Integer id;
	private String traderId;
	private Trader trader;
	private String instrumentId;
	private Instrument instrument;
	private String accountsId;
	private Accounts accounts;
	private String opentradedate;
	private String openprice;
	private String closetradedate;
	private String closeprice;
	private String stoploss;
	private String tp;
	private String entrycoms;
	private String exitcoms;
	private String entryfees;
	private String exitfees;
	private String reference;
	private String carrycost;
	private String quantity;
	private Date lastupdtdate;
	private Integer updatedby;
	private String instrumentName;
	private String description;
	private String newvalue;
	private String eventtypeid;
	private String eventdate;
	private String tradetype;
	private MultipartFile[] myfile;
	
	public TradeBean(){
		traderId = "";
		instrumentId = "";
		accountsId = "";
		opentradedate = "";
		openprice = "";
		closetradedate = "";
		closeprice = "";
		stoploss = "";
		tp = "";
		entrycoms = "";
		exitcoms = "";
		entryfees = "";
		exitfees = "";
		reference = "";
		carrycost = "";
		quantity = "";
		instrumentName = "";
		tradetype = "";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public String getAccountsId() {
		return accountsId;
	}
	public void setAccountsId(String accountsId) {
		this.accountsId = accountsId;
	}
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	public String getOpentradedate() {
		return opentradedate;
	}
	public void setOpentradedate(String opentradedate) {
		this.opentradedate = opentradedate;
	}
	public String getOpenprice() {
		return openprice;
	}
	public void setOpenprice(String openprice) {
		this.openprice = openprice;
	}
	public String getClosetradedate() {
		return closetradedate;
	}
	public void setClosetradedate(String closetradedate) {
		this.closetradedate = closetradedate;
	}
	public String getCloseprice() {
		return closeprice;
	}
	public void setCloseprice(String closeprice) {
		this.closeprice = closeprice;
	}
	public String getStoploss() {
		return stoploss;
	}
	public void setStoploss(String stoploss) {
		this.stoploss = stoploss;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getEntrycoms() {
		return entrycoms;
	}
	public void setEntrycoms(String entrycoms) {
		this.entrycoms = entrycoms;
	}
	public String getExitcoms() {
		return exitcoms;
	}
	public void setExitcoms(String exitcoms) {
		this.exitcoms = exitcoms;
	}
	public String getEntryfees() {
		return entryfees;
	}
	public void setEntryfees(String entryfees) {
		this.entryfees = entryfees;
	}
	public String getExitfees() {
		return exitfees;
	}
	public void setExitfees(String exitfees) {
		this.exitfees = exitfees;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCarrycost() {
		return carrycost;
	}
	public void setCarrycost(String carrycost) {
		this.carrycost = carrycost;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Date getLastupdtdate() {
		return lastupdtdate;
	}
	public void setLastupdtdate(Date lastupdtdate) {
		this.lastupdtdate = lastupdtdate;
	}
	public Integer getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNewvalue() {
		return newvalue;
	}

	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}

	public String getEventtypeid() {
		return eventtypeid;
	}

	public void setEventtypeid(String eventtypeid) {
		this.eventtypeid = eventtypeid;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public MultipartFile[] getMyfile() {
		return myfile;
	}

	public void setMyfile(MultipartFile[] myfile) {
		this.myfile = myfile;
	}
}
