package com.casmall.dts.print;

import java.util.ArrayList;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;

public class PDocument extends PContainer {

	public static double PAPER_WIDTH = 20.6;
	public static double PAPER_HEIGHT = 29.6;
	public static int PAPER_SCALING = 100;

	protected double pageWidth;
	protected double pageHeight;
	protected double[] margins;
	protected String documentTitle;
	protected Point originOnFirstPage;

	/**
	 * 持失切
	 * @param pageWidth
	 * @param pageHeight
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 * @param docTitle
	 */
	public PDocument(double pageWidth, double pageHeight, double top, double right, double bottom, double left, String docTitle) {
		super(null);
		this.doc = this;
		this.pageHeight = pageHeight;
		this.pageWidth = pageWidth;
		this.documentTitle = docTitle;
		margins = new double[4];
		margins[0] = top;
		margins[1] = right;
		margins[2] = bottom;
		margins[3] = left;

		int offsetX = 0, offsetY = 0;
		originOnFirstPage = new Point(PBox.pixelX(margins[3]) + offsetX, PBox.pixelY(margins[0]) + offsetY);
	}

	/**
	 * 持失切
	 * @param docname
	 */
	public PDocument(String docname) {
		this(PAPER_WIDTH, PAPER_HEIGHT, 0, 0, 0, 0, docname);
	}

	/**
	 * get title
	 * @return
	 */
	public String getTitle() {
		return documentTitle;
	}

	/**
	 * draw
	 */
	public void draw() {
		super.draw(originOnFirstPage);
	}
	
	/**
	 * print with default printer
	 */
	public void print(int orientation) {
		PrinterData data = Printer.getDefaultPrinterData();
		data.orientation = orientation;
		print(new Printer(data));
	}
	
	/**
	 * print with printer
	 * @param printer
	 */
	public void print(Printer printer) {
		GC gc = new GC(printer);

		PBox.setParameters(gc, printer, printer.getDPI());

		if (printer.startJob(this.getTitle())) {
	    	printer.startPage();
			this.draw();
	    	printer.endPage();
			printer.endJob();
		}
		gc.dispose();
		printer.dispose();
	}
	
	/**
	 * print with printer name
	 * @param printerName
	 */
	public void print(String printerName, int orientation) {
//		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		PrinterData[] printerData = Printer.getPrinterList();
		for (PrinterData pd : printerData){
			if(pd.name.equals(printerName)){
				Printer printer = new Printer(pd);
				print(printer);
				return;
			}
        }
		print(orientation);
	}
	
	public ArrayList<String> getPrinterList(){
		ArrayList<String> list = new ArrayList<String>();
		PrinterData[] printerData = Printer.getPrinterList();
		for (PrinterData pd : printerData){
			list.add(pd.name);
        }
		return list;
	}
}
