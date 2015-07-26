package com.casmall.dts.biz.domain;

import java.util.Date;

import com.casmall.common.BaseObject;

/**
 * 계량 정보
 * @author OBERAK
 */
public class TsWgtInfDTO extends BaseObject {
    private static final long serialVersionUID = 2968442464630756815L;

    /** 계량 코드*/
    private String wgt_cd;
    /** 차량 코드 */
    private String car_cd;
    /** 1차 계량 일시 */
    private Date fst_wgt_dt;
    /** 1차 중량 */
    private double fst_wgh;
    /** 계량 번호 */
    private String wgt_num;
    /** 거래처 코드 */
    private String cst_cd;
    /** 제품 코드 */
    private String prdt_cd;
    /** 2차 중량 일시 */
    private Date scnd_wgt_dt;
    /** 2차 중량 */
    private double scnd_wgh;
    /** 감량 기준 코드 - WT:정량, PT:비율 */
    private String dscnt_bss_cd;
    /** 감량 입력 값 */
    private double dscnt_val;
    /** 감량 */
    private double dscnt;
    /** 실중량 */
    private double rl_wgh;
    /** 입출고 구분 */
    private String io_flg;
    /** 비고 */
    private String nt;
    /** 계량 구분 코드 - 10:일반계량, 30:일회계량 */
    private String wgt_flg_cd;
    /** 계량 상태 코드 - 10:1차 계량, 50:계량 완료 */
    private String wgt_stat_cd;
    
    // 조회용 필드 -------------------------
    /** No */
    private int no;
    /** 차량 번호 */
    private String car_num;
    /** 제품 명 */
    private String prdt_nm;
    /** 거래처 명 */
    private String cst_nm;
    /** 입출고 구분 */
    private String io_flg_nm;
    /** 수정자명 */
    private String edt_nm;
    
    // --------- 현재 미사용 ---------------------
    /** 1차 중량 이미지 path */
    private String fst_wgt_image_path;
    /** 2차 중량 이미지 path */
    private String scnd_wgt_image_path;
    /** 카드 코드 */
    private String card_cd;
    /** 단가 */
    private int unt_prc;
    /** 금액 */
    private int amt;
    /** 운전자 */
    private String drvr;
    /** 기타 1*/
    private String ext_1;
    /** 기타 2*/
    private String ext_2;
    /** 기타 3*/
    private String ext_3;
    /** 기타 4*/
    private String ext_4;
    /** 기타 5*/
    private String ext_5;
    /** 기타 6*/
    private String ext_6;
    
	public Date getFst_wgt_dt() {
    	return fst_wgt_dt;
    }
	public String getWgt_cd() {
    	return wgt_cd;
    }
	public String getCar_cd() {
    	return car_cd;
    }
	public void setCar_cd(String car_cd) {
    	this.car_cd = car_cd;
    }
	public String getCst_cd() {
    	return cst_cd;
    }
	public void setCst_cd(String cst_cd) {
    	this.cst_cd = cst_cd;
    }
	public String getPrdt_cd() {
    	return prdt_cd;
    }
	public void setPrdt_cd(String prdt_cd) {
    	this.prdt_cd = prdt_cd;
    }
	public void setWgt_cd(String wgt_cd) {
    	this.wgt_cd = wgt_cd;
    }
	public void setFst_wgt_dt(Date fst_wgt_dt) {
    	this.fst_wgt_dt = fst_wgt_dt;
    }
	public double getFst_wgh() {
    	return fst_wgh;
    }
	public void setFst_wgh(double fst_wgh) {
    	this.fst_wgh = fst_wgh;
    }
	public String getWgt_num() {
    	return wgt_num;
    }
	public void setWgt_num(String wgt_num) {
    	this.wgt_num = wgt_num;
    }
	public Date getScnd_wgt_dt() {
    	return scnd_wgt_dt;
    }
	public void setScnd_wgt_dt(Date scnd_wgt_dt) {
    	this.scnd_wgt_dt = scnd_wgt_dt;
    }
	public double getScnd_wgh() {
    	return scnd_wgh;
    }
	public void setScnd_wgh(double scnd_wgh) {
    	this.scnd_wgh = scnd_wgh;
    }
	public String getDscnt_bss_cd() {
    	return dscnt_bss_cd;
    }
	public void setDscnt_bss_cd(String dscnt_bss_cd) {
    	this.dscnt_bss_cd = dscnt_bss_cd;
    }
	public double getDscnt_val() {
    	return dscnt_val;
    }
	public void setDscnt_val(double dscnt_val) {
    	this.dscnt_val = dscnt_val;
    }
	public double getDscnt() {
    	return dscnt;
    }
	public void setDscnt(double dscnt) {
    	this.dscnt = dscnt;
    }
	public double getRl_wgh() {
    	return rl_wgh;
    }
	public void setRl_wgh(double rl_wgh) {
    	this.rl_wgh = rl_wgh;
    }
	public String getIo_flg() {
    	return io_flg;
    }
	public void setIo_flg(String io_flg) {
    	this.io_flg = io_flg;
    }
	public String getNt() {
    	return nt;
    }
	public void setNt(String nt) {
    	this.nt = nt;
    }
	public String getWgt_flg_cd() {
    	return wgt_flg_cd;
    }
	public void setWgt_flg_cd(String wgt_flg_cd) {
    	this.wgt_flg_cd = wgt_flg_cd;
    }
	public String getWgt_stat_cd() {
    	return wgt_stat_cd;
    }
	public void setWgt_stat_cd(String wgt_stat_cd) {
    	this.wgt_stat_cd = wgt_stat_cd;
    }
	public String getCar_num() {
    	return car_num;
    }
	public void setCar_num(String car_num) {
    	this.car_num = car_num;
    }
	public String getPrdt_nm() {
    	return prdt_nm;
    }
	public void setPrdt_nm(String prdt_nm) {
    	this.prdt_nm = prdt_nm;
    }
	public String getCst_nm() {
    	return cst_nm;
    }
	public void setCst_nm(String cst_nm) {
    	this.cst_nm = cst_nm;
    }
	public int getNo() {
    	return no;
    }
	public void setNo(int no) {
    	this.no = no;
    }
	public String getFst_wgt_image_path() {
    	return fst_wgt_image_path;
    }
	public void setFst_wgt_image_path(String fst_wgt_image_path) {
    	this.fst_wgt_image_path = fst_wgt_image_path;
    }
	public String getScnd_wgt_image_path() {
    	return scnd_wgt_image_path;
    }
	public void setScnd_wgt_image_path(String scnd_wgt_image_path) {
    	this.scnd_wgt_image_path = scnd_wgt_image_path;
    }
	public String getCard_cd() {
    	return card_cd;
    }
	public void setCard_cd(String card_cd) {
    	this.card_cd = card_cd;
    }
	public int getUnt_prc() {
    	return unt_prc;
    }
	public void setUnt_prc(int unt_prc) {
    	this.unt_prc = unt_prc;
    }
	public int getAmt() {
    	return amt;
    }
	public void setAmt(int amt) {
    	this.amt = amt;
    }
	public String getDrvr() {
    	return drvr;
    }
	public void setDrvr(String drvr) {
    	this.drvr = drvr;
    }
	public String getExt_1() {
    	return ext_1;
    }
	public void setExt_1(String ext_1) {
    	this.ext_1 = ext_1;
    }
	public String getExt_2() {
    	return ext_2;
    }
	public void setExt_2(String ext_2) {
    	this.ext_2 = ext_2;
    }
	public String getExt_3() {
    	return ext_3;
    }
	public void setExt_3(String ext_3) {
    	this.ext_3 = ext_3;
    }
	public String getExt_4() {
    	return ext_4;
    }
	public void setExt_4(String ext_4) {
    	this.ext_4 = ext_4;
    }
	public String getExt_5() {
    	return ext_5;
    }
	public void setExt_5(String ext_5) {
    	this.ext_5 = ext_5;
    }
	public String getExt_6() {
    	return ext_6;
    }
	public void setExt_6(String ext_6) {
    	this.ext_6 = ext_6;
    }
	/**
	 * 짐차중량
	 * @return
	 */
	public double getFull_wgh() {
    	return (fst_wgh>scnd_wgh)?fst_wgh:scnd_wgh;
    }
	/** 
	 * 공차중량
	 * @return
	 */
	public double getEmpty_wgh() {
		return (fst_wgh<scnd_wgh)?fst_wgh:scnd_wgh;
    }
	/**
	 * 짐차 중량 일시
	 * @return
	 */
	public Date getFull_wgt_dt() {
		return (fst_wgh>scnd_wgh)?fst_wgt_dt:scnd_wgt_dt;
    }
	/** 
	 * 공차중량 일시
	 * @return
	 */
	public Date getEmpty_wgt_dt() {
		return (fst_wgh<scnd_wgh)?fst_wgt_dt:scnd_wgt_dt;
    }
	public String getIo_flg_nm() {
    	return io_flg_nm;
    }
	public void setIo_flg_nm(String io_flg_nm) {
    	this.io_flg_nm = io_flg_nm;
    }
	public String getEdt_nm() {
    	return edt_nm;
    }
	public void setEdt_nm(String edt_nm) {
    	this.edt_nm = edt_nm;
    }
}
