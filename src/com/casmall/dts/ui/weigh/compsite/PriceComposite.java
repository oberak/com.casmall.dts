package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.SWTResourceManager;

public class PriceComposite extends Composite{
	public static final String KEY_UP = "UP";
	public static final String KEY_AMT = "AMT";
	
	private Label lblTitle;
	private FormattedText txtUnitPrice;
	private int style;
	private FormattedText txtAmount;
	private Label lblSep;
	private Label lblUnit;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public PriceComposite(Composite parent, int style) {
		super(parent, style);
		this.style = style;
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(5, false));

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));
		lblTitle.setText("단가/금액");
		
		txtUnitPrice = new FormattedText(this, SWT.BORDER | style);
		txtUnitPrice.setFormatter(new NumberFormatter("###,###,###"));
		txtUnitPrice.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtUnitPrice.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		txtUnitPrice.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.character;
				Text text = (Text)e.widget;
				if(0x0D == c){
					// Enter 일 경우 Tab 처리
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
			}
		});
		lblSep = new Label(this, SWT.NONE);
		lblSep.setText("원 / ");
		
		txtAmount = new FormattedText(this, SWT.BORDER | style);
		txtAmount.setFormatter(new NumberFormatter("###,###,###"));
		txtAmount.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtAmount.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtAmount.getControl().setEnabled(false);
		txtAmount.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char c = e.character;
				Text text = (Text)e.widget;
				if(0x0D == c){
					// Enter 일 경우 Tab 처리
					text.traverse(SWT.TRAVERSE_TAB_NEXT);
					return;
				}
			}
		});
		
		lblUnit = new Label(this, SWT.NONE);
		lblUnit.setText("원 ");
	}// initGUI

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		PriceComposite inst = new PriceComposite(shell, SWT.NULL);
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
	
	public void addModifyListener (ModifyListener listener) {
		txtUnitPrice.getControl().addModifyListener(listener);
	}
	
	/**
	 * Font 설정<br>
	 * 0 : title, 1 : label, 2 : input
	 * @param font
	 */
	public void setFont(Font... font){
		lblTitle.setFont(font[0]);
		txtUnitPrice.getControl().setFont(font[0]);
		txtAmount.getControl().setFont(font[0]);
		if(font.length > 1){
			lblUnit.setFont(font[1]);
			lblSep.setFont(font[1]);
		}else{
			lblUnit.setFont(font[0]);
			lblSep.setFont(font[0]);
		}
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
	public void setText(String text){
		txtUnitPrice.getControl().setText(text);
	}
	
	public void setValue(String key, String val){
		if(KEY_UP.equals(key)){
			txtUnitPrice.setValue(Long.parseLong(val));
		}else if(KEY_AMT.equals(key)){
			txtAmount.setValue(Long.parseLong(val));
		}
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtUnitPrice.getControl().setFocus();
    }
    
    public String getValue(String key){
    	if(KEY_UP.equals(key)){
    		return txtUnitPrice.getControl().getText();
    	}else if(KEY_AMT.equals(key)){
    		return txtAmount.getControl().getText();
    	}
    	return null;
    }
    
    public void setReadonly(){
    	this.setEnabled(false);
    	txtUnitPrice.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    	txtAmount.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    }
    
    public void setFormatter(ITextFormatter formatter){
    	txtUnitPrice.setFormatter(formatter);
    	txtAmount.setFormatter(formatter);
    }
}
