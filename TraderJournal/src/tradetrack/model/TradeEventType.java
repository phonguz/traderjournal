package tradetrack.model;

import java.util.ArrayList;
import java.util.List;

public class TradeEventType {

	public static TradeEventType NONE = new TradeEventType(0,"NONE");
	public static TradeEventType LONG = new TradeEventType(1,"Long");
	public static TradeEventType SHORT = new TradeEventType(2,"Short");
	public static TradeEventType MOVESL = new TradeEventType(3,"Move SL");
	public static TradeEventType CLOSE = new TradeEventType(4,"Close");
	
	public static List<TradeEventType> getAll(){
		List<TradeEventType> ret = new ArrayList<TradeEventType>();
		ret.add(NONE);
		ret.add(LONG);
		ret.add(SHORT);
		ret.add(MOVESL);
		ret.add(CLOSE);
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
	
	
	
}
