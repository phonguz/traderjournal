package traderjournal.ui.rap;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import traderjournal.ui.views.AccountDetailView;
import traderjournal.ui.views.InstrumentView;
import traderjournal.ui.views.TradeDetailView;


/**
 * Configures the perspective layout. This class is contributed through the
 * plugin.xml.
 */
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		layout.addStandaloneView(InstrumentView.ID, false, IPageLayout.LEFT, 1.0f,
				editorArea);
		layout.addStandaloneView(TradeDetailView.ID, false, IPageLayout.BOTTOM, 1.0f,
				editorArea);
		
		layout.addStandaloneView(AccountDetailView.ID, false, IPageLayout.RIGHT, 1.0f,
				editorArea);
		
	}
}
