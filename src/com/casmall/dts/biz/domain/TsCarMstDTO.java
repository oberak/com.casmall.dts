package com.casmall.dts.biz.domain;

import com.casmall.common.BaseObject;

/**
 * ���� ������
 * @author OBERAK
 */
public class TsCarMstDTO extends BaseObject {
    private static final long serialVersionUID = -3202946365455705562L;
    
    /** ���� �ڵ� */
	private String car_cd;
	/** ���� ���� �ڵ� */
	private String car_mgt_cd;
	/** ���� ��ȣ */
	private String car_num;
	/** ��� */
	private String nt;
	/** ���� ���� */
	private String mgt_yn;

    private int no;
    private String search;
	
	// --------- ���� �̻�� ---------------------
	/** ������ */
	private String drvr;
	/** �����߷� */
	private String emtcar_wgh;
	/** ��ǰ �ڵ� */
	private String prdt_cd;
	/** �ŷ�ó �ڵ� */
	private String cst_cd;
	
	public String getCar_cd() {
    	return car_cd;
    }
	public void setCar_cd(String car_cd) {
    	this.car_cd = car_cd;
    }
	public String getCar_mgt_cd() {
    	return car_mgt_cd;
    }
	public void setCar_mgt_cd(String car_mgt_cd) {
    	this.car_mgt_cd = car_mgt_cd;
    }
	public String getCar_num() {
    	return car_num;
    }
	public void setCar_num(String car_num) {
    	this.car_num = car_num;
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
	public String getDrvr() {
    	return drvr;
    }
	public void setDrvr(String drvr) {
    	this.drvr = drvr;
    }
	public String getEmtcar_wgh() {
    	return emtcar_wgh;
    }
	public void setEmtcar_wgh(String emtcar_wgh) {
    	this.emtcar_wgh = emtcar_wgh;
    }
	public String getPrdt_cd() {
    	return prdt_cd;
    }
	public void setPrdt_cd(String prdt_cd) {
    	this.prdt_cd = prdt_cd;
    }
	public String getCst_cd() {
    	return cst_cd;
    }
	public void setCst_cd(String cst_cd) {
    	this.cst_cd = cst_cd;
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
