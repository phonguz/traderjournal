package tradetrack.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;

import tradetrack.model.Trade;

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
        "tradetrack.views.TradeDetailView"; //$NON-NLS-1$
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

        
        private Trade trade = new Trade();
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
	    	parent.setSize(109, 209);
    	}
    	{
    	}
        composite1 = new Composite(parent, SWT.NULL);
        composite1.setLayout(null);
        {
        	txtOpenPrice = new Text(composite1, SWT.NONE);
        	txtOpenPrice.setBounds(39, 12, 70, 13);
        }
        {
        	lblOpen = new Label(composite1, SWT.NONE);
        	lblOpen.setText("Open");
        	lblOpen.setBounds(5, 12, 28, 11);
        }
        {
        	lblSL = new Label(composite1, SWT.NONE);
        	lblSL.setText("SL");
        	lblSL.setBounds(5, 49, 28, 11);
        }
        {
        	txtSL = new Text(composite1, SWT.NONE);
        	txtSL.setBounds(39, 49, 70, 13);
        }
        {
        	label1 = new Label(composite1, SWT.NONE);
        	label1.setText("TP");
        	label1.setBounds(5, 66, 28, 11);
        }
        {
        	txtTP = new Text(composite1, SWT.NONE);
        	txtTP.setBounds(39, 66, 70, 13);
        }
        {
        	txtClose = new Text(composite1, SWT.NONE);
        	txtClose.setBounds(39, 107, 70, 13);
        }
        {
        	label2 = new Label(composite1, SWT.NONE);
        	label2.setText("Close");
        	label2.setBounds(5, 105, 28, 11);
        }
        {
        	txtOpenDate = new Text(composite1, SWT.NONE);
        	txtOpenDate.setBounds(39, 30, 70, 13);
        }
        {
        	txtCloseDate = new Text(composite1, SWT.NONE);
        	txtCloseDate.setBounds(39, 126, 70, 13);
        }
        {
        	btnSave = new Button(composite1, SWT.PUSH | SWT.CENTER);
        	btnSave.setText("Save");
        	btnSave.setBounds(7, 145, 48, 20);
        	btnSave.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent evt) {
        			trade.setTradeOpen(Double.parseDouble(txtOpenPrice.getText()));
        			trade.setTradeClose(Double.parseDouble(txtClose.getText()));
        			trade.setStoploss(Double.parseDouble(txtSL.getText()));
        			trade.setTp(Double.parseDouble(txtTP.getText()));
        			trade.setQty(Integer.parseInt(txtQty.getText()));
        			DateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        			try {
						trade.setTradeOpenDate(df.parse(txtOpenDate.getText()) );
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
        			
        			trade.update();
        			Trade.notifyTradeListChangeListeners();
        		}
        	});
        }
        {
        	btnAdd = new Button(composite1, SWT.PUSH | SWT.CENTER);
        	btnAdd.setText("Add");
        	btnAdd.setBounds(61, 145, 48, 20);
        	btnAdd.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent evt) {
        			trade.addNewTrade();
        			Trade.notifyTradeListChangeListeners();
        			
;        		}
        	});
        }
        {
        	btnRemove = new Button(composite1, SWT.PUSH | SWT.CENTER);
        	btnRemove.setText("Remove");
        	btnRemove.setBounds(35, 177, 48, 20);
        	btnRemove.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent evt) {
        			trade.delete();
        			Trade.notifyTradeListChangeListeners();
        		}
        	});
        }
        {
        	txtQty = new Text(composite1, SWT.NONE);
        	txtQty.setText("" + trade.getTp());
        	txtQty.setBounds(39, 88, 70, 13);
        }
        {
        	label3 = new Label(composite1, SWT.NONE);
        	label3.setText("QTY");
        	label3.setBounds(5, 88, 28, 11);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        // TODO Auto-generated method stub
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
				
				txtOpenPrice.setText("" + trade.getTradeOpen());
				txtClose.setText("" + trade.getTradeClose());
				if( trade.getTradeOpenDate() != null)
					txtOpenDate.setText(trade.getTradeOpenDate().toString());
				if(trade.getTradeCloseDate() != null)
					txtCloseDate.setText(trade.getTradeCloseDate().toString());
				txtSL.setText(""+ trade.getStoploss());
				txtTP.setText("" +trade.getTp());
				txtQty.setText("" + trade.getQty());
			}


		}
	}
	
	public void dispose(){
		super.dispose();
		getSite().getPage().removeSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
	}
    
}
