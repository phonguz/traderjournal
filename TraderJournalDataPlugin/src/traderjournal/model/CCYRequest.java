package traderjournal.model;

import traderjournal.model.entities.Ccy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Ccy.class)
public interface CCYRequest extends RequestContext {
	Request<Long> countCCy();
	
}
