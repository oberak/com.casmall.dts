package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.domain.TsPrdtMstDTO;

/**
 * ������ ���� ���� DAO
 * @author OBERAK
 */
public interface TsMstDAO {
	/**
	 * ���� ���� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsCarMstDTO> selectTsCarMst(TsCarMstDTO dto);
	
	/**
	 * ���� �ڵ� ä��
	 * @return
	 */
	public String selectTsCarMstKey();
	
	/**
	 * ���� �����ڵ� �ߺ�üũ
	 * @return
	 */
	public ArrayList<TsCarMstDTO> selectTsCarMstMgtCd(TsCarMstDTO dto);
	
	/**
	 * ���� ���� ���
	 * @param dto
	 * @return
	 */
	public int insertTsCarMst(TsCarMstDTO dto);
	
	/**
	 * ���� ���� ����
	 * @param dto
	 * @return
	 */
	public int updateTsCarMst(TsCarMstDTO dto);
	
	/**
	 * �ŷ�ó ���� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsCstMstDTO> selectTsCstMst(TsCstMstDTO dto);
	
	/**
	 * �ŷ�ó �ڵ� ä��
	 * @return
	 */
	public String selectTsCstMstKey();
	
	/**
	 * �ŷ�ó �����ڵ� �ߺ�üũ
	 * @return
	 */
	public ArrayList<TsCstMstDTO> selectTsCstMstMgtCd(TsCstMstDTO dto);
	
	/**
	 * �ŷ�ó ���� ���
	 * @param dto
	 * @return
	 */
	public int insertTsCstMst(TsCstMstDTO dto);
	
	/**
	 * �ŷ�ó ���� ����
	 * @param dto
	 * @return
	 */
	public int updateTsCstMst(TsCstMstDTO dto);
	
	/**
	 * ��ǰ ���� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrdtMstDTO> selectTsPrdtMst(TsPrdtMstDTO dto);

	/**
	 * ��ǰ �ڵ� ä��
	 * @return
	 */
	public String selectTsPrdtMstKey();
	
	/**
	 * ��ǰ �����ڵ� �ߺ�üũ
	 * @return
	 */
	public ArrayList<TsPrdtMstDTO> selectTsPrdtMstMgtCd(TsPrdtMstDTO dto);
	
	/**
	 * ��ǰ ���� ���
	 * @param dto
	 * @return
	 */
	public int insertTsPrdtMst(TsPrdtMstDTO dto);
	
	/**
	 * ��ǰ ���� ����
	 * @param dto
	 * @return
	 */
	public int updateTsPrdtMst(TsPrdtMstDTO dto);
}
