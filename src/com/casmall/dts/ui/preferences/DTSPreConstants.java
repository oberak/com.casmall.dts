package com.casmall.dts.ui.preferences;

public interface DTSPreConstants {
	/** general - 일반 */
	public static final String GN_PRINT_CONFIRM = "general.print.confirm";
	public static final String GN_PRINT_COUNT = "general.print.count";
	public static final String GN_PRINT_FORM = "general.print.form";
	
	/** general - login */
	public static final String GN_AUTO_LOGIN_FLAG = "general.auto_login.flag";
	public static final String GN_AUTO_LOGIN_ID = "general.auto_login.id";
	public static final String GN_AUTO_LOGIN_PW = "general.auto_login.pw";

	/** general - Home */
	public static final String GN_HOME_SUMMARY = "general.home.summary";
	public static final String GN_HOME_SCND = "general.home.scnd";

	/** Data - 감량 */
	public static final String DATA_MINUS_FLAG = "data.minus.flag";
	public static final String DATA_MINUS_POINT = "data.minus.point";
	public static final String DATA_MINUS_TYPE = "data.minus.type";
	public static final String DATA_MINUS_TYPE_ROUND = "ROUND";
	public static final String DATA_MINUS_TYPE_FLOOR = "FLOOR";
	public static final String DATA_MINUS_TYPE_CEIL = "CEIL";
	public static final String DATA_MINUS_DEFAULT = "data.minus.default";
	
	/** Data - 거래처 */
	public static final String DATA_CUST_FLAG = "data.cust.flag";
	public static final String DATA_CUST_MUST = "data.cust.must";
	
	/** Data - 제품 */
	public static final String DATA_PRDT_FLAG = "data.prdt.flag";
	public static final String DATA_PRDT_MUST = "data.prdt.must";
	
	/** Data - 단가 */
	public static final String DATA_PRICE_FLAG = "data.price.flag";
	public static final String DATA_PRICE_MUST = "data.price.must";
	public static final String DATA_PRICE_LOAD = "data.price.load";
	public static final String DATA_AMOUNT_POINT = "data.amount.point";
	public static final String DATA_AMOUNT_TYPE = "data.amount.type";
	
	public static final String DATA_WEIGH_CD = "data.weigh_cd";
	public static final String DATA_WEIGH_CD_TYPE = "data.weigh_cd.type";
	public static final String DATA_WEIGH_CD_TYPE_NONE = "NONE";
	public static final String DATA_WEIGH_CD_TYPE_YYYY = "yyyy";
	public static final String DATA_WEIGH_CD_TYPE_YY = "yy";
	public static final String DATA_WEIGH_CD_TYPE_YYYY_MM = "yyyy-MM";
	public static final String DATA_WEIGH_CD_TYPE_YYYYMM = "yyyyMM";
	public static final String DATA_WEIGH_CD_TYPE_YY_MM = "yy-MM";
	public static final String DATA_WEIGH_CD_TYPE_YYMM = "yyMM";
	public static final String DATA_WEIGH_CD_TYPE_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATA_WEIGH_CD_TYPE_YYYYMMDD = "yyyyMMdd";
	public static final String DATA_WEIGH_CD_TYPE_YY_MM_DD = "yy-MM-dd";
	public static final String DATA_WEIGH_CD_TYPE_YYMMDD = "yyMMdd";
	public static final String DATA_WEIGH_CD_WITH_HYPHEN = "data.weigh_cd.with_hyphen";
	public static final String DATA_WEIGH_CD_LENGTH = "data.weigh_cd.length";
	public static final String DATA_WEIGH_CD_NEXT = "data.weigh_cd.next";
	
	/** 기타 */
	public static final String GN_EXT_AUTH_SERIAL = "ext.auth.serial";
	public static final String GN_EXT_COM_ADD = "ext.com.add";
	public static final String GN_EXT_COM_NAME = "ext.com.name";
	public static final String GN_EXT_COM_TEL = "ext.com.tel";
	public static final String GN_EXT_COM_FAX = "ext.com.fax";
}