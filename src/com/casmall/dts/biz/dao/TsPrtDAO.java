package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;

/**
 * 마스터 정보 관리 DAO
 * @author OBERAK
 */
public interface TsPrtDAO {
	/**
	 * 출력정보 조회
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrtInfDTO> selectTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * 출력정보 일련번호 채번
	 * @return
	 */
	public long selectTsPrtInfKey();
	
	/**
	 * 출력정보 등록
	 * @param dto
	 * @return
	 */
	public int insertTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * 출력정보 수정
	 * @param dto
	 * @return
	 */
	public int updateTsPrtInf(TsPrtInfDTO dto);
	
	/**
	 * 출력항목 조회
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrtAttrDTO> selectTsPrtAttr(TsPrtAttrDTO dto);
	
	/**
	 * 출력항목 일련번호 채번
	 * @return
	 */
	public long selectTsPrtAttrKey(TsPrtAttrDTO dto);
	
	/**
	 * 출력항목 등록
	 * @param dto
	 * @return
	 */
	public int insertTsPrtAttr(TsPrtAttrDTO dto);
	
	/**
	 * 출력항목 수정
	 * @param dto
	 * @return
	 */
	public int updateTsPrtAttr(TsPrtAttrDTO dto);
}
