package tradetrack;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import tradetrack.views.AccountListView;
import tradetrack.views.ImageViewPart;
import tradetrack.views.TradeDetailView;
import tradetrack.views.TradeListView;

public class AccountPerspective implements IPerspectiveFactory {
	public final static String ID = "TraderJournal.AccountPerspective";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);

		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.addView(AccountListView.ID, IPageLayout.LEFT,0.2f, editorArea);
		
	}

	private void defineActions(IPageLayout layout) {
		layout.addShowViewShortcut(AccountListView.ID);
		
	}

}
