package org.traderjournal.model.dao.ext;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.dao.TradeHome;
import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trader;

public class AccountHomeExt extends TradeHome {
	
	public static final List<Account> findByTrader(Session _session, Trader trader) {
		Query query = _session
				.createQuery("from Account as Account where Account.trader = :trader");
		query.setParameter("trader", trader);
		return (List<Account>) query.list();
	}

}
