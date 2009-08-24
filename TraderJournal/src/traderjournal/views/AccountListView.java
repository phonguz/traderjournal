package traderjournal.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Account;
import traderjournal.views.contentproviders.AccountContentProvider;
import traderjournal.views.labelproviders.AccountLabelProvider;

public class AccountListView extends ViewPart implements ISelectionListener{
	class NameSorter extends ViewerSorter {
	}
	public static final String ID = "traderjournal.views.AccountListView";
	private TableViewer viewer;
	private Action doubleClickAction;
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new AccountContentProvider());
		viewer.setLabelProvider(new AccountLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		
	
		hookDoubleClickAction();
		contributeActions();
		getSite().setSelectionProvider(viewer);
		getSite().getPage().addSelectionListener(AccountDetailView.ID,
				(ISelectionListener) this);
		
	}

	
	public void refreshData(){
		viewer.refresh();
	}
	
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	private void hookDoubleClickAction() {

		viewer.addDoubleClickListener(new IDoubleClickListener() {


	
			@Override
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
				
			}

		});

	}

	// New

	private void contributeActions() {

		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();

				Account acc = (Account) obj;
				
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					page.showView(TradeTableView.ID);
					

					viewer.setSelection(viewer.getSelection());
				} catch (PartInitException e) {
					System.out.println(e.getMessage());
				}
			}
		};
	}


	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		Account  newAcc = (Account) ((IStructuredSelection) selection)
		.getFirstElement();
		refreshData();
		
	}



}
