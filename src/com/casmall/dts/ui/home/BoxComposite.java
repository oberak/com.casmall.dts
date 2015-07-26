package com.casmall.dts.ui.home;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.casmall.dts.common.ImageRepository;

public class BoxComposite extends Composite {
//	private Composite control = new Composite(this, SWT.NONE);
	Image[] images = new Image[8];
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public BoxComposite(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	@Override
	protected void checkSubclass() {
	}

	private void initGUI() {
		try {
			images[0] = ImageRepository.getImage(ImageRepository.BOX_TOP_L);
			images[1] = ImageRepository.getImage(ImageRepository.BOX_TOP_C);
			images[2] = ImageRepository.getImage(ImageRepository.BOX_TOP_R);
			images[3] = ImageRepository.getImage(ImageRepository.BOX_MID_L);
			images[4] = ImageRepository.getImage(ImageRepository.BOX_MID_R);
			images[5] = ImageRepository.getImage(ImageRepository.BOX_BOTTOM_L);
			images[6] = ImageRepository.getImage(ImageRepository.BOX_BOTTOM_C);
			images[7] = ImageRepository.getImage(ImageRepository.BOX_BOTTOM_R);

//			Composite box = new Composite(this.getParent(), SWT.NONE);
			
			final GridLayout layout = new GridLayout();
			layout.numColumns=3;
			layout.horizontalSpacing = 0;
			layout.verticalSpacing = 0;
			layout.marginBottom = 0;
			layout.marginHeight = 0;
			layout.marginLeft = 0;
			layout.marginRight = 0;
			layout.marginTop = 0;
			layout.marginWidth = 0;
			this.setLayout(layout);
//			box.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
//			this.setBackground(ColorRepository.getColor(ColorRepository.BG_CONTENTS));
			{
				final CLabel lblTopLeft = new CLabel(this, SWT.NONE);
				final GridData gd_lblTopLeft = new GridData(SWT.BEGINNING, SWT.TOP, false, false);
				gd_lblTopLeft.heightHint = 3;
				gd_lblTopLeft.widthHint = 3;
				lblTopLeft.setLayoutData(gd_lblTopLeft);
				lblTopLeft.setBackground(images[0]);
			}
			{
				final CLabel lblTopCenter = new CLabel(this, SWT.NONE);
				final GridData gd_lblTopCenter = new GridData(SWT.FILL, SWT.TOP, true, false);
				gd_lblTopCenter.heightHint = 3;
				lblTopCenter.setLayoutData(gd_lblTopCenter);
				lblTopCenter.setBackgroundImage(images[1]);
			}
			{
				final CLabel lblTopRight = new CLabel(this, SWT.NONE);
				final GridData gd_lblTopCenter = new GridData(SWT.END, SWT.TOP, false, false);
				gd_lblTopCenter.heightHint = 3;
				gd_lblTopCenter.widthHint = 3;
				lblTopRight.setLayoutData(gd_lblTopCenter);
				lblTopRight.setBackground(images[2]);
			}

			{
				final CLabel lblMiddleLeft = new CLabel(this, SWT.NONE);
				final GridData gd_lblMiddleLeft = new GridData(SWT.BEGINNING, SWT.FILL, false, true);
				gd_lblMiddleLeft.widthHint = 3;
				lblMiddleLeft.setLayoutData(gd_lblMiddleLeft);
				lblMiddleLeft.setBackground(images[3]);
			}

			
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void add(){
		{
			final CLabel lblMiddleRight = new CLabel(this, SWT.NONE);
			final GridData gd_lblMiddleRight = new GridData(SWT.END, SWT.FILL, false, true);
			gd_lblMiddleRight.widthHint = 3;
			lblMiddleRight.setLayoutData(gd_lblMiddleRight);
			lblMiddleRight.setBackground(images[4]);
		}
		
		{
			final CLabel lblBottomLeft = new CLabel(this, SWT.NONE);
			final GridData gd_lblBottomLeft = new GridData(SWT.BEGINNING, SWT.END, false, false);
			gd_lblBottomLeft.widthHint = 3;
			gd_lblBottomLeft.heightHint = 3;
			lblBottomLeft.setLayoutData(gd_lblBottomLeft);
			lblBottomLeft.setBackground(images[5]);
		}

		{
			final CLabel lblBottomCenter = new CLabel(this, SWT.NONE);
			final GridData gd_lblBottomCenter = new GridData(SWT.FILL, SWT.END, true, false);
			gd_lblBottomCenter.heightHint = 3;
			lblBottomCenter.setLayoutData(gd_lblBottomCenter);
			lblBottomCenter.setBackgroundImage(images[6]);
		}
		{
			final CLabel lblBottomRight = new CLabel(this, SWT.NONE);
			final GridData gd_lblBottomRight = new GridData(SWT.END, SWT.TOP, false, false);
			gd_lblBottomRight.widthHint = 3;
			gd_lblBottomRight.heightHint = 3;
			lblBottomRight.setLayoutData(gd_lblBottomRight);
			lblBottomRight.setBackground(images[7]);
		}
	}
//	public Composite getControl(){
//		return control;
//	}
}