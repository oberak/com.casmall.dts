package com.casmall.dts.ui.weigh.compsite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import com.casmall.common.StringUtil;
import com.casmall.dts.common.DTSConstants;
import com.swtdesigner.SWTResourceManager;

/**
 * 감량 Composite
 * @author OBERAK
 */
public class MinusWeighComposite extends Composite{
	protected static Log logger = LogFactory.getLog(MinusWeighComposite.class);
	
	public static final String KEY_CD = "CD";
	public static final String KEY_VAL = "VAL";
	
	private Label lblTitle;
	private FormattedText txtWeigh;
	private Button[] btnRdo = new Button[2];
	private Label lblKg;
	private Composite composite;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public MinusWeighComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(6, false));
		
		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		btnRdo[0] = new Button(composite, SWT.RADIO);
		btnRdo[0].setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		btnRdo[0].setText("\uC815\uB7C9(Kg)");
		btnRdo[0].setData(DTSConstants.CD_MINUS_KG);
		btnRdo[0].setSelection(true);
		btnRdo[0].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtWeigh.getControl().setFocus();
			}
		});
		
		btnRdo[1] = new Button(composite, SWT.RADIO);
		btnRdo[1].setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		btnRdo[1].setText("\uC815\uB960(%)");
		btnRdo[1].setData(DTSConstants.CD_MINUS_PCT);
		btnRdo[1].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnRdo[1].getSelection()){
					if(StringUtil.getLong(txtWeigh.getValue())>100)
						txtWeigh.setValue(0);
				}
				if(btnRdo[0].getSelection()){
					lblKg.setText("Kg");
					txtWeigh.setFormatter(new NumberFormatter("###,###,##0"));
				}else{
					lblKg.setText("%");
					txtWeigh.setFormatter(new NumberFormatter("###,###,##0.##"));
				}
				txtWeigh.getControl().setFocus();
			}
		});
		
		txtWeigh = new FormattedText(this, SWT.BORDER | SWT.RIGHT);
		txtWeigh.setFormatter(new NumberFormatter("###,###,##0.##"));
		txtWeigh.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtWeigh.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtWeigh.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(btnRdo[1].getSelection()){
					if(StringUtil.getLong(txtWeigh.getValue())>100){
						txtWeigh.setValue(100);
					}
				}
				char c = e.character;
				Text text = (Text)e.widget;
				if(0x0D == c){
					// Enter 일 경우 Tab 처리
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
			}
		});
		
		lblKg = new Label(this, SWT.NONE);
		lblKg.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 1, 1));
		lblKg.setText("Kg");
	}// initGUI

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		MinusWeighComposite inst = new MinusWeighComposite(shell, SWT.NULL);
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
	
	public void setForeground (Color color) {
		super.setForeground(color);
		lblTitle.setForeground(color);
	}
	
	public void addSelectionListener (SelectionListener listener) {
		btnRdo[0].addSelectionListener(listener);
		btnRdo[1].addSelectionListener(listener);
	}
	
	public void addKeyListener (KeyListener listener) {
		super.addKeyListener(listener);
		txtWeigh.getControl().addKeyListener(listener);
	}
	/**
	 * Font 설정<br>
	 * 0 : title, 1 : label, 2 : input
	 * @param font
	 */
	public void setFont(Font... font){
		lblTitle.setFont(font[0]);
		if(font.length > 1){
			lblKg.setFont(font[1]);
			for(Button btn:btnRdo)
				btn.setFont(font[1]);
			if(font.length > 2){
				txtWeigh.getControl().setFont(font[2]);
			}else{
				txtWeigh.getControl().setFont(font[1]);
			}
		}else{
			lblKg.setFont(font[0]);
			for(Button btn:btnRdo)
				btn.setFont(font[0]);
			txtWeigh.getControl().setFont(font[0]);
		}
	}

	/**
	 * Set title
	 * @param title
	 */
	public void setTitle(String title){
		lblTitle.setText(title);
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtWeigh.getControl().setFocus();
    }
    
    /**
     * Data setting (0:중량, 1:중량 구분)
     * @param val
     */
    public void setValue(String... val){
    	if(val.length>1){
    		if(val[1]!=null){
				if(val[1].equals((String)btnRdo[0].getData())){
					btnRdo[0].setSelection(true);
					btnRdo[1].setSelection(false);
					lblKg.setText("Kg");
				}else{
					btnRdo[0].setSelection(false);
					btnRdo[1].setSelection(true);
					lblKg.setText("%");
					txtWeigh.setFormatter(new NumberFormatter("###,###,##0.##"));
				}
    		}
    	}
    	if(val[0]!=null && !"".equals(val[0])){
    		txtWeigh.setValue(Double.valueOf(val[0].replaceAll(",", "")));
    	}else{
    		txtWeigh.setValue(0);
    	}
    }
    
	public String getValue(String key){
		if(KEY_CD.equals(key)){
			for(Button btn:btnRdo){
    			if(btn.getSelection())
    				return (String)btn.getData();
    		}
			return "WRONG CD";
		}else if(KEY_VAL.equals(key)){
			return String.valueOf(txtWeigh.getValue());
		}else{
    		return "WRONG KEY";
    	}
    }
	
	public void setReadonly(){
		this.setEnabled(false);
		txtWeigh.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
	}
}
