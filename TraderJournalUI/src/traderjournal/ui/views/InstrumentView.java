package traderjournal.ui.views;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
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
import org.hibernate.Transaction;

import traderjournal.model.hibernate.Ccy;
import traderjournal.model.hibernate.CcyHome;
import traderjournal.model.hibernate.Instrument;
import traderjournal.model.hibernate.InstrumentHome;

public class InstrumentView extends ViewPart {
	Composite composite1;
	Combo cmbIns;
	Text txtName;
	Combo cmbCCY;
	Text txtValuePerPoint;
	InstrumentHome ih = new InstrumentHome();
	Instrument currentIns;
	public final static String ID = "traderjournal.ui.views.InstrumentView";

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
				CcyHome ch = new CcyHome();
				Ccy ccy = ch.findAll().get(0);

				Transaction tx = ih.getSessionFactory().getCurrentSession()
						.beginTransaction();
				Instrument ins = new Instrument();
				ih.getSessionFactory().getCurrentSession().refresh(ccy);
				ins.setCcy(ccy);
				ins.setName("1New");
				ins.setValuePerPoint(0d);
				ih.persist(ins);
				tx.commit();
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

				Transaction tx = ih.getSessionFactory().getCurrentSession()
						.beginTransaction();

				ih.getSessionFactory().getCurrentSession().refresh(currentIns);
				ih.delete(currentIns);
				tx.commit();

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

				Transaction tx = ih.getSessionFactory().getCurrentSession()
						.beginTransaction();
				ih.getSessionFactory().getCurrentSession().refresh(currentIns);
				ih.getSessionFactory().getCurrentSession().refresh(ccy);
				currentIns.setCcy(ccy);
				currentIns.setName(txtName.getText());
				currentIns.setValuePerPoint(Double.parseDouble(txtValuePerPoint.getText()));
				ih.merge(currentIns);
				tx.commit();
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
			CcyHome ch = new CcyHome();
			List<Ccy> li = ch.findAll();
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

		List<Instrument> li = ih.findAll();
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
