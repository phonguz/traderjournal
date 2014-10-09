package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trader;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync {

	void getAccountListForUser(String userID,
			AsyncCallback<List<Account>> callback);

	void getAccountDetail(int accountID, AsyncCallback<Account> callback);

	void getAccountListForTrader(Trader trader,
			AsyncCallback<List<Account>> callback);

}
