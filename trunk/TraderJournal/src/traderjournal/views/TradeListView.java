package traderjournal.views;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import traderjournal.editors.TradeEditor;
import traderjournal.editors.TradeEditorInput;
import traderjournal.model.ITradeListChanged;
import traderjournal.model.Trade;
import traderjournal.model.TradeContentProvider;
import traderjournal.model.TradeLabelProvider;


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

public class TradeListView extends ViewPart implements ITradeListChanged {
	public final static String ID = "traderjournal.views.TradeListView";
	private TableViewer viewer;

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
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TradeContentProvider());
		viewer.setLabelProvider(new TradeLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		Trade.addTradeListChangeListener(this);
	
		hookDoubleClickAction();
		contributeActions();
		getSite().setSelectionProvider(viewer);
		
		
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

	@Override
	public void tradeListChanged() {
		refreshData();
		
	}
	
}