package com.casmall.dts.ui.usr;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.StringUtil;
import com.casmall.common.ui.composite.CustomCCombo;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.common.ObjectUtil;
import com.casmall.dts.ui.common.CallbackIF;
import com.casmall.dts.ui.common.RoundImageComposite;
import com.casmall.usr.domain.CmUsrInfDTO;
import com.casmall.usr.mgr.CmUsrInfMgr;
import com.casmall.usr.mgr.SessionManager;
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
public class UsrInfEditComposite extends Composite implements CallbackIF{
	protected static Log log = LogFactory.getLog(UsrInfEditComposite.class);
	private Text txtLgnId;
	private Composite cptButton;
	private RoundImageComposite round;
	private Composite cptHeader;
	private Button btnClear;
	private Button btnDelete;
	private Button btnSave;
	private CallbackIF callback;

	private Font defaultLabelFont = SWTResourceManager.getFont("굴림체", 14, SWT.BOLD);
	private Font defaultInputFont = SWTResourceManager.getFont("Arial", 14, SWT.NORMAL);
	private Font defaultInputFont2 = SWTResourceManager.getFont("Arial", 12, SWT.NORMAL);

	private CmUsrInfMgr mgr = new CmUsrInfMgr();
	private Text txtLgnPw;
	private Text txtNt;
	private CmUsrInfDTO dto;
	private Text txtUsrNm;
	private Text txtTel;
	private Text txtLgnPwCfm;
	private CustomCCombo cboAthGrd;
	private Button[] btnAuth = new Button[11];
	private String[] authText = {"수정 - 1차 계량일시", "수정 - 1차 계량중량", "수정 - 2차 계량일시","수정 - 2차 계량중량", "수정 - 차량",
			"수정 - 감량", "수정 - 단가/금액", "수정 - 2차 계량정보", "등록 - 1회 계량", "삭제 - 1차 계량정보", 
			"삭제 - 2차 계량정보"};
	private CmUsrInfDTO usr;
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
		UsrInfEditComposite inst = new UsrInfEditComposite(shell, SWT.NULL);

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

	public UsrInfEditComposite(org.eclipse.swt.widgets.Composite parent,
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
				cptBodyLayout.numColumns = 2;
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
					lblTag.setText("사용자ID: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtLgnId = new Text(round.getControl(), SWT.BORDER);
					txtLgnId.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if(e.keyCode == 0x0D){
								txtLgnPw.setFocus();
							}
						}
						@Override
						public void keyPressed(KeyEvent e) {
							if( StringUtil.isHangle(e.character) ){
								e.doit = false;
							}
						}
					});
					txtLgnId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtLgnId.setFont(defaultInputFont);
					txtLgnId.setTextLimit(20);
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("비밀번호: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtLgnPw = new Text(round.getControl(), SWT.BORDER | SWT.PASSWORD);
					txtLgnPw.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtLgnPw.setFont(defaultInputFont);
					txtLgnPw.setTextLimit(20);
					txtLgnPw.addKeyListener(new KeyAdapter() {
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
					lblTag.setText("비번확인: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtLgnPwCfm = new Text(round.getControl(), SWT.BORDER | SWT.PASSWORD);
					txtLgnPwCfm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtLgnPwCfm.setFont(defaultInputFont);
					txtLgnPwCfm.setTextLimit(20);
					txtLgnPwCfm.addKeyListener(new KeyAdapter() {
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
					lblTag.setText("사용자명: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtUsrNm = new Text(round.getControl(), SWT.BORDER);
					txtUsrNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtUsrNm.setFont(defaultInputFont);
					txtUsrNm.setTextLimit(15);
					txtUsrNm.addKeyListener(new KeyAdapter() {
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
					txtTel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
					lblTag.setText("권한등급: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					cboAthGrd = new CustomCCombo(round.getControl(), SWT.BORDER);
					cboAthGrd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					cboAthGrd.setFont(defaultInputFont);
					cboAthGrd.setEditable(false);
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("개별권한: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					Composite compBtn = new Composite(round.getControl(), SWT.BORDER);
					compBtn.setLayout(new GridLayout(1, true));
					compBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					
					for(int i=0;i<btnAuth.length;i++){
						btnAuth[i] = new Button(compBtn, SWT.CHECK);
						btnAuth[i].setText(authText [i]);
						btnAuth[i].setFont(defaultInputFont2);
						btnAuth[i].setData(1<<(i+1));
					}
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
					txtNt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
		CommonManager cm = CommonManager.getInstance();
		usr = SessionManager.getInstance().getUsr();
		CmCdDTO pDto = new CmCdDTO();
		pDto.setCd_id(DTSConstants.CD_USR_GRD);
		pDto.setCd_val(usr.getAth_grd());
		ArrayList<CmCdDTO> list = cm.selectCmCd(pDto);
		for(CmCdDTO cd:list){
			cboAthGrd.add(cd.getCd_val(), cd.getCd_nm());
		}
		for(int i=0;i<btnAuth.length;i++){
			if(DTSConstants.CD_USR_GRD_USER.equals(usr.getAth_grd())){
				btnAuth[i].setEnabled(false);
			}
		}
	}

	private void btnSaveMouseDown(SelectionEvent evt) {
		if(DTSConstants.CD_USR_GRD_USER.equals(usr.getAth_grd()) && dto.getUsr_seq() != usr.getUsr_seq() ){
			MessageDialog.openError(getShell(), "권한 부족 오류", "처리 권한이 없습니다. \n\n관리자로 로그인 후 처리 하세요.");
			return;
		}
		if(!validate())
			return;
		
		if(MessageDialog.openConfirm(getShell(), "저장확인", "저장하시겠습니까?")){
			try{
				// 데이터 저장
				CmUsrInfDTO pdto = (CmUsrInfDTO)ObjectUtil.getDefaultObject(CmUsrInfDTO.class.getName());
				pdto.setLgn_id(txtLgnId.getText().trim());
				pdto.setLgn_pw(txtLgnPw.getText().trim());
				pdto.setUsr_nm(txtUsrNm.getText().trim());
				pdto.setAth_grd(cboAthGrd.getText().trim());
				int auth = 0;
				for(int i=0;i<btnAuth.length;i++){
					if(btnAuth[i].getSelection())
						auth |= (Integer)btnAuth[i].getData();
				}
				pdto.setAth_cd(auth);
				pdto.setNt(txtNt.getText().trim());
				pdto.setTel(txtTel.getText().trim());
				
				// 신규일 경우
				if(dto == null){
					pdto.setUsr_seq(Integer.parseInt(mgr.selectCmUsrInfKey()));
					mgr.insertCmUsrInf(pdto);
				}else{
				// 수정일 경우
					pdto.setUsr_seq(dto.getUsr_seq());
					pdto.setRgn_dt(null);
					pdto.setRgn_id(null);
					mgr.updateCmUsrInf(pdto);
				}
				if(pdto.getLgn_id().equals(SessionManager.getInstance().getUsr().getLgn_id())){
					SessionManager.getInstance().getUsr().setAth_cd(pdto.getAth_cd());
					SessionManager.getInstance().getUsr().setAth_grd(pdto.getAth_grd());
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
		if("".equals(txtLgnId.getText().trim())){
			MessageDialog.openWarning(getShell(), "사용자ID 입력 확인", "사용자ID를 입력해 주세요.");
			txtLgnId.setFocus();
			return false;
		}
		if(dto==null){
			// 신규등록
			if("".equals(txtLgnPw.getText().trim())){
				MessageDialog.openWarning(getShell(), "비밀번호 입력 확인", "비밀번호를 입력해 주세요.");
				txtLgnPw.setFocus();
				return false;
			}
		}
		if(!"".equals(txtLgnPw.getText().trim()) && "".equals(txtLgnPwCfm.getText().trim())){
			MessageDialog.openWarning(getShell(), "비밀번호 확인 입력 확인", "비밀번호 확인 값을 입력해 주세요.");
			txtLgnPwCfm.setFocus();
			return false;
		}
		if("".equals(txtLgnPw.getText().trim()) && !txtLgnPw.getText().trim().equals(txtLgnPwCfm.getText().trim())){
			MessageDialog.openWarning(getShell(), "비밀번호 확인 입력 확인", "비밀번호와 비빌번호 확인 값이 일치하지 않습니다.\n\n다시 입력해 주세요.");
			txtLgnPwCfm.setText("");
			txtLgnPw.setFocus();
			txtLgnPw.selectAll();
			return false;
		}
		if("".equals(txtUsrNm.getText().trim())){
			MessageDialog.openWarning(getShell(), "사용자명 입력 확인", "사용자명을 입력해 주세요.");
			txtUsrNm.setFocus();
			return false;
		}
		if("".equals(cboAthGrd.getText().trim())){
			MessageDialog.openWarning(getShell(), "사용자 등급 입력 확인", "사용자등급을 선택해 주세요.");
			cboAthGrd.setFocus();
			return false;
		}
		
		CmUsrInfDTO p = new CmUsrInfDTO();
		if(dto != null)
		p.setUsr_seq(dto.getUsr_seq());
		// 관리번호 중복 확인
		p.setLgn_id(txtLgnId.getText().trim());
		if(mgr.existCmUsrInfLgnId(p)){
			MessageDialog.openWarning(getShell(), "사용자ID 중복 확인", "이미 사용된 ID입니다. 수정 후 처리바랍니다.");
			txtLgnId.setFocus();
			return false;
		}
		
	    return true;
    }

	private void btnDeleteMouseDown(MouseEvent evt) {
		try{
			if(DTSConstants.CD_USR_GRD_USER.equals(usr.getAth_grd())){
				MessageDialog.openError(getShell(), "권한 부족 오류", "처리 권한이 없습니다. \n\n관리자로 로그인 후 처리 하세요.");
				return;
			}
			if(dto == null){
				this.btnClearMouseDown(null);
				return;
			}
			if(MessageDialog.openConfirm(getShell(), "삭제확인", "데이터를 삭제하시겠습니까?")){
				dto = (CmUsrInfDTO)ObjectUtil.getUpdateObject(dto);
				mgr.deleteCmUsrInf(dto);
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
		this.txtLgnId.setText("");
		this.txtLgnId.setEditable(true);
		this.txtLgnPw.setText("");
		this.txtLgnPwCfm.setText("");
		this.txtNt.setText("");
		this.txtUsrNm.setText("");
		this.txtTel.setText("");
		for(int i=0;i<btnAuth.length;i++){
			btnAuth[i].setSelection(false);
		}
		this.cboAthGrd.setText("");
	}

	@Override
	public void callback(String cmd, Object obj) {
		btnClearMouseDown(null);
		dto = (CmUsrInfDTO)obj;
		select();
	}

	private void select() {
		ArrayList<CmUsrInfDTO> list = mgr.selectUsrInf(dto);
		if(list == null || list.size()==0){
			MessageDialog.openWarning(getShell(), "조회 오류", "조회된 데이터가 없습니다.");
			return;
		}
		dto = list.get(0);
		this.txtLgnId.setText(StringUtil.nullToBlank(dto.getLgn_id()));
		this.txtUsrNm.setText(StringUtil.nullToBlank(dto.getUsr_nm()));
		this.cboAthGrd.setText(StringUtil.nullToBlank(dto.getAth_grd()));
		for(int i=0;i<btnAuth.length;i++){
			if( ((Integer)btnAuth[i].getData() & dto.getAth_cd()) > 0)
				btnAuth[i].setSelection(true);
		}
		this.txtNt.setText(StringUtil.nullToBlank(dto.getNt()));
		this.txtTel.setText(StringUtil.nullToBlank(dto.getTel()));
		txtLgnId.setEditable(false);
		txtLgnPw.setFocus();
    }

	@Override
	public void addCallback(CallbackIF callback) {
		this.callback = callback;
	}
}