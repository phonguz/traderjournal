package traderjournal.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.model.hibernate.TradeHome;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class TradeDetailView extends ViewPart implements ISelectionListener {
    public static final String ID_VIEW =
        "traderjournal.views.TradeDetailView"; //$NON-NLS-1$
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

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
    	{
	    	
	    	{
	    		
	    		parent.setLayout(new GridLayout());
	    		GridData gd = new GridData (GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
	    		GridLayout gl = new GridLayout();
	    		gl.numColumns = 2;
	    		composite1 = new Composite(parent, SWT.NULL);
	    		composite1.setLayout(gl);
	    		parent.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
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
	    		
	    		}
	    		{
	    			lblOpen = new Label(composite1, SWT.NONE);
	    			lblOpen.setText("Open");

		    		txtOpenPrice = new Text(composite1, SWT.NONE);
		    			

	    			
	    			
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
	    		}

	    		{
	    			lblSL = new Label(composite1, SWT.NONE);
	    			lblSL.setText("SL");
	    			
	    		}
	    		{
	    			txtSL = new Text(composite1, SWT.NONE);
	    			
	    		}
	    		{
	    			label1 = new Label(composite1, SWT.NONE);
	    			label1.setText("TP");
	    		
	    		}
	    		{
	    			txtTP = new Text(composite1, SWT.NONE);
	    		
	    		}
	    		{
	    			label2 = new Label(composite1, SWT.NONE);
	    			label2.setText("Close");
	    		
	    		
	    		
	    		}
	    		{
	    			txtClose = new Text(composite1, SWT.NONE);
	    			
	    		}
	    		
	    		{
	    			label5 = new Label(composite1, SWT.NONE);
	    			label5.setText("CDate");
	    			txtCloseDate = new Text(composite1, SWT.NONE);
	    			
	    			
	    			
	    		
	    		}
	    		{
	    			lblReference = new Label(composite1,SWT.NONE);
	    			lblReference.setText("Reference");
	    			txtReference = new Text(composite1,SWT.NONE);
	    			
	    		}
	    		{
	    			lblCarryCost = new Label(composite1,SWT.NONE);
	    			lblCarryCost.setText("Carry");
	    			txtCarryCost = new Text(composite1,SWT.NONE);
	    			
	    		}
	    		{
	    			lblAccount = new Label(composite1,SWT.NONE);
	    			lblAccount.setText("Account");
	    			
	    			cmbAccounts = new Combo(composite1,SWT.NONE);
	    			
	    		}
	    		
	    		{
	    			btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
	    			btnSave.setText("Save");
	    			
	    			btnSave.addSelectionListener(new SelectionAdapter() {
	    				public void widgetSelected(SelectionEvent evt) {
	    					trade.setOpenprice(Double.parseDouble(txtOpenPrice.getText()));
	    					trade.setCloseprice(Double.parseDouble(txtClose.getText()));
	    					trade.setStoploss(Double.parseDouble(txtSL.getText()));
	    					trade.setTp(Double.parseDouble(txtTP.getText()));
	    					trade.setQty(Integer.parseInt(txtQty.getText()));
	    					trade.setInstrument(txtIns.getText());
	    					DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
	    					try {
	    						trade.setOpenTradeDate(df.parse(txtOpenDate.getText()) );
	    						trade.setCloseTradeDate(df.parse(txtCloseDate.getText()));
	    					} catch (ParseException e) {
	    						
	    						// skip unparsable dates
	    					}
	    					trade.setReference(txtReference.getText());
	    					trade.setCarrycost(Double.parseDouble(txtCarryCost.getText()));
	    					
	    					
	    					th.getSessionFactory().getCurrentSession().beginTransaction();
	    					th.attachDirty(trade);
	    					
	    					th.getSessionFactory().getCurrentSession().getTransaction().commit();
	    					
	    				}
	    			});
	    		}
	    		{
	    			btnAdd = new Button(composite1, SWT.PUSH | SWT.CENTER);
	    			btnAdd.setText("Add");
	    			
	    			btnAdd.addSelectionListener(new SelectionAdapter() {
	    				public void widgetSelected(SelectionEvent evt) {
	    					Trade t = new Trade();
	    					List<Trade> li= th.findAll();
	    					int bigid = 0;
	    					for(Trade tt : li){
	    						if(tt.getId() > bigid)
	    							bigid = tt.getId();
	    					}
	    					t.setId(bigid +1);
	    					t.setOpenTradeDate(new Date());
	    					AccountHome ach = new AccountHome();
	    					Account ac = ach.findAll().get(0);
	    					t.setAccount(ac);
	    					th.getSessionFactory().getCurrentSession().beginTransaction();
	    					th.persist(t);
	    					th.getSessionFactory().getCurrentSession().getTransaction().commit();
	    					
	    					
	    					
	    					;        		}
	    			});
	    		}
	    		{
	    			btnRemove = new Button(composite1, SWT.PUSH | SWT.CENTER);
	    			btnRemove.setText("Remove");
	    			
	    			btnRemove.addSelectionListener(new SelectionAdapter() {
	    				public void widgetSelected(SelectionEvent evt) {
	    					th.getSessionFactory().getCurrentSession().beginTransaction();
	    					th.delete(trade);
	    					th.getSessionFactory().getCurrentSession().getTransaction().commit();
	    					
	    				}
	    			});
	    		}

	    		
	    		
	    		
	    			
	    		
	    		    	}
    	}
    	
    }

    /* (non-Javadoc)
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
			if(newTrade != null){
				trade = newTrade;
				
				txtOpenPrice.setText("" + trade.getOpenprice());
				txtClose.setText("" + trade.getCloseprice());
				if( trade.getOpenTradeDate() != null)
					txtOpenDate.setText(trade.getOpenTradeDate() .toString());
				else
					txtOpenDate.setText("");
				if(trade.getCloseTradeDate() != null)
					txtCloseDate.setText(trade.getCloseTradeDate().toString());
				else
					txtCloseDate.setText("");
				txtSL.setText(""+ trade.getStoploss());
				txtTP.setText("" +trade.getTp());
				txtQty.setText("" + trade.getQty());
				if(trade.getInstrument() != null)
					txtIns.setText(trade.getInstrument());
				else
					txtIns.setText("");
				if(trade.getReference() !=null)
					txtReference.setText(trade.getReference());
				else
					txtReference.setText("");
				txtCarryCost.setText("" + trade.getCarrycost());
				
				cmbAccounts.clearSelection();
				cmbAccounts.removeAll();
				AccountHome achome = new AccountHome();
				List<Account> aclist = achome.findAll();
				int i=0;
				int selectedAccountid = 0;
				for(Account ac:aclist){
					cmbAccounts.add(ac.getName());
					if (trade.getAccount().getId() == i)
						selectedAccountid = i;
					i++;
				}
				cmbAccounts.select(selectedAccountid);
				
					
			}


		}
	}
	
	public void dispose(){
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}
    
}
