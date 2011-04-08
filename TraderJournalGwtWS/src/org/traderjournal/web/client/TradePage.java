package org.traderjournal.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TradePage extends Composite implements HasText {

	private static TradePageUiBinder uiBinder = GWT
			.create(TradePageUiBinder.class);

	interface TradePageUiBinder extends UiBinder<Widget, TradePage> {
	}

	public TradePage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}



	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	

}
