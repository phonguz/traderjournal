package org.traderjournal.web.client;

import java.util.Date;
import java.util.List;

import org.traderjournal.model.gen.Account;
import org.traderjournal.model.gen.Trade;
import org.traderjournal.model.gen.Trader;
import org.traderjournal.web.client.svc.AccountService;
import org.traderjournal.web.client.svc.AccountServiceAsync;
import org.traderjournal.web.client.svc.TradeService;
import org.traderjournal.web.client.svc.TradeServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.TreeViewModel.NodeInfo;
import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;
import com.google.gwt.cell.client.TextCell;


public class TradeList extends Composite {
	TradeServiceAsync tradeservice = (TradeServiceAsync) GWT
	.create(TradeService.class);
	
	AccountServiceAsync accountservice = (AccountServiceAsync) GWT
	.create(AccountService.class);
	
	private List<Trade> currentTrades = null;
	private List<Account> currentAccounts = null;
	
	private static final Binder binder = GWT.create(Binder.class);
	@UiField Tree tradeTree;
	@UiField VerticalPanel vertPAnel;
	@UiField(provided=true) CellTree cellTree = new CellTree(
		new TreeViewModel() {
			final AbstractDataProvider<String> dataProvider = new ListDataProvider<String>();
			final AbstractSelectionModel<String> selectionModel = new NoSelectionModel<String>();
			@Override
			public <T> NodeInfo<?> getNodeInfo(T value) {
				return new DefaultNodeInfo<String>(dataProvider, new TextCell(), selectionModel, null);
			}
			@Override
			public boolean isLeaf(Object value) {
				return true;
			}
		}, null);

	interface Binder extends UiBinder<Widget, TradeList> {
	}

	class TradeCallback implements AsyncCallback<List<Trade>>{
		private TreeItem accountTreeItem;
		
		public TradeCallback(TreeItem treeItem){
			accountTreeItem = treeItem;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Trade> result) {
			populateTreeItemWithTrades(accountTreeItem,result);
			
		}
		
		
		
	}
	
	public TradeList() {
		initWidget(binder.createAndBindUi(this));
		
		ServiceDefTarget serviceDeftrade = (ServiceDefTarget) tradeservice;
		serviceDeftrade.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ TradeService.url);
		ServiceDefTarget serviceDefaccount = (ServiceDefTarget) accountservice;
		serviceDefaccount.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ AccountService.url);
		
		loadAllAccounts();
		
	}
	
	public void populateTreeItemWithTrades(TreeItem accountTreeItem,
			List<Trade> result) {
		accountTreeItem.removeItems();
		currentTrades = result;
		for (Trade tr : currentTrades) {
			String tradeName = formatDate( tr.getOpenTradeDate())+ ":"+ tr.getInstrument().getName();
			TreeItem ti = new TreeItem();
			ti.setText(tradeName);
			accountTreeItem.addItem(ti);
    		
		}
		
	}

	public static String formatDate(Date dt){
		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
		
		return dtf.format(dt);
		
	}
	
	private void populateTreeWithAccounts() {
		tradeTree.removeItems();
		for(Account ac : currentAccounts){
			
			
			TreeItem ti = new TreeItem();
			ti.setText(ac.getName());
			ti.setUserObject(ac);
			TreeItem tie = new TreeItem();
			tie.setText("");
			ti.addItem(tie);
			
			tradeTree.addItem(ti);
		}
	}
	
 

	private void loadAllAccounts() {
		
		Trader trader = new Trader();
		trader.setId(0);
		AsyncCallback<List<Account>> callback = new AsyncCallback<List<Account>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Account> result) {
				currentAccounts= result;
				populateTreeWithAccounts();

			}

			
		};
		accountservice.getAccountListForTrader(trader, callback);
 
	}

    
	private void loadAllTradesForAccount(Account ac,TreeItem ti) {
		
		TradeCallback tc = new TradeCallback(ti);
		tradeservice.getAllTradesForAccount(ac, tc);
	}

	
	@UiHandler("tradeTree")
	void onTradeTreeOpen(OpenEvent<TreeItem>  event) {
		TreeItem item = event.getTarget();
		Object uo = item.getUserObject();
		if(uo instanceof Account){
			loadAllTradesForAccount((Account) uo, item);
			
			
		}
	}
}
