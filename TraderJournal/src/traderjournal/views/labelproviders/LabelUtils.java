package traderjournal.views.labelproviders;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class LabelUtils {
	
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat getDateFormat(){
		return df;
		

	}
	public static NumberFormat nf =new DecimalFormat("####.##"); 
	
	public static NumberFormat priceFormat = new DecimalFormat("###,###,###,###.##");
	
}
