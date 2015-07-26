package com.casmall.dts.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		// Perspective ∞Ì¡§
		layout.setFixed(true);
		
		layout.setEditorAreaVisible(false);
		layout.addView(HomeView.ID, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
	}
}
