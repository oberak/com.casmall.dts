package com.casmall.dts.print;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class PTextBox extends PBox {

	protected String text;
	protected double marginLeft;
	protected double marginRight;
	protected double marginTop;
	protected double marginBottom;
	private double thickness;
	
	protected PTextBox(PContainer parent) {
		super(parent);
		init();
	}

	/**
	 * Creates a non-wrapping text box with a fixed size according to its text.
	 * 
	 * @param parent
	 * @param style
	 */
	protected PTextBox(PContainer parent, int style) {
		super(parent, style);
		init();
	}

	public PTextBox(PContainer parent, int style, double posX, double posY, String text) {
		this(parent, style);
		this.posX = posX;
		this.posY = posY;
		this.text = text;
	}
	
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, String fontData) {
		this(parent, style, posX, posY, text);
		this.prtStyle.fontData = fontData;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, RGB textColor) {
		this(parent, style, posX, posY, text);
		this.prtStyle.fontColor = textColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, RGB fontColor, RGB lineColor) {
		this(parent, style, posX, posY, text, fontColor);
		this.prtStyle.lineColor = lineColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, String fontData, RGB fontColor) {
		this(parent, style, posX, posY, text, fontData);
		this.prtStyle.fontColor = fontColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, String fontData, RGB fontColor, RGB lineColor) {
		this(parent, style, posX, posY, text, fontData, fontColor);
		this.prtStyle.lineColor = lineColor;
	}
	
	public PTextBox(PContainer parent, int style, double posX, double posY, String text, String fontData, RGB fontColor, RGB lineColor, RGB backColor) {
		this(parent, style, posX, posY, text, fontData, fontColor, lineColor);
		this.prtStyle.backColor = backColor;
	}

	public PTextBox(PContainer parent, int style, double posX, double posY, String text, String fontData, RGB fontColor, RGB lineColor, RGB backColor, double thickness) {
		this(parent, style, posX, posY, text, fontData, fontColor, lineColor, backColor);
		this.thickness = thickness;
		this.prtStyle.lines = new double[] { thickness, thickness, thickness, thickness };
	}

	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text) {
		this(parent, style);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.text = text;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, RGB textColor) {
		this(parent, style, posX, posY, width, height, text);
		this.prtStyle.lineColor = textColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, RGB textColor, RGB backColor) {
		this(parent, style, posX, posY, width, height, text, textColor);
		this.prtStyle.backColor = backColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, String fontData) {
		this(parent, style, posX, posY, width, height, text);
		this.prtStyle.fontData = fontData;
	}
	
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, String fontData, RGB lineColor) {
		this(parent, style, posX, posY, width, height, text, fontData);
		this.prtStyle.lineColor = lineColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, String fontData, RGB fontColor, RGB lineColor) {
		this(parent, style, posX, posY, width, height, text, fontData, fontColor);
		this.prtStyle.lineColor = lineColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, String fontData, RGB fontColor, RGB lineColor, RGB backColor) {
		this(parent, style, posX, posY, width, height, text, fontData, fontColor, lineColor);
		this.prtStyle.backColor = backColor;
	}
	public PTextBox(PContainer parent, int style, double posX, double posY, double width, double height, String text, String fontData, RGB fontColor, RGB lineColor, RGB backColor, double thickness) {
		this(parent, style, posX, posY, width, height, text, fontData, fontColor, lineColor,backColor);
		this.thickness = thickness;
		this.prtStyle.lines = new double[] { thickness, thickness, thickness, thickness };
	}
	private void init() {
		text = "";
		prtStyle = PStyle.getDefaultStyle();
	}

	public void setText(String text) {
		if (text == null)
			text = "";
		this.text = text;
	}

	/*
	 * overridden from superclass
	 */
	protected int getWidth() {
		if (this.width > 0) {
			return pixelX(width);
		}
		gc.setFont(prtStyle.getFont(prtStyle.fontData));
		int erg = gc.stringExtent(text).x;
		erg += pixelX(marginLeft);
		erg += pixelX(marginRight);
		return erg;
	}

	/*
	 * overridden from superclass
	 */
	protected int getHeight() {
		if (this.height > 0) {
			return pixelY(height);
		}
		gc.setFont(prtStyle.getFont(prtStyle.fontData));
		int lineHeight = gc.stringExtent("A").y;
		lineHeight += pixelY(marginTop);
		lineHeight += pixelY(marginBottom);
		return lineHeight;
	}

	public void draw(Point originOffset) {
		Point origin = getPoint();
		Point originForDrawing = new Point(origin.x + originOffset.x, origin.y + originOffset.y);

		// draw line, fill color
		if ( thickness > 0) {
			super.draw(originOffset);
		}
		
		// draw text
		gc.setFont(prtStyle.getFont(prtStyle.fontData));
		gc.setForeground(prtStyle.getFontColor());
		int textHeight = gc.stringExtent("A").y;
		if(getHeight()>textHeight)
			originForDrawing.y = originForDrawing.y + (getHeight()-textHeight)/2;
		
		int alignPixel = pixelX(thickness);
		if ( (style & CENTER) > 0) {
			int textWidth = gc.stringExtent(text).x;
			alignPixel = (getWidth() - pixelX(marginLeft) - pixelX(marginRight) - textWidth) / 2;
		} else if ( (style & RIGHT) > 0) {
			int textWidth = gc.stringExtent(text).x;
			alignPixel = (getWidth() - pixelX(marginLeft) - pixelX(marginRight) - textWidth) - pixelX(thickness);
		}
		gc.drawText(text, originForDrawing.x + alignPixel + originOffset.x + pixelX(marginLeft),
		        originForDrawing.y, true);
	}


	/**
	 * Sets the textStyle.
	 * 
	 * @param textStyle
	 *            The textStyle to set
	 */
	public void setTextStyle(PStyle textStyle) {
		this.prtStyle = textStyle;
	}

	/**
	 * margin set
	 * top,right,bottom,left
	 * @param margin
	 */
	public void setMargin(double... margin){
		this.marginTop = margin[0];
		if(margin.length == 1){
			this.marginRight = margin[0];
			this.marginBottom = margin[0];
			this.marginLeft = margin[0];
		}
		if(margin.length > 1){
			this.marginRight = margin[1];
			if(margin.length > 2){
				this.marginBottom = margin[2];
				if(margin.length > 3){
					this.marginLeft = margin[3];
				}
			}
		}
			
	}
}
