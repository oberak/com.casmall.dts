package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

public class FormattedInputComposite extends Composite{
	private Label lblTitle;
	private FormattedText txtInput;
	private int style;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FormattedInputComposite(Composite parent, int style) {
		super(parent, style);
		this.style = style;
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(2, false));

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		txtInput = new FormattedText(this, SWT.BORDER | style);
		txtInput.setFormatter(new NumberFormatter("#,###"));
		txtInput.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtInput.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtInput.getControl().addKeyListener(new KeyAdapter() {
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
		FormattedInputComposite inst = new FormattedInputComposite(shell, SWT.NULL);
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
	
	public void setFont(Font font){
		lblTitle.setFont(font);
		txtInput.getControl().setFont(font);
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
		txtInput.getControl().setText(text);
	}
	
	public void setValue(String... val){
		txtInput.getControl().setText(val[0]);
	}
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtInput.getControl().setFocus();
    }
    
    public String getValue(String key){
    	return txtInput.getControl().getText();
    }
    
    public void setReadonly(){
    	this.setEnabled(false);
    	txtInput.getControl().setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    }
    
    public void setFormatter(ITextFormatter formatter){
    	txtInput.setFormatter(formatter);
    }


}
