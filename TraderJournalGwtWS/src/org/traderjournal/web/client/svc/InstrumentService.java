package org.traderjournal.web.client.svc;

import java.util.List;

import org.traderjournal.model.gen.Instrument;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("InstrumentService")
public interface InstrumentService extends RemoteService {
	
	public static final String url = "InstrumentService";
	
 	List<Instrument> getAllInstruments();
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static InstrumentServiceAsync instance;
		public static InstrumentServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(InstrumentService.class);
			}
			return instance;
		}
	}
}
