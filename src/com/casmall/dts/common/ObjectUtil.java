package com.casmall.dts.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.casmall.common.BaseObject;
import com.casmall.common.StringUtil;
import com.casmall.dts.ui.preferences.DTSPreConstants;
import com.casmall.usr.domain.CmUsrInfDTO;
import com.casmall.usr.mgr.SessionManager;

public class ObjectUtil {
	protected static Log logger = LogFactory.getLog(ObjectUtil.class);
	public static final String DEL_FLAG_N = "N";
	protected static ScopedPreferenceStore preferences = new ScopedPreferenceStore(ConfigurationScope.INSTANCE, DTSConstants.PLUGIN_ID);
	
	public static BaseObject getDefaultObject(String className){
		BaseObject o = null;
		Class<?> obj;
        try {
	        obj = Class.forName(className);
			o = (BaseObject)obj.newInstance();
			SessionManager sm = SessionManager.getInstance();
			CmUsrInfDTO usr = sm.getUsr();
			o.setEdt_dt(new Date());
			o.setRgn_dt(new Date());
			o.setEdt_id(usr.getLgn_id());
			o.setRgn_id(usr.getLgn_id());
			o.setDel_yn(DEL_FLAG_N);
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        } catch (InstantiationException e) {
	        e.printStackTrace();
        } catch (IllegalAccessException e) {
	        e.printStackTrace();
        }
		return o;
	}
	
	public static BaseObject getUpdateObject(BaseObject o){
		SessionManager sm = SessionManager.getInstance();
		CmUsrInfDTO usr = sm.getUsr();
		o.setEdt_dt(new Date());
		o.setEdt_id(usr.getLgn_id());
		return o;
	}
	
	/**
	 * 계량 코드 얻기
	 * @return
	 */
	public static String nextWeighCd(){
		String wgtCd = "";
		String cdType = preferences.getString(DTSPreConstants.DATA_WEIGH_CD_TYPE);
		int seqLen = preferences.getInt(DTSPreConstants.DATA_WEIGH_CD_LENGTH);
		int currSeq = preferences.getInt(DTSPreConstants.DATA_WEIGH_CD);
		String prefix = "";
		
		boolean withHyphen = preferences.getBoolean(DTSPreConstants.DATA_WEIGH_CD_WITH_HYPHEN);
		if( ! DTSPreConstants.DATA_WEIGH_CD_TYPE_NONE.equals(cdType) ){
			prefix = StringUtil.getDate(cdType);
			if(withHyphen)
				prefix += "-";
		}else{
		}// if
		currSeq = nextWeighSeq();
		
		wgtCd += StringUtil.padZero(currSeq, seqLen);
		
		return prefix+wgtCd;
	}
	
	public static int nextWeighSeq(){
		String cdType = preferences.getString(DTSPreConstants.DATA_WEIGH_CD_TYPE);
		String prefix = "";
		prefix = StringUtil.getDate(cdType);
		int currSeq = preferences.getInt(DTSPreConstants.DATA_WEIGH_CD);
		int seqLen = preferences.getInt(DTSPreConstants.DATA_WEIGH_CD_LENGTH);

		String lastSeq = preferences.getString(DTSPreConstants.DATA_WEIGH_CD_NEXT);
		if( ! DTSPreConstants.DATA_WEIGH_CD_TYPE_NONE.equals(cdType) ){
			// 마지막 일련번호와 다를 경우 일련번호 초기화
			if(!lastSeq.startsWith(prefix)){
				return 1;
			}
		}else{
			// 자리수를 초과할 경우 초기화
			if(currSeq >= Math.pow(10, seqLen) ){
				currSeq = 1;
			}
		}// if
		return preferences.getInt(DTSPreConstants.DATA_WEIGH_CD);
	}
	
	public static String nextWeighCd(String cdType, int seqLen, int currSeq, boolean withHyphen){
		String wgtCd = "";
		String prefix = "";
		if( ! DTSPreConstants.DATA_WEIGH_CD_TYPE_NONE.equals(cdType) ){
			prefix = StringUtil.getDate(cdType);
			// 마지막 일련번호와 다를 경우 일련번호 초기화
			if(withHyphen)
				prefix += "-";
		}else{
			// 자리수를 초과할 경우 초기화
			if(currSeq >= Math.pow(10, seqLen) ){
				currSeq = 1;
			}
		}// if
		
		wgtCd += StringUtil.padZero(currSeq, seqLen);
		
		return prefix+wgtCd;
	}
	
	public static Object[] setSeq(Object[] list){
		if(list == null)
			return null;
		for(int i=0; i<list.length;i++){
			try {
	            Method methods = list[i].getClass().getMethod("setNo", int.class);
	            methods.invoke(list[i], new Integer(i+1));
            } catch (SecurityException e) {
	            e.printStackTrace();
            } catch (NoSuchMethodException e) {
	            e.printStackTrace();
            } catch (IllegalArgumentException e) {
	            e.printStackTrace();
            } catch (IllegalAccessException e) {
	            e.printStackTrace();
            } catch (InvocationTargetException e) {
	            e.printStackTrace();
            }
		}
		return list;
	}
}
