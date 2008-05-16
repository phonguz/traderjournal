package tradetrack.editors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
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
import tradetrack.model.TradeEventType;
import tradetrack.views.TradeListView;

public class TradeEditor extends EditorPart implements ISelectionListener {
	public static final String ID = "TradeTrack.editors.TradeEditor";
	private boolean changed = false;
	private Trade trade;
	private TableColumn tblColEventDate;

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
		getSite().setSelectionProvider(tblViewer);

		

		rightComposite = new Composite(parent, SWT.BORDER);
		// Create a composite to hold the children
		GridData gridData = new GridData( GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL);
		parent.setLayout(new GridLayout());
		

		// Set numColumns to 3 for the buttons
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 4;
		rightComposite.setLayout(layout);
		parent.setLayoutData(gridData);
		rightComposite.setLayoutData(gridData);
		
		createTradeEventPart();
		fillTradeEventPart();

		

	}

	private void fillTradeEventPart() {

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
				final TradeEvent selectedEvent = (TradeEvent) item.getData();
				if (item == null)
					return;

				switch (col) {
				case 0:
					return;

				case 1: // date

					Text newEditor = new Text(tblEvents, SWT.NONE);
					newEditor.setText(item.getText(EDITABLECOLUMN));
					newEditor.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent me) {
							Text text = (Text) editor.getEditor();
							editor.getItem().setText(EDITABLECOLUMN,
									text.getText());
							TableItem item = (TableItem) editor.getItem();
							TradeEvent tEvent = (TradeEvent) item.getData();
							SimpleDateFormat sd = new SimpleDateFormat(
									"yyyy-MM-dd");
							try {
								tEvent.setEventDate(sd.parse(text.getText()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							tEvent.update();
							tblViewer.update(tEvent, null);

						}
					});
					newEditor.selectAll();
					newEditor.setFocus();
					editor.setEditor(newEditor, item, EDITABLECOLUMN);
					return;
				case 2: // type
					Combo tradeTypeEditor = new Combo(tblEvents, SWT.NONE);
					for (TradeEventType tt : TradeEventType.getAll()) {
						tradeTypeEditor.add(tt.getName(), tt.getID());
					}

					tradeTypeEditor.select(selectedEvent.getEventtype());
					tradeTypeEditor
							.addSelectionListener(new SelectionListener() {

								@Override
								public void widgetDefaultSelected(
										SelectionEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void widgetSelected(SelectionEvent ee) {
									Combo cmb = (Combo) ee.widget;
									TableItem item = (TableItem) editor
											.getItem();
									TradeEvent tEvent = (TradeEvent) item
											.getData();
									tEvent
											.setEventtype(cmb
													.getSelectionIndex());
									tEvent.update();
									tblViewer.update(tEvent, null);

								}
							});

					tradeTypeEditor.setFocus();
					editor.setEditor(tradeTypeEditor, item, EDITABLECOLUMN);

					return;

				case 3: // description

					Text descriptionEditor = new Text(tblEvents, SWT.NONE);
					descriptionEditor.setText(item.getText(EDITABLECOLUMN));
					descriptionEditor.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent me) {
							Text text = (Text) editor.getEditor();
							editor.getItem().setText(EDITABLECOLUMN,
									text.getText());
							TableItem item = (TableItem) editor.getItem();
							TradeEvent tEvent = (TradeEvent) item.getData();
							tEvent.setDescription(text.getText());
							tEvent.update();
							tblViewer.update(tEvent, null);

						}
					});
					descriptionEditor.selectAll();
					descriptionEditor.setFocus();
					editor.setEditor(descriptionEditor, item, EDITABLECOLUMN);
					return;

				case 4: // eventorder

					Text orderEditor = new Text(tblEvents, SWT.NONE);
					orderEditor.addVerifyListener(

					new VerifyListener() {
						public void verifyText(VerifyEvent e) {
							// Here, we could use a RegExp such as the following
							// if using JRE1.4 such as e.doit =
							// e.text.matches("[\\-0-9]*");
							e.doit = "0123456789".indexOf(e.text) >= 0;
						}
					});
					orderEditor.setText(item.getText(EDITABLECOLUMN));
					orderEditor.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent me) {
							Text text = (Text) editor.getEditor();
							editor.getItem().setText(EDITABLECOLUMN,
									text.getText());
							TableItem item = (TableItem) editor.getItem();
							TradeEvent tEvent = (TradeEvent) item.getData();
							tEvent.setEventorder(Integer.parseInt(text
									.getText()));
							tEvent.update();
							tblViewer.update(tEvent, null);

						}
					});
					orderEditor.selectAll();
					orderEditor.setFocus();
					editor.setEditor(orderEditor, item, EDITABLECOLUMN);
					return;
				case 5:
					String txt = item.getText(5);
					if (item.getText(5) != null && !item.getText(5).equals("")) {

						FileDialog fd = new FileDialog(new Shell());
						String filepath = fd.open();

						if (filepath != null) {
							selectedEvent.addImage(filepath);

						}

					} else {
					}

					break;

				case 6:
					TradeEvent te = (TradeEvent) item.getData();
					TradeEvent.remove(te.getID());
					refresh();
					return;
				}

			}
		});
		refresh();

	}

	private void createTradeEventPart() {

		{

			// composite1.setBounds(3,30, 500, 400);
			{
				int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
						| SWT.V_SCROLL | SWT.FULL_SELECTION
						| SWT.HIDE_SELECTION;
				tblEvents = new Table(rightComposite, style);

				tblViewer = new TableViewer(tblEvents);
				tblViewer.setContentProvider(new TradeEventContentProvider(
						trade));
				tblViewer.setLabelProvider(new TradeEventLabelProvider());

				tblEvents.setLinesVisible(true);
				tblEvents.setHeaderVisible(true);
				GridData gridData = new GridData(GridData.FILL_BOTH
						| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
				gridData.grabExcessVerticalSpace = true;
				gridData.horizontalSpan = 3;
				tblEvents.setLayoutData(gridData);

				{
					tblColID = new TableColumn(tblEvents, SWT.NONE);
					tblColID.setText("ID");
					tblColID.setWidth(20);
					tblColID.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {
							tblViewer.setSorter(new TradeEventSorter(
									TradeEventSorter.ID));
						}
					});
				}
				{
					tblColEventDate = new TableColumn(tblEvents, SWT.NONE);
					tblColEventDate.setText("Date");
					tblColEventDate.setWidth(60);
					tblColEventDate
							.addSelectionListener(new SelectionAdapter() {

								public void widgetSelected(SelectionEvent e) {
									tblViewer.setSorter(new TradeEventSorter(
											TradeEventSorter.EVENTDATE));
								}
							});
				}
				{
					tblColEventType = new TableColumn(tblEvents, SWT.NONE);
					tblColEventType.setText("EventType");
					tblColEventType.setWidth(60);
				}
				{
					tblColDescription = new TableColumn(tblEvents, SWT.NONE);
					tblColDescription.setText("Description");
					tblColDescription.setWidth(400);

				}
				{
					tblColOrder = new TableColumn(tblEvents, SWT.NONE);
					tblColOrder.setText("Order");
					tblColOrder.setWidth(60);
					tblColOrder.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {
							tblViewer.setSorter(new TradeEventSorter(
									TradeEventSorter.EVENTORDER));
						}
					});

				}
				{
					tblColImg1 = new TableColumn(tblEvents, SWT.NONE);
					tblColImg1.setText("IMG1");
					tblColImg1.setWidth(60);
				}

				{
					tblColRemove = new TableColumn(tblEvents, SWT.NONE);
					tblColRemove.setText("Remove");
					tblColRemove.setWidth(30);
				}
			}

		}
		tblViewer.setSorter(new TradeEventSorter(TradeEventSorter.EVENTORDER));
		tblEvents.setSortColumn(tblColOrder);

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

		Button btnSave = new Button(rightComposite, SWT.PUSH);
		btnAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				trade.update();

				refresh();
			}
		});

		btnSave.setText("Save");

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
			//tblEvents.getColumn(i).pack();
		}
		tblEvents.redraw();
		rightComposite.pack();
	}

	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}

}
