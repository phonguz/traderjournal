package traderjournal.model;

import java.util.ArrayList;
import java.util.List;

public class TradeEventType {

	public static TradeEventType NONE = new TradeEventType(0,"NONE");
	public static TradeEventType LONG = new TradeEventType(1,"Long");
	public static TradeEventType SHORT = new TradeEventType(2,"Short");
	public static TradeEventType MOVESL = new TradeEventType(3,"Move SL");
	public static TradeEventType CLOSE = new TradeEventType(4,"Close");
	public static TradeEventType REVIEW = new TradeEventType(5,"Review");
	public static TradeEventType MOVETP = new TradeEventType(6,"Move TP");
	public static TradeEventType UPDATE = new TradeEventType(7,"Update"); //for intra trade updates / info
	
	
	
	public static List<TradeEventType> getAll(){
		List<TradeEventType> ret = new ArrayList<TradeEventType>();
		ret.add(NONE);
		ret.add(LONG);
		ret.add(SHORT);
		ret.add(MOVESL);
		ret.add(CLOSE);
		ret.add(REVIEW);
		ret.add(MOVETP);
		ret.add(UPDATE);
		return ret;
	}
	
	public TradeEventType(int id, String nm){
		setID(id);
		setName(nm);
	}
	
	private int ID;
	private String Name;
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public static TradeEventType getTradeEventType(int eventtype) {
		return getAll().get(eventtype);
	}
	
	
	
}
