package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.RequestFactoryUtilsJpa;
import traderjournal.model.entities.Account;

public class AccountContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		
		
		List<Account> l = RequestFactoryUtilsJpa.findAll(Account.class);

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
