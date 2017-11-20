package com.ibs.core.test.module.demo.biz;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.account.service.ICnlCustAcntService;
import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;
import com.ibs.core.module.cnlcust.service.ICnlCustService;
import com.ibs.core.test.module.demo.ContextHelper;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;

public class DemoBizTest {
	protected final static Log logger = LogFactory.getLog(DemoBizTest.class);
	
	//private IDemoBiz demoBiz;
	private ICnlCustService cnlCustService;
	private ICnlCustAcntService acntService;

	
	@Before
	public void runBefore() {
		ContextHelper.getContext();
		//this.demoBiz = (IDemoBiz) ContextHelper.getBean("demoBiz");
		this.cnlCustService = (ICnlCustService) ContextHelper.getBean("cnlCustService");
		this.acntService = (ICnlCustAcntService) ContextHelper.getBean("acntService");
	}

	@After
	public void runAfter() {
		//demoBiz = null;
		cnlCustService = null;
		acntService=null;
	}

	@Test
	public void test() throws Exception {
		try {
			logger.info("Starting");
//          查询余额测试 OK!			
			/*Long balance = acntService.queryBalance("01");
			System.out.println(balance+"============================");*/
			
//         查询出入金测试 Ok!
			/*String cnlCustCode = "01";
			String StartTransTime = "2015-01-01 00:00:00";
			String endTransTime = "2018-01-01 00:00:00";
			String transType = "01";
		    CnlTrans3m trans3m = acntService.queryCnlTrans3m(cnlCustCode, StartTransTime, endTransTime, transType);
		    System.out.println(trans3m.getTransNum()+"============================="+trans3m.getCustCode());*/
			
//       测试添加账户 OK!
			//acntService.createAccount("3333", "33333", "333333", "333333", "333333", "3333333");
			
// 销户操作 Ok!
			/*acntService.logout("01");*/
			
			
			
			
//			Demo demo = new Demo();
//			demo.setId("297eb51d556d087d01556d096adb0003");
//			demo.setDemoName("testname1");
//			demo.setDemoDescription("testdesc1");
//			demoBiz.updateDemo(demo);
			//新增测试
//			CnlCustPersonalDto cnlCustPersonalDto = new CnlCustPersonalDto();
//			cnlCustPersonalDto.setCnlCustCode("1234567893");
//			cnlCustPersonalDto.setCnlCustType("1");
//			cnlCustPersonalDto.setCertType("idcard");
//			cnlCustPersonalDto.setBirthday(new Date());
//			cnlCustPersonalDto.setCustStatus("3");
//			cnlCustPersonalDto.setEmail("abc@ibm.com");
//			
//			cnlCustService.addCnlPersonalCust(cnlCustPersonalDto);
			
			//修改测试
//			CnlCustPersonalDto cnlCustPersonalDto = new CnlCustPersonalDto();
//			cnlCustPersonalDto.setCnlCustCode("1234567893");
//			cnlCustPersonalDto.setCnlCustType("1");
//			cnlCustPersonalDto.setCountry("America");
//			cnlCustPersonalDto.setCertType("modifiedIdcard");
//			cnlCustPersonalDto.setBirthday(new Date());
//			cnlCustPersonalDto.setCustStatus("3");
//			cnlCustPersonalDto.setEmail("abcdef@ibm.com");
			
//			cnlCustService.updateCnlPersonalCust(cnlCustPersonalDto);
			
			//查询测试
//			CnlCustPersonalDto cnlCustPersonalDto = cnlCustService.getPersonalCustByCustCode("1234567893");
//			System.out.println(cnlCustPersonalDto);
			
			//删除测试
//			cnlCustService.deleteCnlCust("1234567893");
	CorBankAcnt findBankAcnt = acntService.findBankAcnt("3333");
	System.out.println(findBankAcnt.getBankCardNum()+"====="+findBankAcnt.getCustName());

			
			//logger.info(ToStringBuilder.reflectionToString(cnlCustPersonalDto, ToStringStyle.SIMPLE_STYLE));
			
		} catch (Exception e) {
			e.printStackTrace();
//			if((e instanceof DataIntegrityViolationException) ){
//				;
//			}else {
//				e.printStackTrace();
//				Assert.fail();
//			}
		} finally {
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DemoBizTest.class);
	}
	
}
