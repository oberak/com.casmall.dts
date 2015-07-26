package com.casmall.dts.ui.home;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.jface.gridviewer.GridTableViewer;
import org.eclipse.nebula.jface.gridviewer.GridViewerEditor;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.weigh.dialog.WeighSecondDialog;
import com.swtdesigner.SWTResourceManager;

/**
 * HomeView 계류차량 정보 표기
 * 
 * @author OBERAK
 */
public class WeighFstInftComposite extends org.eclipse.swt.widgets.Composite {
	private final Image titleLeft = ImageRepository.getImage(ImageRepository.HOME_GRID_TITLE_LEFT);
	private final Image titleCenter = ImageRepository.getImage(ImageRepository.HOME_GRID_TITLE_CENTER);
	private final Image titleRight = ImageRepository.getImage(ImageRepository.HOME_GRID_TITLE_RIGHT);

	private final Image gridLeft = ImageRepository.getImage(ImageRepository.HOME_GRID_LEFT);
	private final Image gridRight = ImageRepository.getImage(ImageRepository.HOME_GRID_RITHT);

	private final Image gridCenterBottom = ImageRepository.getImage(ImageRepository.HOME_GRID_CENTER_BOTTOM);
	private final Image gridLeftBottom = ImageRepository.getImage(ImageRepository.HOME_GRID_LEFT_BOTTOM);
	private final Image gridRightBottom = ImageRepository.getImage(ImageRepository.HOME_GRID_RIGHT_BOTTOM);

	private final Font titleFont = SWTResourceManager.getFont("굴림", 14, SWT.BOLD);

	private String[] colProp = { "no", "car_num", "fst_wgt_dt", "fst_wgt_dt", "fst_wgh" };
	private String[] colName = { "No", "차량번호", "일자", "시간", "중량   " };
	/** 컬럼 너비 : 퍼센트 */
	private int[] colWidth = { 10, 30, 27, 15, 18 };
	private int[] colAlign = { SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.RIGHT };

	private Label lblTitle;
	private Label lblCount;
	private GridTableViewer gridViewer;
	private TsWgtInfDTO[] listData;

	/**
	 * Auto-generated main method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void main(String[] args) {
		showGUI();
	}

	/**
	 * Overriding checkSubclass allows this class to extend
	 * org.eclipse.swt.widgets.Composite
	 */
	protected void checkSubclass() {
	}

	/**
	 * Auto-generated method to display this org.eclipse.swt.widgets.Composite
	 * inside a new Shell.
	 */
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		WeighFstInftComposite inst = new WeighFstInftComposite(shell, SWT.NONE);
		Point size = inst.getSize();
		shell.setLayout(new GridLayout());
		shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
//		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.layout();
		if (size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}// showGUI

	public WeighFstInftComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		initGUI();
	}// WeighFstInftComposite

	private void initGUI() {
		try {
			final GridLayout gLayout = new GridLayout(3, false);
			gLayout.horizontalSpacing = 0;
			gLayout.marginHeight = 0;
			gLayout.marginWidth = 0;
			gLayout.marginWidth = 0;
			gLayout.verticalSpacing = 0;
			setLayout(gLayout);

			// title left part
			Label label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
			label.setImage(titleLeft);

			// title center part
			final Composite centerComposite = new Composite(this, SWT.NONE);
			centerComposite.setBackgroundImage(titleCenter);
			centerComposite.setBackgroundMode(SWT.INHERIT_FORCE);
			GridData gd_centerComposite = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
			gd_centerComposite.heightHint = 36;
			centerComposite.setLayoutData(gd_centerComposite);
			GridLayout wLayout = new GridLayout(3, false);
			wLayout.horizontalSpacing = 0;
			wLayout.marginTop = 6;
			wLayout.verticalSpacing = 6;
			wLayout.marginBottom = 5;
			centerComposite.setLayout(wLayout);

			lblTitle = new Label(centerComposite, SWT.LEFT);
			lblTitle.setFont(titleFont);
			lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
			lblTitle.setText("계류 차량");
			lblTitle.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));

			lblCount = new Label(centerComposite, SWT.RIGHT);
			lblCount.setFont(titleFont);
			GridData gd_lblCount = new GridData(SWT.LEFT, SWT.BEGINNING, false, false);
			gd_lblCount.widthHint = 70;
			lblCount.setLayoutData(gd_lblCount);
			lblCount.setText("0");

			label = new Label(centerComposite, SWT.NONE);
			label.setFont(titleFont);
			label.setLayoutData(new GridData(SWT.LEFT, SWT.BEGINNING, false, false));
			label.setText(" 건");

			// title right part
			label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
			label.setImage(titleRight);

			// grid left
			label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
			label.setBackgroundImage(gridLeft);

			// grid
			initGird();

			// grid right
			label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
			label.setBackgroundImage(gridRight);

			// bottom left
			label = new Label(this, SWT.NONE);
			GridData gdl_label = new GridData(SWT.FILL, SWT.BEGINNING, false, false);
			gdl_label.heightHint = 7;
			label.setLayoutData(gdl_label);
			label.setBackgroundImage(gridLeftBottom);

			// bottom center
			label = new Label(this, SWT.NONE);
			GridData gdc_label = new GridData(SWT.FILL, SWT.BEGINNING, false, false);
			gdc_label.heightHint = 7;
			label.setLayoutData(gdc_label);
			label.setBackgroundImage(gridCenterBottom);

			// bottom right
			label = new Label(this, SWT.NONE);
			GridData gdr_label = new GridData(SWT.FILL, SWT.BEGINNING, false, false);
			gdr_label.heightHint = 7;
			label.setLayoutData(gdr_label);
			label.setBackgroundImage(gridRightBottom);

			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//initGUI

	private void initGird() {
		gridViewer = new GridTableViewer(this, SWT.V_SCROLL | SWT.BORDER);
		gridViewer.setLabelProvider(new ListGridLabelProvider());
		gridViewer.setContentProvider(new ListGridContentProvider());
		gridViewer.getGrid().setSelectionEnabled(true);
		gridViewer.getGrid().setFont(SWTResourceManager.getFont("Arial", 15, SWT.NORMAL));
		gridViewer.getGrid().setItemHeight(30);
		
		gridViewer.setCellEditors(new CellEditor[] { new TextCellEditor(gridViewer.getGrid()),
		        new TextCellEditor(gridViewer.getGrid()) });
		gridViewer.setCellModifier(new ICellModifier() {

			public boolean canModify(Object element, String property) {
				return false;
			}

			public Object getValue(Object element, String property) {
				return element.toString();
			}

			public void modify(Object element, String property, Object value) {

			}
		});
		gridViewer.setColumnProperties(colProp);
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(gridViewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
				        || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
				        || (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR);
			}
		};
		GridViewerEditor.create(gridViewer, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
		        | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL
		        | ColumnViewerEditor.KEYBOARD_ACTIVATION);

		GridColumnGroup cgSpot = new GridColumnGroup(gridViewer.getGrid(), SWT.CENTER);
		cgSpot.setText("        1차 계량");

		for (int i = 0; i < colName.length; i++) {
			GridColumn column = null;
			if (i == 3 || i == 4) {
				column = new GridColumn(cgSpot, SWT.NONE);
			} else {
				column = new GridColumn(gridViewer.getGrid(), SWT.NONE);
			}
			column.setWidth(colWidth[i]);
			column.setAlignment(colAlign[i]);
			column.setText(colName[i]);
			column.setResizeable(true);
		}
		gridViewer.getGrid().setHeaderVisible(true);
		gridViewer.getGrid().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent evt) {
				Grid g = (Grid) evt.getSource();
				if (listData != null && listData.length > g.getSelectionIndex() && g.getSelectionIndex() != -1) {
					TsWgtInfDTO vo = listData[g.getSelectionIndex()];
					WeighSecondDialog dialog = new WeighSecondDialog(evt.display.getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					Object answer = dialog.open(vo);
					if(answer != null){
						HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
						hv.refreshData();
					}
				}
			}
		});
		Grid grid = gridViewer.getGrid();
		grid.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		grid.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				int width = 0;
				for(int i=0;i<gridViewer.getGrid().getColumnCount();i++){
					width = (int)(colWidth[i]*gridViewer.getGrid().getClientArea().width/100);
					if(i==gridViewer.getGrid().getColumnCount()-1){
						width+=5;
					}
					gridViewer.getGrid().getColumn(i).setWidth(width);
				}
				int add = gridViewer.getGrid().getBounds().width/100 - 9;
				gridViewer.getGrid().setFont(SWTResourceManager.getFont("Arial", 15 + (add>-5?add:-5), SWT.NORMAL));
				gridViewer.getGrid().setItemHeight(28 + (add>0?add:0));
			}
		});
		grid.setRowsResizeable(true);

		grid.setLineColor(ColorRepository.getColor(ColorRepository.GRID_LINE));
		// grid.setBackgroundMode(SWT.INHERIT_FORCE);
		GridData gridViewerLData = new GridData();
		gridViewerLData.grabExcessHorizontalSpace = true;
		gridViewerLData.horizontalAlignment = GridData.FILL;
		gridViewerLData.grabExcessVerticalSpace = true;
		gridViewerLData.verticalAlignment = GridData.FILL;
		gridViewerLData.verticalIndent = 5;
		gridViewer.getGrid().setLayoutData(gridViewerLData);
		gridViewer.getGrid().setCellHeaderSelectionBackground(ColorRepository.getColor(ColorRepository.GRID_SELECT_BG));
	}// initGird

	public TsWgtInfDTO[] getListData() {
    	return listData;
    }

	/**
	 * list data setting
	 * @param listData
	 */
	public void setListData(TsWgtInfDTO[] listData) {
    	this.listData = listData;
    	gridViewer.setInput(listData);
    	lblCount.setText(StringUtil.getString(listData.length));
    }
	
	public class ListGridLabelProvider extends LabelProvider implements ITableLabelProvider, ITableFontProvider,
	        ITableColorProvider {
		FontRegistry registry = new FontRegistry();

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			TsWgtInfDTO data = (TsWgtInfDTO) element;
			Object o = StringUtil.invoke(data, StringUtil.makeGetter(colProp[columnIndex]),null);
			if (columnIndex == 3){
				return StringUtil.extractTime(o);
			}
			return StringUtil.extractString(o,0);
		}

		public Font getFont(Object element, int columnIndex) {

			return null;
		}

		public Color getBackground(Object element, int columnIndex) {
			TsWgtInfDTO data = (TsWgtInfDTO) element;
			if (data.getNo() % 2 == 0) {
				return ColorRepository.getColor(ColorRepository.GRID_ODD_BG);
			}else{
				return ColorRepository.getColor(ColorRepository.GRID_EVEN_BG);
			}
		}

		public Color getForeground(Object element, int columnIndex) {
			TsWgtInfDTO data = (TsWgtInfDTO) element;
			if (data.getNo() % 2 == 0) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
			}
			return null;
		}
	}// class ListGridLabelProvider

	private class ListGridContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (TsWgtInfDTO[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}
	}// class ListGridContentProvider
}// class
