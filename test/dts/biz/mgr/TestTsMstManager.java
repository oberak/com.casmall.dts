package dts.biz.mgr;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casmall.dts.biz.domain.TsCarMstDTO;
import com.casmall.dts.biz.domain.TsCstMstDTO;
import com.casmall.dts.biz.domain.TsPrdtMstDTO;
import com.casmall.dts.biz.mgr.TsMstManager;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ObjectUtil;

public class TestTsMstManager {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectTsCarMst() {
		String mgtCd = "1111";
		TsMstManager tmm = new TsMstManager();
		TsCarMstDTO dto = new TsCarMstDTO();
		Assert.assertNotNull(tmm.selectTsCarMst(dto ));
		dto.setCar_cd("1");
		Assert.assertEquals(1,tmm.selectTsCarMst(dto ).size());
		dto.setCar_mgt_cd(mgtCd);
		Assert.assertEquals(1,tmm.selectTsCarMst(dto ).size());
		dto.setCar_num("11 가 1111");
		Assert.assertEquals(1,tmm.selectTsCarMst(dto ).size());
		dto.setMgt_yn("Y");
		Assert.assertEquals(1,tmm.selectTsCarMst(dto ).size());
		dto.setMgt_yn("N");
		Assert.assertEquals(0,tmm.selectTsCarMst(dto ).size());
	}

	@Test
	public void testSelectTsCarMstKey() {
		TsMstManager tmm = new TsMstManager();
		Assert.assertNotNull(tmm.selectTsCarMstKey());
	}

	@Test
	public void testInsertTsCarMst() {
		TsMstManager tmm = new TsMstManager();
		TsCarMstDTO dto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		try {
			dto.setCar_cd(tmm.selectTsCarMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setCar_num("99 가 1212");
			Assert.assertEquals(1,tmm.insertTsCarMst(dto));
			Assert.assertEquals(1,tmm.selectTsCarMst(dto ).size());
			Assert.assertEquals(dto,tmm.selectTsCarMst(dto ).get(0));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}
	
	@Test
	public void testExistTsCarMstMgtCd() {
		TsMstManager tmm = new TsMstManager();
		TsCarMstDTO dto = new TsCarMstDTO();
		
		String mgtCd = "1111";
		
		dto.setCar_mgt_cd(mgtCd);
		Assert.assertTrue(tmm.existTsCarMstMgtCd(dto));
		dto.setCar_mgt_cd("XXXX");
		Assert.assertFalse(tmm.existTsCarMstMgtCd(dto));
		dto.setCar_mgt_cd(mgtCd);
		dto.setCar_cd("1");
		Assert.assertFalse(tmm.existTsCarMstMgtCd(dto));
		dto.setCar_mgt_cd(mgtCd);
		dto.setCar_cd("2");
		Assert.assertTrue(tmm.existTsCarMstMgtCd(dto));
	}

	@Test
	public void testUpdateTsCarMst() {
		TsMstManager tmm = new TsMstManager();
		TsCarMstDTO dto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		try {
			String updateStr = "11 가 1112";
			dto.setCar_cd("1");
			dto.setCar_num(updateStr);
			Assert.assertEquals(1,tmm.updateTsCarMst(dto));
			ArrayList<TsCarMstDTO> list = tmm.selectTsCarMst(dto );
			Assert.assertNotNull(list.get(0));
			Assert.assertEquals(updateStr,list.get(0).getCar_num());
			dto.setCar_num("11 가 1111");
			Assert.assertEquals(1,tmm.updateTsCarMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}

	@Test
	public void testDeleteTsCarMst(){
		TsMstManager tmm = new TsMstManager();
		TsCarMstDTO dto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		try {
			dto.setCar_cd(tmm.selectTsCarMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setCar_num("99 가 9999");
			Assert.assertEquals(1,tmm.insertTsCarMst(dto));
			Assert.assertNotNull(tmm.selectTsCarMst(dto ));
			Assert.assertEquals(1,tmm.deleteTsCarMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}
	
	@Test
	public void testSelectTsCstMst() {
		TsMstManager tmm = new TsMstManager();
		TsCstMstDTO dto = new TsCstMstDTO();
		
		String mgtCd = "11";
		
		Assert.assertNotNull(tmm.selectTsCstMst(dto ).get(0));
		dto.setCst_cd("1");
		Assert.assertEquals(1,tmm.selectTsCstMst(dto ).size());
		dto.setCst_mgt_cd(mgtCd);
		Assert.assertEquals(1,tmm.selectTsCstMst(dto ).size());
		dto.setCst_nm("동아일보");
		Assert.assertEquals(1,tmm.selectTsCstMst(dto ).size());
		dto.setMgt_yn("Y");
		Assert.assertEquals(1,tmm.selectTsCstMst(dto ).size());
		dto.setMgt_yn("N");
		Assert.assertEquals(0,tmm.selectTsCstMst(dto ).size());
	}

	@Test
	public void testSelectTsCstMstKey() {
		TsMstManager tmm = new TsMstManager();
		Assert.assertNotNull(tmm.selectTsCstMstKey());
	}

	@Test
	public void testExistTsCstMstMgtCd() {
		TsMstManager tmm = new TsMstManager();
		TsCstMstDTO dto = new TsCstMstDTO();
		
		String mgtCd = "11";
		
		dto.setCst_mgt_cd(mgtCd);
		Assert.assertTrue(tmm.existTsCstMstMgtCd(dto));
		dto.setCst_mgt_cd("XXXX");
		Assert.assertFalse(tmm.existTsCstMstMgtCd(dto));
		dto.setCst_mgt_cd(mgtCd);
		dto.setCst_cd("1");
		Assert.assertFalse(tmm.existTsCstMstMgtCd(dto));
		dto.setCst_mgt_cd(mgtCd);
		dto.setCst_cd("2");
		Assert.assertTrue(tmm.existTsCstMstMgtCd(dto));
	}

	@Test
	public void testInsertTsCstMst() {
		TsMstManager tmm = new TsMstManager();
		TsCstMstDTO dto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
		try {
			dto.setCst_cd(tmm.selectTsCstMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setCst_nm("거래처 테스트");
			Assert.assertEquals(1,tmm.insertTsCstMst(dto));
			Assert.assertEquals(1,tmm.selectTsCstMst(dto ).size());
			Assert.assertEquals(dto,tmm.selectTsCstMst(dto ).get(0));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}

	@Test
	public void testUpdateTsCstMst() {
		TsMstManager tmm = new TsMstManager();
		TsCstMstDTO dto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
		try {
			String updateStr = "11-Change";
			dto.setCst_cd("1");
			dto.setCst_nm(updateStr);
			Assert.assertEquals(1,tmm.updateTsCstMst(dto));
			ArrayList<TsCstMstDTO> list = tmm.selectTsCstMst(dto );
			Assert.assertNotNull(list.get(0));
			Assert.assertEquals(updateStr,list.get(0).getCst_nm());
			dto.setCst_nm("동아일보");
			Assert.assertEquals(1,tmm.updateTsCstMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}

	@Test
	public void testDeleteTsCstMst(){
		TsMstManager tmm = new TsMstManager();
		TsCstMstDTO dto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
		try {
			dto.setCst_cd(tmm.selectTsCstMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setCst_nm("거래처-삭제");
			Assert.assertEquals(1,tmm.insertTsCstMst(dto));
			Assert.assertNotNull(tmm.selectTsCstMst(dto ));
			Assert.assertEquals(1,tmm.deleteTsCstMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}
	
	@Test
	public void testSelectTsPrdtMst() {
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO dto = new TsPrdtMstDTO();
		
		String mgtCd = "11";
		
		Assert.assertNotNull(tmm.selectTsPrdtMst(dto ).get(0));
		dto.setPrdt_cd("1");
		Assert.assertEquals(1,tmm.selectTsPrdtMst(dto ).size());
		dto.setPrdt_mgt_cd(mgtCd);
		Assert.assertEquals(1,tmm.selectTsPrdtMst(dto ).size());
		dto.setPrdt_nm("깡통");
		Assert.assertEquals(1,tmm.selectTsPrdtMst(dto ).size());
		dto.setMgt_yn("Y");
		Assert.assertEquals(1,tmm.selectTsPrdtMst(dto ).size());
		dto.setMgt_yn("N");
		Assert.assertEquals(0,tmm.selectTsPrdtMst(dto ).size());
	}

	@Test
	public void testSelectTsPrdtMstKey() {
		TsMstManager tmm = new TsMstManager();
		Assert.assertNotNull(tmm.selectTsPrdtMstKey());
	}

	@Test
	public void testExistTsPrdtMstMgtCd() {
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO dto = new TsPrdtMstDTO();
		
		String mgtCd = "11";
		
		dto.setPrdt_mgt_cd(mgtCd);
		Assert.assertTrue(tmm.existTsPrdtMstMgtCd(dto));
		dto.setPrdt_mgt_cd("XXXX");
		Assert.assertFalse(tmm.existTsPrdtMstMgtCd(dto));
		dto.setPrdt_mgt_cd(mgtCd);
		dto.setPrdt_cd("1");
		Assert.assertFalse(tmm.existTsPrdtMstMgtCd(dto));
		dto.setPrdt_mgt_cd(mgtCd);
		dto.setPrdt_cd("2");
		Assert.assertTrue(tmm.existTsPrdtMstMgtCd(dto));
	}

	@Test
	public void testInsertTsPrdtMst() {
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO dto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
		try {
			dto.setPrdt_cd(tmm.selectTsPrdtMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setPrdt_nm("제품 테스트");
			Assert.assertEquals(1,tmm.insertTsPrdtMst(dto));
			Assert.assertEquals(1,tmm.selectTsPrdtMst(dto ).size());
			Assert.assertEquals(dto,tmm.selectTsPrdtMst(dto ).get(0));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}

	@Test
	public void testUpdateTsPrdtMst() {
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO dto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
		try {
			String updateStr = "11-Change";
			dto.setPrdt_cd("1");
			dto.setPrdt_nm(updateStr);
			Assert.assertEquals(1,tmm.updateTsPrdtMst(dto));
			ArrayList<TsPrdtMstDTO> list = tmm.selectTsPrdtMst(dto );
			Assert.assertNotNull(list.get(0));
			Assert.assertEquals(updateStr,list.get(0).getPrdt_nm());
			dto.setPrdt_nm("깡통");
			Assert.assertEquals(1,tmm.updateTsPrdtMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}

	@Test
	public void testDeleteTsPrdtMst(){
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO dto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
		try {
			dto.setPrdt_cd(tmm.selectTsPrdtMstKey());
			dto.setMgt_yn(DTSConstants.FLAG_N);
			dto.setPrdt_nm("제품-삭제");
			Assert.assertEquals(1,tmm.insertTsPrdtMst(dto));
			Assert.assertNotNull(tmm.selectTsPrdtMst(dto ));
			Assert.assertEquals(1,tmm.deleteTsPrdtMst(dto));
        } catch (IOException e) {
	        e.printStackTrace();
	        fail("Error occured"+e.getMessage());
        }
	}
	
	@Test
	public void testInsertTsMst() {
		TsMstManager tmm = new TsMstManager();
		TsPrdtMstDTO prdtDto = (TsPrdtMstDTO)ObjectUtil.getDefaultObject(TsPrdtMstDTO.class.getName());
		TsCarMstDTO carDto = (TsCarMstDTO)ObjectUtil.getDefaultObject(TsCarMstDTO.class.getName());
		TsCstMstDTO cstDto = (TsCstMstDTO)ObjectUtil.getDefaultObject(TsCstMstDTO.class.getName());
		try {
			carDto.setCar_cd(tmm.selectTsCarMstKey());
			carDto.setMgt_yn(DTSConstants.FLAG_N);
			carDto.setCar_num("일괄-99 가 1212");

			cstDto.setCst_cd(tmm.selectTsCstMstKey());
			cstDto.setMgt_yn(DTSConstants.FLAG_N);
			cstDto.setCst_nm("일괄-거래처 테스트");
			
			prdtDto.setPrdt_cd(tmm.selectTsPrdtMstKey());
			prdtDto.setMgt_yn(DTSConstants.FLAG_N);
			prdtDto.setPrdt_nm("일괄-제품 테스트");
			Assert.assertEquals(3,tmm.insertTsMst(carDto, cstDto, prdtDto));
			
        } catch (IOException ie) {
	        ie.printStackTrace();
	        fail("Error occured"+ie.getMessage());
		} 
		
		carDto.setCar_cd(tmm.selectTsCarMstKey());
		carDto.setCar_num("일괄22-99 가 1212");
		
		try {
	        Assert.assertEquals(3,tmm.insertTsMst(carDto, cstDto, prdtDto));
	        fail("IOException expected..");
        } catch (IOException e) {
        }

	}
}
