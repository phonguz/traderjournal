package traderjournal.wap.model.entities;

import java.util.Comparator;

import traderjournal.wap.model.entities.Tradeevent;

public class TradeEventOrderComparotor implements Comparator<Tradeevent> {

	@Override
	public int compare(Tradeevent arg0, Tradeevent arg1) {
		
		return arg0.getEventorder() - arg1.getEventorder();
	}

}
