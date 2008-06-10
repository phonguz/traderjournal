package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

public class TradeListContentProvider implements IStructuredContentProvider {

	
	public Object[] getElements(Object inputElement) {
		TradeHome th = new TradeHome();
		
		
		List<Trade> l = th.findAll();

		return l.toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}



}
