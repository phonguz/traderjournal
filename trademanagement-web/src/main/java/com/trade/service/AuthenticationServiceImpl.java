package com.trade.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trade.model.Trader;
import com.trade.util.Base64;
import com.trade.util.ViewConstant;


@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

	
	@Autowired
	private SessionFactory factory;
	
	public boolean isAuthenticate(String username, String password,HttpServletRequest request) {
		boolean flag = false;
		String encPassword = Base64.base64encode(Base64.xorMessage(password, ViewConstant.PASSWORD_KEY));
		//String passwordToHash = DigestUtils.md5DigestAsHex(password.getBytes());
	 
		StringBuilder queryString = new StringBuilder();
		queryString.append(" from Trader as t ");
		queryString.append(" where t.username = ? ");
		queryString.append(" and t.password = ? ");
		
		Query query = factory.getCurrentSession().createQuery(queryString.toString());
		query.setString(0, username);
		query.setString(1, encPassword);
		
		@SuppressWarnings("unchecked")
		List<Trader> traderList = query.list();
		if(traderList != null && traderList.size() > 0){
			request.getSession().setAttribute("loggedUserId", traderList.get(0).getId());
			request.getSession().setAttribute("traderType", traderList.get(0).getType());
			request.getSession().setAttribute("firstName", traderList.get(0).getFirstname());
			request.getSession().setAttribute("trader", traderList.get(0));
			flag=true;
		}
		return flag;
	}


	@Override
	public boolean resetPassword(Trader user,String password) {
		 
		boolean msz=true;
		try {
			user.setPassword(password);
			user.setTmppassword((byte)1);
			factory.getCurrentSession().saveOrUpdate(user);
		} catch (Exception e) {
			msz = false;
		}
		return msz;
	}

}
