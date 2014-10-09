package org.traderjournal.web.client;

import java.util.List;

import javax.security.auth.login.AccountLockedException;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trader;
import org.traderjournal.web.client.svc.AccountService;
import org.traderjournal.web.client.svc.AccountServiceAsync;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class TradeTreeViewModel implements TreeViewModel {
	AccountServiceAsync accountservice = (AccountServiceAsync) GWT
			.create(AccountService.class);

	public TradeTreeViewModel() {
		super();
		ServiceDefTarget serviceDefaccount = (ServiceDefTarget) accountservice;
		serviceDefaccount.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ AccountService.url);
		accountDataProvider = new ListDataProvider<Account>();
		loadAllAccounts();

	}

	private void loadAllAccounts() {

		Trader trader = new Trader();
		trader.setId(0);
		AsyncCallback<List<Account>> callback = new AsyncCallback<List<Account>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Account> result) {

				accountDataProvider.setList(result);

			}

		};
		accountservice.getAccountListForTrader(trader, callback);

	}

	private final ListDataProvider<Account> accountDataProvider;

	/**
	 * The cell used to render categories.
	 */
	private static class AccountCell extends AbstractCell<Account> {

		/**
		 * The html of the image used for contacts.
		 */
		private final String imageHtml;

		public AccountCell() {
			imageHtml = "";
		}

		@Override
		public void render(Context context, Account value, SafeHtmlBuilder sb) {
			if (value != null) {
				sb.appendHtmlConstant(imageHtml).appendEscaped(" ");
				sb.appendEscaped(value.getName());
			}
		}
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			// Return top level accounts.
			return new DefaultNodeInfo<Account>(accountDataProvider,
					new AccountCell());
		} else if (value instanceof Account) {

		}
		// Unhandled type.
		String type = value.getClass().getName();
		throw new IllegalArgumentException("Unsupported object type: " + type);

	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}
