package tradetrack.editors;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import tradetrack.model.TradeEvent;
import tradetrack.model.TradeEventImage;

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
		TradeEvent te = (TradeEvent) element;
		switch (columnIndex) {

		case 5: // / img1
			if (te.getAllImages() != null && te.getAllImages().size() > 0
					&& te.getAllImages().get(0) != null) {
				TradeEventImage ei = te.getAllImages().get(0);
				Image img = ei.getImage();
				return new Image(img.getDevice(), img.getImageData().scaledTo(
						50, 30));
			}
			break;

		case 6: // / img2
			if (te.getAllImages() != null && te.getAllImages().size() > 1
					&& te.getAllImages().get(1) != null) {
				TradeEventImage ei = te.getAllImages().get(1);
				Image img = ei.getImage();
				return new Image(img.getDevice(), img.getImageData().scaledTo(
						50, 30));
			}
			break;

		case 7: // / img3
			if (te.getAllImages() != null && te.getAllImages().size() > 2
					&& te.getAllImages().get(2) != null) {
				TradeEventImage ei = te.getAllImages().get(2);
				Image img = ei.getImage();
				return new Image(img.getDevice(), img.getImageData().scaledTo(
						50, 30));
			}
			break;

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
				if(te.getEventDate() != null)
				return te.getEventDate().toString();
				else
					return "";
			case 2: // type
				return te.getTradeEventType().getName();
			case 3: // description
				if (te.getDescription() != null)
				return te.getDescription();
				else
					return "";
			case 4: // order
				return "" + te.getEventorder();
			case 5: // / img1
				if (te.getAllImages() != null && te.getAllImages().size() > 0
						&& te.getAllImages().get(0) != null)
					return null;

				else
					return "add";
			case 6: // / img2
				if (te.getAllImages() != null && te.getAllImages().size() > 1
						&& te.getAllImages().get(1) != null)
					return null;

				else
					return "add";
			case 7: // / img3
				if (te.getAllImages() != null && te.getAllImages().size() > 2
						&& te.getAllImages().get(2) != null)
					return null;

				else
					return "add";
			case 8:
				return "r";
			}
		}
		return null;
	}

}
