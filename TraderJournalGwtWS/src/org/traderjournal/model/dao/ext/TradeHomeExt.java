package org.traderjournal.model.dao.ext;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.dao.TradeHome;
import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trade;

public class TradeHomeExt extends TradeHome {
	
	public static final List<Trade> findByAccounID(Session _session, int accountID) {
		Query query = _session
				.createQuery("from Trade as Trade where Trade.account.id = :accountid");
		query.setParameter("accountid", accountID);
		return (List<Trade>) query.list();
	}

	public static final List<Trade> findAndLoadAllByAccount(Session _session, Account account) {
		Query query = _session
				.createQuery("from Trade as Trade inner join fetch Trade.account inner join fetch Trade.instrument where Trade.account = :account order by Trade.openTradeDate asc");
		query.setParameter("account", account);
		return (List<Trade>) query.list();
	}

}
