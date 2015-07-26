package com.casmall.dts.ui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.common.ListAndEditSashComposite;
import com.casmall.dts.ui.master.PrdtEditComposite;
import com.casmall.dts.ui.master.PrdtListComposite;

public class PrdtMgtView extends ViewPart {
	public static final String ID = PrdtMgtView.class.getName();
	public PrdtMgtView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		ListAndEditSashComposite comp = new ListAndEditSashComposite(parent, SWT.NULL);
		comp.setComposite(new PrdtListComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER), new PrdtEditComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER));
		comp.setWeights(new int[] { 65, 35});
	}

	@Override
	public void setFocus() {
	}
}
