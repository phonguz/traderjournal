package traderjournal.gwt.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.model.DBUtils;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TradeServiceImpl extends RemoteServiceServlet implements
		TradeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TradeServiceImpl() {
		 DBUtils.setupHBM();
	}
	
	@Override
	public Trade[] findAll() {
		StatelessSession ses = DBUtils.getSessionFactory().openStatelessSession();
		Transaction tx = ses.beginTransaction();

		Query query= ses.createQuery(
				"from " + "traderjournal.model.hibernate.Trade");
		
		//ses.beginTransaction();
		List<Trade> li = (List<Trade>) query.list();
		tx.commit();
		ses.close();
//		TradeHome th = new TradeHome();
//		
//		List<Trade> li = th.findAll();
		if(li != null)
			return li.toArray(new Trade[li.size()]);
		else
			return null;
	}

}
