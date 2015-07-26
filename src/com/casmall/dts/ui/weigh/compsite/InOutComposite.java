package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

import com.casmall.dts.common.DTSConstants;

/**
 * 입출고 구분
 * 
 * @author OBERAK
 */
public class InOutComposite extends Composite{
	private Label lblTitle;
	private Button[] btnRdo = new Button[2];
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public InOutComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(3, false));

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		btnRdo[0] = new Button(this, SWT.RADIO);
		btnRdo[0].setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		btnRdo[0].setText("입고");
		btnRdo[0].setData(DTSConstants.CD_INOUT_IN);
		btnRdo[0].setSelection(true);
		btnRdo[0].addKeyListener(new KeyAdapter() {
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
		
		btnRdo[1] = new Button(this, SWT.RADIO);
		btnRdo[1].setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		btnRdo[1].setText("출고");
		btnRdo[1].setData(DTSConstants.CD_INOUT_OUT);
		
		btnRdo[1].addKeyListener(new KeyAdapter() {
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
	}// initGUI

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		InOutComposite inst = new InOutComposite(shell, SWT.NULL);
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
	
	/**
	 * Font 설정<br>
	 * 0 : title, 1 : label, 2 : input
	 * @param font
	 */
	public void setFont(Font... font){
		lblTitle.setFont(font[0]);
		if(font.length > 1){
			for(Button btn:btnRdo)
				btn.setFont(font[1]);
		}else{
			for(Button btn:btnRdo)
				btn.setFont(font[0]);
		}
	}
	
	/**
	 * Set title
	 * @param title
	 */
	public void setTitle(String title){
		lblTitle.setText(title);
	}
	
	public void addSelectionListener (SelectionListener listener) {
		btnRdo[0].addSelectionListener(listener);
		btnRdo[1].addSelectionListener(listener);
	}
	
	public void setValue(String... val){
		if(val[0]!=null){
			if(val[0].equals((String)btnRdo[0].getData())){
				btnRdo[0].setSelection(true);
				btnRdo[1].setSelection(false);
			}else if(val[0].equals((String)btnRdo[1].getData())){
				btnRdo[0].setSelection(false);
				btnRdo[1].setSelection(true);
			}
		}
	}
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return btnRdo[0].setFocus();
    }
    
    public String getValue(String key){
    	for(Button btn:btnRdo){
			if(btn.getSelection())
				return (String)btn.getData();
		}
    	return null;
    }
    
    public void setReadonly(){
    	this.setEnabled(false);
    }
}
