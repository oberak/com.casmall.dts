package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsWgtInfDTO;

public interface TsWgtInfDAO {
	/**
	 * �跮 ���� ��ȸ(1��,2��)
	 * @param dto
	 * @return
	 */
	public ArrayList<TsWgtInfDTO> selectTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * ���� �跮 ������� ��ȸ
	 * @return
	 */
	public TsWgtInfDTO selectTsWgtInfMonth();
	
	/**
	 * �跮���� ���
	 * @param dto
	 * @return
	 */
	public int insertTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * �跮���� ����
	 * @param dto
	 * @return
	 */
	public int updateTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * �跮 �ڵ� ä��
	 * @return
	 */
	public String selectTsWgtInfKey();
	
	/**
	 * �跮 ������ȣ ä��
	 * @param str
	 * @return
	 */
	public String selectTsWgtInfWgtNum(TsWgtInfDTO dto);
	
	/**
	 * �Ǽ� ��ȸ
	 * type : DAY, MONTH, YEAR
	 * @param type
	 * @return
	 */
	public int selectTsWgtInfCount(String type);
}
