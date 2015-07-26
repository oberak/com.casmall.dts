package com.casmall.dts.ui.home;

import org.eclipse.swt.SWT;
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

public class WeighComposite extends org.eclipse.swt.widgets.Composite {
	private static Image left = ImageRepository.getImage(ImageRepository.WEIGH_BG_LEFT);
    private static Image center = ImageRepository.getImage(ImageRepository.WEIGH_BG_CENTER);
    private static Image right = ImageRepository.getImage(ImageRepository.WEIGH_BG_RIGHT);

    private static Image conn_on = ImageRepository.getImage(ImageRepository.STAT_CONN_ON);
    private static Image conn_off = ImageRepository.getImage(ImageRepository.STAT_CONN_OFF);
    private static Image data_on = ImageRepository.getImage(ImageRepository.STAT_DATA_ON);
    private static Image data_off = ImageRepository.getImage(ImageRepository.STAT_DATA_OFF);
    
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}

	private Label lblConnStat;
	private Label lblWeigh;
	private Label lblDataStat;
	private Label lblLeft;
	private GridData gd_1;
	
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
		WeighComposite inst = new WeighComposite(shell, SWT.NONE);
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

	public WeighComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
        final GridLayout gLayout = new GridLayout(3,false);
        gLayout.horizontalSpacing = 0;
        gLayout.marginHeight = 0;
        gLayout.marginWidth = 0;
        gLayout.marginWidth = 0;
        gLayout.verticalSpacing = 0;
		setLayout(gLayout);

		Label label;
		lblLeft = new Label(this, SWT.NONE);
		lblLeft.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, true, 1, 1));
		lblLeft.setImage(left);
		
        final Composite centerComposite = new Composite(this, SWT.NONE);
        centerComposite.setBackgroundImage(center);
        centerComposite.setBackgroundMode(SWT.INHERIT_FORCE);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        centerComposite.setLayoutData(gd);
        GridLayout wLayout = new GridLayout(3,false);
        wLayout.horizontalSpacing = 0;
        wLayout.verticalSpacing = 0;
        centerComposite.setLayout(wLayout);
        
        lblConnStat = new Label(centerComposite, SWT.NONE);
        gd_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        gd_1.heightHint = 43;
        gd_1.verticalIndent = 5;
        lblConnStat.setLayoutData(gd_1);
        lblConnStat.setImage(conn_off);
        
        lblWeigh = new Label(centerComposite, SWT.RIGHT);
        lblWeigh.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        lblWeigh.setFont(SWTResourceManager.getFont("Arial", 55, SWT.BOLD));
        gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3);
        lblWeigh.setLayoutData(gd);
        
		Label labelKg = new Label(centerComposite, SWT.HORIZONTAL | SWT.SHADOW_NONE);
		labelKg.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		labelKg.setFont(SWTResourceManager.getFont("Arial", 35, SWT.BOLD));
		labelKg.setLayoutData(new GridData(SWT.END, SWT.BOTTOM, false, true, 1, 2));
		labelKg.setText("Kg");
        
                lblDataStat = new Label(centerComposite, SWT.NONE);
                gd = new GridData(SWT.LEFT, SWT.TOP, false, false);
                gd.verticalIndent = 3;
                lblDataStat.setLayoutData(gd);
                lblDataStat.setImage(data_off);


		label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.END, SWT.BEGINNING, false, false));
		label.setImage(right);
		initGUI();
	}
	
	public void setWeigh(String wgt){
		if(!lblWeigh.isDisposed())
			lblWeigh.setText(wgt);
	}
	
	public void setConnectStatus(boolean stat){
		if(stat){
			lblConnStat.setImage(conn_on);
		}else{
			lblConnStat.setImage(conn_off);
		}
	}

	public void setDataStatus(boolean stat){
		if(stat){
			lblDataStat.setImage(data_on);
		}else{
			lblDataStat.setImage(data_off);
		}
	}
	private void initGUI() {
		try {
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
