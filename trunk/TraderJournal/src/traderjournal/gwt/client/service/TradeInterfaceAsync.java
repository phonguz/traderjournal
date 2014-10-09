package traderjournal.gwt.client.service;

import java.util.List;

import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeInterfaceAsync {

	public void findAll(AsyncCallback<List<Trade>> callback);
	public void findTradeByTradeID(int tradeID, AsyncCallback<Trade> callback);
	public void  findAllInstruments(AsyncCallback<List<Instrument>> callback);
}
