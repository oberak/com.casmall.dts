package com.casmall.dts.ui.home;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.casmall.dts.common.ImageRepository;
import com.swtdesigner.SWTResourceManager;

/**
 * HomeView의 날자 및 시간 display
 * @author OBERAK
 */
public class TodayComposite extends org.eclipse.swt.widgets.Composite {
	private final Image left = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_LEFT);
    private final Image center = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_CENTER);
    private final Image right = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_RIGHT);

	private final Image innerLeft = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_LEFT);
    private final Image innerCenter = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_CENTER);
    private final Image innerRight = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_RIGHT);

    private final Font titleFont = SWTResourceManager.getFont("굴림", 14, SWT.BOLD);
    private final Font contentsFont = SWTResourceManager.getFont("Arial", 15, SWT.BOLD);
    private final Color contentsColor = SWTResourceManager.getColor(SWT.COLOR_WHITE);

	private Label lblDate;
	private Label lblTime;
	
    /**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}

	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		TodayComposite inst = new TodayComposite(shell, SWT.NONE);
		Point size = inst.getSize();
		shell.setLayout(new GridLayout());
		shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.layout();
		if(size.x == 0 && size.y == 0) {
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

	public TodayComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}// TodayComposite
	
	private void initGUI() {
		try {
			final GridLayout gLayout = new GridLayout(3,false);
	        gLayout.horizontalSpacing = 0;
	        gLayout.marginHeight = 0;
	        gLayout.marginWidth = 0;
	        gLayout.marginWidth = 0;
	        gLayout.verticalSpacing = 0;
			setLayout(gLayout);
	
			// left part
			Label label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true, 1, 1));
			label.setImage(left);
			
			// center part
	        final Composite centerComposite = new Composite(this, SWT.NONE);
	        centerComposite.setBackgroundImage(center);
	        centerComposite.setBackgroundMode(SWT.INHERIT_FORCE);
	        centerComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	        GridLayout wLayout = new GridLayout(1,false);
	        wLayout.horizontalSpacing = 0;
	        wLayout.marginTop = 6;
	        wLayout.verticalSpacing = 6;
	        wLayout.marginBottom = 5;
	        centerComposite.setLayout(wLayout);
	        
	        label = new Label(centerComposite, SWT.CENTER);
	        label.setFont(titleFont);
	        label.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false));
	        label.setText("현재 시각");
	        
	        // inner composite
			final Composite infoComposite = new Composite(centerComposite, SWT.NONE);
			GridLayout innerGLayout = new GridLayout(3,false);
			innerGLayout.horizontalSpacing = 0;
			innerGLayout.marginHeight = 0;
			innerGLayout.marginWidth = 0;
			innerGLayout.marginWidth = 0;
			innerGLayout.verticalSpacing = 0;
			infoComposite.setLayout(innerGLayout);
			infoComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, true));
			
			// inner left
			label = new Label(infoComposite, SWT.NONE);
			label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true));
			label.setImage(innerLeft);
	        
			// inner center
	        final Composite innerCenterComposite = new Composite(infoComposite, SWT.NONE);
	        innerCenterComposite.setBackgroundImage(innerCenter);
	        innerCenterComposite.setBackgroundMode(SWT.INHERIT_FORCE);
	        GridData gd_innerCenterComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
	        gd_innerCenterComposite.heightHint = 75;
	        innerCenterComposite.setLayoutData( gd_innerCenterComposite);
	        GridLayout innerCLayout = new GridLayout(1,false);
	        innerCLayout.horizontalSpacing = 0;
	        innerCLayout.verticalSpacing = 0;
	        innerCenterComposite.setLayout(innerCLayout);
			
	        lblDate = new Label(innerCenterComposite, SWT.CENTER);
	        lblDate.setForeground(contentsColor);
	        lblDate.setFont(contentsFont);
	        lblDate.setText("2010-01-01");
	        lblDate.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        lblTime = new Label(innerCenterComposite, SWT.CENTER);
	        lblTime.setForeground(contentsColor);
	        lblTime.setFont(contentsFont);
	        lblTime.setText("PM 03:30");
	        lblTime.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        // inner right
	        label = new Label(infoComposite, SWT.NONE);
			label.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, false));
			label.setImage(innerRight);
			
	        // right part
	        label = new Label(this, SWT.NONE);
			label.setLayoutData(new GridData(SWT.END, SWT.BEGINNING, false, false));
			label.setImage(right);
			
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set display info.
	 * @param date
	 * @param time
	 */
	public void setTime(String date, String time){
		lblDate.setText(date);
		lblTime.setText(time);
	}// setTime
}
