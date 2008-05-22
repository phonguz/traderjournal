package tradetrack.views;

import java.net.URL;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tradetrack.Activator;
import tradetrack.model.Trade;

public class ReportView extends ViewPart implements ISelectionListener{
	private Trade trade;
	
	public static final String ID = "tradetrack.views.ReportView";
	@Override
	public void createPartControl(Composite parent) {
		
			getSite().getPage().addSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
			
	

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void executeReport(int tradeid) throws EngineException
	{

	IReportEngine engine=null;
	EngineConfig config = null;

	try{
		config = new EngineConfig( );			
	

		IReportEngineFactory factory = (IReportEngineFactory) Platform
		.createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
		engine = factory.createReportEngine( config );		

		IReportRunnable design = null;
		//Open the report design
		// URL to the root ("/") of the plugin-path:
		URL relativeURL = Activator.getDefault().getBundle().getEntry("/");


		// Turn relative path to a local path with the help of Eclipse-platform:
		URL localURL = Platform.asLocalURL(relativeURL);


		// From this you can get the path
		String pluginDirString = localURL.getPath();

		
		design = engine.openReportDesign(pluginDirString+ "reports/tradereport.rptdesign"); 
		IRunAndRenderTask task = engine.createRunAndRenderTask(design); 		
		task.setParameterValue("TradeId", (new Integer(tradeid)));
		//task.validateParameters();
				
		//HTMLRenderOption options = new HTMLRenderOption();		
		//options.setOutputFileName(pluginDirString+"output/tradereport.html");
		//options.setOutputFormat("html");
		//options.setHtmlRtLFlag(false);
		//options.setEmbeddable(false);
		//options.setImageDirectory("C:\\test\\images");

		PDFRenderOption options = new PDFRenderOption();
		options.setOutputFileName(pluginDirString+"output/test.pdf");
		options.setOutputFormat("pdf");

		task.setRenderOption(options);
		task.run();
		task.close();
		engine.destroy();
	}catch( Exception ex){
		ex.printStackTrace();
	}		
	finally
	{
	       Platform.shutdown( );
	}

	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Trade newTrade = (Trade) ((IStructuredSelection) selection)
					.getFirstElement();
			trade = newTrade;

			try {
				executeReport(trade.getId());
			} catch (EngineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
