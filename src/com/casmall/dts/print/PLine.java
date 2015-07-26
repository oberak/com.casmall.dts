package com.casmall.dts.print;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class PLine extends PBox {

	private PLine(PContainer parent, int style) {
		super(parent);
		this.height = 0.01;
		prtStyle.lines = new double[] { 0.0, 0.0, 0.0, 0.0 };
		prtStyle.backColor = prtStyle.lineColor;
		this.style = style;
	}

	/**
	 * Create line
	 * 
	 * @param parent
	 * @param style
	 * @param posX
	 * @param posY
	 * @param length
	 * @param thickness
	 * @param color
	 */
	public PLine(PContainer parent, int style, double posX, double posY, double length, double thickness, RGB color) {
		this(parent, style);
		this.posX = posX;
		this.posY = posY;
		this.prtStyle.backColor = color;

		if ((style & PBox.HORIZONTAL) > 0) {
			this.width = length;
			this.height = thickness;
		} else if ((style & PBox.VERTICAL) > 0) {
			this.width = thickness;
			this.height = length;
		}
	}

	/**
	 * Create line with default color(BLACK)
	 * 
	 * @param parent
	 * @param style
	 *            PBox.HORIZONTAL or PBox.VERTICAL, PBox.DIAGONAL
	 * @param posX
	 * @param posY
	 * @param length
	 * @param thickness
	 */
	public PLine(PContainer parent, int style, double posX, double posY, double length, double thickness) {
		this(parent, style, posX, posY, length, thickness, new RGB(0,0,0));
	}

	protected int getHeight() {
		int erg = PBox.pixelY(height);
		if (erg == 0)
			return 1;
		return erg;
	}
	
	protected void draw(Point originOffset) {
		// TODO LOW 사선 추후 필요 시 구현
		/*if ((style & PBox.DIAGONAL) > 0) {
			Point origin = getPoint();
			Point originForDrawing = new Point(origin.x + originOffset.x, origin.y + originOffset.y);
			int height = getHeight();
			gc.setLineWidth(height);
			gc.setForeground(boxStyle.getBackColor());
			gc.drawLine(originForDrawing.x, originForDrawing.y, PBox.pixelX(width), PBox.pixelY(height));
			return;
		}*/
		
		super.draw(originOffset);
	}
}