package com.casmall.dts.biz.mgr;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.casmall.common.StringUtil;
import com.casmall.common.db.DBManager;
import com.casmall.dts.biz.dao.TsWgtInfDAO;
import com.casmall.dts.biz.domain.HomeViewInfoVO;
import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.common.DTSConstants;

public class TsWgtInfManager {
	protected static Log logger = LogFactory.getLog(TsWgtInfManager.class);
	
	private DBManager dbm = DBManager.getInstance();
	
	/**
	 * Home ������� ǥ��
	 * @return
	 */
	public HomeViewInfoVO selectHomeViewInfo() {
		HomeViewInfoVO vo = new HomeViewInfoVO();
		int scdWeigh = 0;
		int idx = 0;
		
		SqlSession session = dbm.openSession();
		TsWgtInfDAO wgtDao = session.getMapper(TsWgtInfDAO.class);
		try{
			TsWgtInfDTO param = new TsWgtInfDTO();
			param.setWgt_stat_cd(DTSConstants.WGT_STAT_FST);
			ArrayList<TsWgtInfDTO> fstList = wgtDao.selectTsWgtInf(param);
			for( TsWgtInfDTO dto : fstList){
				scdWeigh += dto.getRl_wgh();
				fstList.get(idx).setNo(++idx);
			} // for
			vo.setFstList(fstList);
			vo.setFstCnt(fstList.size());
			
			idx = 0;
			param.setWgt_stat_cd(DTSConstants.WGT_STAT_SCND);
			ArrayList<TsWgtInfDTO> scdList = wgtDao.selectTsWgtInf(param);
			for( TsWgtInfDTO dto : scdList){
				scdWeigh += dto.getRl_wgh();
				scdList.get(idx).setNo(++idx);
			} // for
			vo.setScdList(scdList);
			vo.setScdCnt(scdList.size());
			vo.setScdWeigh(scdWeigh);
			
			TsWgtInfDTO dto = wgtDao.selectTsWgtInfMonth();
			vo.setMonthCnt((int)dto.getScnd_wgh());
			vo.setMonthWeigh(dto.getRl_wgh());
		}finally{
			session.close();
		} // try
		return vo;
	}// selectMainDisplayInfo
	
	/**
	 * �跮���� ���
	 * @param dto
	 * @return
	 */
	public int insertTsWgtInf(TsWgtInfDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		try{
			int chk = dao.insertTsWgtInf(dto);
			if(chk == 0){
				throw new IOException("�跮  ������ ��ϵ��� �ʾҽ��ϴ�.");
			}
			session.commit();
			return chk;
		}catch(Exception e){
			throw new IOException(e);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �跮���� ����
	 * @param dto
	 * @return
	 */
	public int updateTsWgtInf(TsWgtInfDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		try{
			int chk = dao.updateTsWgtInf(dto);
			if(chk == 0){
				throw new IOException("�跮  ������ �������� �ʾҽ��ϴ�.");
			}
			session.commit();
			return chk;
		}catch(Exception e){
			throw new IOException(e);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �跮 �ڵ� ä��
	 * @return
	 */
	public String selectTsWgtInfKey(){
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		try{
			return dao.selectTsWgtInfKey();
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �跮 ������ȣ ä��
	 * @param str
	 * @return
	 */
	public String makeTsWgtInfWgtNum(boolean isFstWgt, String term, int len){
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		String seq = null, prefix=null;
		SimpleDateFormat sdf = new SimpleDateFormat(term);
		
		try{
			TsWgtInfDTO dto = new TsWgtInfDTO();
			if(isFstWgt){
				dto.setExt_1(term);
			}else{
				dto.setExt_2(term);
			}
			String preSeq = dao.selectTsWgtInfWgtNum(dto);
			prefix = sdf.format(new Date());
			
			if(preSeq == null || "".equals(preSeq)){
				seq = StringUtil.padZero(1,len);
			}else{
				seq = preSeq.substring(term.length());
				seq = StringUtil.padZero(Integer.parseInt(seq)+1,len);;
			}
			
			return prefix+seq;
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �跮 ���� ��ȸ(1��,2��)
	 * @param dto
	 * @return
	 */
	public ArrayList<TsWgtInfDTO> selectTsWgtInf(TsWgtInfDTO dto){
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		try{
			return dao.selectTsWgtInf(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �Ǽ� ��ȸ
	 * type : DAY, MONTH, YEAR
	 * @param type
	 * @return
	 */
	public int selectTsWgtInfCount(String type){
		SqlSession session = dbm.openSession();
		TsWgtInfDAO dao = session.getMapper(TsWgtInfDAO.class);
		try{
			return dao.selectTsWgtInfCount(type);
		}finally{
			session.close();
		} // try
	}
}// class
