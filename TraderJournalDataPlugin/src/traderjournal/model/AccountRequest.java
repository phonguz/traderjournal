package traderjournal.model;

import java.util.List;

import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.proxy.AccountProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
@Service(Account.class)
public interface AccountRequest extends RequestContext {
	
	Request<Long> countAccount();
	Request<List<AccountProxy>> findAllAccounts();
	
	
}
