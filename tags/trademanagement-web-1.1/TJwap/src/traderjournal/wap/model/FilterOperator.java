package traderjournal.wap.model;

public class FilterOperator {
	public static final FilterOperator EQUAL = new FilterOperator("=");
	public static final FilterOperator GREATERTHAN = new FilterOperator(">");
	
	
	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	private String operator;
	
	
	public FilterOperator(String oper) {
			operator = oper;
	}
}
