package traderjournal.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;

import traderjournal.AccountPerspective;

public class OpenAccountPerspectiveAction implements
		IWorkbenchWindowActionDelegate {
	IWorkbenchWindow wb ;
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
	
			wb = window;

	}

	@Override
	public void run(IAction action) {
		try {
			wb.getWorkbench().showPerspective(AccountPerspective.ID,wb);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
