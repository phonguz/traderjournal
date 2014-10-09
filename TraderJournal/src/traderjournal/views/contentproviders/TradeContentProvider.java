package traderjournal.views.contentproviders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.Filter;
import traderjournal.model.FilterOperator;
import traderjournal.model.RequestFactoryUtilsJpa;
import traderjournal.model.entities.Account;
import traderjournal.model.entities.Trade;

public class TradeContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		Account ac = (Account)inputElement;
		
		
		Filter f = new Filter("o.account.id",FilterOperator.EQUAL,ac.getId());
		List<Filter> fl = new ArrayList<Filter>();
		fl.add(f);
		List <Trade> tradeList  =   RequestFactoryUtilsJpa.findList(Trade.class, fl,0l,100l);
		
				//session.createCriteria(Trade.class).createCriteria("account").add(Restrictions.eq("id",accid )).list();
		
		
		
		
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
