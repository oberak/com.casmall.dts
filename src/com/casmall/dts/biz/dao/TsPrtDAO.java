package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;

/**
 * ������ ���� ���� DAO
 * @author OBERAK
 */
public interface TsPrtDAO {
	/**
	 * ������� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrtInfDTO> selectTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * ������� �Ϸù�ȣ ä��
	 * @return
	 */
	public long selectTsPrtInfKey();
	
	/**
	 * ������� ���
	 * @param dto
	 * @return
	 */
	public int insertTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * ������� ����
	 * @param dto
	 * @return
	 */
	public int updateTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * ����׸� ��ȸ
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrtAttrDTO> selectTsPrtAttr(TsPrtAttrDTO dto);
	
	/**
	 * ����׸� �Ϸù�ȣ ä��
	 * @return
	 */
	public long selectTsPrtAttrKey(TsPrtAttrDTO dto);
	
	/**
	 * ����׸� ���
	 * @param dto
	 * @return
	 */
	public int insertTsPrtAttr(TsPrtAttrDTO dto);
	
	/**
	 * ����׸� ����
	 * @param dto
	 * @return
	 */
	public int updateTsPrtAttr(TsPrtAttrDTO dto);
}
