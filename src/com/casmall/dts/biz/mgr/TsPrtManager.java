package com.casmall.dts.biz.mgr;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.casmall.common.db.DBManager;
import com.casmall.dts.biz.dao.TsPrtDAO;
import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;
import com.casmall.dts.common.DTSConstants;

public class TsPrtManager {
	protected static Log logger = LogFactory.getLog(TsPrtManager.class);
	
	private DBManager dbm = DBManager.getInstance();
	
	/**
	 * 출력정보 조회
	 * @param car
	 * @return
	 */
	public ArrayList<TsPrtInfDTO> selectTsPrtInf(TsPrtInfDTO dto){
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			return dao.selectTsPrtInf(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 *출력정보 일련번호 채번
	 * @return
	 */
	public long selectTsPrtInfKey(){
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			return dao.selectTsPrtInfKey();
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * 출력정보 등록
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int insertTsPrtInf(TsPrtInfDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			int chk = dao.insertTsPrtInf(dto);
			if(chk == 0){
				throw new IOException("출력정보가 등록되지 않았습니다.");
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
	 * 출력정보 수정
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int updateTsPrtInf(TsPrtInfDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			int chk = dao.updateTsPrtInf(dto);
			if(chk == 0){
				throw new IOException("출력정보가 수정되지 않았습니다.");
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
	 * 출력정보 삭제
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int deleteTsPrtInf(TsPrtInfDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			dto.setDel_yn(DTSConstants.FLAG_Y);
			int chk = dao.updateTsPrtInf(dto);
			if(chk == 0){
				throw new IOException("출력정보가 삭제되지 않았습니다.");
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
	 * 출력항목 조회
	 * @param car
	 * @return
	 */
	public ArrayList<TsPrtAttrDTO> selectTsPrtAttr(TsPrtAttrDTO dto){
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			return dao.selectTsPrtAttr(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 *출력항목 일련번호 채번
	 * @return
	 */
	public long selectTsPrtAttrKey(TsPrtAttrDTO dto){
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			return dao.selectTsPrtAttrKey(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * 출력항목 등록
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int insertTsPrtAttr(TsPrtAttrDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			int chk = dao.insertTsPrtAttr(dto);
			if(chk == 0){
				throw new IOException("출력항목이 등록되지 않았습니다.");
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
	 * 출력항목 수정
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int updateTsPrtAttr(TsPrtAttrDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			int chk = dao.updateTsPrtAttr(dto);
			if(chk == 0){
				throw new IOException("출력항목이 수정되지 않았습니다.");
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
	 * 출력항목 삭제
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int deleteTsPrtAttr(TsPrtAttrDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsPrtDAO dao = session.getMapper(TsPrtDAO.class);
		try{
			dto.setDel_yn(DTSConstants.FLAG_Y);
			int chk = dao.updateTsPrtAttr(dto);
			if(chk == 0){
				throw new IOException("출력항목이 삭제되지 않았습니다.");
			}
			session.commit();
			return chk;
		}catch(Exception e){
			throw new IOException(e);
		}finally{
			session.close();
		} // try
	}
	
}// class
