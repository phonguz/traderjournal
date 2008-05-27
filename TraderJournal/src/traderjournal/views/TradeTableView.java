package traderjournal.views;



import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Account;
import traderjournal.views.contentproviders.TradeContentProvider;
import traderjournal.views.labelproviders.TradeLabelProvider;
import traderjournal.views.sorters.TradeEventSorter;
import traderjournal.views.sorters.TradeSorter;

public class TradeTableView extends ViewPart implements ISelectionListener {
	public static final String ID = "traderjournal.views.TradeTableView";
	private Account account;
	private Table tblTrades;
	private TableViewer tblViewer;
	private Composite composite1;
	private TableColumn colID;
	public static final int COL_colID = 0;
	private TableColumn colInstrument;
	public static final int COL_colInstrument = 1;
	private TableColumn colOpenDate;
	public static final int COL_colOpenDate = 2;
	private TableColumn colOpenPrice;
	public static final int COL_colOpenPrice = 3;
	private TableColumn colCloseDate;
	public static final int COL_colCloseDate = 4;
	private TableColumn colClosePrice;
	public static final int COL_colClosePrice = 5;
	private TableColumn colSL;
	public static final int COL_colSL = 6;
	private TableColumn colTP;
	public static final int COL_colTP = 7;
	private TableColumn colReference;
	public static final int COL_colReference = 8;
	

	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(AccountListView.ID,
				(ISelectionListener) this);

		parent.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		GridLayout gl = new GridLayout();
		
		composite1 = new Composite(parent, SWT.NULL);
		composite1.setLayout(gl);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		composite1.setLayoutData(gd);
		
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		tblTrades = new Table(composite1, style);

		tblViewer = new TableViewer(tblTrades);
		tblViewer.setContentProvider(new TradeContentProvider());
		tblViewer.setLabelProvider(new TradeLabelProvider());

		tblTrades.setLinesVisible(true);
		tblTrades.setHeaderVisible(true);
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 1;
		tblTrades.setLayoutData(gridData);
		createCols();
		
		
	}
	
	private void createCols(){
		colID = new TableColumn(tblTrades, SWT.LEFT);
		colID.setText("ID");
		colID.setWidth(20);
		colID.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tblViewer.setSorter(new TradeSorter(
						TradeSorter.ID));
			}
		});
		
		colInstrument = new TableColumn(tblTrades, SWT.LEFT);
		colInstrument.setText("Instrument");
		colInstrument.setWidth(80);
		colInstrument.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tblViewer.setSorter(new TradeSorter(
						TradeSorter.INSTRUMENT));
			}
		});
		colOpenDate = new TableColumn(tblTrades, SWT.LEFT);
		colOpenDate.setText("ODate");
		colOpenDate.setWidth(80);
		colOpenDate.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tblViewer.setSorter(new TradeSorter(
						TradeSorter.OPENDATE));
			}
		});
		colOpenPrice = new TableColumn(tblTrades, SWT.LEFT);
		colOpenPrice.setText("OPrice");
		colOpenPrice.setWidth(60);
		
		colCloseDate = new TableColumn(tblTrades, SWT.LEFT);
		colCloseDate.setText("CDate");
		colCloseDate.setWidth(80);
		colCloseDate.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				tblViewer.setSorter(new TradeSorter(
						TradeSorter.CLOSEDATE));
			}
		});
		colClosePrice = new TableColumn(tblTrades, SWT.LEFT);
		colClosePrice.setText("CPrice");
		colClosePrice.setWidth(60);
		
		colSL = new TableColumn(tblTrades, SWT.LEFT);
		colSL.setText("SL");
		colSL.setWidth(60);
		
		colTP = new TableColumn(tblTrades, SWT.LEFT);
		colTP.setText("TP");
		colTP.setWidth(60);

		colReference = new TableColumn(tblTrades, SWT.LEFT);
		colReference.setText("Reference");
		colReference.setWidth(90);
		
		tblViewer.setSorter(new TradeSorter(TradeSorter.ID));
		tblTrades.setSortColumn(colID);
		refresh();
	}

	@Override
	public void setFocus() {

	}

	public void refresh(){
		tblViewer.refresh();
		tblTrades.redraw();
		for (int i = 0, n = tblTrades.getColumnCount(); i < n; i++) {
			tblTrades.getColumn(i).pack();
		}
		//composite1.pack();
		tblTrades.select(0);
		tblTrades.showSelection();
		
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		Account newAcc = (Account) ((IStructuredSelection) selection)
				.getFirstElement();
		if (newAcc != null) {
			account = newAcc;
			tblViewer.setInput(account);
			
			refresh();
		}

	}

}
