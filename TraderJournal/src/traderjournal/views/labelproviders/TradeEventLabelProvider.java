package traderjournal.views.labelproviders;

import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import traderjournal.Activator;
import traderjournal.editors.TradeEditor;
import traderjournal.model.hibernate.Tradeevent;
import traderjournal.model.hibernate.TradeeventHome;

public class TradeEventLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {


	}

	@Override
	public void dispose() {
		

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		

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
			case TradeEditor.COL_ID: // id
				return "" + te.getId();

			case TradeEditor.COL_EVENT_DATE: // date
				if(te.getEventDate() != null){
					
					return LabelUtils.getDateFormat().format(te.getEventDate());
				}
				else
					return "";
			case TradeEditor.COL_EVENT_TYPE: // type
				TradeeventHome th = new TradeeventHome();
				th.getSessionFactory().getCurrentSession().beginTransaction();
				th.getSessionFactory().getCurrentSession().refresh(te);
				String ret = te.getTradeeventtype().getName();
				th.getSessionFactory().getCurrentSession().getTransaction().commit();
				return ret;
			case TradeEditor.COL_DESCRIPTION: // description
				if (te.getDescription() != null)
				return te.getDescription();
				else
					return "";
			case TradeEditor.COL_EVENT_ORDER: // order
				if(te.getEventorder() != null)
				return "" + te.getEventorder();
				else
					return "0";
			case TradeEditor.COL_NEW_VALUE: // newvalue
				if(te.getNewValue() != null)
				return "" + te.getNewValue();
				else
					return "0.0";
				
			case TradeEditor.COL_IMAGE: // / img1
//				if (te.getAllImages() != null && te.getAllImages().size() > 0
//						&& te.getAllImages().get(0) != null)
//					return null;
//
//				else
					return "add";
			case TradeEditor.COL_DND_UPLOAD: // / img1
//				if (te.getAllImages() != null && te.getAllImages().size() > 0
//						&& te.getAllImages().get(0) != null)
//					return null;
//
//				else
					return "dnd";

			case TradeEditor.COL_REMOVE:
				return null;
			}
		}
		return null;
	}

}
