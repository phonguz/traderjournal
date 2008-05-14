package tradetrack;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iExitAction;
	
	
	
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	iExitAction = ActionFactory.QUIT.create(window);
		register(iExitAction);
		
		iAboutAction = ActionFactory.ABOUT.create(window);
		register(iAboutAction);

    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager("&File",
				IWorkbenchActionConstants.M_FILE);
		MenuManager helpMenu = new MenuManager("&Help",
				IWorkbenchActionConstants.M_HELP);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		// File Menu
		fileMenu.add(iExitAction);
		// Help Menu
		helpMenu.add(iAboutAction);

    }
    
}
