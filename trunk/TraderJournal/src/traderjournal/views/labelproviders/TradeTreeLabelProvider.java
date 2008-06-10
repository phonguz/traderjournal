package traderjournal.views.labelproviders;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.Trade;

public class TradeTreeLabelProvider implements ILabelProvider {

	private String delim = null;
	
	public TradeTreeLabelProvider(){
		delim = ":";
	}
	public TradeTreeLabelProvider(String delimeter){
		delim = delimeter;
	}
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof Account){
			Account a = (Account)element;
			return a.getName();
		}else{
		Trade t = (Trade)element;
		
		return t.getId() + delim + t.getInstrument() +delim+ t.getOpenTradeDate();
		}
		
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
