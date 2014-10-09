package traderjournal.views.sorters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import traderjournal.model.entities.Trade;

public class TradeSorter extends ViewerSorter {
	
	public static final int ID = 0;
	public static final int INSTRUMENT = 1;
	public static final int OPENDATE = 2;
	public static final int CLOSEDATE = 4;
	
	

	private int sortcol;
	
	public TradeSorter(int sortid){
		sortcol = sortid;
	}
	
	
	
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Trade te1 = (Trade)e1;
		Trade te2 = (Trade)e2;
		int result = 0;
		switch (sortcol) {
		case OPENDATE:
			result = te1.getOpenTradeDate().compareTo(te2.getOpenTradeDate());
			return result;
		case CLOSEDATE:
			result = te1.getCloseTradeDate().compareTo(te2.getCloseTradeDate());
			return result;
		case ID:
			long lresult = (te1.getId() - te2.getId());
			
			result = lresult < 0 ? -1 : (lresult > 0) ? 1 : 0;  
			return result;
			
		case INSTRUMENT:
			result = te1.getInstrument().getName().compareTo(te2.getInstrument().getName());


			return result;
		default:
			return 0;
		}
		
	
	}

}
