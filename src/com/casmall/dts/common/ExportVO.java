package com.casmall.dts.common;
import java.util.ArrayList;

import com.casmall.common.BaseObject;
import com.casmall.common.StringUtil;

public class ExportVO extends BaseObject {

	private static final long serialVersionUID = 1L;
	private String title;
	private String cond;
	private int[] width;
	private String[] header;
	private String[] format;
	
	private ArrayList<ArrayList<Object>> data;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public int[] getWidth() {
		return width;
	}

	public void setWidth(int[] width) {
		this.width = width;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public ArrayList<ArrayList<Object>> getData() {
		return data;
	}

	public void setData(ArrayList<ArrayList<Object>> data) {
		this.data = data;
	}
	
	public String[] getFormat() {
		return format;
	}

	public void setFormat(String[] format) {
		this.format = format;
	}

	public void setData(Object[] listData, String[] colProp) {
		data = new ArrayList<ArrayList<Object>>();
		for(int i=0;i<listData.length;i++){
			ArrayList<Object> row = new ArrayList<Object>();
			for(int j=0; j<colProp.length;j++){
				Object o = StringUtil.invoke(listData[i], StringUtil.makeGetter(colProp[j]),null);
				row.add(o);
			}
			data.add(row);
		}
	}

}