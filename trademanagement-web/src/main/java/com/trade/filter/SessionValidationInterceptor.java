package com.trade.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionValidationInterceptor extends HandlerInterceptorAdapter{
	
private static final String [] IGNORE_URI ={"index.jsp","Login","Trader/signup","Trader/save","admin","account",".xml",".jpg",".jpeg",".png",".gif",".css",".js",".ico"};
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("Inside preHandle()" + request.getRequestURI());
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //Forces caches to obtain a new copy of the page from the origin server  
																					//Directs caches not to store the page under any circumstance  
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"  
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
		
		String requestURI = request.getRequestURI();
		for (String uri : SessionValidationInterceptor.IGNORE_URI) {
			if(requestURI.contains(uri)){
				return true;
			}		
		 	
		}
		String username = (String) request.getSession().getAttribute("username");
		if (username == null ) {
			response.sendRedirect(request.getContextPath()+"/Login");
	 		return false;
	 	}
		return true;
	}

}