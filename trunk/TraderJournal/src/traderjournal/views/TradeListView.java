package traderjournal.views;


import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import traderjournal.editors.TradeEditor;
import traderjournal.editors.TradeEditorInput;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.model.hibernate.Trade;
import traderjournal.views.TradeDetailView.TradeStructerdSelection;
import traderjournal.views.contentproviders.TradeTreeContentProvider;
import traderjournal.views.labelproviders.TradeTreeLabelProvider;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class TradeListView extends ViewPart  implements ISelectionListener{
	public final static String ID = "traderjournal.views.TradeListView";
	private TreeViewer viewer;

	private Action doubleClickAction;


	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public TradeListView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(TradeDetailView.ID_VIEW,
				(ISelectionListener) this);
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TradeTreeContentProvider());
		viewer.setLabelProvider(new TradeTreeLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getAllAccounts());
		
	
		hookDoubleClickAction();
		contributeActions();
		getSite().setSelectionProvider(viewer);

		
	}

	
	private List<Account> getAllAccounts() {
		AccountHome ach = new AccountHome();
		return ach.findAll();
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

				Trade trade = (Trade) obj;
				TradeEditorInput input = new TradeEditorInput(trade);
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				try {
					page.openEditor(input, TradeEditor.ID);
					// notify any listeners of the view with the actual data of
					// the view
					viewer.setSelection(viewer.getSelection());
				} catch (PartInitException e) {
					System.out.println(e.getMessage());
				}
			}
		};
	}

	private ISelection curSel =null;


	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		ISelection sel = viewer.getSelection();
		curSel = sel;
		TradeStructerdSelection tr = (TradeStructerdSelection)selection;
		if(tr.getAction().equals("add") || tr.getAction().equals("remove"))
			viewer.refresh();
		else{
		Trade tra = (Trade)tr.getFirstElement();
		viewer.refresh(tra);
		}
		viewer.setSelection(viewer.getSelection());
		
		//viewer.refresh();
		
	}

//	@Override
//	public void tradeListChanged() {
//		refreshData();
//		
//	}
	
}