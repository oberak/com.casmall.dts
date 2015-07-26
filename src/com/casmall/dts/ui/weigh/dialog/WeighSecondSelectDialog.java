package com.casmall.dts.ui.weigh.dialog;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
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
import org.eclipse.nebula.widgets.grid.Grid;
import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.GridColumnGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsMstManager;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.common.ObjectUtil;
import com.casmall.dts.common.PrintUtil;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.preferences.DTSPreConstants;
import com.casmall.dts.ui.weigh.compsite.CarComposite;
import com.casmall.usr.domain.CmUsrInfDTO;
import com.casmall.usr.mgr.SessionManager;
import com.swtdesigner.SWTResourceManager;

/**
 * 2차 계량 - 1차계량 목록에서 선택
 * 
 * @author OBERAK
 */
public class WeighSecondSelectDialog extends Dialog {
	protected static Log logger = LogFactory.getLog(WeighSecondSelectDialog.class);
			
	protected final Font titleFont = SWTResourceManager.getFont("굴림체", 23, SWT.BOLD);
	protected final Font defaultFont = SWTResourceManager.getFont("굴림체", 17, SWT.BOLD);
	protected final Font helpFont = SWTResourceManager.getFont("굴림체", 12, SWT.NONE);
	protected final Font btnFont = SWTResourceManager.getFont("굴림체", 15, SWT.BOLD);
	
	protected Object result;
	protected Shell shell;

	private CarComposite car;
	private Composite compCenter;
	
	private ScopedPreferenceStore preferences;
	
	private String[] colProp = { "no", "car_num", "fst_wgt_dt", "fst_wgt_dt", "fst_wgh" };
	private String[] colName = { "No", "차량번호", "일자", "시간", "중량   " };
	/** 컬럼 너비 : 퍼센트 */
	private int[] colWidth = { 10, 30, 27, 15, 18 };
	private int[] colAlign = { SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.CENTER, SWT.RIGHT };

	private GridTableViewer gridViewer;
	private TsWgtInfDTO[] listData;
	private Shell parent;
	private CmUsrInfDTO user;

	private CLabel lblHelp;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WeighSecondSelectDialog(Shell parent, int style) {
		super(parent, style);
		this.parent = parent;
		setText("2차 계량 - 대상 차량 선택");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		user = SessionManager.getInstance().getUsr();
		createContents();
		shell.open();
		shell.layout();
		
		car.setFocus();
		init();
		
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(624, 617);
		shell.setText(getText());
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.horizontalSpacing = 0;
		gl_shell.marginHeight = 0;
		gl_shell.verticalSpacing = 0;
		gl_shell.marginWidth = 0;
		shell.setLayout(gl_shell);
		shell.setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
//		shell.setBackgroundMode(SWT.TRANSPARENT);
		
		CLabel lblTitle = new CLabel(shell, SWT.CENTER);
		lblTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblTitle = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_lblTitle.heightHint = 44;
		lblTitle.setLayoutData(gd_lblTitle);
		lblTitle.setFont(titleFont);
		lblTitle.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_TITLE_BG));
		lblTitle.setText("2차 계량 - 대상 차량 선택");
		
		Composite compContents = new Composite(shell, SWT.NONE);
		compContents.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		compContents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout gl_compContents = new GridLayout(1, false);
		gl_compContents.marginTop = 10;
		gl_compContents.marginRight = 10;
		gl_compContents.marginLeft = 10;
		gl_compContents.marginBottom = 10;
		gl_compContents.verticalSpacing = 0;
		gl_compContents.marginWidth = 0;
		gl_compContents.marginHeight = 0;
		compContents.setLayout(gl_compContents);
		
		compCenter = new Composite(compContents, SWT.BORDER);
		compCenter.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compCenter.setLayout(new GridLayout(2, false));
		
		car = new CarComposite(compCenter, SWT.NONE);
		car.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				filter();
			}
		});
		car.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		car.setFont(defaultFont);
		car.setTitle("차량 번호: ");
		car.addFocusListener(new HelpAdapter("차량번호를 입력하거나 목록에서 선택 후 Enter."));
		
		Button btnSearch = new Button(compCenter, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				select();
			}
		});
		btnSearch.setFont(btnFont);
		btnSearch.setText(" 조 회 ");
		
		initGird(compContents);
		
		Composite btn = new Composite(shell,SWT.NONE);
		btn.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		btn.setBackgroundMode(SWT.INHERIT_FORCE);
		if(user.hasAuth(DTSConstants.AUTH_DEL_FST)){
			btn.setLayout(new GridLayout(4, false));
		}else{
			btn.setLayout(new GridLayout(3, false));
		}
		
		Button btnSave = new Button(btn, SWT.NONE);
		GridData gd_btnSave = new GridData(SWT.RIGHT, SWT.FILL, true, false);
		gd_btnSave.heightHint = 36;
		gd_btnSave.widthHint = 130;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setFont(btnFont);
		btnSave.setText("2차 계량");
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickOK();
			}
		});
		
		Button btnPrint = new Button(btn, SWT.NONE);
		GridData gd_btnPrint = new GridData(SWT.RIGHT, SWT.FILL, true, false);
		gd_btnPrint.heightHint = 36;
		gd_btnPrint.widthHint = 130;
		btnPrint.setLayoutData(gd_btnPrint);
		btnPrint.setFont(btnFont);
		btnPrint.setText("전표 출력");
		btnPrint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Grid g = gridViewer.getGrid();
				if(g.getSelectionIndex() == -1){
					MessageDialog.openInformation(shell, "데이터 선택 필요", "선택된 데이터가 없습니다. \n\n목록에서 선택 후 작업하세요.");
					return;
				}else{
					if (listData != null && listData.length > g.getSelectionIndex()) {
						TsWgtInfDTO dto = listData[g.getSelectionIndex()];

						PrintUtil.print(dto, preferences.getInt(DTSPreConstants.GN_PRINT_COUNT));
					}
				}
			}
		});
		
		if(user.hasAuth(DTSConstants.AUTH_DEL_FST)){
			Button btnDelete = new Button(btn, SWT.NONE);
			GridData gd_btnDelete = new GridData(SWT.CENTER, SWT.FILL, true, false);
			gd_btnDelete.heightHint = 36;
			gd_btnDelete.widthHint = 130;
			btnDelete.setLayoutData(gd_btnDelete);
			btnDelete.setFont(btnFont);
			btnDelete.setText("삭 제");
			btnDelete.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					onClickDelete();
				}
			});
		}
		Button btnCancel = new Button(btn, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.FILL, true, false);
		gd_btnCancel.heightHint = 36;
		gd_btnCancel.widthHint = 130;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setFont(btnFont);
		btnCancel.setText("취  소");

		lblHelp = new CLabel(shell, SWT.LEFT);
		lblHelp.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblHelp = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_lblHelp.heightHint = 25;
		lblHelp.setLayoutData(gd_lblHelp);
		lblHelp.setText("도움말:");
		lblHelp.setFont(helpFont);
		lblHelp.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_HELP_BG));
		
		shell.addListener(SWT.Traverse, new Listener() {
            public void handleEvent(Event event) {
                if(event.detail == SWT.TRAVERSE_ESCAPE) {
                    event.doit = false;
                }
            }
        });
	}// createContents

	protected void onClickDelete() {
		Grid g = gridViewer.getGrid();
		if(g.getSelectionIndex() == -1){
			MessageDialog.openInformation(shell, "데이터 선택 필요", "선택된 데이터가 없습니다. \n\n목록에서 선택 후 작업하세요.");
			return;
		}else{
			if (listData != null && listData.length > g.getSelectionIndex()) {
				boolean rtn = MessageDialog.openConfirm(shell, "삭제 확인", "1차 계량 정보를 삭제하시겠습니까?\n\n차량번호-"+listData[g.getSelectionIndex()].getCar_num());
				if(!rtn){
					return;
				}
				try {
					TsWgtInfManager wim = new TsWgtInfManager();
					TsWgtInfDTO dto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());
					dto.setDel_yn(DTSConstants.FLAG_Y);
					dto.setWgt_cd(listData[g.getSelectionIndex()].getWgt_cd());
	                wim.updateTsWgtInf(dto);
	                // 목록 재조회
	                select();
	                // HomeView refresh
					HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
					hv.refreshData();
                } catch (IOException e) {
                	MessageDialog.openError(shell, "삭제 오류", "데이터 삭제 중 오류가 발생하였습니다. \n\n"+e.getMessage());
                	return;
                }
			}
		}
    }

	protected void filter() {
		TsCarMstDTO item = car.getSelectionItem();
		ArrayList<TsWgtInfDTO> items = filter(item);
		
		gridViewer.setInput(items.toArray(new TsWgtInfDTO[0]));
		gridViewer.getGrid().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    }
	public ArrayList<TsWgtInfDTO> filter(TsCarMstDTO item){
		ArrayList<TsWgtInfDTO> filtered = new ArrayList<TsWgtInfDTO>();
		if(item.getCar_cd() != null && item.getCar_cd().length()>0){
			for(int i=0;i<listData.length;i++){
				if(listData[i].getCar_cd().equals(item.getCar_cd())){
					filtered.add(listData[i]);
				}
			}
		}else{
			for(int i=0;i<listData.length;i++){
				if(StringUtil.nullToBlank(listData[i].getCar_num()).indexOf(item.getCar_num())>-1){
					filtered.add(listData[i]);
				}
			}
		}
		return filtered;
	}
	/**
	 * init
	 */
	private void init() {
		TsMstManager mgr = new TsMstManager();
		car.setItems(mgr.selectTsCarMst(null));
		select();
    }// initData
	
	private void select(){
		TsCarMstDTO item = car.getSelectionItem();
		if(item.getCar_num().length()==0){
			TsWgtInfManager wim = new TsWgtInfManager();
			TsWgtInfDTO param = new TsWgtInfDTO();
			param.setWgt_stat_cd(DTSConstants.WGT_STAT_FST);
			ArrayList<TsWgtInfDTO> fstList = wim.selectTsWgtInf(param);
			for(int i=0;i<fstList.size();i++)
				fstList.get(i).setNo(i+1);
			listData = fstList.toArray(new TsWgtInfDTO[0]);
			gridViewer.setInput(listData);
		}else{
			filter();
		}
	}
	/**
	 * OK Button click event
	 */
	private void onClickOK(){
		Grid g = gridViewer.getGrid();
		if(g.getSelectionIndex() == -1){
			MessageDialog.openInformation(shell, "데이터 선택 필요", "선택된 데이터가 없습니다. \n\n목록에서 선택 후 작업하세요.");
			return;
		}else{
			if (listData != null && listData.length > g.getSelectionIndex()) {
				TsWgtInfDTO vo = listData[g.getSelectionIndex()];
				WeighSecondDialog dialog = new WeighSecondDialog(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				shell.close();
	
				Object answer = dialog.open(vo);
				if(answer != null){
					HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
					hv.refreshData();
				}
			}
		}
	}
	
	private void initGird(Composite compContents) {
		gridViewer = new GridTableViewer(compContents, SWT.V_SCROLL | SWT.BORDER);
		gridViewer.setLabelProvider(new ListGridLabelProvider());
		gridViewer.setContentProvider(new ListGridContentProvider());
		gridViewer.getGrid().setSelectionEnabled(true);
		gridViewer.getGrid().setFont(SWTResourceManager.getFont("Arial", 16, SWT.NORMAL));
		gridViewer.getGrid().setItemHeight(28);
		gridViewer.getGrid().addFocusListener(new HelpAdapter("계류차량 선택 후 더블클릭 또는 [2차 계량] 버튼 클릭."));
		
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

		gridViewer.getGrid().setLinesVisible(true);
		gridViewer.getGrid().setHeaderVisible(true);
		gridViewer.getGrid().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent evt) {
				Grid g = (Grid) evt.getSource();
				if (listData != null && listData.length > g.getSelectionIndex() && g.getSelectionIndex() != -1) {
					TsWgtInfDTO vo = listData[g.getSelectionIndex()];
					WeighSecondDialog dialog = new WeighSecondDialog(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					shell.close();

					Object answer = dialog.open(vo);
					if(answer != null){
						HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
						hv.refreshData();
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
				int add = gridViewer.getGrid().getBounds().width/100 - 0;
				gridViewer.getGrid().setFont(SWTResourceManager.getFont("Arial", 12 + (add>0?add:0), SWT.NORMAL));
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
	}// initGird
	
	public class ListGridLabelProvider extends LabelProvider implements ITableLabelProvider, ITableFontProvider,
	        ITableColorProvider {
		FontRegistry registry = new FontRegistry();

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			TsWgtInfDTO data = (TsWgtInfDTO) element;
			Object o = StringUtil.invoke(data, StringUtil.makeGetter(colProp[columnIndex]), null);
			if (columnIndex == 3) {
				return StringUtil.extractTime(o);
			}
			return StringUtil.extractString(o, 0);
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
	
	private void setMessage(String msg){
		lblHelp.setText("도움말:"+msg);
	}
	
	class HelpAdapter extends FocusAdapter{

		String msg = "";
		HelpAdapter(String msg){
			this.msg = msg;
		}
		@Override
        public void focusGained(FocusEvent e) {
			setMessage(msg);
        }

		@Override
        public void focusLost(FocusEvent e) {
			setMessage("");
        }
		
	}// class HelpAdapter
}
