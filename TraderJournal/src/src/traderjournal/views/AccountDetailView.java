package traderjournal.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.model.hibernate.Ccy;
import traderjournal.model.hibernate.CcyHome;

public class AccountDetailView extends ViewPart implements ISelectionListener, ISelectionProvider{
	public static final String ID = "traderjournal.views.AccountDetailView";
	Account account;
	Label lblName = null;
	Text txtName = null;
	Composite composite1 = null;
	Combo cmbCCY = null;
	Label lblCCY = null;
	Label lblBalance = null;
	Text txtBalance = null;
	
	
	
	Button btnSave;
	Button btnAdd;
	Button btnRemove;
	AccountHome acHome = new AccountHome();
	
	@Override
	public void createPartControl(Composite parent) {
		getSite().setSelectionProvider(this);
		getSite().getPage().addSelectionListener(AccountListView.ID,
				(ISelectionListener) this);
		
		parent.setLayout(new GridLayout());
		GridData gd = new GridData (GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite1 = new Composite(parent, SWT.NULL);
		composite1.setLayout(gl);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		composite1.setLayoutData(gd);
		{
			lblName = new Label(composite1, SWT.NONE);
			lblName.setText("Name");
		
			txtName = new Text(composite1, SWT.NONE);
			
			lblCCY = new Label(composite1,SWT.NONE);
			lblCCY.setText("CCY");
			
			cmbCCY = new Combo(composite1,SWT.NONE);
			
			List<Ccy> listAll = new CcyHome().findAll();
			cmbCCY.setData(listAll);
			for(Ccy c : listAll){
				cmbCCY.add(c.getName(),c.getId());
			}
			cmbCCY.select(listAll.get(0).getId());
			
			
			lblBalance = new Label(composite1,SWT.NONE);
			lblBalance.setText("Balance");
			txtBalance = new Text(composite1,SWT.NONE);
			
			
		}
		{
			btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
			btnSave.setText("Save");
			
			btnSave.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					account.setName(txtName.getText());
					List<Ccy> list = (List<Ccy>)cmbCCY.getData();
					
					account.setCcy(list.get(cmbCCY.getSelectionIndex())  );
					account.setBalance(Double.parseDouble(txtBalance.getText()));
					acHome.getSessionFactory().getCurrentSession().beginTransaction();
					acHome.attachDirty(account);
					acHome.getSessionFactory().getCurrentSession().getTransaction().commit();
					setSelection(new AccountStructerdSelection());			
				}
			});
		}
		{
			btnAdd = new Button(composite1, SWT.PUSH | SWT.CENTER);
			btnAdd.setText("Add");
			
			btnAdd.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					
						Account ac = new Account();
						ac.setName("NewAccount");
						ac.setBalance(0);
						ac.setCcy(new CcyHome().findAll().get(0));
						
						int biggestID =0;
						for(Account acl:acHome.findAll()){
							if(acl.getId() > biggestID)
								biggestID = acl.getId();
						}
						ac.setId(biggestID +1);
						acHome.getSessionFactory().getCurrentSession().beginTransaction();	
						acHome.persist(ac);
						acHome.getSessionFactory().getCurrentSession().getTransaction().commit();	
						account = ac;
						setSelection(new AccountStructerdSelection());
						
					
					     		}
			});
		}
		{
			btnRemove = new Button(composite1, SWT.PUSH | SWT.CENTER);
			btnRemove.setText("Remove");
			
			btnRemove.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent evt) {
					acHome.getSessionFactory().getCurrentSession().beginTransaction();
					acHome.delete(account);
					acHome.getSessionFactory().getCurrentSession().getTransaction().commit();
					setSelection(new AccountStructerdSelection());
				}
			});
		}

		
		
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void dispose(){
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		Account  newAcc = (Account) ((IStructuredSelection) selection)
		.getFirstElement();
		if(newAcc != null){
			account = newAcc;
			if(account.getName() != null )
				txtName.setText(account.getName());
				
				cmbCCY.select(account.getCcy().getId());
				txtBalance.setText(Double.toString(account.getBalance()));
	
		}
		
		
		
	}
	
	List<ISelectionChangedListener> il = new ArrayList<ISelectionChangedListener>();
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		il.add(listener);
		
	}

	@Override
	public ISelection getSelection() {
		return new AccountStructerdSelection();		
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		il.remove(listener);
		
	}

	@Override
	public void setSelection(ISelection selection) {
		for(ISelectionChangedListener ill : il){

			SelectionChangedEvent se = new SelectionChangedEvent(this,selection);
			ill.selectionChanged(se);
		}
		
	}

	public class AccountStructerdSelection implements IStructuredSelection{
		
		
	
		@Override
		public Object getFirstElement() {
			// TODO Auto-generated method stub
			return account;
		}

		@Override
		public Iterator iterator() {
			List<Account> acl =new ArrayList<Account>();
			acl.add(account);
			return acl.iterator();
		}

		@Override
		public int size() {
			if(account == null)
				return 0;
			else
				return 1;
			
		}

		@Override
		public Object[] toArray() {
			List<Account> acl =new ArrayList<Account>();
			acl.add(account);
			return acl.toArray();
		}

		@Override
		public List toList() {
			List<Account> acl =new ArrayList<Account>();
			acl.add(account);
			return acl;
		}

		@Override
		public boolean isEmpty() {
			if(account == null)
				return true;
			else
				return false;	
		}		
	}
	
}
