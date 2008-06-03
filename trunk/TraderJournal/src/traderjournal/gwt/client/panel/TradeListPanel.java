package traderjournal.gwt.client.panel;

import java.util.List;

import traderjournal.gwt.server.TradeService;
import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TradeListPanel extends Composite {
	TradeHome th = new TradeHome();
	VerticalPanel panel = new VerticalPanel();
	FlexTable fx = new FlexTable();
	TradeService tradeService;
	public TradeListPanel(){
		initWidget(panel);
		
		
		panel.add(fx);
		Button btnRefresh = new Button();
		  if (tradeService == null) {   
			  tradeService = GWT.create(TradeService.class);  
			  ServiceDefTarget endpoint = (ServiceDefTarget)tradeService;   
			  endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc"); 
			  }
		tradeService.findAll( callback);
		panel.add(btnRefresh);
		
		
		
	}
	
	AsyncCallback<List<Trade>> callback = new AsyncCallback<List<Trade>>(){

		@Override
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
		
	};



}
