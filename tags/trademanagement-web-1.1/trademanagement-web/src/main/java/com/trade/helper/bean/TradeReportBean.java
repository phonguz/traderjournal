package com.trade.helper.bean;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;



public class TradeReportBean {

	
    private String trader;
	private Integer tradeId;
	private String accname;
	private String instmnt;
	private Date opendt;
	private BigDecimal openprc;
	private Date closedt;
	private BigDecimal closeprc;
	private BigDecimal stoploss;
	private Integer qnt;
	private BigDecimal bal;
	private Integer risk;
	public List<TradeEventBean> events;
	
	
	 
	
	
	
	public List<TradeEventBean> getEvents() {
		return events;
	}
	public void setEvents(List<TradeEventBean> events) {
		this.events = events;
	}
	 
	
	
 
	 
	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getInstmnt() {
		return instmnt;
	}
	public void setInstmnt(String instmnt) {
		this.instmnt = instmnt;
	}
	public Date getOpendt() {
		return opendt;
	}
	public void setOpendt(Date opendt) {
		this.opendt = opendt;
	}
	public BigDecimal getOpenprc() {
		return openprc;
	}
	public void setOpenprc(BigDecimal openprc) {
		this.openprc = openprc;
	}
	public Date getClosedt() {
		return closedt;
	}
	public void setClosedt(Date closedt) {
		this.closedt = closedt;
	}
	public BigDecimal getCloseprc() {
		return closeprc;
	}
	public void setCloseprc(BigDecimal closeprc) {
		this.closeprc = closeprc;
	}
	public BigDecimal getStoploss() {
		return stoploss;
	}
	public void setStoploss(BigDecimal stoploss) {
		this.stoploss = stoploss;
	}
	public Integer getQnt() {
		return qnt;
	}
	public void setQnt(Integer qnt) {
		this.qnt = qnt;
	}
	public BigDecimal getBal() {
		return bal;
	}
	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}
	public Integer getRisk() {
		return risk;
	}
	public void setRisk(Integer risk) {
		this.risk = risk;
	}
	 
	/**
	 * @return the trader
	 */
	public String getTrader() {
		return trader;
	}
	/**
	 * @param trader the trader to set
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}
}
