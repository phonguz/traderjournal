package traderjournal.editors;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import traderjournal.model.TradeEvent;

public class TradeEventSorter extends ViewerSorter {
	public static final int EVENTDATE = 1;
	public static final int ID = 0;
	public static final int EVENTORDER = 8;

	private int sortcol;
	
	public TradeEventSorter(int sortid){
		sortcol = sortid;
	}
	
	
	
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		TradeEvent te1 = (TradeEvent)e1;
		TradeEvent te2 = (TradeEvent)e2;
		int result = 0;
		switch (sortcol) {
		case EVENTDATE:
			
			return result;
		case ID:
			result = te1.getID() - te2.getID();
			result = result < 0 ? -1 : (result > 0) ? 1 : 0;  
			return result;
			
		case EVENTORDER:
			result = te1.getEventorder() - te2.getEventorder();
			result = result < 0 ? -1 : (result > 0) ? 1 : 0;  
			return result;
		default:
			return 0;
		}
		
	
	}

}
