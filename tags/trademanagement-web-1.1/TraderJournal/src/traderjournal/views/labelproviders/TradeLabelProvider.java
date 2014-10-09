package traderjournal.views.labelproviders;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import traderjournal.model.entities.Trade;
import traderjournal.stats.TradeStatistics;
import traderjournal.views.TradeTableView;

public class TradeLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Trade t = (Trade) element;
		TradeStatistics ts = new TradeStatistics(t);
		String ret ="";
		switch (columnIndex) {
		case TradeTableView.COL_colID:
			return new Long(t.getId()).toString();
		case TradeTableView.COL_colInstrument:
			
			
				if(t.getInstrument() != null)
					ret = t.getInstrument().getName();
				else
					ret = "";
		
			
					
			
			
			return ret;
		case TradeTableView.COL_colOpenDate:
			if(t.getOpenTradeDate() != null)
				return LabelUtils.getDateFormat().format(t.getOpenTradeDate());
			else
			return null; 
			
			
		case TradeTableView.COL_colOpenPrice:
			if(t.getOpenprice() != null)
				return Double.toString(t.getOpenprice());
			else
				return null;
		
		case TradeTableView.COL_colCloseDate:
			if(t.getCloseTradeDate() != null)
			 return LabelUtils.getDateFormat().format(t.getCloseTradeDate());
			else
				return null;
		case TradeTableView.COL_colClosePrice:
			if(t.getCloseprice() != null)
				return Double.toString(t.getCloseprice());
			return
				"";
		case TradeTableView.COL_colSL:
			if(t.getStoploss() != null)
				return Double.toString(t.getStoploss());
			return "";
		case TradeTableView.COL_colTP:
			if(t.getTp() != null)
				return Double.toString(t.getTp());
			else
				return "";
		case TradeTableView.COL_colReference:
			if(t.getReference() != null)
				return t.getReference();
			else
				return "";
		
		case TradeTableView.COL_colOriginalRR:
			
			return  LabelUtils.nf.format(ts.getOriginalRR());
		case TradeTableView.COL_colFinalRR:
			
			return  LabelUtils.nf.format(ts.getRealisedRR());
			
		}
		
		return ret;

	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
