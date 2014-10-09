package traderjournal.gwt.server;

import java.util.List;

import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.InstrumentHome;
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
	
	public Trade findTradeByTradeID(int tradeID){
		TradeHome th = new TradeHome();
		return th.findById(tradeID);
		
	}
	
	public List<Instrument> findAllInstruments(){
		
		InstrumentHome ih = new InstrumentHome();
		return ih.findAll();
	}
	

}
