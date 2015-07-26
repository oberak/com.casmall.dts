package com.casmall.dts.ui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.common.ListAndEditSashComposite;
import com.casmall.dts.ui.usr.UsrInfEditComposite;
import com.casmall.dts.ui.usr.UsrInfListComposite;

public class UsrInfMgtView extends ViewPart {
	public static final String ID = UsrInfMgtView.class.getName();
	public UsrInfMgtView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		ListAndEditSashComposite comp = new ListAndEditSashComposite(parent, SWT.NULL);
		comp.setComposite(new UsrInfListComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER), new UsrInfEditComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER));
		comp.setWeights(new int[] { 65, 35});
	}

	@Override
	public void setFocus() {
	}
}
