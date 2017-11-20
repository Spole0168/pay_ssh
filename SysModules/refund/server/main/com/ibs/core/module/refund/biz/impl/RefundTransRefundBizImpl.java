/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.refund.biz.impl;

import java.util.Date;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.refund.biz.IRefundTransRefundBiz;
import com.ibs.core.module.refund.dao.IRefundTransRefundDao;
import com.ibs.core.module.refund.dao.IRefundTransTraceOptDao;
import com.ibs.core.module.refund.domain.RefundTransRefund;
import com.ibs.core.module.refund.domain.RefundTransTraceOpt;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;


/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_REFUND
 * @modify		: your comments goes here (author,date,reason).
 */
public class RefundTransRefundBizImpl extends BaseBiz implements IRefundTransRefundBiz {

	private IRefundTransRefundDao refundTransRefundDao;
	private IRefundTransTraceOptDao	refundTransTraceOptDao;
	private IExcelService excelService;
	RefundTransTraceOpt refundTransTraceOpt = null;
	@Override
	public IPage<RefundTransRefund> findRefundTransRefundByPage(QueryPage queryPage) {
		logger.info("entering action::CnlTransRefundBizImpl.findCnlTransRefundByPage()...");
		return refundTransRefundDao.findRefundTransRefundByPage(queryPage);
	}

	@Override
	public RefundTransRefund getRefundTransRefundById(String id) {
		logger.info("entering action::CnlTransRefundBizImpl.getCnlTransRefundById()...");
		return refundTransRefundDao.load(id);
	}

	@Override
	public RefundTransRefund saveRefundTransRefund(RefundTransRefund cnlTransRefund) {
		logger.info("entering action::CnlTransRefundBizImpl.saveCnlTransRefund()...");
		//设置操作信息
		Date createTime = new Date();
		String creator = this.getUserName();
		cnlTransRefund.setCreateTime(createTime);
		cnlTransRefund.setCreator(creator);
		//01-待审核,02-审核成功,03-审核失败,04-未退款,05-退款中,06-退款成功
		cnlTransRefund.setRefundStatus("01");
		cnlTransRefund.setRefundTime(createTime);//申请退款时间
		cnlTransRefund.setHandler(creator);
		cnlTransRefund.setHandleTime(createTime);
		String uuid = StringUtils.getUUID();
		cnlTransRefund.setRefundCode(uuid);				//退款申请单单号
		cnlTransRefund.setId(null);
		
		refundTransRefundDao.saveOrUpdate(cnlTransRefund);
		//记录操作
		refundTransTraceOpt = new RefundTransTraceOpt();
		refundTransTraceOpt.setRefNum(uuid);
		refundTransTraceOpt.setCreateTime(createTime);
		refundTransTraceOpt.setCreator(creator);
		refundTransTraceOpt.setOperator(creator);
		refundTransTraceOpt.setOptTime(createTime);
		refundTransTraceOpt.setOptCode("");
		refundTransTraceOpt.setOptDesc("退款管理_新增退款申请");
		refundTransTraceOpt.setOptResult("");
		refundTransTraceOptDao.saveOrUpdate(refundTransTraceOpt);
		return cnlTransRefund;
	}

	@Override
	public RefundTransRefund updateRefundTransRefund(RefundTransRefund cnlTransRefund) {
		logger.info("entering action::CnlTransRefundBizImpl.updateCnlTransRefund()...");
		RefundTransRefund cnlTransRefundTemp = refundTransRefundDao.load(cnlTransRefund.getId());
		//设置操作信息
		Date updateTime = new Date();
		String updator = this.getUserName();
		cnlTransRefundTemp.setUpdateTime(updateTime);
		cnlTransRefundTemp.setUpdator(updator);
		//01-待审核,02-审核成功,03-审核失败,04-未退款,05-退款中,06-退款成功
		cnlTransRefundTemp.setRefundStatus("01");
		cnlTransRefundTemp.setVoucherLocation(cnlTransRefund.getVoucherLocation());
		cnlTransRefundTemp.setRefundContact(cnlTransRefund.getRefundContact());
		cnlTransRefundTemp.setRefundContactTel(cnlTransRefund.getRefundContactTel());
		cnlTransRefundTemp.setRefundReason(cnlTransRefund.getRefundReason());
		cnlTransRefundTemp.setBankDebitCustName(cnlTransRefund.getBankDebitCustName());
		cnlTransRefundTemp.setBankDebitName(cnlTransRefund.getBankDebitName());
		cnlTransRefundTemp.setBankDebitCardNum(cnlTransRefund.getBankDebitCardNum());
		cnlTransRefundTemp.setRefundAmount(cnlTransRefund.getRefundAmount());
		
		cnlTransRefundTemp.setBankTransNum(cnlTransRefund.getBankTransNum());
		cnlTransRefundTemp.setVoucherNum(cnlTransRefund.getVoucherNum());
		
		refundTransRefundDao.saveOrUpdate(cnlTransRefundTemp);
		//记录操作
		refundTransTraceOpt = new RefundTransTraceOpt();
		refundTransTraceOpt.setUpdator(updator);
		refundTransTraceOpt.setUpdateTime(updateTime);
		refundTransTraceOpt.setOperator(updator);
		refundTransTraceOpt.setOptTime(updateTime);
		refundTransTraceOpt.setOptCode("");
		refundTransTraceOpt.setOptDesc("退款管理_修改退款申请");
		
		refundTransTraceOptDao.saveOrUpdate(refundTransTraceOpt);
		return cnlTransRefund;
	}

	@Override
	public void exportRefundTransRefund(ExportSetting exportSetting) {
		logger.info("entering action::CnlTransRefundBizImpl.exportCnlTransRefund()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<RefundTransRefund> cnlTransRefundPage = (Page<RefundTransRefund>) refundTransRefundDao.findRefundTransRefundByPage(exportSetting);

		excelService.exportToFile(cnlTransRefundPage.getRows(), exportSetting);
	}


	public IRefundTransRefundDao getRefundTransRefundDao() {
		return refundTransRefundDao;
	}

	public void setRefundTransRefundDao(IRefundTransRefundDao refundTransRefundDao) {
		this.refundTransRefundDao = refundTransRefundDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CnlTransRefundBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CnlTransRefundBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	public IRefundTransTraceOptDao getRefundTransTraceOptDao() {
		return refundTransTraceOptDao;
	}

	public void setRefundTransTraceOptDao(IRefundTransTraceOptDao refundTransTraceOptDao) {
		this.refundTransTraceOptDao = refundTransTraceOptDao;
	}

	@Override
	public void verifyTransRefund(RefundTransRefund refundTransRefund) {
		logger.info("entering action::CnlTransRefundBizImpl.updateCnlTransTrace()...");
		//修改退款申请单数据
		refundTransRefundDao.saveOrUpdate(refundTransRefund);
	}
	//
	private boolean checkFormData(RefundTransRefund refund){
		boolean isLegal = false;
		//请求退款联系人，请求退款联系人电话，退款原因，收款方开户名，收款方银行名称，收款方银行卡号，退款金额
		if(null==refund){
			return isLegal;
		}
		if("".equals(refund.getRefundContact())||refund.getRefundContact().length()>50){
			return isLegal;
		}
		//联系人电话
		if("".equals(refund.getRefundContactTel())||refund.getRefundContactTel().length()>20||!refund.getRefundContactTel().matches("[0-9]{10,15}")){
			return isLegal;
		}
		//银行卡号码
		if("".equals(refund.getBankDebitCardNum())||refund.getBankDebitCardNum().length()>50||!refund.getBankDebitCardNum().matches("[0-9]{10,20}")){
			return isLegal;
		}
		//退款金额
		if("".equals(refund.getRefundAmount())||refund.getRefundAmount()<=0){
			return isLegal;
		}
		if("".equals(refund.getRefundReason())||refund.getRefundReason().length()>200){
			return isLegal;
		}
		if("".equals(refund.getBankDebitName())||refund.getBankDebitName().length()>50){
			return isLegal;
		}
		if("".equals(refund.getBankDebitCustName())||refund.getBankDebitCustName().length()>50){
			return isLegal;
		}
		return true;
	}
//	public static void main(String[] args) {
//		String str = "15090908990";
//		System.out.println(str.matches("[0-9]{10,15}"));
//		System.out.println(str.matches("/d"));
//	}
}

