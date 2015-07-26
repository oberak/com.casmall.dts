package com.casmall.dts.common;

public interface DTSConstants {
	public static final String PLUGIN_ID = "com.casmall.dts";
	
	/** 계량 구분 코드: 일반계량 */
	public static final String WGT_FLAG_GEN = "10";
	/** 계량 구분 코드: 1회계량 */
	public static final String WGT_FLAG_ONE = "30";
	/** 계량 상태 코드: 1차 계량*/
	public static final String WGT_STAT_FST = "10";
	/** 계량 상태 코드: 2차 계량 */
	public static final String WGT_STAT_SCND = "50";
	
	/** 입출고 구분코드 */
	public static final String CD_INOUT_IN = "10";
	public static final String CD_INOUT_OUT = "30";
	
	/** 감량 기준 코드 */
	public static final String CD_MINUS_PCT = "P";
	public static final String CD_MINUS_KG = "K";
	
	/** 출력 항목 구분 코드 */
	public static final String CD_ATTR_FLAG_DB = "DB";
	public static final String CD_ATTR_FLAG_TEXT = "TX";
	public static final String CD_ATTR_FLAG_FIX = "FX";
	public static final String CD_ATTR_FLAG_BOX = "BO";
	public static final String CD_ATTR_FLAG_LINE = "LN";
	public static final String CD_ATTR_FLAG_IMAGE = "IM";
	
	/** 데이터 타입 코드 */
	public static final String CD_DATA_TYPE_NUM = "NM";
	public static final String CD_DATA_TYPE_DATE = "DT";
	public static final String CD_DATA_TYPE_STR = "ST";
	
	/** 계량번호 채번 구분 */
	public static final String WGT_SEQ_YYYY = "yyyy";
	public static final String WGT_SEQ_MM = "yyyyMM";
	public static final String WGT_SEQ_DD = "yyyyMMdd";
	
	public static final String FLAG_Y = "Y";
	public static final String FLAG_N = "N";
	
	/** 공통 코드 */
	public static final String CD_SUP_INF = "SUP_INF";
	public static final String CD_CUST_INF = "CUST_INF";
	public static final String CD_BSS_CONF = "BSS_CONF";
	
	/** 사용자 등급 */
	public static final String CD_USR_GRD = "ATH_GRD";
	public static final String CD_USR_GRD_USER = "10";
	public static final String CD_USR_GRD_ADM = "30";
	public static final String CD_USR_GRD_ROOT = "50";
	
	/** 권한 */
	public static final int AUTH_EDT_FST_DT   = 1<<1; // 1차 계량일시
	public static final int AUTH_EDT_FST_WGH  = 1<<2; // 1차 중량
	public static final int AUTH_EDT_SCND_DT  = 1<<3; // 2차 계량일자
	public static final int AUTH_EDT_SCND_WGH = 1<<4; // 2차 중량
	public static final int AUTH_EDT_CAR      = 1<<5; // 차량
	public static final int AUTH_EDT_MINUS    = 1<<6; // 감량
	public static final int AUTH_EDT_PRICE    = 1<<7; // 단가
	public static final int AUTH_EDT_SCND      = 1<<8; // 2차 계량 수정
	
	public static final int AUTH_INS_ONE      = 1<<9; // 1회계량 등록 
	
	public static final int AUTH_DEL_FST      = 1<<10; // 1차 정보 삭제
	public static final int AUTH_DEL_SCND     = 1<<11; // 2차 정보 삭제
}