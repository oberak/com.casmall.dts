package com.casmall.dts.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.action.CarMgtAction;
import com.casmall.dts.ui.action.CstMgtAction;
import com.casmall.dts.ui.action.HomeAction;
import com.casmall.dts.ui.action.MessageConfigAction;
import com.casmall.dts.ui.action.PrdtMgtAction;
import com.casmall.dts.ui.action.SerialConfigAction;
import com.casmall.dts.ui.action.UsrInfMgtAction;
import com.casmall.dts.ui.action.WeighFirstAction;
import com.casmall.dts.ui.action.WeighListAction;
import com.casmall.dts.ui.action.WeighOneAction;
import com.casmall.dts.ui.action.WeighSecondAction;
import com.casmall.usr.mgr.SessionManager;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
//	private UpdateAction updateAction;
	
	private Action homeAction;
	
	private Action weighFirstAction;
	private Action weighSecondAction;
	private Action weighOneAction;
	private Action messageConfigAction;
	
	private CarMgtAction carMgtAction;
	private CstMgtAction cstMgtAction;
	private PrdtMgtAction prdtMgtAction;
	private UsrInfMgtAction usrInfMgtAction;

	private IWorkbenchAction preferenceAction;
	
	private Action serialConfigAction;
	private WeighListAction weighListAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = ActionFactory.QUIT.create(window);
    	exitAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_EXIT));
    	register(exitAction);

    	aboutAction = ActionFactory.ABOUT.create(window);
    	aboutAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_ABOUT));
    	register(aboutAction);

    	homeAction = new HomeAction(window);

    	weighFirstAction = new WeighFirstAction(window);
    	register(weighFirstAction);
    	
    	weighOneAction = new WeighOneAction(window);
    	register(weighOneAction);
    	
    	weighSecondAction = new WeighSecondAction(window);
    	register(weighSecondAction);
    	
    	serialConfigAction = new SerialConfigAction(window);
    	register(serialConfigAction);
    	
    	messageConfigAction = new MessageConfigAction(window);
    	register(messageConfigAction);
    	
    	carMgtAction = new CarMgtAction(window);
    	register(carMgtAction);
    	cstMgtAction = new CstMgtAction(window);
    	register(cstMgtAction);
    	prdtMgtAction = new PrdtMgtAction(window);
    	register(prdtMgtAction);
    	usrInfMgtAction = new UsrInfMgtAction(window);
    	register(usrInfMgtAction);
    	weighListAction = new WeighListAction(window);
    	register(weighListAction);
    	
    	preferenceAction = ActionFactory.PREFERENCES.create(window);
    	preferenceAction.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(DTSConstants.PLUGIN_ID, ImageRepository.MENU_PREFERENCE));
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager("&File", "File");
    	fileMenu.add(homeAction);
    	fileMenu.add(exitAction);

    	MenuManager weighMenu = new MenuManager("계량", "계량");
    	weighMenu.add(weighFirstAction);
    	weighMenu.add(weighSecondAction);
    	weighMenu.add(weighOneAction);
    	weighMenu.add(weighListAction);

    	MenuManager masterMenu = new MenuManager("기초 정보", "기초 정보");
    	masterMenu.add(carMgtAction);
    	masterMenu.add(cstMgtAction);
    	masterMenu.add(prdtMgtAction);
    	masterMenu.add(usrInfMgtAction);

//    	MenuManager reportMenu = new MenuManager("&Report", "보고서");
//    	reportMenu.add(weighListAction);
    	
    	MenuManager optionMenu = new MenuManager("&Option", "환경 설정");
    	
		if( DTSConstants.CD_USR_GRD_ROOT.equals(SessionManager.getInstance().getUsr().getAth_grd()) ){
	    	optionMenu.add(serialConfigAction);
	    	optionMenu.add(messageConfigAction);
		}
    	optionMenu.add(preferenceAction);

    	MenuManager helpMenu = new MenuManager("&Help", "Help");
//    	helpMenu.add(updateAction);
    	helpMenu.add(aboutAction);
    	
    	menuBar.add(fileMenu);
    	menuBar.add(weighMenu);
//    	menuBar.add(reportMenu);
    	menuBar.add(masterMenu);
    	menuBar.add(optionMenu);
    	menuBar.add(helpMenu);
    }
    
}
