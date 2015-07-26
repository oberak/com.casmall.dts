package com.casmall.dts.ui.common;

public interface CallbackIF {
	public static final String CMD_EDIT = "EDIT";
	public static final String CMD_LIST = "LIST";
	
	public void callback(String cmd, Object obj);
	public void addCallback(CallbackIF callback);
}
