package com.trade.controller.marketdata;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.model.marketdata.Last;
import com.trade.service.marketdata.GoogleMarketDataService;

@Controller
@RequestMapping("/marketdata")
public class MarketDataController {
	
	
	@RequestMapping(value =  "/getLast/{google_code_id}", method = RequestMethod.GET)
	public @ResponseBody Last getPrice(@PathVariable("google_code_id") String googleId){
	Last ls = new Last();
	long pr = GoogleMarketDataService.getPriceForInstrument(googleId);
	ls.setGoogleID(googleId);
	ls.setLast(pr);
	return ls;
	}
	
	@RequestMapping(value =  "/getme", method = RequestMethod.GET)
	public @ResponseBody int getPrice(){
	
	
	return 1;
	}
}
