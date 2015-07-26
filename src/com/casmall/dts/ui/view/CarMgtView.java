package com.casmall.dts.ui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.casmall.dts.ui.common.ListAndEditSashComposite;
import com.casmall.dts.ui.master.CarEditComposite;
import com.casmall.dts.ui.master.CarListComposite;

public class CarMgtView extends ViewPart {
	public static final String ID = CarMgtView.class.getName();
	public CarMgtView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		ListAndEditSashComposite comp = new ListAndEditSashComposite(parent, SWT.NULL);
		comp.setComposite(new CarListComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER), new CarEditComposite(comp.getSashForm(), SWT.PUSH | SWT.CENTER));
		comp.setWeights(new int[] { 65, 35});
	}

	@Override
	public void setFocus() {
	}
}
