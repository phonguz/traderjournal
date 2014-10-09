package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trade;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeServiceAsync {

	void getAllTradesForAccount(Account account,
			AsyncCallback<List<Trade>> callback);

	void getTradeDetail(int tradeID, AsyncCallback<Trade> callback);

	
	
}
