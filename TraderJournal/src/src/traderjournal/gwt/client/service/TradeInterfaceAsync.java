package traderjournal.gwt.client.service;

import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeInterfaceAsync {

	public void findAll(AsyncCallback<Trade[]> callback);
	
}
