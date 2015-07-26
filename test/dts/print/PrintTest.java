package dts.print;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.printing.PrinterData;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsPrtManager;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.PrintUtil;
import com.casmall.dts.print.PBox;
import com.casmall.dts.print.PDocument;
import com.casmall.dts.print.PLine;
import com.casmall.dts.print.PTextBox;

public class PrintTest {
	protected static Log logger = LogFactory.getLog(PrintTest.class);
	public PrintTest() {
		
		
//		text.setFont("굴림체", 20, SWT.BOLD);
//		
//		text.setMargin(0.1);
//		doc.print();
//		PrinterData[] pd = Printer.getPrinterList();
//		for(PrinterData p : pd){
//			System.out.println(p);
//		}
		
//		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//        System.out.println("Number of print services: " + printServices.length);
//        
//        for (PrintService printer : printServices){
//            System.out.println("Printer: " + printer);
//            
//        }
	}

	public void print(long prtSeq, TsWgtInfDTO data){
		TsPrtManager tpm = new TsPrtManager();
		CommonManager cm = CommonManager.getInstance();
		ArrayList<CmCdDTO> list = cm.selectCmCd(null);
		TsPrtInfDTO prtDto = new TsPrtInfDTO();
		prtDto.setPrt_seq(prtSeq);
		ArrayList<TsPrtInfDTO> prtList = tpm.selectTsPrtInf(prtDto);
		if(prtList.size()>0){
			prtDto = prtList.get(0);
//			System.out.println(prtDto);
		}else{
			System.out.println("ERROR : prt list size is wrong - "+prtList.size());
		}
		TsPrtAttrDTO atrDto = new TsPrtAttrDTO();
		atrDto.setPrt_seq(prtSeq);
		ArrayList<TsPrtAttrDTO> atrList = tpm.selectTsPrtAttr(atrDto);
		
		PDocument doc = new PDocument(prtDto.getPrt_nm());
		RGB lineColor = new RGB(0,0,0);
		RGB backColor = new RGB(255,255,255);
		String fontData = prtDto.getBss_font();
		
		for(TsPrtAttrDTO dto : atrList){
			ArrayList<Double> area = StringUtil.splitWithPipe(dto.getArea());
			if(area.size() != 4){
				if(logger.isErrorEnabled())
					logger.error("Print 영역 데이터 이상["+dto.getAttr_nm()+"] - size : "+area.size());
				for(int i= area.size();i<=4;i++){
					area.add(0.0);
				}
			}
			String prtStr = dto.getAttr_cd();
			dto.getFont();
			if(dto.getFont() != null || !"".equals(dto.getFont().trim())){
				fontData = dto.getFont();
			}
			
			if(DTSConstants.CD_ATTR_FLAG_DB.equals(dto.getAttr_flg_cd())){
				prtStr = getData(data, dto.getAttr_cd(), dto.getData_fmt());
				new PTextBox(doc, dto.getStyle(), area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, lineColor, backColor);
			}else if(DTSConstants.CD_ATTR_FLAG_FIX.equals(dto.getAttr_flg_cd())){
				prtStr = getData(list, dto.getAttr_cd(), dto.getData_fmt());
				new PTextBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, lineColor, backColor);
			}else if(DTSConstants.CD_ATTR_FLAG_TEXT.equals(dto.getAttr_flg_cd())){
				new PTextBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, lineColor, backColor);
			}else if(DTSConstants.CD_ATTR_FLAG_BOX.equals(dto.getAttr_flg_cd())){
				new PBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), 0.1, lineColor);
			}else if(DTSConstants.CD_ATTR_FLAG_LINE.equals(dto.getAttr_flg_cd())){
				new PLine(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), lineColor);
			}else if(DTSConstants.CD_ATTR_FLAG_IMAGE.equals(dto.getAttr_flg_cd())){
				// TODO LOW 이미지 출력 구현
			}else{
				System.out.println("field type error:"+dto.getAttr_flg_cd());
			}
		}
		// 가로/세로 인쇄 설정
		if(DTSConstants.FLAG_Y.equals(prtDto.getWdt_prt_yn())){
			doc.print(PrinterData.LANDSCAPE);
		}else{
			doc.print(PrinterData.PORTRAIT);
		}
	}
	
	@SuppressWarnings("unchecked")
    public String getData(Object obj, String attr, String fmt){
		Object data = null;
		if(obj instanceof TsWgtInfDTO){
			data = StringUtil.invoke(obj, StringUtil.makeGetter(attr), null);
		}else{
			if("sysdate".equals(attr)){
				data = new Date();
			}else{
				for(CmCdDTO dto : (ArrayList<CmCdDTO>)obj){
					if(attr.equals(dto.getCd_val())){
						data = dto.getCd_nm();
						break;
					}
				}
			}
		}
		if(data == null){
			return "#N/A";
		}
		return StringUtil.getString(data, fmt);
	}
	
	public void printTest(String name){
		PDocument doc = new PDocument(name);
		ArrayList<Double> area = new ArrayList<Double>();
		area.add(0.0);
		area.add(0.0);
		area.add(100.0);
		area.add(10.0);
		String prtStr = "TEST";
		String fontData = "1|굴림|14.25|1|WINDOWS|1|-19|0|0|0|700|0|1|1|-127|3|2|1|50|굴림";
		RGB fontColor = new RGB(0,0,0);
		RGB lineColor = new RGB(255,0,0);
		RGB backColor = new RGB(0,255,255);
		
		new PTextBox(doc, PBox.LEFT, area.get(0), area.get(1), area.get(2), area.get(3), prtStr, fontData, fontColor, lineColor, backColor, 2);
		new PBox(doc, 0, area.get(0), area.get(1), area.get(2), area.get(3), 0.1, lineColor);
		doc.print(PrinterData.LANDSCAPE);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintTest pt = new PrintTest();
		TsWgtInfDTO dto = new TsWgtInfDTO();
		TsWgtInfManager wim = new TsWgtInfManager();
		dto.setWgt_cd("1");
		ArrayList<TsWgtInfDTO> list = wim.selectTsWgtInf(dto);
		if(list.size() == 1){
			PrintUtil.print(list.get(0));
//			PrintUtil.print(list.get(0));
			pt.print(2, list.get(0));
		}else{
			System.out.println("error!!");
		}
//		pt.printTest("P1");
//		pt.printTest("P2");
		
//		System.out.println(StringUtil.getDate("yy"));
	}

}
