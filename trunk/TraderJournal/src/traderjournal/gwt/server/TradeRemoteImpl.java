package traderjournal.gwt.server;

import java.util.List;

import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

public class TradeRemoteImpl  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Trade> findAll() {
		
		TradeHome th = new TradeHome();
		return th.findAll();
		
	}
	

}
