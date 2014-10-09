package traderjournal.model.entities.proxy;

import traderjournal.model.entities.Ccy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
@ProxyFor(Ccy.class)
public interface CCYProxy extends EntityProxy{
	 
	Integer getID();
	String getName();
	void setID(Integer id);
	void setName(String name);

}
