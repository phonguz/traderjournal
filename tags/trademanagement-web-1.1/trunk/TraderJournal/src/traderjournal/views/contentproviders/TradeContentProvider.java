package traderjournal.views.contentproviders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

public class TradeContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		Account ac = (Account)inputElement;
		TradeHome th = new TradeHome();
	
		Session session = th.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Integer accid =  new Integer(ac.getId());
		List <Trade> tradeList  = session.createCriteria(Trade.class).createCriteria("account").add(Restrictions.eq("id",accid )).list();
		
		
		
		session.getTransaction().commit();
		if (tradeList != null && tradeList.size() >0)
			return tradeList.toArray();
		else{
			List<Trade> li = new ArrayList<Trade>();
			return li.toArray();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}


}
