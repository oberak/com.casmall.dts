package com.casmall.dts.ui.weigh.compsite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.swtdesigner.SWTResourceManager;

public class CstComposite extends Composite{
	public static final String KEY_ID = "ID";
	public static final String KEY_NAME = "NAME";

	private TableComboViewer tableComboViewer;
	
	private CLabel lblTitle;
	private List<TsCstMstDTO> items;
	private List<TsCstMstDTO> filteredItems;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public CstComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginRight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		lblTitle = new CLabel(this, SWT.NONE);
		lblTitle.setText("title");
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));

		tableComboViewer = new TableComboViewer(this, SWT.BORDER);
		TableCombo tableCombo = tableComboViewer.getTableCombo();
		tableCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		tableCombo.setShowTableHeader(true);
		tableCombo.defineColumns(new String[]{"관리코드","거래처명","설명"});
		tableCombo.setDisplayColumnIndex(1);
		tableCombo.addTextKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.character;
				Text text = (Text)e.widget;
				TableCombo tc = (TableCombo)text.getParent();
				Table tbl = tc.getTable();
				
				if(0x0D == c){
					// Enter 일 경우 Tab 처리
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
				// ESC나 값이 없을 경우 close
				if(0x1B == c || "".equals(text.getText())){
					tc.dropDown(false,false);
					return;
				}
				// 한글일 경우 마지막 미완성 한글 제거
				String txt = StringUtil.getCompleteHangle(text.getText());
				if(txt.trim().length()==0){
					tbl.removeAll();
					loadThreeColumnDataset(items);
					tc.dropDown(false,false);
				}else{
					tbl.removeAll();
					List<TsCstMstDTO> f = filter(txt.trim());
					loadThreeColumnDataset(f);
				}
				
				if(!tc.isDisposed())
					tc.dropDown (!tc.isDisposed(), false);
			}
		});
	}// initGUI

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		CstComposite inst = new CstComposite(shell, SWT.NULL);
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
	}// showGUI

	public static void main(String[] args) {
		showGUI();
	}
	
	/**
	 * load a list of rows
	 * @return
	 */
	private List<TableItem> loadThreeColumnDataset(List<TsCstMstDTO> modelList) {
		Table table = tableComboViewer.getTableCombo().getTable();
		List<TableItem> rowList = new ArrayList<TableItem>();
		
		int total = (modelList == null ? 0 : modelList.size());
		
		for (int index=0; index < total; index++) {
			TableItem ti = new TableItem(table, SWT.NONE);
			TsCstMstDTO model = (TsCstMstDTO)modelList.get(index);
			ti.setText(new String[] { StringUtil.nullToBlank(model.getCst_mgt_cd()), model.getCst_nm(), 
				model.getNt()});
			rowList.add(ti);			
		}
		
		filteredItems = modelList;
		return rowList;
	}
	
	public void setFont(Font font){
		lblTitle.setFont(font);
		tableComboViewer.getTableCombo().setFont(font);
	}
	
	/**
	 * Filter
	 * @param str
	 * @return
	 */
	public List<TsCstMstDTO> filter(String str){
		List<TsCstMstDTO> filtered = new ArrayList<TsCstMstDTO>();
		for(int i=0;i<items.size();i++){
			if(StringUtil.nullToBlank(((TsCstMstDTO)items.get(i)).getCst_mgt_cd()).indexOf(str)>-1 || ((TsCstMstDTO)items.get(i)).getCst_nm().indexOf(str)>-1){
				filtered.add(items.get(i));
			}
		}
		return filtered;
	}
	
	/**
	 * Selection listener 추가
	 * @param listener
	 */
	public void addSelectionListener(SelectionListener listener){
		tableComboViewer.getTableCombo().addSelectionListener(listener);
	}
	
	/**
	 * Set title
	 * @param title
	 */
	public void setTitle(String title){
		lblTitle.setText(title);
	}
	
	/**
	 * 선택된 Object return<br>
	 * 선택된 것 없으면 신규 생성용 Object return
	 * @return
	 */
	public TsCstMstDTO getSelectionItem(){
		TsCstMstDTO model;
		int idx = tableComboViewer.getTableCombo().getSelectionIndex();
		if(idx >= 0){
			model = filteredItems.get(idx);
		}else{
			model = new TsCstMstDTO();
			model.setCst_nm(tableComboViewer.getTableCombo().getText());
		}
		return model;
	}
	
	/**
	 * set column data list
	 * @param items
	 */
	public void setItems(List<TsCstMstDTO> items){
		this.items = items;
		this.filteredItems = items;
		loadThreeColumnDataset(items);
	}
	
	public List<TsCstMstDTO> getItems(){
		return items;
	}
	
	/**
	 * 컬럼 선택 후 표시할 column index
	 * @param idx
	 */
	public void setDisplayColumnIndex(int idx){
		tableComboViewer.getTableCombo().setDisplayColumnIndex(idx);
	}
	
	/**
	 * 컬럼 헤더 setting
	 * @param c
	 */
	public void setColumnHeader(String[] c){
		tableComboViewer.getTableCombo().defineColumns(c);
	}
	
	public void setForeground (Color color) {
		super.setForeground(color);
		lblTitle.setForeground(color);
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return tableComboViewer.getTableCombo().forceFocus();
    }
    
    public String getValue(String key){
    	TsCstMstDTO dto = getSelectionItem();
    	if(KEY_ID.equals(key)){
    		return dto.getCst_cd();
    	}else if(KEY_NAME.equals(key)){
    		return dto.getCst_nm();
    	}else{
    		return "WRONG KEY";
    	}
    }
    
    public void setValue(String... val){
    	if(val.length>0)
    		tableComboViewer.getTableCombo().setText(val[0]);
    }
    
    public boolean setSelection(String data){
    	if(data == null){
    		return false;
    	}
		for(int i=0;i<filteredItems.size();i++){
			if(data.equals(filteredItems.get(i).getCst_cd())){
				tableComboViewer.getTableCombo().select(i);
				return true;
			}
		}
    	return false;
    }
    
    public void setReadonly(){
    	setEnabled(false);
    	tableComboViewer.getTableCombo().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    }
    
	public void setImage(Image image) {
		lblTitle.setImage(image);
    }
	
	public void addFocusListener (FocusListener listener) {
		tableComboViewer.getTableCombo().addFocusListener(listener);
	}
}
