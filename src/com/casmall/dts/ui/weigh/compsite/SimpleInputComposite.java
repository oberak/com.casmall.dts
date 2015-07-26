package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

public class SimpleInputComposite extends Composite{
	private Label lblTitle;
	private Text txtInput;
	private int style;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public SimpleInputComposite(Composite parent, int style) {
		super(parent, style);
		this.style = style;
		initGUI();
	}

	private void initGUI() {
		GridLayout gridLayout = new GridLayout(2, false);
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
		
		txtInput = new Text(this, SWT.BORDER | style);
		txtInput.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		txtInput.addKeyListener(new KeyAdapter() {
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
		SimpleInputComposite inst = new SimpleInputComposite(shell, SWT.NULL);
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
	
	public void setFont(Font font){
		lblTitle.setFont(font);
		txtInput.setFont(font);
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
		txtInput.setText(text);
	}
	
	public void setValue(String... val){
		txtInput.setText(val[0]);
	}
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtInput.setFocus();
    }
    
    public String getValue(String key){
    	return txtInput.getText();
    }
    
    public void setReadonly(){
    	this.setEnabled(false);
    	txtInput.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    }
    
	public void addFocusListener (FocusListener listener) {
		txtInput.addFocusListener(listener);
	}
}
