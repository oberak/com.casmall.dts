package com.casmall.dts.ui.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.weigh.dialog.WeighSecondSelectDialog;

public class WeighSecondAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = WeighSecondAction.class.getName();
	
	public WeighSecondAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("2차 계량");
		setToolTipText("2차 계량 시작.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_WEIGH_SECOND));
//		setHoverImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(RVConstants.PLUGIN_ID, IImageKeys.TOOL_01_ON));
	}

	@Override
	public void run() {
		super.run();
		WeighSecondSelectDialog dialog = new WeighSecondSelectDialog(window.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		Object answer = dialog.open();
		if(answer != null){
			HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
			hv.refreshData();
		}
	}
}
