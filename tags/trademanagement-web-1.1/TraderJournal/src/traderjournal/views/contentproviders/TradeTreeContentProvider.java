package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.entities.Account;
import traderjournal.model.entities.Trade;

public class TradeTreeContentProvider implements ITreeContentProvider {

	
	public Object[] getElements(Object inputElement) {
		List<Account> li = (List<Account>)inputElement;
		return li.toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement  instanceof Account) {
			Account ac = (Account)parentElement;
			
			
			List<Trade> li = ac.getTrades();
			Trade [] ret = (Trade []) li.toArray(new Trade[li.size()]);
		
			return ret;
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof Trade){
			Trade tr = (Trade)element;
			
			Account ac =tr.getAccount();
			
			return  ac;
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element  instanceof Account) {
			Account ac = (Account)element;
			
			List<Trade> li = ac.getTrades();
			int size = li.size(); 
			
			if(size > 0)
				return true;
			else return false;
		
		}
		return false;
	}

}
