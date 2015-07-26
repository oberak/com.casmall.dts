package com.casmall.dts.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class PQRCode{
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

	protected PQRCode(PContainer parent) {
		this.parent = parent;
		if (parent != null)
			parent.addChild(this);

		prtStyle = PStyle.getDefaultStyle();
	}

	protected PQRCode(PContainer parent, int style) {
		this.parent = parent;
		this.style = style;
		if (parent != null)
			parent.addChild(this);

		prtStyle = PStyle.getDefaultStyle();
	}

	protected PQRCode(PContainer parent, int style, double xPos, double yPos) {
		this(parent, style, xPos, yPos, 0.0, 0.0, 0.01);
	}

	public PQRCode(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness) {
		this(parent, style);

		this.posX = xPos;
		this.posY = yPos;
		this.width = width;
		this.height = height;
		prtStyle.lines = new double[] { thickness, thickness, thickness, thickness };
	}

	public PQRCode(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness, RGB lineColor) {
		this(parent, style, xPos, yPos, width, height, thickness);
		this.prtStyle.lineColor = lineColor;
	}

	public PQRCode(PContainer parent, int style, double xPos, double yPos, double width, double height, double thickness, RGB lineColor, RGB backColor) {
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
		/*
String qrCodeData = "";
		String filePath = "QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
		createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
		System.out.println("QR Code image created successfully!");

		System.out.println("Data read from QR Code: "
				+ readQRCode(filePath, charset, hintMap));
 
		 */
		
		/*
		String fileName = ""; // TODO file name set
		
		// Load the image
		ImageLoader loader = new ImageLoader();
		ImageData[] imageData = loader.load(fileName);
	      
		if (imageData.length > 0) {
	        // Show the Choose Printer dialog
	        PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
	        PrinterData printerData = dialog.open();

	        if (printerData != null) {
	          // Create the printer object
	          Printer printer = new Printer(printerData);
	  
	          // Calculate the scale factor between the screen resolution and printer
	          // resolution in order to correctly size the image for the printer
	          Point screenDPI = display.getDPI();
	          Point printerDPI = printer.getDPI();
	          int scaleFactor = printerDPI.x / screenDPI.x;
	  
	          // Determine the bounds of the entire area of the printer
	          Rectangle trim = printer.computeTrim(0, 0, 0, 0);

	          // Start the print job
	          if (printer.startJob(fileName)) {
	            if (printer.startPage()) {
	              GC gc = new GC(printer);
	              Image printerImage = new Image(printer, imageData[0]);
	              
	              // Draw the image
	              gc.drawImage(printerImage, 0, 0, imageData[0].width,
	                imageData[0].height, -trim.x, -trim.y, 
	                scaleFactor * imageData[0].width, 
	                scaleFactor * imageData[0].height);
	  
	              // Clean up
	              printerImage.dispose();
	              gc.dispose();
	              printer.endPage();
	            }
	          }
	          // End the job and dispose the printer
	          printer.endJob();
	          printer.dispose();
	        }
	      }
	      */
		
		/*
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
public class MainClass {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    Image image = new Image(display, "fileName.gif");
    ImageData imageData = image.getImageData();
    PrintDialog dialog = new PrintDialog(shell, SWT.NULL);
    PrinterData printerData = dialog.open();
    Printer printer = new Printer(printerData);
    Point screenDPI = display.getDPI();
    Point printerDPI = printer.getDPI();
    int scaleFactor = printerDPI.x / screenDPI.x;
    Rectangle trim = printer.ruputeTrim(0, 0, 0, 0);
    if (printer.startJob("fileName")) {
      if (printer.startPage()) {
        GC gc = new GC(printer);
        Image printerImage = new Image(printer, imageData);
        // Draw the image
        gc.drawImage(printerImage, 0, 0, imageData.width, imageData.height, -trim.x, -trim.y,
            scaleFactor * imageData.width, scaleFactor * imageData.height);
        // Clean up
        printerImage.dispose();
        gc.dispose();
        printer.endPage();
      }
    }
    // End the job and dispose the printer
    printer.endJob();
    printer.dispose();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
		 */
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
	
	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map<EncodeHintType,Object> hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				//new String(qrCodeData.getBytes(charset), charset),
				qrCodeData,
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
//		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
//				.lastIndexOf('.') + 1), new File(filePath));
		MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath).toPath());
		
		// MatrixToImageWriter.writeToStream(matrix, "png", out); 
	}

	public static String readQRCode(String filePath, String charset, Map<DecodeHintType,?> hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
}
