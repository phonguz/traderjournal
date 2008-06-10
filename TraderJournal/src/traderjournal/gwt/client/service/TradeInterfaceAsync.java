package traderjournal.gwt.client.service;

import java.util.List;

import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeInterfaceAsync {

	public void findAll(AsyncCallback<List<Trade>> callback);
	
}
