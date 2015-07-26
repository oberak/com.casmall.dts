package com.casmall.dts.ui.weigh.dialog;

import java.io.IOException;
import java.text.NumberFormat;
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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.common.StringUtil;
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
import com.casmall.dts.ui.weigh.compsite.InOutComposite;
import com.casmall.dts.ui.weigh.compsite.MinusWeighComposite;
import com.casmall.dts.ui.weigh.compsite.PrdtComposite;
import com.casmall.dts.ui.weigh.compsite.PriceComposite;
import com.casmall.dts.ui.weigh.compsite.SimpleInputComposite;
import com.casmall.dts.ui.weigh.compsite.WeighInputComposite;
import com.casmall.usr.domain.CmUsrInfDTO;
import com.casmall.usr.mgr.SessionManager;
import com.swtdesigner.SWTResourceManager;

public class WeighOneDialog extends Dialog {
	protected static Log logger = LogFactory.getLog(WeighOneDialog.class);
			
	protected final Font titleFont = SWTResourceManager.getFont("����ü", 23, SWT.BOLD);
	protected final Font defaultFont = SWTResourceManager.getFont("����ü", 17, SWT.BOLD);
	protected final Font helpFont = SWTResourceManager.getFont("����ü", 12, SWT.NONE);
	protected final Font btnFont = SWTResourceManager.getFont("����ü", 15, SWT.BOLD);
	protected final Font labelFont = SWTResourceManager.getFont("����ü", 14, SWT.NONE);

	protected Object result;
	protected Shell shell;

	private CarComposite car;
	private CstComposite cst;
	private PrdtComposite prdt;
	private DatetimeComposite fstDateTime;
	private SimpleInputComposite note;
	private WeighInputComposite fstWeigh;
	
	private InOutComposite inout;
	private DatetimeComposite scndDateTime;
	private WeighInputComposite scndWeigh;
	private SimpleInputComposite wgtNum;
	private SimpleInputComposite realWeigh;
	private PriceComposite price;
	
	private TsWgtInfDTO wgtDto;

	SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
	NumberFormat nf = NumberFormat.getInstance();

	private MinusWeighComposite minus;
	
	private ScopedPreferenceStore preferences;
	
	private CmUsrInfDTO user;
	
	/** ��� ���� */
	private boolean useMinus;
	private boolean useCust;
	private boolean usePrdt;
	private boolean usePrice;

	private CLabel lblHelp;

	private Composite compFst;

	private Composite compScnd;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WeighOneDialog(Shell parent, int style) {
		super(parent, style);
		setText("1ȸ �跮");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		user = SessionManager.getInstance().getUsr();
		
		useMinus = preferences.getBoolean(DTSPreConstants.DATA_MINUS_FLAG);
		useCust = preferences.getBoolean(DTSPreConstants.DATA_CUST_FLAG);
		usePrdt = preferences.getBoolean(DTSPreConstants.DATA_PRDT_FLAG);
		usePrice = preferences.getBoolean(DTSPreConstants.DATA_PRICE_FLAG);
		
		createContents();
		shell.open();
		shell.layout();
		
		init();
		car.setFocus();
		
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
		shell.setSize(1000, 550);
		shell.setText(getText());
		GridLayout gl_shell = new GridLayout(2, false);
		gl_shell.verticalSpacing = 0;
		gl_shell.marginWidth = 0;
		gl_shell.marginHeight = 0;
		gl_shell.horizontalSpacing = 0;
		shell.setLayout(gl_shell);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setBackgroundMode(SWT.TRANSPARENT);
		
		CLabel lblTitle = new CLabel(shell, SWT.CENTER);
		lblTitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblTitle = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lblTitle.heightHint = 44;
		lblTitle.setLayoutData(gd_lblTitle);
		lblTitle.setFont(titleFont);
		lblTitle.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_TITLE_BG));
		lblTitle.setText("1ȸ �跮");
		
		Composite compContents = new Composite(shell, SWT.NONE);
		compContents.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		compContents.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout gl_compContents = new GridLayout(2, false);
		gl_compContents.marginTop = 10;
		gl_compContents.marginRight = 10;
		gl_compContents.marginLeft = 10;
		gl_compContents.marginBottom = 10;
		gl_compContents.verticalSpacing = 0;
		gl_compContents.marginWidth = 0;
		gl_compContents.marginHeight = 0;
		compContents.setLayout(gl_compContents);
		
		// 1�� �跮 �� ----------------------------------------------
		Group grpFst = new Group(compContents, SWT.NONE);
		grpFst.setFont(btnFont);
		grpFst.setLayout(new GridLayout(1, false));
		grpFst.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		grpFst.setText("1\uCC28 \uACC4\uB7C9");
		
		compFst = new Composite(grpFst, SWT.BORDER);
		compFst.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_compFst = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_compFst.heightHint = 237;
		compFst.setLayoutData(gd_compFst);
		GridLayout gl_compFst = new GridLayout(1, false);
		gl_compFst.horizontalSpacing = 0;
		gl_compFst.verticalSpacing = 0;
		gl_compFst.marginWidth = 0;
		gl_compFst.marginHeight = 0;
		compFst.setLayout(gl_compFst);
		
		wgtNum = new SimpleInputComposite(compFst, SWT.NONE);
		GridData gd_wgtNum = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_wgtNum.heightHint = 40;
		wgtNum.setLayoutData(gd_wgtNum);
		wgtNum.setFont(defaultFont);
		wgtNum.setTitle("�跮 ��ȣ: ");
		wgtNum.setReadonly();
		wgtNum.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		wgtNum.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));

		fstDateTime = new DatetimeComposite(compFst, SWT.NONE);
		GridData gd_fstDateTime = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_fstDateTime.heightHint = 40;
		fstDateTime.setLayoutData(gd_fstDateTime);
		fstDateTime.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		fstDateTime.setFont(defaultFont);
		fstDateTime.setTitle("�跮 �Ͻ�: ");
		if(!user.hasAuth(DTSConstants.AUTH_EDT_FST_DT))
			fstDateTime.setReadonly();
		fstDateTime.addFocusListener(new HelpAdapter("1�� �跮���� �� �ð� �Է� �� Enter."));
		
		fstWeigh = new WeighInputComposite(compFst, SWT.NONE);
		GridData gd_fstWeigh = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_fstWeigh.heightHint = 40;
		fstWeigh.setLayoutData(gd_fstWeigh);
		fstWeigh.setFont(defaultFont, labelFont);
		fstWeigh.setTitle("1 �� �߷�: ");
		fstWeigh.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				calc();
			}
		});
		fstWeigh.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		fstWeigh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		fstWeigh.addFocusListener(new HelpAdapter("1�� �߷��� �Է��ϰų� [�߷� �б�]�� ���� �� Enter."));

		if(!user.hasAuth(DTSConstants.AUTH_EDT_FST_WGH))
			fstWeigh.setEnabled(false);
		
		car = new CarComposite(compFst, SWT.NONE);
		GridData gd_car = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_car.heightHint = 40;
		car.setLayoutData(gd_car);
		car.setFont(defaultFont);
		car.setTitle("���� ��ȣ: ");
		car.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		car.addFocusListener(new HelpAdapter("������ȣ�� �Է��ϰų� ��Ͽ��� ���� �� Enter."));
		
		int cntFst = 0;
		if(useCust){
			cst = new CstComposite(compFst, SWT.NONE);
			cst.setFont(defaultFont);
			cst.setTitle("�� ��  ó: ");
			GridData gd_cst = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_cst.heightHint = 40;
			cst.setLayoutData(gd_cst);
			cst.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			cst.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			cst.addFocusListener(new HelpAdapter("�ŷ�ó���� �Է��ϰų� ��Ͽ��� ���� �� Enter."));
			cntFst++;
		}
		
		if(usePrdt){
			prdt = new PrdtComposite(compFst, SWT.NONE);
			prdt.setFont(defaultFont);
			prdt.setTitle("��     ǰ: ");
			prdt.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String up = prdt.getValue(PrdtComposite.KEY_UP);
					if(!"".equals(up) && preferences.getBoolean(DTSPreConstants.DATA_PRICE_LOAD)){
						price.setValue(PriceComposite.KEY_UP, up);
					}
				}
			});
			GridData gd_prdt = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_prdt.heightHint = 40;
			prdt.setLayoutData(gd_prdt);
			prdt.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			if (cntFst % 2 == 0)
				prdt.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				prdt.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			prdt.addFocusListener(new HelpAdapter("��ǰ���� �Է��ϰų� ��Ͽ��� ���� �� Enter."));
			cntFst++;
		}
		for(int i=0; i<3-cntFst; i++){
			Label lbl = new Label(compFst, SWT.NONE);
			GridData gd_lbl = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_lbl.heightHint = 40;
			lbl.setLayoutData(gd_lbl);
			if (cntFst % 2 == 0)
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		
		// ----------------- 2�� �跮 �κ� ------------------------------------------
		Group grpScnd = new Group(compContents, SWT.NONE);
		grpScnd.setFont(btnFont);
		grpScnd.setLayout(new GridLayout(1, false));
		GridData gd_grpScnd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_grpScnd.horizontalIndent = 2;
		grpScnd.setLayoutData(gd_grpScnd);
		grpScnd.setText("2\uCC28 \uACC4\uB7C9");
		
		compScnd = new Composite(grpScnd, SWT.BORDER);
		compScnd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_compScnd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_compScnd.heightHint = 237;
		compScnd.setLayoutData(gd_compScnd);
		GridLayout gl_compScnd = new GridLayout(1, false);
		gl_compScnd.horizontalSpacing = 0;
		gl_compScnd.verticalSpacing = 0;
		gl_compScnd.marginWidth = 0;
		gl_compScnd.marginHeight = 0;
		compScnd.setLayout(gl_compScnd);
		
		// ����� ����
		inout = new InOutComposite(compScnd, SWT.NONE);
		inout.setFont(defaultFont, labelFont);
		inout.setTitle("���� ����: ");
		inout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inoutChanged();
			}
		});
		GridData gd_inout = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_inout.heightHint = 40;
		inout.setLayoutData(gd_inout);
		inout.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		inout.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		inout.addFocusListener(new HelpAdapter("����� ���� ����."));
		
		scndDateTime = new DatetimeComposite(compScnd, SWT.NONE);
		scndDateTime.setFont(defaultFont);
		scndDateTime.setTitle("�跮 �Ͻ�: ");
		if(!user.hasAuth(DTSConstants.AUTH_EDT_SCND_DT))
			scndDateTime.setReadonly();
		GridData gd_scndDateTime = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_scndDateTime.heightHint = 40;
		scndDateTime.setLayoutData(gd_scndDateTime);
		scndDateTime.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		scndDateTime.addFocusListener(new HelpAdapter("2�� �跮���� �� �ð� �Է� �� Enter."));
		
		scndWeigh = new WeighInputComposite(compScnd, SWT.NONE);
		scndWeigh.setFont(defaultFont, labelFont);
		scndWeigh.setTitle("2 �� �߷�: ");
		scndWeigh.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				calc();
			}
		});
		if(!user.hasAuth(DTSConstants.AUTH_EDT_SCND_WGH))
			scndWeigh.setReadonly();
		GridData gd_scndWeigh = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_scndWeigh.heightHint = 40;
		scndWeigh.setLayoutData(gd_scndWeigh);
		scndWeigh.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		scndWeigh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		scndWeigh.addFocusListener(new HelpAdapter("2�� �߷��� �Է��ϰų� [�߷� �б�]�� ���� �� Enter."));
		
		// ����
		int cntScnd = 0;
		if(useMinus){
			minus = new MinusWeighComposite(compScnd, SWT.NONE);
			minus.setFont(defaultFont, labelFont, defaultFont);
			minus.setTitle("��     ��: ");
			minus.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					calc();
				}
			});
			minus.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Button b = (Button)e.getSource();
					if(b.getSelection())
						calc();
				}
			});
			GridData gd_minus = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_minus.heightHint = 40;
			minus.setLayoutData(gd_minus);
			minus.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			minus.addFocusListener(new HelpAdapter("���� ���� �� ���� �� �Է� �� Enter."));
			cntScnd++;
		}
		
		// ���߷�
		realWeigh = new SimpleInputComposite(compScnd, SWT.RIGHT);
		realWeigh.setFont(defaultFont);
		realWeigh.setTitle("��  �� ��: ");
		realWeigh.setReadonly();
		GridData gd_realWeigh = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_realWeigh.heightHint = 40;
		realWeigh.setLayoutData(gd_realWeigh);
		realWeigh.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		if (cntScnd % 2 != 0)
			realWeigh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		else
			realWeigh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		realWeigh.addFocusListener(new HelpAdapter("���߷��� �ڵ� ���˴ϴ�."));
		cntScnd++;
		
		if(usePrice){
			price = new PriceComposite(compScnd, SWT.RIGHT);
			price.setFont(defaultFont, labelFont);
			price.setTitle("�ܰ�/�ݾ�: ");
			price.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					calc();
				}
			});
			GridData gd_price = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_price.heightHint = 40;
			price.setLayoutData(gd_price);
			price.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
			if (cntScnd % 2 != 0)
				price.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				price.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			price.addFocusListener(new HelpAdapter("�ܰ� �� Enter. �ݾ��� �ڵ����˴ϴ�."));
			cntScnd++;
		}
		
		note = new SimpleInputComposite(compScnd, SWT.NONE);
		note.setFont(defaultFont);
		note.setTitle("��     ��: ");
		GridData gd_note = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_note.heightHint = 40;
		note.setLayoutData(gd_note);
		note.setForeground(ColorRepository.getColor(ColorRepository.HOME_TITLE));
		if (cntScnd % 2 != 0)
			note.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		else
			note.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		note.addFocusListener(new HelpAdapter("��� �Է� �� Enter."));
		
		for(int i=0; i<3-cntScnd; i++){
			Label lbl = new Label(compScnd, SWT.NONE);
			GridData gd_lbl = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd_lbl.heightHint = 40;
			lbl.setLayoutData(gd_lbl);
			if (cntScnd % 2 == 0)
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			else
				lbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		}
		
		Composite compBtn = new Composite(shell, SWT.NONE);
		GridData gd_compBtn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1);
		compBtn.setLayoutData(gd_compBtn);
		int btnCnt = 2;
		GridLayout gl_compBtn = new GridLayout(btnCnt, false);
		gl_compBtn.marginRight = 5;
		compBtn.setLayout(gl_compBtn);
		
		Button btnSave = new Button(compBtn, SWT.NONE);
		GridData gd_btnSave = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnSave.heightHint = 40;
		gd_btnSave.widthHint = 200;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setText("�� ��");
		btnSave.setFont(btnFont);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickOK();
			}
		});
		
		Button btnCancel = new Button(compBtn, SWT.NONE);
		btnCancel.setFont(btnFont);
		GridData gd_btnCancel = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.heightHint = 40;
		gd_btnCancel.widthHint = 200;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("\uCDE8  \uC18C");
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		
		lblHelp = new CLabel(shell, SWT.LEFT);
		lblHelp.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_lblHelp = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lblHelp.verticalIndent = 5;
		gd_lblHelp.heightHint = 25;
		lblHelp.setLayoutData(gd_lblHelp);
		lblHelp.setText("����:");
		lblHelp.setFont(helpFont);
		lblHelp.setBackgroundImage(ImageRepository.getImage(ImageRepository.POPUP_HELP_BG));
		
		shell.addListener(SWT.Traverse, new Listener() {
            public void handleEvent(Event event) {
                if(event.detail == SWT.TRAVERSE_ESCAPE) {
                    event.doit = false;
                }
            }
        });
	}
	
	/**
	 * init
	 */
	private void init() {
		TsMstManager mgr = new TsMstManager();
		car.setItems(mgr.selectTsCarMst(null));
		
		if(useCust){
			cst.setItems(mgr.selectTsCstMst(null));
		}
		if(usePrdt){
			prdt.setItems(mgr.selectTsPrdtMst(null));
		}
		
		Calendar cal = Calendar.getInstance();
		scndDateTime.setText(dfDate.format(cal.getTime()), dfTime.format(cal.getTime()));
		fstDateTime.setText(dfDate.format(cal.getTime()), dfTime.format(cal.getTime()));
		if(useMinus && preferences.getBoolean(DTSPreConstants.DATA_MINUS_DEFAULT)){
			minus.setValue("0",DTSConstants.CD_MINUS_PCT);
		}
		wgtNum.setText(ObjectUtil.nextWeighCd());
		if(DTSConstants.CD_INOUT_IN.equals(inout.getValue(null))){
			// �԰��� ��� �԰��߷� ����
//			fstWeigh.setEnabled(false);
//			fstWeigh.refreshData();
		}else{
//			scndWeigh.setEnabled(false);
//			scndWeigh.refreshData();
		}
    }// initData
	
	/**
	 * �߷� ��� ó��
	 */
	public void calc(){
		// ���߷� ���
		double scnd = Double.parseDouble(scndWeigh.getValue(null));
		double fst = Double.parseDouble(fstWeigh.getValue(null));
		double dis = 0;
		int point = 0;

		// ���� ���
		if(useMinus){
			dis = Double.parseDouble(minus.getValue(MinusWeighComposite.KEY_VAL));
			point = preferences.getInt(DTSPreConstants.DATA_MINUS_POINT);
			String type = preferences.getString(DTSPreConstants.DATA_MINUS_TYPE);
			if(DTSConstants.CD_MINUS_PCT.equals(minus.getValue(MinusWeighComposite.KEY_CD))){
				if(dis >0 && dis <= 100){
					dis = Math.abs(scnd - fst) / 100.0 * dis;
					dis = dis*Math.pow(10, point);
					if(DTSPreConstants.DATA_MINUS_TYPE_ROUND.equals(type)){
						dis = Math.round(dis);
					}else if(DTSPreConstants.DATA_MINUS_TYPE_CEIL.equals(type)){
						dis = Math.ceil(dis);
					}else{
						dis = Math.floor(dis);
					}
					dis = dis / Math.pow(10, point);
				}
			}
		}
		realWeigh.setText(StringUtil.getNumber(String.valueOf(Math.abs(scnd - fst)-dis), point));
		
		if(usePrice){
			String type = preferences.getString(DTSPreConstants.DATA_AMOUNT_TYPE);
			point = preferences.getInt(DTSPreConstants.DATA_AMOUNT_POINT);

			long up = 0;
			if(!"".equals(price.getValue(PriceComposite.KEY_UP)))
				up = Long.parseLong(price.getValue(PriceComposite.KEY_UP).replaceAll(",", ""));
			
			double amt = up * Double.parseDouble(realWeigh.getValue(null).replaceAll(",",""));
			
			amt = amt / Math.pow(10, point);
			if(DTSPreConstants.DATA_MINUS_TYPE_ROUND.equals(type)){
				amt = Math.round(amt);
			}else if(DTSPreConstants.DATA_MINUS_TYPE_CEIL.equals(type)){
				amt = Math.ceil(amt);
			}else{
				amt = Math.floor(amt);
			}
			amt = amt*Math.pow(10, point);
			price.setValue(PriceComposite.KEY_AMT,String.valueOf((long)amt));
		}
	}
	
	/**
	 * OK Button click event
	 */
	private void onClickOK(){
		if(!validate())
			return;
		boolean rtn = MessageDialog.openConfirm(shell, "���� Ȯ��", "�跮 ������ �����Ͻðڽ��ϱ�?");
		if(rtn){
			try {
				saveData();
            } catch (Exception e) {
            	e.printStackTrace();
            	MessageDialog.openError(shell, "������ ���� ����", "������ ���� �� ������ �߻��Ͽ����ϴ�.\n\n"+e.getMessage());
            	return;
            }
		}else{
			return;
		}
		if(preferences.getBoolean(DTSPreConstants.GN_PRINT_CONFIRM)){
			print();
		}
		result = new Object();
		shell.close();
	}
	
	/**
	 * Save data
	 * @return 
	 * @throws IOException
	 */
	private void saveData() throws IOException{
		TsMstManager tmm = new TsMstManager();
		TsWgtInfManager twim = new TsWgtInfManager();
		
		wgtDto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());
		
		// ������ȣ : �ʼ���
		TsCarMstDTO carDto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		carDto.setCar_cd(car.getValue(CarComposite.KEY_ID));
		carDto.setCar_num(car.getValue(CarComposite.KEY_NAME));
		
		if(carDto.getCar_cd() == null || carDto.getCar_cd().length() == 0){
			// ���翩�� Ȯ��
			carDto.setMgt_yn(DTSConstants.FLAG_N);
			ArrayList<TsCarMstDTO> list = tmm.selectTsCarMst(carDto);
			if(list != null && list.size() > 0){
				// ���ϸ��� �̰��� ������ �����ϸ� �ش� Key ���
				wgtDto.setCar_cd(list.get(0).getCar_cd());
				carDto = null; // ���� key ���
			}else{
				// ä�� - �ű� ����
				carDto.setCar_cd(tmm.selectTsCarMstKey());
				wgtDto.setCar_cd(carDto.getCar_cd());
			}
		}else{
			wgtDto.setCar_cd(carDto.getCar_cd());
			carDto = null; // ���� key ���
		}
		
		// �ŷ�ó����
		TsCstMstDTO cstDto = null;
		if(useCust){
			cstDto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
			cstDto.setCst_cd(cst.getValue(CstComposite.KEY_ID));
			cstDto.setCst_nm(cst.getValue(CstComposite.KEY_NAME));
			if(cstDto.getCst_nm() != null && cstDto.getCst_nm().length()>0){
				if(cstDto.getCst_cd() == null || cstDto.getCst_cd().length() == 0){
					// ���翩�� Ȯ��
					cstDto.setMgt_yn(DTSConstants.FLAG_N);
					ArrayList<TsCstMstDTO> list = tmm.selectTsCstMst(cstDto);
					if(list != null && list.size() > 0){
						// ���ϸ��� �̰��� ������ �����ϸ� �ش� Key ���
						wgtDto.setCst_cd(list.get(0).getCst_cd());
						cstDto = null; // ���� key ���
					}else{
						// ä�� - �ű� ����
						cstDto.setCst_cd(tmm.selectTsCstMstKey());
						wgtDto.setCst_cd(cstDto.getCst_cd());
					}
				}else{
					wgtDto.setCst_cd(cstDto.getCst_cd());
					cstDto = null; // ���� key ���
				}
			}else{
				cstDto = null; // �ŷ�ó �� ����
			}
		}
		
		// ��ǰ����
		TsPrdtMstDTO prdtDto = null;
		if(usePrdt){
			prdtDto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
			prdtDto.setPrdt_cd(prdt.getValue(PrdtComposite.KEY_ID));
			prdtDto.setPrdt_nm(prdt.getValue(PrdtComposite.KEY_NAME));
			if(prdtDto.getPrdt_nm() != null && prdtDto.getPrdt_nm().length() > 0){
				if(prdtDto.getPrdt_cd() == null || prdtDto.getPrdt_cd().length() == 0){
					// ���翩�� Ȯ��
					prdtDto.setMgt_yn(DTSConstants.FLAG_N);
					ArrayList<TsPrdtMstDTO> list = tmm.selectTsPrdtMst(prdtDto);
					if(list != null && list.size() > 0){
						// ���ϸ��� �̰��� ������ �����ϸ� �ش� Key ���
						wgtDto.setPrdt_cd(list.get(0).getPrdt_cd());
						prdtDto = null; // ���� key ���
					}else{
						// ä�� - �ű� ����
						prdtDto.setPrdt_cd(tmm.selectTsPrdtMstKey());
						wgtDto.setPrdt_cd(prdtDto.getPrdt_cd());
					}
				}else{
					wgtDto.setPrdt_cd(prdtDto.getPrdt_cd());
					prdtDto = null; // ���� key ���
				}
			}else{
				prdtDto = null; // ��ǰ �� ����
			}
		}
		wgtDto.setFst_wgh(Double.parseDouble(fstWeigh.getValue(null)));
		
		// �ܰ�/�ݾ�
		if(usePrice){
			wgtDto.setUnt_prc(Integer.parseInt(price.getValue(PriceComposite.KEY_UP).replaceAll(",", "")));
			wgtDto.setAmt(Integer.parseInt(price.getValue(PriceComposite.KEY_AMT).replaceAll(",", "")));
		}
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-ddHH:mm");
		try {
	        wgtDto.setFst_wgt_dt(sdf.parse(fstDateTime.getValue(DatetimeComposite.KEY_DATE)+fstDateTime.getValue(DatetimeComposite.KEY_TIME)));
	        wgtDto.setScnd_wgt_dt(sdf.parse(scndDateTime.getValue(DatetimeComposite.KEY_DATE)+scndDateTime.getValue(DatetimeComposite.KEY_TIME)));
        } catch (ParseException e) {
	        e.printStackTrace();
        	throw new IOException(e);
        }
		wgtDto.setNt(note.getValue(null));
		wgtDto.setWgt_cd(twim.selectTsWgtInfKey());
		wgtDto.setWgt_num(wgtNum.getValue(null));
		wgtDto.setWgt_flg_cd(DTSConstants.WGT_FLAG_ONE);
		wgtDto.setWgt_stat_cd(DTSConstants.WGT_STAT_SCND);
		
		wgtDto.setScnd_wgh(Double.parseDouble(scndWeigh.getValue(null)));
		if(useMinus){
			wgtDto.setDscnt_bss_cd(minus.getValue(MinusWeighComposite.KEY_CD));
			wgtDto.setDscnt_val(Double.parseDouble(minus.getValue(MinusWeighComposite.KEY_VAL)));
		}
		wgtDto.setRl_wgh(Double.parseDouble(realWeigh.getValue(null).replaceAll(",", "")));
		wgtDto.setDscnt(Math.abs(wgtDto.getFst_wgh() - wgtDto.getScnd_wgh())- wgtDto.getRl_wgh());

		wgtDto.setIo_flg(inout.getValue(null));
		if(logger.isDebugEnabled()){
			logger.debug("2���跮 ����"+wgtDto);
		}

	    // �̰��� ������ ����(����,�ŷ�ó,��ǰ) �� ���� ó��
		tmm.insertTsMst(carDto, cstDto, prdtDto);
		// �跮���� ���
		twim.insertTsWgtInf(wgtDto);
    }

    /**
     * ���
     * 
     * @return ��� ����
     */
    protected boolean print(){
    	boolean prtRtn = MessageDialog.openConfirm(shell, "��ǥ ��� Ȯ��", "��ǥ�� ����Ͻðڽ��ϱ�?");
    	if(prtRtn){
    		try {
    			TsWgtInfManager twim = new TsWgtInfManager();
    			TsWgtInfDTO dto = new TsWgtInfDTO();
    			dto.setWgt_cd(wgtDto.getWgt_cd());
    			ArrayList<TsWgtInfDTO> list = twim.selectTsWgtInf(dto);
    			if(list.size() != 1){
    				throw new Exception("��ǥ��� �� ��ȸ ����");
    			}
				PrintUtil.print(list.get(0), preferences.getInt(DTSPreConstants.GN_PRINT_COUNT));
				return true;
            } catch (Exception e) {
            	e.printStackTrace();
            	MessageDialog.openError(shell, "��ǥ ��� ����", "��ǥ ��� �� ������ �߻��Ͽ����ϴ�.\n\n"+e.getMessage());
            }
    	}
   		return false;
    }

	/**
	 * validation check
	 * @return
	 */
	private boolean validate(){
		if("".equals(car.getValue(CarComposite.KEY_NAME))){
			MessageDialog.openWarning(shell,"���� ���� Ȯ��","������ȣ�� �Էµ��� �ʾҽ��ϴ�.");
			car.setFocus();
			return false;
		}
		if(useCust && preferences.getBoolean(DTSPreConstants.DATA_CUST_MUST) && "".equals(cst.getValue(CarComposite.KEY_NAME))){
			MessageDialog.openWarning(shell,"�ŷ�ó ���� Ȯ��","�ŷ�ó�� �Էµ��� �ʾҽ��ϴ�.");
			cst.setFocus();
			return false;
		}
		if(usePrdt && preferences.getBoolean(DTSPreConstants.DATA_PRDT_MUST) && "".equals(prdt.getValue(CarComposite.KEY_NAME))){
			MessageDialog.openWarning(shell,"��ǰ ���� Ȯ��","��ǰ�� �Էµ��� �ʾҽ��ϴ�.");
			prdt.setFocus();
			return false;
		}
		if(usePrice && preferences.getBoolean(DTSPreConstants.DATA_PRICE_MUST) && 
				("".equals(price.getValue(PriceComposite.KEY_UP)) || "0".equals(price.getValue(PriceComposite.KEY_UP)) ) ){
			MessageDialog.openWarning(shell,"�ܰ� �Է� Ȯ��","�ܰ��� �Էµ��� �ʾҽ��ϴ�.");
			price.setFocus();
			return false;
		}
		// �԰�/��� ���� 1/2�� �߷� Ȯ��
		if(DTSConstants.CD_INOUT_IN.equals(inout.getValue(null))){
			if( Double.parseDouble(fstWeigh.getValue(null)) < Double.parseDouble(scndWeigh.getValue(null)) ){
				MessageDialog.openWarning(shell,"�԰� �߷� Ȯ��","�԰� �� 2�� �߷��� 1�� �߷��� �ʰ��� �� �����ϴ�.");
				inout.setFocus();
				return false;
			}
		}else{
			if( Double.parseDouble(fstWeigh.getValue(null)) > Double.parseDouble(scndWeigh.getValue(null)) ){
				MessageDialog.openWarning(shell,"��� �߷� Ȯ��","��� �� 1�� �߷��� 2�� �߷��� �ʰ��� �� �����ϴ�.");
				inout.setFocus();
				return false;
			}
		}
		return true;
	}
	
	protected void inoutChanged() {
		if(DTSConstants.CD_INOUT_IN.equals(inout.getValue(null))){
			// �԰��� ��� 1���߷� ����
//			fstWeigh.setEnabled(false);
//			fstWeigh.refreshData();
//			scndWeigh.setEnabled(true);
		}else{
//			fstWeigh.setEnabled(true);
//			scndWeigh.setEnabled(false);
//			scndWeigh.refreshData();
		}
    }
	
	private void setMessage(String msg){
		lblHelp.setText("����:"+msg);
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
		
	}// class HelpAdapter
}
