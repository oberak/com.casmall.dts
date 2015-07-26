package com.casmall.dts.ui.action;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.common.dialog.MessageConfig;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;

/**
 * About dialog popup action
 * @author OBERAK
 */
public class MessageConfigAction extends Action{
	private IWorkbenchWindow window;
	public final static String ID = MessageConfigAction.class.getName();
	
	public MessageConfigAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Message config");
		setToolTipText("Message configuration");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_CONFIC_MSG));
	}

	@Override
	public void run() {
		super.run();
		MessageConfig inst = new MessageConfig(window.getShell(), SWT.NULL);
		inst.open();
	}
}
