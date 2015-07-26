package com.casmall.dts.ui.weigh.compsite;

import org.eclipse.swt.graphics.Font;
// TODO LOW FieldIF 상속 및 코드 정리
public interface FieldIF {
	public static final String KEY_ID = "ID";
	public static final String KEY_VAL = "VAL";

	public void setUserFont(Font... font);
	public void setTitle(String title);
	public boolean setFocus();
	public String getValue(String key);
	public boolean setValue(String... data);
	public void loadData(Object obj);
	public void setEditable(boolean editable);
	public void setTextLimit(int limit);
}