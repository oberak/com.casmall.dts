package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * ��ǰ ������
 * @author OBERAK
 */
public class TsPrdtMstDTO extends BaseObject {
    private static final long serialVersionUID = 593222612229022508L;
	/** ��ǰ �ڵ� */
	private String prdt_cd;
	/** ��ǰ ���� �ڵ� */
	private String prdt_mgt_cd;
	/** ��ǰ �� */
	private String prdt_nm;
	/** ��� */
	private String nt;
	/** ���� ���� */
	private String mgt_yn;
	
    private int no;
    private String search;
    
	/** �ܰ� */
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
