package com.casmall.dts.common;

public interface DTSConstants {
	public static final String PLUGIN_ID = "com.casmall.dts";
	
	/** �跮 ���� �ڵ�: �Ϲݰ跮 */
	public static final String WGT_FLAG_GEN = "10";
	/** �跮 ���� �ڵ�: 1ȸ�跮 */
	public static final String WGT_FLAG_ONE = "30";
	/** �跮 ���� �ڵ�: 1�� �跮*/
	public static final String WGT_STAT_FST = "10";
	/** �跮 ���� �ڵ�: 2�� �跮 */
	public static final String WGT_STAT_SCND = "50";
	
	/** ����� �����ڵ� */
	public static final String CD_INOUT_IN = "10";
	public static final String CD_INOUT_OUT = "30";
	
	/** ���� ���� �ڵ� */
	public static final String CD_MINUS_PCT = "P";
	public static final String CD_MINUS_KG = "K";
	
	/** ��� �׸� ���� �ڵ� */
	public static final String CD_ATTR_FLAG_DB = "DB";
	public static final String CD_ATTR_FLAG_TEXT = "TX";
	public static final String CD_ATTR_FLAG_FIX = "FX";
	public static final String CD_ATTR_FLAG_BOX = "BO";
	public static final String CD_ATTR_FLAG_LINE = "LN";
	public static final String CD_ATTR_FLAG_IMAGE = "IM";
	public static final String CD_ATTR_FLAG_QRCODE = "QR";
	
	/** ������ Ÿ�� �ڵ� */
	public static final String CD_DATA_TYPE_NUM = "NM";
	public static final String CD_DATA_TYPE_DATE = "DT";
	public static final String CD_DATA_TYPE_STR = "ST";
	
	/** �跮��ȣ ä�� ���� */
	public static final String WGT_SEQ_YYYY = "yyyy";
	public static final String WGT_SEQ_MM = "yyyyMM";
	public static final String WGT_SEQ_DD = "yyyyMMdd";
	
	public static final String FLAG_Y = "Y";
	public static final String FLAG_N = "N";
	
	/** ���� �ڵ� */
	public static final String CD_SUP_INF = "SUP_INF";
	public static final String CD_CUST_INF = "CUST_INF";
	public static final String CD_BSS_CONF = "BSS_CONF";
	
	/** ����� ��� */
	public static final String CD_USR_GRD = "ATH_GRD";
	public static final String CD_USR_GRD_USER = "10";
	public static final String CD_USR_GRD_ADM = "30";
	public static final String CD_USR_GRD_ROOT = "50";
	
	/** ���� */
	public static final int AUTH_EDT_FST_DT   = 1<<1; // 1�� �跮�Ͻ�
	public static final int AUTH_EDT_FST_WGH  = 1<<2; // 1�� �߷�
	public static final int AUTH_EDT_SCND_DT  = 1<<3; // 2�� �跮����
	public static final int AUTH_EDT_SCND_WGH = 1<<4; // 2�� �߷�
	public static final int AUTH_EDT_CAR      = 1<<5; // ����
	public static final int AUTH_EDT_MINUS    = 1<<6; // ����
	public static final int AUTH_EDT_PRICE    = 1<<7; // �ܰ�
	public static final int AUTH_EDT_SCND      = 1<<8; // 2�� �跮 ����
	
	public static final int AUTH_INS_ONE      = 1<<9; // 1ȸ�跮 ��� 
	
	public static final int AUTH_DEL_FST      = 1<<10; // 1�� ���� ����
	public static final int AUTH_DEL_SCND     = 1<<11; // 2�� ���� ����
}