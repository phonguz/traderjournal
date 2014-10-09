package com.trade.service;

import javax.servlet.http.HttpServletRequest;

 
import com.trade.model.Trader;

public interface AuthenticationService {

	public boolean isAuthenticate(String username,String password,HttpServletRequest request);
	
	public boolean resetPassword(Trader users,String password);
	
	
}
