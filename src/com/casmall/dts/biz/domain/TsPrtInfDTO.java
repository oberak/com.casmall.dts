package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * 출력정보 DTO
 * @author OBERAK
 */
public class TsPrtInfDTO extends BaseObject {
    private static final long serialVersionUID = 4655794995021275637L;
    /** 일련번호 key */
    private long prt_seq;
    /** 설명 */
    private String prt_nm;
    /** 용지 너비 : CM */
    private double paper_width;
    /** 용지 높이 : CM */
    private double paper_height;
    /** 기준좌표 x : CM */
    private double bss_cdnt_x;
    /** 기준좌표 y : CM */
    private double bss_cdnt_y;
    /** 가로 출력 여부 */
    private String wdt_prt_yn;
    /** 기본 폰트 */
    private String bss_font;
    
	public long getPrt_seq() {
    	return prt_seq;
    }
	public void setPrt_seq(long prt_seq) {
    	this.prt_seq = prt_seq;
    }
	public String getPrt_nm() {
    	return prt_nm;
    }
	public void setPrt_nm(String prt_nm) {
    	this.prt_nm = prt_nm;
    }
	public double getPaper_width() {
    	return paper_width;
    }
	public void setPaper_width(double paper_width) {
    	this.paper_width = paper_width;
    }
	public double getPaper_height() {
    	return paper_height;
    }
	public void setPaper_height(double paper_height) {
    	this.paper_height = paper_height;
    }
	public double getBss_cdnt_x() {
    	return bss_cdnt_x;
    }
	public void setBss_cdnt_x(double bss_cdnt_x) {
    	this.bss_cdnt_x = bss_cdnt_x;
    }
	public double getBss_cdnt_y() {
    	return bss_cdnt_y;
    }
	public void setBss_cdnt_y(double bss_cdnt_y) {
    	this.bss_cdnt_y = bss_cdnt_y;
    }
	public String getWdt_prt_yn() {
    	return wdt_prt_yn;
    }
	public void setWdt_prt_yn(String wdt_prt_yn) {
    	this.wdt_prt_yn = wdt_prt_yn;
    }
	public String getBss_font() {
    	return bss_font;
    }
	public void setBss_font(String bss_font) {
    	this.bss_font = bss_font;
    }
}
