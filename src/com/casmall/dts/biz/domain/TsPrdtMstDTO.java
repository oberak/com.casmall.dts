package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * 力前 付胶磐
 * @author OBERAK
 */
public class TsPrdtMstDTO extends BaseObject {
    private static final long serialVersionUID = 593222612229022508L;
	/** 力前 内靛 */
	private String prdt_cd;
	/** 力前 包府 内靛 */
	private String prdt_mgt_cd;
	/** 力前 疙 */
	private String prdt_nm;
	/** 厚绊 */
	private String nt;
	/** 包府 咯何 */
	private String mgt_yn;
	
    private int no;
    private String search;
    
	/** 窜啊 */
	private int unt_prc;
	
	public String getPrdt_cd() {
    	return prdt_cd;
    }
	public void setPrdt_cd(String prdt_cd) {
    	this.prdt_cd = prdt_cd;
    }
	public String getPrdt_mgt_cd() {
    	return prdt_mgt_cd;
    }
	public void setPrdt_mgt_cd(String prdt_mgt_cd) {
    	this.prdt_mgt_cd = prdt_mgt_cd;
    }
	public String getPrdt_nm() {
    	return prdt_nm;
    }
	public void setPrdt_nm(String prdt_nm) {
    	this.prdt_nm = prdt_nm;
    }
	public String getNt() {
    	return nt;
    }
	public void setNt(String nt) {
    	this.nt = nt;
    }
	public String getMgt_yn() {
    	return mgt_yn;
    }
	public void setMgt_yn(String mgt_yn) {
    	this.mgt_yn = mgt_yn;
    }
	public int getUnt_prc() {
    	return unt_prc;
    }
	public void setUnt_prc(int unt_prc) {
    	this.unt_prc = unt_prc;
    }
	public int getNo() {
    	return no;
    }
	public void setNo(int no) {
    	this.no = no;
    }
	public String getSearch() {
    	return search;
    }
	public void setSearch(String search) {
    	this.search = search;
    }
}
