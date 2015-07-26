package com.casmall.dts.ui.preferences;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ObjectUtil;
import com.swtdesigner.FieldLayoutPreferencePage;
import com.swtdesigner.SWTResourceManager;

public class DataPrePage extends FieldLayoutPreferencePage implements IWorkbenchPreferencePage {
	private ScopedPreferenceStore preferences;
	private Font defaultLabelFont = SWTResourceManager.getFont("굴림", 12, SWT.NORMAL);

	private Composite compMinus;
	private BooleanFieldEditor minusFlag;
	private Composite compMinusPoint;
	private Composite compMinusType;
	private IntegerFieldEditor minusPoint;
	private RadioGroupFieldEditor minusType;
	private Composite compMinusDefult;
	private BooleanFieldEditor minusDefult;
	private Composite compDataWeighCd;
	private Composite compWeighCdType;
	private Composite compWeightCdLen;
	private Composite compWeighCdNext;
	private Composite compWeighCdWithHyphen;
	private StringFieldEditor weighCdNext;
	private ComboFieldEditor weighCdType;
	private IntegerFieldEditor weighCdLen;
	private IntegerFieldEditor weighCd;
	private BooleanFieldEditor weighCdWithHyphen;
	private Button btnResetSeq;
	private BooleanFieldEditor custFlag;
	private BooleanFieldEditor custMust;
	private Composite compCustMust;
	private BooleanFieldEditor prdtFlag;
	private Composite compPrdtMust;
	private BooleanFieldEditor prdtMust;
	private BooleanFieldEditor priceFlag;
	private Composite compAmtPoint;
	private IntegerFieldEditor amtPoint;
	private RadioGroupFieldEditor amtType;
	private Composite compAmtType;
	private BooleanFieldEditor priceMust;
	private Composite compPriceMust;
	private FieldEditor priceLoad;
	private Composite compPriceLoad;

	/**
	 * Create the preference page.
	 */
	public DataPrePage() {
		preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
		setPreferenceStore(preferences);
	}

	/**
	 * Create contents of the preference page.
	 * @param parent
	 */
	@Override
	public Control createPageContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout gl_container = new GridLayout(1, false);
		container.setLayout(gl_container);
		
		Group gGeneral = new Group(container, SWT.NONE);
		gGeneral.setFont(defaultLabelFont );
		gGeneral.setText("계량 번호");
		gGeneral.setLayout(new GridLayout(3, false));
		gGeneral.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		compWeighCdType = new Composite(gGeneral, SWT.NONE);
		compWeighCdType.setFont(defaultLabelFont );
		compWeighCdType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		weighCdType =new ComboFieldEditor(DTSPreConstants.DATA_WEIGH_CD_TYPE, "날짜 사용", new String[][] {
		        { "사용 안함", DTSPreConstants.DATA_WEIGH_CD_TYPE_NONE },
		        { "전체 년도 (예: 2011)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYYY }, 
		        { "짧은 년도 (예: 11)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YY }, 
		        { "전체년도-월, 하이픈 사용(예: 2011-01)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYYY_MM }, 
		        { "짧은년도-월, 하이픈 사용(예: 11-01)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YY_MM }, 
		        { "전체년도+월(예: 201101)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYYYMM }, 
		        { "짧은년도+월(예: 1101)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYMM }, 
		        { "전체년도-월-일, 하이픈 사용(예: 2011-01-01)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYYY_MM_DD }, 
		        { "짧은년도-월-일, 하이픈 사용(예: 11-01-01)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YY_MM_DD }, 
		        { "전체년도+월+일(예: 20110101)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYYYMMDD }, 
		        { "짧은년도+월+일(예: 110101)", DTSPreConstants.DATA_WEIGH_CD_TYPE_YYMMDD }}, 
		        compWeighCdType); 
		addField(weighCdType);

		compWeightCdLen = new Composite(gGeneral, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 150;
		compWeightCdLen.setLayoutData(gd_composite);
		compWeightCdLen.setFont(defaultLabelFont );
		weighCdLen = new IntegerFieldEditor(DTSPreConstants.DATA_WEIGH_CD_LENGTH, "일련번호 자리수", compWeightCdLen);
		weighCdLen.setValidRange(2, 10);
		addField(weighCdLen);
		
		compDataWeighCd = new Composite(gGeneral, SWT.NONE);
		compDataWeighCd.setFont(defaultLabelFont );
		GridData gd_compDataWeighCd = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_compDataWeighCd.widthHint = 150;
		compDataWeighCd.setLayoutData(gd_compDataWeighCd);
		weighCd = new IntegerFieldEditor(DTSPreConstants.DATA_WEIGH_CD, "다음 일련번호", compDataWeighCd);
		addField(weighCd);
		
		btnResetSeq = new Button(gGeneral, SWT.NONE);
		btnResetSeq.setFont(defaultLabelFont );
		btnResetSeq.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetWeighSeq();
			}
		});
		btnResetSeq.setText("재설정");
		
		compWeighCdWithHyphen = new Composite(gGeneral, SWT.NONE);
		compWeighCdWithHyphen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		compWeighCdWithHyphen.setFont(defaultLabelFont );
		weighCdWithHyphen = new BooleanFieldEditor(DTSPreConstants.DATA_WEIGH_CD_WITH_HYPHEN, "일련번호와 날짜사이에 하이픈(-) 사용", BooleanFieldEditor.DEFAULT, compWeighCdWithHyphen);
		addField(weighCdWithHyphen);
		
		compWeighCdNext = new Composite(gGeneral, SWT.NONE);
		compWeighCdNext.setEnabled(false);
		compWeighCdNext.setFont(defaultLabelFont );
		compWeighCdNext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		weighCdNext = new StringFieldEditor(DTSPreConstants.DATA_WEIGH_CD_NEXT, "다음 계량 번호", -1, StringFieldEditor.VALIDATE_ON_KEY_STROKE, compWeighCdNext);
		addField(weighCdNext);
		
		// 감량 ===========================================================
		Group gMinus = new Group(container, SWT.NONE);
		gMinus.setFont(defaultLabelFont );
		gMinus.setText("감 량");
		gMinus.setLayout(new GridLayout(2, false));
		gMinus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		compMinus = new Composite(gMinus, SWT.NONE);
		compMinus.setFont(defaultLabelFont );
		minusFlag = new BooleanFieldEditor(DTSPreConstants.DATA_MINUS_FLAG, "감량 사용", BooleanFieldEditor.DEFAULT, compMinus);
		compMinus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		addField(minusFlag);
		
		compMinusPoint = new Composite(gMinus, SWT.NONE);
		compMinusPoint.setFont(defaultLabelFont );
		GridData gd = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd.horizontalIndent = 15;
		compMinusPoint.setLayoutData(gd);
		minusPoint = new IntegerFieldEditor(DTSPreConstants.DATA_MINUS_POINT, "소수점", compMinusPoint);
		minusPoint.setTextLimit(2);
		addField(minusPoint);
		minusPoint.setEnabled(false, compMinusPoint);
		
		compMinusType = new Composite(gMinus, SWT.NONE);
		compMinusType.setFont(defaultLabelFont );
		compMinusType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		minusType = new RadioGroupFieldEditor(DTSPreConstants.DATA_MINUS_TYPE, "째 자리", 3, 
				new String[][]{{"반올림", DTSPreConstants.DATA_MINUS_TYPE_ROUND}, {"올림", DTSPreConstants.DATA_MINUS_TYPE_CEIL}, {"버림", DTSPreConstants.DATA_MINUS_TYPE_FLOOR}}, compMinusType, false);
		addField(minusType);
		minusType.setEnabled(false, compMinusType);
		
		compMinusDefult = new Composite(gMinus, SWT.NONE);
		compMinusDefult.setFont(defaultLabelFont );
		minusDefult = new BooleanFieldEditor(DTSPreConstants.DATA_MINUS_DEFAULT, "기본값을 비율(%)로 설정", BooleanFieldEditor.DEFAULT, compMinusDefult);
		addField(minusDefult);
		minusDefult.setEnabled(false, compMinusDefult);

		// 거래처 ===========================================================
		Group gCust = new Group(container, SWT.NONE);
		gCust.setFont(defaultLabelFont );
		gCust.setText("거래처");
		gCust.setLayout(new GridLayout(2, false));
		gCust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compUseCust = new Composite(gCust, SWT.NONE);
		compUseCust.setFont(defaultLabelFont );
		custFlag = new BooleanFieldEditor(DTSPreConstants.DATA_CUST_FLAG, "거래처 사용", BooleanFieldEditor.DEFAULT, compUseCust);
		compUseCust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(custFlag);
		
		compCustMust = new Composite(gCust, SWT.NONE);
		compCustMust.setFont(defaultLabelFont );
		custMust = new BooleanFieldEditor(DTSPreConstants.DATA_CUST_MUST, "거래처 필수 입력", BooleanFieldEditor.DEFAULT, compCustMust);
		compCustMust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(custMust);
		
		// 제품 ===========================================================
		Group gPrdt = new Group(container, SWT.NONE);
		gPrdt.setFont(defaultLabelFont );
		gPrdt.setText("제품");
		gPrdt.setLayout(new GridLayout(2, false));
		gPrdt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compUsePrdt = new Composite(gPrdt, SWT.NONE);
		compUsePrdt.setFont(defaultLabelFont );
		prdtFlag = new BooleanFieldEditor(DTSPreConstants.DATA_PRDT_FLAG, "제품 사용", BooleanFieldEditor.DEFAULT, compUsePrdt);
		compUsePrdt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(prdtFlag);
		
		compPrdtMust = new Composite(gPrdt, SWT.NONE);
		compPrdtMust.setFont(defaultLabelFont );
		prdtMust = new BooleanFieldEditor(DTSPreConstants.DATA_PRDT_MUST, "제품 필수 입력", BooleanFieldEditor.DEFAULT, compPrdtMust);
		compPrdtMust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(prdtMust);
		
		// 단가/금액  ===========================================================
		Group gPrice = new Group(container, SWT.NONE);
		gPrice.setFont(defaultLabelFont );
		gPrice.setText("단가/금액");
		gPrice.setLayout(new GridLayout(2, false));
		gPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compUsePrice = new Composite(gPrice, SWT.NONE);
		compUsePrice.setFont(defaultLabelFont );
		priceFlag = new BooleanFieldEditor(DTSPreConstants.DATA_PRICE_FLAG, "단가/금액 사용", BooleanFieldEditor.DEFAULT, compUsePrice);
		compUsePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addField(priceFlag);
		
		compPriceMust = new Composite(gPrice, SWT.NONE);
		compPriceMust.setFont(defaultLabelFont );
		priceMust = new BooleanFieldEditor(DTSPreConstants.DATA_PRICE_MUST, "단가 필수 입력", BooleanFieldEditor.DEFAULT, compPriceMust);
		compPriceMust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(priceMust);
		priceMust.setEnabled(false, compPriceMust);
		
		compAmtPoint = new Composite(gPrice, SWT.NONE);
		compAmtPoint.setFont(defaultLabelFont );
		GridData gdAmt = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
		gdAmt.horizontalIndent = 15;
		compAmtPoint.setLayoutData(gdAmt);
		amtPoint = new IntegerFieldEditor(DTSPreConstants.DATA_AMOUNT_POINT, "금액", compAmtPoint);
		amtPoint.setTextLimit(2);
		addField(amtPoint);
		amtPoint.setEnabled(false, compAmtPoint);
		
		compAmtType = new Composite(gPrice, SWT.NONE);
		compAmtType.setFont(defaultLabelFont );
		compAmtType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		amtType = new RadioGroupFieldEditor(DTSPreConstants.DATA_AMOUNT_TYPE, "째 자리", 3, 
				new String[][]{{"반올림", DTSPreConstants.DATA_MINUS_TYPE_ROUND}, {"올림", DTSPreConstants.DATA_MINUS_TYPE_CEIL}, {"버림", DTSPreConstants.DATA_MINUS_TYPE_FLOOR}}, compAmtType, false);
		addField(amtType);
		amtType.setEnabled(false, compAmtType);
		
		compPriceLoad = new Composite(gPrice, SWT.NONE);
		compPriceLoad.setFont(defaultLabelFont );
		priceLoad = new BooleanFieldEditor(DTSPreConstants.DATA_PRICE_LOAD, "제품 선택 시 단가 조회", BooleanFieldEditor.DEFAULT, compPriceLoad);
		compPriceLoad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addField(priceLoad);
		priceLoad.setEnabled(false, compPriceLoad);
		
		
		return container;
	}

	protected void resetWeighSeq() {
		boolean prtRtn = MessageDialog.openConfirm(this.getShell(), "일련번호 재설정", "실 데이터(DB) 기반으로 일련번호를 재설정 하시겠습니까?");
    	if(prtRtn){
    		Combo cbo = weighCdType.getComboBoxControl(compWeighCdNext);
    		String cdType = weighCdType.getValueForName(cbo.getItem(cbo.getSelectionIndex()));
    		if( DTSPreConstants.DATA_WEIGH_CD_TYPE_NONE.equals( cdType ) ){
    		}else{
    			TsWgtInfManager wim = new TsWgtInfManager();
    			int seq = 0;
    			if(cdType.contains("dd")){
    				seq = wim.selectTsWgtInfCount("DAY");
    			}else if(cdType.contains("MM")){
    				seq = wim.selectTsWgtInfCount("MONTH");
    			}else if(cdType.contains("yy")){
    				seq = wim.selectTsWgtInfCount("YEAR");
    			}
    			weighCd.setStringValue(String.valueOf(seq));
    		}
    	}
    }

	@Override
    protected void initialize() {
	    super.initialize();
		if(preferences.getBoolean(DTSPreConstants.DATA_MINUS_FLAG)){
			minusPoint.setEnabled(true, compMinusPoint);
			minusType.setEnabled(true, compMinusType);
			minusDefult.setEnabled(true, compMinusDefult);
		}
		if(preferences.getBoolean(DTSPreConstants.DATA_PRICE_FLAG)){
			amtPoint.setEnabled(true, compAmtPoint);
			amtType.setEnabled(true, compAmtType);
			priceMust.setEnabled(true, compPriceMust);
			priceLoad.setEnabled(true, compPriceLoad);
		}
		if(!preferences.getBoolean(DTSPreConstants.DATA_CUST_FLAG)){
			custMust.setEnabled(false, compCustMust);
		}
		if(!preferences.getBoolean(DTSPreConstants.DATA_PRDT_FLAG)){
			prdtMust.setEnabled(false, compPrdtMust);
		}
		weighCdNext.setStringValue(ObjectUtil.nextWeighCd(preferences.getString(DTSPreConstants.DATA_WEIGH_CD_TYPE),
        preferences.getInt(DTSPreConstants.DATA_WEIGH_CD_LENGTH), preferences.getInt(DTSPreConstants.DATA_WEIGH_CD), preferences.getBoolean(DTSPreConstants.DATA_WEIGH_CD_WITH_HYPHEN)));
    }

	@Override
	public boolean performOk() {
		try {
			preferences.save();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return super.performOk();
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
    public void propertyChange(PropertyChangeEvent event) {
		if(event.getSource().equals(minusFlag)){
			if(minusFlag.getBooleanValue()){
				minusPoint.setEnabled(true, compMinusPoint);
				minusType.setEnabled(true, compMinusType);
				minusDefult.setEnabled(true, compMinusDefult);
			}else{
				minusPoint.setEnabled(false, compMinusPoint);
				minusType.setEnabled(false, compMinusType);
				minusDefult.setEnabled(false, compMinusDefult);
			}
		}else if(event.getSource().equals(weighCdType) || event.getSource().equals(weighCdLen)
				|| event.getSource().equals(weighCd) || event.getSource().equals(weighCdWithHyphen)){
			Combo cbo = weighCdType.getComboBoxControl(compWeighCdNext);
			try{
				weighCdNext.setStringValue(ObjectUtil.nextWeighCd(weighCdType.getValueForName(cbo.getItem(cbo.getSelectionIndex())), 
						weighCdLen.getIntValue(), weighCd.getIntValue(), weighCdWithHyphen.getBooleanValue()));
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}else if(event.getSource().equals(custFlag)){
			if(custFlag.getBooleanValue()){
				custMust.setEnabled(true, compCustMust);
			}else{
				custMust.setEnabled(false, compCustMust);
			}
		}else if(event.getSource().equals(prdtFlag)){
			if(prdtFlag.getBooleanValue()){
				prdtMust.setEnabled(true, compPrdtMust);
			}else{
				prdtMust.setEnabled(false, compPrdtMust);
			}
		}else if(event.getSource().equals(priceFlag)){
			if(priceFlag.getBooleanValue()){
				amtPoint.setEnabled(true, compAmtPoint);
				amtType.setEnabled(true, compAmtType);
				priceMust.setEnabled(true, compPriceMust);
				priceLoad.setEnabled(true, compPriceLoad);
			}else{
				amtPoint.setEnabled(false, compAmtPoint);
				amtType.setEnabled(false, compAmtType);
				priceMust.setEnabled(false, compPriceMust);
				priceLoad.setEnabled(false, compPriceLoad);
			}
		}
	    super.propertyChange(event);
    }
}
