package tradetrack.editors;

import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import tradetrack.Activator;
import tradetrack.model.TradeEvent;
import tradetrack.model.TradeEventImage;
import tradetrack.model.TradeEventType;

public class TradeEventLabelProvider implements ITableLabelProvider {

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

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
	//	TradeEvent te = (TradeEvent) element;
		switch (columnIndex) {

		case 5: // / img1
			//looks as though this slows it down
//			if (te.getAllImages() != null && te.getAllImages().size() > 0
//					&& te.getAllImages().get(0) != null) {
//				TradeEventImage ei = te.getAllImages().get(0);
//				Image img = ei.getImage();
//				return new Image(img.getDevice(), img.getImageData().scaledTo(
//						50, 30));
//			}
			
			break;

		
		case 6:

			ImageDescriptor id = Activator.getImageDescriptor("icons/delete.gif");
			return new Image(Activator.getDefault().getWorkbench().getDisplay(), id.getImageData().scaledTo(
					16, 16));

		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		TradeEvent te = (TradeEvent) element;
		if (te != null) {
			switch (columnIndex) {
			case 0: // id
				return "" + te.getID();

			case 1: // date
				if(te.getEventDate() != null){
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					return sd.format(te.getEventDate());
				}
				else
					return "";
			case 2: // type
				return TradeEventType.getTradeEventType(te.getEventtype()).getName();
			case 3: // description
				if (te.getDescription() != null)
				return te.getDescription();
				else
					return "";
			case 4: // order
				return "" + te.getEventorder();
			case 5: // / img1
//				if (te.getAllImages() != null && te.getAllImages().size() > 0
//						&& te.getAllImages().get(0) != null)
//					return null;
//
//				else
					return "add";

			case 6:
				return null;
			}
		}
		return null;
	}

}
