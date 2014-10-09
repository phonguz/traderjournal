package traderjournal.stats;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Transaction;


import traderjournal.model.hibernate.Trade;
import traderjournal.model.hibernate.Tradeevent;
import traderjournal.model.hibernate.TradeeventHome;

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
	
	
	public TreeSet<Tradeevent> getOrderedEvents(Set<Tradeevent> set){
		TreeSet< Tradeevent> ts = new TreeSet<Tradeevent>(new Comparator<Tradeevent>(){

			@Override
			public int compare(Tradeevent o1, Tradeevent o2) {
				return o1.getEventorder().compareTo(o2.getEventorder());
				
			}
			
		});
		ts.addAll(set);
		return ts;
	}
	
	public Double getLatestOpenPrice(){
		TradeeventHome teh = new TradeeventHome();
		Transaction tx = teh.getSessionFactory().getCurrentSession().beginTransaction();
		teh.getSessionFactory().getCurrentSession().refresh(currentTrade);
		Set<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getOpenprice();
		for(Tradeevent te : getOrderedEvents(events)){
			if(te.getTradeeventtype().getName().equals("Add To Position") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}
		tx.commit();
		return ret;
	}
	
	public Double getLatestSL(){
		TradeeventHome teh = new TradeeventHome();
		Transaction tx = teh.getSessionFactory().getCurrentSession().beginTransaction();
		teh.getSessionFactory().getCurrentSession().refresh(currentTrade);
		Set<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getStoploss();
		for(Tradeevent te : getOrderedEvents(events)){
			if(te.getTradeeventtype().getName().equals("Move SL") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}
		tx.commit();
		return ret;
	}
	
	public Double getLatestTP(){
		TradeeventHome teh = new TradeeventHome();
		Transaction tx = teh.getSessionFactory().getCurrentSession().beginTransaction();
		teh.getSessionFactory().getCurrentSession().refresh(currentTrade);
		Set<Tradeevent> events = currentTrade.getTradeevents();
		Double ret = currentTrade.getTp();
		for(Tradeevent te : getOrderedEvents(events)){
			if(te.getTradeeventtype().getName().equals("Move TP") && te.getNewValue() !=null && te.getNewValue()!= 0d){
				ret = te.getNewValue();
			}
			
		}
		tx.commit();
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
