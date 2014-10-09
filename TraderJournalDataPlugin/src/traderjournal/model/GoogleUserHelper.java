package traderjournal.model;

import java.util.ArrayList;
import java.util.logging.Logger;


import traderjournal.model.entities.Trader;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleUserHelper {
	private static final Logger log = Logger.getLogger(GoogleUserHelper.class.getName());
	 public static Trader findLoggedInTrader() {
		    UserService userService = UserServiceFactory.getUserService();
		    if (userService.isUserLoggedIn() == false) {
		      log.info("GoogleUserHelper.findLoggedInTrader(): info: trader is not logged in");
		      return null;
		    }
		    
		    User user = userService.getCurrentUser();
		    String googleUserId = user.getUserId();
		    
		    
		    return GoogleUserHelper.findTraderByGoogleUserId(googleUserId);
		  }

	  /**
	   * get Google User
	   * @return
	   */
	  public static User getGoogleUser() {
	    UserService userService = UserServiceFactory.getUserService();
	    if (userService.isUserLoggedIn() == false) {
	      return null;
	    }
	    User user = userService.getCurrentUser();
	    return user;
	  }
	  
		public static Trader findTraderByGoogleUserId(String googleuserid){
			
			
			Filter useridfilter = new Filter("googleuserid",FilterOperator.EQUAL,googleuserid);
		    ArrayList<Filter> myf = new ArrayList<Filter>();
		    myf.add(useridfilter);
		    // did we save this user in db yet?
		    java.util.List<Trader> traderList = null;
		    try {
		      traderList = RequestFactoryUtilsJpa.findList(Trader.class, myf, 0, 1);
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }

		    
		    return traderList.get(0);
		}
	 
}
