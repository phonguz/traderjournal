package traderjournal.views;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

import traderjournal.Activator;
import traderjournal.Application;
import traderjournal.model.Trade;

public class ReportView extends ViewPart implements ISelectionListener {
	private Trade trade;
	private IReportEngineFactory factory;
	IReportEngine engine = null;
	EngineConfig config = null;
	Browser browser = null;

	String reportDir;
	String outputDir;

	public static final String ID = "traderjournal.views.ReportView";

	@Override
	public void createPartControl(Composite parent) {

		getSite().getPage().addSelectionListener(TradeReportListView.ID,
				(ISelectionListener) this);
		browser = new Browser(parent, SWT.NONE);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	String getPDFReportURL() {
		Bundle bd = org.eclipse.core.runtime.Platform.getBundle(Activator.PLUGIN_ID);
		Enumeration<String> epaths = bd.getEntryPaths("/");
		System.out.println(bd.getLocation());
		while(epaths.hasMoreElements() ){
			String  path = epaths.nextElement();
			Application.logError(path);
			System.out.println(path);
		}
	//	Application.logError("Bundle Location" + FileLocator.toFileURL( bd.get).);
		
		if (reportDir == null) {
			try {
				
				
				
				reportDir = FileLocator.toFileURL(
						bd.getEntry("reports"))
						.getPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (outputDir == null) {
			try {
				outputDir = FileLocator.toFileURL(
						org.eclipse.core.runtime.Platform.getBundle(Activator.PLUGIN_ID).getEntry("output"))
						.getPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outputDir + "Trade" + trade.getId() + ".pdf";

	}

	public void executeReport() throws EngineException {

		try {
			getPDFReportURL();
			this.showBusy(true);
			if (config == null)
				config = new EngineConfig();

			if (factory == null)
				factory = (IReportEngineFactory) Platform
						.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

			if (engine == null)
				engine = factory.createReportEngine(config);

			IReportRunnable design = null;
			// Open the report design
			// URL to the root ("/") of the plugin-path:

			design = engine.openReportDesign(reportDir + "/tradereport.rptdesign");
			IRunAndRenderTask task = engine.createRunAndRenderTask(design);
			task.setParameterValue("TradeId", (new Integer(trade.getId())));
			// task.validateParameters();

			// HTMLRenderOption options = new HTMLRenderOption();
			// options.setOutputFileName(pluginDirString+"output/tradereport.html");
			// options.setOutputFormat("html");
			// options.setHtmlRtLFlag(false);
			// options.setEmbeddable(false);
			// options.setImageDirectory("C:\\test\\images");

			PDFRenderOption options = new PDFRenderOption();
			options.setOutputFileName(getPDFReportURL());
			options.setOutputFormat("pdf");

			Application.logInfo("pdf written:" + getPDFReportURL());

			task.setRenderOption(options);
			task.run();
			task.close();
			// engine.destroy();

			// engine = null;

			// task = null;
			this.showBusy(false);
		} catch (Exception ex) {
			Application.logError("General PDF creation Erro", ex);
			ex.printStackTrace();
		} finally {
			// Platform.shutdown( );
		}

	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Trade newTrade = (Trade) ((IStructuredSelection) selection)
					.getFirstElement();
			trade = newTrade;

			try {

				executeReport();
				displyPDFinBrowser();
				System.out.println("display pdf:" + getPDFReportURL());
			} catch (EngineException e) {
				Application.logError("In SelectionChanged, show PDF", e);
				e.printStackTrace();
			}
		}
	}

	private void displyPDFinBrowser() {
		String fname = "file://" + getPDFReportURL();
		browser.setUrl(fname);

	}
}
