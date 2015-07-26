package com.casmall.dts.ui.weigh.dialog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.domain.TsPrdtMstDTO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsMstManager;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.common.ObjectUtil;
import com.casmall.dts.common.PrintUtil;
import com.casmall.dts.ui.preferences.DTSPreConstants;
import com.casmall.dts.ui.weigh.compsite.CarComposite;
import com.casmall.dts.ui.weigh.compsite.CstComposite;
import com.casmall.dts.ui.weigh.compsite.DatetimeComposite;
import com.casmall.dts.ui.weigh.compsite.PrdtComposite;
import com.casmall.dts.ui.weigh.compsite.SimpleInputComposite;
import com.casmall.dts.ui.weigh.compsite.WeighInputComposite;
import com.casmall.usr.domain.CmUsrInfDTO;
import com.casmall.usr.mgr.SessionManager;
import com.swtdesigner.SWTResourceManager;

public class WeighFirstDialog extends Dialog {
	protected static Log logger = LogFactory.getLog(WeighFirstDialog.class);
			
	protected final Font titleFont = SWTResourceManager.getFont("굴림체", 23, SWT.BOLD);
	protected final Font defaultFont = SWTResourceManager.getFont("굴림체", 17, SWT.BOLD);
	protected final Font helpFont = SWTResourceManager.getFont("굴림체", 12, SWT.NONE);
	protected final Font btnFont = SWTResourceManager.getFont("굴림체", 15, SWT.BOLD);
	protected final Font labelFont = SWTResourceManager.getFont("굴림체", 14, SWT.NONE);
	
	protected Object result;
	protected Shell shell;

	private CarComposite car;
	private CstComposite cst;
	private PrdtComposite prdt;
	private DatetimeComposite fstDateTime;
	private SimpleInputComposite note;
	private WeighInputComposite fstWeigh;
	private Composite compCenter;

	private SimpleInputComposite wgtNum;
	
	private ScopedPreferenceStore preferences;
	private CmUsrInfDTO user;

	private boolean useCust;
	private boolean usePrdt;

	private CLabel lblHelp;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WeighFirstDialog(Shell parent, int style) {
		super(parent, style);
		setText("1차 계량");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		user = SessionManager.getInstance().getUsr();
		
		useCust = preferences.getBoolean(DTSPreConstants.DATA_CUST_FLAG);
		usePrdt = preferences.getBoolean(DTSPreConstants.DATA_PRDT_FLAG);
		
		createContents();
		shell.open();
		shell.layout();
		
		car.setFocus();
		init();
		
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(490, 540);
		shell.setText(getText());
		
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.verticalSpacing = 0;
		gl_shell.horizontalSpacing = 0;
		gl_shell.marginBottom = 1;
		gl_shell.marginLeft = 1;
		gl_shell.marginRight = 1;
		gl_shell.marginTop = 1;
		gl_shell.marginWidth = 0;
		gl_shell.marginHeight = 0;
		shell.setLayout(gl_shell);
		shell.setBackground(ColorRepository.getColor(ColorRepository.HOME_LINE));
		shell.setBackgroundMode(SWT.TRANSPARENT);
		
		CLabel lblTitle = new CLabel(shell, SWT.CENTER);
		lblTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblTitle = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_lblTitle.heightHint = 44;
		lblTitle.setLayoutData(gd_lblTitle);
		lblTitle.setFont(titleFont);
		lblTitle.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_TITLE_BG));
		lblTitle.setText("1\uCC28 \uACC4\uB7C9");			
		
		Composite compContents = new Composite(shell, SWT.NONE);
		compContents.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		compContents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout gl_compContents = new GridLayout(1, false);
		gl_compContents.marginTop = 10;
		gl_compContents.marginRight = 10;
		gl_compContents.marginLeft = 10;
		gl_compContents.marginBottom = 10;
		gl_compContents.verticalSpacing = 0;
		gl_compContents.marginWidth = 0;
		gl_compContents.marginHeight = 0;
		compContents.setLayout(gl_compContents);
		
		compCenter = new Composite(compContents, SWT.BORDER);
		compCenter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_compCenter = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_compCenter.heightHint = 237;
		compCenter.setLayoutData(gd_compCenter);
		GridLayout gl_compCenter = new GridLayout(1, false);
		gl_compCenter.horizontalSpacing = 0;
		gl_compCenter.verticalSpacing = 0;
		gl_compCenter.marginWidth = 0;
		gl_compCenter.marginHeight = 0;
		compCenter.setLayout(gl_compCenter);
		
		wgtNum = new SimpleInputComposite(compCenter, SWT.NONE);
		wgtNum.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		GridData gd_wgtNum = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_wgtNum.heightHint = 40;
		wgtNum.setLayoutData(gd_wgtNum);
		wgtNum.setFont(defaultFont);
		wgtNum.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		wgtNum.setTitle("계량 번호: ");
		wgtNum.setReadonly();
		
		fstDateTime = new DatetimeComposite(compCenter, SWT.NONE);
		GridData gd_fstDateTime = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_fstDateTime.heightHint = 40;
		fstDateTime.setLayoutData(gd_fstDateTime);
		fstDateTime.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		fstDateTime.setFont(defaultFont);
		fstDateTime.setTitle("계량 일시: ");
		new Label(fstDateTime, SWT.NONE);
		if(!user.hasAuth(DTSConstants.AUTH_EDT_FST_DT))
			fstDateTime.setReadonly();
		fstDateTime.addFocusListener(new HelpAdapter("1차 계량일자 및 시간 입력 후 Enter."));
		
		fstWeigh = new WeighInputComposite(compCenter, SWT.NONE);
		fstWeigh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		fstWeigh.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		GridData gd_fstWeigh = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_fstWeigh.heightHint = 40;
		fstWeigh.setLayoutData(gd_fstWeigh);
		fstWeigh.setFont(defaultFont, labelFont);
		fstWeigh.setTitle("일차 중량: ");
		if(!user.hasAuth(DTSConstants.AUTH_EDT_FST_WGH))
			fstWeigh.setReadonly();
		fstWeigh.addFocusListener(new HelpAdapter("1차 중량을 입력하거나 [중량 읽기]로 설정 후 Enter."));
		
		car = new CarComposite(compCenter, SWT.NONE);
		GridData gd_car = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_car.heightHint = 40;
		car.setLayoutData(gd_car);
		car.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		car.setFont(defaultFont);
		car.setTitle("차량 번호: ");
		car.addFocusListener(new HelpAdapter("차량번호를 입력하거나 목록에서 선택 후 Enter."));
		
		int colorCnt = 0;
		if(useCust){
			cst = new CstComposite(compCenter, SWT.NONE);
			GridData gd_cst = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_cst.heightHint = 40;
			cst.setLayoutData(gd_cst);
			cst.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			cst.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			cst.setFont(defaultFont);
			cst.setTitle("거 래  처: ");
			cst.addFocusListener(new HelpAdapter("거래처명을 입력하거나 목록에서 선택 후 Enter."));
			colorCnt++;
		}
		
		if(usePrdt){
			prdt = new PrdtComposite(compCenter, SWT.NONE);
			GridData gd_prdt = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_prdt.heightHint = 40;
			prdt.setLayoutData(gd_prdt);
			prdt.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			if (colorCnt % 2 == 0)
				prdt.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				prdt.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			prdt.setFont(defaultFont);
			prdt.setTitle("제     품: ");
			prdt.addFocusListener(new HelpAdapter("제품명을 입력하거나 목록에서 선택 후 Enter."));
			colorCnt++;
		}
		
		note = new SimpleInputComposite(compCenter, SWT.NONE);
		GridData gd_note = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_note.heightHint = 40;
		note.setLayoutData(gd_note);
		note.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		note.setFont(defaultFont);
		note.setTitle("비     고: ");
		note.addFocusListener(new HelpAdapter("비고 입력 후 Enter."));
		if (colorCnt % 2 == 0)
			note.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		else
			note.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		for(int i=0; i<2-colorCnt; i++){
			Label lbl = new Label(compCenter, SWT.NONE);
			GridData gd_lbl = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_lbl.heightHint = 40;
			lbl.setLayoutData(gd_lbl);
			if (colorCnt % 2 != 0)
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		Composite btn = new Composite(shell,SWT.NONE);
		btn.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_btn = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_btn.heightHint = 54;
		btn.setLayoutData(gd_btn);
		btn.setLayout(new GridLayout(3, false));
		
		Button btnSave = new Button(btn, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickOK();
			}
		});
		GridData gd_btnSave = new GridData(SWT.RIGHT, SWT.FILL, true, false);
		gd_btnSave.heightHint = 36;
		gd_btnSave.widthHint = 130;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setFont(btnFont);
		btnSave.setText("저  장");
		btnSave.setForeground(ColorRepository.getColor(ColorRepository.POPUP_BTN));

		Button btnSaveNPrint = new Button(btn, SWT.NONE);
		btnSaveNPrint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TsWgtInfDTO dto = onClickOK();
				TsWgtInfManager twim = new TsWgtInfManager();
    			ArrayList<TsWgtInfDTO> list = twim.selectTsWgtInf(dto);
				PrintUtil.print(list.get(0), preferences.getInt(DTSPreConstants.GN_PRINT_COUNT));
			}
		});
		GridData gd_btnSaveNPrint = new GridData(SWT.CENTER, SWT.FILL, true, false);
		gd_btnSaveNPrint.heightHint = 36;
		gd_btnSaveNPrint.widthHint = 140;
		btnSaveNPrint.setLayoutData(gd_btnSaveNPrint);
		btnSaveNPrint.setFont(btnFont);
		btnSaveNPrint.setText("저장/출력");
		btnSaveNPrint.setForeground(ColorRepository.getColor(ColorRepository.POPUP_BTN));

		Button btnCancel = new Button(btn, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.FILL, true, false);
		gd_btnCancel.widthHint = 130;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setFont(btnFont);
		btnCancel.setText("취  소");
		btnCancel.setForeground(ColorRepository.getColor(ColorRepository.POPUP_BTN));

		lblHelp = new CLabel(shell, SWT.LEFT);
		lblHelp.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblHelp = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_lblHelp.heightHint = 25;
		lblHelp.setLayoutData(gd_lblHelp);
		lblHelp.setText("도움말:");
		lblHelp.setFont(helpFont);
		lblHelp.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_HELP_BG));
		
		shell.addListener(SWT.Traverse, new Listener() {
            public void handleEvent(Event event) {
                if(event.detail == SWT.TRAVERSE_ESCAPE) {
                    event.doit = false;
                }
            }
        });
	}// createContents

	/**
	 * init
	 */
	private void init() {
		TsMstManager mgr = new TsMstManager();
		car.setItems(mgr.selectTsCarMst(null));
		
		if(useCust){
			cst.setItems(mgr.selectTsCstMst(null));
			cst.addSelectionListener(new ItemSelected(prdt));
		}
		if(usePrdt){
			prdt.setItems(mgr.selectTsPrdtMst(null));
		}
		
		car.addSelectionListener(new ItemSelected(car));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
		
		fstDateTime.setText(dfDate.format(cal.getTime()), dfTime.format(cal.getTime()));
		fstWeigh.refreshData();
		
		wgtNum.setText(ObjectUtil.nextWeighCd());
    }// initData
	
	/**
	 * OK Button click event
	 */
	private TsWgtInfDTO onClickOK(){
		TsWgtInfDTO dto = null;
		if(!validate())
			return dto;
		boolean rtn = MessageDialog.openConfirm(shell, "저장 확인", "1차 계량 정보를 저장하시겠습니까?");
		if(rtn){
			try {
				dto = saveData();
				result = new Object();
				
				// seq 증가 및 저장
				preferences.setValue(DTSPreConstants.DATA_WEIGH_CD, ObjectUtil.nextWeighSeq()+1);
				preferences.setValue(DTSPreConstants.DATA_WEIGH_CD_NEXT, ObjectUtil.nextWeighCd());
				preferences.save();
				shell.close();
            } catch (IOException e) {
            	e.printStackTrace();
            	MessageDialog.openError(shell, "데이터 저장 오류", "데이터 저장 중 오류가 발생하였습니다.\n\n"+e.getMessage());
            }
		}
		return dto;
	}
	
	/**
	 * Save data
	 * @return 
	 * @throws IOException
	 */
	private TsWgtInfDTO saveData() throws IOException{
		TsMstManager tmm = new TsMstManager();
		TsWgtInfManager twim = new TsWgtInfManager();
		
		// 1차 계량 정보
		TsWgtInfDTO wgtDto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());

		// 차량번호 : 필수값
		TsCarMstDTO carDto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		carDto.setCar_cd(car.getValue(CarComposite.KEY_ID));
		carDto.setCar_num(car.getValue(CarComposite.KEY_NAME));
		if(carDto.getCar_cd() == null || carDto.getCar_cd().length() == 0){
			// 존재여부 확인
			carDto.setMgt_yn(DTSConstants.FLAG_N);
			ArrayList<TsCarMstDTO> list = tmm.selectTsCarMst(carDto);
			if(list != null && list.size() > 0){
				// 동일명의 미관리 차량이 존재하면 해당 Key 사용
				wgtDto.setCar_cd(list.get(0).getCar_cd());
				carDto = null; // 기존 key 사용
			}else{
				// 채번 - 신규 생성
				carDto.setCar_cd(tmm.selectTsCarMstKey());
				wgtDto.setCar_cd(carDto.getCar_cd());
			}
		}else{
			wgtDto.setCar_cd(carDto.getCar_cd());
			carDto = null; // 기존 key 사용
		}
		

		// 거래처정보 : Option
		TsCstMstDTO cstDto = null;
		if(useCust){
			cstDto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
			cstDto.setCst_cd(cst.getValue(CstComposite.KEY_ID));
			cstDto.setCst_nm(cst.getValue(CstComposite.KEY_NAME));
			if(cstDto.getCst_nm() != null && cstDto.getCst_nm().length()>0){
				if(cstDto.getCst_cd() == null || cstDto.getCst_cd().length() == 0){
					// 존재여부 확인
					cstDto.setMgt_yn(DTSConstants.FLAG_N);
					ArrayList<TsCstMstDTO> list = tmm.selectTsCstMst(cstDto);
					if(list != null && list.size() > 0){
						// 동일명의 미관리 차량이 존재하면 해당 Key 사용
						wgtDto.setCst_cd(list.get(0).getCst_cd());
						cstDto = null; // 기존 key 사용
					}else{
						// 채번 - 신규 생성
						cstDto.setCst_cd(tmm.selectTsCstMstKey());
						wgtDto.setCst_cd(cstDto.getCst_cd());
					}
				}else{
					wgtDto.setCst_cd(cstDto.getCst_cd());
					cstDto = null; // 기존 key 사용
				}
			}else{
				cstDto = null; // 거래처 미 선택
			}
		}
		
		// 제품정보 : Option
		TsPrdtMstDTO prdtDto = null;
		if(usePrdt){
			prdtDto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
			prdtDto.setPrdt_cd(prdt.getValue(PrdtComposite.KEY_ID));
			prdtDto.setPrdt_nm(prdt.getValue(PrdtComposite.KEY_NAME));
			if(prdtDto.getPrdt_nm() != null && prdtDto.getPrdt_nm().length() > 0){
				if(prdtDto.getPrdt_cd() == null || prdtDto.getPrdt_cd().length() == 0){
					// 존재여부 확인
					prdtDto.setMgt_yn(DTSConstants.FLAG_N);
					ArrayList<TsPrdtMstDTO> list = tmm.selectTsPrdtMst(prdtDto);
					if(list != null && list.size() > 0){
						// 동일명의 미관리 차량이 존재하면 해당 Key 사용
						wgtDto.setPrdt_cd(list.get(0).getPrdt_cd());
						if(preferences.getBoolean(DTSPreConstants.DATA_PRICE_LOAD))
							wgtDto.setUnt_prc(list.get(0).getUnt_prc());
						prdtDto = null; // 기존 key 사용
					}else{
						// 채번 - 신규 생성
						prdtDto.setPrdt_cd(tmm.selectTsPrdtMstKey());
						wgtDto.setPrdt_cd(prdtDto.getPrdt_cd());
					}
				}else{
					if(preferences.getBoolean(DTSPreConstants.DATA_PRICE_LOAD))
						wgtDto.setUnt_prc(Integer.parseInt(prdt.getValue(PrdtComposite.KEY_UP)));
					wgtDto.setPrdt_cd(prdtDto.getPrdt_cd());
					prdtDto = null; // 기존 key 사용
				}
			}else{
				prdtDto = null; // 제품 미 선택
			}
		}

		wgtDto.setFst_wgh(Double.parseDouble(fstWeigh.getValue(null)));
		
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-ddHH:mm");
		try {
	        wgtDto.setFst_wgt_dt(sdf.parse(fstDateTime.getValue(DatetimeComposite.KEY_DATE)+fstDateTime.getValue(DatetimeComposite.KEY_TIME)));
        } catch (ParseException e) {
	        e.printStackTrace();
        }
		wgtDto.setNt(note.getValue(null));
		wgtDto.setWgt_cd(twim.selectTsWgtInfKey());
		wgtDto.setWgt_num(wgtNum.getValue(null));
		wgtDto.setWgt_flg_cd(DTSConstants.WGT_FLAG_GEN);
		wgtDto.setWgt_stat_cd(DTSConstants.WGT_STAT_FST);
		
		if(logger.isDebugEnabled()){
			logger.debug("1차계량 저장"+wgtDto);
		}
		
	    // 미관리 마스터 정보(차량,거래처,제품) 선 저장 처리
		tmm.insertTsMst(carDto, cstDto, prdtDto);
		// 계량정보 등록
		twim.insertTsWgtInf(wgtDto);
		return wgtDto;
    }

	/**
	 * validation check
	 * @return
	 */
	private boolean validate(){
		if("".equals(car.getValue(CarComposite.KEY_NAME))){
			MessageDialog.openWarning(shell,"차량 선택 확인","차량번호가 입력되지 않았습니다.");
			car.setFocus();
			return false;
		}
		return true;
	}
	
	private static class ItemSelected extends SelectionAdapter {
		public ItemSelected(Composite next) {
		}
		public void widgetSelected(SelectionEvent e) {
		}
	}
	
	private void setMessage(String msg){
		lblHelp.setText("도움말:"+msg);
	}
	
	class HelpAdapter extends FocusAdapter{

		String msg = "";
		HelpAdapter(String msg){
			this.msg = msg;
		}
		@Override
        public void focusGained(FocusEvent e) {
			setMessage(msg);
        }

		@Override
        public void focusLost(FocusEvent e) {
			setMessage("");
        }
		
	} // class HelpAdapter
}
