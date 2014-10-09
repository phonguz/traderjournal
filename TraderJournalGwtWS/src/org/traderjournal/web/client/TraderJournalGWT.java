package org.traderjournal.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TraderJournalGWT implements EntryPoint {
	
	public void onModuleLoad() {
		
		TradePage tp = new TradePage();
		
		RootPanel.get().add(tp);
		
	
	}
}
