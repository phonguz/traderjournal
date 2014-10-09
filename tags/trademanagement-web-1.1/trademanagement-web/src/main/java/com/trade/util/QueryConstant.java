package com.trade.util;

public class QueryConstant {

	private QueryConstant(){}
	
	public static final String DELETE_FROM_TRADEATTACHMENT_BY_TRADEEVENT_ID = "DELETE from tradeattachment as ta join tradeevent as te on te.id=ta.tradeevent_id and b.trade_id=?";
	public static final String DELETE_FROM_TRADEEVENT_BY_TRADE_ID = "DELETE FROM Tradeevent WHERE trade_id=?";
	public static final String DELETE_FROM_ACCOUNTS_BY_TRADER_ID = "DELETE FROM Accounts WHERE trader_id=?";
}
