package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * 거래처 마스터
 * @author OBERAK
 */
public class TsCstMstDTO extends BaseObject {
    private static final long serialVersionUID = 883877737611367380L;
	/** 거래처 코드 */
	private String cst_cd;
	/** 거래처 관리 코드 */
	private String cst_mgt_cd;
	/** 거래처 명 */
	private String cst_nm;
	/** 대표자 명 */
	private String rpr_nm;
	/** 연락처 */
	private String tel;
	/** 비고 */
	private String nt;
	/** 관리 여부 */
	private String mgt_yn;
	
    private int no;
    private String search;
    
	public String getCst_cd() {
    	return cst_cd;
    }
	public void setCst_cd(String cst_cd) {
    	this.cst_cd = cst_cd;
    }
	public String getCst_mgt_cd() {
    	return cst_mgt_cd;
    }
	public void setCst_mgt_cd(String cst_mgt_cd) {
    	this.cst_mgt_cd = cst_mgt_cd;
    }
	public String getCst_nm() {
    	return cst_nm;
    }
	public void setCst_nm(String cst_nm) {
    	this.cst_nm = cst_nm;
    }
	public String getRpr_nm() {
    	return rpr_nm;
    }
	public void setRpr_nm(String rpr_nm) {
    	this.rpr_nm = rpr_nm;
    }
	public String getTel() {
    	return tel;
    }
	public void setTel(String tel) {
    	this.tel = tel;
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
