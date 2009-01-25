package traderjournal.gwt.client;

import java.util.List;

import traderjournal.gwt.client.service.TradeInterface;
import traderjournal.gwt.client.service.TradeInterfaceAsync;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.Trade;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.binder.TreeBinder;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionEvent;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.treetable.TreeTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class TradeViewTab extends LayoutContainer   {
	private FormBinding formBindings;  
    
	  @Override  
	  protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);  
	    setStyleAttribute("margin", "10px");  
	      
	    ContentPanel cp = new ContentPanel();  
	      
	    cp.setHeading("Form Bindings");  
	    cp.setFrame(true);  
	    cp.setSize(800, 400);  
	    cp.setLayout(new RowLayout(Orientation.HORIZONTAL));  
	  TreeTable tt = new TreeTable();
	  Grid<Trade> g ;
	  
	  
	    Tree tree = createTree();
	    tree.  addListener(Events.SelectionChange,  
	        new Listener<TreeEvent>() {  
	          public void handleEvent(TreeEvent be) {  
	        	  
	        	  formBindings.bind((ModelData)be.selected.get(0).getModel());
	              
	          }  
	        });
	         
	    cp.add(tree, new RowData(.6, 1));  
	  
	    FormPanel panel = createForm();  
	    formBindings = new FormBinding(panel, true);  
	  
	    cp.add(panel, new RowData(.4, 1));  
	  
	    add(cp);  
	  }  
	
	HorizontalPanel splitPanel = new HorizontalPanel();
	
	private FormPanel createForm() {  
	    FormPanel panel = new FormPanel();  
	    panel.setHeaderVisible(false);  
	  
      
	    DateField openDate = new DateField();  
	    openDate.setName("openTradeDate");  
	    openDate.setFieldLabel("OpenDate");  
	    panel.add(openDate);  
	    
	    NumberField openPrice = new NumberField();  
	    openPrice.setName("openprice");  
	    openPrice.setFieldLabel("OpenPrice");  
	    openPrice.setFormat(NumberFormat.getDecimalFormat());  
	    panel.add(openPrice);  
	  
	    return panel;  
	  }  
	  
	  private Tree createTree() {  
		  setLayout(new FlowLayout(10));  
		  
		    final TradeInterfaceAsync tradeService = (TradeInterfaceAsync) GWT
						.create(TradeInterface.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) tradeService;
				endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
			
		      
		  
		    // data proxy  Trade
		    RpcProxy<Trade, List<Trade>> proxy = new RpcProxy<Trade, List<Trade>>() {  
		      @Override  
		      protected void load(Trade loadConfig, AsyncCallback<List<Trade>> callback) {  
		        tradeService.findAll( callback);  
		      }  
		    };  
		  
		    // tree loader  
		    TreeLoader loader = new BaseTreeLoader(proxy) {  
		      @Override  
		      public boolean hasChildren(ModelData parent) {  
		        return parent instanceof Account;  
		        
		      }  
		    };  
		  
		    // trees store  
		    TreeStore<Trade> store = new TreeStore<Trade>(loader);  
		    store.setStoreSorter(new StoreSorter<Trade>() {  
		  
		      @Override  
		      public int compare(Store store, Trade m1, Trade m2, String property) {  
		        boolean m1Folder = m1 instanceof Trade;  
		        boolean m2Folder = m2 instanceof Trade;  
		  
		        if (m1Folder && !m2Folder) {  
		          return -1;  
		        } else if (!m1Folder && m2Folder) {  
		          return 1;  
		        }  
		  
		        return super.compare(store, m1, m2, property);  
		      }  
		    });  
		  
		    Tree tree = new Tree();  
		  
		    TreeBinder<Trade> binder = new TreeBinder<Trade>(tree, store);  
		    		    binder.setDisplayProperty("openprice");  
		     binder.setStringProvider(new ModelStringProvider<Trade>(){

				
				public String getStringValue(Trade model, String property) {
					if(property.equals("openprice"))
						return model.getOpenprice()+"";
					return null;
					
				}
		    	 
		     });
		    loader.load(null);  
		    addText("<div style='font-size: 12px;padding-bottom: 8px'>Tree which loads its children on demand using a remote service.</div>");  
		    
		    
		    return tree;
	  }  
	
	
	

}
