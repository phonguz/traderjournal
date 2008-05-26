package traderjournal.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.editors.TradeEditor;
import traderjournal.model.TradeEvent;
import traderjournal.model.TradeEventImage;


public class ImageViewPart extends ViewPart implements ISelectionListener{
    public static final String ID_VIEW =
        "traderjournal.views.ImageViewPart"; //$NON-NLS-1$
        private CTabFolder cTabFolder;
        
        

    List<CTabItem> cTabItemList = new ArrayList<CTabItem>();
    TradeEventImage eventImage;
    TradeEvent tradeEvent;
    Composite parentComposite;
    /**
     * 
     */
    public ImageViewPart() {
        super();
       
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
 
    }
    
    /**
     * Cleans up all resources created by this ViewPart.
     */
    public void dispose() {
        super.dispose();
    }

	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if(selection.isEmpty())
			return;
		StructuredSelection sl = (StructuredSelection)selection;
		tradeEvent= (TradeEvent)sl.getFirstElement();
	
		
		for(CTabItem ct : cTabItemList){
			ct.getControl().dispose();
			ct.dispose();
			
		}
		cTabItemList.clear();
		
//		Control [] arc = cTabFolder.getChildren();
//	
//		for(int j =0; j < arc.length; j++){
//			arc[j].dispose();
//			arc[j] = null;
//			
//		}
//		
		cTabFolder.setTopRight(null);
		List<TradeEventImage> imglist = tradeEvent.getAllImages();
		int i = 1;
		for(TradeEventImage ti : imglist){
			CTabItem cti = new CTabItem(cTabFolder,SWT.NONE);
			cti.addListener(SWT.MouseDown,new Listener(){

				@Override
				public void handleEvent(Event event) {
					System.out.println(event.toString());
				}});
			
			
			GridData gData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
			Composite cmp = new Composite(cTabFolder,SWT.NONE);
			cmp.setLayoutData(gData);
			cmp.setLayout(new GridLayout(4,false));
			Label lbl = new Label(cmp,SWT.NONE);
			lbl.setText("Description");
			Text txt = new Text(cmp,SWT.NONE);
			txt.setSize(new Point(200,10));
			if (ti.getDescription() != null) {
				
			
				txt.setText(ti.getDescription());
				cti.setText("Image : " + i + " : " + ti.getDescription());
			}else{
				cti.setText("Image : " + i);
			}
			txt.setData(ti);
			txt.addModifyListener(new ModifyListener(){


				@Override
				public void modifyText(ModifyEvent e) {
					TradeEventImage it = (TradeEventImage)e.widget.getData();
					
					it.setDescription(((Text)e.widget).getText());
					System.out.println(it.getDescription());
					
					
				}});
			Button btnSAVE = new Button(cmp,SWT.PUSH);
			btnSAVE.setText("SAVE");
			btnSAVE.setData(ti);
			btnSAVE.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					TradeEventImage it = (TradeEventImage)e.widget.getData();

					it.save();
					
				}});

			
			
			Button btnREMOVE = new Button(cmp,SWT.PUSH);
			btnREMOVE.setText("DELETE");
			btnREMOVE.setData(ti);
			btnREMOVE.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					TradeEventImage it = (TradeEventImage)e.widget.getData();
					it.remove();
					cTabFolder.redraw();
					
				}});
			
			Canvas cv = new Canvas(cmp,SWT.NONE);
			GridData gridData = new GridData(GridData.FILL_BOTH
					| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
			gridData.grabExcessVerticalSpace = true;
			gridData.horizontalSpan =4;
			
			cv.setLayoutData(gridData);
			cv.setData(ti);
			cv.addPaintListener(new PaintListener(){

				@Override
				public void paintControl(PaintEvent e) {
					
					GC gc = e.gc;
					TradeEventImage ti = (TradeEventImage)e.widget.getData();
					if(ti.getImage().getImageData().width > 1024 || ti.getImage().getImageData().height > 768)
						gc.drawImage(new Image(ti.getImage().getDevice(), ti.getImage().getImageData().scaledTo(1024, 768)), 0, 0);
					else
						gc.drawImage(ti.getImage(), 0, 0);
				}
				
			});
			cv.setFocus();
			cv.redraw();
			cti.setControl(cmp);
			cmp.redraw();
			cTabItemList.add(cti);
			i++;
			cTabFolder.setSelection(i);
		}
		
		cTabFolder.setSelection(0);
		cTabFolder.redraw();
		
		
		
		
		
	}
    
}