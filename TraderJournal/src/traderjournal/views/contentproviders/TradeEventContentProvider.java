package traderjournal.views.contentproviders;

import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;
import traderjournal.model.hibernate.Tradeevent;

public class TradeEventContentProvider implements IStructuredContentProvider {

	Trade trade;
	
	public TradeEventContentProvider(Trade t){
		trade =t;
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}


	@Override
	public Object[] getElements(Object inputElement) {
		Trade t =  (Trade)inputElement;
		TradeHome th = new TradeHome();
		th.getSessionFactory().getCurrentSession().beginTransaction();
		th.getSessionFactory().getCurrentSession().refresh(t);
		
		
		Set<Tradeevent> l = t.getTradeevents();
		
		
		Object [] ret = l.toArray();
		th.getSessionFactory().getCurrentSession().getTransaction().commit();
		return ret;
		
	}

}
