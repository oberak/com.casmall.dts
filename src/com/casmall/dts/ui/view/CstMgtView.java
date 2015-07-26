package com.casmall.dts.ui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.common.ListAndEditSashComposite;
import com.casmall.dts.ui.master.CstEditComposite;
import com.casmall.dts.ui.master.CstListComposite;

public class CstMgtView extends ViewPart {
	public static final String ID = CstMgtView.class.getName();
	public CstMgtView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		ListAndEditSashComposite comp = new ListAndEditSashComposite(parent, SWT.NULL);
		comp.setComposite(new CstListComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER), new CstEditComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER));
		comp.setWeights(new int[] { 65, 35});
	}

	@Override
	public void setFocus() {
	}
}
