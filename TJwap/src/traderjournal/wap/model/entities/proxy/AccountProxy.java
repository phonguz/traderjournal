package traderjournal.wap.model.entities.proxy;

import traderjournal.wap.model.entities.Account;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
@ProxyFor (Account.class)
public interface AccountProxy extends EntityProxy{
	
	String getName();
	double getBalance();
	CCYProxy getCcy();
	Integer getVersion();
	
	void setName(String name);
	void setBalance(double balance);
	void setCcy(CCYProxy ccy);

}
