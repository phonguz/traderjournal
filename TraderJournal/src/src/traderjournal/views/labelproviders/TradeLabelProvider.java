package traderjournal.views.labelproviders;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import traderjournal.model.hibernate.Trade;
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
		switch (columnIndex) {
		case TradeTableView.COL_colID:
			return Integer.toString(t.getId());
		case TradeTableView.COL_colInstrument:
			return t.getInstrument();
		case TradeTableView.COL_colOpenDate:

			return t.getOpenTradeDate().toString();
		case TradeTableView.COL_colOpenPrice:
			return Double.toString(t.getOpenprice());
		
		case TradeTableView.COL_colCloseDate:
			if(t.getCloseTradeDate() != null)
			return t.getCloseTradeDate().toString();
			else
				return null;
		case TradeTableView.COL_colClosePrice:
			return Double.toString(t.getCloseprice());
		case TradeTableView.COL_colSL:
			return Double.toString(t.getStoploss());
		case TradeTableView.COL_colTP:
			return Double.toString(t.getTp());
		case TradeTableView.COL_colReference:
			return t.getReference();
		
		}
		return null;

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
