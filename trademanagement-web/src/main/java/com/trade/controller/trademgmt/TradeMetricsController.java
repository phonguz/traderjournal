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
import com.trade.service.marketdata.GoogleMarketDataService;
import com.trade.util.ObjectErrorFactory;
import com.trade.util.ViewConstant;




@Controller
@RequestMapping("/trademetrics")
public class TradeMetricsController {
	@Autowired
	HibernateEntityService<Trade> tradeService;
	
	@Autowired
	HibernateEntityService<Tradeevent> tradeEventService;

	
	
	@RequestMapping(value="getMetrics/{trade_id}", method = RequestMethod.POST)
	public @ResponseBody TradeMetrics getTradeMetrics(@PathVariable("trade_id") String id, HttpServletRequest request) {   
      TradeMetrics tm = new TradeMetrics();
      Trade trade = tradeService.getById(new Trade(), Integer.parseInt(request.getParameter("trade_id")));
      
      tm.setLastPrice( new BigDecimal( GoogleMarketDataService.getPriceForInstrument( trade.getInstrument().getGoogle_code_id() ) ));
      tm.setCurrentPnL(  ( trade.getOpenprice().subtract( tm.getLastPrice() )).multiply(new BigDecimal(trade.getQuantity() )));
      tm.setCurrentPnLPercentage(tm.getCurrentPnL().divide(trade.getOpenprice()));
      
      double rr = trade.getTp().doubleValue() - trade.getOpenprice().doubleValue() / (trade.getOpenprice().doubleValue() - trade.getStoploss().doubleValue() ) ;
      
      tm.setOriginalRiskRewad(new BigDecimal(rr));
      
      
      
      
      
      
      return tm;
      
	}
}
