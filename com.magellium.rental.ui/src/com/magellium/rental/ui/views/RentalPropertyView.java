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

public class RentalPropertyView extends ViewPart implements ISelectionListener{

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
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_table.heightHint = 72;
		gd_table.widthHint = 279;
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
		
		 Table table2 = new Table(parent, SWT.MULTI | SWT.BORDER
			        | SWT.FULL_SELECTION);
		 table2.setLinesVisible(true);
		 table2.setHeaderVisible(true);
			    String[] titles = { " ", "C", "!", "Description", "Resource",
			        "In Folder", "Location" };
			    for (int i = 0; i < titles.length; i++) {
			      TableColumn column = new TableColumn(table2, SWT.NONE);
			      column.setText(titles[i]);
			    }
			    int count = 128;
			    for (int i = 0; i < count; i++) {
			      TableItem item = new TableItem(table2, SWT.NONE);
			      item.setText(0, "x");
			      item.setText(1, "y");
			      item.setText(2, "!");
			      item.setText(3, "this stuff behaves the way I expect");
			      item.setText(4, "almost everywhere");
			      item.setText(5, "some.folder");
			      item.setText(6, "line " + i + " in nowhere");
			    }
			    for (int i = 0; i < titles.length; i++) {
			    	table2.getColumn(i).pack();
			    }
			    table2.setSize(table2.computeSize(SWT.DEFAULT, 200));		
		
		
		
		
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

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection){
			Object select = ((IStructuredSelection)selection).getFirstElement();
			if (select instanceof Rental){
				setRental((Rental)select);
			} else {
				System.out.println("selection non prise en compte");
			}
		}
	}
	
	//drag & drop
	public void setLabelAsDragSource(final Label label){
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[]{TextTransfer.getInstance()});
		
		source.addDragListener(new DragSourceAdapter(){
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)){
					event.data = label.getText();
				}
			}
		});
	}
	

	
}
