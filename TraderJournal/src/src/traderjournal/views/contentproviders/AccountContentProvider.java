package traderjournal.views.contentproviders;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;

public class AccountContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		AccountHome ach = new AccountHome();
		
		List<Account> l = ach.findAll();

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
