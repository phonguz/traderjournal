package traderjournal.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import traderjournal.model.hibernate.Trade;

public class TradeEditorInput implements IEditorInput {
	Trade trade = null;
	
	public TradeEditorInput(Trade t){
		trade = t;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return trade.getId() +"-" + trade.getOpenTradeDate().toString();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		
		return "Tooltip: " + getName();
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean equals(Object obj){
		if(super.equals(obj)){
			return true;
		}
		if (obj instanceof TradeEditorInput) {
			return getName().equals(((TradeEditorInput)obj).getName());
			
		}
		return false;
	}
	public int hashCode() {
		return getName().hashCode();
	}

}
