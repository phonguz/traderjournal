package com.trade.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trade.helper.validator.GeneralValidator;
import com.trade.model.Accounts;
import com.trade.model.Ccy;
import com.trade.model.Instrument;
import com.trade.model.Trade;
import com.trade.service.HibernateEntityService;
import com.trade.util.ViewConstant;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	HibernateEntityService<Ccy> ccyService;
	
	@Autowired
	HibernateEntityService<Instrument> instrumentService;
	
	@Autowired
	HibernateEntityService<Trade> tradeService;
	
	@Autowired
	HibernateEntityService<Accounts> accountService;

	@RequestMapping(value="/saveCurrency", method = RequestMethod.POST)
	public String saveCurrencyData(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		List<Ccy> ccyList = null;
		try {
			String currencyName = request.getParameter("ccyName");
			if(!"".equals(currencyName)){
				ccyList = ccyService.getByName(new Ccy(), "name", currencyName);
			}
			if(!"".equals(currencyName) && (ccyList==null || ccyList.size()<=0)){
				if(GeneralValidator.isValidCurrencyName(currencyName)){
					Ccy ccy = new Ccy();
					ccy.setName(currencyName);
					ccy.setLastupdtdate(new Date());
					ccy.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					ccyService.add(ccy);
					redirectAttributes.addFlashAttribute("success", "Currecny created succesfully");
				}else{
					redirectAttributes.addFlashAttribute("error", "Currency must between 3 to 25 charcters");
				}
			}else{
				redirectAttributes.addFlashAttribute("error", "Currency already exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:currencyList";
	}
	
	@RequestMapping(value="updateCurrency", method= RequestMethod.POST)
	public String updateCurrencyData(HttpServletRequest request , RedirectAttributes redirectAttributes) {
		Ccy ccy = null;
		List<Ccy> ccyList = null;
		try {
			String currencyId = "".equals(request.getParameter("ccyId"))?"":request.getParameter("ccyId");
			String currencyName = request.getParameter("ccyName");
			ccy = new Ccy();
			
			if(!"".equals(currencyId)){
				ccy = ccyService.getById(ccy, Integer.parseInt(currencyId));
			}
			if(!"".equals(currencyName) && !currencyName.equals(ccy.getName())){
				ccyList = ccyService.getByName(new Ccy(), "name", currencyName);
			}
			
			if(ccy!=null && (ccyList==null || ccyList.size()<=0)){
				if(GeneralValidator.isValidCurrencyName(currencyName)){
					ccy.setName(currencyName);
					ccy.setLastupdtdate(new Date());
					ccy.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
					ccyService.update(ccy);
					redirectAttributes.addFlashAttribute("success", "Currency updated successfully");
				}else{
					redirectAttributes.addFlashAttribute("error", "Currency must between 3 to 25 charcters");
				}
			}else{
				redirectAttributes.addFlashAttribute("error", "Currency already exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:currencyList";
	}
	
	@RequestMapping(value="deleteCurrency", method= RequestMethod.POST)
	public String deleteCurrencyData(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Ccy ccy = null;
		try {
			String currencyId = "".equals(request.getParameter("ccyId"))?"":request.getParameter("ccyId");
			ccy = new Ccy();
			if(!"".equals(currencyId)){
				int id = Integer.parseInt(currencyId);
				ccy = ccyService.getById(ccy, id);
				if(ccy != null){
					List<Accounts> accountList = accountService.getByName(new Accounts(), "ccy_id", ccy.getId());
					if(accountList != null && accountList.size()>0){
						redirectAttributes.addFlashAttribute("error", "Cannot delete, Currency is associated with account(s)");
					}else{
						ccyService.remove(ccy,id);
						redirectAttributes.addFlashAttribute("success", "Currency deleted succesfully");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:currencyList";
	}
	
	@RequestMapping(value="currencyList", method= RequestMethod.GET)
	public String getCurrencyList(HttpServletRequest request, Model model){
		List<Ccy> currencyList = null;
		try {
			currencyList = ccyService.getDataList(new Ccy());
			if(currencyList!=null && currencyList.size()>0){
				model.addAttribute("currencyList", currencyList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViewConstant.CURRENCY_PAGE;
	}
	
	@RequestMapping(value="instrumentList", method= RequestMethod.GET)
	public String getInstrumentList(HttpServletRequest request, Model model){
		List<Instrument> instrumentList = null;
		List<Ccy> currencyList = null;
		try {
			currencyList = ccyService.getDataList(new Ccy());
			if(currencyList!=null && currencyList.size()>0){
				model.addAttribute("currencyList", currencyList);
			}
			instrumentList = instrumentService.getDataList(new Instrument());
			if(instrumentList!=null && instrumentList.size()>0){
				model.addAttribute("instrumentList", instrumentList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ViewConstant.INSTRUMENT_PAGE;
	}
	
	@RequestMapping(value="/saveInstrument", method = RequestMethod.POST)
	public String saveInstrumentData(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Instrument instrument = null;
		List<Instrument> instrumentList = null;
		try {
			String instrumentName = request.getParameter("instrumentName");
			String googleCodeId = request.getParameter("google_code_id");
			String ccyId = request.getParameter("ccyId");
			if(!"".equals(instrumentName)){
				instrumentList = instrumentService.getByName(new Instrument(), "name", instrumentName);
			}
			if(!"".equals(instrumentName) && (instrumentList==null || instrumentList.size()<=0)){
				if(GeneralValidator.isValidInstrument(instrumentName)){
					instrument = new Instrument();
					if(ccyId!=null && !"".equals(ccyId)){
						Ccy ccy = ccyService.getById(new Ccy(), Integer.parseInt(ccyId));
						instrument.setCcy(ccy);
						instrument.setName(instrumentName);
						instrument.setGoogle_code_id(googleCodeId);
						instrument.setLastupdtdate(new Date());
						instrument.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
						instrumentService.add(instrument);
						redirectAttributes.addFlashAttribute("success", "Instrument added succesfully");
					}else{
						redirectAttributes.addFlashAttribute("error", "Please select Currency");
					}
				}else{
					redirectAttributes.addFlashAttribute("error", "Instrument must between 3 to 25 alphanumeric");
				}
			}else{
				redirectAttributes.addFlashAttribute("error", "Instrument already exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:instrumentList";
	}

	@RequestMapping(value="updateInstrument", method= RequestMethod.POST)
	public String updateInstrumentData(HttpServletRequest request , RedirectAttributes redirectAttributes) {
		Instrument instrument = null;
		List<Instrument> instrumentList = null;
		try {
				String instrumentName = request.getParameter("instrumentName");
				String ccyId = request.getParameter("ccyId");
				String googleCodeId = request.getParameter("google_code_id");
				String instrumentId = "".equals(request.getParameter("instrumentId"))?"":request.getParameter("instrumentId");
				
				if(!"".equals(instrumentId)){
					instrument = instrumentService.getById(new Instrument(), Integer.parseInt(instrumentId));
				}
				if(!"".equals(instrumentName) && !instrumentName.equals(instrument.getName())){
					instrumentList = instrumentService.getByName(new Instrument(), "name", instrumentName);
				}
				if(!"".equals(instrumentName) && (instrumentList==null || instrumentList.size()<=0)){
					if(GeneralValidator.isValidInstrument(instrumentName)){
						if(ccyId!=null && !"".equals(ccyId)){
							Ccy ccy = ccyService.getById(new Ccy(), Integer.parseInt(ccyId));
							instrument.setCcy(ccy);
							instrument.setName(instrumentName);
							instrument.setGoogle_code_id(googleCodeId);
							instrument.setLastupdtdate(new Date());
							instrument.setUpdatedby(Integer.parseInt(request.getSession().getAttribute("loggedUserId").toString()));
							instrumentService.update(instrument);
							redirectAttributes.addFlashAttribute("success", "Instrument updated succesfully");
						}else{
							redirectAttributes.addFlashAttribute("error", "Please select Currency");
						}
					}else{
						redirectAttributes.addFlashAttribute("error", "Instrument must between 3 to 25 alphanumeric");
					}
				}else{
					redirectAttributes.addFlashAttribute("error", "Instrument already exist");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:instrumentList";
	}
	
	@RequestMapping(value="deleteInstrument", method= RequestMethod.POST)
	public String deleteInstrumentData(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		try {
			String instrumentId = "".equals(request.getParameter("instrumentId"))?"":request.getParameter("instrumentId");
			if(!"".equals(instrumentId)){
				int id = Integer.parseInt(instrumentId);
				Instrument instrument = instrumentService.getById(new Instrument(), id);
				if(instrument != null){
					List<Trade> tradeList = tradeService.getByName(new Trade(), "instrument_id", instrument.getId());
					if(tradeList != null && tradeList.size()>0){
						redirectAttributes.addFlashAttribute("error", "cannot delete, Instrument is associated with trade");
					}else{
						instrumentService.remove(instrument,id);
						redirectAttributes.addFlashAttribute("success", "Instrument deleted succesfully");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:instrumentList";
	}
	
}
