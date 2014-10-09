package traderjournal.views.verify;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class IntegerVerifyListener implements VerifyListener{

	
		public void verifyText(VerifyEvent e) {
			// Here, we could use a RegExp such as the following
			// if using JRE1.4 such as e.doit =
			// e.text.matches("[\\-0-9]*");
			e.doit = "0123456789".indexOf(e.text) >= 0;
		}


}
