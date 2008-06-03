package traderjournal.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.model.hibernate.TradeHome;
import traderjournal.views.AccountDetailView.AccountStructerdSelection;
import traderjournal.views.verify.DoubleVerifyListener;
import traderjournal.views.verify.IntegerVerifyListener;

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
public class TradeDetailView extends ViewPart implements ISelectionListener,
		ISelectionProvider {
	public static final String ID_VIEW = "traderjournal.views.TradeDetailView"; //$NON-NLS-1$
	private Label labelIns;
	private Text txtIns;

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
	private CDateTime dateTime;
	private Trade trade = new Trade();
	private TradeHome th = new TradeHome();
	Composite composite1;

	/**
	 * 
	 */
	public TradeDetailView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
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

					txtIns = new Text(composite1, SWT.NONE);

				}
				{
					label4 = new Label(composite1, SWT.NONE);
					label4.setText("ODate");

					txtOpenDate = new Text(composite1, SWT.NONE);

					Label l2 = new Label(composite1, SWT.NONE);
					l2.setText("ODate2");
					dateTime = new CDateTime(composite1, CDT.BORDER
							| CDT.SPINNER );
					dateTime.setPattern("yyyy-MM-dd HH:mm");
					
				}
				{
					lblOpen = new Label(composite1, SWT.NONE);
					lblOpen.setText("Open");

					txtOpenPrice = new Text(composite1, SWT.NONE);
					txtOpenPrice.addVerifyListener(new DoubleVerifyListener());

				}
				{
					label3 = new Label(composite1, SWT.NONE);
					label3.setText("QTY");
					label3.setBounds(5, 88, 28, 11);
				}
				{
					txtQty = new Text(composite1, SWT.NONE);
					txtQty.setText("" + trade.getTp());
					txtQty.setBounds(39, 88, 70, 13);
					txtQty.addVerifyListener(new IntegerVerifyListener());
				}

				{
					lblSL = new Label(composite1, SWT.NONE);
					lblSL.setText("SL");

				}
				{
					txtSL = new Text(composite1, SWT.NONE);
					txtSL.addVerifyListener(new DoubleVerifyListener());
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
					btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnSave.setText("Save");

					btnSave.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {

							trade
									.setOpenprice(getDoubleFromtTextBox(txtOpenPrice));

							trade
									.setCloseprice(getDoubleFromtTextBox(txtClose));
							trade.setStoploss(getDoubleFromtTextBox(txtSL));
							trade.setTp(getDoubleFromtTextBox(txtTP));
							trade.setQty(getIntegerFromtTextBox(txtQty));
							trade.setInstrument(txtIns.getText());
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							try {
								trade.setOpenTradeDate(df.parse(txtOpenDate
										.getText()));
								trade.setCloseTradeDate(df.parse(txtCloseDate
										.getText()));
							} catch (ParseException e) {

								// skip unparsable dates
							}
							trade.setReference(txtReference.getText());
							trade
									.setCarrycost(getDoubleFromtTextBox(txtCarryCost));
							AccountHome ach = new AccountHome();
							List<Account> li = ach.findAll();
							for (Account ac : li) {
								if (ac.getName().equals(
										cmbAccounts.getItem(cmbAccounts
												.getSelectionIndex()))) {
									trade.setAccount(ac);
								}

							}

							th.getSessionFactory().getCurrentSession()
									.beginTransaction();
							th.getSessionFactory().getCurrentSession().refresh(
									trade.getAccount());
							th.attachDirty(trade);

							th.getSessionFactory().getCurrentSession()
									.getTransaction().commit();
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
							AccountHome ach = new AccountHome();
							Account ac = ach.findAll().get(0);
							t.setAccount(ac);

							th.getSessionFactory().getCurrentSession()
									.beginTransaction();
							th.getSessionFactory().getCurrentSession().refresh(
									ac);
							th.persist(t);
							th.getSessionFactory().getCurrentSession()
									.getTransaction().commit();
							setSelection(new TradeStructerdSelection("add"));

							;
						}
					});
				}
				{
					btnRemove = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnRemove.setText("Remove");

					btnRemove.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {
							th.getSessionFactory().getCurrentSession()
									.beginTransaction();
							th.delete(trade);
							th.getSessionFactory().getCurrentSession()
									.getTransaction().commit();
							setSelection(new TradeStructerdSelection("remove"));
						}
					});
				}

			}
		}

	}

	private Double getDoubleFromtTextBox(Text box) {
		try {
			return Double.parseDouble(box.getText());
		} catch (NumberFormatException ne) {
			// Leave as was;
		}
		return null;
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
		if (selection instanceof IStructuredSelection) {
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
				if (trade.getInstrument() != null)
					txtIns.setText(trade.getInstrument());
				else
					txtIns.setText("");
				if (trade.getReference() != null)
					txtReference.setText(trade.getReference());
				else
					txtReference.setText("");

				if (trade.getCarrycost() != null)
					txtCarryCost.setText("" + trade.getCarrycost());
				else
					txtCarryCost.setText("");

				cmbAccounts.clearSelection();
				cmbAccounts.removeAll();
				AccountHome achome = new AccountHome();
				List<Account> aclist = achome.findAll();
				int i = 0;
				int selectedAccountid = 0;
				for (Account ac : aclist) {
					cmbAccounts.add(ac.getName());
					if (trade.getAccount().getId() == i)
						selectedAccountid = i;
					i++;
				}
				cmbAccounts.select(selectedAccountid);

			}

		}
	}

	public void dispose() {
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}

	List<ISelectionChangedListener> listenerList = new ArrayList<ISelectionChangedListener>();

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
		
		public TradeStructerdSelection(String ac){
			action  = ac;
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
