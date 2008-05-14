package tradetrack.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import tradetrack.model.Trade;
import tradetrack.model.TradeEvent;
import tradetrack.views.TradeListView;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class TradeEditor extends EditorPart implements ISelectionListener,
		ISelectionProvider {
	public static final String ID = "TradeTrack.editors.TradeEditor";
	private boolean changed = false;
	private Trade trade;
	private TableColumn tblColEventDate;
	private Composite composite1;

	private TableColumn tblIMG3;
	private TableColumn tblColIMG2;
	private TableColumn tblColImg1;
	private TableColumn tblColOrder;
	private TableColumn tblColDescription;
	private TableColumn tblColEventType;
	private TableColumn tblColID;
	private TableColumn tblColRemove;

	private Table tblEvents;
	private TableViewer tblViewer;
	private int EDITABLECOLUMN;

	Composite rightComposite;

	public TradeEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		save();
	}

	private void save() {

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(input.getName());

	}

	@Override
	public boolean isDirty() {

		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {

		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		getSite().getPage().addSelectionListener(TradeListView.ID,
				(ISelectionListener) this);

		parent.setLayout(null);

		rightComposite = new Composite(parent, SWT.NONE);

		rightComposite.setLayout(null);
		rightComposite.setBounds(0, 4, 534, 501);

		createTradeEventPart();
		fillTradeEventPart();

		parent.pack();
		parent.setSize(543, 505);

	}

	private void fillTradeEventPart() {
		/*
		 * tblEvents.removeAll();
		 * 
		 * 
		 * 
		 * 
		 * List<TradeEvent> list = trade.getAllEvents(); for (int i = 0; i <
		 * list.size(); i++) { TradeEvent te = list.get(i); TableItem ti = new
		 * TableItem(tblEvents,SWT.BORDER); ti.setText(0, "" + te.getID());
		 * if(te.getEventDate() != null)
		 * ti.setText(1,te.getEventDate().toString());
		 * ti.setText(2,te.getTradeEventType().getName());
		 * if(te.getDescription() != null) ti.setText(3,te.getDescription());
		 * ti.setText(4,"" +te.getEventorder()); List<TradeEventImage> imglist =
		 * te.getAllImages();
		 * 
		 * 
		 * 
		 * if(imglist.size() > 0 && imglist.get(0) != null){ TradeEventImage ei =
		 * imglist.get(0); Image img = ei.getImage();
		 * 
		 * ti.setImage(5, new
		 * Image(img.getDevice(),img.getImageData().scaledTo(50,30))); }else{
		 * ti.setText(5,"upload"); }
		 * 
		 * if(imglist.size() > 1 && imglist.get(1) != null){ TradeEventImage ei2 =
		 * imglist.get(1);
		 * 
		 * 
		 * Image img = ei2.getImage();
		 * 
		 * ti.setImage(6, new
		 * Image(img.getDevice(),img.getImageData().scaledTo(50,30))); }else{
		 * ti.setText(6,"upload"); }
		 * 
		 * if(imglist.size() > 2 && imglist.get(2) != null){ TradeEventImage ei3 =
		 * imglist.get(2);
		 * 
		 * Image img = ei3.getImage();
		 * 
		 * ti.setImage(7, new
		 * Image(img.getDevice(),img.getImageData().scaledTo(50,30))); }else{
		 * ti.setText(7,"upload"); }
		 *  }
		 */
		/*
		 * 
		 * Button btnupload = new Button(composite,SWT.PUSH);
		 * btnupload.setText("Upload"); btnupload.setData(te);
		 * btnupload.addSelectionListener(new SelectionListener(){
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) { //
		 * TODO Auto-generated method stub
		 *  }
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * 
		 * FileDialog fileDialog = new FileDialog(new Shell());
		 * 
		 * String selected = fileDialog.open(); TradeEvent te =
		 * (TradeEvent)e.widget.getData(); te.addImage(selected); }
		 * 
		 * });
		 * 
		 * 
		 * 
		 * Button btnRemove = new Button(composite,SWT.PUSH);
		 * btnRemove.setText("Remove"); btnRemove.setData(new
		 * Integer(te.getID())); btnRemove.addSelectionListener(new
		 * SelectionListener(){
		 * 
		 * @Override public void widgetDefaultSelected(SelectionEvent e) { //
		 * TODO Auto-generated method stub
		 *  }
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) { Widget w =
		 * e.widget;
		 * 
		 * Integer id = (Integer) w.getData(); TradeEvent.remove(id.intValue());
		 * fillTradeEventPart(); }
		 * 
		 * });
		 */

		/*
		 * table.addSelectionListener(new SelectionAdapter() { public void
		 * widgetSelected(SelectionEvent e) { // Clean up any previous editor
		 * control Control oldEditor = editor.getEditor(); if (oldEditor !=
		 * null) oldEditor.dispose();
		 *  // Identify the selected row TableItem item = (TableItem)e.item; if
		 * (item == null) return;
		 *  // The control that will be the editor must be a child of the Table
		 * Text newEditor = new Text(table, SWT.NONE);
		 * newEditor.setText(item.getText(EDITABLECOLUMN));
		 * newEditor.addModifyListener(new ModifyListener() { public void
		 * modifyText(ModifyEvent me) { Text text = (Text)editor.getEditor();
		 * editor.getItem().setText(EDITABLECOLUMN, text.getText()); } });
		 * newEditor.selectAll(); newEditor.setFocus();
		 * editor.setEditor(newEditor, item, EDITABLECOLUMN); } });
		 */

		tblEvents.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = tblEvents.getClientArea();
				Point selectedPoint = new Point(event.x, event.y);
				int index = tblEvents.getTopIndex();
				while (index < tblEvents.getItemCount()) {
					boolean visible = false;
					TableItem item = tblEvents.getItem(index);
					for (int i = 0; i < tblEvents.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(selectedPoint)) {
							// System.out.println ("Item " + index + "-" + i);
							EDITABLECOLUMN = i;
						}
						if (!visible && rect.intersects(clientArea)) {
							visible = true;
						}
					}
					if (!visible)
						return;
					index++;
				}
			}
		});

		final TableEditor editor = new TableEditor(tblEvents);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		tblEvents.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int col = EDITABLECOLUMN;
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				if (item == null)
					return;

				switch (col) {
				case 8:
					TradeEvent te = (TradeEvent) item.getData();
					TradeEvent.remove(te.getID());
					break;
				}

				// The control that will be the editor must be a child of the
				// Table
				Text newEditor = new Text(tblEvents, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent me) {
						Text text = (Text) editor.getEditor();
						editor.getItem()
								.setText(EDITABLECOLUMN, text.getText());

					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor.setEditor(newEditor, item, EDITABLECOLUMN);
			}
		});
		refresh();
		
		/*tblColID.pack();
		tblColDescription.pack();
		tblColEventDate.pack();
		tblColEventType.pack();
		tblColImg1.pack();
		tblColIMG2.pack();
		tblIMG3.pack();
		tblEvents.pack();
		*/

	}

	private void createTradeEventPart() {
		Button btnAdd = new Button(rightComposite, SWT.PUSH);
		btnAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				trade.addTradeEvent();

				refresh();
			}
		});

		btnAdd.setText("Add");
		btnAdd.setBounds(3, 3, 57, 23);
		{
			GridData composite1LData = new GridData();
			composite1LData.verticalAlignment = GridData.FILL;
			composite1LData.horizontalAlignment = GridData.FILL;
			composite1LData.grabExcessHorizontalSpace = true;
			composite1LData.grabExcessVerticalSpace = true;
			
			composite1 = new Composite(rightComposite, SWT.BORDER);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			composite1.setLayout(composite1Layout);
			composite1.setLayoutData(composite1LData);
			composite1.setBounds(3,30, 500, 400);
			{
				
				tblViewer = new TableViewer(composite1, SWT.FULL_SELECTION | SWT.SINGLE | SWT.V_SCROLL);
				tblViewer.setContentProvider(new TradeEventContentProvider(
						trade));
	
	
				tblViewer.setLabelProvider(new TradeEventLabelProvider());
				

				
				
				
				tblEvents = tblViewer.getTable();
			
				tblEvents.setLinesVisible(true);
				tblEvents.setHeaderVisible(true);
				tblEvents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
				
				{
					tblColID = new TableColumn(tblEvents, SWT.NONE);
					tblColID.setText("ID");
					tblColID.setWidth(20);
				}
				{
					tblColEventDate = new TableColumn(tblEvents, SWT.NONE);
					tblColEventDate.setText("Date");
					tblColEventDate.setWidth(60);
				}
				{
					tblColEventType = new TableColumn(tblEvents, SWT.NONE);
					tblColEventType.setText("EventType");
					tblColEventType.setWidth(60);
				}
				{
					tblColDescription = new TableColumn(tblEvents, SWT.NONE);
					tblColDescription.setText("Description");
					tblColDescription.setWidth(120);
				}
				{
					tblColOrder = new TableColumn(tblEvents, SWT.NONE);
					tblColOrder.setText("Order");
					tblColOrder.setWidth(60);
				}
				{
					tblColImg1 = new TableColumn(tblEvents, SWT.NONE);
					tblColImg1.setText("IMG1");
					tblColImg1.setWidth(60);
				}
				{
					tblColIMG2 = new TableColumn(tblEvents, SWT.NONE);
					tblColIMG2.setText("IMG2");
					tblColIMG2.setWidth(60);
				}
				{
					tblIMG3 = new TableColumn(tblEvents, SWT.NONE);
					tblIMG3.setText("IMG3");
					tblIMG3.setWidth(60);
				}
				{
					tblColRemove = new TableColumn(tblEvents, SWT.NONE);
					tblColRemove.setText("Remove");
					tblColRemove.setWidth(30);
				}
			}
		}
		{

		}

		getSite().setSelectionProvider(tblViewer);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Trade newTrade = (Trade) ((IStructuredSelection) selection)
					.getFirstElement();
			trade = newTrade;

			tblViewer.setInput(trade);
			refresh();

		}
	}
	 private void refresh() {
		    tblViewer.refresh();
		    
		    for (int i = 0, n = tblEvents.getColumnCount(); i < n; i++) {
		    	tblEvents.getColumn(i).pack();
		    }
		    tblEvents.pack();
		    composite1.pack();
		  }
	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

}
