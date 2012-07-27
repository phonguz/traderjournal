package traderjournal.views.labelproviders;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import traderjournal.model.entities.Trade;

public class TradeListLabelProvider implements ILabelProvider {

	private String delim = null;
	
	public TradeListLabelProvider(){
		delim = ":";
	}
	public TradeListLabelProvider(String delimeter){
		delim = delimeter;
	}
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		Trade t = (Trade)element;
		String ret = "";
		
		ret =  LabelUtils.df.format(t.getOpenTradeDate()) + delim + t.getInstrument().getName() +delim+ t.getId();
		
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
