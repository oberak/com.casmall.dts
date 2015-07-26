package com.casmall.dts.ui.weigh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.nebula.widgets.formattedtext.DateFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.nebula.widgets.radiogroup.RadioItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsMstManager;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ExportVO;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.common.ObjectUtil;
import com.casmall.dts.common.RvExcelWriter;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.view.WeighListView;
import com.casmall.dts.ui.weigh.compsite.CarComposite;
import com.casmall.dts.ui.weigh.compsite.CstComposite;
import com.casmall.dts.ui.weigh.compsite.PrdtComposite;
import com.casmall.dts.ui.weigh.dialog.WeighSecondDialog;
import com.cloudgarden.resource.SWTResourceManager;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class WeighListComposite extends Composite{

	protected static Log log = LogFactory.getLog(WeighListComposite.class);
	private Button btnSelect;
	private Composite ctTop;
	private Composite ctSelect;

	private GridTableViewer gridViewer;
	private String[] colProp = { "no", "car_num", "fst_wgt_dt", "fst_wgt_dt", "fst_wgh", "scnd_wgt_dt", "scnd_wgh", "dscnt", "rl_wgh","cst_nm","prdt_nm","io_flg_nm" };
	private String[] colName = { "No", "차량번호", "일자", "시간", "중량   ","시간", "중량   ","감량","실중량 ","거래처","제품","구분" };
	private String[] colFormat = { "", "", "yyyy-MM-dd", "HH:mm", "","HH:mm", "","","","","","" };

	// 구분, 거래처, 차량
	/** 컬럼 너비 : 퍼센트 */
	private int[] colWidth = { 5, 14, 11, 8, 7, 8, 7, 7, 7, 12, 8, 6 };
	private int[] colAlign = { SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.RIGHT, SWT.CENTER, SWT.RIGHT, SWT.RIGHT, SWT.RIGHT, SWT.LEFT, SWT.LEFT, SWT.CENTER};

	private ArrayList<TsWgtInfDTO> listData;
	
	private Font titleFont = com.swtdesigner.SWTResourceManager.getFont("Arial", 18, SWT.NORMAL);
	private Font defaultFont = com.swtdesigner.SWTResourceManager.getFont("Arial", 13, SWT.NORMAL);
	TsWgtInfManager mgr = new TsWgtInfManager();
	private Text txtFrom;
	private Text txtTo;
	private CarComposite car;
	private CstComposite cst;
	private PrdtComposite prdt;
	private RadioItem rdoToday;
	private RadioItem rdoThisWeek;
	private RadioItem rdoThisMonth;
	private RadioGroup rgTerm;
	private RadioItem[] rdoFlag = new RadioItem[3];
	private RadioGroup rgWgtFlag;
	
	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	/**
	 * Auto-generated main method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void main(String[] args) {
		showGUI();
	}

	/**
	 * Auto-generated method to display this org.eclipse.swt.widgets.Composite
	 * inside a new Shell.
	 */
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		WeighListComposite inst = new WeighListComposite(shell, SWT.NULL);

		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
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
	}

	public WeighListComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));

		initGUI();
		init();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.setSize(1010, 418);
			{
				final CLabel lblTitle = new CLabel(this, SWT.NONE);
				lblTitle.setText("계량정보 조회");
				lblTitle.setImage(ImageRepository.getImage(ImageRepository.ICO_SELECT));
				lblTitle.setFont(titleFont);
				lblTitle.setForeground(ColorRepository.getColor(ColorRepository.TEXT_TITLE));
				lblTitle.setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
				GridData lblTitleLData = new GridData(SWT.FILL, SWT.BOTTOM, false, false);
				lblTitleLData.verticalIndent = 3;
				lblTitleLData.horizontalIndent = 10;
				lblTitle.setLayoutData(lblTitleLData);
			}
			{
				ctSelect = new Composite(this, SWT.NONE);
				ctSelect.setBackgroundMode(SWT.INHERIT_FORCE);
				GridLayout ctSelectLayout = new GridLayout();
				ctSelectLayout.marginLeft = -9;
				ctSelectLayout.marginRight = -9;
				ctSelectLayout.marginWidth = 0;
				ctSelectLayout.verticalSpacing = 0;
				ctSelectLayout.numColumns = 3;
				ctSelectLayout.marginHeight = 0;
				ctSelectLayout.horizontalSpacing = 0;
				GridData ctSelectLData = new GridData(SWT.FILL, SWT.FILL, true, false);
				ctSelectLData.heightHint = 75;
				ctSelect.setLayoutData(ctSelectLData);
				ctSelect.setLayout(ctSelectLayout);

				final Composite ctSelectLeft = new Composite(ctSelect, SWT.NONE);
				ctSelectLeft.setBackgroundImage(ImageRepository.getImage(ImageRepository.BG_SELECT_LEFT));
				ctSelectLeft.setLayout(new GridLayout());
				GridData ctSelectLeftLData = new GridData(SWT.LEFT, SWT.FILL, false, true);
				ctSelectLeftLData.widthHint = 19;
				ctSelectLeft.setLayoutData(ctSelectLeftLData);
				
				ctTop = new Composite(ctSelect, SWT.NONE);
				ctTop.setBackgroundImage(ImageRepository.getImage(ImageRepository.BG_SELECT));
				GridLayout ctTopLayout = new GridLayout();
				ctTopLayout.numColumns = 2;
				GridData ctTopLData = new GridData();
				ctTopLData.grabExcessHorizontalSpace = true;
				ctTopLData.horizontalAlignment = GridData.FILL;
				ctTopLData.grabExcessVerticalSpace = true;
				ctTopLData.verticalAlignment = GridData.FILL;
				ctTop.setLayoutData(ctTopLData);
				ctTop.setLayout(ctTopLayout);
				{
					Composite cSearch = new Composite(ctTop, SWT.NONE);
					cSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
					GridLayout gl_cSearch = new GridLayout(3, false);
					gl_cSearch.marginHeight = 0;
					cSearch.setLayout(gl_cSearch);
					
					Composite cSearchTerm = new Composite(cSearch, SWT.NONE);
					cSearchTerm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
					GridLayout gl_cSearchTerm = new GridLayout(7, false);
					gl_cSearchTerm.verticalSpacing = 0;
					gl_cSearchTerm.marginHeight = 0;
					cSearchTerm.setLayout(gl_cSearchTerm);
					{
						CLabel lblTag = new CLabel(cSearchTerm, SWT.NONE);
						lblTag.setFont(defaultFont);
						lblTag.setText("기간: ");
						lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					
						FormattedText fmtFrom = new FormattedText(cSearchTerm, SWT.BORDER);
						fmtFrom.setFormatter(new DateFormatter("yyyy-MM-dd"));
						txtFrom = fmtFrom.getControl();
						txtFrom.setFont(defaultFont);
						GridData gd_txtFrom = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
						gd_txtFrom.widthHint = 110;
						txtFrom.setLayoutData(gd_txtFrom);
						
						Label label = new Label(cSearchTerm, SWT.NONE);
						label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						label.setText("~");
						label.setFont(defaultFont);
						
						FormattedText fmtTo = new FormattedText(cSearchTerm, SWT.BORDER);
						fmtTo.setFormatter(new DateFormatter("yyyy-MM-dd"));
						txtTo = fmtTo.getControl();
						txtTo.setFont(defaultFont);
						GridData gd_txtTo = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
						gd_txtTo.widthHint = 110;
						txtTo.setLayoutData(gd_txtTo);
					}
					
					rgTerm = new RadioGroup(cSearchTerm, SWT.NONE);
					rgTerm.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							try {
								setTerm((Integer)rgTerm.getSelection().getData());
                            } catch (Exception e2) {
                            }
						}
					});
					rgTerm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
					
					
					rdoToday = new RadioItem(rgTerm, SWT.NONE);
					rdoToday.setFont(defaultFont);
					rdoToday.setData(0);
					rdoToday.setText("오늘");
					
					rdoThisWeek = new RadioItem(rgTerm, SWT.NONE);
					rdoThisWeek.setFont(defaultFont);
					rdoThisWeek.setData(1);
					rdoThisWeek.setText("일주일");
					
					rdoThisMonth = new RadioItem(rgTerm, SWT.NONE);
					rdoThisMonth.setFont(defaultFont);
					rdoThisMonth.setData(2);
					rdoThisMonth.setText("한달");
					
					{
						CLabel lblTag = new CLabel(cSearchTerm, SWT.NONE);
						lblTag.setFont(defaultFont);
						lblTag.setText("계량구분: ");
						lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
						
						rgWgtFlag = new RadioGroup(cSearchTerm, SWT.NONE);
						rgWgtFlag.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
						rdoFlag[0] = new RadioItem(rgWgtFlag, SWT.NONE);
						rdoFlag[0].setFont(defaultFont);
						rdoFlag[0].setData(null);
						rdoFlag[0].setText("전체");
						
						rdoFlag[1] = new RadioItem(rgWgtFlag, SWT.NONE);
						rdoFlag[1].setFont(defaultFont);
						rdoFlag[1].setData(DTSConstants.WGT_FLAG_GEN);
						rdoFlag[1].setText("일반계량");
						
						rdoFlag[2] = new RadioItem(rgWgtFlag, SWT.NONE);
						rdoFlag[2].setFont(defaultFont);
						rdoFlag[2].setData(DTSConstants.WGT_FLAG_ONE);
						rdoFlag[2].setText("1회계량");
					}
					car = new CarComposite(cSearch, SWT.NONE);
					car.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
					car.setFont(defaultFont);
					car.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					car.setTitle("차량번호:");
					
					cst = new CstComposite(cSearch, SWT.NONE);
					cst.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
					cst.setFont(defaultFont);
					cst.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					cst.setTitle("거래처:");
					
					prdt = new PrdtComposite(cSearch, SWT.NONE);
					prdt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
					prdt.setFont(defaultFont);
					prdt.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					prdt.setTitle("제품:");
				}
				{
					Composite cBtn = new Composite(ctTop, SWT.NONE);
					cBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 1, 1));
					cBtn.setLayout(new FillLayout(SWT.HORIZONTAL));
					{
						btnSelect = new Button(cBtn, SWT.PUSH | SWT.CENTER);
						btnSelect.setImage(ImageRepository.getImage(ImageRepository.BTN_SELECT));
						btnSelect.setFont(defaultFont);
						btnSelect.setText("조회");
						Button btnExport = new Button(cBtn, SWT.PUSH | SWT.CENTER);
						btnExport.setImage(ImageRepository.getImage(ImageRepository.BTN_EXPORT));
						btnExport.setFont(defaultFont);
						btnExport.setText("엑셀");
						Button btnClose = new Button(cBtn, SWT.PUSH | SWT.CENTER);
						btnClose.setImage(ImageRepository.getImage(ImageRepository.BTN_CLOSE));
						btnClose.setFont(defaultFont);
						btnClose.setText("닫기");
						btnClose.addMouseListener(new MouseAdapter() {
							public void mouseDown(MouseEvent evt) {
								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
								IViewReference ref = page.findViewReference(WeighListView.ID);
								page.hideView(ref);
							}
						});	
						btnExport.addMouseListener(new MouseAdapter() {
							public void mouseDown(MouseEvent evt) {
								btnExportMouseDown(evt);
							}
						});	
					}
					btnSelect.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent evt) {
							btnSelectMouseDown(evt);
						}
					});	
				}
				final Composite ctSelectRight = new Composite(ctSelect, SWT.NONE);
				ctSelectRight.setBackgroundImage(ImageRepository.getImage(ImageRepository.BG_SELECT_RIGHT));
				ctSelectRight.setLayout(new GridLayout());
				GridData ctSelectRightLData = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
				ctSelectRightLData.widthHint = 19;
				ctSelectRight.setLayoutData(ctSelectRightLData);

			}
			{
				gridViewer = new GridTableViewer(this, SWT.BORDER|SWT.V_SCROLL);
				gridViewer.setLabelProvider(new ListGridLabelProvider());
				gridViewer.setContentProvider(new ListGridContentProvider());
				gridViewer.getGrid().setSelectionEnabled(true);
				gridViewer.getGrid().setFont(defaultFont);
				gridViewer.getGrid().setItemHeight(28);
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
				
				GridColumnGroup cgFst = new GridColumnGroup(gridViewer.getGrid(), SWT.CENTER);
				cgFst.setText("        1차 계량");

				GridColumnGroup cgScnd = new GridColumnGroup(gridViewer.getGrid(), SWT.CENTER);
				cgScnd.setText("        2차 계량");
				
				for (int i = 0; i < colName.length; i++) {
					GridColumn column = null;
					if (i == 3 || i == 4) {
						column = new GridColumn(cgFst, SWT.NONE);
					}else if (i == 5 || i == 6) {
						column = new GridColumn(cgScnd, SWT.NONE);
					} else {
						column = new GridColumn(gridViewer.getGrid(), SWT.NONE);
					}
					column.setWidth(colWidth[i]);
					column.setAlignment(colAlign[i]);
					column.setText(colName[i]);
					column.setResizeable(true);
				}
				
				gridViewer.getGrid().setLinesVisible(true);
				gridViewer.getGrid().setHeaderVisible(true);
//				gridViewer.getGrid().setRowHeaderVisible(true);
				gridViewer.getGrid().addMouseListener(new MouseAdapter() {
					public void mouseDoubleClick(MouseEvent evt) {
						Grid g = (Grid) evt.getSource();
						if (listData != null && listData.size() > g.getSelectionIndex() && g.getSelectionIndex() != -1) {
							TsWgtInfDTO vo = listData.get(g.getSelectionIndex());
							WeighSecondDialog dialog = new WeighSecondDialog(evt.display.getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
							Object answer = dialog.open(vo);
							if(answer != null){
								HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
								hv.refreshData();
								selectData();
							}
						}
					}
				});
				
				Grid grid = gridViewer.getGrid();
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
						int add = gridViewer.getGrid().getBounds().width/100 - 6;
						gridViewer.getGrid().setFont(SWTResourceManager.getFont("Arial", 10 + (add>0?add:0), SWT.NORMAL));
					}
				});
				grid.setLineColor(ColorRepository.getColor(ColorRepository.GRID_LINE));
				GridData gridViewerLData = new GridData();
				gridViewerLData.grabExcessHorizontalSpace = true;
				gridViewerLData.horizontalAlignment = GridData.FILL;
				gridViewerLData.grabExcessVerticalSpace = true;
				gridViewerLData.verticalAlignment = GridData.FILL;
				gridViewer.getGrid().setLayoutData(gridViewerLData);
			}

			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void setTerm(int i) {
		String today = StringUtil.getDate("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtTo.setText(today);
		Calendar cal = Calendar.getInstance();
	    switch (i) {
        case 0:
	        txtFrom.setText(today);
	        break;
        case 1:
    		cal.add(Calendar.DATE, -6);
    		txtFrom.setText(sdf.format(cal.getTime()));
	        break;
        case 2:
    		cal.add(Calendar.MONTH, -1);
    		cal.add(Calendar.DATE, 1);
    		txtFrom.setText(sdf.format(cal.getTime()));
	        break;
        default:
	        break;
        }
    }

	private void init(){
		TsMstManager mgr = new TsMstManager();
		car.setItems(mgr.selectTsCarMst(null));
		cst.setItems(mgr.selectTsCstMst(null));
		prdt.setItems(mgr.selectTsPrdtMst(null));
		
		txtFrom.setText(StringUtil.getDate("yyyy-MM")+"-01");
		txtTo.setText(StringUtil.getDate("yyyy-MM-dd"));
		gridViewer.getGrid().setLineColor(ColorRepository.getColor(ColorRepository.GRID_LINE));
		
		rgWgtFlag.select(0);
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
			if (columnIndex == 3 || columnIndex == 5){
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
			}
			return null;
		}

		public Color getForeground(Object element, int columnIndex) {
			TsWgtInfDTO data = (TsWgtInfDTO) element;
			if (data.getNo() % 2 == 0) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
			}
			return null;
		}
	}

	private class ListGridContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (Object[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	/**
	 * 조회버튼 클릭
	 * @param evt
	 */
	private void btnSelectMouseDown(MouseEvent evt) {
		selectData();
		if (listData == null || listData.size() == 0) {
			MessageDialog.openInformation(getShell(), "조회 결과", "조회된 자료가 없습니다.");
		}
	}
	
	/**
	 * 조회 처리
	 */
	private void selectData(){
		try{
			
			TsWgtInfDTO dto = new TsWgtInfDTO();
			dto.setExt_5(txtFrom.getText());
			dto.setExt_6(txtTo.getText()+" 23:59:59");
			
			if(car.getValue(CarComposite.KEY_ID) != null)
				dto.setCar_cd(car.getValue(CarComposite.KEY_ID));
			if(!"".equals(car.getValue(CarComposite.KEY_NAME).trim()))
				dto.setCar_num(car.getValue(CarComposite.KEY_NAME));
			
			if(cst.getValue(CstComposite.KEY_ID) != null)
				dto.setCst_cd(cst.getValue(CstComposite.KEY_ID));
			if(!"".equals(cst.getValue(CstComposite.KEY_NAME).trim()))
				dto.setCst_nm(cst.getValue(CstComposite.KEY_NAME));

			if(prdt.getValue(PrdtComposite.KEY_ID) != null)
				dto.setPrdt_cd(prdt.getValue(PrdtComposite.KEY_ID));
			if(!"".equals(prdt.getValue(PrdtComposite.KEY_NAME).trim()))
				dto.setPrdt_nm(prdt.getValue(PrdtComposite.KEY_NAME));
			if(rgWgtFlag.getSelection() != null && rgWgtFlag.getSelection().getData() != null)
				dto.setWgt_flg_cd((String)rgWgtFlag.getSelection().getData());
			listData = mgr.selectTsWgtInf(dto);
			Object[] objs = ObjectUtil.setSeq(listData.toArray(new Object[0]));
			gridViewer.setInput(objs);
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			MessageDialog.openError(getShell(), "조회 Error", e.getMessage());
		}
	}
	
	private void btnExportMouseDown(MouseEvent evt){
		if(listData == null){
			MessageDialog.openInformation(this.getShell(), "엑셀", "데이터가 조회되지 않았습니다.");
			return;
		}
		FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
	    dialog.setFilterNames(new String[] { "Excel Files", "All Files (*.*)" });
	    dialog.setFilterExtensions(new String[] { "*.xls", "*.*"});
	    dialog.setFileName("계량정보 "+txtFrom.getText()+"~"+txtTo.getText()+".xls");
	    String saveFile = dialog.open();
	    if (saveFile == null) {
			return;
		}
	    saveExport(saveFile, "계량 정보 ");
	}
	
	/**
	 * Excel 저장
	 * @param saveFile
	 */
	private void saveExport(String saveFile, String... str) {
		
		RvExcelWriter w = new RvExcelWriter(saveFile);

		ExportVO export = new ExportVO();
		export.setWidth(colWidth);
		export.setTitle(str[0]);
		export.setHeader(colName);
		export.setFormat(colFormat);
		if(str.length>1)
		export.setCond(str[1]);
		export.setData(listData.toArray(), colProp);

		try {
			w.write(export);
			MessageDialog.openInformation(this.getShell(), "엑셀", "Excel로 저장되었습니다.");
		} catch (IOException e) {
			MessageDialog.openError(this.getShell(), "Export Fail", "[Error] 저장되지 않았습니다.\n\n"+e.getMessage());
		}
	}
}
