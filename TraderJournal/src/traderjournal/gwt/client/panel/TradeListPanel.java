package traderjournal.gwt.client.panel;


import java.util.List;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TradeListPanel extends Composite {

	VerticalPanel panel = new VerticalPanel();
	FlexTable fx = new FlexTable();
	TradeInterfaceAsync tradeService;

	public TradeListPanel() {
		initWidget(panel);

		panel.add(fx);
		Button btnRefresh = new Button();
		if (tradeService == null) {
			tradeService = (TradeInterfaceAsync) GWT
					.create(TradeInterface.class);
			ServiceDefTarget endpoint = (ServiceDefTarget) tradeService;
			endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
		}
		tradeService.findAll(callback);
		btnRefresh.setText("Refresh");
		btnRefresh.addClickListener(new ClickListener(){

			
			public void onClick(Widget sender) {
				tradeService.findAll(callback);
				
			}
			
		});
		panel.add(btnRefresh);
		

	}

	AsyncCallback<List<Trade>> callback = new AsyncCallback<List<Trade>>() {

		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		public void onSuccess(List<Trade> result) {
			// TODO Auto-generated method stub
			System.out.println(result);
			
			int i = 0;
			fx.setStylePrimaryName("tj-Table");

			for (Trade trade : result) {

				Instrument ins = trade.getInstrument();
				if(ins != null)
					fx.setText(i, 1, trade.getInstrument().getName());

				fx.getFlexCellFormatter().setStyleName(i, 1, "tj-Table-Cell");

				DateTimeFormat df =  DateTimeFormat.getShortDateTimeFormat();
				
				fx.setText(i, 2, df.format(trade.getOpenTradeDate()));
				fx.getFlexCellFormatter().setStyleName(i, 2, "tj-Table-Cell");

				i++;
			}
		}

		/*
		 * @Override public void onFailure(Throwable caught) { panel.add(new
		 * HTML("Failed:" + caught.getMessage()));
		 *  }
		 * 
		 * 
		 * 
		 * @Override public void onSuccess(List<Trade> result) { fx.clear();
		 * for(Trade tr: result){
		 * 
		 * fx.insertRow(fx.getRowCount()-1); int row = fx.getRowCount();
		 * fx.setWidget(row, 0, new Label(tr.getInstrument()));
		 *  }
		 *  }
		 */
	};

}
