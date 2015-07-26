package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * 출력 항목 DTO
 * @author OBERAK
 */
public class TsPrtAttrDTO extends BaseObject {
    private static final long serialVersionUID = -5995201560606165740L;
    /** 출력 일련번호 key */
    private long prt_seq;
    /** 출력항목 일련번호 key */
    private long attr_seq;
    /** 항목 구분 코드 : DB-DB field, TX-Text, FX-Fixed 항목(sysdate, 공급사 정보 등), BO-Box, LN-Line, IM-Image*/
    private String attr_flg_cd;
    /** 항목 코드 */
    private String attr_cd;
    /** 항목 명 */
    private String attr_nm;
    /** 데이터 타입 코드 : NU-Number, DT-Date, ST-String */
    private String data_type_cd;
    /** 데이터 포멧 */
    private String data_fmt;
    /** 폰트 : 명,Size,Style,Underline,StrikeOut 등 */
    private String font;
    /** 폰트 색상 R|G|B*/
    private String font_color;
    /** 배경 색상 R|G|B*/
    private String bg_color;
    /** 라인 색상 R|G|B*/
    private String line_color;
    /** 영역 : x|y|width|height */
    private String area;
    /** 스타일 */
    private int style;
    /** 두께 */
    private double tkn;
    /** 인쇄 여부 */
    private String prt_yn;
    
	public long getPrt_seq() {
    	return prt_seq;
    }
	public void setPrt_seq(long prt_seq) {
    	this.prt_seq = prt_seq;
    }
	public long getAttr_seq() {
    	return attr_seq;
    }
	public void setAttr_seq(long attr_seq) {
    	this.attr_seq = attr_seq;
    }
	public String getAttr_flg_cd() {
    	return attr_flg_cd;
    }
	public void setAttr_flg_cd(String attr_flg_cd) {
    	this.attr_flg_cd = attr_flg_cd;
    }
	public String getAttr_cd() {
    	return attr_cd;
    }
	public void setAttr_cd(String attr_cd) {
    	this.attr_cd = attr_cd;
    }
	public String getAttr_nm() {
    	return attr_nm;
    }
	public void setAttr_nm(String attr_nm) {
    	this.attr_nm = attr_nm;
    }
	public String getData_type_cd() {
    	return data_type_cd;
    }
	public void setData_type_cd(String data_type_cd) {
    	this.data_type_cd = data_type_cd;
    }
	public String getData_fmt() {
    	return data_fmt;
    }
	public void setData_fmt(String data_fmt) {
    	this.data_fmt = data_fmt;
    }
	public String getFont() {
    	return font;
    }
	public void setFont(String font) {
    	this.font = font;
    }
	public String getFont_color() {
    	return font_color;
    }
	public void setFont_color(String font_color) {
    	this.font_color = font_color;
    }
	public String getBg_color() {
    	return bg_color;
    }
	public void setBg_color(String bg_color) {
    	this.bg_color = bg_color;
    }
	public String getLine_color() {
    	return line_color;
    }
	public void setLine_color(String line_color) {
    	this.line_color = line_color;
    }
	public String getArea() {
    	return area;
    }
	public void setArea(String area) {
    	this.area = area;
    }
	public int getStyle() {
    	return style;
    }
	public void setStyle(int style) {
    	this.style = style;
    }
	public double getTkn() {
    	return tkn;
    }
	public void setTkn(double tkn) {
    	this.tkn = tkn;
    }
	public String getPrt_yn() {
    	return prt_yn;
    }
	public void setPrt_yn(String prt_yn) {
    	this.prt_yn = prt_yn;
    }
}
