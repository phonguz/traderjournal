package com.trade.helper.bean;

import java.math.BigDecimal;
import java.util.Date;

public class TradeEventBean {

	private String eventdescription;
	private Date eventdate;
	private BigDecimal newvalue;
	private String eventtype;
	private String attachdescription;
	private String filename;
	public String getAttachdescription() {
		return attachdescription;
	}
	public void setAttachdescription(String attachdescription) {
		this.attachdescription = attachdescription;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getEventdescription() {
		return eventdescription;
	}
	public void setEventdescription(String eventdescription) {
		this.eventdescription = eventdescription;
	}
	public Date getEventdate() {
		return eventdate;
	}
	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}
	public BigDecimal getNewvalue() {
		return newvalue;
	}
	public void setNewvalue(BigDecimal newvalue) {
		this.newvalue = newvalue;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	
}
