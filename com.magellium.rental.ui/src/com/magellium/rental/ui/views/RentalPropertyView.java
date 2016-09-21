package com.magellium.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Label startDateLabel;
	private Label endDateLabel;
	private Label lblLou;
	private Label lblDu;
	private Label lblAu;

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_infoGroup.widthHint = 340;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(3, false));

		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 3; // occupe les 2 premières colonnes
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		setLabelAsDragSource(rentedObjectLabel);

		lblLou = new Label(infoGroup, SWT.NONE);
		lblLou.setText("Lou\u00E9 \u00E0 :");

		customerLabel = new Label(infoGroup, SWT.NONE);
		GridData gd2 = new GridData();
		gd2.horizontalSpan = 2;
		gd2.horizontalAlignment = SWT.FILL;
		customerLabel.setLayoutData(gd2);
		setLabelAsDragSource(customerLabel);

		Group dateGroup = new Group(parent, SWT.NONE);
		GridData gd_dateGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateGroup.widthHint = 340;
		dateGroup.setLayoutData(gd_dateGroup);
		dateGroup.setText("Dates");
		dateGroup.setLayout(new GridLayout(2, false));

		lblDu = new Label(dateGroup, SWT.NONE);
		lblDu.setText("du : ");

		startDateLabel = new Label(dateGroup, SWT.NONE);
		GridData gd3 = new GridData();
		gd3.horizontalAlignment = SWT.FILL;
		startDateLabel.setLayoutData(gd3);

		lblAu = new Label(dateGroup, SWT.NONE);
		lblAu.setText("au :");

		endDateLabel = new Label(dateGroup, SWT.NONE);

		setRental(RentalCoreActivator.getAgency().getRentals().get(1));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName());
		startDateLabel.setText(r.getStartDate().toString());
		endDateLabel.setText(r.getEndDate().toString());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object select = ((IStructuredSelection) selection).getFirstElement();
			if (select instanceof Rental) {
				setRental((Rental) select);
			} else {
				System.out.println("selection non prise en compte");
			}
		}
	}

	// drag & drop
	public void setLabelAsDragSource(final Label label) {
	}

}
