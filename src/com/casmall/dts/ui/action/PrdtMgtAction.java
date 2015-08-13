package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.view.PrdtMgtView;

public class PrdtMgtAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = PrdtMgtAction.class.getName();
	
	public PrdtMgtAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("제품 정보 관리");
		setToolTipText("제품 정보를 관리합니다.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_BASIS_PRDTMGT));
	}

	@Override
	public void run() {
		super.run();
		try {
			window.getActivePage().showView(PrdtMgtView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
