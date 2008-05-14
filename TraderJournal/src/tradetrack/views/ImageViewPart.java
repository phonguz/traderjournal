package tradetrack.views;

import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

import tradetrack.editors.TradeEditor;
import tradetrack.model.TradeEventImage;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ImageViewPart extends ViewPart implements ISelectionListener{
    public static final String ID_VIEW =
        "tradetrack.views.ImageViewPart"; //$NON-NLS-1$
        private Canvas canvasIMGDisplay;

    Composite composite1;
    TradeEventImage eventImage;
    
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
    	getSite().getPage().addSelectionListener(TradeEditor.ID,
				(ISelectionListener) this);
        composite1 = new Composite(parent, SWT.NULL);
        
        composite1.setLayout(new GridLayout(4, false));
        {
        	GridData canvasIMGDisplayLData = new GridData();
        	canvasIMGDisplayLData.grabExcessHorizontalSpace = true;
        	canvasIMGDisplayLData.grabExcessVerticalSpace = true;
        	canvasIMGDisplayLData.horizontalAlignment = GridData.FILL;
        	canvasIMGDisplayLData.verticalAlignment = GridData.FILL;
        	canvasIMGDisplay = new Canvas(composite1, SWT.NONE);
        	canvasIMGDisplay.setLayoutData(canvasIMGDisplayLData);
        }
        
        parent.pack();
        parent.redraw();
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
		// TODO Auto-generated method stub
		
	}
    
}
