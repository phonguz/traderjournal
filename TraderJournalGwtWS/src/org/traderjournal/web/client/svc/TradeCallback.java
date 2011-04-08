package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Trade;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TradeCallback implements AsyncCallback<List<Trade>> {

	List<Trade> tradeList = null;
	
	public List<Trade> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}

	@Override
	public void onFailure(Throwable caught) {
		tradeList = null;
		Window.alert(caught.getMessage());

	}

	@Override
	public void onSuccess(List<Trade> result) {
		tradeList = result;
		
	}

}
