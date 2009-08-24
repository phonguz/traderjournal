package traderjournal;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import traderjournal.views.AccountDetailView;
import traderjournal.views.AccountListView;
import traderjournal.views.ImageView;
import traderjournal.views.InstrumentView;
import traderjournal.views.TradeDetailView;
import traderjournal.views.TradeListView;
import traderjournal.views.TradeTableView;

public class AccountPerspective implements IPerspectiveFactory {
	public final static String ID = "TraderJournal.AccountPerspective";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);

		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addView(AccountListView.ID, IPageLayout.LEFT,0.2f, editorArea);
		layout.addView(AccountDetailView.ID,IPageLayout.BOTTOM,0.5f,AccountListView.ID);
		layout.addView(TradeTableView.ID,IPageLayout.RIGHT,0.2f,AccountListView.ID);
		layout.addView(InstrumentView.ID,IPageLayout.BOTTOM,0.2f,TradeTableView.ID);
	}

	private void defineActions(IPageLayout layout) {
		layout.addShowViewShortcut(AccountListView.ID);
		
	}

}
