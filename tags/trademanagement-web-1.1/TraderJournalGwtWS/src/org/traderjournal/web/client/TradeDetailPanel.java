package org.traderjournal.web.client;

import java.util.List;

import org.traderjournal.model.gen.Instrument;
import org.traderjournal.model.gen.Trade;
import org.traderjournal.web.client.svc.InstrumentService;
import org.traderjournal.web.client.svc.InstrumentServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;

public class TradeDetailPanel extends Composite implements HasText {
	InstrumentServiceAsync instrumentservice = (InstrumentServiceAsync) GWT
	.create(InstrumentService.class);
	private static TradeDetailPanelUiBinder uiBinder = GWT
			.create(TradeDetailPanelUiBinder.class);
	@UiField
	HTMLPanel panel;
	@UiField
	DoubleBox openPrice;
	@UiField
	DateBox openDate;
	@UiField
	DoubleBox quantity;
	@UiField
	DoubleBox closePrice;
	@UiField
	DateBox closeDate;
	@UiField
	ListBox account;
	@UiField
	ListBox instrument;
	@UiField Button btnSave;
	@UiField Button btnDelete;
	
	
	

	
	
	interface TradeDetailPanelUiBinder extends
			UiBinder<Widget, TradeDetailPanel> {
	}

	public TradeDetailPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		ServiceDefTarget serviceDef = (ServiceDefTarget) instrumentservice;
		serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ "InstrumentService");
	}

	public TradeDetailPanel(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public void setText(String text) {

	}

	public String getText() {
		return null;
	}

	void loadTrade(Trade trade) {

		openPrice.setValue(trade.getOpenprice());
		openDate.setValue(trade.getOpenTradeDate());
		quantity.setValue(trade.getQty());
		closePrice.setValue(trade.getCloseprice());
		closeDate.setValue(trade.getCloseTradeDate());

		AsyncCallback<List<Instrument>> callback = new AsyncCallback<List<Instrument>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(List<Instrument> result) {

				for (Instrument ins : result) {
					instrument.addItem(ins.getId().toString(), ins.getName());

				}

			}
		};
		instrumentservice.getAllInstruments(callback);

	}

	@UiHandler("panel")
	void onPanelAttachOrDetach(AttachEvent event) {
		// Not Sure if this is the

	}

	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent event) {
	}
	@UiHandler("btnDelete")
	void onBtnDeleteClick(ClickEvent event) {
	}
}
