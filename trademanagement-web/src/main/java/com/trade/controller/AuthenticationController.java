package com.trade.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 






 

 
import com.trade.model.Trader;
import com.trade.service.AuthenticationService;
import com.trade.service.HibernateEntityService;
import com.trade.util.Base64;
import com.trade.util.GmailHelper;
import com.trade.util.ViewConstant;

@Controller
@RequestMapping("/Login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	HibernateEntityService<Trader> traderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null){
			return "redirect:/trade";
		}
		return ViewConstant.LOGIN_PAGE;
	}
	
	@RequestMapping(method =  RequestMethod.POST)
	public String authenticate(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginFailMsg = null;
		String viewPage = ViewConstant.LOGIN_PAGE;
		if(username !=null && !username.equalsIgnoreCase("")){
			if(password !=null && !password.equalsIgnoreCase("")){
				if(authenticationService.isAuthenticate(username, password, request)){
					Trader user =(Trader) request.getSession().getAttribute("trader");
					byte temppassFlage = user.getTmppassword();
					if(temppassFlage==(byte)1){
						model.addAttribute("user", user.getUsername());
						viewPage = ViewConstant.CONFIRM_PASSWORD;
					}else{
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						viewPage = "redirect:/trade";
							 
						}
						
					
				}else{
					loginFailMsg = "Email or Password you have entered is incorrect";
				}
			}else{
				loginFailMsg = "Password cannot be empty";
			}			
		}else{
			loginFailMsg = "Email address cannot be empty";
		}
		model.addAttribute("loginFailMsg", loginFailMsg);		
		return viewPage;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		return ViewConstant.LOGIN_PAGE;
	}
	
	
	
	 @RequestMapping("/forgotPassword")
	public String forgotPassword(HttpServletRequest request,HttpServletResponse response,Model model){
		boolean done=false;
		
		
		String name = request.getParameter("userName");
		 
		if(name.trim().equalsIgnoreCase("")){
			model.addAttribute("msg", "username is required.");
		}else{
			model.addAttribute("msg", "User is not available.");
			
			
			Properties prop = new Properties();
			File file = new File(request.getServletContext().getRealPath("/properties/mail.properties"));
			InputStream inputStream;
			try {
				inputStream = new FileInputStream(file);
				prop.load(inputStream);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			List<Trader> users = traderService.getByName(new Trader(), "username", name);
			model.addAttribute("msg", "User is not available.");
			
			if(users!=null && users.size()>0){
				Trader user = users.get(0);
			    String password = Long.toHexString(new Date().getTime());
				String encPassword = Base64.base64encode(Base64.xorMessage(password, ViewConstant.PASSWORD_KEY));
				boolean ret = authenticationService.resetPassword(user,encPassword);
				
				if(ret){
					try {
						String email = user.getUsername();
						String body ="A request has been received to reset your password for the TradeManagement system. Your new password is <b>"+password+"</b>. You will be asked to change this value the next time you log in.<br/>";
						body += "If you did not request this change, Please contact us at admin@trademanagement.com";
						GmailHelper.getPreConfigured(prop).sendEmail(email,"TradeManagement Access Detail",body);
						model.addAttribute("msg", "Password sent successfully to "+users.get(0).getUsername());
						done=true;
					} catch (Exception e) {
						model.addAttribute("msg", "Password sending action failed!");
						e.printStackTrace();
						
					}
				}
				else{
					model.addAttribute("msg", "Password sending action failed!");
				}
			}else{
				model.addAttribute("msg", "The Username you entered is incorrect.");
			}
		}
		model.addAttribute("done",done);
		return ViewConstant.FORGOT_PASSWORD;
	}
	 
	 
	 @RequestMapping(value="/changePassword",method = RequestMethod.POST)
		public String changePassword(HttpServletRequest request,HttpServletResponse response,Model model){
			
			String password = request.getParameter("chPassword");
			String confirmpassword = request.getParameter("chConfirmpassword");
			String username = request.getParameter("user");
			model.addAttribute("user", username);
			int id1=Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
			String loginFailMsg = null;
			String viewPage =  ViewConstant.CONFIRM_PASSWORD;
			if( (password != null && confirmpassword != null) && (!password.equalsIgnoreCase("") && !confirmpassword.equalsIgnoreCase("")) ){
				
				if(password.equals(confirmpassword)){
					List<Trader> users = traderService.getByName(new Trader(), "username", username);
					if(users!=null && users.size()>0){
						Trader user = users.get(0);
						String encPassword = Base64.base64encode(Base64.xorMessage(password, ViewConstant.PASSWORD_KEY));
						user.setPassword(encPassword);
						user.setTmppassword((byte)0);
						user.setId(id1);
						traderService.update(user);
						viewPage = "redirect:/";
					}else {
						loginFailMsg = "Either Username/Password is incorrect or Invalidated.";
					}
				}else{
					loginFailMsg = "New Password and Confirm Password does not match.";
				}
				
			}else{
				loginFailMsg = "UserName or Password cannot be empty";
			}
			model.addAttribute("loginFailMsg", loginFailMsg);
			return viewPage;
			
		}
	 
	
	
}
