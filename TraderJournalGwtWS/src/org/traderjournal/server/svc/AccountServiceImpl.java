package org.traderjournal.server.svc;

import java.util.List;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.traderjournal.TJHibernateUtil;
import org.traderjournal.model.dao.AccountHome;
import org.traderjournal.model.dao.ext.AccountHomeExt;
import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trader;
import org.traderjournal.web.client.svc.AccountService;

public class AccountServiceImpl extends PersistentRemoteService implements AccountService {
	Session currentSession= null;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountServiceImpl(){
		HibernateUtil hibernateUtil = new HibernateUtil(TJHibernateUtil.getSessionFactory());
		currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
		PersistentBeanManager persistentBeanManager = GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateUtil);
		setBeanManager(persistentBeanManager);

	}
	
	
	@Override
	public List<Account> getAccountListForUser(String userID) {
		List<Account> accts = AccountHome.findAll(currentSession);
		return accts;
	}

	@Override
	public Account getAccountDetail(int accountID) {
		Account ac = AccountHome.findById(currentSession, new Integer(accountID));
		return ac;
	}


	@Override
	public List<Account> getAccountListForTrader(Trader trader) {
		HibernateUtil hibernateUtil = new HibernateUtil(TJHibernateUtil.getSessionFactory());
		currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		List<Account> accts = AccountHomeExt.findByTrader(currentSession,trader);
		tx.commit();
		return accts;
	}
	
}
