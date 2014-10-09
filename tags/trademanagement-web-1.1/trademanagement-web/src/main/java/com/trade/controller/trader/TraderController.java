package com.trade.controller.trader;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trade.helper.bean.TradeBean;
import com.trade.helper.bean.TraderBean;
import com.trade.helper.validator.GeneralValidator;
import com.trade.helper.validator.TraderValidator;
import com.trade.model.Accounts;
import com.trade.model.Trade;
import com.trade.model.Tradeattachment;
import com.trade.model.Tradeevent;
import com.trade.model.Tradeeventtype;
import com.trade.model.Trader;
import com.trade.model.Transaction;
import com.trade.model.Transactiontype;
import com.trade.service.HibernateEntityService;
import com.trade.util.Base64;
import com.trade.util.ErrorConstant;
import com.trade.util.ObjectErrorFactory;
import com.trade.util.QueryConstant;
import com.trade.util.ViewConstant;

@Controller
@RequestMapping("/Trader")
public class TraderController {
	
	private boolean updatePass=true;
	
	@Autowired
	HibernateEntityService<Trader> traderService;
	
	@Autowired
	HibernateEntityService<Tradeeventtype> tradeEventTypeService;

	@Autowired
	HibernateEntityService<Transactiontype> transactionTypeService;

	@Autowired
	HibernateEntityService<Tradeattachment> tradeAttachmentService;
	
	@Autowired
	HibernateEntityService<Tradeevent> tradeEventService;
	
	@Autowired
	HibernateEntityService<Transaction> transactionService;
	
	@Autowired
	HibernateEntityService<Accounts> accountsService;
	
	@Autowired
	HibernateEntityService<Trade> tradeService;
	
	@Autowired
	TraderValidator commonValidator;
	
	@RequestMapping(value =  "/signup", method = {RequestMethod.GET,RequestMethod.POST})
	public String createUser(@ModelAttribute("traderBean") TraderBean traderBean) {
		return ViewConstant.UPDATETRADER;
	}
	
	@RequestMapping(value =  "/save", method = {RequestMethod.GET,RequestMethod.POST})
	public String addTrader(@ModelAttribute("traderBean") TraderBean traderBean,BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes,HttpServletRequest request){
		String returns = null;
		if(request.getMethod().equals("POST")){
				Trader trader = new Trader();
				if(!traderBean.isUpdateMode()){
					try{
						validateTrader(traderBean,bindingResult, false,request);
						List<Trader> traderList = new ArrayList<Trader>();
						traderList = traderService.getByName(new Trader(), "username", traderBean.getUsername());
						if(traderList.size() > 0){
							ObjectError error = ObjectErrorFactory.getObject("traderExistingException", ErrorConstant.USER_ALREDY_EXIST);
							bindingResult.addError(error);
						}
						if(bindingResult.hasErrors()){
							return ViewConstant.UPDATETRADER;
						}
						String passwordToHash = Base64.base64encode(Base64.xorMessage(traderBean.getPassword(), ViewConstant.PASSWORD_KEY));
						//String passwordToHash = DigestUtils.md5DigestAsHex(traderBean.getPassword().getBytes());
						trader.setUsername(traderBean.getUsername());
						trader.setFirstname(traderBean.getFirstname());
						trader.setLastname(traderBean.getLastname());
						trader.setPassword(passwordToHash);
						trader.setLastupdtdate(new Timestamp(new Date().getTime()));
						trader.setUpdatedby(null);
						trader.setType(ViewConstant.USER);
						trader.setAccountses(null);
						trader.setTrades(null);
						traderService.add(trader);
						List<Trader> tradersList = traderService.getByName(new Trader(), "username", traderBean.getUsername());
						Trader tempTrader = new Trader();
						tempTrader = tradersList.get(0);
						tempTrader.setUpdatedby(tempTrader.getId());
						traderService.update(tempTrader);
						if(request.getSession().getAttribute("traderType") !=null){
							if(!"ADMIN".equalsIgnoreCase(request.getSession().getAttribute("traderType").toString())){
								createSession(request, traderBean.getUsername(), tempTrader.getId(), tempTrader.getType(), tempTrader.getFirstname());
							}
						}else{
							createSession(request, traderBean.getUsername(), tempTrader.getId(), tempTrader.getType(), tempTrader.getFirstname());
						}
						model.addAttribute("SUCCESS_MSG", "Trader created successfully");
						if(request.getSession().getAttribute("username")==null){
							return ViewConstant.LOGIN_PAGE;
						}else{
							redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trader created successfully");
							return "redirect:/trade";
							//return "redirect:/Trader/gettraders";
							
						}
					}catch(Exception e){
						e.printStackTrace();
						ObjectError error = ObjectErrorFactory.getObject("traderException", ErrorConstant.TRADER_ERROR);
						bindingResult.addError(error);
						return ViewConstant.UPDATETRADER;
					}
				}else if(traderBean.isUpdateMode()){
					validateTrader(traderBean,bindingResult, true,request);
					List<Trader> tradersList = new ArrayList<Trader>();
					if(!request.getSession().getAttribute("oldUserName").toString().equalsIgnoreCase(traderBean.getUsername())){
						tradersList = traderService.getByName(new Trader(), "username", traderBean.getUsername());
						if(tradersList.size() > 0){
							ObjectError error = ObjectErrorFactory.getObject("traderExistingException", ErrorConstant.USER_ALREDY_EXIST);
							bindingResult.addError(error);
						}
					}
					if(bindingResult.hasErrors()){
						return ViewConstant.UPDATETRADER;
					}
					try{
						trader.setId(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
						trader.setUsername(traderBean.getUsername());
						trader.setFirstname(traderBean.getFirstname());
						trader.setLastname(traderBean.getLastname());
						if(updatePass){
							String passwordToHash = Base64.base64encode(Base64.xorMessage(traderBean.getPassword(), ViewConstant.PASSWORD_KEY));
							//String passwordToHash = DigestUtils.md5DigestAsHex(traderBean.getPassword().getBytes());
							trader.setPassword(passwordToHash);
						}else{
							trader.setPassword(request.getSession().getAttribute("oldPass").toString());
							request.getSession().removeAttribute("oldPass");
						}
						trader.setLastupdtdate(new Timestamp(new Date().getTime()));
						trader.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
						trader.setType(request.getSession().getAttribute("userType").toString());
						trader.setAccountses(null);
						trader.setTrades(null);
						traderService.update(trader);
						redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trader updated successfully");
						request.getSession().removeAttribute("userId");
						traderBean.setUpdateMode(false);
						request.getSession().removeAttribute("userType");
						request.getSession().removeAttribute("oldUserName");
						updatePass=true;
						if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
							returns = "redirect:/Trader/gettraders";
						}else{
							return "redirect:/trade";
						}
					}catch(Exception e){
						ObjectError error = ObjectErrorFactory.getObject(
								"traderException", ErrorConstant.TRADER_UPDATE_ERROR);
						bindingResult.addError(error);
						return ViewConstant.UPDATETRADER;
					}
				}else{
					ObjectError error = ObjectErrorFactory.getObject(
							"traderException", ErrorConstant.UNKNOWN_ERROR);
					bindingResult.addError(error);
					return ViewConstant.UPDATETRADER;
				}
		}
		return returns;
	}
	
	
	@RequestMapping(value = "/gettraders", method = RequestMethod.GET)
	public String getTraders(Model model,HttpServletRequest request){
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			List<Trader> tradersList = traderService.getDataList(new Trader());			
			Collections.reverse(tradersList);		
			model.addAttribute("tradersList", tradersList);
			return ViewConstant.MANAGETRADER_HOME;
		}else{
			request.getSession().removeAttribute("userId");
			//traderBean.setUpdateMode(false);
			request.getSession().removeAttribute("userType");
			request.getSession().removeAttribute("oldUserName");
			return "redirect:/";
		}
	}
	
/*	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeTraders(final RedirectAttributes redirectAttributes,HttpServletRequest request){
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			traderService.remove(new Trader(),Integer.parseInt(request.getParameter("refId")));
			redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trader deleted successfully");
			return "redirect:/Trader/gettraders";
		}else{
			return "redirect:/";
		}
	}*/
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeTraders(final RedirectAttributes redirectAttributes,HttpServletRequest request){
		int traderId = Integer.parseInt(request.getParameter("refId"));
		Trader trader = traderService.getById(new Trader(), traderId);
		if(!"ADMIN".equals(trader.getType())){
			List<Accounts> accountsList = accountsService.getByName(new Accounts(), "trader_id", traderId);
			if(accountsList!=null && accountsList.size()>0){
				List<Trade> tradeList = tradeService.getByName(new Trade(), "trader_id", traderId);
				if(tradeList!=null && tradeList.size()>0){
					for(Trade trade : tradeList){
						List<Tradeevent> tradeEventList = tradeEventService.getByName(new Tradeevent(), "trade_id", trade.getId());
						if(tradeEventList != null && tradeEventList.size()>0){
							//Delete from trade event
							for(Tradeevent tEvent : tradeEventList){
								tradeEventService.deleteQuery(QueryConstant.DELETE_FROM_TRADEEVENT_BY_TRADE_ID, tEvent.getId());
							}
						}else{
							//Delete from trade service
							tradeService.remove(new Trade(), traderId);
						}
					}
				}
				//Delete from account
				accountsService.deleteQuery(QueryConstant.DELETE_FROM_ACCOUNTS_BY_TRADER_ID, traderId);
			}
			//Delete from trader
			traderService.remove(new Trader(),traderId);
			redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trader deleted successfully");	
		}
		return "redirect:/Trader/gettraders";
	}

	@RequestMapping(value = "/updatetrader", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateTrader(@ModelAttribute("traderBean") TraderBean traderBean,HttpServletRequest request,Model model){
		Trader tradersObj = new Trader();
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			tradersObj = traderService.getById(new Trader(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
			request.getSession().setAttribute("userId", request.getParameter("refId"));
		}else{
			tradersObj = traderService.getById(new Trader(), Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
			request.getSession().setAttribute("userId", request.getSession().getAttribute("loggedUserId"));
		}
		request.getSession().setAttribute("oldPass", tradersObj.getPassword());
		request.getSession().setAttribute("userType", tradersObj.getType());
		request.getSession().setAttribute("oldUserName", tradersObj.getUsername());
		traderBean.setUsername(tradersObj.getUsername());
		traderBean.setFirstname(tradersObj.getFirstname());
		traderBean.setLastname(tradersObj.getLastname()); 
		traderBean.setUpdateMode(true);
		//model.addAttribute("updateMod", true);
		return ViewConstant.UPDATETRADER;
	}
	
	@RequestMapping(value = "/updateprofile", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateProfile(@ModelAttribute("traderBean") TraderBean traderBean,HttpServletRequest request,Model model){
		Trader tradersObj = new Trader();
		/*if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			tradersObj = traderService.getById(new Trader(), Integer.parseInt(request.getParameter("refId")));
			request.getSession().setAttribute("userId", request.getParameter("refId"));
		}else{*/
			tradersObj = traderService.getById(new Trader(), Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
			request.getSession().setAttribute("userId", request.getSession().getAttribute("loggedUserId"));
		//}
		request.getSession().setAttribute("oldPass", tradersObj.getPassword());
		request.getSession().setAttribute("userType", tradersObj.getType());
		request.getSession().setAttribute("oldUserName", tradersObj.getUsername());
		traderBean.setUsername(tradersObj.getUsername());
		traderBean.setFirstname(tradersObj.getFirstname());
		traderBean.setLastname(tradersObj.getLastname()); 
		traderBean.setUpdateMode(true);
		//model.addAttribute("updateMod", true);
		return ViewConstant.UPDATETRADER;
	}
	
	public void validateTrader(TraderBean traderBean,BindingResult bindingResult,boolean update,HttpServletRequest request){
		if(update){
			if((traderBean.getOldPassword().equalsIgnoreCase("") || traderBean.getOldPassword() == null) &&	 
				(traderBean.getPassword().equalsIgnoreCase("") || traderBean.getPassword() == null ) &&
				(traderBean.getConfirmPassword().equalsIgnoreCase("") || traderBean.getConfirmPassword() == null)){
					traderBean.setPassword("test");
					traderBean.setConfirmPassword("test");
					updatePass=false;
			}else if((!traderBean.getOldPassword().equalsIgnoreCase("") || traderBean.getOldPassword() != null) &&	 
					(!traderBean.getPassword().equalsIgnoreCase("") || traderBean.getPassword() != null ) &&
					(!traderBean.getConfirmPassword().equalsIgnoreCase("") || traderBean.getConfirmPassword() != null)){
							/*if(traderBean.getPassword().length()<=8 && traderBean.getConfirmPassword().length()<=8){
								ObjectError error = ObjectErrorFactory.getObject("traderExistingException", "Minimum 8 character required for password");
								bindingResult.addError(error);
							 }*/
							String oldPasswordToHash = Base64.base64encode(Base64.xorMessage(traderBean.getOldPassword(), ViewConstant.PASSWORD_KEY));
							//String oldPasswordToHash = DigestUtils.md5DigestAsHex(traderBean.getOldPassword().getBytes());
							if(!oldPasswordToHash.equalsIgnoreCase(request.getSession().getAttribute("oldPass").toString())){
								ObjectError error = ObjectErrorFactory.getObject("passwordException", ErrorConstant.OLD_PASS_NOT_MATCH_ERROR);
								bindingResult.addError(error);
							}
			}else{
				ObjectError error = ObjectErrorFactory.getObject("passwordException", ErrorConstant.ALL_PASS_REQUIRED_ERROR);
				bindingResult.addError(error);
			}			
		}else{
			if((!traderBean.getPassword().equalsIgnoreCase("") || traderBean.getPassword() != null ) &&
					(!traderBean.getConfirmPassword().equalsIgnoreCase("") || traderBean.getConfirmPassword() != null)){
							/*if(traderBean.getPassword().length()<=8 && traderBean.getConfirmPassword().length()<=8){
								ObjectError error = ObjectErrorFactory.getObject("traderExistingException", "Minimum 8 character required for password");
								bindingResult.addError(error);
							 }*/
			}
		}
		commonValidator.validate(traderBean, bindingResult);
	}
	
	private void createSession(HttpServletRequest request, String username, int userId, String type, String firstName){

		try {
			request.getSession().setAttribute("loggedUserId", userId);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("traderType", type);
			request.getSession().setAttribute("firstName", firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value="eventTypeList", method=RequestMethod.GET)
	public String getTradeEventTypeList(HttpServletRequest request, Model model){
		List<Trader> traderList = null;
		List<Tradeeventtype> tradeEventTypeList = null;
		try {
			String traderUserName = request.getSession().getAttribute("username").toString();
			if(!"".equals(traderUserName)){
				traderList = traderService.getByName(new Trader(), "username", traderUserName);	
			}
			if(traderList!=null && traderList.size()>0){
				if("ADMIN".equals(request.getSession().getAttribute("traderType"))){
					tradeEventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
				}else{
					tradeEventTypeList = tradeEventTypeService.getByName(new Tradeeventtype(), "updatedby", traderList.get(0).getId());
				}
				
			}
			model.addAttribute("tradeEventTypeList", tradeEventTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ViewConstant.TRADEEVENTTYPE_PAGE;
	}
	
	@RequestMapping(value="saveEventType", method=RequestMethod.POST)
	public String addEventTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Tradeeventtype tradeEventType = null;
		List<Tradeeventtype> list = null;
		try {
			String eventTypeName = request.getParameter("eventTypeName");
			
			if(!"".equals(eventTypeName)){
				list = tradeEventTypeService.getByName(new Tradeeventtype(), "name", eventTypeName);
			}
			
			if(!"".equals(eventTypeName) && (list==null || list.size()<=0)){
				if(GeneralValidator.isValidEventTypeName(eventTypeName)){
					tradeEventType = new Tradeeventtype();
					tradeEventType.setName(eventTypeName);
					tradeEventType.setLastupdtdate(new Date());
					tradeEventType.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					tradeEventTypeService.add(tradeEventType);
					redirectAttributes.addFlashAttribute("success","Event type created Successfully");
				}else{
					redirectAttributes.addFlashAttribute("error","Invalid Event type");					
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Event type already exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:eventTypeList";
	}
	
	@RequestMapping(value="updateEventType", method=RequestMethod.POST)
	public String updateEventTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Tradeeventtype tradeEventType = null;
		List<Tradeeventtype> list = null;
		try {
			String eventTypeId = request.getParameter("eventId");
			String eventTypeName = request.getParameter("eventTypeName");
			
			if(!"".equals(eventTypeId)){
				tradeEventType = tradeEventTypeService.getById(new Tradeeventtype(), Integer.parseInt(eventTypeId));
			}
			
			if(tradeEventType!=null && !eventTypeName.equals(tradeEventType.getName())){
				list = tradeEventTypeService.getByName(new Tradeeventtype(), "name", eventTypeName);
			}
			
			if(!"".equals(eventTypeName) && (list==null || list.size()<=0)){
				if(GeneralValidator.isValidEventTypeName(eventTypeName)){
					tradeEventType.setName(eventTypeName);
					tradeEventType.setLastupdtdate(new Date());
					tradeEventType.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					tradeEventTypeService.update(tradeEventType);
					redirectAttributes.addFlashAttribute("success","Event type updated Successfully");
				}else{
					redirectAttributes.addFlashAttribute("error","Invalid Event type");					
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Event type already exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:eventTypeList";
	}
	
	@RequestMapping(value="deleteEventType", method=RequestMethod.POST)
	public String deleteEventTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Tradeeventtype tradeEventType = null;
		try {
			
			String eventTypeId = request.getParameter("eventId");
			
			if(!"".equals(eventTypeId)){
				tradeEventType = tradeEventTypeService.getById(new Tradeeventtype(), Integer.parseInt(eventTypeId));
			}
			if(tradeEventType != null){
				List<Tradeevent> eventList = tradeEventService.getByName(new Tradeevent(), "tradeeventtype_id", tradeEventType.getId());
				if(eventList != null && eventList.size()>0){
					redirectAttributes.addFlashAttribute("error","Cannot delete, Event Type is associated with event");
				}else{
					tradeEventTypeService.remove(tradeEventType, Integer.parseInt(eventTypeId));
					redirectAttributes.addFlashAttribute("success","Event type deleted Successfully");
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Error in deleting data");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:eventTypeList";
	}

	@RequestMapping(value="transactionTypeList", method=RequestMethod.GET)
	public String getTransactionTypeList(HttpServletRequest request, Model model){
		List<Trader> traderList = null;
		List<Transactiontype> transactionTypeList = null;
		try {
			String traderUserName = request.getSession().getAttribute("username").toString();
			if(!"".equals(traderUserName)){
				traderList = traderService.getByName(new Trader(), "username", traderUserName);
			}
			if(traderList!=null && traderList.size()>0){
				if("ADMIN".equals(request.getSession().getAttribute("traderType"))){
					transactionTypeList = transactionTypeService.getDataList(new Transactiontype());
				}else{
					transactionTypeList = transactionTypeService.getByName(new Transactiontype(), "updatedby", traderList.get(0).getId());
				}
			}
			model.addAttribute("transactionTypeList", transactionTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ViewConstant.TRANSACTIONTYPE_PAGE;
	}

	@RequestMapping(value="saveTransactionType", method=RequestMethod.POST)
	public String addTransactionTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Transactiontype transactionType = null;
		List<Transactiontype> list = null;
		try {
			String transactionTypeName = request.getParameter("transactionType");
			String transactionTypeAction = request.getParameter("transactionTypeAction");
			
			if(!"".equals(transactionTypeName)){
				list = transactionTypeService.getByName(new Transactiontype(), "type", transactionTypeName);
			}
			
			if(!"".equals(transactionTypeName) && (list==null || list.size()<=0)){
				if(GeneralValidator.isValidEventTypeName(transactionTypeName)){
					transactionType = new Transactiontype();
					transactionType.setAction(transactionTypeAction);
					transactionType.setType(transactionTypeName);
					transactionType.setLastupdtdate(new Date());
					transactionType.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					transactionTypeService.add(transactionType);
					redirectAttributes.addFlashAttribute("success","Transaction type created Successfully");
				}else{
					redirectAttributes.addFlashAttribute("error","Invalid Transaction type");					
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Transaction type already exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:transactionTypeList";
	}

	@RequestMapping(value="updateTransactionType", method=RequestMethod.POST)
	public String updateTransactionTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Transactiontype transactionType = null;
		List<Transactiontype> list = null;
		try {
			String transactionTypeId = request.getParameter("transactionTypeId");
			String transactionTypeName = request.getParameter("transactionType");
			String transactionTypeAction = request.getParameter("transactionTypeAction");
			
			if(!"".equals(transactionTypeId)){
				transactionType = transactionTypeService.getById(new Transactiontype(), Integer.parseInt(transactionTypeId));
			}
			
			if(transactionType!=null && !transactionTypeName.equals(transactionType.getType())){
				list = transactionTypeService.getByName(new Transactiontype(), "type", transactionTypeName);
			}
			
			if(!"".equals(transactionTypeName) && (list==null || list.size()<=0)){
				if(GeneralValidator.isValidEventTypeName(transactionTypeName)){
					transactionType.setType(transactionTypeName);
					transactionType.setAction(transactionTypeAction);
					transactionType.setLastupdtdate(new Date());
					transactionType.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					transactionTypeService.update(transactionType);
					redirectAttributes.addFlashAttribute("success","Transaction type updated Successfully");
				}else{
					redirectAttributes.addFlashAttribute("error","Invalid Transaction type");					
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Transaction type already exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:transactionTypeList";
	}

	@RequestMapping(value="deleteTransactionType", method=RequestMethod.POST)
	public String deleteTransactionTypeData(HttpServletRequest request, RedirectAttributes redirectAttributes){
		
		Transactiontype transactionType = null;
		try {
			String transactionTypeId = request.getParameter("transactionTypeId");
			if(!"".equals(transactionTypeId)){
				transactionType = transactionTypeService.getById(new Transactiontype(), Integer.parseInt(transactionTypeId));
			}
			if(transactionType != null){
				List<Transaction> transactionList = transactionService.getByName(new Transaction(), "transactiontype_id", transactionType.getId());
				
				if(transactionList != null && transactionList.size()>0){
					redirectAttributes.addFlashAttribute("error","Cannot delete, Transaction type is associated with Transaction");
				}else{
					transactionTypeService.remove(transactionType, Integer.parseInt(transactionTypeId));
					redirectAttributes.addFlashAttribute("success","Transaction type deleted Successfully");
				}
			}else{
				redirectAttributes.addFlashAttribute("error","Error in deleting data");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:transactionTypeList";
	}
	
	//Start Trade Event Attachment
	
	@RequestMapping(value="eventAttachment", method=RequestMethod.GET)
	public String getEventAttachmentData(HttpServletRequest request, Model model){
		String userId = "";
		List<Tradeattachment> tradeAttachemnetlist = null;
		try {
			userId = request.getSession().getAttribute("loggedUserId").toString();
			if(!"".equals(userId)){
				tradeAttachemnetlist = tradeAttachmentService.getDataList(new Tradeattachment());
			}
			if(tradeAttachemnetlist!=null && tradeAttachemnetlist.size()>0){
				model.addAttribute("tradeAttachemnetlist", tradeAttachemnetlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViewConstant.TRADEATTACHMENT_HOME;
	}

	@RequestMapping(value="saveEventAttachment", method=RequestMethod.POST)
	public void saveEventAttachmentData(@RequestParam("file") MultipartFile file, @ModelAttribute("tradeBean") TradeBean tradeBean, HttpServletRequest request, RedirectAttributes attributes){

		//System.out.println("imageFile : " + file);
		/*InputStream inputStream = null;
		try {
			if(!isValidImageFile(imageFile)){
				attributes.addFlashAttribute("error", "Upload Image of size maximum 2 MB");
				attributes.addFlashAttribute("description", description);
			}else{
				int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
				inputStream = imageFile.getInputStream();

				Tradeattachment tradeAttachment = new Tradeattachment();
				tradeAttachment.setFiletype(FilenameUtils.getExtension(imageFile.getOriginalFilename()));
				tradeAttachment.setImage(IOUtils.toByteArray(inputStream));
				tradeAttachment.setDescription(description);
				tradeAttachment.setLastupdtdate(new Date());
				tradeAttachment.setUpdatedby(userId);
				tradeAttachment.setTradeevent(tradeEventService.getById(new Tradeevent(), userId));

				tradeAttachmentService.add(tradeAttachment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		//return "redirect:eventAttachment";
	}

	@RequestMapping(value="editEventAttachment/{id}", method=RequestMethod.GET)
	public String editEventAttachmaentData(@PathVariable("id") String attachmentId, HttpServletRequest request, Model model){
		try {
			int id = !"".equals(attachmentId)?Integer.parseInt(attachmentId):-1;

			if(id != -1){
				Tradeattachment tradeAttachment = tradeAttachmentService.getById(new Tradeattachment(), id);
				if(tradeAttachment != null){
					model.addAttribute("description", tradeAttachment.getDescription());
					model.addAttribute("attachmentId", id);
					request.getSession().setAttribute("attachmentId", id);
				}
			}
			model.addAttribute("update", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/Trader/eventAttachment";
	}

	@RequestMapping(value="updateEventAttachment", method=RequestMethod.POST)
	public String updateEventAttachmaentData(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("attachmentDesc")String description, HttpServletRequest request, RedirectAttributes attributes){

		InputStream inputStream = null;
		Tradeattachment tradeAttachment = null;
		try {
			if(!isValidImageFile(imageFile)){
				attributes.addFlashAttribute("error", "Upload only image of size maximum 2 MB");
				attributes.addFlashAttribute("description", description);
				return "redirect:eventAttachment";
			}else{
				int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
				int id = Integer.parseInt(request.getSession().getAttribute("attachmentId").toString());

				tradeAttachment = tradeAttachmentService.getById(new Tradeattachment(), id);
				if(tradeAttachment != null){
					inputStream = imageFile.getInputStream();

					tradeAttachment.setFiletype(FilenameUtils.getExtension(imageFile.getOriginalFilename()));
					//tradeAttachment.setImage(IOUtils.toByteArray(inputStream));
					tradeAttachment.setDescription(description);
					tradeAttachment.setLastupdtdate(new Date());
					tradeAttachment.setUpdatedby(userId);
					tradeAttachment.setTradeevent(tradeEventService.getById(new Tradeevent(), userId));

					tradeAttachmentService.update(tradeAttachment);
				}
				request.getSession().removeAttribute("attachmentId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{

			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:eventAttachment";
	}

	@RequestMapping(value="deleteEventAttachment/{id}", method=RequestMethod.GET)
	public String deleteEventAttachmaentData(@PathVariable("id") String attachmentId, HttpServletRequest request, Model model){

		try {
			int id = !"".equals(attachmentId)?Integer.parseInt(attachmentId):-1;
			if(id != -1){
				tradeAttachmentService.remove(new Tradeattachment(), id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/Trader/eventAttachment";
	}
	
	private boolean isValidImageFile(MultipartFile imageFile){

		boolean flag = false;
		long size = imageFile.getSize();
		final String[] extensions = {"jpg","jpeg","png","gif","bmp"};
		try {
			if(FilenameUtils.isExtension(imageFile.getOriginalFilename(), extensions)){
				BufferedImage image = ImageIO.read(imageFile.getInputStream());
				if(image.getWidth() <= 2500 && image.getHeight() <= 1500){
					if(size > 0 && size <= 2097152){
						flag = true;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	//End Trade Event Attachment
}
