package com.trade.model.marketdata;

import java.io.Serializable;

public class Last  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	String googleID;
	long last;
	public String getGoogleID() {
		return googleID;
	}
	public void setGoogleID(String googleID) {
		this.googleID = googleID;
	}
	public long getLast() {
		return last;
	}
	public void setLast(long last) {
		this.last = last;
	}
	

}
