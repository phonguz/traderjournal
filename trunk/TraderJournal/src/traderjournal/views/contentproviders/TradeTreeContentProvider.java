package traderjournal.views.contentproviders;

import java.util.List;
import java.util.Set;

import javax.transaction.Transaction;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.hibernate.classic.Session;

import traderjournal.model.DBUtils;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

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
			Session ses= DBUtils.getSessionFactory().getCurrentSession();
			org.hibernate.Transaction tx = ses.beginTransaction();
			ses.refresh(ac);
			
			Set<Trade> li = ac.getTrades();
			Trade [] ret = (Trade []) li.toArray(new Trade[li.size()]);
			tx.commit();
			return ret;
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof Trade){
			Trade tr = (Trade)element;
			Session ses= DBUtils.getSessionFactory().getCurrentSession();
			org.hibernate.Transaction tx = ses.beginTransaction();
			ses.refresh(tr);
			Account ac =tr.getAccount();
			tx.commit();
			return  ac;
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element  instanceof Account) {
			Account ac = (Account)element;
			Session ses= DBUtils.getSessionFactory().getCurrentSession();
			org.hibernate.Transaction tx = ses.beginTransaction();
			ses.refresh(ac);
			Set<Trade> li = ac.getTrades();
			int size = li.size(); 
			tx.commit();
			if(size > 0)
				return true;
			else return false;
		
		}
		return false;
	}

}
