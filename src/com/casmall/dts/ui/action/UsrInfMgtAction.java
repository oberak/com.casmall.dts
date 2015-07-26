package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.view.UsrInfMgtView;

public class UsrInfMgtAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = UsrInfMgtAction.class.getName();
	
	public UsrInfMgtAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("����� ����");
		setToolTipText("����� ������ �����մϴ�.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_BASIS_USRINF));
	}

	@Override
	public void run() {
		super.run();
		try {
			window.getActivePage().showView(UsrInfMgtView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
