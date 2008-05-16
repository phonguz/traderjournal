package tradetrack;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import tradetrack.views.AccountListView;
import tradetrack.views.ImageViewPart;
import tradetrack.views.TradeDetailView;
import tradetrack.views.TradeListView;

public class Perspective implements IPerspectiveFactory {
	public static final String ID = "TradeTrack.tradePerspective";
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.addView(TradeListView.ID, IPageLayout.LEFT,0.2f, editorArea);
		layout.addView(ImageViewPart.ID_VIEW, IPageLayout.BOTTOM,0.5f, editorArea);
		layout.addView(TradeDetailView.ID_VIEW, IPageLayout.BOTTOM,0.5f, TradeListView.ID);
	}
	
	private void defineActions(IPageLayout layout) {
		layout.addShowViewShortcut(TradeListView.ID);
		layout.addShowViewShortcut(ImageViewPart.ID_VIEW);
		layout.addShowViewShortcut(TradeDetailView.ID_VIEW);
		
		
	}
}
