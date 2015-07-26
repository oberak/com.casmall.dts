package com.casmall.dts.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.action.CarMgtAction;
import com.casmall.dts.ui.action.CstMgtAction;
import com.casmall.dts.ui.action.PrdtMgtAction;
import com.casmall.dts.ui.action.WeighFirstAction;
import com.casmall.dts.ui.action.WeighListAction;
import com.casmall.dts.ui.action.WeighOneAction;
import com.casmall.dts.ui.action.WeighSecondAction;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private static Image bannerleft = ImageRepository.getImage(ImageRepository.TOOLBAR_BG_LEFT);
    private static Image bannerfill = ImageRepository.getImage(ImageRepository.TOOLBAR_BG_CENTER);
    private static Image bannerright = ImageRepository.getImage(ImageRepository.TOOLBAR_BG_RIGHT);
    
    private Composite logo;
    private Control page;

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        // 윈도우 초기 크기 지정(Full size)
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        configurer.setInitialSize(new Point(dim.width, dim.height));
    }

	@Override
	public void createWindowContents(Shell shell) {
		final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        final Menu menu = configurer.createMenuBar();
        shell.setMenuBar(menu);
        final FormLayout layout = new FormLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        shell.setLayout(layout);
        this.logo = new Composite(
                getWindowConfigurer().getWindow().getShell(), SWT.NONE);

        final GridLayout gLayout = new GridLayout(3,false);
        gLayout.horizontalSpacing = 0;
        gLayout.marginHeight = 0;
        gLayout.marginWidth = 0;
        gLayout.marginWidth = 0;
        gLayout.verticalSpacing = 0;

        this.logo.setLayout(gLayout);

        final Label left = new Label(this.logo, SWT.NONE);
        left.setImage(bannerleft);

        GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        left.setLayoutData(gd);

        final Composite fill = new Composite(this.logo, SWT.NONE);
        fill.setBackgroundImage(bannerfill);
        fill.setLayout(new GridLayout(0,false));

        final ToolBarManager tbm = new ToolBarManager(new ToolBar(fill, SWT.NONE));
        tbm.getControl().setBackgroundImage(bannerfill);
        WeighFirstAction weighFiratAction = new WeighFirstAction(getWindowConfigurer().getWindow());
        weighFiratAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_WEIGH_FIRST));
        tbm.add(weighFiratAction);

        WeighSecondAction weighSecondAction = new WeighSecondAction(getWindowConfigurer().getWindow());
        weighSecondAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_WEIGH_SECOND));
        tbm.add(weighSecondAction);

        WeighOneAction weighOneAction = new WeighOneAction(getWindowConfigurer().getWindow());
        weighOneAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_WEIGH_ONE));
        tbm.add(weighOneAction);
        
        CarMgtAction carMgtAction = new CarMgtAction(getWindowConfigurer().getWindow());
        carMgtAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_BASIS_CARMGT));
        tbm.add(carMgtAction);

        CstMgtAction cstMgtAction = new CstMgtAction(getWindowConfigurer().getWindow());
        cstMgtAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_BASIS_CSTMGT));
        tbm.add(cstMgtAction);

        PrdtMgtAction prdtMgtAction = new PrdtMgtAction(getWindowConfigurer().getWindow());
        prdtMgtAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_BASIS_PRDTMGT));
        tbm.add(prdtMgtAction);

        WeighListAction weighListAction = new WeighListAction(getWindowConfigurer().getWindow());
        weighListAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.TOOLBAR_REPORT_WEIGH_LIST));
        tbm.add(weighListAction);

        // TODO INFO toolbar 메뉴 추가

        tbm.update(true);
       
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        fill.setLayoutData(gd);

        final Label right = new Label(this.logo, SWT.NONE);
        right.setImage(bannerright);

        gd = new GridData(SWT.END, SWT.BEGINNING, false, false);
        right.setLayoutData(gd);

        this.page = configurer.createPageComposite(shell);
        this.page.getShell().setBackground(ColorRepository.getColor(ColorRepository.BG_MAIN));
        
        layoutNormal();
//		super.createWindowContents(shell);
	}
	
	private void layoutNormal() {
        FormData data = new FormData();
        data.top = new FormAttachment(0, 2);
        data.left = new FormAttachment(0, 2);
        data.right = new FormAttachment(100, -2);
        this.logo.setLayoutData(data);
        data = new FormData();
        data.top = new FormAttachment(this.logo, 2, SWT.BOTTOM);
        data.left = new FormAttachment(0, 2);
        data.right = new FormAttachment(100, -2);
        data.bottom = new FormAttachment(100,-2);
        this.page.setLayoutData(data);
        layout();
    }

    private void layout() {
        getWindowConfigurer().getWindow().getShell().layout(true);
        if (this.page != null) {
            ((Composite) this.page).layout(true);
        }
    }
}
