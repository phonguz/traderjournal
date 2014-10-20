package com.trade.controller.trademgmt;

import static com.trade.util.QueryConstant.DELETE_FROM_TRADEEVENT_BY_TRADE_ID;
import static com.trade.util.ViewConstant.MAIN_HOME;
import static com.trade.util.ViewConstant.MANAGETRADE;
import static com.trade.util.ViewConstant.TRADEEVENT_TRADE;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trade.helper.bean.FileMeta;
import com.trade.helper.bean.TradeBean;
import com.trade.helper.validator.GeneralValidator;
import com.trade.helper.validator.TradeEventValidator;
import com.trade.helper.validator.TradeValidator;
import com.trade.model.Accounts;
import com.trade.model.Ccy;
import com.trade.model.Instrument;
import com.trade.model.Sharedaccount;
import com.trade.model.Trade;
import com.trade.model.Tradeattachment;
import com.trade.model.Tradeevent;
import com.trade.model.Tradeeventtype;
import com.trade.model.Trader;
import com.trade.model.nonpersistent.TradeMetrics;
import com.trade.service.HibernateEntityService;
import com.trade.util.ObjectErrorFactory;
import com.trade.util.ViewConstant;

@Controller
@RequestMapping("/trade")
public class TradeMgmtController {
	
	@Autowired
	HibernateEntityService<Accounts> accountService;
	@Autowired
	HibernateEntityService<Instrument> instrumentService;
	@Autowired
	HibernateEntityService<Trader> traderService;
	@Autowired
	HibernateEntityService<Trade> tradeService;
	@Autowired
	HibernateEntityService<Ccy> ccyService;
	@Autowired
	HibernateEntityService<Tradeeventtype> tradeEventTypeService;
	@Autowired
	HibernateEntityService<Tradeevent> tradeEventService;
	@Autowired
	HibernateEntityService<Tradeattachment> tradeAttachmentService;
	@Autowired
	TradeValidator tradeValidator;
	@Autowired
	TradeEventValidator tradeEventValidator;
	@Autowired
	HibernateEntityService<Sharedaccount> sharedAccountService;
	
	@Autowired
	TradeMetricsController tradeMetricsService;
	


	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String mainHome(@ModelAttribute("tradeBean") TradeBean tradeBean,Model model,HttpServletRequest request){
		List<Trade> tradeList = new ArrayList<Trade>();
		List<Sharedaccount> sharedaccountList = new ArrayList<Sharedaccount>();
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			tradeList = tradeService.getDataList(new Trade());
		}else{
			tradeList = tradeService.getByName(new Trade(), "trader_id", request.getSession().getAttribute("loggedUserId").toString()); 
			sharedaccountList = sharedAccountService.getByName(new Sharedaccount(), "share_trader_id", request.getSession().getAttribute("loggedUserId").toString());
			if(sharedaccountList != null && sharedaccountList.size() > 0){
				List<Trade> tempTradeList = tradeService.getDataList(new Trade());
				if(tempTradeList != null &&  tempTradeList.size() > 0){
					for(int i=0; i< sharedaccountList.size(); i++ ){
						for(int j=0; j< tempTradeList.size(); j++){
							if(tempTradeList.get(j).getAccounts().getId().equals(sharedaccountList.get(i).getAccounts().getId()) &&
									tempTradeList.get(j).getTrader().getId().equals(sharedaccountList.get(i).getTraderByTraderId().getId())){
								tradeList.add(tempTradeList.get(j));
							}
						}
					}
				}
			}
		}
		Collections.reverse(tradeList);		
		model.addAttribute("tradeList", tradeList);
		request.getSession().removeAttribute("eventupdate");
		request.getSession().removeAttribute("eventattachmentList");
		request.getSession().removeAttribute("filesList");
		//request.getSession().removeAttribute("createIn");
		//request.setAttribute("tradeopendate",openDate);
		return MANAGETRADE;
	}
	
	@RequestMapping(value = "/create", method = {RequestMethod.GET,RequestMethod.POST})
	public String createTrade(@ModelAttribute("tradeBean") TradeBean tradeBean,Model model,HttpServletRequest request){
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		tradeBean.setOpentradedate(df.format(new Date()));
		List<Accounts> accountsList = new ArrayList<Accounts>();
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			accountsList = accountService.getDataList(new Accounts());
		}else{
			accountsList = accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
		}
		model.addAttribute("accountsList", accountsList);
		List<Instrument> instrumentList = instrumentService.getDataList(new Instrument());
		model.addAttribute("instrumentList", instrumentList);
		model.addAttribute("newInst", false);
		return MAIN_HOME;
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String addTrade(@ModelAttribute("tradeBean") TradeBean tradeBean,BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes,HttpServletRequest request) throws ParseException{
		validateTrade(tradeBean,bindingResult,request);
		model.addAttribute("newInst", request.getParameter("newInst"));
		/*if(!bindingResult.hasErrors()){
			if(request.getParameter("isUpdate").equalsIgnoreCase("true")){
				tradeEventValidator.validate(tradeBean,bindingResult);
				if(bindingResult.hasErrors()){
					request.setAttribute("desc", "true");
				}
			}
		}*/
		if(bindingResult.hasErrors() || request.getAttribute("instrumentException") != null){
			List<Accounts> accountsList = new ArrayList<Accounts>();
			if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
				accountsList = accountService.getDataList(new Accounts());
			}else{
				accountsList = accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
			}
			model.addAttribute("accountsList", accountsList);
			Instrument instrument = new Instrument();
			List<Instrument> instrumentList = instrumentService.getDataList(instrument);
			model.addAttribute("instrumentList", instrumentList);
			List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
			model.addAttribute("eventTypeList", eventTypeList);
			if(!request.getParameter("isUpdate").trim().equalsIgnoreCase("") && request.getParameter("isUpdate") != null){
				if(request.getParameter("isUpdate").trim().equalsIgnoreCase("true")){
					model.addAttribute("isUpdate", true);
				}else{
					request.setAttribute("validFail", "true");
				}
			}else{
				request.setAttribute("validFail", "true");
			}
			return MAIN_HOME;
		}
		/*if(request.getParameter("isUpdate").equalsIgnoreCase("true")){
			model.addAttribute("newInst", request.getParameter("newInst").equalsIgnoreCase("true"));
			List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
			model.addAttribute("eventTypeList", eventTypeList);
			List<Tradeevent> eventList = tradeEventService.getDataList(new Tradeevent());
			model.addAttribute("eventList", eventList);
			return TRADEEVENT_TRADE;
		}*/
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date fromdate = (Date)df.parse(tradeBean.getOpentradedate());
		Accounts accounts = accountService.getById(new Accounts(), Integer.parseInt(tradeBean.getAccountsId()));
		Trade trade = new Trade();
		Trader trader = traderService.getById(new Trader(), Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
		if(request.getParameter("newInst").equalsIgnoreCase("true")){
			Instrument instrument = new Instrument();
			Ccy ccy = ccyService.getById(new Ccy(), accounts.getCcy().getId());
			instrument.setCcy(ccy);
			instrument.setName(tradeBean.getInstrumentName());
			instrument.setLastupdtdate(new Date());
			instrument.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
			instrumentService.add(instrument);
			List<Instrument> newInstrumentList = instrumentService.getByName(new Instrument(), "name", tradeBean.getInstrumentName());
			trade.setInstrument(newInstrumentList.get(0));
		}else{
			Instrument instrument = instrumentService.getById(new Instrument(), Integer.parseInt(tradeBean.getInstrumentId()));
			trade.setInstrument(instrument);
		}
		trade.setTradetype(Integer.parseInt(tradeBean.getTradetype()));
		trade.setAccounts(accounts);
		trade.setTrader(trader);
		trade.setQuantity(Integer.parseInt(tradeBean.getQuantity()));
		trade.setOpenprice(new BigDecimal(tradeBean.getOpenprice()));
		if(!"".equalsIgnoreCase(tradeBean.getCloseprice()) && tradeBean.getCloseprice().length()>=0){
			trade.setCloseprice(new BigDecimal(tradeBean.getCloseprice()));
		}
		trade.setStoploss(new BigDecimal(tradeBean.getStoploss()));
		trade.setTp(new BigDecimal(tradeBean.getTargetprice()));
		trade.setOpentradedate(fromdate);
		if(!tradeBean.getClosetradedate().equalsIgnoreCase("") && tradeBean.getClosetradedate() != null){
			Date todate = (Date)df.parse(tradeBean.getClosetradedate());
			trade.setClosetradedate(todate);
		}
		trade.setLastupdtdate(new Date());
		trade.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
		String returnPage="redirect:/trade";
		if(request.getParameter("isUpdate").equalsIgnoreCase("") || request.getParameter("isUpdate") == null || request.getParameter("isUpdate").equalsIgnoreCase("false")){
			tradeService.add(trade);
				/*    Add Event   */
				Tradeevent tradeevent = new Tradeevent();
				tradeevent.setEventdate(new Date());
				tradeevent.setDescription("New Trade");
				tradeevent.setEventorder(1);
				tradeevent.setNewvalue(new BigDecimal(0));
				tradeevent.setLastupdtdate(new Date());
				tradeevent.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
				tradeevent.setTrade(trade);
				String eventName = null;
				if(tradeBean.getTradetype().equalsIgnoreCase("0")){
					eventName="Long";
				}else{
					eventName="Short";
				}
				Tradeeventtype eventType = new Tradeeventtype();
				List<Tradeeventtype> tradeEventTypeList =  tradeEventTypeService.getByLikeName(new Tradeeventtype(), "name", eventName);
				if(tradeEventTypeList == null || tradeEventTypeList.size()==0){
						eventType.setName(eventName);
						eventType.setLastupdtdate(new Date());
						eventType.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
						tradeEventTypeService.add(eventType);
				}else{
					eventType = tradeEventTypeList.get(0);
				}
				tradeevent.setTradeeventtype(eventType);
				tradeEventService.add(tradeevent);
			redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trade created successfully");
			//returnPage="redirect:/trade";
		}else if(request.getParameter("isUpdate").equalsIgnoreCase("true")){
			trade.setId(Integer.parseInt(request.getSession().getAttribute("tradeId").toString()));
			
			/*Tradeevent tradeevent = new Tradeevent();
			tradeevent.setEventdate((Date)df.parse(tradeBean.getEventdate()));
			tradeevent.setDescription(tradeBean.getDescription());
			tradeevent.setEventorder(1);
			tradeevent.setNewvalue(new BigDecimal(tradeBean.getNewvalue()));
			tradeevent.setLastupdtdate(new Date());
			tradeevent.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
			tradeevent.setTrade(trade);
			Tradeeventtype tradeeventtype = tradeEventTypeService.getById(new Tradeeventtype(), Integer.parseInt(tradeBean.getEventtypeid()));
			tradeevent.setTradeeventtype(tradeeventtype);
			tradeEventService.add(tradeevent);*/
			
			tradeService.update(trade);
			request.getSession().removeAttribute("tradeId");
			redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trade updated successfully");
			//returnPage="redirect:/trade/getalltrades";
		}
		return returnPage;
	}
	
	/*@RequestMapping(value = "/getalltrades", method = RequestMethod.GET)
	public String getTraders(Model model,HttpServletRequest request){
		List<Trade> tradeList = new ArrayList<Trade>();
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			tradeList = tradeService.getDataList(new Trade());
		}else{
			tradeList = tradeService.getByName(new Trade(), "trader_id", request.getSession().getAttribute("loggedUserId").toString()); 
		}
		Collections.reverse(tradeList);		
		model.addAttribute("tradeList", tradeList);
		return MANAGETRADE;
	}*/
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeTrade(final RedirectAttributes redirectAttributes,HttpServletRequest request){
//		int resultAttach = tradeEventService.deleteQuery(DELETE_FROM_TRADEATTACHMENT_BY_TRADEEVENT_ID, Integer.parseInt(request.getParameter("refId")));
		List<Tradeevent> tradeEventList = tradeEventService.getByName(new Tradeevent(), "trade_id", Integer.parseInt(request.getParameter("refId")));
		if(tradeEventList!=null && tradeEventList.size() > 0){
			for(Tradeevent event : tradeEventList){
				List<Tradeattachment> attachments = tradeAttachmentService.getByName(new Tradeattachment(), "tradeevent_id", event.getId());
				String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments");
				for(Tradeattachment attach : attachments){
			        File file = new File(fullPath + File.separator + attach.getFilename());
			        boolean isDelete = file.delete();
				}
			}
			int resultEvent = tradeEventService.deleteQuery(DELETE_FROM_TRADEEVENT_BY_TRADE_ID, Integer.parseInt(request.getParameter("refId")));
			tradeService.remove(new Trade(), Integer.parseInt(request.getParameter("refId")));
		}else{
			tradeService.remove(new Trade(), Integer.parseInt(request.getParameter("refId")));
		}
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trade removed successfully");
		return "redirect:/trade";
	}
	
	@RequestMapping(value = "/removeevent", method = RequestMethod.POST)
	public String removeEvent(final RedirectAttributes redirectAttributes,HttpServletRequest request,Model model){
//		int resultAttach = tradeEventService.deleteQuery(DELETE_FROM_TRADEATTACHMENT_BY_TRADEEVENT_ID, Integer.parseInt(request.getParameter("refId")));
		Tradeevent tradeEvent = tradeEventService.getById(new Tradeevent(), Integer.parseInt(request.getParameter("eventId")));
		if(tradeEvent!=null){
			List<Tradeattachment> attachments = tradeAttachmentService.getByName(new Tradeattachment(), "tradeevent_id", tradeEvent.getId());
			String fullPath = request.getServletContext().getRealPath("/WEB-INF/attachments");
			for(Tradeattachment attach : attachments){
		        File file = new File(fullPath + File.separator + attach.getFilename());
		        boolean isDelete = file.delete();
			}
			tradeEventService.remove(new Tradeevent(), tradeEvent.getId());
		}
		model.addAttribute("SUCCESS_MSG", "Event removed successfully");
		List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
		model.addAttribute("eventTypeList", eventTypeList);
		List<Tradeevent> eventList = tradeEventService.getByName(new Tradeevent(), "trade_id", Integer.parseInt(request.getParameter("refId")));
		model.addAttribute("eventList", eventList);
		Object tradeObj = tradeService.getById(new Trade(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
		model.addAttribute("tradeObj",tradeObj);
		return ViewConstant.TRADEATTACHMENT_HOME;
	}
	
	@RequestMapping(value = "/gettrade", method = RequestMethod.POST)
	public String getTrade(@ModelAttribute("tradeBean") TradeBean tradeBean,HttpServletRequest request,Model model){
		List<Accounts> accountsList = new ArrayList<Accounts>();
		if(request.getSession().getAttribute("traderType").toString().equalsIgnoreCase("ADMIN")){
			accountsList = accountService.getDataList(new Accounts());
		}else{
			accountsList = accountService.getByName(new Accounts(), "trader_id", request.getSession().getAttribute("loggedUserId").toString());
		}
		model.addAttribute("accountsList", accountsList);
		List<Instrument> instrumentList = instrumentService.getDataList(new Instrument());
		model.addAttribute("instrumentList", instrumentList);
		List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
		model.addAttribute("eventTypeList", eventTypeList);
		Trade trade = tradeService.getById(new Trade(), Integer.parseInt(request.getParameter("refId")));
		tradeBean.setAccountsId(String.valueOf(trade.getAccounts().getId()));
		tradeBean.setInstrumentId(String.valueOf(trade.getInstrument().getId()));
		tradeBean.setTradetype(String.valueOf(trade.getTradetype()));
		tradeBean.setQuantity(String.valueOf(trade.getQuantity()));
		tradeBean.setOpenprice(String.valueOf(trade.getOpenprice()));
		tradeBean.setTargetprice(String.valueOf(trade.getTp()));
		if(trade.getCloseprice() != null){
			tradeBean.setCloseprice(String.valueOf(trade.getCloseprice()));
		}else{
			tradeBean.setCloseprice(String.valueOf(""));
		}
		tradeBean.setStoploss(String.valueOf(trade.getStoploss()));
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String openDate = df.format(trade.getOpentradedate());
		tradeBean.setOpentradedate(openDate);
		if(trade.getClosetradedate()!=null){
			String closeDate = df.format(trade.getClosetradedate());
			tradeBean.setClosetradedate(closeDate);
		}
		model.addAttribute("isUpdate", true);
		request.getSession().setAttribute("tradeId", trade.getId());
		model.addAttribute("newInst", false);
		return MAIN_HOME;
	}
	
	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
	public String createEvent(@ModelAttribute("tradeBean") TradeBean tradeBean,Model model, final RedirectAttributes redirectAttributes,HttpServletRequest request){
		//System.out.println("session variable : " + request.getSession().getAttribute("createIn"));
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if(request.getParameter("eventId")!=null){
			if(!request.getParameter("eventId").equalsIgnoreCase("") && GeneralValidator.isValidNumber(request.getParameter("eventId"))){
				Tradeevent tradeEvent = tradeEventService.getById(new Tradeevent(), Integer.parseInt(request.getParameter("eventId")));
				tradeBean.setDescription(tradeEvent.getDescription());
				tradeBean.setNewvalue(String.valueOf(tradeEvent.getNewvalue()));
				Tradeeventtype eventType = tradeEvent.getTradeeventtype();
				tradeBean.setEventtypeid(String.valueOf(eventType.getId()));
				tradeBean.setEventdate(df.format(tradeEvent.getEventdate()));
				
				List<Tradeattachment> attachments = (List<Tradeattachment>) tradeAttachmentService.getByName(new Tradeattachment(), "tradeevent_id", Integer.parseInt(request.getParameter("eventId")));
				if(attachments!=null){
					if(attachments.size()>0){
						model.addAttribute("attachments", attachments);
						model.addAttribute("attachmentDesc", attachments.get(0).getDescription());
					}
				}
				request.getSession().setAttribute("eventupdate", request.getParameter("eventId"));
			}else{
				tradeBean.setEventdate(df.format(new Date()));
			}
		}else{
			tradeBean.setEventdate(df.format(new Date()));
		}
		List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
		Object tradeObj = tradeService.getById(new Trade(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
		model.addAttribute("eventTypeList", eventTypeList);
		/*List<Tradeevent> eventList = tradeEventService.getDataList(new Tradeevent());
		model.addAttribute("eventList", eventList);*/
		model.addAttribute("refId", request.getParameter("refId"));
		model.addAttribute("createIn", request.getParameter("createIn"));
		model.addAttribute("tradeObj",tradeObj);
		List<Tradeattachment> attachmentList =new ArrayList<Tradeattachment>(); 
		request.getSession().setAttribute("eventattachmentList",attachmentList);
		List<FileMeta> files = new ArrayList<FileMeta>(); 
		request.getSession().setAttribute("filesList",files);
		return TRADEEVENT_TRADE;
	}
	
	/*public String editEvent(@ModelAttribute("tradeBean") TradeBean tradeBean,HttpServletRequest request){
		return 
	}*/
	
	@RequestMapping(value = "/updateevent", method = RequestMethod.POST)
	public String updateEvent(@ModelAttribute("tradeBean") TradeBean tradeBean,HttpServletRequest request,Model model,BindingResult bindingResult,final RedirectAttributes redirectAttributes,
		@RequestParam("attachmentDesc")String description) throws ParseException{
		tradeEventValidator.validate(tradeBean,bindingResult);
		request.getSession().getAttribute("eventAttachment");
		//System.out.println("Creat var : " + request.getSession().getAttribute("createIn"));
		if(bindingResult.hasErrors()){
			List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
			model.addAttribute("eventTypeList", eventTypeList);
			List<Tradeevent> eventList = tradeEventService.getDataList(new Tradeevent());
			model.addAttribute("eventList", eventList);			
			model.addAttribute("attachmentDesc", description);
			model.addAttribute("refId", request.getParameter("refId"));
			Object tradeObj = tradeService.getById(new Trade(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
			model.addAttribute("tradeObj",tradeObj);
			return TRADEEVENT_TRADE;
		}
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");	
		
		Tradeevent tradeevent = new Tradeevent();
		try {
			tradeevent.setEventdate((Date)df.parse(tradeBean.getEventdate()));
		} catch (ParseException e) {
			e.printStackTrace();
			ObjectError error = ObjectErrorFactory.getObject("dateParseException", "Problem occured in date, please try later");
			bindingResult.addError(error);
			return TRADEEVENT_TRADE;
		}
		tradeevent.setDescription(tradeBean.getDescription());
		tradeevent.setEventorder(1);
		if(!"".equalsIgnoreCase(tradeBean.getNewvalue()) && tradeBean.getNewvalue().length()>=0){
			tradeevent.setNewvalue(new BigDecimal(tradeBean.getNewvalue()));
		}else{
			tradeevent.setNewvalue(new BigDecimal(0));
		}
		tradeevent.setLastupdtdate(new Date());
		tradeevent.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
		tradeevent.setTrade(tradeService.getById(new Trade(), Integer.parseInt(request.getParameter("refId").toString())));
		tradeevent.setTradeeventtype(tradeEventTypeService.getById(new Tradeeventtype(), Integer.parseInt(tradeBean.getEventtypeid())));
		String returnView = null;
		if(request.getSession().getAttribute("eventupdate")!=null && !String.valueOf(request.getSession().getAttribute("eventupdate")).equalsIgnoreCase("")){
			tradeevent.setId(Integer.parseInt(String.valueOf(request.getSession().getAttribute("eventupdate"))));
			tradeEventService.update(tradeevent);
			
			List<Tradeattachment> tradeAttachmentList = tradeAttachmentService.getByName(new Tradeattachment(), "tradeevent_id", Integer.parseInt(String.valueOf(request.getSession().getAttribute("eventupdate"))));
			for(Tradeattachment tradeAttachment : tradeAttachmentList){
				tradeAttachment.setId(tradeAttachment.getId());
				tradeAttachment.setDescription(description);
				tradeAttachment.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
				tradeAttachment.setTradeevent(tradeevent);	
				tradeAttachmentService.update(tradeAttachment);
			}
			model.addAttribute("SUCCESS_MSG", "Trade event updated successfully");
			List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
			model.addAttribute("eventTypeList", eventTypeList);
			List<Tradeevent> eventList = tradeEventService.getByName(new Tradeevent(), "trade_id", Integer.parseInt(String.valueOf(request.getParameter("refId"))));
			model.addAttribute("eventList", eventList);	
			returnView = ViewConstant.TRADEATTACHMENT_HOME;
		}else{
			redirectAttributes.addFlashAttribute("SUCCESS_MSG", "Trade event added successfully");
			tradeEventService.add(tradeevent);
			/*if(request.getParameter("createIn")!=null && request.getParameter("createIn").equalsIgnoreCase("true")){
				returnView = "redirect:/trade/eventAttachment";
			}else{*/
				returnView = "redirect:/trade";
			//}
		}

		if(request.getSession().getAttribute("eventattachmentList") !=null){
			addEventAttachment(request, description, tradeevent);
		}
		Object tradeObj = tradeService.getById(new Trade(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
		model.addAttribute("tradeObj",tradeObj);
		model.addAttribute("refId", request.getParameter("refId"));
		request.getSession().removeAttribute("eventupdate");
		request.getSession().removeAttribute("tradeId");
		request.getSession().removeAttribute("eventattachmentList");
		request.getSession().removeAttribute("filesList");
		return returnView;
	}
	
	public void validateTrade(TradeBean tradeBean,BindingResult bindingResult,HttpServletRequest request){	
		if(request.getParameter("newInst").equalsIgnoreCase("true")){
			List<Instrument> instrument = instrumentService.getByName(new Instrument(), "name", tradeBean.getInstrumentName());
			if(instrument.size()>0){
				request.setAttribute("instrumentException", "Either select instrument or enter instrument name");
			}
		}
		tradeValidator.validate(tradeBean,bindingResult);
	}
	
	
	@RequestMapping(value="eventAttachment", method=RequestMethod.POST)
	public String getEventAttachmentData(HttpServletRequest request, Model model){
		/*String userId = "";
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
		}*/
		
		
		
		List<Tradeeventtype> eventTypeList = tradeEventTypeService.getDataList(new Tradeeventtype());
		model.addAttribute("eventTypeList", eventTypeList);
		//List<Tradeevent> eventList = tradeEventService.getDataList(new Tradeevent());
		Object tradeObj = tradeService.getById(new Trade(), Integer.parseInt(String.valueOf(request.getParameter("refId"))));
		model.addAttribute("tradeObj",tradeObj);
		String refid = request.getParameter("refId");
		
		TradeMetrics tmetrics = tradeMetricsService.getTradeMetrics(refid, request);
		model.addAttribute("metricObject", tmetrics);
		//q=AEG&x=JSE
		Object googleIdObject = "q=" + ((Trade)tradeObj).getInstrument().getGoogle_code_id() + "&x=" +((Trade)tradeObj).getInstrument().getGoogle_exchange_id() ;
		model.addAttribute("instrumentGoogleChartId",googleIdObject);
		List<Tradeevent> eventList = tradeEventService.getByName(new Tradeevent(), "trade_id", Integer.parseInt(String.valueOf(request.getParameter("refId"))));
		model.addAttribute("refId", request.getParameter("refId"));
		model.addAttribute("eventList", eventList);
		
		return ViewConstant.TRADEATTACHMENT_HOME;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addAttach", method = RequestMethod.POST)
	private void addEventAttachment(HttpServletRequest request,String description, Tradeevent tradeEvent){
			int userId = Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString());
			if(request.getSession().getAttribute("eventattachmentList") !=null){
				List<Tradeattachment> tradeAttachmentList = (List<Tradeattachment>) request.getSession().getAttribute("eventattachmentList");
				//System.out.println("session list : " + tradeAttachmentList.size());
				//Tradeattachment tradeAttachment = (Tradeattachment) request.getSession().getAttribute("eventattachmentList");
				/*tradeAttachment.setFiletype(FilenameUtils.getExtension(files.getOriginalFilename()));			
				tradeAttachment.setImage(IOUtils.toByteArray(inputStream));			
				tradeAttachment.setLastupdtdate(new Date());*/
				for(Tradeattachment tradeAttachment : tradeAttachmentList){
					tradeAttachment.setDescription(description);
					tradeAttachment.setUpdatedby(userId);
					tradeAttachment.setTradeevent(tradeEvent);		

					tradeAttachmentService.add(tradeAttachment);
				}
			}
	}
	
	@RequestMapping(value="getattachment/{id}", method = RequestMethod.POST)
	public @ResponseBody String attachmentList(@PathVariable("id") String id, HttpServletRequest request) {   
      List<Tradeattachment> tradeattachmentList = tradeAttachmentService.getByName(new Tradeattachment(), "tradeevent_id", Integer.parseInt(id));
      JSONArray arrayJSON = new JSONArray();
      for(Tradeattachment tradeattachment : tradeattachmentList){
	      try{
	    	  JSONObject attachJSON = new JSONObject();
	    	  attachJSON.put("attachId", tradeattachment.getId());
	    	  arrayJSON.put(attachJSON);
	      }
	      catch(Exception ex){
	    	  ex.printStackTrace();
	      }
      }
      return arrayJSON.toString();
	}
}
