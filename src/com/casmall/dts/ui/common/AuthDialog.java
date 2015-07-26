package com.casmall.dts.ui.common;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.AuthUtil;
import com.casmall.common.CryptoUtil;
import com.casmall.dts.common.ColorRepository;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ImageRepository;
import com.casmall.dts.ui.preferences.DTSPreConstants;
import com.swtdesigner.SWTResourceManager;

/**
 * ���α׷� ��� ����
 * 
 * @author OBERAK
 */
public class AuthDialog extends Dialog {
	protected static Log logger = LogFactory.getLog(AuthDialog.class);

	protected final Font titleFont = SWTResourceManager.getFont("Arial Black", 18, SWT.BOLD);
	protected final Font defaultFont = SWTResourceManager.getFont("����ü", 16, SWT.BOLD);

	protected Object result;
	protected Shell shell;

	private Group grpAuth;

	private Group grpUsr;

	private Text txtBase;
	private Text txtSerial1;
	private Text txtSerial2;
	private Text txtSerial3;

	private Text txtCustNm;
	private Text txtCustAdd;
	private Text txtCustTel;
	private Text txtCustFax;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public AuthDialog(Shell parent, int style) {
		super(parent, style);
		setText("���α׷� ��� ����");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();

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
		shell.setSize(438, 427);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		shell.setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
		shell.setBackgroundMode(SWT.TRANSPARENT);

		Label lblTitle = new Label(shell, SWT.NONE);
		lblTitle.setBackground(SWTResourceManager.getColor(65, 105, 225));
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		lblTitle.setFont(titleFont);
		lblTitle.setText("���α׷� ��� ����");

		CLabel lbl = new CLabel(shell, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		lbl.setFont(SWTResourceManager.getFont("����", 12, SWT.NORMAL));
		lbl.setText("���α׷� ��������� ���� ������ȣ�� �Է��ϼ���.\n  - ������ȣ ����: (��)ī����īƮ�δн�. 031)618-3500");

		grpAuth = new Group(shell, SWT.NONE);
		grpAuth.setText("���� ����");
		grpAuth.setFont(defaultFont);
		grpAuth.setLayout(new GridLayout(6, false));
		grpAuth.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		{
			final CLabel lblTag = new CLabel(grpAuth, SWT.RIGHT);
			lblTag.setText("�� �� Ű: ");
			lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblTag.setFont(defaultFont);

			txtBase = new Text(grpAuth, SWT.BORDER | SWT.CENTER);
			txtBase.setEditable(false);
			txtBase.setFont(defaultFont);
			txtBase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));

			lbl = new CLabel(grpAuth, SWT.RIGHT);
			lbl.setText("������ȣ: ");
			lbl.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lbl.setFont(defaultFont);

			txtSerial1 = new Text(grpAuth, SWT.BORDER | SWT.CENTER);
			txtSerial1.setFont(defaultFont);
			txtSerial1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			txtSerial1.setTextLimit(4);
			txtSerial1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (txtSerial1.getText().length() == 4) {
						txtSerial2.setFocus();
					}
				}
			});

			Label label = new Label(grpAuth, SWT.NONE);
			label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			label.setFont(defaultFont);
			label.setText("-");

			txtSerial2 = new Text(grpAuth, SWT.BORDER | SWT.CENTER);
			txtSerial2.setFont(defaultFont);
			txtSerial2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			txtSerial2.setTextLimit(4);
			txtSerial2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (txtSerial2.getText().length() == 4) {
						txtSerial3.setFocus();
					}
					if (txtSerial2.getText().length() == 0 && 0x2E == e.keyCode) {
						txtSerial1.setFocus();
					}
					
				}
			});

			label = new Label(grpAuth, SWT.NONE);
			label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			label.setFont(defaultFont);
			label.setText("-");

			txtSerial3 = new Text(grpAuth, SWT.BORDER | SWT.CENTER);
			txtSerial3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (txtSerial3.getText().length() == 0 && 0x2E == e.keyCode) {
						txtSerial2.setFocus();
					}
					if (0x0D == e.keyCode) {
						// Enter �� ��� Tab ó��
						txtSerial2.traverse(SWT.TRAVERSE_TAB_NEXT);
						return;
					}
				}
			});
			txtSerial3.setFont(defaultFont);
			txtSerial3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			txtSerial3.setTextLimit(4);
		}

		grpUsr = new Group(shell, SWT.NONE);
		grpUsr.setText("���� ����");
		grpUsr.setFont(defaultFont);
		grpUsr.setLayout(new GridLayout(2, false));
		grpUsr.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		{
			CLabel lblTag = new CLabel(grpUsr, SWT.RIGHT);
			lblTag.setText("ȸ���ȣ: ");
			lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblTag.setFont(defaultFont);

			txtCustNm = new Text(grpUsr, SWT.BORDER);
			txtCustNm.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (0x0D == e.keyCode) {
						// Enter �� ��� Tab ó��
						txtSerial2.traverse(SWT.TRAVERSE_TAB_NEXT);
						return;
					}
				}
			});
			txtCustNm.setTextLimit(100);
			txtCustNm.setFont(defaultFont);
			txtCustNm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			lblTag = new CLabel(grpUsr, SWT.RIGHT);
			lblTag.setText("ȸ���ּ�: ");
			lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblTag.setFont(defaultFont);

			txtCustAdd = new Text(grpUsr, SWT.BORDER);
			txtCustAdd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (0x0D == e.keyCode) {
						// Enter �� ��� Tab ó��
						txtSerial2.traverse(SWT.TRAVERSE_TAB_NEXT);
						return;
					}
				}
			});
			txtCustAdd.setTextLimit(100);
			txtCustAdd.setFont(defaultFont);
			txtCustAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			lblTag = new CLabel(grpUsr, SWT.RIGHT);
			lblTag.setText("��ȭ��ȣ: ");
			lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblTag.setFont(defaultFont);

			txtCustTel = new Text(grpUsr, SWT.BORDER);
			txtCustTel.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (0x0D == e.keyCode) {
						// Enter �� ��� Tab ó��
						txtSerial2.traverse(SWT.TRAVERSE_TAB_NEXT);
						return;
					}
				}
			});
			txtCustTel.setTextLimit(100);
			txtCustTel.setFont(defaultFont);
			txtCustTel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			lblTag = new CLabel(grpUsr, SWT.RIGHT);
			lblTag.setText("�ѽ���ȣ: ");
			lblTag.setImage(ImageRepository.getImage(ImageRepository.DOT_SELECT));
			lblTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			lblTag.setFont(defaultFont);

			txtCustFax = new Text(grpUsr, SWT.BORDER);
			txtCustFax.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (0x0D == e.keyCode) {
						// Enter �� ��� Tab ó��
						txtSerial2.traverse(SWT.TRAVERSE_TAB_NEXT);
						return;
					}
				}
			});
			txtCustFax.setTextLimit(100);
			txtCustFax.setFont(defaultFont);
			txtCustFax.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}

		Button btnOK = new Button(shell, SWT.NONE);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clickOK();
			}
		});
		btnOK.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnOK.setFont(defaultFont);
		btnOK.setText(" Ȯ ��  ");

		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnCancel.setFont(defaultFont);
		btnCancel.setText(" ��  �� ");
	}// createContents

	protected void clickOK() {
		// validation
		if (!validate())
			return;

		// ����Ȯ��
		if (AuthUtil.genSerial().equals(txtSerial1.getText() + "-" + txtSerial2.getText() + "-" + txtSerial3.getText())) {
			// data ����
			CommonManager cm = CommonManager.getInstance();
			ArrayList<CmCdDTO> list = new ArrayList<CmCdDTO>();

			try {
				CmCdDTO dto = new CmCdDTO();
				dto.setCd_id("CUST_INF");
				dto.setCd_val("cust_nm");
	            dto.setCd_nm(CryptoUtil.encrypt3DES(txtCustNm.getText().trim()));
				list.add(dto);

				dto = new CmCdDTO();
				dto.setCd_id("CUST_INF");
				dto.setCd_val("cust_add");
				dto.setCd_nm(CryptoUtil.encrypt3DES(txtCustAdd.getText().trim()));
				list.add(dto);

				dto = new CmCdDTO();
				dto.setCd_id("CUST_INF");
				dto.setCd_val("cust_tel");
				dto.setCd_nm(CryptoUtil.encrypt3DES(txtCustTel.getText().trim()));
				list.add(dto);

				dto = new CmCdDTO();
				dto.setCd_id("CUST_INF");
				dto.setCd_val("cust_fax");
				dto.setCd_nm(CryptoUtil.encrypt3DES(txtCustFax.getText().trim()));
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
				MessageDialog.openError(shell, "�������", "������ ���� �� ������ �߻��Ͽ����ϴ�.\n\n" + e.getMessage());
				return;
			}

			try {
				ScopedPreferenceStore preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
				preferences.setValue(DTSPreConstants.GN_EXT_AUTH_SERIAL, CryptoUtil.encrypt3DES(AuthUtil.genSerial()));
				preferences.save();
			} catch (Exception e) {
				MessageDialog.openError(shell, "��������", "������ ���� �� ������ �߻��Ͽ����ϴ�.\n\n" + e.getMessage());
				e.printStackTrace();
			}
			result = new Object();
			shell.close();
		} else {
			MessageDialog.openWarning(shell, "������ȣ", "�������� ������ȣ�� �ƴմϴ�.\nȮ�� �� �Է¹ٶ��ϴ�.\n\n������ȣ����: ī����īƮ�δн�. 031)618-3500");
			txtSerial1.setFocus();
			return;
		}
	}

	protected boolean validate() {
		if (txtSerial1.getText().trim().length() != 4) {
			MessageDialog.openWarning(shell, "������ȣ", "������ȣ�� �߸� �ԷµǾ����ϴ�.");
			txtSerial1.setFocus();
			return false;
		}
		if (txtSerial2.getText().trim().length() != 4) {
			MessageDialog.openWarning(shell, "������ȣ", "������ȣ�� �߸� �ԷµǾ����ϴ�.");
			txtSerial2.setFocus();
			return false;
		}
		if (txtSerial2.getText().trim().length() != 4) {
			MessageDialog.openWarning(shell, "������ȣ", "������ȣ�� �߸� �ԷµǾ����ϴ�.");
			txtSerial2.setFocus();
			return false;
		}

		if ("".equals(txtCustNm.getText().trim())) {
			MessageDialog.openWarning(shell, "ȸ���ȣ", "ȸ���ȣ�� �Էµ��� �ʾҽ��ϴ�.");
			txtCustNm.setFocus();
			return false;
		}

		if ("".equals(txtCustAdd.getText().trim())) {
			MessageDialog.openWarning(shell, "ȸ���ּ�", "ȸ���ּҰ� �Էµ��� �ʾҽ��ϴ�.");
			txtCustAdd.setFocus();
			return false;
		}

		if ("".equals(txtCustTel.getText().trim())) {
			MessageDialog.openWarning(shell, "ȸ�翬��ó", "ȸ�翬��ó�� �Էµ��� �ʾҽ��ϴ�.");
			txtCustTel.setFocus();
			return false;
		}

		if ("".equals(txtCustFax.getText().trim())) {
			MessageDialog.openWarning(shell, "ȸ���ѽ�", "ȸ���ѽ��� �Էµ��� �ʾҽ��ϴ�.");
			txtCustFax.setFocus();
			return false;
		}

		return true;
	}

	/**
	 * init
	 */
	private void init() {
		txtBase.setText(AuthUtil.genBase());
	}// initData
}
