package com.casmall.dts.print;

import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class PQRCode extends PBox{
	protected String text;
	
	protected PQRCode(PContainer parent, int style, double xPos, double yPos) {
		this(parent, style, xPos, yPos, 0.0, 0.0, "");
	}

	public PQRCode(PContainer parent, int style, double xPos, double yPos, double width, double height, String text) {
		super(parent, style);

		this.posX = xPos;
		this.posY = yPos;
		this.width = width;
		this.height = height;
		this.text = text;
	}

	public PQRCode(PContainer parent, int style, double xPos, double yPos, double width, double height, String text, RGB lineColor) {
		this(parent, style, xPos, yPos, width, height, text);
		this.prtStyle.lineColor = lineColor;
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
	
		Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
		try {
			
			BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
			Image printerImage = new Image(gc.getDevice(),convertToSWT(MatrixToImageWriter.toBufferedImage(matrix)));
			gc.drawImage(printerImage, 0, 0, width,
	                height, originForDrawing.x, originForDrawing.y, 
	               width, 
	               height);
			System.out.println("QR Code image created successfully!");
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
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
	
	private ImageData convertToSWT(BufferedImage bufferedImage) {
		if (bufferedImage.getColorModel() instanceof DirectColorModel) {
			DirectColorModel colorModel = (DirectColorModel)bufferedImage.getColorModel();
			PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(), colorModel.getBlueMask());
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int rgb = bufferedImage.getRGB(x, y);
					int pixel = palette.getPixel(new RGB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF)); 
					data.setPixel(x, y, pixel);
					if (colorModel.hasAlpha()) {
						data.setAlpha(x, y, (rgb >> 24) & 0xFF);
					}
				}
			}
			return data;		
		} else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
			IndexColorModel colorModel = (IndexColorModel)bufferedImage.getColorModel();
			int size = colorModel.getMapSize();
			byte[] reds = new byte[size];
			byte[] greens = new byte[size];
			byte[] blues = new byte[size];
			colorModel.getReds(reds);
			colorModel.getGreens(greens);
			colorModel.getBlues(blues);
			RGB[] rgbs = new RGB[size];
			for (int i = 0; i < rgbs.length; i++) {
				rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
			}
			PaletteData palette = new PaletteData(rgbs);
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
			data.transparentPixel = colorModel.getTransparentPixel();
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					raster.getPixel(x, y, pixelArray);
					data.setPixel(x, y, pixelArray[0]);
				}
			}
			return data;
		}
		return null;
	}
}
