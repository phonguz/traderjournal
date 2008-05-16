package tradetrack.views;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tradetrack.Activator;
import tradetrack.editors.TradeEditor;
import tradetrack.model.TradeEvent;
import tradetrack.model.TradeEventImage;


public class ImageViewPart extends ViewPart implements ISelectionListener{
    public static final String ID_VIEW =
        "tradetrack.views.ImageViewPart"; //$NON-NLS-1$
        private CTabFolder cTabFolder;
        private CTabItem cTabItem1;
        

    
    TradeEventImage eventImage;
    TradeEvent tradeEvent;
    Composite parentComposite;
    /**
     * 
     */
    public ImageViewPart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	parentComposite = parent;
    	getSite().getPage().addSelectionListener(TradeEditor.ID,
				(ISelectionListener) this);

        {

        	{
        		cTabFolder = new CTabFolder(parent, SWT.CLOSE | SWT.TOP);
        		cTabFolder.setBorderVisible(true);
        		cTabFolder.setSimple(false);
        		parent.setLayout(new GridLayout());
        		GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        		cTabFolder.setTabPosition(SWT.TOP);
        		cTabFolder.setTabHeight(30);
        		//cTabFolder.setSingle(true);
        		cTabFolder.setLayoutData(gd);

        		{

        		}
        		cTabFolder.setSelection(0);
        	}
        }
        
        parent.pack();
        
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        // TODO Auto-generated method stub
    }
    
    /**
     * Cleans up all resources created by this ViewPart.
     */
    public void dispose() {
        super.dispose();
    }

	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		System.out.println(selection.toString());
		if(selection.isEmpty())
			return;
		StructuredSelection sl = (StructuredSelection)selection;
		tradeEvent= (TradeEvent)sl.getFirstElement();
	
		
		Control [] arc = cTabFolder.getChildren();
	
		for(int j =0; j < arc.length; j++){
			arc[j].dispose();
			arc[j] = null;
			
		}
		cTabFolder.setTopRight(null);
		List<TradeEventImage> imglist = tradeEvent.getAllImages();
		int i = 1;
		for(TradeEventImage ti : imglist){
			CTabItem cti = new CTabItem(cTabFolder,SWT.NONE);
			
			cti.setText("Image : " + i);
			GridData gData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
			Composite cmp = new Composite(cTabFolder,SWT.NONE);
			cmp.setLayoutData(gData);
			
			cmp.setBackgroundImage(ti.getImage());
			cti.setControl(cmp);
			i++;
		}
		
		cTabFolder.setSelection(0);
		cTabFolder.redraw();
		
		
		
		
	}
    
}
