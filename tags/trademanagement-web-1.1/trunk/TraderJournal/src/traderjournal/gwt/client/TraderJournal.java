package traderjournal.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TraderJournal implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	 
    DecoratedTabPanel tp = new DecoratedTabPanel();
    tp.setWidth("800px");
    tp.setHeight("600px");
    
    

    
    // Add image and button to the RootPanel
    RootPanel.get().add(tp);

    TradeViewTab tvp = new TradeViewTab();
    
    
    
    tp.add(tvp,"Trade View");
    tp.selectTab(0);
    
    
    
 
  }
}
