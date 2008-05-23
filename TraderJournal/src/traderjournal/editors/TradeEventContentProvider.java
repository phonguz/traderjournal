package traderjournal.editors;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.Trade;
import traderjournal.model.TradeEvent;

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
		List<TradeEvent> l = t.getAllEvents();

		return l.toArray();
		
	}

}
