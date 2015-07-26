package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsWgtInfDTO;

public interface TsWgtInfDAO {
	/**
	 * 계량 정보 조회(1차,2차)
	 * @param dto
	 * @return
	 */
	public ArrayList<TsWgtInfDTO> selectTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * 월간 계량 요약정보 조회
	 * @return
	 */
	public TsWgtInfDTO selectTsWgtInfMonth();
	
	/**
	 * 계량정보 등록
	 * @param dto
	 * @return
	 */
	public int insertTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * 계량정보 수정
	 * @param dto
	 * @return
	 */
	public int updateTsWgtInf(TsWgtInfDTO dto);
	
	/**
	 * 계량 코드 채번
	 * @return
	 */
	public String selectTsWgtInfKey();
	
	/**
	 * 계량 관리번호 채번
	 * @param str
	 * @return
	 */
	public String selectTsWgtInfWgtNum(TsWgtInfDTO dto);
	
	/**
	 * 건수 조회
	 * type : DAY, MONTH, YEAR
	 * @param type
	 * @return
	 */
	public int selectTsWgtInfCount(String type);
}
