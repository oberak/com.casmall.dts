package dts.biz.mgr;

import static org.junit.Assert.fail;

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.swt.SWT;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casmall.dts.biz.domain.TsPrtAttrDTO;
import com.casmall.dts.biz.domain.TsPrtInfDTO;
import com.casmall.dts.biz.mgr.TsPrtManager;
import com.casmall.dts.common.ObjectUtil;

public class TestTsPrtManager {
	private static TsPrtManager tpm;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tpm = new TsPrtManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectTsPrtInfKey() {
		Assert.assertNotNull(tpm.selectTsPrtInfKey());
		Assert.assertNotSame(0, tpm.selectTsPrtInfKey());
	}

	@Test
	public void testInsertTsPrtInf() {
		TsPrtInfDTO dto = (TsPrtInfDTO)ObjectUtil.getDefaultObject(TsPrtInfDTO.class.getName());
		dto.setPrt_seq(tpm.selectTsPrtInfKey());
		dto.setBss_cdnt_x(10);
		dto.setBss_cdnt_y(20);
		dto.setBss_font("1|굴림|14.25|1|WINDOWS|1|-19|0|0|0|700|0|0|0|-127|3|2|1|50|굴림");
		dto.setPaper_height(364);
		dto.setPaper_width(240);
		dto.setPrt_nm("테스트 용지 설정 1");
		dto.setWdt_prt_yn("N");
		try {
	        Assert.assertEquals(1, tpm.insertTsPrtInf(dto));
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}
	
	@Test
	public void testSelectTsPrtInf() {
		TsPrtInfDTO dto = new TsPrtInfDTO();
		dto.setPrt_seq(1);
		Assert.assertEquals(1, tpm.selectTsPrtInf(dto).size());
	}

	@Test
	public void testUpdateTsPrtInf() {
		String tmp = "테스트 수정 테스트 1";
		TsPrtInfDTO dto = (TsPrtInfDTO)ObjectUtil.getDefaultObject(TsPrtInfDTO.class.getName());
		dto.setPrt_seq(1);
		dto = tpm.selectTsPrtInf(dto).get(0);
		dto.setPrt_nm(tmp);
		try {
	        Assert.assertEquals(1, tpm.updateTsPrtInf(dto));
	        Assert.assertEquals(tmp, tpm.selectTsPrtInf(dto).get(0).getPrt_nm());
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}

	@Test
	public void testDeleteTsPrtInf() {
		TsPrtInfDTO dto = (TsPrtInfDTO)ObjectUtil.getDefaultObject(TsPrtInfDTO.class.getName());
		dto.setPrt_seq(2);
		try {
	        Assert.assertEquals(1, tpm.deleteTsPrtInf(dto));
	        dto.setDel_yn("N");
	        Assert.assertEquals(1, tpm.updateTsPrtInf(dto));
	        Assert.assertEquals(1, tpm.selectTsPrtInf(dto).size());
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}

	@Test
	public void testSelectTsPrtAttrKey() {
		TsPrtAttrDTO dto = new TsPrtAttrDTO();
		dto.setPrt_seq(1);
		Assert.assertNotNull(tpm.selectTsPrtAttrKey(dto));
		Assert.assertNotSame(0, tpm.selectTsPrtAttrKey(dto));
	}

	@Test
	public void testInsertTsPrtAttr() {
		TsPrtAttrDTO dto = (TsPrtAttrDTO)ObjectUtil.getDefaultObject(TsPrtAttrDTO.class.getName());
		dto.setPrt_seq(1);
		dto.setAttr_seq(tpm.selectTsPrtAttrKey(dto));
		dto.setArea("10|11|30|10");
		dto.setAttr_flg_cd("DB");
		dto.setAttr_cd("car_num");
		dto.setAttr_nm("차량번호");
		dto.setBg_color("0|0|0");
		dto.setData_fmt(null);
		dto.setData_type_cd("ST");
		dto.setFont("1|굴림|14.25|1|WINDOWS|1|-19|0|0|0|700|0|1|1|-127|3|2|1|50|굴림");
		dto.setFont_color("0|255|0");
		dto.setPrt_yn("Y");
		dto.setStyle(SWT.CENTER);
		dto.setTkn(0);

		try {
	        Assert.assertEquals(1, tpm.insertTsPrtAttr(dto));
        } catch (IOException e) {
        	fail(e.getMessage());
        }
        
		TsPrtAttrDTO dto2 = (TsPrtAttrDTO)ObjectUtil.getDefaultObject(TsPrtAttrDTO.class.getName());
		dto2.setPrt_seq(1);
		dto2.setAttr_seq(tpm.selectTsPrtAttrKey(dto));
		dto2.setArea("10|11|30|10");
		dto2.setAttr_flg_cd("DB");
		dto2.setAttr_cd("fst_wgt");
		dto2.setAttr_nm("1차중량");
		dto2.setBg_color("0|0|0");
		dto2.setData_fmt("###,###");
		dto2.setData_type_cd("ST");
		dto2.setFont("1|굴림|14.25|1|WINDOWS|1|-19|0|0|0|700|0|0|0|-127|3|2|1|50|굴림");
		dto2.setFont_color("0|255|0");
		dto2.setPrt_yn("Y");
		dto2.setStyle(SWT.RIGHT);
		dto2.setTkn(0);
		
		try {
	        Assert.assertEquals(1, tpm.insertTsPrtAttr(dto2));
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}
	
	@Test
	public void testSelectTsPrtAttr() {
		TsPrtAttrDTO dto = (TsPrtAttrDTO)ObjectUtil.getDefaultObject(TsPrtAttrDTO.class.getName());
		dto.setPrt_seq(1);
		Assert.assertNotSame(0, tpm.selectTsPrtAttr(dto).size());
		dto.setAttr_seq(1);
		Assert.assertEquals(1, tpm.selectTsPrtAttr(dto).size());
		dto.setPrt_yn("N");
		Assert.assertEquals(0, tpm.selectTsPrtAttr(dto).size());
	}

	@Test
	public void testUpdateTsPrtAttr() {
		TsPrtAttrDTO dto = (TsPrtAttrDTO)ObjectUtil.getDefaultObject(TsPrtAttrDTO.class.getName());
		dto.setPrt_seq(1);
		dto.setAttr_seq(1);
		dto.setAttr_cd("car_num2");
		try {
	        Assert.assertEquals(1, tpm.updateTsPrtAttr(dto));
	        
	        Assert.assertEquals("car_num2", tpm.selectTsPrtAttr(dto).get(0).getAttr_cd());
	        dto.setAttr_cd("car_num");
	        Assert.assertEquals(1, tpm.updateTsPrtAttr(dto));
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}

	@Test
	public void testDeleteTsPrtAttr() {
		TsPrtAttrDTO dto = new TsPrtAttrDTO();
		dto.setPrt_seq(1);
		dto.setAttr_seq(1);
		try {
	        Assert.assertEquals(1, tpm.deleteTsPrtAttr(dto));
	        Assert.assertEquals(0, tpm.selectTsPrtAttr(dto).size());
	        dto.setDel_yn("N");
	        Assert.assertEquals(1, tpm.updateTsPrtAttr(dto));
        } catch (IOException e) {
        	fail(e.getMessage());
        }
	}

}
