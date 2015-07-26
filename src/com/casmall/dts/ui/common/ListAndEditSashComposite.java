package com.casmall.dts.ui.common;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.casmall.dts.common.ColorRepository;
import com.cloudgarden.resource.SWTResourceManager;
import com.novocode.naf.swt.custom.LiveSashForm;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ListAndEditSashComposite extends org.eclipse.swt.widgets.Composite {
	private LiveSashForm sash;
	private CallbackIF cpList;
//	private CLabel lblStatus;
//	private CLabel lblMessage;
	private CallbackIF cpEdit;

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}
		
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		ListAndEditSashComposite inst = new ListAndEditSashComposite(shell, SWT.NULL);

		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
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

	public ListAndEditSashComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout gridLayout = new GridLayout();
			gridLayout.makeColumnsEqualWidth = true;
			gridLayout.marginWidth = 0;
			gridLayout.marginHeight = 0;
			this.setLayout(gridLayout);
			this.setSize(1010, 618);
			{
				sash = new LiveSashForm(this, SWT.HORIZONTAL);
				sash.sashWidth = 6;
				GridData sashLData = new GridData();
				sashLData.verticalAlignment = GridData.FILL;
				sashLData.horizontalAlignment = GridData.FILL;
				sashLData.grabExcessVerticalSpace = true;
				sashLData.grabExcessHorizontalSpace = true;
				sash.setLayoutData(sashLData);
				sash.setBackground(ColorRepository.getColor(ColorRepository.SASH_LINE));
// status bar
//				FramedComposite status = new FramedComposite(this, SWT.EMBEDDED);
//				GridData statusGridData = new GridData();
//				statusGridData.horizontalAlignment = GridData.FILL;
//				statusGridData.grabExcessHorizontalSpace = true;
//				status.setLayoutData(statusGridData);
//
//				GridLayout statusLayout = new GridLayout();
//				statusLayout.numColumns =2;
//				status.setLayout(statusLayout);
//				{
//					lblMessage = new CLabel(status, SWT.NO_MERGE_PAINTS);
//					lblMessage.setText("Message :");
//					GridData lblMessageLData = new GridData();
//					lblMessage.setLayoutData(lblMessageLData);
//				}
//				{
//					lblStatus = new CLabel(status, SWT.NO_MERGE_PAINTS);
//					GridData lblStatusLData = new GridData();
//					lblStatusLData.horizontalAlignment = GridData.FILL;
//					lblStatusLData.grabExcessHorizontalSpace = true;
//					lblStatus.setLayoutData(lblStatusLData);
//				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setComposite(CallbackIF cpList, CallbackIF cpEdit){
		{
			this.cpList = cpList;
		}
		{
			this.cpEdit = cpEdit;
		}
		this.cpList.addCallback(this.cpEdit);
		this.cpEdit.addCallback(this.cpList);
	}
	
	public void setWeights(int[] i){
		sash.setWeights(i);
	}
	
	public void listCallback(String cmd, Object obj){
		if(CallbackIF.CMD_EDIT.equals(cmd)){
			cpEdit.callback(cmd, obj);
		}
	}

	public Composite getSashForm(){
		return sash;
	}
	
	public void setSashWidth(int w){
		this.sash.sashWidth = w;
	}
}
