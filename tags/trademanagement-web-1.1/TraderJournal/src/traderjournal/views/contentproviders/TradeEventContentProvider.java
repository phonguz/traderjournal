package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.entities.Trade;
import traderjournal.model.entities.Tradeevent;

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
		
		
		
		List<Tradeevent> l = t.getTradeevents();
		
		
		Object [] ret = l.toArray();
		
		return ret;
		
	}

}
