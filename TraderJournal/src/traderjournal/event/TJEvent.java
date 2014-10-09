package traderjournal.event;

import java.util.HashMap;
import java.util.Map;

public class TJEvent {

	private String action;
	private Object data;

	public Map<String,Object> getMap(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(ACTION_PROPERTY_NAME, action);
		map.put(DATA_PROPERTY_NAME, data);
		return map;
		
	}
	
	public TJEvent(String action, Object data) {
		super();
		this.action = action;
		this.data = data;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static final String ACTION_PROPERTY_NAME = "ACTION";
	public static final String DATA_PROPERTY_NAME = "DATA";
	public static String EVENT_ACTION_ANY = "ANY";
	public static String EVENT_ACTION_REMOVE = "REMOVE";
	public static String EVENT_ACTION_ADD = "ADD";
	public static String EVENT_ACTION_UPDATE = "UPDATE";
	

	


}
