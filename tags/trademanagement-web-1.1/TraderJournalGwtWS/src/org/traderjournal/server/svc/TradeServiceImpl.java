package org.traderjournal.server.svc;

import java.util.List;

import org.hibernate.Transaction;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.HibernateUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.hibernate.Session;
import org.traderjournal.TJHibernateUtil;
import org.traderjournal.model.dao.TradeHome;
import org.traderjournal.model.dao.ext.TradeHomeExt;
import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trade;
import org.traderjournal.web.client.svc.TradeService;

public class TradeServiceImpl extends PersistentRemoteService implements TradeService {
	Session currentSession= null;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeServiceImpl(){
		HibernateUtil hibernateUtil = new HibernateUtil(TJHibernateUtil.getSessionFactory());
		currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
		PersistentBeanManager persistentBeanManager = GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateUtil);
		setBeanManager(persistentBeanManager);

	}
	



	@Override
	public List<Trade> getAllTradesForAccount(Account account) {
		HibernateUtil hibernateUtil = new HibernateUtil(TJHibernateUtil.getSessionFactory());
		currentSession = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		List<Trade> trades = TradeHomeExt.findAndLoadAllByAccount(currentSession, account);
		
		tx.commit();
		return trades;
	}


	@Override
	public Trade getTradeDetail(int tradeID) {
		// TODO Auto-generated method stub
		return null;
	}
}
