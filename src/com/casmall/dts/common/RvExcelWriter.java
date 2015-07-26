package com.casmall.dts.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class RvExcelWriter {
	/**
	 * The filename
	 */
	private String filename;

	/**
	 * The workbook
	 */
	private WritableWorkbook workbook;

	/**
	 * Constructor
	 * 
	 * @param fn
	 */
	public RvExcelWriter(String fn) {
		filename = fn;
	}

	/**
	 * Uses the JExcelAPI to create a spreadsheet
	 * 
	 * @exception IOException
	 * @exception WriteException
	 */
	public void write(ExportVO export) throws IOException {
		// File ����
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("ko", "KR"));
		workbook = Workbook.createWorkbook(new File(filename), ws);

		// Sheet �߰�
		WritableSheet sheet = workbook.createSheet("DATA", 0);

		workbook.setColourRGB(Colour.LIME, 0xff, 0, 0);

		try {
			writeSheet(sheet, export);

			workbook.write();
			workbook.close();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	/**
	 * Writes out a sheet containing the various numerical formats
	 * 
	 * @param s
	 */
	private void writeSheet(WritableSheet s, ExportVO export) throws WriteException {
		int[] cellWidth = export.getWidth();
		String[] header = export.getHeader();
		
		WritableCellFormat dtFmtC = new WritableCellFormat();
		
		dtFmtC.setAlignment(Alignment.CENTRE);     // CENTRE�� ��� ����
		dtFmtC.setVerticalAlignment(VerticalAlignment.CENTRE);
		dtFmtC.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// �� ���� ����
		WritableCellFormat wrappedText = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT);
		wrappedText.setWrap(true);
		wrappedText.setBorder(Border.ALL, BorderLineStyle.THIN);

		// �� �ʺ� ����
		for(int i=0;i<cellWidth.length;i++){
			s.setColumnView(i, cellWidth[i]); // No
		}
		
		// Title
		WritableCellFormat titleFmt = new WritableCellFormat();
		titleFmt.setAlignment(Alignment.CENTRE);     // CENTRE�� ��� ����
		titleFmt.setVerticalAlignment(VerticalAlignment.CENTRE);
		WritableFont arial18ptBoldItalicUnderline = new WritableFont(WritableFont.ARIAL,18,WritableFont.BOLD,true,UnderlineStyle.SINGLE);
		titleFmt.setFont(arial18ptBoldItalicUnderline);
		
		int row = 0;
		s.mergeCells(row, 0, header.length-1, 0);
		Label lblTitle = new Label(0, row++, export.getTitle(), titleFmt);
		s.addCell(lblTitle);
		
		// ��ȸ����
		WritableCellFormat condFmt = new WritableCellFormat();
		condFmt.setAlignment(Alignment.LEFT);     // CENTRE�� ��� ����
		WritableFont yearFont = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE);
		condFmt.setFont(yearFont);
		s.mergeCells(0, row, header.length-1, row);
		Label condLabel = new Label(0, row++, export.getCond(), condFmt);
		s.addCell(condLabel);
		
		// ���̺� ���� ���
		WritableCellFormat ttlFmt = new WritableCellFormat();
		ttlFmt.setAlignment(Alignment.CENTRE);     // CENTRE�� ��� ����
		ttlFmt.setVerticalAlignment(VerticalAlignment.CENTRE);
		ttlFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
		ttlFmt.setBackground(Colour.ICE_BLUE);     //ICE_BLUE�� �������� �ϳ�~
		WritableFont headerFont = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,true,UnderlineStyle.NO_UNDERLINE);
		ttlFmt.setFont(headerFont);
		
		WritableCellFormat nfFmt;
		NumberFormat nf = new NumberFormat("#,###.###");
		DateFormat dfmt = new DateFormat("yyyy-MM-dd HH:mm:ss");
		nfFmt = new WritableCellFormat(nf);
		nfFmt.setBorder(Border.ALL, BorderLineStyle.THIN);
		nfFmt.setAlignment(Alignment.RIGHT);
		
		int col = 0;
		for(int i=0; i<header.length;i++){
			Label lblTtl = new Label(col++, row, header[i], ttlFmt);
			s.addCell(lblTtl);
		}
		
		row++;
		col=0;
		ArrayList<ArrayList<Object>> data = export.getData();
		for(int i=0;i<data.size();i++){
			ArrayList<Object> rowData = data.get(i);
			for(int j=0;j<rowData.size();j++){
				if(rowData.get(j) instanceof String){
					s.addCell(new Label( col++, row, (String)rowData.get(j), dtFmtC));
				}else if(rowData.get(j) instanceof Integer){
					s.addCell(new Number( col++, row, ((Integer)rowData.get(j)).intValue(), nfFmt));
				}else if(rowData.get(j) instanceof Double){
					s.addCell(new Number( col++, row, ((Double)rowData.get(j)).doubleValue(), nfFmt));
				}else if(rowData.get(j) instanceof Date){
					WritableCellFormat datetFmt;
					if(!"".equals(export.getFormat()[col])){
						datetFmt = new WritableCellFormat(new DateFormat(export.getFormat()[col]));
					}else{
						datetFmt = new WritableCellFormat(dfmt);
					}
					s.addCell(new DateTime( col++, row, (Date)rowData.get(j), datetFmt));
				}else{
					s.addCell(new Label( col++, row, (String)rowData.get(j), dtFmtC));
				}
			}
			row++;
			col=0;
		}
	}
}