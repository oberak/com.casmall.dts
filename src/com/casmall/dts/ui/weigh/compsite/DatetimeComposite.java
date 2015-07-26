package com.casmall.dts.ui.weigh.compsite;

import java.util.HashMap;

import org.eclipse.nebula.widgets.formattedtext.DateFormatter;
import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
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

public class DatetimeComposite extends Composite{
	public static final String KEY_DATE = "DATE";
	public static final String KEY_TIME = "TIME";
	
	private Label lblTitle;
	private Text txtDate;
	private Text txtTime;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DatetimeComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginLeft = 5;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		setLayout(gridLayout);

		lblTitle = new Label(this, SWT.NONE);
		lblTitle.setText("title");
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true));
		FormattedText fmtDate = new FormattedText(this, SWT.BORDER|SWT.CENTER);
		fmtDate.setFormatter(new DateFormatter("yyyy-MM-dd"));
		txtDate = fmtDate.getControl();
		txtDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		txtDate.addKeyListener(new KeyAdapter() {
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
		
		FormattedText fmtTime = new FormattedText(this, SWT.BORDER|SWT.CENTER);
		fmtTime.setFormatter(new DateTimeFormatter("H:m"));
		txtTime = fmtTime.getControl();
		txtTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtTime.addKeyListener(new KeyAdapter() {
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
		DatetimeComposite inst = new DatetimeComposite(shell, SWT.NULL);
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
		txtDate.setFont(font);
		txtTime.setFont(font);
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
	public void setText(String date, String time){
		txtDate.setText(date);
		txtTime.setText(time);
	}
	
	public HashMap<String, String> getValue(){
		HashMap<String, String> result = new HashMap<String, String>();
		result.put(KEY_DATE, txtDate.getText());
		result.put(KEY_TIME, txtTime.getText()+":00");
		return result;
	}
	
	public String getValue(String key){
		if(KEY_DATE.equals(key)){
			return txtDate.getText();
		}else if(KEY_TIME.equals(key)){
			return txtTime.getText();
		}else{
			return "WRONG KEY";
		}
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
		return txtDate.setFocus();
    }

	public void setReadonly() {
		txtTime.setEditable(false);
		txtDate.setEditable(false);
		txtDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
    }
	
	public void addFocusListener (FocusListener listener) {
		txtDate.addFocusListener(listener);
		txtTime.addFocusListener(listener);
	}
}
