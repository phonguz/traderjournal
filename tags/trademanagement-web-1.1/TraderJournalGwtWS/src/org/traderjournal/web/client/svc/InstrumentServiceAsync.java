package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Instrument;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InstrumentServiceAsync {

	void getAllInstruments(AsyncCallback<List<Instrument>> callback);

	

}
