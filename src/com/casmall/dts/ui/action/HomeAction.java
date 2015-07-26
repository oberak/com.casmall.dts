package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.HomeView;

public class HomeAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = HomeAction.class.getName();
	
	public HomeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Home");
		setToolTipText("Home display");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_HOME));
	}

	@Override
	public void run() {
		super.run();
		try {
			window.getActivePage().showView(HomeView.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
