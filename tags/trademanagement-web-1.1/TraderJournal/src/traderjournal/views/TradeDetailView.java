package traderjournal.views;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import traderjournal.event.TJEvent;
import traderjournal.event.TJEventTopics;
import traderjournal.model.RequestFactoryUtilsJpa;
import traderjournal.model.entities.Account;
import traderjournal.model.entities.Instrument;
import traderjournal.model.entities.Trade;
import traderjournal.views.labelproviders.LabelUtils;
import traderjournal.views.verify.DoubleVerifyListener;

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
public class TradeDetailView extends TJViewPart implements ISelectionListener,
		ISelectionProvider {
	public static final String ID_VIEW = "traderjournal.views.TradeDetailView"; //$NON-NLS-1$
	private Label labelIns;
	private Text txtIns;
	private Label labelIns2;
	private Combo cmbIns;

	private Label label5;
	private Label label4;
	private Label label3;
	private Text txtQty;
	private Button btnRemove;
	private Button btnAdd;
	private Label label2;
	private Text txtClose;
	private Text txtOpenDate;

	private Button btnSave;
	private Text txtCloseDate;
	private Text txtTP;
	private Label label1;
	private Text txtSL;
	private Label lblSL;
	private Label lblOpen;
	private Text txtOpenPrice;
	private Label lblReference;
	private Text txtReference;
	private Label lblCarryCost;
	private Text txtCarryCost;
	private Label lblAccount;
	private Combo cmbAccounts;
	private Label lblAccountBalance;
	private Text txtAccountBalance;
	private Label lblAccountPercentRisk;
	private Text txtAccountPercentRisk;

	private Label lblSugTradeValue;
	private Text txtSugTradeValue;

	private Label lblSugTradeSize;
	private Text txtSugTradeSize;

	private Trade trade = new Trade(); // @jve:decl-index=0:

	Composite composite1;

	SuggestModifyListener mySugModifyListener = new SuggestModifyListener();

	@Inject
	IEventBroker eventBroker  ;
	
	
	/**
	 * 
	 */
	public TradeDetailView() {
		super();
		// TODO Auto-generated constructor stub
	}

	private class SuggestModifyListener implements ModifyListener {

		@Override
		public void modifyText(ModifyEvent e) {
			double sl = 0;
			double open = 0;
			double acbalance = 0;
			double acrisk = 0;
			double rpp = 1;
			try {
				if (txtSL.getText().length() > 1)
					sl = getDoubleFromtTextBox(txtSL);
				if (txtOpenPrice.getText().length() > 1)
					open = getDoubleFromtTextBox(txtOpenPrice);
				if (txtAccountBalance.getText().length() > 1)
					acbalance = getDoubleFromtTextBox(txtAccountBalance);
				if (txtAccountPercentRisk.getText().length() > 1)
					acrisk = getDoubleFromtTextBox(txtAccountPercentRisk);
				if(cmbIns.getItemCount() > 1)
				rpp = ((Instrument) cmbIns.getData(cmbIns.getItem(cmbIns
						.getSelectionIndex()))).getValuePerPoint();
				double sugqty = calcSugQTY(sl, open, acbalance, acrisk, rpp);

			} catch (Exception ee) {
				ee.printStackTrace();

			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
		getSite().setSelectionProvider(this);
		{

			{

				parent.setLayout(new GridLayout());
				GridData gd = new GridData(GridData.FILL_BOTH
						| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
				gd.verticalSpan = 2;
				GridLayout gl = new GridLayout();
				gl.numColumns = 2;
				composite1 = new Composite(parent, SWT.NULL);
				composite1.setLayout(gl);
				parent.setLayoutData(new GridData(GridData.FILL_BOTH
						| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
				composite1.setLayoutData(gd);
				{
					labelIns = new Label(composite1, SWT.NONE);
					labelIns.setText("Instrument");

					cmbIns = new Combo(composite1, SWT.NONE);
					cmbIns.setToolTipText("DoubleClick me to add new Instruments");
					comboViewer = new ComboViewer(cmbIns);
					cmbIns.addListener(SWT.MouseDoubleClick, new Listener() {

						@Override
						public void handleEvent(Event event) {
							// do nothing for now

						}
					});

				}
				{
					label4 = new Label(composite1, SWT.NONE);
					label4.setText("ODate");

					txtOpenDate = new Text(composite1, SWT.NONE);

				}
				{
					lblOpen = new Label(composite1, SWT.NONE);
					lblOpen.setText("Open");

					txtOpenPrice = new Text(composite1, SWT.NONE);
					txtOpenPrice.addVerifyListener(new DoubleVerifyListener());
					txtOpenPrice.addModifyListener(mySugModifyListener);

				}
				{
					label3 = new Label(composite1, SWT.NONE);
					label3.setText("QTY");
					label3.setBounds(5, 88, 28, 11);
				}
				{
					txtQty = new Text(composite1, SWT.NONE);
					// txtQty.setText("" + trade.getTp());

					txtQty.addVerifyListener(new DoubleVerifyListener());
				}

				{
					lblSL = new Label(composite1, SWT.NONE);
					lblSL.setText("SL");

				}
				{
					txtSL = new Text(composite1, SWT.NONE);
					txtSL.addVerifyListener(new DoubleVerifyListener());
					txtSL.addModifyListener(mySugModifyListener);
				}
				{
					label1 = new Label(composite1, SWT.NONE);
					label1.setText("TP");

				}
				{
					txtTP = new Text(composite1, SWT.NONE);
					txtTP.addVerifyListener(new DoubleVerifyListener());
				}
				{
					label2 = new Label(composite1, SWT.NONE);
					label2.setText("Close");

				}
				{
					txtClose = new Text(composite1, SWT.NONE);
					txtClose.addVerifyListener(new DoubleVerifyListener());
				}

				{
					label5 = new Label(composite1, SWT.NONE);
					label5.setText("CDate");
					txtCloseDate = new Text(composite1, SWT.NONE);

				}
				{
					lblReference = new Label(composite1, SWT.NONE);
					lblReference.setText("Reference");
					txtReference = new Text(composite1, SWT.NONE);

				}
				{
					lblCarryCost = new Label(composite1, SWT.NONE);
					lblCarryCost.setText("Carry");
					txtCarryCost = new Text(composite1, SWT.NONE);
					txtCarryCost.addVerifyListener(new DoubleVerifyListener());

				}
				{
					lblAccount = new Label(composite1, SWT.NONE);
					lblAccount.setText("Account");

					cmbAccounts = new Combo(composite1, SWT.NONE);

				}

				{
					lblAccountBalance = new Label(composite1, SWT.NONE);
					lblAccountBalance.setText("AcctBalance");

					txtAccountBalance = new Text(composite1, SWT.NONE);
					txtAccountBalance
							.addVerifyListener(new DoubleVerifyListener());
					txtAccountBalance.addModifyListener(mySugModifyListener);

				}

				{
					lblAccountPercentRisk = new Label(composite1, SWT.NONE);
					lblAccountPercentRisk.setText("Acc % Risk");

					txtAccountPercentRisk = new Text(composite1, SWT.NONE);
					txtAccountPercentRisk
							.addVerifyListener(new DoubleVerifyListener());
					txtAccountPercentRisk
							.addModifyListener(mySugModifyListener);

				}
				{
					lblSugTradeSize = new Label(composite1, SWT.NONE);
					lblSugTradeSize.setText("Sug QTY");

					txtSugTradeSize = new Text(composite1, SWT.NONE);
					txtSugTradeSize
							.addVerifyListener(new DoubleVerifyListener());

				}

				{
					lblSugTradeValue = new Label(composite1, SWT.NONE);
					lblSugTradeValue.setText("Sug QTY");

					txtSugTradeValue = new Text(composite1, SWT.NONE);
					txtSugTradeValue
							.addVerifyListener(new DoubleVerifyListener());

				}

				{
					btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnSave.setText("Save");

					btnSave.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {

							trade.setOpenprice(getDoubleOrZeroFromtTextBox(txtOpenPrice));

							trade.setCloseprice(getDoubleOrZeroFromtTextBox(txtClose));
							trade.setStoploss(getDoubleOrZeroFromtTextBox(txtSL));
							trade.setTp(getDoubleOrZeroFromtTextBox(txtTP));
							trade.setQty(getDoubleOrZeroFromtTextBox(txtQty));

							trade.setInstrument((Instrument) cmbIns
									.getData(cmbIns.getItem(cmbIns
											.getSelectionIndex())));

							try {
								trade.setOpenTradeDate(LabelUtils
										.getDateFormat().parse(
												txtOpenDate.getText()));
								trade.setCloseTradeDate(LabelUtils
										.getDateFormat().parse(
												txtCloseDate.getText()));
							} catch (ParseException e) {

								// skip unparsable dates
							}
							trade.setReference(txtReference.getText());
							trade.setCarrycost(getDoubleOrZeroFromtTextBox(txtCarryCost));

							List<Account> li = Account.findAll();
							for (Account ac : li) {
								if (ac.getName().equals(
										cmbAccounts.getItem(cmbAccounts
												.getSelectionIndex()))) {
									trade.setAccount(ac);
								}

							}

							RequestFactoryUtilsJpa.persist(trade);

							setSelection(new TradeStructerdSelection("save"));
						}
					});
				}
				{
					btnAdd = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnAdd.setText("Add");

					btnAdd.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {

							Trade t = new Trade();

							t.setOpenTradeDate(new Date());

							Account ac = null;
							if (trade != null) {
								ac = trade.getAccount();
							} else {
								ac = Account.findAll().get(0);
							}
							t.setAccount(ac);

							RequestFactoryUtilsJpa.persist(t);

						}
					});
				}
				{
					btnRemove = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnRemove.setText("Remove");

					btnRemove.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							RequestFactoryUtilsJpa.remove(trade);

							setSelection(new TradeStructerdSelection("remove"));
							
							TJEvent tjevent = new TJEvent(TJEvent.EVENT_ACTION_REMOVE, trade);
							eventBroker.post(TJEventTopics.TOPIC_TRADE, tjevent.getMap());
						}
					});
				}

			}
		}

	}
	

	private Double getDoubleFromtTextBox(Text box) throws NumberFormatException {
		String txt = box.getText();
		txt = txt.replaceAll(",", "");
		return Double.parseDouble(txt);
	}

	private Double getDoubleOrZeroFromtTextBox(Text box) {
		try {
			return Double.parseDouble(box.getText());
		} catch (NumberFormatException ne) {
		}
		return 0.0;
	}

	private Integer getIntegerFromtTextBox(Text box) {
		try {
			return Integer.parseInt(box.getText());
		} catch (NumberFormatException ne) {
			// Leave as was;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPart#setFocus()
	 */
	public void setFocus() {

	}

	/**
	 * Cleans up all resources created by this ViewPart.
	 */

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection
				&& ((IStructuredSelection) selection).getFirstElement() instanceof Trade) {

			Trade newTrade = (Trade) ((IStructuredSelection) selection)
					.getFirstElement();
			if (newTrade != null) {
				trade = newTrade;
				if (trade.getOpenprice() != null)
					txtOpenPrice.setText(Double.toString(trade.getOpenprice()));
				else
					txtOpenPrice.setText("");
				if (trade.getCloseprice() != null)
					txtClose.setText("" + trade.getCloseprice());
				else
					txtClose.setText("");

				if (trade.getOpenTradeDate() != null)
					txtOpenDate.setText(trade.getOpenTradeDate().toString());
				else
					txtOpenDate.setText("");
				if (trade.getCloseTradeDate() != null)
					txtCloseDate.setText(trade.getCloseTradeDate().toString());
				else
					txtCloseDate.setText("");
				if (trade.getStoploss() != null)
					txtSL.setText("" + trade.getStoploss());
				else
					txtSL.setText("");
				if (trade.getTp() != null)
					txtTP.setText("" + trade.getTp());
				else
					txtTP.setText("");
				if (trade.getQty() != null)
					txtQty.setText("" + trade.getQty());
				else
					txtQty.setText("");

				if (trade.getReference() != null)
					txtReference.setText(trade.getReference());
				else
					txtReference.setText("");

				if (trade.getCarrycost() != null)
					txtCarryCost.setText("" + trade.getCarrycost());
				else
					txtCarryCost.setText("");

				if (trade.getAccount() != null) {

					txtAccountBalance.setText(""
							+ LabelUtils.priceFormat.format(trade.getAccount()
									.getBalance()));

				} else
					txtAccountBalance.setText("");

				if (trade.getAccount() != null) {

					txtAccountPercentRisk.setText(""
							+ trade.getAccount().getPercentRisk());

				} else
					txtAccountPercentRisk.setText("");

				cmbAccounts.clearSelection();
				cmbAccounts.removeAll();

				List<Account> aclist = Account.findAll();
				int i = 0;
				int selectedAccountid = 0;
				for (Account ac : aclist) {
					cmbAccounts.add(ac.getName());
					if (trade.getAccount().getId() == i)
						selectedAccountid = i;
					i++;
				}
				cmbAccounts.select(selectedAccountid);

				cmbIns.removeAll();

				List<Instrument> li = Instrument.findAll();
				Collections.sort(li);
				i = 0;

				for (Instrument ins : li) {
					cmbIns.add(ins.getName());
					cmbIns.setData(ins.getName(), ins);

				}
				if (trade != null) {

					if (trade.getInstrument() != null)
						cmbIns.select(cmbIns.indexOf(trade.getInstrument()
								.getName()));

				}
			}

		}
	}

	public double calcSugQTY(double sl, double open, double acbalance,
			double acrisk, double rpp) {

		double ret = 0;
		double valrisk = acbalance * acrisk / 100;
		double riskpts = (open - sl) * rpp;
		if (riskpts < 0) {// short
			ret = valrisk / -riskpts;
		} else { // long
			ret = valrisk / riskpts;

		}

		txtSugTradeSize.setText(ret + "");
		txtSugTradeValue.setText((ret * open) + "");

		return ret;

	}

	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}

	List<ISelectionChangedListener> listenerList = new ArrayList<ISelectionChangedListener>();
	private ComboViewer comboViewer = null;

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		listenerList.add(listener);

	}

	@Override
	public ISelection getSelection() {

		return new TradeStructerdSelection("save");
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (listenerList.contains(listener))
			listenerList.remove(listener);

	}

	@Override
	public void setSelection(ISelection selection) {
		for (ISelectionChangedListener ill : listenerList) {

			SelectionChangedEvent se = new SelectionChangedEvent(this,
					selection);
			ill.selectionChanged(se);
		}

	}

	public class TradeStructerdSelection implements IStructuredSelection {

		public String action;

		public TradeStructerdSelection(String ac) {
			action = ac;
		}

		@Override
		public Object getFirstElement() {
			// TODO Auto-generated method stub
			return trade;
		}

		@Override
		public Iterator iterator() {
			List<Trade> acl = new ArrayList<Trade>();
			acl.add(trade);
			return acl.iterator();
		}

		@Override
		public int size() {
			if (trade == null)
				return 0;
			else
				return 1;

		}

		@Override
		public Object[] toArray() {
			List<Trade> acl = new ArrayList<Trade>();
			acl.add(trade);
			return acl.toArray();
		}

		@Override
		public List toList() {
			List<Trade> acl = new ArrayList<Trade>();
			acl.add(trade);
			return acl;
		}

		@Override
		public boolean isEmpty() {
			if (trade == null)
				return true;
			else
				return false;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
	}

}
