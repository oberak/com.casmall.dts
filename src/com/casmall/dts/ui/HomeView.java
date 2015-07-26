package com.casmall.dts.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.home.HomeComposite;

public class HomeView extends ViewPart {
    public static final String ID = HomeView.class.getName(); 

    HomeComposite hc;
    /**
     * 
     */
    public HomeView() {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
    	hc = new HomeComposite(parent, SWT.NULL);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
    }
    
    /**
     * Cleans up all resources created by this ViewPart.
     */
    public void dispose() {
        super.dispose();
    }
    
    public synchronized void refreshData(){
    	hc.loadData();
    }
    
    public String getWeigh(){
    	if(hc.getWeigh() == null)
    		return null;
    	return hc.getWeigh().replaceAll(",", "");
    }
    
}
