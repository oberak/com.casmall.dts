package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.casmall.common.StringUtil;
import com.casmall.dts.ui.HomeView;
import com.swtdesigner.SWTResourceManager;

public class WeighInputComposite extends Composite{
	private Label lblTitle;
	private FormattedText txtWeigh;
	private Button btnRefresh;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public WeighInputComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginRight = 5;
		gridLayout.marginLeft = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setText("title");
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));
		
		txtWeigh = new FormattedText(this, SWT.BORDER | SWT.RIGHT);
		txtWeigh.setFormatter(new NumberFormatter("###,###,###"));
		txtWeigh.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		txtWeigh.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtWeigh.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.character;
				Text text = (Text)e.widget;
				if(0x0D == c){
					// Enter 老 版快 Tab 贸府
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
			}
		});

		btnRefresh = new Button(this, SWT.NONE);
		btnRefresh.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshData();
				// tab 贸府
				Button btn = (Button)e.widget;
				btn.traverse(SWT.TRAVERSE_TAB_NEXT);
			}
		});
		btnRefresh.setText("吝樊 佬扁");
		btnRefresh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.character;
				Text text = (Text)e.widget;
				if(0x0D == c){
					// Enter 老 版快 Tab 贸府
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
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
		WeighInputComposite inst = new WeighInputComposite(shell, SWT.NULL);
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
	
	public void addModifyListener (ModifyListener listener) {
		txtWeigh.getControl().addModifyListener(listener);
	}
	
	public void refreshData() {
		HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
		if(hv.getWeigh() != null && !"".equals(hv.getWeigh())){
			if(!"#N/A".equals(hv.getWeigh())){
				txtWeigh.setValue(StringUtil.parseDouble(hv.getWeigh()));
			}
		}else{
			txtWeigh.setValue(0);
		}
	}
	
	public void setForeground (Color color) {
		super.setForeground(color);
		lblTitle.setForeground(color);
		btnRefresh.setForeground(color);
	}
	
	public void setFont(Font... font){
		lblTitle.setFont(font[0]);
		txtWeigh.getControl().setFont(font[0]);
		if(font.length>1)
			btnRefresh.setFont(font[1]);
		else
			btnRefresh.setFont(font[2]);
	}

	/**
	 * Set title
	 * @param title
	 */
	public void setTitle(String title){
		lblTitle.setText(title);
	}
	
	/**
	 * set column data list
	 * @param items
	 */
	public void setValue(String... text){
		if(text[0]!=null && !"".equals(text[0])){
			txtWeigh.setValue(Double.parseDouble(text[0].replaceAll(",","")));
		}else{
			txtWeigh.setValue(0);
		}
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtWeigh.getControl().setFocus();
    }
    
	
	public String getValue(String key){
    	return String.valueOf(txtWeigh.getValue());
    }
	
	public void setReadonly(){
		txtWeigh.getControl().setEditable(false);
		txtWeigh.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
	}

	@Override
    public void setEnabled(boolean enabled) {
		if(enabled){
			txtWeigh.getControl().setEditable(true);
			txtWeigh.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}else{
			setReadonly();
		}
		btnRefresh.setEnabled(enabled);
	    super.setEnabled(enabled);
    }
	
	public void addFocusListener (FocusListener listener) {
		txtWeigh.getControl().addFocusListener(listener);
		btnRefresh.addFocusListener(listener);
	}
}
