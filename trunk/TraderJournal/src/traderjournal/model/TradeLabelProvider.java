package traderjournal.model;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class TradeLabelProvider implements ILabelProvider {

	private String delim = null;
	
	public TradeLabelProvider(){
		delim = ":";
	}
	public TradeLabelProvider(String delimeter){
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
		
		return t.getId() + delim + t.getInstrument() +delim+ t.getTradeOpenDate();
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
