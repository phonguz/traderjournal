package traderjournal.model.entities.proxy;

import traderjournal.model.entities.Account;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
@ProxyFor (Account.class)
public interface AccountProxy extends EntityProxy{
	String getName();
	Double getBalance();
	CCYProxy getBaseCCY();
	
	void setName(String name);
	void setBalance(Double balance);
	void setCCY(CCYProxy ccy);

}
