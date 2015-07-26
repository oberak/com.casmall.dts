package com.casmall.dts.ui.home;

import java.text.NumberFormat;

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

import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.ImageRepository;
import com.swtdesigner.SWTResourceManager;

public class SummaryComposite extends org.eclipse.swt.widgets.Composite {
	private final Image left = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_LEFT);
    private final Image center = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_CENTER);
    private final Image right = ImageRepository.getImage(ImageRepository.HOME_INFO_BG_RIGHT);

	private final Image innerLeft = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_LEFT);
    private final Image innerCenter = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_CENTER);
    private final Image innerRight = ImageRepository.getImage(ImageRepository.HOME_INFO_INNER_BG_RIGHT);

    private final Font titleFont = SWTResourceManager.getFont("굴림", 14, SWT.BOLD);
    private final Font contentsFont = SWTResourceManager.getFont("Arial", 15, SWT.BOLD);
    private final Color contentsColor = SWTResourceManager.getColor(SWT.COLOR_WHITE);

	private Label lblCountToday;
	private Label lblSumToday;
	private Label lblCountMonth;
	private Label lblSumMonth;
	private Label lblMonth;
	
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
		SummaryComposite inst = new SummaryComposite(shell, SWT.NONE);
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

	public SummaryComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}// SummaryComposite
	
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
	        GridData gd_centerComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
	        gd_centerComposite.heightHint = 156;
	        centerComposite.setLayoutData(gd_centerComposite);
	        GridLayout wLayout = new GridLayout(1,false);
	        wLayout.horizontalSpacing = 0;
	        wLayout.marginTop = 6;
	        wLayout.verticalSpacing = 6;
	        wLayout.marginBottom = 5;
	        centerComposite.setLayout(wLayout);
	        
	        label = new Label(centerComposite, SWT.CENTER);
	        label.setFont(titleFont);
	        label.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false));
	        label.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
	        label.setText("계량 요약");
	        
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
	        GridLayout innerCLayout = new GridLayout(5,false);
	        innerCLayout.verticalSpacing = 0;
	        innerCenterComposite.setLayout(innerCLayout);
	        
	        label = new Label(innerCenterComposite, SWT.NONE);
	        label.setForeground(contentsColor);
	        label.setFont(contentsFont);
	        label.setText("오늘");
			
	        lblCountToday = new Label(innerCenterComposite, SWT.RIGHT);
	        lblCountToday.setForeground(contentsColor);
	        lblCountToday.setFont(contentsFont);
	        lblCountToday.setText("1,000");
	        lblCountToday.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        label = new Label(innerCenterComposite, SWT.NONE);
	        label.setForeground(contentsColor);
	        label.setFont(contentsFont);
	        label.setText("건");
	        
	        lblSumToday = new Label(innerCenterComposite, SWT.RIGHT);
	        lblSumToday.setForeground(contentsColor);
	        lblSumToday.setFont(contentsFont);
	        lblSumToday.setText("1,000,000");
	        lblSumToday.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        label = new Label(innerCenterComposite, SWT.NONE);
	        label.setForeground(contentsColor);
	        label.setFont(contentsFont);
	        label.setText("Kg");
	
	        lblMonth = new Label(innerCenterComposite, SWT.NONE);
	        lblMonth.setForeground(contentsColor);
	        lblMonth.setFont(contentsFont);
	        lblMonth.setText("12월");
			
	        lblCountMonth = new Label(innerCenterComposite, SWT.RIGHT);
	        lblCountMonth.setForeground(contentsColor);
	        lblCountMonth.setFont(contentsFont);
	        lblCountMonth.setText("2,000");
	        lblCountMonth.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        label = new Label(innerCenterComposite, SWT.NONE);
	        label.setForeground(contentsColor);
	        label.setFont(contentsFont);
	        label.setText("건");
	        
	        lblSumMonth = new Label(innerCenterComposite, SWT.RIGHT);
	        lblSumMonth.setForeground(contentsColor);
	        lblSumMonth.setFont(contentsFont);
	        lblSumMonth.setText("100,000,000");
	        lblSumMonth.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	
	        label = new Label(innerCenterComposite, SWT.NONE);
	        label.setForeground(contentsColor);
	        label.setFont(contentsFont);
	        label.setText("Kg");
	        
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
	}// initGUI

	/**
	 * Set display info.
	 * @param todayCount
	 * @param todaySum
	 * @param month
	 * @param monthCount
	 * @param monthSum
	 */
	public void setInfo(int todayCount, double todaySum, int month, int monthCount, double monthSum){
		NumberFormat nf = NumberFormat.getInstance();
		lblCountToday.setText(nf.format(todayCount));
		lblSumToday.setText(nf.format(todaySum));
		lblMonth.setText(month+"월");
		lblCountMonth.setText(nf.format(monthCount));
		lblSumMonth.setText(nf.format(monthSum));
	}// setInfo
}
