package traderjournal;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.OpenPerspectiveAction;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction iAboutAction;
	private IWorkbenchAction iExitAction;

	
	
	private IWorkbenchAction preferenceAction;
	
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	iExitAction = ActionFactory.QUIT.create(window);
		register(iExitAction);
		
		iAboutAction = ActionFactory.ABOUT.create(window);
		
		
		
		register(iAboutAction);
		
		
		
		preferenceAction = ActionFactory.PREFERENCES.create(window);

		register(preferenceAction);

		


    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
		MenuManager helpMenu = new MenuManager("&Help",
				IWorkbenchActionConstants.M_HELP);
		
		
		// File Menu
		helpMenu.add(iExitAction);
		
		MenuManager layoutMenu = new MenuManager("Configuration", "window");
		
		//layoutMenu.add(perspectivesMenu);
		
		layoutMenu.add(preferenceAction);
		menuBar.add(layoutMenu);
		
		
		// Help Menu
		helpMenu.add(iAboutAction);
		
		menuBar.add(helpMenu);
		
        

		

    }
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		toolbar.add(iExitAction);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));

	}
    
}
