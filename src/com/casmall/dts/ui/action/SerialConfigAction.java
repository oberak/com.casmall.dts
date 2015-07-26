package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.common.dialog.SerialConfig;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;

/**
 * Serial communication dialog popup action
 * @author OBERAK
 */
public class SerialConfigAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = SerialConfigAction.class.getName();
	
	public SerialConfigAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Serial config");
		setToolTipText("Serial commucation configuration");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_CONFIC_SERIAL));
	}

	@Override
	public void run() {
		super.run();
		SerialConfig inst = new SerialConfig(window.getShell(), SWT.NULL);
		inst.open();
	}
}
