package traderjournal.views;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.hibernate.Trade;
import traderjournal.stats.TradeStatistics;
import traderjournal.views.labelproviders.LabelUtils;

public class TradeStatisticsView extends ViewPart implements ISelectionListener {
	public static final String ID = "traderjournal.views.TradeStatisticsView";
	private Trade currentTrade;
	private TradeStatistics ts;
	Composite composite1;
	private Text originalRR;
	private Text currentRR;
	private Text realisedRR;

	@Override
	public void createPartControl(Composite parent) {

		getSite().getPage().addSelectionListener(TradeListView.ID,
				(ISelectionListener) this);
		parent.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite1 = new Composite(parent, SWT.NULL);
		composite1.setLayout(gl);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		composite1.setLayoutData(gd);

		Label lblOr = new Label(composite1, SWT.NONE);
		lblOr.setText("Ori RR");
		originalRR = new Text(composite1, SWT.NONE);
		originalRR.setText("");

		Label lblcur = new Label(composite1, SWT.NONE);
		lblcur.setText("Cur RR");
		currentRR = new Text(composite1, SWT.NONE);
		currentRR.setText("");

		Label lblreal = new Label(composite1, SWT.NONE);
		lblreal.setText("Real RR");
		realisedRR = new Text(composite1, SWT.NONE);
		realisedRR.setText("");

	}

	@Override
	public void setFocus() {

	}

	public void refreshStatistics() {
		NumberFormat nf = LabelUtils.nf;
		if (currentTrade != null && ts.canCalcStat()) {
			originalRR.setText(nf.format(ts.getOriginalRR()));
			currentRR.setText(nf.format(ts.getCurrentRR()));
			realisedRR.setText(nf.format(ts.getRealisedRR()));
		}
		composite1.redraw();

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			if(((IStructuredSelection) selection)
					.getFirstElement() instanceof Trade){
				Trade newTrade = (Trade) ((IStructuredSelection) selection)
						.getFirstElement();
				if (newTrade != null) {
					currentTrade = newTrade;
					ts = new TradeStatistics(currentTrade);
					refreshStatistics();
				}
			}
		}

	}

}
