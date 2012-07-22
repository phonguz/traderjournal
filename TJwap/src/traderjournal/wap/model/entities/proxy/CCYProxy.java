package traderjournal.wap.model.entities.proxy;

import traderjournal.wap.model.entities.Ccy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
@ProxyFor(Ccy.class)
public interface CCYProxy extends EntityProxy{
	 
	
	String getName();

	void setName(String name);
	Integer getVersion();
}
