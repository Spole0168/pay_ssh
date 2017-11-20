package com.ibs.core.module.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.account.dao.ICnlCustAcntDao;
import com.ibs.core.module.account.dao.ICnlCustTraceDao;
import com.ibs.core.module.account.dao.ICnlTrans3mDao;
import com.ibs.core.module.account.domain.CnlCustAcnt;
import com.ibs.core.module.account.domain.CnlCustTrace;
import com.ibs.core.module.account.domain.CnlTrans3m;
import com.ibs.core.module.account.service.ICnlCustAcntService;
import com.ibs.core.module.bank.dao.ICorBankAcntDao;
import com.ibs.core.module.bank.domain.CorBankAcnt;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.StringUtils;

public class CnlCustAcntServiceimpl extends BaseService implements ICnlCustAcntService {

	private IDataDictService dataDictService;

	ICnlCustAcntDao cnlCustAcntDao;

	ICnlTrans3mDao cnlTrans3mDao;

	ICorBankAcntDao corBankAcntDao;

	ICnlCustDao cnlCustDao;

	IPblBankDictDao pblBankDictDao;

	ICnlCustTraceDao cnlCustTraceDao;

	public ICnlCustTraceDao getCnlCustTraceDao() {
		return cnlCustTraceDao;
	}

	public void setCnlCustTraceDao(ICnlCustTraceDao cnlCustTraceDao) {
		this.cnlCustTraceDao = cnlCustTraceDao;
	}

	public IPblBankDictDao getPblBankDictDao() {
		return pblBankDictDao;
	}

	public void setPblBankDictDao(IPblBankDictDao pblBankDictDao) {
		this.pblBankDictDao = pblBankDictDao;
	}

	public ICnlCustDao getCnlCustDao() {
		return cnlCustDao;
	}

	public void setCnlCustDao(ICnlCustDao cnlCustDao) {
		this.cnlCustDao = cnlCustDao;
	}

	public IDataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(IDataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}

	public ICnlCustAcntDao getCnlCustAcntDao() {
		return cnlCustAcntDao;
	}

	public void setCnlCustAcntDao(ICnlCustAcntDao cnlCustAcntDao) {
		this.cnlCustAcntDao = cnlCustAcntDao;
	}

	public ICnlTrans3mDao getCnlTrans3mDao() {
		return cnlTrans3mDao;
	}

	public void setCnlTrans3mDao(ICnlTrans3mDao cnlTrans3mDao) {
		this.cnlTrans3mDao = cnlTrans3mDao;
	}

	public ICorBankAcntDao getCorBankAcntDao() {
		return corBankAcntDao;
	}

	public void setCorBankAcntDao(ICorBankAcntDao corBankAcntDao) {
		this.corBankAcntDao = corBankAcntDao;
	}

	// 根据渠道客户编号查询出余额
	@Override
	public BigDecimal queryBalance(String cnlCustCode, String cnlCnlCode) throws Exception {
		logger.debug(
				"entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.queryBalance()..." + cnlCustCode + cnlCnlCode);
		// 数据不为空校验
		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {

			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");

		}

		CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();
		cnlCustAcnt.setCnlCustCode(cnlCustCode);
		cnlCustAcnt.setCnlCnlCode(cnlCnlCode);
		CnlCustAcnt custAcnt = cnlCustAcntDao.findByCnlCustAcnt(cnlCustAcnt);

		if (null == custAcnt) {
			if (logger.isErrorEnabled())
				logger.error(custAcnt + "没有符合要求的记录");
			throw new BizException(custAcnt + "没有符合要求的记录");

		}
		logger.info("返回可用余额" + custAcnt.getBalanceAvalible());
		return custAcnt.getBalanceAvalible();

	}

	// 根据渠道客户编号，交易时间段，交易类型 查询渠道交易流水
	@Override
	public CnlTrans3m queryCnlTrans3m(String cnlCustCode, String StartTransTime, String endTransTime, String transType)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.queryCnlTrans3m()..." + cnlCustCode
				+ StartTransTime + endTransTime + transType);

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");

		}
		if (StringUtils.isEmpty(transType)) {
			if (logger.isErrorEnabled())
				logger.error(transType + "交易类型不能为空");
			throw new BizException(transType + "交易类型不能为空");
		}
		if (StringUtils.isEmpty(StartTransTime)) {
			if (logger.isErrorEnabled())
				logger.error(StartTransTime + "交易开始时间不能为空");
			throw new BizException(StartTransTime + "交易开始时间不能为空");
		}
		if (StringUtils.isEmpty(endTransTime)) {
			if (logger.isErrorEnabled())
				logger.error(endTransTime + "交易结束时间不能为空");
			throw new BizException(endTransTime + "交易结束时间不能为空");
		}

		CnlTrans3m cnlTrans3m = cnlTrans3mDao.queryCnlTrans3m(cnlCustCode, StartTransTime, endTransTime, transType);

		if (cnlTrans3m == null) {
			if (logger.isErrorEnabled())
				logger.error(endTransTime + "查询失败，没有对应记录");
			throw new BizException(endTransTime + "查询失败，没有对应记录");
		}
		logger.info("返回交易流水" + cnlTrans3m);
		return cnlTrans3m;
	}

	// 新增用户资金账号
	@Override
	public void addBanktAcnt(CorBankAcnt corBankAcnt) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.addBanktAcnt()..." + corBankAcnt);
		if (null == corBankAcnt) {
			if (logger.isErrorEnabled())
				logger.error(corBankAcnt + "银行账户没有信息,不能新增");
			throw new BizException(corBankAcnt + "银行账户没有信息,不能新增");
		}
		String country = corBankAcnt.getCountry();
		String certType = corBankAcnt.getCertType();
		String isDefault = corBankAcnt.getIsDefault();
		String bankCode = corBankAcnt.getBankCode();
		String status = corBankAcnt.getStatus();
		String acntType = corBankAcnt.getAcntType();
		String isValid = corBankAcnt.getIsValid();
		Date createTime = corBankAcnt.getCreateTime();
		Date updateTime = corBankAcnt.getUpdateTime();
		String creator = corBankAcnt.getCreator();
		String updator = corBankAcnt.getUpdator();
		String bankBranchCode = corBankAcnt.getBankBranchCode();
		String currency = corBankAcnt.getCurrency();

		if (StringUtils.isEmpty(country)) {
			corBankAcnt.setCountry(Constants.COUNTRY_37);
		}
		if (StringUtils.isEmpty(certType)) {
			corBankAcnt.setCertType(Constants.CERT_TYPE_ID);
		}
		if (StringUtils.isEmpty(isDefault)) {
			corBankAcnt.setIsDefault(Constants.BANK_IS_DEFAULT);
		}
		if (StringUtils.isEmpty(status)) {
			corBankAcnt.setStatus(Constants.ACNT_STATUS_NORMAL);
		}
		if (StringUtils.isEmpty(acntType)) {
			corBankAcnt.setAcntType(Constants.BANK_CARD_TYPE_DEBIT_CARD);
		}
		if (StringUtils.isEmpty(isValid)) {
			corBankAcnt.setIsValid(Constants.IS_VALID_VALID);
		}
		if (null == createTime) {
			corBankAcnt.setCreateTime(new Date());
		}
		if (null == updateTime) {
			corBankAcnt.setUpdateTime(new Date());
		}
		if (StringUtils.isEmpty(creator)) {
			corBankAcnt.setCreator(Constants.SYSADMIN);
		}
		if (StringUtils.isEmpty(updator)) {
			corBankAcnt.setUpdator(Constants.SYSADMIN);
		}
		if (StringUtils.isEmpty(currency)) {
			corBankAcnt.setCurrency(Constants.CURRENCY_TYPE_50);
		}

		// 根据银行编号去数据字典中找到对应的银行名称
		if (StringUtils.isNotEmpty(bankCode)) {
			List<DataDict> dataDictBankName = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME);
			for (DataDict name : dataDictBankName) {
				if (name.getCode().equals(bankCode)) {
					corBankAcnt.setBankName(name.getName());
				}
			}
		}

		// 根据支行编号去数据库中找到对应的支行名称
		if (StringUtils.isNotEmpty(bankBranchCode)) {
			PblBankDict pblBankDict = pblBankDictDao.findByBankBranchCode(bankBranchCode);
			String bankBranchName = pblBankDict.getBankBranchName();
			String code = pblBankDict.getBankCode();
			String bankName = pblBankDict.getBankName();
			corBankAcnt.setBankBranchName(bankBranchName);
			corBankAcnt.setBankCode(code);
			corBankAcnt.setBankName(bankName);
		}

		corBankAcntDao.saveCorBankAcnt(corBankAcnt);
		logger.info("银行账号开户成功");
	}

	// 新增总帐户
	@Override
	public void addCustAcnt(CnlCustAcnt cnlCustAcnt) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.addCustAcnt()..." + cnlCustAcnt);
		if (null == cnlCustAcnt) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustAcnt + "没有总帐户信息,不能开卡片帐");
			throw new BizException(cnlCustAcnt + "没有总帐户信息,不能开卡片帐");
		}
		String cnlAcntCode = cnlCustAcnt.getCnlAcntCode();
		String pcnlAcntCode = cnlCustAcnt.getCnlAcntCode();
		String currency = cnlCustAcnt.getCurrency();
		BigDecimal balance = cnlCustAcnt.getBalance();
		BigDecimal balanceAvalible = cnlCustAcnt.getBalanceAvalible();
		BigDecimal balanceFrozen = cnlCustAcnt.getBalanceFrozen();
		BigDecimal balanceReserved = cnlCustAcnt.getBalanceReserved();
		Date regTime = cnlCustAcnt.getRegTime();
		String acntStatus = cnlCustAcnt.getAcntStatus();
		String acntType = cnlCustAcnt.getAcntType();
		String isValid = cnlCustAcnt.getIsValid();
		String creator = cnlCustAcnt.getCreator();
		String updator = cnlCustAcnt.getUpdator();
		Date createTime = cnlCustAcnt.getCreateTime();
		Date updateTime = cnlCustAcnt.getUpdateTime();

		if (StringUtils.isEmpty(cnlAcntCode)) {
			// 使用uuid自动生成
			String uid = StringUtils.getUUID();
			cnlCustAcnt.setCnlAcntCode(uid);
		}
		if (StringUtils.isEmpty(pcnlAcntCode)) {
			cnlCustAcnt.setPcnlAcntCode(Constants.P_CNL_ACNT_CODE);
		}
		if (null == balance) {

			cnlCustAcnt.setBalance(new BigDecimal("0"));
		}
		if (null == balanceAvalible) {
			cnlCustAcnt.setBalanceAvalible(new BigDecimal("0"));
		}
		if (null == balanceFrozen) {
			cnlCustAcnt.setBalanceFrozen(new BigDecimal("0"));
		}
		if (null == balanceReserved) {
			cnlCustAcnt.setBalanceReserved(new BigDecimal("0"));
		}
		if (null == regTime) {
			cnlCustAcnt.setRegTime(new Date());
		}
		if (StringUtils.isEmpty(acntStatus)) {
			cnlCustAcnt.setAcntStatus(Constants.CNL_CUST_STATUS_PASSED);
		}

		if (StringUtils.isEmpty(acntType)) {
			cnlCustAcnt.setAcntType(Constants.ACNT_TYPE_TRANSACTION_FEE);
		}

		if (StringUtils.isEmpty(isValid)) {
			cnlCustAcnt.setIsValid(Constants.IS_VALID_VALID);
		}

		if (StringUtils.isEmpty(creator)) {
			cnlCustAcnt.setCreator(Constants.SYSADMIN);
		}
		if (StringUtils.isEmpty(updator)) {
			cnlCustAcnt.setUpdator(Constants.SYSADMIN);
		}
		if (null == createTime) {
			cnlCustAcnt.setCreateTime(new Date());
		}
		if (null == updateTime) {
			cnlCustAcnt.setUpdateTime(new Date());
		}
		if (null == currency) {
			cnlCustAcnt.setCurrency(Constants.CURRENCY_TYPE_50);
		}

		cnlCustAcntDao.saveCnlCustAcnt(cnlCustAcnt);
		logger.info("总账户开户成功");
	}

	// 修改总帐户表中的账户状态 来进行逻辑 销户
	@Override
	public void logout(String cnlCustCode, String cnlCnlCode) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.logout()..." + cnlCustCode + cnlCnlCode);
		// 根据渠道客户编号查询出总帐户的信息 再将他状态变成无效
		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不可为空");
			throw new BizException(cnlCustCode + "会员编号不可为空");
		}
		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不可为空");
			throw new BizException(cnlCnlCode + "渠道编号不可为空");
		}
		CnlCustAcnt acnt = new CnlCustAcnt();
		acnt.setCnlCustCode(cnlCustCode);
		acnt.setCnlCnlCode(cnlCnlCode);
		CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findByCnlCustAcnt(acnt);

		if (null == cnlCustAcnt) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustAcnt + "不可注销，找不到该账户");
			throw new BizException(cnlCustAcnt + "不可注销，找不到该账户");
		}

		if (cnlCustAcnt.getIsValid().equals(Constants.IS_VALID_NOT_VALID)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustAcnt + "不可注销，此账户是无效的");
			throw new BizException(cnlCustAcnt + "不可注销，此账户是无效的");
		}

		if (cnlCustAcnt.getAcntStatus().equals(Constants.ACNT_STATUS_ABNORMAL)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustAcnt + "不可注销，此账户状态不正常");
			throw new BizException(cnlCustAcnt + "不可注销，此账户状态不正常");
		}
		cnlCustAcnt.setAcntStatus(Constants.ACNT_STATUS_ABNORMAL);

		cnlCustAcntDao.updateCnlCustAcnt(cnlCustAcnt);
		logger.info("销户成功");
	}

	// 企业卡片帐开户

	@Override
	public void createCompanyAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String certNum,
			String custName, String bankBranchCode) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.createCompanyAccount()..." + cnlCustCode
				+ cnlCnlCode + bankCardNum + certNum + custName + bankBranchCode);

		CorBankAcnt corBankAcnt = new CorBankAcnt();
		CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();
		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}

		if (StringUtils.isEmpty(bankCardNum)) {
			if (logger.isErrorEnabled())
				logger.error(bankCardNum + "银行卡号不能为空");
			throw new BizException(bankCardNum + "银行卡号不能为空");
		}

		if (StringUtils.isEmpty(certNum)) {
			if (logger.isErrorEnabled())
				logger.error(certNum + "证件号不能为空");
			throw new BizException(certNum + "证件号不能为空");
		}

		if (StringUtils.isEmpty(custName)) {
			if (logger.isErrorEnabled())
				logger.error(custName + "客户姓名不能为空");
			throw new BizException(custName + "客户姓名不能为空");
		}

		if (StringUtils.isEmpty(bankBranchCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankBranchCode + "支行编号不能为空");
			throw new BizException(bankBranchCode + "支行编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");
		}

		// 获取省份城市信息
		CnlCustTrace cnlCustTrace = cnlCustTraceDao.findByCnlCustCode(cnlCustCode, cnlCnlCode);
		if (null == cnlCustTrace) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustTrace + "找不到省份城市信息");
			throw new BizException(cnlCustTrace + "找不到省份城市信息");
		}

		// 插入省份
		corBankAcnt.setCity(cnlCustTrace.getCity());
		// 插入城市
		corBankAcnt.setProvience(cnlCustTrace.getProvience());
		// 插入会员编号
		corBankAcnt.setCnlCustCode(cnlCustCode);
		// 证件号码
		corBankAcnt.setCertNum(certNum);
		// 客户姓名
		corBankAcnt.setCustName(custName);
		// 银行卡号
		corBankAcnt.setBankCardNum(bankCardNum);
		// 分行编号
		corBankAcnt.setBankBranchCode(bankBranchCode);
		// 银行账户编号
		String uid = StringUtils.getUUID();
		corBankAcnt.setBankAcntCode(uid);
		// 总帐户渠道编号
		cnlCustAcnt.setCnlCnlCode(cnlCnlCode);
		// 会员编号
		cnlCustAcnt.setCnlCustCode(cnlCustCode);
		// 总帐户对应银行账户编号
		cnlCustAcnt.setBankAcntCode(uid);
		logger.info("开始创建银行资金账户");
		addBanktAcnt(corBankAcnt);
		logger.info("开始创建总账户");
		addCustAcnt(cnlCustAcnt);

	}

	/**
	 * 更新卡片帐银行卡信息
	 * 
	 * @author jicheng
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param bankCardNum
	 * @param bankCode
	 * @param certNum
	 * @param custName
	 * @throws Exception
	 */
	@Override
	public void updateAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String bankCode,
			String certNum, String custName) throws Exception {
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.updateAccount()..." + cnlCustCode
				+ cnlCnlCode + bankCardNum + certNum + custName + bankCode);

		// TODO Auto-generated method stub
		logger.info("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.updateAccount()...");

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "渠道编号不能为空");
			throw new BizException(cnlCustCode + "渠道编号不能为空");
		}

		if (StringUtils.isNotEmpty(bankCode)) {
			if (StringUtils.isEmpty(bankCardNum)) {
				if (logger.isErrorEnabled())
					logger.error(bankCardNum + "如果存在银行编号则银行卡号不得为空");
				throw new BizException(bankCardNum + "如果存在银行编号则银行卡号不得为空");
			}
		}

		if (StringUtils.isNotEmpty(bankCardNum)) {
			if (StringUtils.isEmpty(bankCode)) {
				if (logger.isErrorEnabled())
					logger.error(bankCode + "如果存在银行卡号则银行编号不得为空");
				throw new BizException(bankCode + "如果存在银行卡号则银行编号不得为空");
			}
		}

		CnlCustAcnt acnt = new CnlCustAcnt();
		acnt.setCnlCnlCode(cnlCnlCode);
		acnt.setCnlCustCode(cnlCustCode);
		CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findByCnlCustAcnt(acnt);
		if (cnlCustAcnt == null) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustAcnt + "没有对应的卡片帐账户");
			throw new BizException(cnlCustAcnt + "没有对应的卡片帐账户");
		}
		String bankAcntCode = cnlCustAcnt.getBankAcntCode();
		if (StringUtils.isEmpty(bankAcntCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankAcntCode + "卡片账户没有对应的银行账户");
			throw new BizException(bankAcntCode + "卡片账户没有对应的银行账户");
		}

		// 根据条件查询出对应银行账号
		CorBankAcnt corBankAcnt = corBankAcntDao.findByBankAcntCode(bankAcntCode);
		if (null == corBankAcnt) {
			if (logger.isErrorEnabled())
				logger.error(corBankAcnt + "此银行编号没有对应的银行账户");
			throw new BizException(corBankAcnt + "此银行编号没有对应的银行账户");
		}

		String cardNum = corBankAcnt.getBankCardNum();
		if (StringUtils.isEmpty(cardNum)) {
			if (logger.isErrorEnabled())
				logger.error(cardNum + "此银行编号没有对应的银行卡号");
			throw new BizException(cardNum + "此银行编号没有对应的银行卡号");
		}
		// 判断卡号是否相同
		if (StringUtils.isEmpty(bankCardNum) || cardNum.equals(bankCardNum)) {
			logger.info(bankCardNum + "修改原卡信息");
			// 如果卡号相同则改变他的其他内容

			// 如果传入的银行编号不为空则更新
			if (StringUtils.isNotEmpty(bankCode)) {
				corBankAcnt.setBankCode(bankCode);
			}

			// 如果传入的证件号不为空则更新
			if (StringUtils.isNotEmpty(certNum)) {
				corBankAcnt.setCertNum(certNum);
			}
			// 如果传入的客户名称不为空则更新
			if (StringUtils.isNotEmpty(custName)) {
				corBankAcnt.setCustName(custName);
			}
			corBankAcntDao.updateCorBankAcnt(corBankAcnt);

		} else {
			logger.info("注销原卡，新增新卡");
			// 如果卡号不相同将银行账号变成无效
			corBankAcnt.setIsValid(Constants.IS_VALID_NOT_VALID);
			corBankAcntDao.updateCorBankAcnt(corBankAcnt);
			// 重新新增一个银行账户
			CorBankAcnt bankAcnt = new CorBankAcnt();
			bankAcnt.setBankAcntCode(bankAcntCode);
			bankAcnt.setCnlCustCode(cnlCustCode);
			bankAcnt.setBankCardNum(bankCardNum);
			bankAcnt.setBankCode(bankCode);
			// 插入证件号
			if (StringUtils.isEmpty(certNum)) {
				bankAcnt.setCertNum(corBankAcnt.getCertNum());
			} else {
				bankAcnt.setCertNum(certNum);
			}

			// 插入客户名称
			if (StringUtils.isEmpty(custName)) {
				bankAcnt.setCustName(corBankAcnt.getCustName());
			} else {
				bankAcnt.setCustName(custName);
			}
			// 插入城市
			bankAcnt.setCity(corBankAcnt.getCity());

			// 插入省份
			bankAcnt.setProvience(corBankAcnt.getProvience());
			logger.info("开始重新创建银行资金账户银行卡");
			addBanktAcnt(bankAcnt);

		}
	}

	/**
	 * 校验银行卡
	 * 
	 * @author jicheng
	 * @param bankCardNum
	 * @param cnlCustCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkCodeAndNum(String bankCardNum, String cnlCustCode) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.checkCodeAndNum()..." + bankCardNum
				+ cnlCustCode);

		if (StringUtils.isEmpty(bankCardNum)) {
			if (logger.isErrorEnabled())
				logger.error(bankCardNum + "银行卡号不能为空");
			throw new BizException(bankCardNum + "银行卡号不能为空");
		}

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}
		CorBankAcnt corBankAcnt = corBankAcntDao.findByCustCode(cnlCustCode);
		if (corBankAcnt == null) {
			if (logger.isErrorEnabled())
				logger.error(corBankAcnt + "没有对应的银行账号");
			throw new BizException(corBankAcnt + "没有对应的银行账号");
		}
		if (corBankAcnt.getBankCardNum().equals(bankCardNum)) {
			return true;
		}

		return false;
	}

	/**
	 * @author jicheng
	 * @param bankCardNum
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return
	 * @throws Exception
	 *             校验银行卡完整版
	 */

	public boolean checkCnlCustCodeAndbankCardNum(String bankCardNum, String cnlCustCode, String cnlCnlCode)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.checkCodeAndNum()..." + bankCardNum
				+ cnlCustCode + cnlCnlCode);

		if (StringUtils.isEmpty(bankCardNum)) {
			if (logger.isErrorEnabled())
				logger.error(bankCardNum + "银行卡号不能为空");
			throw new BizException(bankCardNum + "银行卡号不能为空");
		}

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");
		}
		// 根据会员编号以及渠道编号查询是否有此账户
		CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();
		cnlCustAcnt.setCnlCnlCode(cnlCnlCode);
		cnlCustAcnt.setCnlCustCode(cnlCustCode);
		CnlCustAcnt acnt = cnlCustAcntDao.findByCnlCustAcnt(cnlCustAcnt);
		if (acnt == null) {
			if (logger.isErrorEnabled())
				logger.error(acnt + "没有对应的账户");
			throw new BizException(acnt + "没有对应的账户");
		}
		// 获取银行账户编号
		String bankAcntCode = acnt.getBankAcntCode();
		if (StringUtils.isEmpty(bankAcntCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankAcntCode + "此账户没有对应的银行账户编号");
			throw new BizException(bankAcntCode + "此账户没有对应的银行账户编号");
		}
		// 根据银行账户编号查询银行账户信息
		CorBankAcnt corBankAcnt = corBankAcntDao.findByBankAcntCode(bankAcntCode);
		if (corBankAcnt == null) {
			if (logger.isErrorEnabled())
				logger.error(corBankAcnt + "此银行账户编号在数据库中没有记录");
			throw new BizException(corBankAcnt + "此银行账户编号在数据库中没有记录");
		}
		if (corBankAcnt.getBankCardNum().equals(bankCardNum)) {
			logger.info("校验银行卡符合");
			return true;
		}
		logger.info("校验银行卡不符合");
		return false;
	}

	/**
	 * 根据渠道客户编号查询资金账号信息
	 * 
	 * @param cnlCustCode
	 * @return
	 */

	@Override
	public CorBankAcnt findBankAcnt(String cnlCustCode) throws Exception {
		// TODO Auto-generated method stub

		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.findBankAcnt()..." + cnlCustCode);
		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}
		CorBankAcnt corBankAcnt = corBankAcntDao.findByCustCode(cnlCustCode);
		if (corBankAcnt == null) {
			logger.info("返回银行资金账户信息：" + corBankAcnt);
			return null;
		}
		logger.info("返回银行资金账户信息：" + corBankAcnt);
		return corBankAcnt;
	}

	/**
	 * 根据渠道客户编号查询资金账号信息
	 * 
	 * @param cnlCustCode
	 * @return
	 */
	@Override
	public CorBankAcnt queryBankAcnt(String cnlCustCode, String cnlCnlCode) throws Exception {

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException("会员编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");
		}

		// 根据会员编号以及渠道编号查询是否有此账户
		CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();
		cnlCustAcnt.setCnlCnlCode(cnlCnlCode);
		cnlCustAcnt.setCnlCustCode(cnlCustCode);
		CnlCustAcnt acnt = cnlCustAcntDao.findByCnlCustAcnt(cnlCustAcnt);
		if (acnt == null) {
			if (logger.isErrorEnabled())
				logger.error(acnt + "没有对应的账户");
			throw new BizException(acnt + "没有对应的账户");
		}
		// 获取银行账户编号
		String bankAcntCode = acnt.getBankAcntCode();
		if (StringUtils.isEmpty(bankAcntCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankAcntCode + "没有对应的账户");
			throw new BizException(bankAcntCode + "没有对应的账户");
		}
		//// 根据银行账户编号查询银行账户信息
		CorBankAcnt corBankAcnt = corBankAcntDao.findByBankAcntCode(bankAcntCode);
		if (corBankAcnt == null) {
			if (logger.isErrorEnabled())
				logger.error(corBankAcnt + "此银行账户编号在数据库中没有记录");
			throw new BizException(corBankAcnt + "此银行账户编号在数据库中没有记录");
		}
		logger.info("返回银行资金账户信息：" + corBankAcnt);
		return corBankAcnt;

	}

	/**
	 * @author jicheng 验证银行编号是否存在于数字字典中
	 */
	@Override
	public boolean checkBankCode(String bankCode) throws Exception {
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.checkBankCode()..." + bankCode);

		if (StringUtils.isEmpty(bankCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankCode + "银行编号不能为空");
			throw new BizException(bankCode + "银行编号不能为空");
		}
		List<DataDict> dataDictBankCode = dataDictService.list(IDataDictService.DATA_DICT_TYPE__BANK_NAME);

		if (dataDictBankCode == null) {
			if (logger.isErrorEnabled())
				logger.error(dataDictBankCode + "数据库中没有银行编号的数据字典");
			throw new BizException(dataDictBankCode + "数据库中没有银行编号的数据字典");
		}

		for (DataDict bankCodeData : dataDictBankCode) {
			if (bankCodeData.getCode().equals(bankCode)) {
				logger.info("银行编号存在");
				return true;
			}
		}
		logger.info("银行编号不存在");
		return false;
	}

	/**
	 * @author jicheng 检验分行编号是否存在
	 */
	@Override
	public boolean checkBankBranchCode(String bankBranchCode) throws Exception {

		logger.debug(
				"entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.checkBankBranchCode()..." + bankBranchCode);

		if (StringUtils.isEmpty(bankBranchCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankBranchCode + "分行编号不可为空");
			throw new BizException(bankBranchCode + "分行编号不可为空");
		}
		PblBankDict pblBankDict = pblBankDictDao.findByBankBranchCode(bankBranchCode);
		if (pblBankDict == null) {
			logger.info("支行编号不存在");
			return false;
		}
		logger.info("支行编号存在");
		return true;
	}

	/**
	 * 个人卡片帐开户
	 */
	@Override
	public void createPersonlAccount(String cnlCustCode, String cnlCnlCode, String bankCardNum, String bankCode,
			String certNum, String custName) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.createPersonlAccount()..." + cnlCustCode
				+ cnlCnlCode + bankCardNum + bankCode + certNum + custName);

		CorBankAcnt corBankAcnt = new CorBankAcnt();
		CnlCustAcnt cnlCustAcnt = new CnlCustAcnt();

		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}
		if (StringUtils.isEmpty(bankCardNum)) {
			if (logger.isErrorEnabled())
				logger.error(bankCardNum + "银行卡号不能为空");
			throw new BizException(bankCardNum + "银行卡号不能为空");
		}
		if (StringUtils.isEmpty(bankCode)) {
			if (logger.isErrorEnabled())
				logger.error(bankCode + "银行编号不能为空");
			throw new BizException(bankCode + "银行编号不能为空");
		}
		if (StringUtils.isEmpty(certNum)) {
			if (logger.isErrorEnabled())
				logger.error(certNum + "证件号不能为空");
			throw new BizException(certNum + "证件号不能为空");
		}
		if (StringUtils.isEmpty(custName)) {
			if (logger.isErrorEnabled())
				logger.error(custName + "客户姓名不能为空");
			throw new BizException(custName + "客户姓名不能为空");
		}
		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");
		}

		// 插入会员编号
		corBankAcnt.setCnlCustCode(cnlCustCode);
		// 插入银行编号
		corBankAcnt.setBankCode(bankCode);
		// 证件号码
		corBankAcnt.setCertNum(certNum);
		// 客户姓名
		corBankAcnt.setCustName(custName);
		// 银行卡号
		corBankAcnt.setBankCardNum(bankCardNum);
		// 银行账户编号
		String uid = StringUtils.getUUID();
		corBankAcnt.setBankAcntCode(uid);
		// 总帐户渠道编号
		cnlCustAcnt.setCnlCnlCode(cnlCnlCode);
		// 会员编号
		cnlCustAcnt.setCnlCustCode(cnlCustCode);
		// 总帐户对应银行账户编号
		cnlCustAcnt.setBankAcntCode(uid);
		logger.info("开始创建银行资金账户");
		addBanktAcnt(corBankAcnt);
		logger.info("开始创建和总账户");
		addCustAcnt(cnlCustAcnt);

	}

	/**
	 * @author jicheng 验证账户是否存在是否有效
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return
	 */
	@Override
	public boolean checkCnlCustAcnt(String cnlCustCode, String cnlCnlCode) throws Exception {
		logger.debug("entering CnlCustAcntServiceimpl::CnlCustAcntServiceimpl.checkCnlCustAcnt()..." + cnlCustCode
				+ cnlCnlCode);
		if (StringUtils.isEmpty(cnlCustCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCustCode + "会员编号不能为空");
			throw new BizException(cnlCustCode + "会员编号不能为空");
		}

		if (StringUtils.isEmpty(cnlCnlCode)) {
			if (logger.isErrorEnabled())
				logger.error(cnlCnlCode + "渠道编号不能为空");
			throw new BizException(cnlCnlCode + "渠道编号不能为空");
		}
		CnlCustAcnt acnt = new CnlCustAcnt();
		acnt.setCnlCustCode(cnlCustCode);
		acnt.setCnlCnlCode(cnlCnlCode);
		CnlCustAcnt cnlCustAcnt = cnlCustAcntDao.findByCnlCustAcnt(acnt);
		if (cnlCustAcnt == null) {
			logger.info("账户无效");
			return false;
		}
		logger.info("账户有效");
		return true;
	}

}
