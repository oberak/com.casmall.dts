package com.casmall.dts.print;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class PBox {
	public static final int NONE = SWT.NONE;
	public static final int HORIZONTAL = SWT.HORIZONTAL;
	public static final int VERTICAL = SWT.VERTICAL;
	// public static final int DIAGONAL = SWT.DOWN;
	
	public static final int LEFT = SWT.LEFT;
	public static final int CENTER = SWT.CENTER;
	public static final int RIGHT = SWT.RIGHT;
	
	/** the Device on which the GC works. */
	protected static Device device = null; //
	/** cm to pixel calc */
	protected static Point pixelPerMm = new Point(0, 0);
	protected static GC gc = null;
	protected PContainer parent;

	protected PStyle prtStyle;
	protected double width = 0.0;
	protected double height = 0.0;
	protected double posX = 0.0;
	protected double posY = 0.0;
	protected int style;

	protected PBox(PContainer parent) {
		this.parent = parent;
		if (parent != null)
			parent.addChild(this);

		prtStyle = PStyle.getDefaultStyle();
	}

	protected PBox(PContainer parent, int style) {
		this.parent = parent;
		this.style = style;
		if (parent != null)
			parent.addChild(this);

		prtStyle = PStyle.getDefaultStyle();
	}

	protected PBox(PContainer parent, int style, double xPos, double yPos) {
		this(parent, style, xPos, yPos, 0.0, 0.0, 0.01);
	}

	public PBox(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness) {
		this(parent, style);

		this.posX = xPos;
		this.posY = yPos;
		this.width = width;
		this.height = height;
		prtStyle.lines = new double[] { thickness, thickness, thickness, thickness };
	}

	public PBox(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness, RGB lineColor) {
		this(parent, style, xPos, yPos, width, height, thickness);
		this.prtStyle.lineColor = lineColor;
	}

	public PBox(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness, RGB lineColor, RGB backColor) {
		this(parent, style, xPos, yPos, width, height, thickness, lineColor);
		this.prtStyle.backColor = backColor;
	}

	/**
	 * get Point setParameters 이후에 호출되어야 함.
	 * 
	 * @return
	 */
	public Point getPoint() {
		return new Point(pixelX(posX), pixelX(posY));
	}

	/**
	 * Sets the main parameters for a document to print.
	 * 
	 * @param theGC
	 * @param theDevice
	 * @param dpi
	 */
	public static void setParameters(GC theGC, Device theDevice, Point dpi) {
		gc = theGC;
		device = theDevice;
		pixelPerMm = new Point((int) Math.round(dpi.x / 2.54 /10.0), (int) Math.round(dpi.y / 2.54 / 10.0));
	}

	protected static int pixelX(double mm) {
		return (int) Math.round(mm * pixelPerMm.x);
	}

	protected static int pixelY(double mm) {
		long tmp = Math.round(mm * pixelPerMm.y);
		return (int) tmp;
	}

	/**
	 * 높이 구하기
	 * 
	 * @return
	 */
	protected int getHeight() {
		if (height > 0)
			return pixelY(height);
		return pixelY(parent.getPossibleHeight());
	}

	/**
	 * 너비 구하기 주어진 너비가 없을 경우 최대값 return
	 * 
	 * @return
	 */
	protected int getWidth() {
		if (width > 0)
			return pixelX(width);
		return pixelX(parent.getPossibleWidth());
	}

	protected void draw(Point originOffset) {
		Point origin = getPoint();
		Point originForDrawing = new Point(origin.x + originOffset.x, origin.y + originOffset.y);

		int width = getWidth();
		int height = getHeight();
	
		gc.setBackground(prtStyle.getBackColor());
		gc.fillRectangle(originForDrawing.x, originForDrawing.y, width, height);
		gc.setBackground(prtStyle.getLineColor());

		// top line
		if (prtStyle.hasLine(0)) {
			gc.fillRectangle(originForDrawing.x, originForDrawing.y, width, prtStyle.getLineWidth(0));
		}
		// right line
		if (prtStyle.hasLine(1)) {
			gc.fillRectangle(originForDrawing.x + width - prtStyle.getLineWidth(1), originForDrawing.y,
			        prtStyle.getLineWidth(1), height);
		}
		// bottom line
		if (prtStyle.hasLine(2)) {
			gc.fillRectangle(originForDrawing.x, originForDrawing.y + height - prtStyle.getLineWidth(2), width,
			        prtStyle.getLineWidth(2));
		}
		// left line
		if (prtStyle.hasLine(3)) {
			gc.fillRectangle(originForDrawing.x, originForDrawing.y, prtStyle.getLineWidth(3), height);
		}

		gc.setBackground(prtStyle.getBackColor());
	}

	public PDocument getDocument() {
		return parent.doc;
	}
	
	/**
	 * @return PTextStyle
	 */
	public PStyle getPrtStyle() {
		return prtStyle;
	}

	public void dispose() {
		parent.children.remove(this);
	}
}
