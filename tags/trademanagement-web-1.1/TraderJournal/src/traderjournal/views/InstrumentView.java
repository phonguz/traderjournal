package traderjournal.views;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transaction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import traderjournal.model.RequestFactoryUtilsJpa;
import traderjournal.model.entities.Ccy;
import traderjournal.model.entities.Instrument;

public class InstrumentView extends ViewPart {
	Composite composite1;
	Combo cmbIns;
	Text txtName;
	Combo cmbCCY;
	Text txtValuePerPoint;
	
	Instrument currentIns;
	public final static String ID = "traderjournal.views.InstrumentView";

	@Override
	public void createPartControl(Composite parent) {
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

		Label lblIns = new Label(composite1, SWT.NONE);
		lblIns.setText("Instruments");
		cmbIns = new Combo(composite1, SWT.NONE);
		cmbIns.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Instrument ins = (Instrument) cmbIns.getData(cmbIns.getText());
				currentIns = ins;
				refreshEditArea();

			}

		});

		Label lblInsName = new Label(composite1, SWT.NONE);
		lblInsName.setText("Name");
		txtName = new Text(composite1, SWT.NONE);

		Label lblValuePerPoint = new Label(composite1, SWT.NONE);
		lblValuePerPoint.setText("Value/Point");
		txtValuePerPoint = new Text(composite1, SWT.NONE);

		Label lblCCY = new Label(composite1, SWT.NONE);
		lblCCY.setText("CCY");
		cmbCCY = new Combo(composite1, SWT.NONE);

		Button btnAdd = new Button(composite1, SWT.PUSH);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Ccy ccy = Ccy.findAll().get(0);

				
				Instrument ins = new Instrument();
	
				ins.setCcy(ccy);
				ins.setName("1New");
				ins.setValuePerPoint(0d);
				RequestFactoryUtilsJpa.persist(ins);
				refreshAll();

			}

		});
		Button btnRemove = new Button(composite1, SWT.PUSH);
		btnRemove.setText("Remove");
		btnRemove.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {

				RequestFactoryUtilsJpa.remove(currentIns);
				

				refreshAll();

			}

		});

		Button btnSave = new Button(composite1, SWT.PUSH);
		btnSave.setText("Update");
		btnSave.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Ccy ccy = (Ccy)cmbCCY.getData(cmbCCY.getText());

				
				currentIns.setCcy(ccy);
				currentIns.setName(txtName.getText());
				currentIns.setValuePerPoint(Double.parseDouble(txtValuePerPoint.getText()));
				RequestFactoryUtilsJpa.persist(currentIns);
				
				refreshAll();
				
				
			}

		});
		
		refreshAll();

	}

	private void refreshEditArea() {

		if (currentIns != null) {
			txtName.setText(currentIns.getName());
			if (currentIns.getValuePerPoint() != null)
				txtValuePerPoint.setText(currentIns.getValuePerPoint()
						.toString());
			else
				txtValuePerPoint.setText("0");
			cmbCCY.removeAll();
			
			List<Ccy> li = Ccy.findAll();
			int i = 0;
			for (Ccy cc : li) {
				cmbCCY.add(cc.getName());
				cmbCCY.setData(cc.getName(), cc);
				if (currentIns.getCcy().getId() == cc.getId()) {
					cmbCCY.select(i);
				}
				i++;
			}
		}

	}

	public void refreshAll() {
		cmbIns.removeAll();

		List<Instrument> li = Instrument.findAll();
		Collections.sort(li);
		if (li != null && li.size() > 0) {

			for (Instrument ins : li) {
				cmbIns.add(ins.getName());
				cmbIns.setData(ins.getName(), ins);
			}
			currentIns = (Instrument) li.get(0);
			cmbIns.select(0);
		}
		refreshEditArea();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
