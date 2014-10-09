package org.traderjournal.web.client.svc;


import java.util.List;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AccountService")
public interface AccountService extends RemoteService {
	public static final String url = "AccountService";
	List<Account> getAccountListForUser(String userID);
	List<Account> getAccountListForTrader(Trader trader);
	Account getAccountDetail(int accountID);
	
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static AccountServiceAsync instance;
		public static AccountServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(AccountService.class);
			}
			return instance;
		}
	}
}
