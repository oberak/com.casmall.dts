package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.view.WeighListView;

public class WeighListAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = WeighListAction.class.getName();
	
	public WeighListAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("계량 자료 조회");
		setToolTipText("계량 완료된 자료를 조회합니다.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_REPORT_WEIGH_LIST));
	}

	@Override
	public void run() {
		super.run();
		try {
			window.getActivePage().showView(WeighListView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
