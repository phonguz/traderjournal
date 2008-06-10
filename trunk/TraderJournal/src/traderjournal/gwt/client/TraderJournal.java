package traderjournal.gwt.client;

import traderjournal.gwt.client.panel.TradeListPanel;
import traderjournal.model.DBUtils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TraderJournal implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	 
    
    VerticalPanel vPanel = new VerticalPanel();
    // We can add style names.
    vPanel.addStyleName("widePanel");
    vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    
    // Add image and button to the RootPanel
    RootPanel.get().add(vPanel);

    
    
    
    TradeListPanel tlp = new TradeListPanel();
    vPanel.add(tlp);
  }
}
