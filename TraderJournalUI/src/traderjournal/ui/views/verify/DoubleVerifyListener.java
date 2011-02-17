package traderjournal.ui.views.verify;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class DoubleVerifyListener implements VerifyListener{

	
		public void verifyText(VerifyEvent e) {
			// Here, we could use a RegExp such as the following
			// if using JRE1.4 such as e.doit =
			// e.text.matches("[\\-0-9]*");
			boolean ret = true;
			for(int i=0; i < e.text.length(); i ++){
				ret = "0123456789.".indexOf(e.text.charAt(i)) >= 0;	
			}
			
			e.doit = ret;
		}


}
