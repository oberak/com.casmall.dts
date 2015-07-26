package com.casmall.dts.print;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public class PStyle {
	public double[] lines;
	public RGB fontColor;
	public RGB lineColor;
	public RGB backColor;
	public String fontData;
	public PStyle() {
		lines = new double[4];
		lines[0] = 0.0;
		lines[1] = 0.0;
		lines[2] = 0.0;
		lines[3] = 0.0;
		fontColor = new RGB(0,0,0);
		lineColor = new RGB(0,0,0);
		backColor = new RGB(255,255,255);
	}

	public static PStyle getDefaultStyle() {
		return new PStyle();
	}

	public int getLineWidth(int num) {
		int pixel = 0;
		if (num == 0 || num == 2)
			pixel = PBox.pixelY(lines[num]);
		if (num == 1 || num == 3)
			pixel = PBox.pixelX(lines[num]);
		if (pixel < 0)
			return 0;
		if (pixel == 0)
			return 1;
		return pixel;
	}

	public boolean hasLine(int num) {
		return lines[num] > 0;
	}

	public Color getColor(RGB rgb){
		return new Color(PBox.device, rgb);
	}
	
	public Color getLineColor() {
		return getColor(lineColor);
	}

	public Color getBackColor() {
		return getColor(backColor);
	}
	
	public Color getFontColor() {
		return getColor(fontColor);
	}
	
	public Font getFont(String fd){
		return new Font(PBox.device, new FontData(fd));
	}
}