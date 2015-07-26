package com.casmall.dts.ui.preferences;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.CryptoUtil;
import com.casmall.dts.biz.domain.TsPrtInfDTO;
import com.casmall.dts.biz.mgr.TsPrtManager;
import com.casmall.dts.common.DTSConstants;
import com.swtdesigner.FieldLayoutPreferencePage;
import com.swtdesigner.SWTResourceManager;

public class GeneralPrePage extends FieldLayoutPreferencePage implements IWorkbenchPreferencePage {
	BooleanFieldEditor autoLoginFlag;
	StringFieldEditor autoLoginID;
	StringFieldEditor comAdd;
	StringFieldEditor comName;
	StringFieldEditor comTel;
	StringFieldEditor comFax;
	PasswordFieldEditor autoLoginPW;

	Composite compAutoLoginPW;
	Composite copmAutoLogin;

	private ScopedPreferenceStore preferences;
	private Composite compPrtConfirm;
	private Composite compPrtCnt;
	private ComboFieldEditor prtForm;
	private Composite compPrtForm;
	private TsPrtManager pm;
	
	private Font defaultLabelFont = SWTResourceManager.getFont("굴림", 12, SWT.NORMAL);
	private ArrayList<TsPrtInfDTO> prtList;

	CommonManager cm = CommonManager.getInstance();
	/**
	 * Create the preference page.
	 */
	public GeneralPrePage() {
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		setPreferenceStore(preferences);
	}

	/**
	 * Create contents of the preference page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createPageContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout gl_container = new GridLayout(1, false);
		container.setLayout(gl_container);

		Group gGeneral = new Group(container, SWT.NONE);
		gGeneral.setFont(defaultLabelFont);
		gGeneral.setText("일 반");
		gGeneral.setLayout(new GridLayout(1, false));
		gGeneral.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		compPrtConfirm = new Composite(gGeneral, SWT.NONE);
		compPrtConfirm.setFont(defaultLabelFont);
		addField(new BooleanFieldEditor(DTSPreConstants.GN_PRINT_CONFIRM, "전표 출력 시 확인 창 사용", BooleanFieldEditor.DEFAULT,
		        compPrtConfirm));

		compPrtCnt = new Composite(gGeneral, SWT.NONE);
		compPrtCnt.setFont(defaultLabelFont);
		addField(new IntegerFieldEditor(DTSPreConstants.GN_PRINT_COUNT, "전표 출력 매수", compPrtCnt));
		
		compPrtForm = new Composite(gGeneral, SWT.NONE);
		compPrtForm.setFont(defaultLabelFont );
		compPrtForm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		prtForm =new ComboFieldEditor(DTSPreConstants.GN_PRINT_FORM, "출력 양식", getPrtType(), 
		        compPrtForm); 
		addField(prtForm);

		// Login ===========================================================
		Group gAutoLogin = new Group(container, SWT.NONE);
		gAutoLogin.setFont(defaultLabelFont);
		gAutoLogin.setText("Login");
		gAutoLogin.setLayout(new GridLayout(2, false));
		gAutoLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite copmAutoLoginID = new Composite(gAutoLogin, SWT.NONE);
		copmAutoLoginID.setFont(defaultLabelFont);
		copmAutoLoginID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		autoLoginID = new StringFieldEditor(DTSPreConstants.GN_AUTO_LOGIN_ID, "Login ID:", 20, copmAutoLoginID);
		autoLoginID.getTextControl(copmAutoLoginID).setTextLimit(30);
		addField(autoLoginID);

		compAutoLoginPW = new Composite(gAutoLogin, SWT.NONE);
		compAutoLoginPW.setFont(defaultLabelFont);
		compAutoLoginPW.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		autoLoginPW = new PasswordFieldEditor(DTSPreConstants.GN_AUTO_LOGIN_PW, "Password:", 20, compAutoLoginPW);
		addField(autoLoginPW);
		autoLoginPW.setEnabled(false, compAutoLoginPW);

		copmAutoLogin = new Composite(gAutoLogin, SWT.NONE);
		copmAutoLogin.setFont(defaultLabelFont);
		autoLoginFlag = new BooleanFieldEditor(DTSPreConstants.GN_AUTO_LOGIN_FLAG, "자동 로그인", BooleanFieldEditor.DEFAULT,
		        copmAutoLogin);
		copmAutoLogin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addField(autoLoginFlag);
		autoLoginFlag.setEnabled(false, copmAutoLogin);

		// Home ===========================================================
		Group gHome = new Group(container, SWT.NONE);
		gHome.setFont(defaultLabelFont);
		gHome.setText("Home");
		gHome.setLayout(new GridLayout(1, false));
		gHome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compUseSummary = new Composite(gHome, SWT.NONE);
		compUseSummary.setFont(defaultLabelFont);
		addField(new BooleanFieldEditor(DTSPreConstants.GN_HOME_SUMMARY, "메인(Home) 화면에 계량 요약정보 표시", BooleanFieldEditor.DEFAULT,
				compUseSummary));

		Composite compUseScnd = new Composite(gHome, SWT.NONE);
		compUseScnd.setFont(defaultLabelFont);
		addField(new BooleanFieldEditor(DTSPreConstants.GN_HOME_SCND, "메인(Home) 화면에 2차 계량 목록 표시", BooleanFieldEditor.DEFAULT,
				compUseScnd));

		// comInfo ===========================================================
		Group gComInfo = new Group(container, SWT.NONE);
		gComInfo.setFont(defaultLabelFont);
		gComInfo.setText("Compapny");
		gComInfo.setLayout(new GridLayout(1, false));
		gComInfo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite copmComName = new Composite(gComInfo, SWT.NONE);
		copmComName.setFont(defaultLabelFont);
		copmComName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		comName = new StringFieldEditor(DTSPreConstants.GN_EXT_COM_NAME, "회사명:", 20, copmComName);
		comName.getTextControl(copmComName).setTextLimit(50);
		addField(comName);		
		
		Composite copmComAdd = new Composite(gComInfo, SWT.NONE);
		copmComAdd.setFont(defaultLabelFont);
		copmComAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		comAdd = new StringFieldEditor(DTSPreConstants.GN_EXT_COM_NAME, "회사주소:", 20, copmComAdd);
		comAdd.getTextControl(copmComAdd).setTextLimit(100);
		addField(comAdd);	
		
		Composite copmComTel = new Composite(gComInfo, SWT.NONE);
		copmComTel.setFont(defaultLabelFont);
		copmComTel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		comTel = new StringFieldEditor(DTSPreConstants.GN_EXT_COM_NAME, "회사 Tel:", 20, copmComTel);
		comTel.getTextControl(copmComTel).setTextLimit(30);
		addField(comTel);	
		
		Composite copmComFax = new Composite(gComInfo, SWT.NONE);
		copmComFax.setFont(defaultLabelFont);
		copmComFax.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		comFax = new StringFieldEditor(DTSPreConstants.GN_EXT_COM_NAME, "회사 Fax:", 20, copmComFax);
		comFax.getTextControl(copmComFax).setTextLimit(30);
		addField(comFax);	
		return container;
	}

	private String[][] getPrtType() {
		pm = new TsPrtManager();
		
		TsPrtInfDTO dto = new TsPrtInfDTO();
		prtList = pm.selectTsPrtInf(dto );
		String[][] str = new String[prtList.size()][2];
		for(int i=0;i< prtList.size();i++){
			str[i] = new String[2];
			str[i][0] = prtList.get(i).getPrt_nm();
			str[i][1] = ""+prtList.get(i).getPrt_seq();
		}
	    return str;
    }

	@Override
	protected void initialize() {
		super.initialize();
		
		ArrayList<CmCdDTO> list = cm.selectCmCd(null);
		
		for (CmCdDTO dto : list) {
			try {
				if ("CUST_INF".equals(dto.getCd_id())) {
					if ("cust_nm".equals(dto.getCd_val())) {
						comName.setStringValue(CryptoUtil.decrypt3DES(dto.getCd_nm()));
					} else if ("cust_add".equals(dto.getCd_val())) {
						comAdd.setStringValue(CryptoUtil.decrypt3DES(dto.getCd_nm()));
					} else if ("cust_tel".equals(dto.getCd_val())) {
						comTel.setStringValue(CryptoUtil.decrypt3DES(dto.getCd_nm()));
					} else if ("cust_fax".equals(dto.getCd_val())) {
						comFax.setStringValue(CryptoUtil.decrypt3DES(dto.getCd_nm()));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (preferences.getString(DTSPreConstants.GN_AUTO_LOGIN_ID).length() > 0) {
			autoLoginPW.setEnabled(true, compAutoLoginPW);
			if (preferences.getString(DTSPreConstants.GN_AUTO_LOGIN_PW).length() > 0) {
				autoLoginFlag.setEnabled(true, copmAutoLogin);
			} else {
				autoLoginFlag.setEnabled(false, copmAutoLogin);
			}
		} else {
			autoLoginPW.setEnabled(false, compAutoLoginPW);
		}

		if (autoLoginPW.getStringValue() != null && !"".equals(autoLoginPW.getStringValue())){
			try {
				autoLoginPW.setStringValue(CryptoUtil.decrypt3DES(autoLoginPW.getStringValue()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void performApply() {
		saveValue();
		super.performApply();
	}
	
	private boolean saveValue(){
		// data 저장
		ArrayList<CmCdDTO> list = new ArrayList<CmCdDTO>();

		try {
			CmCdDTO dto = new CmCdDTO();
			dto.setCd_id("CUST_INF");
			dto.setCd_val("cust_nm");
            dto.setCd_nm(CryptoUtil.encrypt3DES(comName.getStringValue().trim()));
			list.add(dto);

			dto = new CmCdDTO();
			dto.setCd_id("CUST_INF");
			dto.setCd_val("cust_add");
			dto.setCd_nm(CryptoUtil.encrypt3DES(comAdd.getStringValue().trim()));
			list.add(dto);

			dto = new CmCdDTO();
			dto.setCd_id("CUST_INF");
			dto.setCd_val("cust_tel");
			dto.setCd_nm(CryptoUtil.encrypt3DES(comTel.getStringValue().trim()));
			list.add(dto);

			dto = new CmCdDTO();
			dto.setCd_id("CUST_INF");
			dto.setCd_val("cust_fax");
			dto.setCd_nm(CryptoUtil.encrypt3DES(comFax.getStringValue().trim()));
			list.add(dto);
		} catch (Exception e1) {
            e1.printStackTrace();
        }

		try {
			for (CmCdDTO cc : list) {
				cm.updateCmCd(cc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(this.getShell(), "저장오류", "데이터 저장 중 오류가 발생하였습니다.\n\n" + e.getMessage());
			return false;
		}
		
		try {
			if (autoLoginPW.getStringValue() != null && !"".equals(autoLoginPW.getStringValue()))
				autoLoginPW.setStringValue(CryptoUtil.encrypt3DES(autoLoginPW.getStringValue()));
			preferences.save();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean performOk() {
		saveValue();
		return super.performOk();
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource().equals(autoLoginID)) {
			if (autoLoginID.getStringValue().length() > 0) {
				setAutoLoginEnable();
				autoLoginPW.setEnabled(true, compAutoLoginPW);
			} else {
				autoLoginPW.setStringValue("");
				autoLoginPW.setEnabled(false, compAutoLoginPW);
			}
		} else if (event.getSource().equals(autoLoginPW)) {
			setAutoLoginEnable();
		}
		super.propertyChange(event);
	}

	private void setAutoLoginEnable() {
		if (autoLoginPW.getStringValue().length() > 0) {
			autoLoginFlag.setEnabled(true, copmAutoLogin);
		} else {
			autoLoginFlag.setEnabled(false, copmAutoLogin);
		}
	}
}
