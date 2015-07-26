package dts.biz.mgr;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casmall.dts.biz.domain.TsWgtInfDTO;
import com.casmall.dts.biz.mgr.TsWgtInfManager;
import com.casmall.dts.common.DTSConstants;
import com.casmall.dts.common.ObjectUtil;

public class TestTsWgtInfManager {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectHomeViewInfo() {
		TsWgtInfManager wim = new TsWgtInfManager();
		Assert.assertNotNull(wim.selectHomeViewInfo());
	}

	@Test
	public void testSelectTsWgtInfKey() {
		TsWgtInfManager wim = new TsWgtInfManager();
		Assert.assertNotNull(wim.selectTsWgtInfKey());
	}

	@Test
	public void testSelectTsWgtInfWgtNum() {
		TsWgtInfManager wim = new TsWgtInfManager();
		String num = wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_YYYY, 8);
		System.out.println("YYYY:["+num+"]");
		Assert.assertNotNull(num);
		Assert.assertEquals(12,num.length());

		num = wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_MM, 6);
		System.out.println("MM:["+num+"]");
		Assert.assertNotNull(num);
		Assert.assertEquals(12,num.length());

		num = wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_DD, 4);
		System.out.println("DD:["+num+"]");
		Assert.assertNotNull(num);
		Assert.assertEquals(12,num.length());
	}
	
	@Test
	public void testInsertTsWgtInf() {
		TsWgtInfManager wim = new TsWgtInfManager();
		TsWgtInfDTO dto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());
		
		try {
			dto.setWgt_cd(wim.selectTsWgtInfKey());
			dto.setCar_cd("1");
			dto.setFst_wgh(1000);
			dto.setFst_wgt_dt(new Date());
			dto.setNt("insert test");
			dto.setWgt_num(wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_MM, 4));
			dto.setWgt_flg_cd(DTSConstants.WGT_FLAG_GEN);
			dto.setWgt_stat_cd(DTSConstants.WGT_STAT_FST);
			
	        Assert.assertEquals(1, wim.insertTsWgtInf(dto));
	        
	        dto.setWgt_cd(wim.selectTsWgtInfKey());
			dto.setCar_cd("1");
			dto.setCst_cd("1");
			dto.setPrdt_cd("1");
			dto.setFst_wgh(2000);
			dto.setFst_wgt_dt(new Date());
			dto.setScnd_wgh(4000);
			dto.setScnd_wgt_dt(new Date());
			dto.setDscnt(200);
			dto.setDscnt_val(10);
			dto.setRl_wgh(1800);
			dto.setIo_flg("10"); // DTSConstants
			dto.setDscnt_bss_cd("P"); // DTSConstants
			dto.setNt("insert test one");
			dto.setWgt_num(wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_MM, 4));
			dto.setWgt_flg_cd(DTSConstants.WGT_FLAG_ONE);
			dto.setWgt_stat_cd(DTSConstants.WGT_STAT_SCND);
			
	        Assert.assertEquals(1, wim.insertTsWgtInf(dto));
        } catch (IOException e) {
	        fail(e.getMessage());
        }
	}

	@Test
	public void testUpdateTsWgtInf() {
		TsWgtInfManager wim = new TsWgtInfManager();
		TsWgtInfDTO dto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());
		
		try {
			String key = wim.selectTsWgtInfKey();
			dto.setWgt_cd(key);
			dto.setCar_cd("2");
			dto.setFst_wgh(1000);
			dto.setFst_wgt_dt(new Date());
			dto.setNt("insert test for update");
			dto.setWgt_num(wim.makeTsWgtInfWgtNum(true, DTSConstants.WGT_SEQ_MM, 4));
			dto.setWgt_flg_cd(DTSConstants.WGT_FLAG_GEN);
			dto.setWgt_stat_cd(DTSConstants.WGT_STAT_FST);
			
	        Assert.assertEquals(1, wim.insertTsWgtInf(dto));
	        
	        dto = (TsWgtInfDTO)ObjectUtil.getDefaultObject(TsWgtInfDTO.class.getName());
			dto.setWgt_cd(key);
			dto.setCst_cd("2");
			dto.setPrdt_cd("2");
			dto.setScnd_wgh(3000);
			dto.setScnd_wgt_dt(new Date());
			dto.setNt("update test");
			dto.setWgt_stat_cd(DTSConstants.WGT_STAT_SCND);
	        
	        Assert.assertEquals(1, wim.updateTsWgtInf(dto));
        } catch (IOException e) {
	        fail(e.getMessage());
        }
	}
	
	@Test
	public void testSelectTsWgtInfCount(){
		TsWgtInfManager wim = new TsWgtInfManager();
		int num = wim.selectTsWgtInfCount("DAY");
		System.out.println("DAY:["+num+"]");

		num = wim.selectTsWgtInfCount("MONTH");
		System.out.println("MONTH:["+num+"]");

		num = wim.selectTsWgtInfCount("YEAR");
		System.out.println("YEAR:["+num+"]");

	}
}
