package traderjournal.wap.server;

import java.util.ArrayList;
import java.util.List;

import traderjournal.wap.client.GreetingService;
import traderjournal.wap.model.Filter;
import traderjournal.wap.model.FilterOperator;
import traderjournal.wap.model.RequestFactoryUtilsJpa;
import traderjournal.wap.model.entities.Account;
import traderjournal.wap.model.entities.Trade;
import traderjournal.wap.model.entities.Trader;
import traderjournal.wap.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public final static void main(String [] args){
		
		Long li = RequestFactoryUtilsJpa.getLoggedInUserId();
		List<Filter> fil = new ArrayList<Filter>();
		Filter fi = new Filter("openprice",FilterOperator.GREATERTHAN, 1.00);
		
		fil.add(fi);
		List<Trade> tr = RequestFactoryUtilsJpa.findList(Trade.class, fil,0L,100L);
		
		
		
		for (Trade account : tr) {
			System.out.println(account.getAccount().getName());
			
		}
		
	}
	
	@Override
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		Long li = RequestFactoryUtilsJpa.getLoggedInUserId();
		Trader tr = RequestFactoryUtilsJpa.find(Trader.class, li);
		

		return "Hello, " + tr.getEmail()+ "!<br><br>I am running " +tr.getAccounts().size() + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		
		
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
