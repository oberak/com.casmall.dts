package com.casmall.dts.biz.dao;

import java.util.ArrayList;

import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.domain.TsPrdtMstDTO;

/**
 * 마스터 정보 관리 DAO
 * @author OBERAK
 */
public interface TsMstDAO {
	/**
	 * 차량 정보 조회
	 * @param dto
	 * @return
	 */
	public ArrayList<TsCarMstDTO> selectTsCarMst(TsCarMstDTO dto);
	
	/**
	 * 차량 코드 채번
	 * @return
	 */
	public String selectTsCarMstKey();
	
	/**
	 * 차량 관리코드 중복체크
	 * @return
	 */
	public ArrayList<TsCarMstDTO> selectTsCarMstMgtCd(TsCarMstDTO dto);
	
	/**
	 * 차량 정보 등록
	 * @param dto
	 * @return
	 */
	public int insertTsCarMst(TsCarMstDTO dto);
	
	/**
	 * 차량 정보 수정
	 * @param dto
	 * @return
	 */
	public int updateTsCarMst(TsCarMstDTO dto);
	
	/**
	 * 거래처 정보 조회
	 * @param dto
	 * @return
	 */
	public ArrayList<TsCstMstDTO> selectTsCstMst(TsCstMstDTO dto);
	
	/**
	 * 거래처 코드 채번
	 * @return
	 */
	public String selectTsCstMstKey();
	
	/**
	 * 거래처 관리코드 중복체크
	 * @return
	 */
	public ArrayList<TsCstMstDTO> selectTsCstMstMgtCd(TsCstMstDTO dto);
	
	/**
	 * 거래처 정보 등록
	 * @param dto
	 * @return
	 */
	public int insertTsCstMst(TsCstMstDTO dto);
	
	/**
	 * 거래처 정보 수정
	 * @param dto
	 * @return
	 */
	public int updateTsCstMst(TsCstMstDTO dto);
	
	/**
	 * 제품 정보 조회
	 * @param dto
	 * @return
	 */
	public ArrayList<TsPrdtMstDTO> selectTsPrdtMst(TsPrdtMstDTO dto);

	/**
	 * 제품 코드 채번
	 * @return
	 */
	public String selectTsPrdtMstKey();
	
	/**
	 * 제품 관리코드 중복체크
	 * @return
	 */
	public ArrayList<TsPrdtMstDTO> selectTsPrdtMstMgtCd(TsPrdtMstDTO dto);
	
	/**
	 * 제품 정보 등록
	 * @param dto
	 * @return
	 */
	public int insertTsPrdtMst(TsPrdtMstDTO dto);
	
	/**
	 * 제품 정보 수정
	 * @param dto
	 * @return
	 */
	public int updateTsPrdtMst(TsPrdtMstDTO dto);
}
