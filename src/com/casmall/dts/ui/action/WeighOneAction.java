package com.casmall.dts.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.weigh.dialog.WeighOneDialog;

public class WeighOneAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = WeighOneAction.class.getName();
	
	public WeighOneAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("1회 계량");
		setToolTipText("1회 계량 처리.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_WEIGH_ONE));
//		setHoverImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(RVConstants.PLUGIN_ID, IImageKeys.TOOL_01_ON));
	}

	@Override
	public void run() {
		super.run();
		WeighOneDialog dialog = new WeighOneDialog(window.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		Object answer = dialog.open();
		if(answer != null){
			HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
			hv.refreshData();
		}
	}
}
