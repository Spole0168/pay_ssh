package com.ibs.core.module.account.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.dao.ICnlCustAcntDtlDao;
import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.dao.ICnlTransTraceDao;
import com.ibs.core.module.account.dao.ICnlTransTraceHisDao;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceHis;
import com.ibs.core.module.account.service.ITmpSettlementService;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.util.BeanUtils;

public class TmpSettlementServiceImpl extends BaseBiz implements ITmpSettlementService {
	private ICnlReqTransDao cnlReqTransDao;
	private ICnlTransTraceDao cnlTransTraceDao;
	private ICnlCustAcntDao cnlCustAcntDao;
	private ICnlTransDao cnlTransDao;
	private ICnlTransTraceHisDao cnlTransTraceHisDao;
	private ICnlCustAcntDtlDao cnlCustAcntDtlDao;
	//状态值，告诉程序在那个位置出错了
	private int anarchy = 0;
	
	private String lost = "00";
	
	public ICnlTransTraceHisDao getCnlTransTraceHisDao() {
		return cnlTransTraceHisDao;
	}

	public void setCnlTransTraceHisDao(ICnlTransTraceHisDao cnlTransTraceHisDao) {
		this.cnlTransTraceHisDao = cnlTransTraceHisDao;
	}

	public ICnlCustAcntDtlDao getCnlCustAcntDtlDao() {
		return cnlCustAcntDtlDao;
	}

	public void setCnlCustAcntDtlDao(ICnlCustAcntDtlDao cnlCustAcntDtlDao) {
		this.cnlCustAcntDtlDao = cnlCustAcntDtlDao;
	}

	public ICnlReqTransDao getCnlReqTransDao() {
		return cnlReqTransDao;
	}

	public void setCnlReqTransDao(ICnlReqTransDao cnlReqTransDao) {
		this.cnlReqTransDao = cnlReqTransDao;
	}

	public ICnlTransTraceDao getCnlTransTraceDao() {
		return cnlTransTraceDao;
	}

	public void setCnlTransTraceDao(ICnlTransTraceDao cnlTransTraceDao) {
		this.cnlTransTraceDao = cnlTransTraceDao;
	}

	public ICnlCustAcntDao getCnlCustAcntDao() {
		return cnlCustAcntDao;
	}

	public void setCnlCustAcntDao(ICnlCustAcntDao cnlCustAcntDao) {
		this.cnlCustAcntDao = cnlCustAcntDao;
	}

	public ICnlTransDao getCnlTransDao() {
		return cnlTransDao;
	}

	public void setCnlTransDao(ICnlTransDao cnlTransDao) {
		this.cnlTransDao = cnlTransDao;
	}
	
	/**
	 * 
	 * */
	
	/**
	 * 对外接口方法 需外界传参清算号进行验证操作 更新或返回异常
	 */
	public boolean stlTrans(String stlNum) {
		logger.info("stlNum:"+stlNum);
		anarchy = 0;
		if (stlNum == null || stlNum.equals("")) {
			return false;
		}
		
		boolean res = false;
		// 状态：01：已受理 02：处理中 03：成功 04：失败 05：异常数据

		// 1 - validate开始验证批次和记录数
		res = this.validateBatch(stlNum);
		// 1.1 - update status by validate result
		// 根据返回值来判断是否更改状态成功还是失败
		if (res == false) {
			if (anarchy == 1) {
				this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_DATA, Constants.FAIL_CODE_07,Constants.FAIL_CODE_07_VALUE);
			} else if (anarchy == 2) {
				this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_DATA, Constants.FAIL_CODE_08,Constants.FAIL_CODE_08_VALUE);
			}
			return false;
		}
		logger.info("ReqTotalBatch==ReqBatch,ReqTotalCnt==ReqCnt,true");

		// 第二步验证两个交易方向的交易金额是否相等
		// 1.2 - update status by validate result
		// 根据返回值来判断是否更改状态成功还是失败
		res = this.yoySumBalance(stlNum);
		if (res == false) {
			this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_DATA, Constants.FAIL_CODE_06,Constants.FAIL_CODE_06_VALUE);
			return false;
		}
		logger.info("TRANS_DC(SumBalance),true");
		// 第三步验证总账户余额加或减交易金额是否等于交易最新余额
		// 1.3 - update status by validate result
		// 根据返回值来判断是否更改状态成功还是失败
		this.validateBalance(stlNum);
		if (anarchy == 3) {
			this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_13,Constants.REQ_TRANS_ERROR_CODE_13_VALUE);
			return false;
		}
		
		if(anarchy == 11){
			this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_14,Constants.REQ_TRANS_ERROR_CODE_14_VALUE);
			return false;
		}
		logger.info("Balance,true");
		// 2 - process
		//更新状态操作
		this.updateTransTrace(stlNum, Constants.REQ_STATUS_SUCCESS, lost,Constants.TRANS_STATUS_SUCCESS_VALUE);
		this.updateReqTransStatus(stlNum, Constants.REQ_STATUS_SUCCESS, lost,Constants.TRANS_STATUS_SUCCESS_VALUE);
		logger.info("REQ_STATUS,TRANS_STATUS,true");
		// 把CnlTransTrace表中的数据复制到Cnltrans这张表里
		this.saveTrans(stlNum);
		if(anarchy==4){return false;}
		logger.info("SAVE CNL_TRANS,true");
		// 更新明细表
		this.saveTransDtl(stlNum);
		if(anarchy==6){return false;}
		logger.info("SAVE CNL_CUST_ACNT_DTL,true");
		// 上面三步验证通过后，进行总账户余额的更新操作
		this.updateAcnt(stlNum);
		if(anarchy==5){return false;}
		logger.info("UPDATE CNL_CUST_ACNT,true");
		// 更新到历史表中
		this.saveTransTraceHis(stlNum);
		if(anarchy==7){return false;}
		logger.info("SAVE CNL_TRANS_TRACE_HIS,true");
		// 删除CnlTransTrace这张表中的数据
		this.deleTransTrace(stlNum);
		logger.info("DELETE CNL_TRANS_TRACE,true");
		// 完成后返回成
		return true;
	}

	/**
	 * 私有验证方法 根据传递过来的清算号进行
	 *
	 */
	private boolean validateBatch(String stlNum) {
		boolean b = false;
		List<CnlReqTrans> list = cnlReqTransDao.findTrans(stlNum);
		int total = 0;
		for (CnlReqTrans res : list) {
			if (list.size() == res.getReqTotalBatch()) {
				total += res.getReqCnt();
				b = true;
			} else {
				anarchy = 1;
				b = false;
				return b;
			}
		}
		CnlReqTrans cnlReqTrans = list.get(0);
		Long l = cnlReqTrans.getReqTotalCnt();
		if (null==l){
			anarchy = 2;
			b = false;
			return b;
		} else {
			if(total != l){
				anarchy = 2;
				b = false;
				return b;
			}else{
				int j = cnlTransTraceDao.countStlNum(stlNum);
				if (j != list.get(0).getReqTotalCnt()) {
					anarchy = 2;
					b = false;
					return b;
				}
			}
			b = true;
		}
		return b;
	}

	/**
	 * 私有验证方法 在上面调用此方法进行验证方向金额总数是否相同 成功向下进行，失败进行异常
	 */
	private boolean yoySumBalance(String stlNum) {
		boolean SumBalance = false;
		List<CnlReqTrans> list = cnlReqTransDao.findTrans(stlNum);
		String cnlCnlCode = list.get(0).getCnlCnlCode();

		BigDecimal debit = cnlTransTraceDao.findSumTransAmount(stlNum, cnlCnlCode, Constants.TRANS_DC_OUTGOING);
		BigDecimal credit = cnlTransTraceDao.findSumTransAmount(stlNum, cnlCnlCode, Constants.TRANS_DC_INCOMING);

		BigDecimal a = new BigDecimal("0");

		if (a.compareTo(debit) != 0 && a.compareTo(credit) != 0) {
			if (debit.compareTo(credit) != 0) {
				SumBalance = false;
				return SumBalance;
			} else {
				SumBalance = true;
			}
		} else {
			return SumBalance;
		}
		return SumBalance;
	}

	/**
	 * 私有验证方法 调用此方法进行余额验证 成功向下进行，失败进行异常
	 */
	private void validateBalance(String stlNum) {
		anarchy=0;
		List<CnlTransTrace> transTraces = cnlTransTraceDao.findDcBalAnce(stlNum);
		BigDecimal bal = null;
		for (CnlTransTrace DC : transTraces) {
			if (DC.getTransDc().equals(Constants.TRANS_DC_INCOMING)) {
				bal = cnlCustAcntDao.findByBal(DC.getCnlCustCode());
				if(bal != null){
					if ((bal.add(DC.getTransAmount())).compareTo(DC.getTransLatestAmount()) != 0) {
						anarchy = 3;
						this.updateTransTraceStatus(DC.getCnlCustCode(), stlNum, Constants.TRANS_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_13,Constants.REQ_TRANS_ERROR_CODE_13_VALUE);
					}
				}else{
					anarchy = 11;
					this.updateTransTraceStatus(DC.getCnlCustCode(), stlNum, Constants.TRANS_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_14,Constants.REQ_TRANS_ERROR_CODE_14_VALUE);
				}
			} else if (DC.getTransDc().equals(Constants.TRANS_DC_OUTGOING)) {
				bal = cnlCustAcntDao.findByBal(DC.getCnlCustCode());
				if(bal != null){
					if ((bal.subtract(DC.getTransAmount())).compareTo(DC.getTransLatestAmount()) != 0) {
						anarchy = 3;
						this.updateTransTraceStatus(DC.getCnlCustCode(), stlNum, Constants.TRANS_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_13,Constants.REQ_TRANS_ERROR_CODE_13_VALUE);
					}
				}else{
					anarchy = 11;
					this.updateTransTraceStatus(DC.getCnlCustCode(), stlNum, Constants.TRANS_STATUS_DATA, Constants.REQ_TRANS_ERROR_CODE_14,Constants.REQ_TRANS_ERROR_CODE_14_VALUE);
				}
			}
		}
	}

	/**
	 * 私有更新总账余额方法 确认验证无误后进行更新总账余额
	 * 
	 */
	private void updateAcnt(String stlNum) {
		cnlCustAcntDao.updateAmount(stlNum);
		/*List<CnlTransTrace> transTrace = cnlTransTraceDao.findDcBalAnce(stlNum);
		try {
			for (int i = 0; i < transTrace.size(); i++) {
				CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();
				String custCode = transTrace.get(i).getCnlCustCode();
				BigDecimal b = transTrace.get(i).getTransLatestAmount();
				// 根据渠道客户编号查询出总账号表中所有数据
				cnlCustAcnt = cnlCustAcntDao.loadBy(custCode);
				// 把总账户余额替换成交易后最新余额
				cnlCustAcnt.setBalance(b);
				// 进行更新操作
				cnlCustAcntDao.saveOrUpdate(cnlCustAcnt);
			}
		} catch (Exception e) {
			anarchy=5;
		}*/
	}

	/**
	 * 历史表迁移 把transtrace流水表中的数据放入历史表中
	 */
	private void saveTransTraceHis(String stlNum) {
		try {
			List<CnlTransTraceHis> his = new ArrayList<CnlTransTraceHis>();
			List<CnlTransTrace> transTraces = cnlTransTraceDao.findDcBalAnce(stlNum);
			for (int i = 0; i < transTraces.size(); i++) {
				CnlTransTrace src = transTraces.get(i);
				CnlTransTraceHis tar = new CnlTransTraceHis();
				BeanUtils.copyBasicTypeProperties(tar, src);
				tar.setId(null);
				tar.setTransStatus(Constants.TRANS_STATUS_SUCCESS);
				his.add(tar);
			}
			cnlTransTraceHisDao.save(his);
		} catch (InvocationTargetException e) {
			anarchy=7;
		}
	}

	/**
	 * 交易进明细表
	 * 
	 */
	private void saveTransDtl(String stlNum) {
		try {
			List<CnlCustAcntDtl> list = new ArrayList<CnlCustAcntDtl>();
			List<CnlTrans> cnltrans = cnlTransDao.findTransDtl(stlNum);
			for (CnlTrans t : cnltrans) {
				CnlCustAcntDtl dtl = new CnlCustAcntDtl();
				BigDecimal b = cnlCustAcntDao.findTransAmount(t.getCnlCustCode());
				dtl.setCnlCustCode(t.getCnlCustCode());
				dtl.setTransNum(t.getTransNum());
				dtl.setCurrency(t.getTransCurrency());
				dtl.setAmount(t.getTransAmount());
				dtl.setDirection(t.getTransDc());
				dtl.setTransType(t.getTransType());
				dtl.setPreBalance(b);
				dtl.setBalance(t.getTransLatestAmount());
				dtl.setTransDate(t.getTransDate());
				dtl.setTransTime(t.getTransTime());
				dtl.setIsPrinted(t.getIsPrinted());
				dtl.setIsValid(t.getIsValid());
				list.add(dtl);
			}
			cnlCustAcntDtlDao.save(list);
		} catch (Exception e) {
			anarchy=6;
		}

	}

	/**
	 * 私有更新状态方法 确认验证无误后进行更新状态
	 * 
	 */
	
	 /* private void updateStatus(String stlNum,String handleStatus,String
	  reqDataErr){
	  cnlTransTraceDao.updateStatus(stlNum,handleStatus,reqDataErr); }*/
	 

	/**
	 * 私有新增方法 确认验证无误后进行更新状态
	 * 
	 */

	private void saveTrans(String stlNum) {
		try {
			List<CnlTrans> transList = new ArrayList<CnlTrans>();
			List<CnlTransTrace> ct = cnlTransTraceDao.findDcBalAnce(stlNum);
			for (int i = 0; i < ct.size(); i++) {
				CnlTransTrace src = ct.get(i);
				CnlTrans tar = new CnlTrans();
				BeanUtils.copyBasicTypeProperties(tar, src);
				tar.setId(null);
				tar.setTransStatus(Constants.TRANS_STATUS_SUCCESS);
				tar.setTransNum(src.getBankTransNum());
				tar.setIsinGl(Constants.ISIN_GL_RECORDED);
				Date updateTime = new Date();
				tar.setInglTime(updateTime);
				tar.setUpdateTime(updateTime);
				tar.setUpdator(Constants.SYSADMIN);
				transList.add(tar);
			}
			cnlTransDao.saveBatch(transList);
		} catch (InvocationTargetException e) {
			anarchy=4;
		}
	}

	/**
	 * 私有删除方法 确认验证无误后进行删除TransTrace表中数据
	 */
	private void deleTransTrace(String stlNum) {
		cnlTransTraceDao.deleTransTrace(stlNum);
	}

	/**
	 * 更新REQ_TRANS这张表中的状态
	 * 错误或成功
	 */
	private void updateReqTransStatus(String stlNum, String handleStatus, String reqDataErr,String errMsg) {
		Date updateTime = new Date();
		cnlReqTransDao.updateStatus(stlNum, handleStatus, reqDataErr,errMsg,updateTime,Constants.SYSADMIN);
	}
	/**
	 * 单条数据状态更新操作
	 * 根据清算号和唯一code确定每一条错误的数据，状态改掉
	 * 以方便后面查询使用
	 * */
	private void updateTransTraceStatus(String cnlCustCode, String stlNum, String handleStatus, String reqDataErr,String errMsg) {
		Date updateTime = new Date();
		cnlTransTraceDao.updateStatus(cnlCustCode, stlNum, handleStatus, reqDataErr,errMsg,updateTime,Constants.SYSADMIN);
	}
	
	/**
	 * 数据状态更新操作
	 * 根据清算号确定的数据，状态改掉
	 * 以方便后面查询使用
	 * */
	private void updateTransTrace (String stlNum, String handleStatus, String reqDataErr,String errMsg) {
		Date updateTime = new Date();
		cnlTransTraceDao.updateTransTrace(stlNum, handleStatus, reqDataErr,errMsg,updateTime,Constants.SYSADMIN);
	}
}
