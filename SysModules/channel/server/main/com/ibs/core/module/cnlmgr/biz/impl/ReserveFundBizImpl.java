package com.ibs.core.module.cnlmgr.biz.impl;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.SumAmountDto;
import com.ibs.core.module.cnlmgr.biz.IReserveFundBiz;
import com.ibs.core.module.cnlmgr.dao.ICnlSysIntfDao;
import com.ibs.core.module.cnlmgr.domain.CnlSysIntf;
import com.ibs.core.module.violationRecord.dao.ISysViolationRecordOverSpendDao;
import com.ibs.core.module.violationRecord.domain.SysViolationRecord;

import com.ibs.core.module.mdmbasedata.common.Constants;

public class ReserveFundBizImpl implements IReserveFundBiz{
	
	private ICnlSysIntfDao cnlSysIntfDao;
	private ICnlTransDao cnlTransDao;
	private ISysViolationRecordOverSpendDao sysViolationRecordOverSpendDao;
	
	private CnlSysIntf cnlSysIntf;
	
	private SysViolationRecord sysViolationRecord = new SysViolationRecord();
	//private CnlTrans cnlTransSave = new CnlTrans();
	
	public void checkAmount(CnlTrans cnlTrans){
		String custCode = null;
		BigDecimal transAmount = null;
		BigDecimal daySum = null;
		BigDecimal weekSum = null;
		BigDecimal monthSum = null;
		BigDecimal yearSum = null;
		SumAmountDto sumAmountDto = null;
		
		cnlSysIntf = cnlSysIntfDao.findByCnlCnlCode(cnlTrans.getCnlCnlCode());
		//商户号
		if(cnlTrans.getCustCode() != null)
			custCode = cnlTrans.getCustCode();
	    //交易时间
		Date transTime = cnlTrans.getTransTime();
		//获取一天的开始时间
		Date startTime = startDay(transTime);
		//获取一天的结束时间
		Date endTime = endTime(transTime);
		//获取当前时间的前一周时间
		Date startWeek = startWeek(transTime);
		//一周结束时间
		Date endWeek = endWeek(transTime);
		//获取交易时间的月初时间
		Date startMonth = startMonth(transTime);
		//交易时间年初时间
		Date startYear = startYear(transTime);
		if(custCode != null && transTime != null){
			sumAmountDto = cnlTransDao.sumAmount(custCode, startTime, endTime, startWeek, endWeek, startMonth, startYear);
		}
		//设置sysViolationRecord
		sysViolationRecord.setReqNum(cnlTrans.getReqNum());
		sysViolationRecord.setCnlIntfCode(cnlSysIntf.getAccessCode());
		sysViolationRecord.setViolationId(cnlTrans.getTransAmount().toString());
		sysViolationRecord.setViolationType(Constants.VIOLATION_TYPE_PAYMENT_EXCEED_LIMIT);
		//设置cnl_trans
		/*cnlTransSave.setCreateTime(cnlTrans.getCreateTime());
		cnlTransSave.setCnlCnlCode(cnlTrans.getCnlCnlCode());
		cnlTransSave.setReqInnerNum(cnlTrans.getReqInnerNum());
		cnlTransSave.setTransNum(cnlTrans.getTransNum());
		cnlTransSave.setBankTransNum(cnlTrans.getBankTransNum());
		cnlTransSave.setTransCurrency(cnlTrans.getTransCurrency());
		cnlTransSave.setTransDc(cnlTrans.getTransDc());
		cnlTransSave.setTransType(cnlTrans.getTransType());		
		cnlTransSave.setTransStatus(cnlTrans.getTransStatus());
		cnlTransSave.setBnakHandlePriority(cnlTrans.getBnakHandlePriority());       
		cnlTransSave.setBankReqTrnasDate(cnlTrans.getBankReqTrnasDate());
		cnlTransSave.setBankReqTransTime(cnlTrans.getBankReqTransTime());
		cnlTransSave.setBnakServiceFeeAct(cnlTrans.getBnakServiceFeeAct());
		cnlTransSave.setBankAccepteTime(cnlTrans.getBankAccepteTime());
		cnlTransSave.setBankReturnTime(cnlTrans.getBankReturnTime());
		cnlTransSave.setBankDebitName(cnlTrans.getBankDebitName());
		cnlTransSave.setBankDebitCustName(cnlTrans.getBankDebitCustName());
		cnlTransSave.setBankDebitCardNum(cnlTrans.getBankDebitCardNum());
		cnlTransSave.setBankCreditName(cnlTrans.getBankCreditName());
		cnlTransSave.setBankCreditCustName(cnlTrans.getBankCreditCustName());
		cnlTransSave.setBankCreditCardNum(cnlTrans.getBankCreditCardNum());
		cnlTransSave.setBankPmtCnlType(cnlTrans.getBankPmtCnlType());
		cnlTransSave.setTermialType(cnlTrans.getTermialType());
		cnlTransSave.setIsinGl(cnlTrans.getIsinGl());
		cnlTransSave.setIsPrinted(cnlTrans.getIsPrinted());
		cnlTransSave.setPrintedTime(cnlTrans.getPrintedTime());*/
		
		if(cnlTrans.getTransAmount() != null){
			transAmount = cnlTrans.getTransAmount();
			daySum = sumAmountDto.getDayAmount().add(transAmount);
			weekSum = sumAmountDto.getWeekAmount().add(transAmount);
			monthSum = sumAmountDto.getMonthAmount().add(transAmount);
			yearSum = sumAmountDto.getYearAmount().add(transAmount);
			
			if(transAmount.compareTo(cnlSysIntf.getPerTransLimit()) == 1){
				sysViolationRecord.setViolationDesc(String.format("支付金额大于单笔支付限额(%s)",cnlSysIntf.getPerTransLimit().toString()));
				sysViolationRecordOverSpendDao.saveOrUpdate(sysViolationRecord);
				//cnlTransDao.saveOrUpdate(cnlTransSave);
			}else if(daySum.compareTo(cnlSysIntf.getDayLimit()) == 1){
				sysViolationRecord.setViolationDesc(String.format("天总支付金额大于每天支付限额(%s)",cnlSysIntf.getDayLimit().toString()));
				sysViolationRecordOverSpendDao.saveOrUpdate(sysViolationRecord);
				//cnlTransDao.saveOrUpdate(cnlTransSave);
			}else if(weekSum.compareTo(cnlSysIntf.getWeekLimit()) == 1){
				sysViolationRecord.setViolationDesc(String.format("周总金额大于每周支付限额(%s)",cnlSysIntf.getWeekLimit().toString()));
				sysViolationRecordOverSpendDao.saveOrUpdate(sysViolationRecord);
				//cnlTransDao.saveOrUpdate(cnlTransSave);
			}else if(monthSum.compareTo(cnlSysIntf.getMonthLimit()) == 1){
				sysViolationRecord.setViolationDesc(String.format("月总金额大于每月支付限额(%s)",cnlSysIntf.getMonthLimit().toString()));
				sysViolationRecordOverSpendDao.saveOrUpdate(sysViolationRecord);
				//cnlTransDao.saveOrUpdate(cnlTransSave);
			}else if(yearSum.compareTo(cnlSysIntf.getYearLimit()) == 1){
				sysViolationRecord.setViolationDesc(String.format("年总金额大于每年支付限额(%s)",cnlSysIntf.getYearLimit().toString()));
				sysViolationRecordOverSpendDao.saveOrUpdate(sysViolationRecord);
				//cnlTransDao.saveOrUpdate(cnlTransSave);
			}
		}
		
	}
	//获取一天开始时间
	public Date startDay(Date transTime){
		Calendar cal = Calendar.getInstance();
		Date startDay = null;
		if(transTime != null){
			cal.setTime(transTime);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			startDay = cal.getTime();
		}
		return startDay;
	}
	//获取一天的结束时间
	public Date endTime(Date transTime){
		Calendar cal = Calendar.getInstance();
		Date endTime = null;
		if(transTime != null){
			cal.setTime(transTime);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			endTime = cal.getTime();
		}
		return endTime;
	}
	//得到交易时间的一周的第一天
	public Date startWeek(Date transTime){
		Calendar cal = Calendar.getInstance();
		Date startWeek = null;
		if(transTime != null){
			cal.setTime(transTime);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			startWeek = cal.getTime();
		}
		return startWeek;
	}
	//得到交易时间的一周的最后一天
	public Date endWeek(Date transTime){
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		Date endWeek = null;
		if(transTime != null){
			cal.setTime(transTime);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //星期天
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			endWeek = cal.getTime();
		}
		return endWeek;
	}
	//月初日期
	public Date startMonth(Date transTime){
		Date startMonth = null;
		if(transTime != null){
			Calendar c = Calendar.getInstance();  
			c.setTime(transTime);
			int day = c.getMinimum(Calendar.DAY_OF_MONTH);// 取得当前月的最小日期(天)
			c.set(Calendar.DAY_OF_MONTH, day);// 设置天
			c.set(Calendar.HOUR_OF_DAY,0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			startMonth = c.getTime();
		}	
		return startMonth;
	}
	//年初日期
	public Date startYear(Date transTime){
		Date startYear = null;
		Calendar c = Calendar.getInstance();  
		if(transTime != null){
			c.setTime(transTime);
			int year = c.get(Calendar.YEAR);
			c.set(year, 1, 1, 0, 0, 0);
			startYear = c.getTime();
		}
		return startYear;
	}

	public ICnlSysIntfDao getCnlSysIntfDao() {
		return cnlSysIntfDao;
	}

	public void setCnlSysIntfDao(ICnlSysIntfDao cnlSysIntfDao) {
		this.cnlSysIntfDao = cnlSysIntfDao;
	}

	public ICnlTransDao getCnlTransDao() {
		return cnlTransDao;
	}

	public void setCnlTransDao(ICnlTransDao cnlTransDao) {
		this.cnlTransDao = cnlTransDao;
	}

	public ISysViolationRecordOverSpendDao getSysViolationRecordOverSpendDao() {
		return sysViolationRecordOverSpendDao;
	}

	public void setSysViolationRecordOverSpendDao(ISysViolationRecordOverSpendDao sysViolationRecordOverSpendDao) {
		this.sysViolationRecordOverSpendDao = sysViolationRecordOverSpendDao;
	}

	public CnlSysIntf getCnlSysIntf() {
		return cnlSysIntf;
	}

	public void setCnlSysIntf(CnlSysIntf cnlSysIntf) {
		this.cnlSysIntf = cnlSysIntf;
	}
	
}
