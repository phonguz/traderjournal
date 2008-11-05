package traderjournal.gwt.server;

import java.util.List;

import net.sf.hibernate4gwt.core.HibernateBeanManager;
import net.sf.hibernate4gwt.gwt.HibernateRemoteService;
import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.model.DBUtils;
import traderjournal.model.hibernate.Trade;

public class TradeServiceImpl extends HibernateRemoteService implements
		TradeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TradeServiceImpl() { 
		 DBUtils.setupHBM();
		 HibernateBeanManager.getInstance().setSessionFactory(DBUtils.getSessionFactory());
	}
	
	@Override
	public List<Trade> findAll() {
		
		TradeRemoteImpl ti = new TradeRemoteImpl();
		List<Trade> li = ti.findAll();
		//Trade [] ret = li.toArray(new Trade[li.size()]);
		return li;
		
//		StatelessSession ses = DBUtils.getSessionFactory().openStatelessSession();
//		Transaction tx = ses.beginTransaction();
//
//		Query query= ses.createQuery(
//				"from " + "traderjournal.model.hibernate.Trade");
//		
//		//ses.beginTransaction();
//		List<Trade> li = (List<Trade>) query.list();
//		tx.commit();
//		ses.close();
////		TradeHome th = new TradeHome();
////		
////		List<Trade> li = th.findAll();
//		if(li != null)
//			return li.toArray(new Trade[li.size()]);
//		else
//			return null;
	}

}
