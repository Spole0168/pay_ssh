package com.ibs.core.module.account.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ibs.core.module.account.common.ConstantsCnlTrans;
import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.dao.ICnlCustAcntDtlDao;
import com.ibs.core.module.account.dao.ICnlCustTraceBankVldDao;
import com.ibs.core.module.account.dao.ICnlCustTraceDao;
import com.ibs.core.module.account.dao.ICnlReqTransDao;
import com.ibs.core.module.account.dao.ICnlTransDao;
import com.ibs.core.module.account.dao.ICnlTransTraceDao;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustAcntDtl;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.core.module.account.domain.CnlCustTraceBankVld;
import com.ibs.core.module.account.domain.CnlReqTrans;
import com.ibs.core.module.account.domain.CnlTrans;
import com.ibs.core.module.account.domain.CnlTransServiceAccountParam;
import com.ibs.core.module.account.domain.CnlTransServiceClearing;
import com.ibs.core.module.account.domain.CnlTransTrace;
import com.ibs.core.module.account.domain.CnlTransTraceService;
import com.ibs.core.module.account.domain.CnlTransTraceServiceBalance;
import com.ibs.core.module.account.domain.Reconciliation;
import com.ibs.core.module.account.service.ICnlCustAcntService;
import com.ibs.core.module.account.service.ICnlTransService;
import com.ibs.core.module.account.utils.TransServiceUtil;
import com.ibs.core.module.blacklist.biz.ISysNamelistBiz;
import com.ibs.core.module.blacklist.dto.BlackListToNoticDto;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.core.module.cnlcust.dto.CnlCustCompanyDto;
import com.ibs.core.module.cnlcust.dto.CnlCustDto;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;
import com.ibs.core.module.cnlcust.service.ICnlCustService;
import com.ibs.core.module.cnlcust.utils.BeanUtil;
import com.ibs.core.module.corcust.dao.ICorBankAcntTransDtlDao;
import com.ibs.core.module.corcust.domain.CorBankAcntTransDtl;
import com.ibs.core.module.interfaces.dao.ICnlMsgDao;
import com.ibs.core.module.interfaces.domain.CnlMsg;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.StringUtils;
/**
 * 申请单，申请单明细服务类
 * @author duxiaolin
 *
 */
public class CnlTransServiceImpl extends BaseService implements ICnlTransService {

	private ICnlReqTransDao cnlReqTransDao; 
	private ICnlTransTraceDao cnlTransTraceDao;
	private ICnlTransDao cnlTransDao;
	private ICnlCustAcntDtlDao cnlCustAcntDtlDao;
	private ICnlCustAcntDao cnlCustAcntDao;
	private ICnlMsgDao cnlMsgDao;
	private ICnlCustTraceDao cnlCustTraceDao;
	private ICnlCustTraceBankVldDao cnlCustTraceBankVldDao;
	private ICnlCustDao cnlCustDao;
	private ICnlCustService cnlCustService;
	private ICnlCustAcntService cnlCustAcntService;
	private IPblBankDictDao pblBankDictDao;



	public IPblBankDictDao getPblBankDictDao() {
		return pblBankDictDao;
	}

	public void setPblBankDictDao(IPblBankDictDao pblBankDictDao) {
		this.pblBankDictDao = pblBankDictDao;
	}


	public ICnlCustAcntService getCnlCustAcntService() {
		return cnlCustAcntService;
	}

	public void setCnlCustAcntService(ICnlCustAcntService cnlCustAcntService) {
		this.cnlCustAcntService = cnlCustAcntService;
	}

	public ICnlCustService getCnlCustService() {
		return cnlCustService;
	}

	public void setCnlCustService(ICnlCustService cnlCustService) {
		this.cnlCustService = cnlCustService;
	}

	public ICnlCustDao getCnlCustDao() {
		return cnlCustDao;
	}

	public void setCnlCustDao(ICnlCustDao cnlCustDao) {
		this.cnlCustDao = cnlCustDao;
	}

	public ICnlCustTraceBankVldDao getCnlCustTraceBankVldDao() {
		return cnlCustTraceBankVldDao;
	}

	public void setCnlCustTraceBankVldDao(ICnlCustTraceBankVldDao cnlCustTraceBankVldDao) {
		this.cnlCustTraceBankVldDao = cnlCustTraceBankVldDao;
	}

	public ICnlCustTraceDao getCnlCustTraceDao() {
		return cnlCustTraceDao;
	}

	public void setCnlCustTraceDao(ICnlCustTraceDao cnlCustTraceDao) {
		this.cnlCustTraceDao = cnlCustTraceDao;
	}

	public ICnlMsgDao getCnlMsgDao() {
		return cnlMsgDao;
	}

	public void setCnlMsgDao(ICnlMsgDao cnlMsgDao) {
		this.cnlMsgDao = cnlMsgDao;
	}

	public ICnlCustAcntDao getCnlCustAcntDao() {
		return cnlCustAcntDao;
	}

	public void setCnlCustAcntDao(ICnlCustAcntDao cnlCustAcntDao) {
		this.cnlCustAcntDao = cnlCustAcntDao;
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

	public ICnlTransDao getCnlTransDao() {
		return cnlTransDao;
	}

	public void setCnlTransDao(ICnlTransDao cnlTransDao) {
		this.cnlTransDao = cnlTransDao;
	}

	public ICnlCustAcntDtlDao getCnlCustAcntDtlDao() {
		return cnlCustAcntDtlDao;
	}

	public void setCnlCustAcntDtlDao(ICnlCustAcntDtlDao cnlCustAcntDtlDao) {
		this.cnlCustAcntDtlDao = cnlCustAcntDtlDao;
	}

	/**
	 * 插入申请单流水信息
	 * @param reqTrans  映射数据库的实体类
	 */
	private String addCnlReqTrans(CnlReqTrans reqTrans)throws Exception {


		//生成申请单流水号
		String reqInnerNum = StringUtils.getUUID();
		//加入申请单流水号到CnlReqTrans对象
		reqTrans.setReqInnerNum(reqInnerNum);
		//保存CnlReqTrans对象数据
		cnlReqTransDao.saveOrUpdate(reqTrans);
		//返回申请单流水号
		return reqInnerNum;
	}

	/**
	 * 插入申请单跟踪信息
	 * @param traceList 映射数据库的实体类集合
	 */
	private void addCnlTransTrace(List<CnlTransTrace> traceList)throws Exception {
		//批量插入
		cnlTransTraceDao.saveBatch(traceList);
	}

	/**
	 * 待处理进流水
	 * @param cnlTransTraceId
	 */
	@Override
	public void moveReqTransToCnlTrans(String cnlTransTraceId)throws Exception {
		//根据编号查到CnlReqTrans
		CnlTransTrace cnlTransTrace = cnlTransTraceDao.load(cnlTransTraceId);
		//根据CnlTransTrace新new一个CnlTrans
		CnlTrans cnlTrans = new CnlTrans();
		BeanUtils.copyBasicTypeProperties(cnlTrans, cnlTransTrace);
		cnlTrans.setTransNum(StringUtils.getUUID());
		//保存CnlTrans对象数据
		cnlTrans.setId(null);
		cnlTrans.setTransStatus(ConstantsCnlTrans.TRANS_STATUS_SUCCESS);
		cnlTransDao.saveOrUpdate(cnlTrans);
	}

	/**
	 * 流水进明细
	 * 插入数据到CnlCustAcntDtl并保存
	 */
	@Override
	public void moveCnlTransToCnlCustAcntDtl(String cnlTransId)throws Exception {
		//根据编号查到CnlTrans
		CnlTrans cnlTrans = cnlTransDao.load(cnlTransId);
		//根据CnlTrans新new一个CnlCustAcntDtl
		CnlCustAcntDtl acntDtl = new CnlCustAcntDtl();

		BeanUtils.copyBasicTypeProperties(acntDtl, cnlTrans);
		acntDtl.setId(null);
		//填充数据
		acntDtl = TransServiceUtil.createCnlCustAcntDtl(acntDtl,cnlTrans);
		CnlCustAcnt acnt = cnlCustAcntDao.loadBy(acntDtl.getCnlCustCode());
		acntDtl.setCnlAcntCode(acnt.getCnlAcntCode());
		//保存CnlCustAcntDtl对象数据
		cnlCustAcntDtlDao.saveOrUpdate(acntDtl);
	}


	@Override
	/**
	 * 充值申请单
	 */
	public String pay(String cnlCode, String intfCode, String msgCode, Timestamp msgTime,String returnUrl) throws Exception{
		logger.info("增加充值申请单pay(String cnlCode, String intfCode, String msgCode, Timestamp msgTime)");
		return addCnlReqTrans(TransServiceUtil.req(1, cnlCode, intfCode, null, null, null, null, msgCode, msgTime,null,returnUrl));
	}
	
	@Override
	/**
	 * 提现申请单
	 */
	public String getMoney(String cnlCode, String intfCode,  String msgCode, Timestamp msgTime,String returnUrl)throws Exception {
		logger.info("增加提现申请单getMoney(String cnlCode, String intfCode,  String msgCode, Timestamp msgTime)");
		return addCnlReqTrans(TransServiceUtil.req(2, cnlCode, intfCode, null, null, null, null, msgCode, msgTime,null,returnUrl));
	}

	@Override
	/**
	 * 清结算申请单
	 */
	public String clearing(String cnlCode, String intfCode, String reqBatch, String reqTotalBatch, String reqCnt,
			String reqTotalCnt, String msgId, Timestamp msgTime, String stlNum,String returnUrl) throws Exception{
		logger.info("增加清结算申请单clearing(String cnlCode, String intfCode, String reqBatch, String reqTotalBatch, String reqCnt,"+
				"String reqTotalCnt, String msgId, Timestamp msgTime, String stlNum)");
		return addCnlReqTrans(TransServiceUtil.req(3, cnlCode, intfCode, reqBatch, reqTotalBatch, reqCnt, reqTotalCnt, msgId, msgTime, stlNum,returnUrl));
	}

	@Override
	/**
	 * 充值申请单跟踪
	 */
	public void payTrace(String reqInnerNum,String msgId, String orderNum, String cnlCustCode, String transAmount,
			Timestamp transDate, String transComments,String termialType,String cnlCnlCode) throws Exception{
		logger.info("增加充值申请单跟踪payTrace(String reqInnerNum,String msgId, String orderNum, String cnlCustCode, String transAmount,"
				+"Timestamp transDate, String transComments,String termialType,String cnlCnlCode)");
		if(getCnlCust(cnlCustCode)==false){
			if(logger.isErrorEnabled())
				logger.error("没有找此到客户的相关信息");
			throw new BizException("无效客户");
		}

		List<CnlTransTrace> list = new ArrayList<CnlTransTrace>();
		CnlTransTrace c = TransServiceUtil.trace(1, reqInnerNum, msgId,orderNum, cnlCustCode, transAmount, transDate, transComments, termialType,cnlCnlCode);
		//查看是否有重复充值记录
		if(cnlTransTraceDao.findByCnlOrderNum(c.getTransOrderNum())!=null){
			//修改成异常状态
			c.setTransStatus(Constants.TRANS_STATUS_DATA);
			//修改
			CnlReqTrans crt = cnlReqTransDao.findByInnerNum(reqInnerNum);
			crt.setReqStatus(Constants.REQ_STATUS_DATA);
			crt.setErrCode(Constants.GLOBAL_ERROR_205);
			//定义常量后更改
			crt.setErrMsg(c.getTransOrderNum()+" 订单号重复存在");
		}
		list.add(c);
		addCnlTransTrace(list);
		//update(l);
	}

	@Override
	/**
	 * 提现申请单跟踪
	 */
	public void getMoneyTrace(String reqInnerNum,String msgId, String cnlCustCode, String transAmount,
			Timestamp transDate, String transComments,String termialType,String cnlCnlCode)throws Exception {
		logger.info("增加提现申请单跟踪getMoneyTrace(String reqInnerNum,String msgId, String cnlCustCode, String transAmount,"
				+"Timestamp transDate, String transComments,String termialType,String cnlCnlCode)");
		//客户不存在验证
		if(getCnlCust(cnlCustCode)==false){
			if(logger.isErrorEnabled())
				logger.error("没有找此到客户的相关信息");
			throw new BizException("无效客户");
		}
		//校验可用余额
		BigDecimal balance = cnlCustAcntDao.cheackBlance(cnlCnlCode, cnlCustCode);
		BigDecimal amount = new BigDecimal(transAmount);
		if(balance.compareTo(amount)<0){
			if(logger.isErrorEnabled())
				logger.error("可用余额小于提现金额，差额:"+balance.compareTo(amount));
			throw new BizException("可用余额小于提现金额，差额"+balance.compareTo(amount));
		}
		//添加申请跟踪记录
		List<CnlTransTrace> list = new ArrayList<CnlTransTrace>();
		list.add(TransServiceUtil.trace(2, reqInnerNum,msgId,  null, cnlCustCode, transAmount, transDate, transComments, termialType,cnlCnlCode));
		addCnlTransTrace(list);
		//修改账户可用余额与冻结金额
		frozenBalance(cnlCustCode,new BigDecimal(transAmount));
		//update(l);
	}

	@Override
	/**
	 * 清结算申请单跟踪 
	 */
	public void clearingTrace(List<CnlTransTraceService> transTraceServices,String reqInnerNum,String StlNum,String reqNum, String reqBatch,String cnlCnlCode)throws Exception {
		logger.info("增加清结算申请单跟踪 clearingTrace(List<CnlTransTraceService> transTraceServices,String reqInnerNum,String StlNum,String reqNum, String reqBatch,String cnlCnlCode)");
		addCnlTransTrace(TransServiceUtil.traceList(transTraceServices,reqInnerNum,StlNum,reqNum,reqBatch,cnlCnlCode));

	}

	@Override
	/**
	 *是否已经插入值
	 */
	public boolean checkCnlOrderNumExists(String cnlOrderNum) {
		logger.info("是否重复插入checkCnlOrderNumExists(String cnlOrderNum)");
		if(cnlTransTraceDao.findByCnlOrderNum(cnlOrderNum)!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	/**
	 * 查询当前批次
	 */
	public Long reqTrans(String stlNum) {	
		logger.info("根据清算号查询当前批次checkCnlOrderNumExists(String cnlOrderNum)");
		//根据清算号获取最大值
		CnlReqTrans c = cnlReqTransDao.maxBatch(stlNum);
		//如果查到数据
		if(c!=null){
			return c.getReqBatch();
		}else{
			return Long.parseLong("0");
		}


	}

	@Override
	/**
	 * 更新银行接入信息
	 */
	public void updateTransTrace(String reqInnerNum, String BankNumCode) {
		logger.info("更新银行接入信息updateTransTrace(String reqInnerNum, String BankNumCode)");
		//通过内部交易流水号查到明细信息
		CnlTransTrace cnlTransTrace = cnlTransTraceDao.findByReqInnerNum(reqInnerNum);
		//填写银行流水号
		cnlTransTrace.setBankTransNum(BankNumCode);
		//修改交易状态
		cnlTransTrace.setTransStatus(ConstantsCnlTrans.TRANS_STATUS_ING);
		//修改银行介入时间
		cnlTransTrace.setBankAccepteTime(new Timestamp(System.currentTimeMillis()));
		//保存到数据库
		cnlTransTraceDao.saveOrUpdate(cnlTransTrace);

	}

	@Override
	/**
	 * 更新银行接入信息
	 */
	public void updateTransTraceByOrderNum(String orderNum, String BankNumCode) {
		logger.info("orderNum:" + orderNum + ", BankNumCode:" + BankNumCode);
		CnlTransTrace cnlTransTrace = cnlTransTraceDao.findByCnlOrderNum(orderNum);
		cnlTransTrace.setBankTransNum(BankNumCode);
		cnlTransTrace.setTransStatus(ConstantsCnlTrans.TRANS_STATUS_ING);
		cnlTransTrace.setBankAccepteTime(new Timestamp(System.currentTimeMillis()));
		logger.info("updateTransTrace-cnlTransTraceDao.saveOrUpdate:" + orderNum);
		cnlTransTraceDao.saveOrUpdate(cnlTransTrace);
	}

	@Override
	/**
	 * 更新银行接入信息
	 */
	public void updateTransTraceByReqNum(String reqNum, String BankNumCode, String status) {
		logger.info("reqNum:" + reqNum + ", BankNumCode:" + BankNumCode);
		//		CnlTransTrace cnlTransTrace = cnlTransTraceDao.findByReqNum(reqNum);
		//		cnlTransTrace.setBankTransNum(bankNumCode);
		//		cnlTransTrace.setTransStatus(status);
		//		cnlTransTrace.setBankAccepteTime(new Timestamp(System.currentTimeMillis()));
		//		logger.info("updateTransTrace-cnlTransTraceDao.saveOrUpdate:" + reqNum);
		//		cnlTransTraceDao.saveOrUpdate(cnlTransTrace);
	}

	/**
	 * 查询交易记录
	 */
	@Override
	public Map records(String cnlCustomerCode, int TransType,String pageSize,String pageIndex) {
		logger.info("查询交易记录 records(String cnlCustomerCode:"+cnlCustomerCode+", int TransType:"+TransType+",String pageSize:"+pageSize+",String pageIndex:"+pageIndex+")");
		String typeCode = "0";
		switch(TransType){
		case 1://查全部
			typeCode = "";
			break;
		case 2://查充值
			typeCode = Constants.TRANS_TYPE_TOPUP;
			break;
		case 3://查提现
			typeCode = Constants.TRANS_TYPE_WITHDRAW;
			break;
		}
		//查到结果集
		List<Reconciliation> list = cnlTransDao.getList(cnlCustomerCode, typeCode,pageSize,pageIndex);
		//获取总行数
		int rowCount = cnlTransDao.listCount(cnlCustomerCode,typeCode);
		//放结果到map中
		Map map = new HashMap();
		map.put("all_count",rowCount);
		//如果分页大于记录数放入null
		if(rowCount-(Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1))>0){
			map.put("records", list);
		}else{
			map.put("records", null);
		}
		//返回map
		return map;
	}

	/**
	 * 查询余额
	 */
	@Override
	public Map findBalance(String cnlCnlCode, String cnlCustCode,String pageSize,String pageIndex) {
		logger.info("查询余额 findBalance(String cnlCnlCode:"+cnlCnlCode+", String cnlCustCode:"+cnlCustCode+",String pageSize:"+pageSize+",String pageIndex:"+pageIndex+")");
		List<CnlTransTraceServiceBalance> list = null;
		int rowCount = 0;
		Map map = new HashMap();
		//查询结果集
		list = cnlCustAcntDao.findBlance(cnlCnlCode, cnlCustCode, pageSize, pageIndex);
		//查询总记录数
		rowCount = cnlCustAcntDao.blanceCount(cnlCnlCode,cnlCustCode);
		map.put("all_count",rowCount);
		//如果分页大于记录数放入null
		if(rowCount-(Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1))>0){
			map.put("records", list);
		}else{
			map.put("records", null);
		}
		//返回map
		return map;
	}

	@Override
	/**
	 * 返回是否已有报文记录
	 */
	public boolean responseStatus(String msgId) {
		logger.info("查询是否收到报文 responseStatus(String msgId:"+msgId+")");
		//根据报文编号查询报文
		List<CnlMsg> list = cnlMsgDao.findByMsgCode(msgId);
		//如果已有报文记录返回true
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	/**
	 * 开户
	 */
	public String account(String cnlCode, String intfCode, String msgId, Timestamp msgTime,String returnUrl) throws Exception {
		logger.info("增加个人开户申请单 account(String cnlCode:"+cnlCode+", String intfCode:"+intfCode+", String msgId:"+msgId+", Timestamp msgTime:"+msgTime+")");
		return addCnlReqTrans(TransServiceUtil.req(4, cnlCode, intfCode, null, null, null, null, msgId, msgTime,null,returnUrl));
	}

	@Override
	/**
	 *修改个人信息 
	 */
	public String updateAccount(String cnlCode, String intfCode, String msgId, Timestamp msgTime,String returnUrl) throws Exception {
		logger.info("增加个人更新申请单 updateAccount(String cnlCode:"+cnlCode+", String intfCode:"+intfCode+", String msgId:"+msgId+", Timestamp msgTime:"+msgTime+")");
		return addCnlReqTrans(TransServiceUtil.req(5, cnlCode, intfCode, null, null, null, null, msgId, msgTime,null,returnUrl));
	}

	@Override
	/**
	 * 注销
	 */
	public String cancellation(String cnlCode, String intfCode, String msgId, Timestamp msgTime,String returnUrl) throws Exception {
		logger.info("增加注销申请单 cancellation(String cnlCode:"+cnlCode+", String intfCode:"+intfCode+", String msgId:"+msgId+", Timestamp msgTime:"+msgTime+")");
		return addCnlReqTrans(TransServiceUtil.req(8, cnlCode, intfCode, null, null, null, null, msgId, msgTime,null,returnUrl));
	}

	/**
	 * 返回清算结果
	 * @param stlNum 清算号 
	 * @param pageSize 每页条数
	 * @param pageIndex 页数索引
	 * @return 结果集
	 * @throws Exception 抛出验证错误
	 */
	@Override
	public Map clearingResult(String stlNum,String pageSize,String pageIndex) throws Exception {
		logger.info("返回清算结果 clearingResult(String stlNum:"+stlNum+",String pageSize:"+pageSize+",String pageIndex:"+pageIndex+")");
		Map map = new HashMap();
		int count = 0;
		List<CnlTransServiceClearing> records = new ArrayList<CnlTransServiceClearing>();
		//根据清算号查询清算信息
		CnlReqTrans c = cnlReqTransDao.getReq(stlNum);
		//如果有清算记录
		if(c!=null){
			//如果清算不成功
			if(!Constants.TRANS_STATUS_SUCCESS.equals(c.getReqStatus())){
				//如果是待处理
				if(Constants.TRANS_STATUS_UNPROCESSED.equals(c.getReqStatus())){
					if(logger.isErrorEnabled())
						logger.error("此条记录待处理,ReqStatus:"+c.getReqStatus());
					throw new BizException("此条记录待处理,ReqStatus:"+c.getReqStatus());
				}
				//如果是处理中
				if(Constants.TRANS_STATUS_PROCESSING.equals(c.getReqStatus())){
					if(logger.isErrorEnabled())
						logger.error("此条记录处理中,ReqStatus:"+c.getReqStatus());
					throw new BizException("此条记录处理中,ReqStatus:"+c.getReqStatus());
				}
				//获取记录数
				count = cnlTransTraceDao.countList(stlNum);
				//如果分页不超过索引
				if(count-(Integer.parseInt(pageSize)*(Integer.parseInt(pageIndex)-1))>=0){
					records = cnlTransTraceDao.getClearing(stlNum, pageSize, pageIndex);
				}
				//如果有错
				if(Constants.REQ_TRANS_ERROR_CODE_07.equals(c.getErrCode())||Constants.REQ_TRANS_ERROR_CODE_08.equals(c.getErrCode())||Constants.REQ_TRANS_ERROR_CODE_06.equals(c.getErrCode())){
					if(logger.isErrorEnabled())
						logger.error(c.getErrMsg());
					throw new BizException(c.getErrMsg());
				}
			}

		}else{
			throw new BizException("没有查到此清算号的记录");
		}
		//放入结果集
		map.put("all_count",count);
		map.put("records", records);
		//返回结果集
		return map;
	}

	/**
	 * 新增企业客户开户申请单
	 * @param cnlCode 渠道编码
	 * @param intfCode 服务编码
	 * @param msgId 渠道订单号
	 * @param msgTime 报文时间
	 * @return 内部交易流水号
	 * @throws Exception 抛出验证错误
	 */
	@Override
	public String addEnterpriseAccount(String cnlCode, String intfCode, String msgId, Timestamp msgTime,String returnUrl)
			throws Exception {
		logger.info("新增企业客户开户申请单 addEnterpriseAccount(String cnlCode:"+cnlCode+", String intfCode:"+intfCode+", String msgId:"+msgId+", Timestamp msgTime:"+msgTime+")");
		return addCnlReqTrans(TransServiceUtil.req(6, cnlCode, intfCode, null, null, null, null, msgId, msgTime,null,returnUrl));
	}

	/**
	 * 增加修改企业客户信息的申请单
	 * @param cnlCode 渠道客户编码
	 * @param intfCode 服务编码
	 * @param msgId 渠道订单号
	 * @param msgTime 报文时间
	 * @return 返回内部流水号
	 * @throws Exception 
	 */
	@Override
	public String modifyEnterpriseAccount(String cnlCode, String intfCode, String msgId, Timestamp msgTime,String returnUrl)
			throws Exception {
		logger.info("增加修改企业客户信息的申请单 modifyEnterpriseAccount(String cnlCode:"+cnlCode+", String intfCode:"+intfCode+", String msgId:"+msgId+", Timestamp msgTime:"+msgTime+")");
		return addCnlReqTrans(TransServiceUtil.req(7, cnlCode, intfCode, null, null, null, null, msgId, msgTime,null,returnUrl));
	}




	/**
	 * 新增企业开户的跟踪单
	 * @param param 参数类实体
	 * @return 返回金额
	 * @throws Exception 抛出验证错误
	 */
	@Override
	public BigDecimal addEnterpriseAccountTrace(CnlTransServiceAccountParam param)throws Exception {
		logger.info("新增企业开户的跟踪单 addEnterpriseAccountTrace(CnlTransServiceAccountParam param):"+param.toString()+"");
		//转成和数据库映射的实体类
		CnlCustTrace c = TransServiceUtil.createCnlCustTrace(1, param);



		//根据支行编号去数据库中找到对应的支行名称
		if(StringUtils.isNotEmpty(param.getBank_branch())){
			PblBankDict pblBankDict = pblBankDictDao.findByBankBranchCode(param.getBank_branch());
			String bankBranchName = pblBankDict.getBankBranchName();
			String code = pblBankDict.getBankCode();
			String bankName = pblBankDict.getBankName();
			c.setBankBranchName(bankBranchName);
			c.setBankCode(code);
			c.setBankName(bankName);
		}


		//保存申请跟踪单
		cnlCustTraceDao.saveOrUpdate(c);
		//调用并返回随机生成的金额
		return bankVld(param);
	}

	/**
	 * 增加修改申请跟踪单
	 * @param reqInnerNum 流水号
	 * @param cnlCnlCode 渠道号
	 * @param cnlCustCode 渠道客户号
	 * @param phonenum 电话号码
	 * @param address 地址
	 * @param msgDate 报文时间
	 * @throws Exception 抛出数据验证错误
	 */
	@Override
	public void addModifyEnterpriseAccountTrace(String reqInnerNum,String cnlCnlCode,String cnlCustCode,String phonenum,String address,Date msgDate) throws Exception 
	{
		logger.info("增加企业更新跟踪单 addModifyEnterpriseAccountTrace(String reqInnerNum:"+reqInnerNum+",String cnlCnlCode:"+cnlCnlCode+",String cnlCustCode:"+cnlCustCode+",String phonenum:"+phonenum+",String address:"+address+",Date msgDate:"+msgDate+")");
		CnlTransServiceAccountParam param = new CnlTransServiceAccountParam();
		//插入数据到参数类对象
		param.setReqInnerNum(reqInnerNum);
		param.setCnl_customer_code(cnlCustCode);
		param.setCnlCnlCode(cnlCnlCode);
		param.setPhonenum(phonenum);
		param.setAddress(address);
		param.setMsgDate(msgDate);
		//转换成和数据库映射的实体类
		CnlCustTrace c = TransServiceUtil.createCnlCustTrace(2, param);
		//保存到数据库
		cnlCustTraceDao.saveOrUpdate(c);
	}

	/**
	 * 插入数据到企业客户转账验证表
	 * @param param 参数类对象
	 * @return
	 */
	@Override
	public BigDecimal bankVld(CnlTransServiceAccountParam param) {
		logger.info("插入数据到企业客户转账验证表 bankVld(CnlTransServiceAccountParam param):"+param.toString());
		//创建和数据库映射的对象
		CnlCustTraceBankVld cnlCustTraceBankVld = TransServiceUtil.createC(param);
		//保存到数据库
		cnlCustTraceBankVldDao.saveOrUpdate(cnlCustTraceBankVld);
		//返回随机生成的金额
		return cnlCustTraceBankVld.getTransAmount();
	}

	/**
	 * 添加假数据
	 */
	private void update(List<CnlTransTrace> successList){


		CnlTransTrace c = successList.get(0);
		c.setTransStatus("03");
		//c.setTransRate(new BigDecimal("1"));
		c.setBankReqTime(new Date());
		c.setBankAccepteTime(new Date());
		c.setBankTransNum(StringUtils.getUUID());
		c.setBankHandleNum("IBMCNLCODE155001");
		c.setBankReturnTime(new Date());
		c.setBankReturnResult("01");
		c.setBankPmtCnlType("01");
		c.setBankPmtCnlCode("01");
		c.setBankCreditAcntCode("01");
		c.setBankCreditName("建设银行");
		c.setBankCreditCode("1001");
		c.setBankCreditBranchName("兴中路支行");
		c.setBankCreditBranchCode("1001001");
		c.setBankCreditCustName("小明");
		c.setBankCreditCardNum("7112345678901234001");
		c.setBankDebitAcntCode("01");
		c.setBankDebitName("建设银行");
		c.setBankDebitCode("1001");
		c.setBankDebitBranchName("兴中路支行");
		c.setBankDebitBranchCode("1001001");
		c.setBankDebitCustName("小红");
		c.setBankDebitCardNum("7112345678901234001");
		c.setBankReqTrnasDate(new Date());
		c.setBankReqTransTime(new Date());
		c.setBnakServiceFeeAct("jdsifcspojfis");
		c.setUpdateTime(new Date());
		c.setUpdator("admin");
		List<CnlTransTrace> l = new ArrayList<CnlTransTrace>();
		l.add(c);
		saveBankAcntTransToDb(l);
		// 4.update 申请单跟踪表(只有返回成功或者失败时),错误需要将msg_code和msg_type更新
		updateTransTrace(successList);
		// 5.update 渠道申请单流水表 T_CNL_REQ_TRANS
		updateCnlReqTrans(successList);
		// 6.只有返回代付成功时，insert 渠道交易流水 T_CNL_TRANS
		insertCnlTrans(successList);
		// 7.更新企业账户和个人账户，增加明细账
		updateCnlCustAcntandCnlCustAcntDTL(successList, true);
	}

	private boolean saveBankAcntTransToDb(List<CnlTransTrace> list) {
		List<CnlCustAcntDtl> corlist = new ArrayList<CnlCustAcntDtl>();
		CnlCustAcntDtl corBankAcntTrans = null;
		for (CnlTransTrace cnlTransTrace : list) {
			corBankAcntTrans = new CnlCustAcntDtl();
			try {
				BeanUtil.copyBasicTypeProperties(corBankAcntTrans, cnlTransTrace);
				//				corBankAcntTrans.setBankNum("7112345678901234001");
				corBankAcntTrans.setTransNum(cnlTransTrace.getBankTransNum());
				//				corBankAcntTrans.setReturnCode(cnlTransTrace.getBankReturnResult());
				//				corBankAcntTrans.setTransOrderNum("001");
				//corBankAcntTrans.setAmount(cnlTransTrace.getTransAmount());
				corlist.add(corBankAcntTrans);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
			}

		}

		cnlCustAcntDtlDao.saveOrUpdate(corlist.get(0));

		return true;
	}

	private boolean updateTransTrace(List<CnlTransTrace> list) {
		cnlTransTraceDao.updateBatchTransTrace(list);

		return true;
	}

	private boolean updateCnlReqTrans(List<CnlTransTrace> list) {
		List<CnlReqTrans> reqList = new ArrayList<CnlReqTrans>();
		CnlReqTrans reqTrans = null;
		for (CnlTransTrace c : list) {
			reqTrans = cnlReqTransDao.findByInnerNum(c.getReqInnerNum());

			reqTrans.setReqStatus(c.getHandleStatus());
			if ("04".equals(c.getErrCode())) {
				reqTrans.setErrCode(c.getErrCode());
				reqTrans.setErrMsg(c.getErrMsg());
			}
			reqList.add(reqTrans);
		}
		cnlReqTransDao.updateBatch(reqList);
		return false;
	}

	private boolean insertCnlTrans(List<CnlTransTrace> cnlTransTracelist) {
		List<CnlTrans> list = new ArrayList<CnlTrans>();
		CnlTrans cnlTrans = null;
		for (CnlTransTrace cnlTransTrace : cnlTransTracelist) {
			cnlTrans = new CnlTrans();
			try {
				BeanUtil.copyBasicTypeProperties(cnlTrans, cnlTransTrace);
				cnlTrans.setId(null);
				cnlTrans.setTransNum(cnlTransTrace.getBankTransNum());
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(cnlTrans);

		}
		cnlTransDao.saveBatch(list);

		return false;
	}

	private boolean updateCnlCustAcntandCnlCustAcntDTL(List<CnlTransTrace> cnlTransTraceList, boolean flag) {
		//		CnlCustAcnt cnlCustAcntCompany = null;
		CnlCustAcnt cnlCustAcntPersonal = null;
		for (int i = 0; i < cnlTransTraceList.size(); i++) {
			String cnlCnlCode = cnlTransTraceList.get(i).getCnlCnlCode();
			String cnlCustCode = cnlTransTraceList.get(i).getCnlCustCode();
			// a.确定企业客户
			//			cnlCustAcntCompany = cnlCustAcntDao.findCompanyByCnlCnlCode(cnlCnlCode);
			// b.确定个人客户
			cnlCustAcntPersonal = cnlCustAcntDao.findPersonalByCnlCnlCodeAndCnlCustCode(cnlCnlCode, cnlCustCode);
			if (flag) {
				//成功
				// c.1对金额进行处理
				//更新企业账户
				//				cnlCustAcntDao.updateCompanyDibetSuccess(cnlCustAcntCompany, cnlTransTraceList.get(i).getTransAmount());
				//更新个人账户
				if(Constants.TRANS_TYPE_TOPUP.equals(cnlTransTraceList.get(i).getTransType())){
					//	cnlCustAcntDao.updatePersonalCreditSuccess(cnlCustAcntPersonal, cnlTransTraceList.get(i).getTransAmount());
				}else{
					//	cnlCustAcntDao.updatePersonalDibetSuccess(cnlCustAcntPersonal, cnlTransTraceList.get(i).getTransAmount());
				}

				// c.2加入明细账
				//				insertCnlCustAcntDtl(cnlCustAcntCompany,cnlTransTraceList.get(i));
				insertCnlCustAcntDtl(cnlCustAcntPersonal,cnlTransTraceList.get(i));
			}else{
				//失败
				// c.1对金额进行处理
				//更新企业账户  企业账户不变
				//更新个人账户  
				//cnlCustAcntDao.updatePersonalDibetFailed(cnlCustAcntPersonal, cnlTransTraceList.get(i).getTransAmount());

			}

		}

		return true;
	}


	private boolean insertCnlCustAcntDtl(CnlCustAcnt cnlCustAcnt,CnlTransTrace cnlTransTrace){
		CnlCustAcntDtl cnlCustAcntDtl = new CnlCustAcntDtl();
		cnlCustAcntDtl.setAcntDtlCode(StringUtils.getUUID());
		cnlCustAcntDtl.setCnlAcntCode(cnlCustAcnt.getCnlAcntCode());
		cnlCustAcntDtl.setCnlCustCode(cnlCustAcnt.getCnlCustCode());
		cnlCustAcntDtl.setCustCode(cnlCustAcnt.getCustCode());
		cnlCustAcntDtl.setTransNum(cnlTransTrace.getBankTransNum());
		cnlCustAcntDtl.setAcntType(cnlCustAcnt.getAcntType());
		cnlCustAcntDtl.setCurrency(cnlCustAcnt.getCurrency());
		//cnlCustAcntDtl.setAmount(cnlTransTrace.getTransAmount());
		cnlCustAcntDtl.setDirection(cnlTransTrace.getTransDc());
		cnlCustAcntDtl.setTransType(cnlTransTrace.getTransType());
		//更新之后的金额为
		//BigDecimal amount=cnlCustAcnt.getBalance().add(cnlTransTrace.getTransAmount());
		//cnlCustAcntDtl.setBalance(amount);
		cnlCustAcntDtl.setPreBalance(cnlCustAcnt.getBalance());
		cnlCustAcntDtl.setTransDate(cnlTransTrace.getTransDate());
		cnlCustAcntDtl.setTransTime(cnlTransTrace.getTransTime());
		cnlCustAcntDtl.setIsPrinted(cnlTransTrace.getIsPrinted());
		cnlCustAcntDtl.setVoucherNum(cnlTransTrace.getVoucherNum());
		cnlCustAcntDtl.setComments(cnlTransTrace.getTransComments());
		cnlCustAcntDtl.setIsValid(cnlTransTrace.getIsValid());
		cnlCustAcntDtl.setCreator(cnlTransTrace.getCreator());
		cnlCustAcntDtl.setUpdator(cnlTransTrace.getUpdator());
		cnlCustAcntDtl.setCreateTime(cnlTransTrace.getCreateTime());
		cnlCustAcntDtl.setUpdateTime(cnlTransTrace.getUpdateTime());

		cnlCustAcntDtlDao.saveCnlCustAcntDtl(cnlCustAcntDtl);
		return true;
	}

	/**
	 * 验证客户有效性
	 * @param code 渠道客户编码
	 * @return 
	 */
	private boolean getCnlCust(String code){
		CnlCust cnlCust = cnlCustDao.loadBy("cnlCustCode", code);
		if(cnlCust!=null){//为空验证
			if(Constants.CNL_STATUS_VALID.equals(cnlCust.getIsValid())){//验证有效性
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}


	/**
	 * 企业开户第二次请求
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param reqNum
	 * @throws Exception
	 */
	@Override
	public void createData(String cnlCustCode,String cnlCnlCode,String infoCode,String reqNum)throws Exception{
		logger.info("企业开户第二次请求createData(String cnlCustCode:"+cnlCustCode+",String cnlCnlCode:"+cnlCnlCode+",String infoCode:"+infoCode+",String reqNum:"+reqNum+")");
		//创建reqTrans
		CnlReqTrans cnlReqTransSecond = TransServiceUtil.req(9, cnlCnlCode, infoCode, null, null, null, null, reqNum, new Date(), null,"");
		cnlReqTransSecond.setReqInnerNum(StringUtils.getUUID());
		//查找第一次开户的CustTrace，判断状态，如果为转账成功，将第一次reqTrans状态改为成功
		CnlCustTrace cnlCustTrace = cnlCustTraceDao.findFirstTrace(cnlCustCode,Constants.REQ_TRACE_TYPE_ENTERPRISE_SIGN_UP);
		if(cnlCustTrace == null)
			throw new BizException("找不到第一次请求跟踪记录");
		if(!Constants.CUST_TRACE_STATUS_WAITING_FOR_CUST_VALID.equals(cnlCustTrace.getReqStatus()))
			throw new BizException("未通过第一次请求验证");
		String reqInnerNum = cnlCustTrace.getReqInnerNum();
		CnlReqTrans cnlReqTransFirst = cnlReqTransDao.findByInnerNum(reqInnerNum);
		if(cnlReqTransFirst == null)
			throw new BizException("找不到第一次请求记录");
		cnlReqTransFirst.setReqStatus(Constants.REQ_STATUS_SUCCESS);
		cnlReqTransFirst.setUpdateTime(new Date());
		cnlReqTransFirst.setUpdator(Constants.SYSADMIN);
		cnlReqTransFirst.setHandleTime(new Date());
		cnlReqTransDao.saveOrUpdate(cnlReqTransFirst);



		//调用核心服务开户
		cnlCustService.addCnlCompanyCustByParams(cnlCustTrace.getCnlCustCode(), cnlCnlCode, cnlCustTrace.getName(), 
				cnlCustTrace.getCertNum(), cnlCustTrace.getCertExpire(), cnlCustTrace.getPhonenum(), cnlCustTrace.getAddr());
		cnlCustAcntService.createCompanyAccount(cnlCustTrace.getCnlCustCode(), cnlCnlCode, cnlCustTrace.getBankCardNum(),
				cnlCustTrace.getCertNum(),cnlCustTrace.getBankCustName(),cnlCustTrace.getBankBranchCode());

		//更新客户跟踪表记录状态为成功，更新第二次请求记录表状态为成功
		cnlCustTrace.setReqStatus(Constants.CUST_TRACE_STATUS_SUCCESS);
		cnlCustTrace.setUpdateTime(new Date());
		cnlCustTrace.setUpdator(Constants.SYSADMIN);
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);
		cnlReqTransSecond.setReqStatus(Constants.REQ_STATUS_SUCCESS);
		cnlReqTransSecond.setUpdateTime(new Date());
		cnlReqTransSecond.setUpdator(Constants.SYSADMIN);
		cnlReqTransSecond.setHandleTime(new Date());
		cnlReqTransDao.saveOrUpdate(cnlReqTransSecond);


	}

	/**
	 * 更新申请表和跟踪表状态为成功
	 * 
	 */
	@Override
	public void updateReqAndTraceStatus(String innerNum){
		logger.info("更新申请表和跟踪表状态为成功 updateReqAndTraceStatus(String innerNum:"+innerNum+")");
		CnlReqTrans cnlReqTrans = cnlReqTransDao.findByInnerNum(innerNum);
		cnlReqTrans.setReqStatus(Constants.REQ_STATUS_SUCCESS);
		cnlReqTransDao.saveOrUpdate(cnlReqTrans);
		CnlCustTrace cnlCustTrace = cnlCustTraceDao.findByInnerNum(innerNum);
		cnlCustTrace.setReqStatus(Constants.CUST_TRACE_STATUS_SUCCESS);
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);
	}

	/**
	 * 增加个人开户跟踪记录
	 */
	@Override
	public void createAccount(CnlTransServiceAccountParam param)throws Exception {
		logger.info("增加个人开户跟踪记录 createAccount(CnlTransServiceAccountParam param:"+param.toString()+")");
		CnlCustTrace cnlCustTrace = TransServiceUtil.createCnlCustTrace(3, param);
		//保存申请跟踪单
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);	
	}

	/**
	 * 增加个人更新跟踪记录
	 */
	@Override
	public void updateAccount(CnlTransServiceAccountParam param)throws Exception {
		logger.info("增加个人更新跟踪记录 updateAccount(CnlTransServiceAccountParam param:"+param.toString()+")");
		CnlCustTrace cnlCustTrace = TransServiceUtil.createCnlCustTrace(4, param);
		//保存申请跟踪单
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);

	}

	/**
	 * 提现冻结账户金额 更改可用余额
	 */
	private void frozenBalance(String custCode,BigDecimal amount){
		logger.info("提现冻结账户金额 更改可用余额 frozenBalance(String custCode:"+custCode+",BigDecimal amount:"+amount+")");
		//查询对应的渠道客户账户
		CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findByCustCode(custCode);
		if(cnlCustAcnt==null){
			throw new BizException("用户不存在");
		}
		//更改冻结金额
		cnlCustAcnt.setBalanceFrozen(cnlCustAcnt.getBalanceFrozen().add(amount));
		//更改可用余额
		cnlCustAcnt.setBalanceAvalible(cnlCustAcnt.getBalanceAvalible().subtract(amount));
	}


	@Override
	public boolean cheackEnterpriseAccountReq(String cnlCustCode, String cnlCnlCode) {
		logger.info("校验重复提交申请 cheackEnterpriseAccountReq(String cnlCustCode:"+cnlCustCode+", String cnlCnlCode:"+cnlCnlCode+")");
		boolean ok = true;
		//通过渠道编号和渠道客户编号查询是否有申请记录存在
		ok = cnlReqTransDao.findByCustCodeAndCnlCode(cnlCustCode, cnlCnlCode);
		if(ok){
			//通过渠道编号和渠道客户编号查询是否有申请明细记录存在
			ok = cnlCustTraceDao.findBooleanByCnlCustCodeAndCnlCnlCode(cnlCustCode,cnlCnlCode);
		}
		return ok;
	}



	@Override
	public void addModifyCustTrace(String reqInnerNum, String cnlCnlCode, String cnlCustCode, String phonenum,
			String address, Date msgDate, String msgId) throws Exception {
		CnlTransServiceAccountParam param = new CnlTransServiceAccountParam();
		//插入数据到参数类对象
		param.setReqInnerNum(reqInnerNum);
		param.setCnl_customer_code(cnlCustCode);
		param.setCnlCnlCode(cnlCnlCode);
		param.setPhonenum(phonenum);
		param.setAddress(address);
		param.setMsgDate(msgDate);
		param.setMsgId(msgId);
		//转换成和数据库映射的实体类
		CnlCustTrace cnlCustTrace = TransServiceUtil.createCnlCustTrace(2, param);
		//保存到数据库
		cnlCustTraceDao.saveOrUpdate(cnlCustTrace);

	}

	@Override
	/**
	 * 校验申请明细
	 */
	public boolean cheackCustTrace(String name, String num) {
		logger.debug("entering Serivce::CnlTransServiceImpl.cheackCustTrace(String name, String num)...name:"+name+"...num:"+num);
		return cnlCustTraceDao.findBooleanByNameAndCretNum(name,num);
	}

	@Override
	/**
	 * 校验客户是否存在
	 */
	public boolean cheackAccount(String cnlCnlCode, String cnlCustCode) {
		logger.debug("entering Serivce::CnlTransServiceImpl.cheackAccount(String cnlCnlCode, String cnlCustCode)...cnlCnlCode:"+cnlCnlCode+"...cnlCustCode:"+cnlCustCode);
		return cnlCustAcntDao.cheackAccount(cnlCnlCode, cnlCustCode);
	}

	@Override
	/**
	 * 查询回调地址
	 */
	public String getReturnUrl(String msgId) {
		logger.debug("entering Serivce::CnlTransServiceImpl.getReturnUrl(String msgId)...msgId:"+msgId);
		return cnlReqTransDao.getReturnUrl(msgId);
	}

	@Override
	/**
	 * 查询清算总批次数
	 */
	public String getClearingTotalBatch(String stlNum) {
		logger.debug("entering Serivce::CnlTransServiceImpl.getClearingTotalBatch(String stlNum)...stlNum:"+stlNum);
		return cnlReqTransDao.getClearingTotalBatch(stlNum);
	}

	@Override
	/**
	 * 查询清算批次
	 */
	public List<String> getClearingBatch(String stlNum) {
		logger.debug("entering Serivce::CnlTransServiceImpl.getClearingBatch(String stlNum)...stlNum:"+stlNum);
		return cnlReqTransDao.getClearingBatch(stlNum);
	}



}
