package com.casmall.dts.ui.master;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.mgr.TsMstManager;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.common.ObjectUtil;
import com.casmall.dts.ui.common.CallbackIF;
import com.casmall.dts.ui.common.RoundImageComposite;
import com.cloudgarden.resource.SWTResourceManager;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class CstEditComposite extends Composite implements CallbackIF{
	protected static Log log = LogFactory.getLog(CstEditComposite.class);
	private Text txtCstMgtCd;
	private Composite cptButton;
	private RoundImageComposite round;
	private Composite cptHeader;
	private Button btnClear;
	private Button btnDelete;
	private Button btnSave;
	private CallbackIF callback;

	private Font defaultLabelFont = com.swtdesigner.SWTResourceManager.getFont("굴림체", 14, SWT.BOLD);
	private Font defaultInputFont = com.swtdesigner.SWTResourceManager.getFont("Arial", 14, SWT.NORMAL);

	private TsMstManager mgr = new TsMstManager();
	private Text txtCstNm;
	private Button btnCheck;
	private Text txtNt;
	private TsCstMstDTO dto;
	private Text txtRprNm;
	private Text txtTel;
	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	/**
	 * Auto-generated main method to display this
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void main(String[] args) {
		showGUI();
	}

	/**
	 * Auto-generated method to display this org.eclipse.swt.widgets.Composite
	 * inside a new Shell.
	 */
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		CstEditComposite inst = new CstEditComposite(shell, SWT.NULL);

		Point size = inst.getSize();
		shell.layout();
		if (size.x == 0 && size.y == 0) {
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
	}

	public CstEditComposite(org.eclipse.swt.widgets.Composite parent,
			int style) {
		super(parent, style);
		setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
		initGUI();
		init();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();

			thisLayout.numColumns = 1;
			this.setLayout(thisLayout);
			this.setBackgroundMode(1);
			{
				cptHeader = new Composite(this, SWT.NONE);
				GridLayout cptHeaderLayout = new GridLayout();
				cptHeaderLayout.numColumns = 2;
				cptHeaderLayout.horizontalSpacing = 10;
				cptHeaderLayout.marginHeight = 0;
				cptHeaderLayout.marginBottom = 0;
				cptHeaderLayout.marginTop = 0;
				cptHeader.setLayout(cptHeaderLayout);

				// cptButtonLData.horizontalAlignment =
				cptHeader.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
				{
					final CLabel titleCLabel = new CLabel(cptHeader, SWT.NONE);
					GridData titleLData = new GridData();
					titleLData.verticalAlignment = GridData.BEGINNING;
					titleCLabel.setLayoutData(titleLData);
				}
				{
					cptButton = new Composite(cptHeader, SWT.NONE);
					GridLayout cptButtonLayout = new GridLayout();
					cptButtonLayout.marginHeight = 0;
					cptButtonLayout.marginBottom = 0;
					cptButtonLayout.marginTop = 0;
					cptButtonLayout.numColumns = 3;
					cptButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP,
							true, false));
					cptButton.setLayout(cptButtonLayout);
					{
						btnSave = new Button(cptButton, SWT.PUSH | SWT.CENTER);
						btnSave.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								btnSaveMouseDown(e);
							}
						});
						btnSave.setText("저장");
						btnSave.setImage(ImageRepository.getImage(ImageRepository.BTN_SAVE));
						btnSave.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false));
						btnSave.setFont(defaultLabelFont);
					}
					{
						btnDelete = new Button(cptButton, SWT.PUSH | SWT.CENTER);
						btnDelete.setText("삭제");
						btnDelete.setImage(ImageRepository.getImage(ImageRepository.BTN_DELETE));
						btnDelete.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false));
						btnDelete.setFont(defaultLabelFont);
						btnDelete.addMouseListener(new MouseAdapter() {
							public void mouseDown(MouseEvent evt) {
								btnDeleteMouseDown(evt);
							}
						});
					}
					{
						btnClear = new Button(cptButton, SWT.PUSH | SWT.CENTER);
						btnClear.setText("초기화");
						btnClear.setImage(ImageRepository.getImage(ImageRepository.BTN_CLEAR));
						btnClear.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false));
						btnClear.setFont(defaultLabelFont);
						btnClear.addMouseListener(new MouseAdapter() {
							public void mouseDown(MouseEvent evt) {
								btnClearMouseDown(evt);
							}
						});
					}
				}
			}
			{
				round = new RoundImageComposite(this, SWT.NONE);
				round.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				
				GridLayout cptBodyLayout = new GridLayout();
				cptBodyLayout.numColumns = 3;
//				cptBodyLayout.makeColumnsEqualWidth = true;
				GridData cptBodyLData = new GridData();
				cptBodyLData.horizontalIndent = 15;
				cptBodyLData.horizontalAlignment = GridData.FILL;
				cptBodyLData.grabExcessHorizontalSpace = true;
				cptBodyLData.grabExcessVerticalSpace = true;
				cptBodyLData.verticalAlignment = GridData.CENTER;
				round.getControl().setLayout(cptBodyLayout);
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("관리코드: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtCstMgtCd = new Text(round.getControl(), SWT.BORDER);
					txtCstMgtCd.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if("".equals(txtCstMgtCd.getText().trim()))
								btnCheck.setSelection(false);
							else
								btnCheck.setSelection(true);
							
							if(e.keyCode == 0x0D){
								txtCstNm.setFocus();
							}
						}
						@Override
						public void keyPressed(KeyEvent e) {
							if( StringUtil.isHangle(e.character) ){
								e.doit = false;
							}
						}
					});
					txtCstMgtCd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtCstMgtCd.setFont(defaultInputFont);
					txtCstMgtCd.setTextLimit(5);
				}
				{
					btnCheck = new Button(round.getControl(), SWT.CHECK);
					btnCheck.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if(!btnCheck.getSelection()){
								txtCstMgtCd.setText("");
							}else{
								if("".equals(txtCstMgtCd.getText().trim()))
									txtCstMgtCd.setFocus();
							}
						}
					});
					btnCheck.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.keyCode == 0x0D){
								((Button)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
							}
						}
					});
					btnCheck.setText("관리 데이터");
					btnCheck.setFont(defaultInputFont);
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("거래처명: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtCstNm = new Text(round.getControl(), SWT.BORDER);
					txtCstNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtCstNm.setFont(defaultInputFont);
					txtCstNm.setTextLimit(15);
					txtCstNm.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.keyCode == 0x0D){
								((Text)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
							}
						}
					});
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("대표자명: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtRprNm = new Text(round.getControl(), SWT.BORDER);
					txtRprNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtRprNm.setFont(defaultInputFont);
					txtRprNm.setTextLimit(15);
					txtRprNm.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.keyCode == 0x0D){
								((Text)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
							}
						}
					});
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("전화번호: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtTel = new Text(round.getControl(), SWT.BORDER);
					txtTel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtTel.setFont(defaultInputFont);
					txtTel.setTextLimit(14);
					txtTel.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.keyCode == 0x0D){
								((Text)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
							}
							if( StringUtil.isHangle(e.character) ){
								e.doit = false;
							}
						}
					});
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("비    고: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtNt = new Text(round.getControl(), SWT.BORDER);
					txtNt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtNt.setFont(defaultInputFont);
					txtNt.setTextLimit(25);
					txtNt.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.keyCode == 0x0D){
								btnSave.setFocus();
							}
						}
					});
				}
				new Label(round.getControl(), SWT.NONE);
				new Label(round.getControl(), SWT.NONE);
				new Label(round.getControl(), SWT.NONE);
			}

			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 초기화 함수
	 */
	private void init() {
		
	}

	private void btnSaveMouseDown(SelectionEvent evt) {
		if(!validate())
			return;
		
		if(MessageDialog.openConfirm(getShell(), "저장확인", "저장하시겠습니까?")){
			try{
				// 데이터 저장
				TsCstMstDTO pdto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
				pdto.setCst_nm(txtCstNm.getText().trim());
				pdto.setCst_mgt_cd(txtCstMgtCd.getText().trim());
				pdto.setNt(txtNt.getText().trim());
				pdto.setRpr_nm(txtRprNm.getText().trim());
				pdto.setTel(txtTel.getText().trim());
				
				if(btnCheck.getSelection())
					pdto.setMgt_yn(DTSConstants.FLAG_Y);
				else
					pdto.setMgt_yn(DTSConstants.FLAG_N);
				
				// 신규일 경우
				if(dto == null){
					pdto.setCst_cd(mgr.selectTsCstMstKey());
					mgr.insertTsCstMst(pdto);
				}else{
				// 수정일 경우
					pdto.setCst_cd(dto.getCst_cd());
					pdto.setRgn_dt(null);
					pdto.setRgn_id(null);
					mgr.updateTsCstMst(pdto);
				}
				
				callback.callback(CallbackIF.CMD_LIST, null);
				MessageDialog.openInformation(getShell(), "저장완료", "정상적으로 저장되었습니다.");
				btnClearMouseDown(null);
			}catch(Exception e){
				MessageDialog.openError(getShell(), "저장 Error", e.getMessage());
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}

	private boolean validate() {
		if("".equals(txtCstNm.getText().trim())){
			MessageDialog.openWarning(getShell(), "거래처명 입력 확인", "거래처명을 입력해 주세요.");
			txtCstNm.setFocus();
			return false;
		}
		
		if(btnCheck.getSelection() && "".equals(txtCstMgtCd.getText().trim())){
			MessageDialog.openWarning(getShell(), "관리번호 입력 확인", "관리번호가 입력되지 않았습니다.\n\n관리코드를 입력하거나, 관리 데이터를 체크해제 하시기 바랍니다.");
			txtCstMgtCd.setFocus();
			return false;
		}
		TsCstMstDTO p = new TsCstMstDTO();
		if(dto != null)
			p.setCst_cd(dto.getCst_cd());
		if(!"".equals(txtCstMgtCd.getText().trim())){
			// 관리번호 중복 확인
			p.setCst_mgt_cd(txtCstMgtCd.getText().trim());
			if(mgr.existTsCstMstMgtCd(p)){
				MessageDialog.openWarning(getShell(), "관리번호 중복 확인", "중복된 관리번호입니다. 수정 후 처리바랍니다.");
				txtCstMgtCd.setFocus();
				return false;
			}
		}
		// 거래처명 중복 확인 : 중복일 경우 confirm!
		p.setCst_nm(txtCstNm.getText().trim());
		ArrayList<TsCstMstDTO> list = mgr.selectTsCstMst(p);
		boolean e = false;
		if(dto == null){
			if(list.size()>0)
				e = true;
		}else{
			for(TsCstMstDTO d:list){
				if(!dto.getCst_cd().equals(d.getCst_cd())){
					e = true;
					break;
				}
			}
		}
		if(e){
			return MessageDialog.openConfirm(getShell(), "거래처명 중복 확인", "동일한 거래처명이 존재합니다.\n\n계속 진행하시겠습니까?");
		}
	    return true;
    }

	private void btnDeleteMouseDown(MouseEvent evt) {
		try{
			if(dto == null){
				this.btnClearMouseDown(null);
				return;
			}
			if(MessageDialog.openConfirm(getShell(), "삭제확인", "데이터를 삭제하시겠습니까?")){
				dto = (TsCstMstDTO)ObjectUtil.getUpdateObject(dto);
				mgr.deleteTsCstMst(dto);
				callback.callback(CallbackIF.CMD_LIST, null);
				btnClearMouseDown(null);
				MessageDialog.openInformation(getShell(), "삭제완료", "정상적으로 삭제되었습니다.");
			}
		}catch(Exception e){
			MessageDialog.openError(getShell(), "삭제 Error", e.getMessage());
			log.error(e.getMessage());
		}
	}

	private void btnClearMouseDown(MouseEvent evt) {
		dto = null;
		this.txtCstMgtCd.setText("");
		this.txtCstNm.setText("");
		this.txtNt.setText("");
		this.btnCheck.setSelection(false);
		this.txtRprNm.setText("");
		this.txtTel.setText("");
	}

	@Override
	public void callback(String cmd, Object obj) {
		btnClearMouseDown(null);
		dto = (TsCstMstDTO)obj;
		select();
	}

	private void select() {
		ArrayList<TsCstMstDTO> list = mgr.selectTsCstMst(dto);
		if(list == null || list.size()==0){
			MessageDialog.openWarning(getShell(), "조회 오류", "조회된 데이터가 없습니다.");
			return;
		}
		dto = list.get(0);
		this.txtCstMgtCd.setText(StringUtil.nullToBlank(dto.getCst_mgt_cd()));
		this.txtCstNm.setText(StringUtil.nullToBlank(dto.getCst_nm()));
		this.txtNt.setText(StringUtil.nullToBlank(dto.getNt()));
		this.txtRprNm.setText(StringUtil.nullToBlank(dto.getRpr_nm()));
		this.txtTel.setText(StringUtil.nullToBlank(dto.getTel()));
		if(DTSConstants.FLAG_Y.equals(dto.getMgt_yn()))
			this.btnCheck.setSelection(true);
		txtCstMgtCd.setFocus();
    }

	@Override
	public void addCallback(CallbackIF callback) {
		this.callback = callback;
	}
}