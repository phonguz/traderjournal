package traderjournal.views;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import traderjournal.editors.TradeEditor;
import traderjournal.model.ITradeListChanged;
import traderjournal.views.contentproviders.TradeListContentProvider;
import traderjournal.views.labelproviders.TradeListLabelProvider;


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

public class TradeReportListView extends ViewPart implements ITradeListChanged {
	public final static String ID = "traderjournal.views.TradeReportListView";
	private TableViewer tradeviewer;

	private Action doubleClickAction;


	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public TradeReportListView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		tradeviewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		tradeviewer.setContentProvider(new TradeListContentProvider());
		tradeviewer.setLabelProvider(new TradeListLabelProvider("*"));
		tradeviewer.setSorter(new NameSorter());
		tradeviewer.setInput(getViewSite());
		
	
		hookDoubleClickAction();
		contributeActions();
		getSite().setSelectionProvider(tradeviewer);
		
		
	}

	
	public void refreshData(){
		tradeviewer.refresh();
	}
	
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tradeviewer.getControl().setFocus();
	}
	
	
	private void hookDoubleClickAction() {

		tradeviewer.addDoubleClickListener(new IDoubleClickListener() {


	
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
				ISelection selection = tradeviewer.getSelection();
				IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();

				try {
					page.showView( TradeEditor.ID);
				} catch (PartInitException e) {
				
					e.printStackTrace();
				}
				tradeviewer.setSelection(tradeviewer.getSelection());
			}
		};
	}

	@Override
	public void tradeListChanged() {
		refreshData();
		
	}
	
}