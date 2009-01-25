package traderjournal.gwt.client.panel;


import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.gwt.client.service.TradeInterfaceAsync;
import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TradeDetailPanel extends Composite implements ClickListener{

	VerticalPanel panel = new VerticalPanel();
	FlexTable fx = new FlexTable();
	TradeInterfaceAsync tradeService;
	int tradeID ;
	
	
	public TradeDetailPanel() {
		initWidget(panel);

		
		panel.add(fx);
		Button btnRefresh = new Button();
		if (tradeService == null) {
			tradeService = (TradeInterfaceAsync) GWT
					.create(TradeInterface.class);
			ServiceDefTarget endpoint = (ServiceDefTarget) tradeService;
			endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
		}
		
		btnRefresh.setText("Refresh");
		btnRefresh.addClickListener(new ClickListener(){

			
			public void onClick(Widget sender) {
				tradeService.findTradeByTradeID(tradeID, callback);
				
			}
			
		});
		panel.add(btnRefresh);
		

	}

	AsyncCallback<Trade> callback = new AsyncCallback<Trade>() {

		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		public void onSuccess(Trade result) {
			// TODO Auto-generated method stub
			System.out.println(result);
			
			
			fx.setStylePrimaryName("tj-Table");

			Trade trade = result;

				Instrument ins = trade.getInstrument();
				if(ins != null)
					fx.setText( 0,1, "Name");
					fx.setText( 0,2, trade.getInstrument().getName());

				
				fx.getFlexCellFormatter().setStyleName(0, 1, "tj-Table-Cell");

				DateTimeFormat df =  DateTimeFormat.getShortDateTimeFormat();
				fx.setText(1, 1 , "OpenDate");
				fx.setText(1, 2, df.format(trade.getOpenTradeDate()));
				fx.getFlexCellFormatter().setStyleName(1, 2, "tj-Table-Cell");

		}


	};

	
	public void onClick(Widget sender) {
		tradeID = 0;
		tradeService. findTradeByTradeID(tradeID,callback);
		
	}

}
