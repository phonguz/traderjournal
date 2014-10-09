package traderjournal.views.sorters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import traderjournal.model.entities.Tradeevent;

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
		Tradeevent te1 = (Tradeevent)e1;
		Tradeevent te2 = (Tradeevent)e2;
		int result = 0;
		switch (sortcol) {
		case EVENTDATE:
			
			return te1.getEventDate().compareTo(te2.getEventDate());
		case ID:
			long lresult = te1.getId() - te2.getId();
			result = lresult < 0 ? -1 : (lresult > 0) ? 1 : 0;  
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
