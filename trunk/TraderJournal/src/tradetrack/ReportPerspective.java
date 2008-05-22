package tradetrack;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import tradetrack.views.AccountListView;
import tradetrack.views.ImageViewPart;
import tradetrack.views.ReportView;
import tradetrack.views.TradeDetailView;
import tradetrack.views.TradeListView;

public class ReportPerspective implements IPerspectiveFactory {
	public final static String ID = "TraderJournal.ReportPerspective";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);

		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addView(TradeListView.ID, IPageLayout.LEFT,0.2f, editorArea);
		layout.addView(ReportView.ID, IPageLayout.RIGHT, 0.2f, TradeListView.ID);
		
	}

	private void defineActions(IPageLayout layout) {
		layout.addShowViewShortcut(AccountListView.ID);
		
	}

}
