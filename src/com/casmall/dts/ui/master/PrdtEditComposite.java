package com.casmall.dts.ui.master;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;
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
import com.casmall.dts.biz.domain.TsPrdtMstDTO;
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
public class PrdtEditComposite extends Composite implements CallbackIF{
	protected static Log log = LogFactory.getLog(PrdtEditComposite.class);
	private Text txtPrdtMgtCd;
	private Composite cptButton;
	private RoundImageComposite round;
	private Composite cptHeader;
	private Button btnClear;
	private Button btnDelete;
	private Button btnSave;
	private CallbackIF callback;

	private Font defaultLabelFont = com.swtdesigner.SWTResourceManager.getFont("����ü", 14, SWT.BOLD);
	private Font defaultInputFont = com.swtdesigner.SWTResourceManager.getFont("Arial", 14, SWT.NORMAL);

	private TsMstManager mgr = new TsMstManager();
	private Text txtPrdtNm;
	private Button btnCheck;
	private Text txtNt;
	private TsPrdtMstDTO dto;
	private FormattedText txtUnitPrice;
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
		PrdtEditComposite inst = new PrdtEditComposite(shell, SWT.NULL);

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

	public PrdtEditComposite(org.eclipse.swt.widgets.Composite parent,
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
						btnSave.setText("����");
						btnSave.setImage(ImageRepository.getImage(ImageRepository.BTN_SAVE));
						btnSave.setLayoutData(new GridData(SWT.FILL,
								SWT.CENTER, true, false));
						btnSave.setFont(defaultLabelFont);
					}
					{
						btnDelete = new Button(cptButton, SWT.PUSH | SWT.CENTER);
						btnDelete.setText("����");
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
						btnClear.setText("�ʱ�ȭ");
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
					lblTag.setText("�����ڵ�: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtPrdtMgtCd = new Text(round.getControl(), SWT.BORDER);
					txtPrdtMgtCd.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if("".equals(txtPrdtMgtCd.getText().trim()))
								btnCheck.setSelection(false);
							else
								btnCheck.setSelection(true);
							
							if(e.keyCode == 0x0D){
								txtPrdtNm.setFocus();
							}
						}
						@Override
						public void keyPressed(KeyEvent e) {
							if( StringUtil.isHangle(e.character) ){
								e.doit = false;
							}
						}
					});
					txtPrdtMgtCd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					txtPrdtMgtCd.setFont(defaultInputFont);
					txtPrdtMgtCd.setTextLimit(5);
				}
				{
					btnCheck = new Button(round.getControl(), SWT.CHECK);
					btnCheck.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if(!btnCheck.getSelection()){
								txtPrdtMgtCd.setText("");
							}else{
								if("".equals(txtPrdtMgtCd.getText().trim()))
									txtPrdtMgtCd.setFocus();
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
					btnCheck.setText("���� ������");
					btnCheck.setFont(defaultInputFont);
				}
				{
					final CLabel lblTag = new CLabel(round.getControl(), SWT.RIGHT);
					lblTag.setText("�� ǰ ��: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtPrdtNm = new Text(round.getControl(), SWT.BORDER);
					txtPrdtNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtPrdtNm.setFont(defaultInputFont);
					txtPrdtNm.setTextLimit(15);
					txtPrdtNm.addKeyListener(new KeyAdapter() {
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
					lblTag.setText("��ǰ�ܰ�: ");
					lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
					lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
					lblTag.setFont(defaultLabelFont);
				}
				{
					txtUnitPrice = new FormattedText(round.getControl(), SWT.BORDER);
					txtUnitPrice.setFormatter(new NumberFormatter("###,###,###"));
					txtUnitPrice.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
					txtUnitPrice.getControl().setFont(defaultInputFont);
					txtUnitPrice.getControl().setTextLimit(15);
					txtUnitPrice.getControl().addKeyListener(new KeyAdapter() {
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
					lblTag.setText("��    ��: ");
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
	 * �ʱ�ȭ �Լ�
	 */
	private void init() {
		
	}

	private void btnSaveMouseDown(SelectionEvent evt) {
		if(!validate())
			return;
		
		if(MessageDialog.openConfirm(getShell(), "����Ȯ��", "�����Ͻðڽ��ϱ�?")){
			try{
				// ������ ����
				TsPrdtMstDTO pdto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
				pdto.setPrdt_nm(txtPrdtNm.getText().trim());
				pdto.setPrdt_mgt_cd(txtPrdtMgtCd.getText().trim());
				if("".equals(txtUnitPrice.getControl().getText())){
					pdto.setUnt_prc(0);
				}else{
					pdto.setUnt_prc(Integer.parseInt(txtUnitPrice.getControl().getText().replaceAll(",", "")));
				}
				pdto.setNt(txtNt.getText().trim());
				if(btnCheck.getSelection())
					pdto.setMgt_yn(DTSConstants.FLAG_Y);
				else
					pdto.setMgt_yn(DTSConstants.FLAG_N);
				
				// �ű��� ���
				if(dto == null){
					pdto.setPrdt_cd(mgr.selectTsPrdtMstKey());
					mgr.insertTsPrdtMst(pdto);
				}else{
				// ������ ���
					pdto.setPrdt_cd(dto.getPrdt_cd());
					pdto.setRgn_dt(null);
					pdto.setRgn_id(null);
					mgr.updateTsPrdtMst(pdto);
				}
				
				callback.callback(CallbackIF.CMD_LIST, null);
				MessageDialog.openInformation(getShell(), "����Ϸ�", "���������� ����Ǿ����ϴ�.");
				btnClearMouseDown(null);
			}catch(Exception e){
				MessageDialog.openError(getShell(), "���� Error", e.getMessage());
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}

	private boolean validate() {
		if("".equals(txtPrdtNm.getText().trim())){
			MessageDialog.openWarning(getShell(), "��ǰ�� �Է� Ȯ��", "��ǰ���� �Է��� �ּ���.");
			txtPrdtNm.setFocus();
			return false;
		}
		
		if(btnCheck.getSelection() && "".equals(txtPrdtMgtCd.getText().trim())){
			MessageDialog.openWarning(getShell(), "������ȣ �Է� Ȯ��", "������ȣ�� �Էµ��� �ʾҽ��ϴ�.\n\n�����ڵ带 �Է��ϰų�, ���� �����͸� üũ���� �Ͻñ� �ٶ��ϴ�.");
			txtPrdtMgtCd.setFocus();
			return false;
		}
		TsPrdtMstDTO p = new TsPrdtMstDTO();
		if(dto != null)
			p.setPrdt_cd(dto.getPrdt_cd());
		if(!"".equals(txtPrdtMgtCd.getText().trim())){
			// ������ȣ �ߺ� Ȯ��
			p.setPrdt_mgt_cd(txtPrdtMgtCd.getText().trim());
			if(mgr.existTsPrdtMstMgtCd(p)){
				MessageDialog.openWarning(getShell(), "������ȣ �ߺ� Ȯ��", "�ߺ��� ������ȣ�Դϴ�. ���� �� ó���ٶ��ϴ�.");
				txtPrdtMgtCd.setFocus();
				return false;
			}
		}
		// ��ǰ�� �ߺ� Ȯ�� : �ߺ��� ��� confirm!
		p.setPrdt_nm(txtPrdtNm.getText().trim());
		ArrayList<TsPrdtMstDTO> list = mgr.selectTsPrdtMst(p);
		boolean e = false;
		if(dto == null){
			if(list.size()>0)
				e = true;
		}else{
			for(TsPrdtMstDTO d:list){
				if(!dto.getPrdt_cd().equals(d.getPrdt_cd())){
					e = true;
					break;
				}
			}
		}
		if(e){
			return MessageDialog.openConfirm(getShell(), "��ǰ�� �ߺ� Ȯ��", "������ ��ǰ���� �����մϴ�.\n\n��� �����Ͻðڽ��ϱ�?");
		}
	    return true;
    }

	private void btnDeleteMouseDown(MouseEvent evt) {
		try{
			if(dto == null){
				this.btnClearMouseDown(null);
				return;
			}
			if(MessageDialog.openConfirm(getShell(), "����Ȯ��", "�����͸� �����Ͻðڽ��ϱ�?")){
				dto = (TsPrdtMstDTO)ObjectUtil.getUpdateObject(dto);
				mgr.deleteTsPrdtMst(dto);
				callback.callback(CallbackIF.CMD_LIST, null);
				btnClearMouseDown(null);
				MessageDialog.openInformation(getShell(), "�����Ϸ�", "���������� �����Ǿ����ϴ�.");
			}
		}catch(Exception e){
			MessageDialog.openError(getShell(), "���� Error", e.getMessage());
			log.error(e.getMessage());
		}
	}

	private void btnClearMouseDown(MouseEvent evt) {
		dto = null;
		this.txtPrdtMgtCd.setText("");
		this.txtPrdtNm.setText("");
		this.txtNt.setText("");
		this.txtUnitPrice.setValue(0);
		this.btnCheck.setSelection(false);
	}

	@Override
	public void callback(String cmd, Object obj) {
		btnClearMouseDown(null);
		dto = (TsPrdtMstDTO)obj;
		select();
	}

	private void select() {
		ArrayList<TsPrdtMstDTO> list = mgr.selectTsPrdtMst(dto);
		if(list == null || list.size()==0){
			MessageDialog.openWarning(getShell(), "��ȸ ����", "��ȸ�� �����Ͱ� �����ϴ�.");
			return;
		}
		dto = list.get(0);
		this.txtPrdtMgtCd.setText(StringUtil.nullToBlank(dto.getPrdt_mgt_cd()));
		this.txtPrdtNm.setText(StringUtil.nullToBlank(dto.getPrdt_nm()));
		this.txtUnitPrice.setValue(dto.getUnt_prc());
		this.txtNt.setText(StringUtil.nullToBlank(dto.getNt()));
		if(DTSConstants.FLAG_Y.equals(dto.getMgt_yn()))
			this.btnCheck.setSelection(true);
		txtPrdtMgtCd.setFocus();
    }

	@Override
	public void addCallback(CallbackIF callback) {
		this.callback = callback;
	}
}