package tradetrack.actions;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import tradetrack.Application;


public class PreferenceAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run(IAction action) {

		// This would be using instance scope
		// Preferences preferences = new InstanceScope()
		// .getNode(Application.PLUGIN_ID);
		// This is using configuration scope
		Preferences preferences = new ConfigurationScope()
				.getNode(Application.PLUGIN_ID);
		// This would be using default n scope
		// Preferences preferences = new DefaultScope()
		// .getNode(Application.PLUGIN_ID);
		Preferences sub1 = preferences.node("note1");
		Preferences sub2 = preferences.node("node2");
		sub1.put("h1", "Hello");
		sub1.put("h2", "Hello again");
		sub2.put("h1", "Moin");
		MessageDialog.openInformation(window.getShell(), "Info", sub1.get("h1",
				"default"));
		MessageDialog.openInformation(window.getShell(), "Info", sub1.get("h2",
				"default"));
		MessageDialog.openInformation(window.getShell(), "Info", sub2.get("h1",
				"default"));
		// Forces the application to save the preferences
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}


}
