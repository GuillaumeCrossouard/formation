package com.magellium.rental.ui.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.browser.Browser;

public class RentalPropertyView extends ViewPart {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Label startDateLabel;
	private Label endDateLabel;
	private Label lblLou;
	private Label lblDu;
	private Label lblAu;
	private Table table;
	private TableItem tableItem;
	private TableItem tableItem_1;
	private Table table_1;
	private TableViewer tableViewer;
	private TableItem tableItem_2;
	private TabFolder tabFolder;
	private TabItem tbtmNewItem;
	private TabItem tbtmNewItem_2;
	private TableItem tableItem_3;
	private TableItem tableItem_4;


	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		tabFolder = new TabFolder(parent, SWT.NONE);
		
		tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("New Item");
		
		Group infoGroup = new Group(parent, SWT.NONE);
		GridData gd_infoGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_infoGroup.widthHint = 232;
		infoGroup.setLayoutData(gd_infoGroup);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(3, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 3; // occupe les 2 premières colonnes
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		lblLou = new Label(infoGroup, SWT.NONE);
		lblLou.setText("Lou\u00E9 \u00E0 :");
		
		customerLabel = new Label(infoGroup, SWT.NONE);
		GridData gd2 = new GridData();
		gd2.horizontalSpan = 2;
		gd2.horizontalAlignment = SWT.FILL;
		customerLabel.setLayoutData(gd2);
		
		Group dateGroup = new Group(parent, SWT.NONE);
		GridData gd_dateGroup = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateGroup.widthHint = 232;
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
		
		table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_table.heightHint = 72;
		gd_table.widthHint = 363;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");
		
		tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("New TableItem");
		
		tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText("New TableItem");
		
		tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText("New TableItem");
		
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		tableItem_2 = new TableItem(table_1, SWT.NONE);
		tableItem_2.setText("New TableItem");
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(1));
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	public void setRental(Rental r){
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName());
		startDateLabel.setText(r.getStartDate().toString());
		endDateLabel.setText(r.getEndDate().toString());
	}
	
}
