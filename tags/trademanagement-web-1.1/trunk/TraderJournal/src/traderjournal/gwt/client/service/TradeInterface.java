package traderjournal.gwt.client.service;

import java.util.List;

import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TradeInterface extends RemoteService {

	public List<Trade> findAll();
	public Trade findTradeByTradeID(int tradeID);
	
	public List<Instrument> findAllInstruments();
}
