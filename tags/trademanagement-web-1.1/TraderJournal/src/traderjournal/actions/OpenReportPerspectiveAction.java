package traderjournal.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;

import traderjournal.AccountPerspective;
import traderjournal.ReportPerspective;

public class OpenReportPerspectiveAction implements
		IWorkbenchWindowActionDelegate {
	IWorkbenchWindow wb ;
	@Override
	public void dispose() {
		

	}

	@Override
	public void init(IWorkbenchWindow window) {
	
			wb = window;

	}

	@Override
	public void run(IAction action) {
		try {
			wb.getWorkbench().showPerspective(ReportPerspective.ID,wb);
		} catch (WorkbenchException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		

	}

}
