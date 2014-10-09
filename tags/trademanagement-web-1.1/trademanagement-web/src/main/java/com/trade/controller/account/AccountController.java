package com.trade.controller.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trade.helper.bean.AccountBean;
import com.trade.helper.validator.AccountValidator;
import com.trade.model.Accounts;
import com.trade.model.Ccy;
import com.trade.model.Sharedaccount;
import com.trade.model.Trade;
import com.trade.model.Trader;
import com.trade.model.Transaction;
import com.trade.model.Transactiontype;
import com.trade.service.HibernateEntityService;
import com.trade.util.ViewConstant;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	HibernateEntityService<Accounts> accountService;
	
	@Autowired
	HibernateEntityService<Ccy> ccyService;
	
	@Autowired
	HibernateEntityService<Trader> traderService;
	
	@Autowired
	HibernateEntityService<Trade> tradeService;

	@Autowired
	AccountValidator accountValidator;
	
	@Autowired
	HibernateEntityService<Transactiontype> transactionTypeService;
	
	@Autowired
	HibernateEntityService<Transaction> transactionService;
	
	@Autowired
	HibernateEntityService<Sharedaccount> sharedAccoutService;
	
	@RequestMapping(value="accountList", method={RequestMethod.GET,RequestMethod.POST})
	public String getAccountList(HttpServletRequest request, Model model) {
		List<Accounts> accountsList = null;
		List<Ccy> currencyList = null;
		List<Transactiontype> transactiontypeList = null;
		try {
			currencyList = ccyService.getDataList(new Ccy());
			if(currencyList!=null && currencyList.size()>0){
				model.addAttribute("currencyList", currencyList);
			}
			if("ADMIN".equals(request.getSession().getAttribute("traderType"))){
				accountsList = accountService.getDataList(new Accounts());
			}else{
				accountsList = accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
			}
			if(accountsList!=null && accountsList.size()>0){
				model.addAttribute("accountsList", accountsList);
			}
			transactiontypeList = transactionTypeService.getDataList(new Transactiontype());
			if(transactiontypeList != null && transactiontypeList.size()>0){
				model.addAttribute("transactiontypeList", transactiontypeList);
			}
			model.addAttribute("accountbean", new AccountBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViewConstant.ACCOUNT_PAGE;
	}
	
	@RequestMapping(value = "saveAccount", method = RequestMethod.POST)
	public String saveAccountData(@Validated@ModelAttribute("accountbean") AccountBean accountBean,BindingResult bindingResult,Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){

		Ccy ccy =null;
		List<Accounts> list = null;
		String moveTo="";
		List<Transactiontype> transactiontypeList = null;
		try {
			
			accountValidator.validate(accountBean, bindingResult);				
			if(bindingResult.hasErrors()){
				model.addAttribute("currencyList", ccyService.getDataList(new Ccy()));
//				model.addAttribute("accountsList", accountService.getDataList(new Accounts()));
				model.addAttribute("accountsList", accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString()));
				transactiontypeList = transactionTypeService.getDataList(new Transactiontype());
				if(transactiontypeList != null && transactiontypeList.size()>0){
					model.addAttribute("transactiontypeList", transactiontypeList);
				}
				moveTo = ViewConstant.ACCOUNT_PAGE;
			}else{
				int traderId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
				ccy = ccyService.getById(new Ccy(), Integer.parseInt(accountBean.getCcyId()));
				Trader trader = traderService.getById(new Trader(), traderId);

				if(!"".equals(accountBean.getName())){
					list = accountService.getByName(new Accounts(), "name", accountBean.getName());
				}
				
				if(list==null || list.size()<=0){
					if(ccy!=null && trader!=null){
						Transaction transaction = new Transaction();
						Accounts accounts = new Accounts();
						
						accounts.setName(accountBean.getName());
						accounts.setBalance(new BigDecimal(accountBean.getBalance()));
						accounts.setPercentRisk(new BigDecimal(accountBean.getPercentageRisk()));
						accounts.setLastupdtdate(new Date());
						accounts.setUpdatedby(traderId);
						accounts.setCcy(ccy);
						accounts.setTrader(trader);
						//accounts.getTransactions().add(transaction);
						
						Transactiontype transactiontype = transactionTypeService.getById(new Transactiontype(), Integer.parseInt(accountBean.getTransactionTypeId()));
						transaction.setTransactiontype(transactiontype);
						transaction.setLastupdtdate(new Date());
						transaction.setTransactionvalue(new BigDecimal(accountBean.getTransactionAmount()));
						transaction.setUpdatedby(traderId);
						transaction.setAccounts(accounts);
						
						accountService.add(accounts);
						transactionService.add(transaction);
						
						
						redirectAttributes.addFlashAttribute("success", "Account created successfully");
					}else{
						redirectAttributes.addFlashAttribute("error", "There was some error");
					}
				}else{
					redirectAttributes.addFlashAttribute("error", "Account already exist");
				}
				moveTo = "redirect:accountList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return moveTo;
	}
	
	@RequestMapping(value = "updateAccount", method = RequestMethod.POST)
	public String updateAccountData(@ModelAttribute("accountbean") AccountBean accountBean,BindingResult bindingResult,Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Ccy ccy =null;
		List<Accounts> list = null;
		try {
			accountValidator.validate(accountBean, bindingResult);				
			if(bindingResult.hasErrors()){
				model.addAttribute("currencyList", ccyService.getDataList(new Ccy()));
				model.addAttribute("accountsList", accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString()));
				model.addAttribute("update", true);
				return ViewConstant.ACCOUNT_PAGE;
			}else{
				int traderId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
				Accounts accounts = accountService.getById(new Accounts(), Integer.parseInt(accountBean.getId()));
				ccy = ccyService.getById(new Ccy(), Integer.parseInt(accountBean.getCcyId()));
				Trader trader = traderService.getById(new Trader(), traderId);

				if(accounts!=null && !accountBean.getName().equals(accounts.getName())){
					list = accountService.getByName(new Accounts(), "name", accountBean.getName());
				}
				
				if(list==null || list.size()<=0){
					if(accounts!=null && ccy!=null && trader!=null){
						Transaction transaction = new Transaction();
						accounts.setName(accountBean.getName());
						accounts.setBalance(new BigDecimal(accountBean.getBalance()));
						accounts.setPercentRisk(new BigDecimal(accountBean.getPercentageRisk()));
						accounts.setLastupdtdate(new Date());
						accounts.setUpdatedby(traderId);
						accounts.setCcy(ccy);
						if(!request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
							accounts.setTrader(trader);
						}
						//accounts.getTransactions().add(transaction);
						
						Transactiontype transactiontype = transactionTypeService.getById(new Transactiontype(), Integer.parseInt(accountBean.getTransactionTypeId()));
						transaction.setTransactiontype(transactiontype);
						transaction.setLastupdtdate(new Date());
						transaction.setTransactionvalue(new BigDecimal(accountBean.getTransactionAmount()));
						transaction.setUpdatedby(traderId);
						transaction.setAccounts(accounts);

						accountService.update(accounts);
						transactionService.add(transaction);
						redirectAttributes.addFlashAttribute("success", "Account updated successfully");
					}else{
						redirectAttributes.addFlashAttribute("error", "There was some error");
					}
				}else{
					redirectAttributes.addFlashAttribute("error", "Account already exist");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:accountList";
	}
	
	@RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
	public String deleteAccountData(HttpServletRequest request, RedirectAttributes attributes){
		Accounts accounts = null;
		try {
			int accountId = "".equals(request.getParameter("id")) ? 0 : Integer.parseInt(request.getParameter("id"));
			if(accountId != 0){
				accounts = accountService.getById(new Accounts(), accountId);
			}
			if(accounts!=null){
				List<Trade> tradeList = tradeService.getByName(new Trade(), "accounts_id", accounts.getId());
				if(tradeList != null && tradeList.size()>0){
					attributes.addFlashAttribute("error", "Cannot delete, Account is associated with Trade");
				}else{
					accountService.remove(accounts, accountId);
					attributes.addFlashAttribute("success", "Account deleted succesfully");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:accountList";
	}
	
	@RequestMapping(value="/userByLikeName", method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String userByLikeName(HttpServletRequest request) throws JSONException{
		JSONArray traderJSONArray = new JSONArray();
		try{
			int traderId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			List<Trader> traderList = traderService.getByLikeName(new Trader(), "firstname", request.getParameter("uid"));
			List<Sharedaccount> shareList = sharedAccoutService.getByName(new Sharedaccount(), "trader_id", traderId);
			if(shareList != null && shareList.size() > 0){
				for(int i=0; i < shareList.size(); i++){
					for(int j=0; j<traderList.size(); j++){
						if(traderList.get(j).getType().equals(ViewConstant.ADMIN) || traderList.get(j).getId().equals(traderId)){
							traderList.remove(j);
						}else if(shareList.get(i).getAccounts().getId().equals(accountId) && traderList.get(j).getId().equals(shareList.get(i).getTraderByShareTraderId().getId())){
							traderList.remove(j);
						}
					}
				}
			}else{
				for(int i = 0; i<traderList.size(); i++){
					if(traderList.get(i).getType().equals(ViewConstant.ADMIN) || traderList.get(i).getId().equals(traderId)){
						traderList.remove(i);
					}
				}
			}
			for(Trader trader : traderList){
				JSONObject json = new JSONObject();
				json.put("id", trader.getId());
				json.put("firstName", trader.getFirstname());
				json.put("lastName", trader.getLastname());
				json.put("userName", trader.getUsername());
				json.put("lastUpdatedDate", trader.getLastupdtdate());
				json.put("type", trader.getType());
				json.put("updatedBy", trader.getUpdatedby());
				traderJSONArray.put(json);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return traderJSONArray.toString();
	}
	
	@RequestMapping(value="/shareAccount", method={RequestMethod.POST})
	public @ResponseBody String shareAccount(HttpServletRequest request){
		String result = "fail";
		Sharedaccount sharedaccount = new Sharedaccount();
		Accounts account = new Accounts();
		Trader sharedTrader = new Trader();
		Trader loggedUser = new Trader();
		try{
			int traderId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
			loggedUser = traderService.getById(new Trader(), traderId);
			account = accountService.getById(new Accounts(), Integer.parseInt(request.getParameter("accountId")));
			sharedTrader = traderService.getById(new Trader(), Integer.parseInt(request.getParameter("sharedTraderId")));
			
			sharedaccount.setTraderByShareTraderId(sharedTrader);
			sharedaccount.setTraderByTraderId(loggedUser);
			sharedaccount.setAccounts(account);
			sharedAccoutService.add(sharedaccount);
			result = "success";
		}catch(Exception ex){
			ex.printStackTrace();
			result = "fail";
		}
		return result;
	}
}
