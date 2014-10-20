package com.trade.controller.trademgmt;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.model.Trade;
import com.trade.model.Tradeevent;
import com.trade.model.nonpersistent.TradeMetrics;
import com.trade.service.HibernateEntityService;
import com.trade.service.marketdata.GoogleMarketDataService;

@Controller
@RequestMapping("/trademetrics")
public class TradeMetricsController {
	@Autowired
	HibernateEntityService<Trade> tradeService;

	@Autowired
	HibernateEntityService<Tradeevent> tradeEventService;

	@RequestMapping(value = "getMetrics/{trade_id}", method = RequestMethod.POST)
	public @ResponseBody
	TradeMetrics getTradeMetrics(@PathVariable("trade_id") String id,
			HttpServletRequest request) {

		TradeMetrics tm = new TradeMetrics();
		Trade trade = tradeService.getById(new Trade(), Integer.parseInt(id));

		tm.setLastPrice(new BigDecimal(GoogleMarketDataService
				.getPriceForInstrument(trade.getInstrument()
						.getGoogle_exchange_id() + ":" + trade.getInstrument()
						.getGoogle_code_id())));
		
		
		tm.setCurrentPnL((tm.getLastPrice().subtract(trade.getOpenprice()))
				.multiply(new BigDecimal(trade.getQuantity())));
		
		double open = trade.getOpenprice().doubleValue();
		double last = tm.getLastPrice().doubleValue();
		double currentpnlperShare = (tm.getLastPrice().subtract(trade
				.getOpenprice())).doubleValue();
		tm.setCurrentPnLPercentage(new BigDecimal(currentpnlperShare / open));
		if (trade.getTp() != null && trade.getStoploss() != null) {
			double tp = trade.getTp().doubleValue();
			double sl = trade.getStoploss().doubleValue();
			double rr = (tp - open) / (open - sl);
			tm.setOriginalRiskReward(new BigDecimal(rr));
		}

		return tm;

	}
}
