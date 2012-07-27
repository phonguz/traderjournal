package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.RequestFactoryUtilsJpa;
import traderjournal.model.entities.Trade;

public class TradeListContentProvider implements IStructuredContentProvider {

	
	public Object[] getElements(Object inputElement) {

		
		
		List<Trade> l = RequestFactoryUtilsJpa.findAll(Trade.class);

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
