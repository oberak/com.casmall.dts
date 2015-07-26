package com.casmall.dts.common;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.biz.domain.CmCdDTO;
import com.casmall.biz.mgr.CommonManager;
import com.casmall.common.CryptoUtil;
import com.casmall.common.StringUtil;
import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsPrtManager;
import com.casmall.dts.print.PBox;
import com.casmall.dts.print.PDocument;
import com.casmall.dts.print.PLine;
import com.casmall.dts.print.PTextBox;
import com.casmall.dts.ui.preferences.DTSPreConstants;

public class PrintUtil {
	protected static Log logger = LogFactory.getLog(PrintUtil.class);
	protected static ScopedPreferenceStore preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
	/**
	 * String(red|green|blue)을 RGB로 변환
	 * @param src Pipe(|)로 구분된 문자열
	 * @return RGB
	 */
	public static RGB getRGB(String src){
		ArrayList<Double> rgb = splitWithPipe(src);
		if("".equals(src)){
			return new RGB(0,0,0);
		}
		if(rgb.size() != 3){
			if(logger.isErrorEnabled())
				logger.error("Color split error : "+rgb.size());
			return new RGB(0,0,0);
		}
		return new RGB(rgb.get(0).intValue(), rgb.get(1).intValue(), rgb.get(2).intValue());
	}
	
	/**
	 * 
	 * @param src Pipe(|)로 구분된 문자열
	 * @return Rectangle
	 */
	/**
	 * String(x|y|width|height)을 Rectangle로 변환
	 * 
	 * @param src
	 * @param pixelCm pixel 변환 기준
	 * @return
	 */
	public static Rectangle getRectangle(String src, Point pixelCm){
		ArrayList<Double> area = splitWithPipe(src);
		if(area.size() != 4){
			if(logger.isErrorEnabled())
				logger.error("Area split error : "+area.size());
			return new Rectangle(0,0,0,0);
		}
		return new Rectangle((int)(area.get(0)*pixelCm.x), (int)(area.get(1)*pixelCm.y), (int)(area.get(2)*pixelCm.x), (int)(area.get(3)*pixelCm.y));
	}
	
	/**
	 * Pipe(|)로 구분된 문자열을 Double array로 변환
	 * @param src Pipe(|)로 구분된 문자열
	 * @return Double ArrayList
	 */
	public static ArrayList<Double> splitWithPipe(String src){
		ArrayList<Double> rtn = new ArrayList<Double>();
		if(src != null && !"".equals(src.trim())){
			String[] split = src.trim().split("\\|");
			for(String tmp:split){
				rtn.add(Double.parseDouble(tmp));
			}
		}
		
		return rtn;
	}
	
	public static void print(TsWgtInfDTO data){
		print(data, 1);
	}
	
	public static void print(TsWgtInfDTO data, int prtCnt){
		print(null, data,  prtCnt);
	}
	
	/**
	 * print
	 * 
	 * @param prtSeq
	 * @param data
	 */
	public static void print(Printer printer, TsWgtInfDTO data, int prtCnt){
		long prtSeq = preferences.getLong(DTSPreConstants.GN_PRINT_FORM);
		TsPrtManager tpm = new TsPrtManager();
		CommonManager cm = CommonManager.getInstance();
		ArrayList<CmCdDTO> list = cm.selectCmCd(null);
		TsPrtInfDTO prtDto = new TsPrtInfDTO();
		prtDto.setPrt_seq(prtSeq);
		
		ArrayList<TsPrtInfDTO> prtList = tpm.selectTsPrtInf(prtDto);
		if(prtList.size()>0){
			prtDto = prtList.get(0);
		}else{
			if(logger.isErrorEnabled())
				logger.error("prt list size is wrong - "+prtList.size());
		}
		TsPrtAttrDTO atrDto = new TsPrtAttrDTO();
		atrDto.setPrt_seq(prtSeq);
		atrDto.setPrt_yn(DTSConstants.FLAG_Y);
		ArrayList<TsPrtAttrDTO> atrList = tpm.selectTsPrtAttr(atrDto);
		
		PDocument doc = new PDocument(prtDto.getPrt_nm());
		RGB fontColor = new RGB(0,0,0);
		RGB lineColor = new RGB(0,0,0);
		RGB backColor = new RGB(255,255,255);
		double thickness = 0.0;
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
			if(dto.getFont() != null && !"".equals(dto.getFont().trim())){
				fontData = dto.getFont();
			}
			thickness = dto.getTkn();
			fontColor = getRGB(dto.getFont_color());
			lineColor = getRGB(dto.getLine_color());
			backColor = getRGB(dto.getBg_color());
			
			if(DTSConstants.CD_ATTR_FLAG_DB.equals(dto.getAttr_flg_cd())){
				prtStr = getData(data, dto.getAttr_cd(), dto.getData_fmt());
				new PTextBox(doc, dto.getStyle(), area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, fontColor, lineColor, backColor, thickness);
			}else if(DTSConstants.CD_ATTR_FLAG_FIX.equals(dto.getAttr_flg_cd())){
				prtStr = getData(list, dto.getAttr_cd(), dto.getData_fmt());
				
				new PTextBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, fontColor, lineColor, backColor, thickness);
			}else if(DTSConstants.CD_ATTR_FLAG_TEXT.equals(dto.getAttr_flg_cd())){
				new PTextBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), prtStr, fontData, fontColor, lineColor, backColor, thickness);
			}else if(DTSConstants.CD_ATTR_FLAG_BOX.equals(dto.getAttr_flg_cd())){
				new PBox(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), area.get(2), area.get(3), thickness, lineColor, backColor);
			}else if(DTSConstants.CD_ATTR_FLAG_LINE.equals(dto.getAttr_flg_cd())){
				Double x = area.get(2), y=area.get(3);
				if( (dto.getStyle() & SWT.VERTICAL) > 0 ){
					x = area.get(3);
					y=area.get(2);
				}
				new PLine(doc, dto.getStyle(),area.get(0)+prtDto.getBss_cdnt_x(), area.get(1)+prtDto.getBss_cdnt_y(), x, y, lineColor);
			}else if(DTSConstants.CD_ATTR_FLAG_IMAGE.equals(dto.getAttr_flg_cd())){
				// TODO LOW 이미지 출력 구현
			}else{
				if(logger.isErrorEnabled())
					logger.error("field type error:"+dto.getAttr_flg_cd());
			}
		}
		for(int i=0;i<prtCnt;i++){
			if(printer == null){
				// 가로/세로 인쇄 설정
				if(DTSConstants.FLAG_Y.equals(prtDto.getWdt_prt_yn())){
					doc.print(PrinterData.LANDSCAPE);
				}else{
					doc.print(PrinterData.PORTRAIT);
				}
			}else{
				doc.print(printer);
			}
		}// for
	}
	
	/**
	 * 객체에서 데이터 추출
	 * 
	 * @param obj
	 * @param attr
	 * @param fmt
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private static String getData(Object obj, String attr, String fmt){
		Object data = null;
		if(obj instanceof TsWgtInfDTO){
			data = StringUtil.invoke(obj, StringUtil.makeGetter(attr), null);
		}else{
			if("sysdate".equals(attr)){
				data = new Date();
			}else{
				for(CmCdDTO dto : (ArrayList<CmCdDTO>)obj){
					if(attr.equals(dto.getCd_val())){
						try {
	                        data = CryptoUtil.decrypt3DES(dto.getCd_nm());
                        } catch (Exception e) {
	                        e.printStackTrace();
                        }
						break;
					}
				}
			}
		}
		if(data == null){
			if(logger.isWarnEnabled())
				logger.warn("getData error! - "+attr);
			return "";
		}
		return StringUtil.getString(data, fmt);
	}
	
	public static void main(String[] agrs) {
//		System.out.println(getRGB("255|100|200"));
	}
}
