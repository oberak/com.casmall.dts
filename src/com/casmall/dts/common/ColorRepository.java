package com.casmall.dts.common;
import java.awt.Paint;

import org.eclipse.swt.graphics.Color;

import com.swtdesigner.SWTResourceManager;

public class ColorRepository {
	public static final int BG_MAIN = 10; // 애플리케이션 바탕색
	public static final int BG_CONTENTS = 11; // 내용 바탕색
	public static final int BG_EDIT_CONTENTS = 12;// 하단 에디트 View 배경
	
	public static final int TEXT_TITLE = 21; // 화면 제목
	public static final int TEXT_TAB = 22; // 텝 Font
	
	public static final int GRID_LINE = 31;
	public static final int GRID_ODD_BG = 32; // 짝수줄 배경색
	public static final int GRID_EVEN_BG = 33; // 홀수줄 배경색
	public static final int GRID_INPUT = 34; // 인풋 색상
	public static final int GRID_SUM_BG = 35; // 하단 합계 색상
	public static final int GRID_SELECT_BG = 36; // 선택 줄 배경색

	public static final int HOME_WIGET_BG = 41; // Home 위젯 배경
	public static final int HOME_WIGET_LINE = 42; // Home 라인색
	public static final int HOME_CAL_TITLE = 43; // Home 라인색
	
	// TABLE
	public static final int TABLE_HEADER = 51;
	public static final int TABLE_LINE = 52;
	
	public static final int SASH_LINE = 91; // sash line
	
	public static final int HOME_TABLE_LINE = 101;
	public static final int HOME_TABLE_HEADER_BG = 102;
	public static final int HOME_TABLE_ROW_HEDER_ODD = 103;
	public static final int HOME_TABLE_ROW_HEDER_EVEN = 104;
	public static final int HOME_TABLE_ROW_EVEN =106;
	public static final int HOME_FX_RATE_UP = 107;
	public static final int HOME_FX_RATE_DOWN = 108;
	public static final int HOME_TITLE = 109;
	public static final int HOME_LINE = 110;
	
	public static final int POPUP_BTN = 201;
	
	public static final int COLOR_WHITE = 901;
	public static final  int COLOR_BLACK = 902;
	public static final int COLOR_RED = 903;

	public static Color getColor(int c){
		switch(c){
		case BG_MAIN:
			return SWTResourceManager.getColor(16,86,100);
		case BG_CONTENTS:
			return SWTResourceManager.getColor(219,238,241);//148,158,173);
		case TEXT_TITLE:
			return SWTResourceManager.getColor(247,110,0);
		case BG_EDIT_CONTENTS:
			return SWTResourceManager.getColor(233,244,246);
			
		case GRID_LINE:
			return SWTResourceManager.getColor(156,188,201);
		case GRID_ODD_BG:
			return SWTResourceManager.getColor(232,235,240);
		case GRID_EVEN_BG:
			return SWTResourceManager.getColor(255,255,255);
		case GRID_INPUT:
			return SWTResourceManager.getColor(245,233,200);
		case GRID_SUM_BG:
			return SWTResourceManager.getColor(245,233,200);
		case GRID_SELECT_BG:
			return SWTResourceManager.getColor(65,95,133);
			
		case SASH_LINE:
			return SWTResourceManager.getColor(139,183,202);
		case TEXT_TAB:
			return SWTResourceManager.getColor(170,170,170);
		case HOME_WIGET_BG:
			return SWTResourceManager.getColor(237,247,248);		
		case HOME_WIGET_LINE:
			return SWTResourceManager.getColor(170,209,216);
		case HOME_CAL_TITLE:
			return SWTResourceManager.getColor(227,203,113);
		case HOME_TABLE_LINE:
			return SWTResourceManager.getColor(197,190,149);
		case HOME_TABLE_HEADER_BG :
			return SWTResourceManager.getColor(245,239,218);
		case HOME_TABLE_ROW_HEDER_ODD  :
			return SWTResourceManager.getColor(251,250,243);
		case HOME_TABLE_ROW_HEDER_EVEN  :
			return SWTResourceManager.getColor(239,236,227);
		case HOME_TABLE_ROW_EVEN  :
			return SWTResourceManager.getColor(241,241,235);
		case HOME_TITLE  :
			return SWTResourceManager.getColor(71,71,71);
		case HOME_LINE  :
			return SWTResourceManager.getColor(105,105,105);

			
		case TABLE_HEADER  :
			return SWTResourceManager.getColor(237,243,229);
		case TABLE_LINE  :
			return SWTResourceManager.getColor(150,198,212);
			
			
			
		case HOME_FX_RATE_UP  :
			return SWTResourceManager.getColor(199,8,0);
		case HOME_FX_RATE_DOWN  :
			return SWTResourceManager.getColor(26,111,230);
			
		case POPUP_BTN  :
			return SWTResourceManager.getColor(39,84,113);
			
		case COLOR_WHITE  :
			return SWTResourceManager.getColor(255,255,255);
		case COLOR_BLACK  :
			return SWTResourceManager.getColor(0,0,0);
		case COLOR_RED  :
			return SWTResourceManager.getColor(255,0,0);			
		}
		return null;
	}

	public static Paint getPaint(int c) {
		switch(c){
		case HOME_WIGET_BG:
			return new java.awt.Color(237,247,248);		

		}
		return null;
	}
}
