package traderjournal.model.hibernate;

import java.util.Comparator;

import traderjournal.model.hibernate.Tradeevent;

public class TradeEventOrderComparotor implements Comparator<Tradeevent> {

	@Override
	public int compare(Tradeevent arg0, Tradeevent arg1) {
		
		return arg0.getEventorder() - arg1.getEventorder();
	}

}
