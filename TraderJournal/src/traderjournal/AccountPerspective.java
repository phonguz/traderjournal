package traderjournal;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import traderjournal.views.AccountListView;
import traderjournal.views.ImageViewPart;
import traderjournal.views.TradeDetailView;
import traderjournal.views.TradeListView;

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
