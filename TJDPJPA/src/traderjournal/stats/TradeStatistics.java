package traderjournal.stats;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transaction;

import traderjournal.model.entities.Trade;
import traderjournal.model.entities.Tradeevent;

public class TradeStatistics {
	Trade currentTrade;
	public TradeStatistics(Trade trade){
		currentTrade = trade;
	}
	public double getOriginalSL(){
		if(currentTrade.getStoploss() != null)
			return currentTrade.getStoploss();
		else
			return 0d;
	}
	
	public double getOriginalTP(){
		if(currentTrade.getTp() != null)
			return currentTrade.getTp();
		else
			return 0d;
	}
	
	

	
	public Double getLatestOpenPrice(){
		
		
		List<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getOpenprice();
		for(Tradeevent te : events){
			if(te.getTradeeventtype().getName().equals("Add To Position") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}

		return ret;
	}
	
	public Double getLatestSL(){
		
		List<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getStoploss();
		for(Tradeevent te : events){
			if(te.getTradeeventtype().getName().equals("Move SL") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}

		return ret;
	}
	
	public Double getLatestTP(){

		List<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getTp();
		for(Tradeevent te : events){
			if(te.getTradeeventtype().getName().equals("Move TP") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}

		return ret;
	}
	
	public boolean canCalcStat(){
		
		if(currentTrade.getOpenprice() == null || currentTrade.getOpenprice().doubleValue() == 0.0){
			return false;
		}
		if(currentTrade.getStoploss() == null || currentTrade.getStoploss().doubleValue() == 0.0){
			return false;
		}
		if(currentTrade.getTp() == null || currentTrade.getTp().doubleValue() == 0.0){
			return false;
		}
		
		return true;
		
	}
	
	public double getRR(double open,double sl, double tp){
		if(sl < open){ //long
			double  risk = open - sl;
			double reward = tp - open;
			return  reward / risk;
		}else{ //short
			double  risk =  sl - open;
			double reward = open - tp;
			return reward / risk;
		}
	}
	
	public double getOriginalRR(){
		if(canCalcStat())
				return getRR(currentTrade.getOpenprice().doubleValue(),currentTrade.getStoploss().doubleValue(), currentTrade.getTp().doubleValue());
		else
			return 0d;
	}

	
	public double getCurrentRR(){
		if(canCalcStat())
			return getRR(getLatestOpenPrice().doubleValue(),getLatestSL().doubleValue(),getLatestTP().doubleValue());
		else
			return 0d;
	}
	
	public double getRealisedRR(){
		if(canCalcStat()  && currentTrade.getCloseprice() != null)
			return getRR(getLatestOpenPrice(),currentTrade.getStoploss().doubleValue(),currentTrade.getCloseprice().doubleValue());
		else
			return 0d;
	}
	
	
}
