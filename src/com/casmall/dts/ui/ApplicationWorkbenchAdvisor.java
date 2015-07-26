package com.casmall.dts.ui;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.IWorkbenchThemeConstants;
import org.eclipse.ui.themes.ITheme;
import org.eclipse.ui.themes.IThemeManager;

@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.casmall.dts.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		//  윈도우 위치와 크기 저장
		configurer.setSaveAndRestore(true);

		// 색상변경
		IThemeManager themeManager = PlatformUI.getWorkbench().getThemeManager();
		ITheme currentTheme = themeManager.getCurrentTheme();
		ColorRegistry colorRegistry = currentTheme.getColorRegistry();
		colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_BG_START,new RGB(16,86,100));
		colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_BG_END,new RGB(219,238,241));
		colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_TEXT_COLOR,new RGB(0,0,255));

		colorRegistry.put(IWorkbenchThemeConstants.ACTIVE_TAB_PERCENT,new RGB(255,0,0));

		super.initialize(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
