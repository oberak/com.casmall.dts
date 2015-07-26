package com.casmall.dts.biz.domain;

import java.util.ArrayList;

import com.casmall.common.BaseObject;

public class HomeViewInfoVO extends BaseObject{
    private static final long serialVersionUID = -5047052189870029643L;
    /** 계류차량 수 */
	private int fstCnt;
	/** 계류차량 목록 */
	private ArrayList<TsWgtInfDTO> fstList;
	/** 당일 계량 수 */
	private int scdCnt;
	/** 당일 계량 합계 */
	private double scdWeigh;
	/** 당일 계량 목록 */
	private ArrayList<TsWgtInfDTO> scdList;
	/** 월 계량 수 */
	private int monthCnt;
	/** 월 계량 합계 */
	private double monthWeigh;
	
	public int getFstCnt() {
    	return fstCnt;
    }
	public void setFstCnt(int fstCnt) {
    	this.fstCnt = fstCnt;
    }
	public ArrayList<TsWgtInfDTO> getFstList() {
    	return fstList;
    }
	public void setFstList(ArrayList<TsWgtInfDTO> fstList) {
    	this.fstList = fstList;
    }
	public int getScdCnt() {
    	return scdCnt;
    }
	public void setScdCnt(int scdCnt) {
    	this.scdCnt = scdCnt;
    }
	public double getScdWeigh() {
    	return scdWeigh;
    }
	public void setScdWeigh(double scdWeigh) {
    	this.scdWeigh = scdWeigh;
    }
	public ArrayList<TsWgtInfDTO> getScdList() {
    	return scdList;
    }
	public void setScdList(ArrayList<TsWgtInfDTO> scdList) {
    	this.scdList = scdList;
    }
	public int getMonthCnt() {
    	return monthCnt;
    }
	public void setMonthCnt(int monthCnt) {
    	this.monthCnt = monthCnt;
    }
	public double getMonthWeigh() {
    	return monthWeigh;
    }
	public void setMonthWeigh(double monthWeigh) {
    	this.monthWeigh = monthWeigh;
    }
}
