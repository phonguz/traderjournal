package traderjournal.views;


import java.text.NumberFormat;
import java.text.ParseException;
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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.InstrumentHome;
import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;
import traderjournal.model.hibernate.Trader;
import traderjournal.model.hibernate.TraderHome;
import traderjournal.views.labelproviders.LabelUtils;
import traderjournal.views.verify.DoubleVerifyListener;
import traderjournal.views.verify.IntegerVerifyListener;
import org.eclipse.jface.viewers.ComboViewer;
import org.hibernate.Transaction;

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
	
	
	private Trade trade = new Trade();  //  @jve:decl-index=0:
	private TradeHome th = new TradeHome();  //  @jve:decl-index=0:
	Composite composite1;
	
	SuggestModifyListener mySugModifyListener = new SuggestModifyListener();
    
	/**
	 * 
	 */
	public TradeDetailView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	private class SuggestModifyListener implements ModifyListener{

		@Override
		public void modifyText(ModifyEvent e) {
			double sl =0;
			double open = 0;
			double acbalance =0;
			double acrisk = 0;
			double rpp = 1;
			try{
				sl = getDoubleFromtTextBox(txtSL);
				open = getDoubleFromtTextBox(txtOpenPrice);
				acbalance = getDoubleFromtTextBox(txtAccountBalance);
				acrisk = getDoubleFromtTextBox(txtAccountPercentRisk);
				rpp = ((Instrument)cmbIns.getData( cmbIns.getItem(cmbIns.getSelectionIndex()))).getValuePerPoint();
				double sugqty = calcSugQTY(sl, open, acbalance, acrisk,rpp);
				
			}catch(Exception ee){
				ee.printStackTrace();
				
			}
			
			
			
		}
		
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
			
					cmbIns = new Combo(composite1,SWT.NONE);
					cmbIns.setToolTipText("DoubleClick me to add new Instruments");
					comboViewer = new ComboViewer(cmbIns);
					cmbIns.addListener(SWT.MouseDoubleClick, new Listener(){

						@Override
						public void handleEvent(Event event) {
							//do nothing for now
							
							
							
						}});
					


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
					//txtQty.setText("" + trade.getTp());
					
					txtQty.addVerifyListener(new DoubleVerifyListener());
				}

				{
					lblSL = new Label(composite1, SWT.NONE);
					lblSL.setText("SL");

				}
				{
					txtSL = new Text(composite1, SWT.NONE);
					txtSL.addVerifyListener(new DoubleVerifyListener());
					txtSL.addModifyListener( mySugModifyListener);
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
					txtAccountBalance.addVerifyListener(new DoubleVerifyListener());
					txtAccountBalance.addModifyListener( mySugModifyListener);
					
				}	
				
				{
					lblAccountPercentRisk = new Label(composite1, SWT.NONE);
					lblAccountPercentRisk.setText("Acc % Risk");

					txtAccountPercentRisk = new Text(composite1, SWT.NONE);
					txtAccountPercentRisk.addVerifyListener(new DoubleVerifyListener());
					txtAccountPercentRisk.addModifyListener( mySugModifyListener);
					
				}
				{
					lblSugTradeSize = new Label(composite1, SWT.NONE);
					lblSugTradeSize.setText("Sug QTY");

					txtSugTradeSize = new Text(composite1, SWT.NONE);
					txtSugTradeSize.addVerifyListener(new DoubleVerifyListener());
					
				}
				
				{
					lblSugTradeValue = new Label(composite1, SWT.NONE);
					lblSugTradeValue.setText("Sug QTY");

					txtSugTradeValue = new Text(composite1, SWT.NONE);
					txtSugTradeValue.addVerifyListener(new DoubleVerifyListener());
					
				}
				

				{
					btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
					btnSave.setText("Save");

					btnSave.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent evt) {

							trade
									.setOpenprice(getDoubleOrZeroFromtTextBox(txtOpenPrice));

							trade
									.setCloseprice(getDoubleOrZeroFromtTextBox(txtClose));
							trade.setStoploss(getDoubleOrZeroFromtTextBox(txtSL));
							trade.setTp(getDoubleOrZeroFromtTextBox(txtTP));
							trade.setQty(getDoubleOrZeroFromtTextBox(txtQty));
							
							trade.setInstrument((Instrument)cmbIns.getData(cmbIns.getItem(cmbIns.getSelectionIndex())));
							
							try {
								trade.setOpenTradeDate(LabelUtils.getDateFormat().parse(txtOpenDate
										.getText()));
								trade.setCloseTradeDate(LabelUtils.getDateFormat().parse(txtCloseDate
										.getText()));
							} catch (ParseException e) {

								// skip unparsable dates
							}
							trade.setReference(txtReference.getText());
							trade
									.setCarrycost(getDoubleOrZeroFromtTextBox(txtCarryCost));
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
							th.getSessionFactory().getCurrentSession().refresh(trade.getInstrument());
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
							TraderHome traderHome = new TraderHome();
							Trader trader = traderHome.findAll().get(0);
							t.setTrader(trader);
							org.hibernate.Transaction tx = th.getSessionFactory().getCurrentSession()
							.beginTransaction();
							try{
							th.getSessionFactory().getCurrentSession().refresh(trader);
							th.getSessionFactory().getCurrentSession().refresh(
									ac);
							th.persist(t);
							tx.commit();
							setSelection(new TradeStructerdSelection("add"));
							
							}catch(Exception ex){
								tx.rollback();
							}finally{
								
							}

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

	private Double getDoubleFromtTextBox(Text box) throws NumberFormatException{
			String txt = box.getText();
			txt = txt.replaceAll(",", "");
			return Double.parseDouble(txt);
	}
	
	private Double getDoubleOrZeroFromtTextBox(Text box) {
		try{
		return Double.parseDouble(box.getText());
		}catch(NumberFormatException ne){}
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
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection)
				.getFirstElement() instanceof Trade) {
			
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
				
				if (trade.getAccount() != null ){
					Transaction tx = th.getSessionFactory().getCurrentSession().beginTransaction();
					th.getSessionFactory().getCurrentSession().refresh(trade);
					txtAccountBalance.setText("" + LabelUtils.priceFormat.format(trade.getAccount().getBalance()));
					tx.commit();
				}else
					txtAccountBalance.setText("");
				
				if (trade.getAccount() != null  ){
					Transaction tx1 = th.getSessionFactory().getCurrentSession().beginTransaction();
				th.getSessionFactory().getCurrentSession().refresh(trade);
					txtAccountPercentRisk.setText("" + trade.getAccount().getPercentRisk());
					tx1.commit();
				}else
					txtAccountPercentRisk.setText("");
				

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
				
				
				cmbIns.removeAll();
				InstrumentHome ih = new InstrumentHome();
				List<Instrument> li = ih.findAll();
				
				i=0;
				int selectedInstrumentID = 0;
				for(Instrument ins : li){
					cmbIns.add(ins.getName());
					cmbIns.setData(ins.getName(), ins);
					
					if(trade.getInstrument()!=null && trade.getInstrument().getId() == i)
						selectedInstrumentID = i;
					i++;
					
				}
				cmbIns.select(selectedInstrumentID);
				
				

			}

		}
	}
	
	public double calcSugQTY(double sl,  double open, double acbalance, double acrisk, double rpp){
		
		double ret = 0;
		double valrisk = acbalance * acrisk /100;
		double riskpts = (open - sl) * rpp;
		if(riskpts < 0) {//short
			ret = valrisk / -riskpts;
		}else{ //long
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
