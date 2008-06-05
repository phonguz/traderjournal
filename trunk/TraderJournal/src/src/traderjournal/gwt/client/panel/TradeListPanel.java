package traderjournal.gwt.client.panel;

import java.util.List;

import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.gwt.client.service.TradeInterfaceAsync;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradeListPanel extends Composite {
	
	VerticalPanel panel = new VerticalPanel();
	FlexTable fx = new FlexTable();
	TradeInterfaceAsync tradeService; 
	public TradeListPanel(){
		initWidget(panel);
		
		
		panel.add(fx);
		Button btnRefresh = new Button();
		  if (tradeService == null) {   
			  tradeService = (TradeInterfaceAsync)GWT.create(TradeInterface.class);  
			  ServiceDefTarget endpoint = (ServiceDefTarget)tradeService;   
			  endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc"); 
			  }
		tradeService.findAll( callback);
		panel.add(btnRefresh);
		
		
		
	}
	
	AsyncCallback <Trade[]> callback = new AsyncCallback<Trade[]>(){

		
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		
		public void onSuccess(Trade[] result) {
			// TODO Auto-generated method stub
			
		}

	
	


	

		/*@Override
		public void onFailure(Throwable caught) {
			panel.add(new HTML("Failed:" + caught.getMessage()));
			
		}



		@Override
		public void onSuccess(List<Trade> result) {
			fx.clear();
			for(Trade tr: result){
				
				fx.insertRow(fx.getRowCount()-1);
				int row = fx.getRowCount();
				fx.setWidget(row, 0, new Label(tr.getInstrument()));

			}
			
		}
		*/
	};



}
