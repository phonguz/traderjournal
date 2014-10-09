package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trade;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TradeService")
public interface TradeService extends RemoteService {
	public static final String url = "TradeService";
	List<Trade> getAllTradesForAccount(Account account);
	
	Trade getTradeDetail(int tradeID);
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static TradeServiceAsync instance;
		public static TradeServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(TradeService.class);
			}
			return instance;
		}
	}
}
