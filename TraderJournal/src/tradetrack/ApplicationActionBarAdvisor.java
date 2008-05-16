package tradetrack;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iExitAction;
	private IContributionItem perspectivesMenu;
	
	
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	iExitAction = ActionFactory.QUIT.create(window);
		register(iExitAction);
		
		iAboutAction = ActionFactory.ABOUT.create(window);
		register(iAboutAction);
		perspectivesMenu = ContributionItemFactory.PERSPECTIVES_SHORTLIST
		.create(window);

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
		
		MenuManager layoutMenu = new MenuManager("Switch Layout", "layout");
		layoutMenu.add(perspectivesMenu);
		menuBar.add(layoutMenu);
		
		// Help Menu
		helpMenu.add(iAboutAction);

    }
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		toolbar.add(iExitAction);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));

	}
    
}
