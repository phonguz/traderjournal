package traderjournal;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.hsqldb.HsqlServerFactory;
import org.hsqldb.Server;

import sun.security.action.GetLongAction;
import traderjournal.model.DBUtils;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {
	public static ILog log;
	public final static String PLUGIN_ID = "TraderJournal";
	Server hs = new Server();
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) 
	throws Exception {
		
		hs.setDatabaseName(0, "TradeTrack");
		hs.setDatabasePath(0, "file:c:/dev/galileoworkspace/traderjournal/dbdata/TradeTrack");
		hs.start();
		
		
		Display display = PlatformUI.createDisplay();
		String statelocation = Activator.getDefault().getStateLocation().toOSString();
		log = Activator.getDefault().getLog();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
		hs.stop();
		hs.shutdown();
		
		
	}
	
	public static void logError(String message){
		Status st = new Status(Status.ERROR,Activator.PLUGIN_ID,message);
		log.log(st);
		st = null;
	}
	public static void logError(String message, Throwable ex){
		Status st = new Status(Status.ERROR,Activator.PLUGIN_ID,message,ex);
		log.log(st);
		st = null;
	}
	public static void logInfo(String message){
		Status st = new Status(Status.INFO,Activator.PLUGIN_ID,message);
		log.log(st);
		st = null;
	}
	
}
