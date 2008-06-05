package traderjournal.gwt.client.service;

import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TradeInterface extends RemoteService {

	public Trade[]  findAll();
	
}
