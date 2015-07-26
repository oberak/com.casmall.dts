package com.casmall.dts.biz.mgr;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.casmall.common.db.DBManager;
import com.casmall.dts.biz.dao.TsMstDAO;
import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.domain.TsPrdtMstDTO;
import com.casmall.dts.common.DTSConstants;

public class TsMstManager {
	protected static Log logger = LogFactory.getLog(TsMstManager.class);
	
	private DBManager dbm = DBManager.getInstance();
	
	/**
	 * �������� ��ȸ
	 * @param car
	 * @return
	 */
	public ArrayList<TsCarMstDTO> selectTsCarMst(TsCarMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsCarMst(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ���� �ڵ� ä��
	 * @return
	 */
	public String selectTsCarMstKey(){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsCarMstKey();
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ���� �����ڵ� �ߺ�üũ
	 * @return
	 */
	public boolean existTsCarMstMgtCd(TsCarMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			ArrayList<TsCarMstDTO> list = dao.selectTsCarMstMgtCd(dto);
			if(list == null || list.size() == 0){
				return false;
			}
			return true;
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ���� ���� ���
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int insertTsCarMst(TsCarMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.insertTsCarMst(dto);
			if(chk == 0){
				throw new IOException("����  ������ ��ϵ��� �ʾҽ��ϴ�.");
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
	 * ���� ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int updateTsCarMst(TsCarMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.updateTsCarMst(dto);
			if(chk == 0){
				throw new IOException("����  ������ �������� �ʾҽ��ϴ�.");
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
	 * ���� ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int deleteTsCarMst(TsCarMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			dto.setDel_yn(DTSConstants.FLAG_Y);
			int chk = dao.updateTsCarMst(dto);
			if(chk == 0){
				throw new IOException("����  ������ �������� �ʾҽ��ϴ�.");
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
	 * �ŷ�ó ���� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsCstMstDTO> selectTsCstMst(TsCstMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsCstMst(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �ŷ�ó �ڵ� ä��
	 * @return
	 */
	public String selectTsCstMstKey(){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsCstMstKey();
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �ŷ�ó �����ڵ� �ߺ�üũ
	 * @return
	 */
	public boolean existTsCstMstMgtCd(TsCstMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			ArrayList<TsCstMstDTO> list = dao.selectTsCstMstMgtCd(dto);
			if(list == null || list.size() == 0){
				return false;
			}
			return true;
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * �ŷ�ó ���� ���
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int insertTsCstMst(TsCstMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.insertTsCstMst(dto);
			if(chk == 0){
				throw new IOException("�ŷ�ó ������ ��ϵ��� �ʾҽ��ϴ�.");
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
	 * �ŷ�ó ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int updateTsCstMst(TsCstMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.updateTsCstMst(dto);
			if(chk == 0){
				throw new IOException("�ŷ�ó ������ �������� �ʾҽ��ϴ�.");
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
	 * �ŷ�ó ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int deleteTsCstMst(TsCstMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			dto.setDel_yn(DTSConstants.FLAG_Y);
			int chk = dao.updateTsCstMst(dto);
			if(chk == 0){
				throw new IOException("�ŷ�ó ������ �������� �ʾҽ��ϴ�.");
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
	 * ��ǰ���� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrdtMstDTO> selectTsPrdtMst(TsPrdtMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsPrdtMst(dto);
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ��ǰ �ڵ� ä��
	 * @return
	 */
	public String selectTsPrdtMstKey(){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			return dao.selectTsPrdtMstKey();
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ��ǰ �����ڵ� �ߺ�üũ
	 * @return
	 */
	public boolean existTsPrdtMstMgtCd(TsPrdtMstDTO dto){
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			ArrayList<TsPrdtMstDTO> list = dao.selectTsPrdtMstMgtCd(dto);
			if(list == null || list.size() == 0){
				return false;
			}
			return true;
		}finally{
			session.close();
		} // try
	}
	
	/**
	 * ��ǰ ���� ���
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int insertTsPrdtMst(TsPrdtMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.insertTsPrdtMst(dto);
			if(chk == 0){
				throw new IOException("��ǰ ������ ��ϵ��� �ʾҽ��ϴ�.");
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
	 * ��ǰ ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int updateTsPrdtMst(TsPrdtMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			int chk = dao.updateTsPrdtMst(dto);
			if(chk == 0){
				throw new IOException("��ǰ �������������� �ʾҽ��ϴ�.");
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
	 * ��ǰ ���� ����
	 * @param dto
	 * @return
	 * @throws IOException 
	 */
	public int deleteTsPrdtMst(TsPrdtMstDTO dto) throws IOException{
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			dto.setDel_yn(DTSConstants.FLAG_Y);
			int chk = dao.updateTsPrdtMst(dto);
			if(chk == 0){
				throw new IOException("��ǰ ������ �������� �ʾҽ��ϴ�.");
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
	 * ���������� �ϰ� ���
	 * @param carDto
	 * @param cstDto
	 * @param prdtDto
	 * @return
	 * @throws IOException
	 */
	public int insertTsMst(TsCarMstDTO carDto, TsCstMstDTO cstDto, TsPrdtMstDTO prdtDto) throws IOException{
		int rtn = 0,chk;
		SqlSession session = dbm.openSession();
		TsMstDAO dao = session.getMapper(TsMstDAO.class);
		try{
			if(carDto!=null){
				chk = dao.insertTsCarMst(carDto);
				if(chk == 0){
					throw new IOException("���� ������ ��ϵ��� �ʾҽ��ϴ�.");
				}
				rtn++;
			}
			if(cstDto!=null){
				chk = dao.insertTsCstMst(cstDto);
				if(chk == 0){
					throw new IOException("�ŷ�ó ������ ��ϵ��� �ʾҽ��ϴ�.");
				}
				rtn++;
			}
			if(prdtDto!=null){
				chk = dao.insertTsPrdtMst(prdtDto);
				if(chk == 0){
					throw new IOException("��ǰ ������ ��ϵ��� �ʾҽ��ϴ�.");
				}
				rtn++;
			}
			session.commit();
			return rtn;
		}catch(IOException ie){
			throw ie;
		}catch(Exception e){
			throw new IOException(e);
		}finally{
			session.close();
		} // try
	}
}// class
