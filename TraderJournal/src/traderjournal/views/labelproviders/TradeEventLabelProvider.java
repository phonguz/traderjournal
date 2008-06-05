package traderjournal.views.labelproviders;

import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import traderjournal.Activator;
import traderjournal.model.hibernate.Tradeevent;
import traderjournal.model.hibernate.TradeeventHome;

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
		Tradeevent te = (Tradeevent) element;
		if (te != null) {
			switch (columnIndex) {
			case 0: // id
				return "" + te.getId();

			case 1: // date
				if(te.getEventDate() != null){
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					return sd.format(te.getEventDate());
				}
				else
					return "";
			case 2: // type
				TradeeventHome th = new TradeeventHome();
				th.getSessionFactory().getCurrentSession().beginTransaction();
				th.getSessionFactory().getCurrentSession().refresh(te);
				String ret = te.getTradeeventtype().getName();
				th.getSessionFactory().getCurrentSession().getTransaction().commit();
				return ret;
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
