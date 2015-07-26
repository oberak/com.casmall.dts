package com.casmall.dts.ui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.weigh.WeighListComposite;

public class WeighListView extends ViewPart {
	public static final String ID = WeighListView.class.getName();
	public WeighListView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		new WeighListComposite(parent, SWT.NONE);
	}

	@Override
	public void setFocus() {
	}
}
