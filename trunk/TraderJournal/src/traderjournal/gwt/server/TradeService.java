package traderjournal.gwt.server;

import java.util.List;

import traderjournal.gwt.client.service.TradeInterfaceAsync;
import traderjournal.model.hibernate.Trade;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class TradeService implements TradeInterfaceAsync {

    TradeInterfaceAsync service = (TradeInterfaceAsync) GWT.create(TradeService.class);
    ServiceDefTarget endpoint = (ServiceDefTarget) service;

    public TradeService()
    {
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
    }

	
	@Override
	public void findAll( AsyncCallback<List<Trade>> callback) {
		service.findAll( callback);


	}

}
