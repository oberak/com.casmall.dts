package com.casmall.dts.ui.home;

import gnu.io.PortInUseException;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.CryptoUtil;
import com.casmall.common.DConstants;
import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.HomeViewInfoVO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.HomeView;
import com.casmall.dts.ui.preferences.DTSPreConstants;
import com.casmall.dts.ui.weigh.dialog.WeighFirstDialog;
import com.casmall.dts.ui.weigh.dialog.WeighSecondSelectDialog;
import com.casmall.serial.CmSerial;
import com.casmall.serial.CmSerialCallbackIF;
import com.casmall.serial.CmSerialManager;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;

public class HomeComposite extends org.eclipse.swt.widgets.Composite implements CmSerialCallbackIF{
	protected static Log logger = LogFactory.getLog(HomeComposite.class);
	private WeighComposite wc;
	private SummaryComposite sc;
	private WeighFstInftComposite wfc;
	private WeighScndInftComposite wsc;
	private String weigh;
	private int errcnt;
	private CDateTime calendarCDateTime;
	private CDateTime todayCDateTime;
	private CLabel lblCustNm;
	private CLabel lblCustTel;
	
	private static final Timer timer = new Timer();
	private ScopedPreferenceStore preferences;
	private boolean useSummary = false;
	private boolean useScnd = false;
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		HomeComposite inst = new HomeComposite(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}// showGUI

	public HomeComposite(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		useSummary = preferences.getBoolean(DTSPreConstants.GN_HOME_SUMMARY);;
		useScnd = preferences.getBoolean(DTSPreConstants.GN_HOME_SCND);;
		initGUI();
		loadData();
		try {
	        CmSerial cm = CmSerial.getInstance();
	        cm.addCallback(this);
        } catch (IOException e) {
        	MessageDialog.openError(this.getShell(),"Serial error","Serail 조작 중 오류가 발생하였습니다.\n\n"+e.getMessage());
			return;
        }
		startTimer();
		startWeigh();
	}// HomeComposite

	private void startWeigh() {
		CmSerial cm;
        try {
	        cm = CmSerial.getInstance();
	        cm.open();
        } catch (IOException e) {
	        e.printStackTrace();
	        MessageDialog.openError(this.getShell(),"Serial error","Serail 조작 중 오류가 발생하였습니다.\n\n"+e.getMessage());
			return;
        } catch (PortInUseException e) {
	        e.printStackTrace();
	        MessageDialog.openError(this.getShell(),"Port open error","Poar open 중 오류가 발생하였습니다.\n\n포트가 이미 사용 중입니다.");
			return;
        }
		
    }// startWeigh

	/**
	 * data load
	 */
	public synchronized void loadData() {
		TsWgtInfManager mgr = new TsWgtInfManager();
		HomeViewInfoVO vo = mgr.selectHomeViewInfo();
		Calendar cal = Calendar.getInstance();
		if(useSummary)
			sc.setInfo(vo.getScdCnt(), vo.getScdWeigh(), cal.get(Calendar.MONTH)+1, vo.getMonthCnt(), vo.getMonthWeigh());
	    wfc.setListData(vo.getFstList().toArray(new TsWgtInfDTO[0]));
	    if(useScnd)
	    	wsc.setListData(vo.getScdList().toArray(new TsWgtInfDTO[0]));
	    
	    CommonManager cmgr = CommonManager.getInstance();
	    CmCdDTO dto = new CmCdDTO();
	    dto.setCd_id(DTSConstants.CD_CUST_INF);
		ArrayList<CmCdDTO> list = cmgr.selectCmCd(dto);
		for(int i=0;i<list.size();i++){
				try {
					if("cust_nm".equals(list.get(i).getCd_val())){
						lblCustNm.setText(CryptoUtil.decrypt3DES(list.get(i).getCd_nm()));
					}else if("cust_tel".equals(list.get(i).getCd_val())){
						lblCustTel.setText(CryptoUtil.decrypt3DES(list.get(i).getCd_nm()));
					}
                } catch (Exception e) {
	                e.printStackTrace();
                }
		}
    }// loadData

	/**
	 * Set weigh
	 * @param wgt
	 */
	public void setWeigh(int wgt){
		NumberFormat nf = NumberFormat.getInstance();
		setWeigh(nf.format(wgt));
	}// setWeigh
	
	private void setWeigh(String weigh){
		final Display display = this.getDisplay();
		final String wgt = weigh;
		display.syncExec(new Runnable() {
			public void run() {
				try{
					if(logger.isDebugEnabled()){
						logger.debug("HomeComposite display:"+wgt);
					}
					wc.setWeigh(wgt);
				}catch(Exception e){
				}
			}
		});
	}
	/**
	 * Set connect status
	 * @param stat
	 */
	public void setConnectStatus(boolean stat){
		wc.setConnectStatus(stat);
	}// setConnectStatus
	
	/**
	 * Set data status
	 * @param stat
	 */
	public void setDataStatus(boolean stat){
		wc.setDataStatus(stat);
	}// setDataStatus
	
	private void initGUI() {
		try {
			GridLayout gridLayout = new GridLayout(4, false);
			gridLayout.verticalSpacing = 0;
			gridLayout.horizontalSpacing = 0;
			gridLayout.marginWidth = 0;
			gridLayout.marginHeight = 0;
			setLayout(gridLayout);
			setBackgroundMode(SWT.INHERIT_FORCE);
			
			// 왼쪽 라인
			{
				final Label lblLeftVLine = new Label(this, SWT.NONE);
				lblLeftVLine.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_lblLeftVLine = new GridData(SWT.CENTER, SWT.FILL, false, true, 1, 1);
				gd_lblLeftVLine.widthHint = 1;
				lblLeftVLine.setLayoutData(gd_lblLeftVLine);
			}
			// 왼쪽 
			Composite cpLeft = new Composite(this, SWT.NONE);
			GridData gd_cpLeft = new GridData(SWT.LEFT, SWT.FILL, false, true);
			gd_cpLeft.widthHint = 383;
			cpLeft.setLayoutData(gd_cpLeft);
			GridLayout gl_cpLfet = new GridLayout(1, false);
			gl_cpLfet.verticalSpacing = 0;
			gl_cpLfet.marginWidth = 0;
			gl_cpLfet.horizontalSpacing = 0;
			gl_cpLfet.marginHeight = 0;
			cpLeft.setLayout(gl_cpLfet);
			{
				// 구분자 라인
				Label lblHline = new Label(cpLeft, SWT.NONE);
				lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
				gd_hline.heightHint = 1;
				lblHline.setLayoutData(gd_hline);
			}
			// 로고 표시 부
			Composite cpLogo = new Composite(cpLeft, SWT.NONE);
			cpLogo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			GridLayout gl_cpLogo = new GridLayout(2, false);
			gl_cpLogo.marginWidth = 0;
			gl_cpLogo.marginHeight = 0;
			cpLogo.setLayout(gl_cpLogo);
			GridData gd_boxLogo = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
			gd_boxLogo.heightHint = 101;
			cpLogo.setLayoutData(gd_boxLogo);
			{
				final CLabel lblLogo = new CLabel(cpLogo, SWT.NONE);
				lblLogo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 1));
				lblLogo.setImage(ImageRepository.getImage(ImageRepository.HOME_LOGO));
				
				final CLabel lblCas = new CLabel(cpLogo, SWT.NONE);
				lblCas.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
				lblCas.setImage(ImageRepository.getImage(ImageRepository.HOME_LOGO_CAS));
			}
			{
				// 구분자 라인
				Label lblHline = new Label(cpLeft, SWT.NONE);
				lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
				gd_hline.heightHint = 1;
				lblHline.setLayoutData(gd_hline);
			}
			
			// 1차,2차 버튼
			Composite cpBtn = new Composite(cpLeft, SWT.NONE);
			cpBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			final GridLayout cpBtnLayout = new GridLayout();
			cpBtnLayout.marginWidth = 0;
			cpBtnLayout.marginHeight = 0;
			cpBtnLayout.numColumns = 2;
			cpBtnLayout.horizontalSpacing = 0;
			cpBtnLayout.verticalSpacing = 0;
			cpBtnLayout.marginLeft = 0;
			cpBtnLayout.marginRight = 0;
			cpBtnLayout.marginTop = 0;
			GridData gd_cpBtn = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
			gd_cpBtn.heightHint = 104;
			cpBtn.setLayoutData(gd_cpBtn);
			cpBtn.setLayout(cpBtnLayout);
			{
				Cursor cursor = new Cursor(Display.getDefault(), SWT.CURSOR_HAND);  

				
				final CLabel lblBtnFst = new CLabel(cpBtn, SWT.CENTER);
				lblBtnFst.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseEnter(MouseEvent e) {
//						lblBtnFst.setImage(ImageRepository.getImage(ImageRepository.HOME_BTN_SCND));
					}
				});
				lblBtnFst.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						WeighFirstDialog dialog = new WeighFirstDialog(e.display.getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						Object answer = dialog.open();
						if(answer != null){
							HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
							hv.refreshData();
						}
					}
				});
				lblBtnFst.setImage(ImageRepository.getImage(ImageRepository.HOME_BTN_FST));
				lblBtnFst.setBackgroundImage(ImageRepository.getImage(ImageRepository.HOME_BTN_FST_BG));
				GridData gd_button = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
				gd_button.heightHint = 106;
				lblBtnFst.setLayoutData(gd_button);
				lblBtnFst.setCursor(cursor);
				
				CLabel lblBtnScnd = new CLabel(cpBtn, SWT.CENTER);
				lblBtnScnd.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						WeighSecondSelectDialog dialog = new WeighSecondSelectDialog(e.display.getActiveShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						Object answer = dialog.open();
						if(answer != null){
							HomeView hv = (HomeView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(HomeView.ID);
							hv.refreshData();
						}
					}
				});
				GridData gd_lblBtnScnd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				gd_lblBtnScnd.heightHint = 106;
				lblBtnScnd.setLayoutData(gd_lblBtnScnd);
				lblBtnScnd.setImage(ImageRepository.getImage(ImageRepository.HOME_BTN_SCND));
				lblBtnScnd.setBackgroundImage(ImageRepository.getImage(ImageRepository.HOME_BTN_SCND_BG));
				lblBtnScnd.setCursor(cursor);
				
			}
			
			{
				// 구분자 라인
				Label lblHline = new Label(cpLeft, SWT.NONE);
				lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
				gd_hline.heightHint = 1;
				lblHline.setLayoutData(gd_hline);
			}
			
			// 달력/시계
			Composite cpCal = new Composite(cpLeft, SWT.NONE);
			final GridLayout cpCalLayout = new GridLayout();
			cpCalLayout.marginWidth = 0;
			cpCalLayout.marginHeight = 0;
			cpCalLayout.numColumns = 1;
			cpCalLayout.horizontalSpacing = 0;
			cpCalLayout.verticalSpacing = 0;
			cpCal.setLayout(cpCalLayout);
			cpCal.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
			{
				final CLabel todayCLabel = new CLabel(cpCal, SWT.NONE);
				todayCLabel.setFont(SWTResourceManager.getFont("굴림체", 11, SWT.BOLD));
				GridData gd_todayCLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				gd_todayCLabel.heightHint = 31;
				todayCLabel.setLayoutData(gd_todayCLabel);
				todayCLabel.setBackgroundImage(ImageRepository.getImage(ImageRepository.HOME_CAL_BG));
				
				String[] dayOfWeek={"일","월","화","수","목","금","토"};
				Calendar cal = Calendar.getInstance();
				todayCLabel.setText(" \uc624\ub298 : " + StringUtil.getDate("yyyy년 MM월 dd일 "+dayOfWeek[cal.get(Calendar.DAY_OF_WEEK)-1]+"요일"));
//				todayCLabel.setImage(ImageRepository.getImage(ImageRepository.ICO_SELECT));
				todayCLabel.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));

				{
					// 구분자 라인
					Label lblHline = new Label(cpCal, SWT.NONE);
					lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
					GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
					gd_hline.heightHint = 1;
					lblHline.setLayoutData(gd_hline);
				}
				
				BoxComposite boxToday = new BoxComposite(cpCal, SWT.NONE);
				GridData gd_boxToday = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
				gd_boxToday.horizontalIndent = 1;
				boxToday.setLayoutData(gd_boxToday);
				{
					Composite control = new Composite(boxToday, SWT.NONE);
					final GridLayout controlLayout = new GridLayout();
					controlLayout.numColumns = 2;
					controlLayout.horizontalSpacing = 0;
					controlLayout.verticalSpacing = 0;
					controlLayout.marginBottom = 0;
					controlLayout.marginHeight = 0;
					controlLayout.marginLeft = 0;
					controlLayout.marginRight = 0;
					controlLayout.marginTop = 0;
					controlLayout.marginWidth = 0;
					control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
					control.setLayout(controlLayout);
					{
						calendarCDateTime = new CDateTime(control, CDT.SIMPLE |CDT.COMPACT);
						calendarCDateTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						calendarCDateTime.setBackground(SWTResourceManager.getColor(204, 255, 255));
					}
					{
						todayCDateTime = new CDateTime(control, CDT.TIME_MEDIUM | CDT.SIMPLE);
//						todayCDateTime.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
						todayCDateTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
						todayCDateTime.setEditable(false);
						todayCDateTime.setBackground(ColorRepository.getColor(ColorRepository.HOME_WIGET_BG));
						todayCDateTime.setEnabled(false);
					}
					boxToday.add();
				}
			}
			{
				// 구분자 라인
				Label lblHline = new Label(cpLeft, SWT.NONE);
				lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
				gd_hline.heightHint = 1;
				lblHline.setLayoutData(gd_hline);
			}
			
			// Infomation
			Composite cpInfo = new Composite(cpLeft, SWT.NONE);
			final GridLayout cpInfoLayout = new GridLayout();
			cpInfoLayout.marginWidth = 0;
			cpInfoLayout.marginHeight = 0;
			cpInfoLayout.numColumns = 1;
			cpInfoLayout.horizontalSpacing = 0;
			cpInfoLayout.verticalSpacing = 0;
			cpInfo.setLayout(cpInfoLayout);
			cpInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			{
				final CLabel lblInfo = new CLabel(cpInfo, SWT.NONE);
				lblInfo.setFont(SWTResourceManager.getFont("굴림체", 11, SWT.BOLD));
				GridData gd_lblInfo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
				gd_lblInfo.heightHint = 31;
				lblInfo.setLayoutData(gd_lblInfo);
				lblInfo.setBackgroundImage(ImageRepository.getImage(ImageRepository.HOME_CAL_BG));
				lblInfo.setText(" INFOMATION");
//				todayCLabel.setImage(ImageRepository.getImage(ImageRepository.ICO_SELECT));
				lblInfo.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
				{
					// 구분자 라인
					Label lblHline = new Label(cpInfo, SWT.NONE);
					lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
					GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
					gd_hline.heightHint = 1;
					lblHline.setLayoutData(gd_hline);
				}
				
				Composite cpCustInfo = new Composite(cpInfo, SWT.NONE);
				cpCustInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				final GridLayout cpCustInfoLayout = new GridLayout();
				cpCustInfoLayout.marginWidth = 0;
				cpCustInfoLayout.marginHeight = 0;
				cpCustInfoLayout.numColumns = 2;
				cpCustInfoLayout.horizontalSpacing = 0;
				cpCustInfoLayout.verticalSpacing = 0;
				cpCustInfo.setLayout(cpCustInfoLayout);
				cpCustInfo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				{
					final CLabel lblCustNmImg = new CLabel(cpCustInfo, SWT.NONE);
					lblCustNmImg.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					lblCustNmImg.setImage(ImageRepository.getImage(ImageRepository.HOME_CUST_NM));
					lblCustNmImg.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
					
					lblCustNm = new CLabel(cpCustInfo, SWT.NONE);
					lblCustNm.setFont(SWTResourceManager.getFont("굴림체", 10, SWT.BOLD));
					lblCustNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					lblCustNm.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
					
					final CLabel lblCustTelImg = new CLabel(cpCustInfo, SWT.NONE);
					lblCustTelImg.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					lblCustTelImg.setImage(ImageRepository.getImage(ImageRepository.HOME_CUST_TEL));
					lblCustTelImg.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
					
					lblCustTel = new CLabel(cpCustInfo, SWT.NONE);
					lblCustTel.setFont(SWTResourceManager.getFont("굴림체", 10, SWT.BOLD));
					lblCustTel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					lblCustTel.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
				}
			}
			{
				// 구분자 라인
				Label lblHline = new Label(cpLeft, SWT.NONE);
				lblHline.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
				GridData gd_hline = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 1);
				gd_hline.heightHint = 1;
				lblHline.setLayoutData(gd_hline);
			}
			
			
			// 가운데 라인 ------------------------------------------------------------------------------------
			final Label line = new Label(this, SWT.NONE);
			line.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
			GridData gd_line = new GridData(SWT.CENTER, SWT.FILL, false, true, 1, 1);
			gd_line.widthHint = 1;
			line.setLayoutData(gd_line);
			
			// 오른쪽 파트
			Composite cpRight = new Composite(this, SWT.NONE);
			GridData gd_cpRight = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			gd_cpRight.horizontalIndent = 3;
			cpRight.setLayoutData(gd_cpRight);
			GridLayout gl_cpRight = new GridLayout(1, false);
			gl_cpRight.horizontalSpacing = 2;
			gl_cpRight.verticalSpacing = 2;
			gl_cpRight.marginWidth = 0;
			gl_cpRight.marginHeight = 0;
			cpRight.setLayout(gl_cpRight);
			
			// 중량표기 부
			Composite cpDisp = new Composite(cpRight, SWT.NONE);
			GridData gd_cpDisp = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
			gd_cpDisp.heightHint = 100;
			cpDisp.setLayoutData(gd_cpDisp);
			GridLayout gl_cpDisp = new GridLayout(2, false);
			gl_cpDisp.verticalSpacing = 0;
			gl_cpDisp.marginWidth = 0;
			gl_cpDisp.horizontalSpacing = 0;
			gl_cpDisp.marginHeight = 0;
			cpDisp.setLayout(gl_cpDisp);
			if(useSummary){
				sc = new SummaryComposite(cpDisp, SWT.NONE);
				GridData gd_sc = new GridData(SWT.FILL, SWT.BEGINNING, false, false);
				gd_sc.heightHint = 100;
				gd_sc.widthHint = 320;
				sc.setLayoutData(gd_sc);
			}
			{
				wc = new WeighComposite(cpDisp, SWT.NONE);
				GridData gd_wc = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
				gd_wc.heightHint = 100;
				gd_wc.widthHint = 319;
				wc.setLayoutData(gd_wc);
			}
			
			{
				// 계량자료 표시
				Composite cpWeigh = new Composite(cpRight, SWT.NONE);
				cpWeigh.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
				GridLayout gl_cpWeigh = new GridLayout(1, false);
				gl_cpWeigh.verticalSpacing = 0;
				gl_cpWeigh.marginWidth = 0;
				gl_cpWeigh.marginHeight = 0;
				gl_cpWeigh.horizontalSpacing = 0;
				cpWeigh.setLayout(gl_cpWeigh);

				wfc = new WeighFstInftComposite(cpWeigh, SWT.NONE);
				wfc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

				if(useScnd){
					wsc = new WeighScndInftComposite(cpWeigh, SWT.NONE);
					wsc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				}
			}
			
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// initGUI

	/**
	 * 1초 마다 처리하는 타이머 시작
	 */
	private void startTimer() {
	    final Display display = this.getDisplay();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(display!=null && !display.isDisposed()) {
					display.syncExec(new Runnable() {
						public void run() {
							if(!calendarCDateTime.isDisposed()){
								todayCDateTime.setSelection(new Date());
							}
							CmSerial csm;
                            try {
                                csm = CmSerialManager.getInstance();
                                if(!wc.isDisposed()){
									wc.setConnectStatus(csm.isOpen());
									wc.setDataStatus(csm.isDataInput());
                                }
                            } catch (IOException e) {
                            	logger.error(e.getMessage());
                                e.printStackTrace();
                            }
						}
					});
				} else {
					timer.cancel();
				}
			}
		};
		Calendar cal = Calendar.getInstance();
		timer.schedule(task, cal.getTime(), 1000);
    }// startTimer
	
	@Override
    public boolean isWork() {
		if(this.isDisposed())
			return false;
	    return true;
    }

	@Override
    public void notify(HashMap<String, Object> o) {
		//weigh = "#N/A";
		if(logger.isDebugEnabled()){
			logger.debug("HomeComposite be notifyed:"+o);
		}
		Object err = o.get(DConstants.MSG_ERROR);
		if(err == null){
			Object objWeight = o.get(DConstants.MSG_ATTR_WEITHT);
//			Object mcno = o.get(DConstants.MSG_ATTR_MC_NO);
			if(logger.isDebugEnabled())
				logger.debug("Weight:"+objWeight);
			if(objWeight != null && !"#N/A".equals(objWeight))
				weigh = StringUtil.extractString(StringUtil.parseDouble((String)objWeight),0);
			setWeigh(weigh);
			errcnt = 0;
		}else if("#N/A".equals((String)err)){
			//weigh="#N/A";
			if(errcnt++ > 5){
				if (logger.isDebugEnabled()) {
	                logger.debug(err+" / cnt:"+errcnt);
                }
				//setWeigh(weigh);
				errcnt = 0;
			}
		}else{
			if(logger.isDebugEnabled()){
                logger.debug(err+" / cnt:"+errcnt);
			}
			//weigh="ERR";
			if(errcnt++ > 5){
				if (logger.isDebugEnabled()) {
	                logger.debug("errcnt:"+errcnt);
                }
				//setWeigh(weigh);
				errcnt = 0;
			}
		}
    }// notify
	
	public String getWeigh(){
		return weigh;
	}
}
