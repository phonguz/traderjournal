package traderjournal.gwt.server;

import java.util.List;

import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.TradeHome;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TradeServiceImpl extends RemoteServiceServlet implements
		TradeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Trade> findAll() {
		TradeHome th = new TradeHome();
		
		return th.findAll();
	}

}
