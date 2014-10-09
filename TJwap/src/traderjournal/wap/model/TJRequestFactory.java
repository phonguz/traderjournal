package traderjournal.wap.model;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface TJRequestFactory extends RequestFactory {
	
	CCYRequest ccyRequest() ;
	AccountRequest accountRequest(); 

}
