package com.ibs.core.module.cnlcust.service.impl;

import java.util.Date;
import java.util.List;


import com.ibs.core.module.cnlcust.dao.ICnlCustCompanyDao;
import com.ibs.core.module.cnlcust.dao.ICnlCustDao;
import com.ibs.core.module.cnlcust.dao.ICnlCustPersonalDao;
import com.ibs.core.module.cnlcust.domain.CnlCust;
import com.ibs.core.module.cnlcust.domain.CnlCustCompany;
import com.ibs.core.module.cnlcust.domain.CnlCustPersonal;
import com.ibs.core.module.cnlcust.dto.CnlCustDto;
import com.ibs.core.module.cnlcust.dto.CnlCustPersonalDto;
import com.ibs.core.module.cnlcust.service.ICnlCustService;
import com.ibs.core.module.cnlcust.utils.BeanUtil;
import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.StringUtils;


public class CnlCustServiceImpl extends BaseService implements ICnlCustService  {
	
	//protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ICnlCustDao cnlCustDao;  
	private ICnlCustCompanyDao cnlCustCompanyDao;  
	private ICnlCustPersonalDao cnlCustPersonalDao; 
	
	/**
	 * 根据渠道客户编码查询客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return CnlCustDto
	 */
	@Override
	public CnlCustDto getCustByCustCode(String cnlCustCode,String cnlCnlCode) throws Exception  {
		logger.info("CnlCustServiceImpl.getCustByCustCode(cnlCustCode, cnlCnlCode):" + cnlCustCode + "," + cnlCnlCode );
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCode,cnlCnlCode);
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		CnlCustDto cnlCustDto = new CnlCustDto();
		//判断客户类型
		String cnlCustType = cnlCust.getCnlCustType();
		if(cnlCustType.equals(Constants.CUST_TYPE_INDIVIDUAL)){
			CnlCustPersonal cnlCustPersonal = cnlCustPersonalDao.loadBy("custCode",cnlCust.getCustCode());
			BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCustPersonal);
		}else if(cnlCustType.equals(Constants.CUST_TYPE_ENTERPRISE)){
			CnlCustCompany cnlCustCompany = cnlCustCompanyDao.loadBy("custCode", cnlCust.getCustCode());
			BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCustCompany);
		}
		BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCust);
		return cnlCustDto;
	}
	
	/**
	 * 根据渠道客户证件号码查询客户
	 * @author kaijiezhao
	 * @param certNum
	 * @param cnlCnlCode
	 * @return CnlCustDto
	 */
	@Override
	public CnlCustDto getCustByCertNum(String certNum,String cnlCnlCode) throws Exception {
		logger.info("CnlCustServiceImpl.getCustByCertNum(certNum, cnlCnlCode):" + certNum + "," + cnlCnlCode);
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(certNum)){
			logger.error("渠道客户证件号码不能为空");
			throw new BizException("渠道客户证件号码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCert(certNum,cnlCnlCode);
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		CnlCustDto cnlCustDto = new CnlCustDto();
		//判断客户类型
		String cnlCustType = cnlCust.getCnlCustType();
		if(cnlCustType.equals(Constants.CUST_TYPE_INDIVIDUAL)){
			CnlCustPersonal cnlCustPersonal = cnlCustPersonalDao.loadBy("custCode",cnlCust.getCustCode());
			BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCustPersonal);
		}else if(cnlCustType.equals(Constants.CUST_TYPE_ENTERPRISE)){
			CnlCustCompany cnlCustCompany = cnlCustCompanyDao.loadBy("custCode", cnlCust.getCustCode());
			BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCustCompany);
		}
		BeanUtil.copyBasicTypeProperties(cnlCustDto, cnlCust);
		return cnlCustDto;
	}
	
	/**
	 * 验证渠道客户编码唯一性
	 * 在同一渠道下，不区分客户状态，根据渠道客户编码只能存在唯一的有效客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @return Boolean
	 */
	@Override
	public Boolean validateCnlCustCode(String cnlCustCode,String cnlCnlCode) throws Exception {
		logger.info("CnlCustServiceImpl.validateCnlCustCode(cnlCustCode, cnlCnlCode):" + cnlCustCode + "," + cnlCnlCode);
		Boolean res = false;
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCode, cnlCnlCode);
		if(cnlCust != null)
			res = true;
		return res;
	}

	/**
	 * 验证渠道编码、姓名、证件号码的唯一性
	 * 在同一渠道下，根据姓名和证件号码只能存在唯一的有效并且未注销的客户
	 * @author kaijiezhao
	 * @param cnlCnlCode
	 * @param localName
	 * @param certNum
	 * @return Boolean
	 */
	@Override
	public Boolean validateCustInCnl(String cnlCnlCode, String localName, String certNum) throws Exception {
		logger.info("CnlCustServiceImpl.validateCustInCnl(cnlCnlCode, localName, certNum):" + cnlCnlCode + "," + localName + "," + certNum);
		Boolean res = false;
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(localName)){
			logger.error("渠道客户名不能为空");
			throw new BizException("渠道客户名不能为空");
		}
		if(StringUtils.isEmpty(certNum)){
			logger.error("渠道客户证件号码不能为空");
			throw new BizException("渠道客户证件号码不能为空");
		}
		List<CnlCust> list = cnlCustDao.validateCustInCnl(cnlCnlCode, localName, certNum);
		if(list.size() == 1)
			res = true;
		return res;
	}
	
	
	/**
	 * 使用DTO方式新增个人客户
	 * @author kaijiezhao
	 * @param cnlCustPersonalDto
	 * @throws Exception
	 */
	@Override
	public void addCnlPersonalCust(CnlCustPersonalDto cnlCustPersonalDto) throws Exception {
		logger.info("CnlCustServiceImpl.addCnlPersonalCust(CnlCustPersonalDto):" + cnlCustPersonalDto);
		if(cnlCustPersonalDto == null){
			logger.error("DTO对象为空");
			throw new BizException("DTO对象为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getCnlCnlCode())){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getCnlCustCode())){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getLocalName())){
			logger.error("渠道客户名不能为空");
			throw new BizException("渠道客户名不能为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getCertNum())){
			logger.error("渠道客户证件号码不能为空");
			throw new BizException("渠道客户证件号码不能为空");
		}
		//验证渠道客户编码是否已存在
		Boolean res = validateCnlCustCode(cnlCustPersonalDto.getCnlCustCode(),cnlCustPersonalDto.getCnlCnlCode());
		if(res == true){
			logger.error("此客户编码已存在");
			throw new BizException("此客户编码已存在");
		}
		res = validateCustInCnl(cnlCustPersonalDto.getCnlCnlCode(), cnlCustPersonalDto.getLocalName(), cnlCustPersonalDto.getCertNum());
		if(res == true){
			logger.error("此客户已注册过");
			throw new BizException("此客户已注册过");
		}
		//将DTO类的属性分别放置在对应的domain类中
		CnlCust cnlCust = new CnlCust();
		CnlCustPersonal cnlCustPersonal = new CnlCustPersonal();
		//使用UUID生成客户内部唯一编码
		String custCode = StringUtils.getUUID();
		String creator = Constants.SYSADMIN;
		Date createTime = new Date();
		
		cnlCust.setCustCode(custCode);
		cnlCust.setCnlCustType(Constants.CUST_TYPE_INDIVIDUAL);
		cnlCust.setCustStatus(Constants.CNL_CUST_STATUS_PASSED);
		cnlCust.setCertType(Constants.CERT_TYPE_ID);
		cnlCust.setIsValid(Constants.IS_VALID_VALID);
		cnlCust.setCountry(Constants.COUNTRY_37);
		cnlCust.setCustLevel(Constants.CUST_LEVEL_LEVEL_I);
		cnlCust.setRealNameLevel(Constants.CERTIFICATION_LEVEL_LEVEL_1);
		cnlCust.setCreator(creator);
		cnlCust.setCreateTime(createTime);
		BeanUtil.copyBasicTypeProperties(cnlCust,cnlCustPersonalDto);
		cnlCust.setId(null);
		cnlCustDao.saveOrUpdate(cnlCust);
		
		cnlCustPersonal.setCustCode(custCode);
		cnlCustPersonal.setIsValid(Constants.IS_VALID_VALID);
		cnlCustPersonal.setCountry(Constants.COUNTRY_37);
		cnlCustPersonal.setName(cnlCustPersonalDto.getLocalName());
		cnlCustPersonal.setCreator(creator);
		cnlCustPersonal.setCreateTime(createTime);
		BeanUtil.copyBasicTypeProperties(cnlCustPersonal,cnlCustPersonalDto);
		cnlCustPersonal.setId(null);
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
	}
	
	/**
	 * 根据多个具体参数来新增个人客户
	 * @author kaijiezhao
	 * @exception Exception
	 */
	@Override
	public void addCnlPersonalCustByParams(String cnlCustCode, String cnlCnlCode,String localName, String certNum, Date certExpireDate,
			String phonenum, String address) throws Exception {
		logger.info("CnlCustServiceImpl.addCnlPersonalCustByParams(cnlCustCode, cnlCnlCode, localName, certNum, certExpireDate, phonenum, address):" + cnlCustCode + 
			"," + cnlCnlCode + "," + localName + "," + certNum + "," + certExpireDate +"," + phonenum +"," + address);
		//检查必选字段是否为null
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		if(StringUtils.isEmpty(localName)){
			logger.error("渠道客户名不能为空");
			throw new BizException("渠道客户名不能为空");
		}
		if(StringUtils.isEmpty(certNum)){
			logger.error("渠道客户证件号码不能为空");
			throw new BizException("渠道客户证件号码不能为空");
		}
		//验证渠道客户编码是否存在
		Boolean res = this.validateCnlCustCode(cnlCustCode,cnlCnlCode);
		if(res == true){
			logger.error("此客户编码已存在");
			throw new BizException("此客户编码已存在");
		}
		res = validateCustInCnl(cnlCnlCode, localName, certNum);
		if(res == true){
			logger.error("此客户已注册过");
			throw new BizException("此客户已注册过");
		}
		String creator = Constants.SYSADMIN;
		Date createTime = new Date();
		String custCode = StringUtils.getUUID();
		
		CnlCust cnlCust = new CnlCust();
		cnlCust.setCnlCustCode(cnlCustCode);
		cnlCust.setCnlCnlCode(cnlCnlCode);
		cnlCust.setLocalName(localName);
		cnlCust.setCertNum(certNum);
		if(null != certExpireDate)
			cnlCust.setCertExpireDate(certExpireDate);
		cnlCust.setCustCode(custCode);
		cnlCust.setCnlCustType(Constants.CUST_TYPE_INDIVIDUAL);
		cnlCust.setCustStatus(Constants.CNL_CUST_STATUS_PASSED);
		cnlCust.setIsValid(Constants.IS_VALID_VALID);
		cnlCust.setCertType(Constants.CERT_TYPE_ID);
		cnlCust.setCountry(Constants.COUNTRY_37);
		cnlCust.setCustLevel(Constants.CUST_LEVEL_LEVEL_I);
		cnlCust.setRealNameLevel(Constants.CERTIFICATION_LEVEL_LEVEL_1);
		cnlCust.setCreator(creator);
		cnlCust.setCreateTime(createTime);
		cnlCustDao.saveOrUpdate(cnlCust);
		
		CnlCustPersonal cnlCustPersonal = new CnlCustPersonal();
		cnlCustPersonal.setCnlCustCode(cnlCustCode);
		cnlCustPersonal.setCustCode(custCode);
		cnlCustPersonal.setName(localName);
		cnlCustPersonal.setIsValid(Constants.IS_VALID_VALID);
		cnlCustPersonal.setCountry(Constants.COUNTRY_37);
		cnlCustPersonal.setCreator(creator);
		cnlCustPersonal.setCreateTime(createTime);
		if(StringUtils.isNotEmpty(phonenum))
			cnlCustPersonal.setPhonenum(phonenum);
		if(StringUtils.isNotEmpty(address))
			cnlCustPersonal.setAddr(address);
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
	}
	
//	/**
//	 * 使用DTO方式新增公司渠道客户
//	 * @author kaijiezhao
//	 * @param cnlCustCompanyDto
//	 * @exception Exception
//	 */
//	@Override
//	public void addCnlCompanyCust(CnlCustCompanyDto cnlCustCompanyDto) throws Exception {
//		if(cnlCustCompanyDto == null){
//			throw new BizException("DTO对象为空");
//		}
//		if(StringUtils.isEmpty(cnlCustCompanyDto.getCnlCustCode()) || StringUtils.isEmpty(cnlCustCompanyDto.getCnlCnlCode()) 
//				|| StringUtils.isEmpty(cnlCustCompanyDto.getLocalName()) || StringUtils.isEmpty(cnlCustCompanyDto.getCertNum()) 
//				|| cnlCustCompanyDto.getCertExpireDate() == null){
//			throw new BizException("参数不合法");
//		}
//		//验证渠道客户编码是否已存在
//		Boolean res = validateCnlCustCode(cnlCustCompanyDto.getCnlCustCode(),cnlCustCompanyDto.getCnlCnlCode());
//		if(res == true){
//			throw new BizException("此客户编码已存在");
//		}
//		res = validateCustInCnl(cnlCustCompanyDto.getCnlCnlCode(), cnlCustCompanyDto.getLocalName(), cnlCustCompanyDto.getCertNum());
//		if(res == true){
//			throw new BizException("此客户已注册过");
//		}
//		//将DTO类的属性分别放置在对应的domain类中
//		CnlCust cnlCust = new CnlCust();
//		CnlCustCompany cnlCustCompany = new CnlCustCompany();
//		//使用UUID生成客户内部唯一编码
//		String custCode = StringUtils.getUUID();
//		String creator = Constants.SYSADMIN;
//		Date createTime = new Date();
//		
//		cnlCust.setCustCode(custCode);
//		cnlCust.setCnlCustType(Constants.CUST_TYPE_ENTERPRISE);
//		cnlCust.setCustStatus(Constants.CNL_CUST_STATUS_PASSED);
//		cnlCust.setCertType(Constants.CERT_TYPE_MERCHANT_LICENSE);
//		cnlCust.setIsValid(Constants.IS_VALID_VALID);
//		cnlCust.setCountry(Constants.COUNTRY_37);
//		cnlCust.setCustLevel(Constants.CUST_LEVEL_LEVEL_I);
//		cnlCust.setRealNameLevel(Constants.CERTIFICATION_LEVEL_LEVEL_1);
//		cnlCust.setCreator(creator);
//		cnlCust.setCreateTime(createTime);
//		BeanUtil.copyBasicTypeProperties(cnlCust,cnlCustCompanyDto);
//		cnlCust.setId(null);
//		cnlCustDao.saveOrUpdate(cnlCust);
//		
//		cnlCustCompany.setCustCode(custCode);
//		cnlCustCompany.setIsValid(Constants.IS_VALID_VALID);
//		cnlCustCompany.setCountry(Constants.COUNTRY_37);
//		cnlCustCompany.setCreator(creator);
//		cnlCustCompany.setCreateTime(createTime);
//		BeanUtil.copyBasicTypeProperties(cnlCustCompany,cnlCustCompanyDto);
//		cnlCustCompany.setId(null);
//		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
//	}
	
	/**
	 * 传输多个具体参数来新增企业客户
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param localName
	 * @param certNum
	 * @param certExpireDate
	 * @param companyTel
	 * @param address
	 * @exception exception
	 * 
	 */
	@Override
	public void addCnlCompanyCustByParams(String cnlCustCode, String cnlCnlCode,String localName, String certNum, Date certExpireDate,
			String companyTel, String address) throws Exception {
		logger.info("CnlCustServiceImpl.addCnlCompanyCustByParams(cnlCustCode, cnlCnlCode, localName, certNum, certExpireDate, companyTel, address):" + cnlCustCode +  
				"," + cnlCnlCode + "," + localName + "," + certNum + "," + certExpireDate +"," + companyTel +"," + address);
		//检查必选字段是否为null
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		if(StringUtils.isEmpty(localName)){
			logger.error("渠道客户名不能为空");
			throw new BizException("渠道客户名不能为空");
		}
		if(StringUtils.isEmpty(certNum)){
			logger.error("渠道客户证件号码不能为空");
			throw new BizException("渠道客户证件号码不能为空");
		}
		//验证渠道客户编码是否存在
		Boolean res = this.validateCnlCustCode(cnlCustCode,cnlCnlCode);
		if(res == true){
			logger.error("此客户编码已存在");
			throw new BizException("此客户编码已存在");
		}
		res = validateCustInCnl(cnlCnlCode, localName, certNum);
		if(res == true){
			logger.error("此客户已注册过");
			throw new BizException("此客户已注册过");
		}
		String creator = Constants.SYSADMIN;
		Date createTime = new Date();
		String custCode = StringUtils.getUUID();
		
		CnlCust cnlCust = new CnlCust();
		cnlCust.setCnlCustCode(cnlCustCode);
		cnlCust.setCnlCnlCode(cnlCnlCode);
		cnlCust.setLocalName(localName);
		cnlCust.setCertNum(certNum);
		cnlCust.setCertExpireDate(certExpireDate);
		cnlCust.setCustCode(custCode);
		cnlCust.setCnlCustType(Constants.CUST_TYPE_ENTERPRISE);
		cnlCust.setCustStatus(Constants.CNL_CUST_STATUS_PASSED);
		cnlCust.setIsValid(Constants.IS_VALID_VALID);
		cnlCust.setCertType(Constants.CERT_TYPE_MERCHANT_LICENSE);
		cnlCust.setCountry(Constants.COUNTRY_37);
		cnlCust.setCustLevel(Constants.CUST_LEVEL_LEVEL_I);
		cnlCust.setRealNameLevel(Constants.CERTIFICATION_LEVEL_LEVEL_1);
		if(null != certExpireDate)
			cnlCust.setCertExpireDate(certExpireDate);
		cnlCust.setCreator(creator);
		cnlCust.setCreateTime(createTime);
		cnlCustDao.saveOrUpdate(cnlCust);
		
		CnlCustCompany cnlCustCompany = new CnlCustCompany();
		cnlCustCompany.setCnlCustCode(cnlCustCode);
		cnlCustCompany.setCustCode(custCode);
		cnlCustCompany.setIsValid(Constants.IS_VALID_VALID);
		cnlCustCompany.setCountry(Constants.COUNTRY_37);
		cnlCustCompany.setCompanyName(localName);
		cnlCustCompany.setCreator(creator);
		cnlCustCompany.setCreateTime(createTime);
		if(StringUtils.isNotEmpty(companyTel))
			cnlCustCompany.setCompanyTel(companyTel);
		if(StringUtils.isNotEmpty(address))
			cnlCustCompany.setAddr(address);
		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
	}
	
	/**
	 * 更新个人渠道客户
	 * @author kaijiezhao
	 * @param cnlCustPersonalDto
	 * @throws Exception
	 */
	@Override
	public void updateCnlPersonalCust(CnlCustPersonalDto cnlCustPersonalDto) throws Exception {
		logger.info("CnlCustServiceImpl.updateCnlPersonalCust(CnlCustPersonalDto):" + cnlCustPersonalDto);
		if(cnlCustPersonalDto == null){
			logger.error("DTO对象为空");
			throw new BizException("DTO对象为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getCnlCnlCode())){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustPersonalDto.getCnlCustCode())){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustPersonalDto.getCnlCustCode(),cnlCustPersonalDto.getCnlCnlCode());
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		if(!cnlCust.getCnlCustType().equals(Constants.CUST_TYPE_INDIVIDUAL)){
			logger.error("客户类型不符合");
			throw new BizException("客户类型不符合");
		}
		CnlCustPersonal cnlCustPersonal = cnlCustPersonalDao.loadBy("custCode", cnlCust.getCustCode());
		//将DTO类的属性分别放置在对应的domain类中
		BeanUtil.copyBasicTypeProperties(cnlCustPersonal,cnlCustPersonalDto);
		BeanUtil.copyBasicTypeProperties(cnlCust,cnlCustPersonalDto);
		//修改人，修改时间
		Date updateTime = new Date();
		String updator = Constants.SYSADMIN;
		cnlCust.setUpdateTime(updateTime);
		cnlCust.setUpdator(updator);
		cnlCustDao.saveOrUpdate(cnlCust);
		
		cnlCustPersonal.setUpdateTime(updateTime);
		cnlCustPersonal.setUpdator(updator);
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
	}
	
	/**
	 * 根据具体字段参数更新个人客户信息
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param phonenum
	 * @param address
	 * @param certExpireDate
	 * @throws Exception
	 */
	@Override
	public void updateCnlPersonalCustByParams(String cnlCustCode,String cnlCnlCode, String phonenum, String address,Date certExpireDate) throws Exception {
		logger.info("CnlCustServiceImpl.updateCnlPersonalCustByParams(cnlCustCode, cnlCnlCode, phonenum, address, certExpireDate)"+ cnlCustCode + "," + cnlCnlCode +
				"," + phonenum + "," + address + "," + certExpireDate);
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCode,cnlCnlCode);
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		if(!cnlCust.getCnlCustType().equals(Constants.CUST_TYPE_INDIVIDUAL)){
			logger.error("客户类型不符合");
			throw new BizException("客户类型不符合");
		}
		CnlCustPersonal cnlCustPersonal = cnlCustPersonalDao.loadBy("custCode", cnlCust.getCustCode());
		if(null != certExpireDate)
			cnlCust.setCertExpireDate(certExpireDate);
		if(StringUtils.isNotEmpty(phonenum))
			cnlCustPersonal.setPhonenum(phonenum);
		if(StringUtils.isNotEmpty(address))
			cnlCustPersonal.setAddr(address);
		
		//修改人，修改时间
		Date updateTime = new Date();
		String updator = Constants.SYSADMIN;
		
		cnlCust.setUpdateTime(updateTime);
		cnlCust.setUpdator(updator);
		cnlCustPersonal.setUpdateTime(updateTime);
		cnlCustPersonal.setUpdator(updator);
		cnlCustDao.saveOrUpdate(cnlCust);
		cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
		
	}
	
//	/**
//	 * 更新企业渠道客户
//	 * @author kaijiezhao
//	 * @param cnlCustCompanyDto
//	 * @throws Exception
//	 */
//	@Override
//	public void updateCnlCompanyCust(CnlCustCompanyDto cnlCustCompanyDto) throws Exception {
//		if(cnlCustCompanyDto == null){
//			throw new BizException("DTO对象为空");
//		}
//		if(StringUtils.isEmpty(cnlCustCompanyDto.getCnlCustCode()) || StringUtils.isEmpty(cnlCustCompanyDto.getCnlCnlCode())){
//			throw new BizException("参数不合法");
//		}
//		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCompanyDto.getCnlCustCode(),cnlCustCompanyDto.getCnlCnlCode());
//		if(cnlCust == null)
//			throw new BizException("客户不存在");
//		CnlCustCompany cnlCustCompany = cnlCustCompanyDao.loadBy("custCode", cnlCust.getCustCode());
//		//将DTO类的属性分别放置在对应的domain类中
//		BeanUtil.copyBasicTypeProperties(cnlCustCompany,cnlCustCompanyDto);
//		BeanUtil.copyBasicTypeProperties(cnlCust,cnlCustCompanyDto);
//		//修改人，修改时间
//		Date updateTime = new Date();
//		String updator = Constants.SYSADMIN;
//		
//		cnlCust.setUpdateTime(updateTime);
//		cnlCust.setUpdator(updator);
//		cnlCustCompany.setUpdateTime(updateTime);
//		cnlCustCompany.setUpdator(updator);
//		cnlCustDao.saveOrUpdate(cnlCust);
//		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
//	}

	/**
	 * 根据具体字段参数更新企业客户信息
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @param companyTel
	 * @param address
	 * @param certExpireDate
	 * @throws Exception
	 */
	@Override
	public void updateCnlCompanyCustByParams(String cnlCustCode,String cnlCnlCode,String companyTel,String address, Date certExpireDate) throws Exception {
		logger.info("CnlCustServiceImpl.updateCnlCompanyCustByParams(cnlCustCode, cnlCnlCode, companyTel, address, certExpireDate):" + cnlCustCode + "," + cnlCnlCode +
				"," + companyTel + "," + address + "," + certExpireDate);
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCode,cnlCnlCode);
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		if(!cnlCust.getCnlCustType().equals(Constants.CUST_TYPE_ENTERPRISE)){
			logger.error("客户类型不符合");
			throw new BizException("客户类型不符合");
		}
		CnlCustCompany cnlCustCompany = cnlCustCompanyDao.loadBy("cnlCustCode", cnlCust.getCnlCustCode());
		if(StringUtils.isNotEmpty(companyTel))
			cnlCustCompany.setCompanyTel(companyTel);
		if(StringUtils.isNotEmpty(address))
			cnlCustCompany.setAddr(address);
		if(null != certExpireDate)
			cnlCust.setCertExpireDate(certExpireDate);
		//修改人，修改时间
		Date updateTime = new Date();
		String updator = Constants.SYSADMIN;
		
		cnlCust.setUpdateTime(updateTime);
		cnlCust.setUpdator(updator);
		cnlCustCompany.setUpdateTime(updateTime);
		cnlCustCompany.setUpdator(updator);
		cnlCustDao.saveOrUpdate(cnlCust);
		cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
		
	}
	/**
	 * 删除渠道客户，将主表中客户状态更改为已注销
	 * @author kaijiezhao
	 * @param cnlCustCode
	 * @param cnlCnlCode
	 * @throws Exception
	 */
	@Override
	public void deleteCnlCust(String cnlCustCode,String cnlCnlCode) throws Exception {
		logger.info("CnlCustServiceImpl.deleteCnlCust(cnlCustCode, cnlCnlCode):" + cnlCustCode + "," + cnlCnlCode);
		if(StringUtils.isEmpty(cnlCnlCode)){
			logger.error("渠道编码不能为空");
			throw new BizException("渠道编码不能为空");
		}
		if(StringUtils.isEmpty(cnlCustCode)){
			logger.error("渠道客户编码不能为空");
			throw new BizException("渠道客户编码不能为空");
		}
		CnlCust cnlCust = cnlCustDao.findByCnlAndCode(cnlCustCode, cnlCnlCode);
		if(cnlCust == null){
			logger.error("客户不存在");
			throw new BizException("客户不存在");
		}
		if(cnlCust.getIsValid().equals(Constants.IS_VALID_NOT_VALID) || cnlCust.getCustStatus().equals(Constants.CNL_CUST_STATUS_WRITTEN_OFF)){
			logger.error("此客户信息无效");
			throw new BizException("此客户信息无效");
		}
		String updator = Constants.SYSADMIN;
		Date updateTime = new Date();
		cnlCust.setUpdateTime(updateTime);
		cnlCust.setUpdator(updator);
		cnlCust.setCustStatus(Constants.CNL_CUST_STATUS_WRITTEN_OFF);
		cnlCustDao.saveOrUpdate(cnlCust);
		
		if(cnlCust.getCnlCustType().equals(Constants.CUST_TYPE_INDIVIDUAL)){
			CnlCustPersonal cnlCustPersonal = cnlCustPersonalDao.loadBy("custCode", cnlCust.getCustCode());
			cnlCustPersonal.setUpdateTime(updateTime);
			cnlCustPersonal.setUpdator(updator);
			cnlCustPersonalDao.saveOrUpdate(cnlCustPersonal);
		}else if(cnlCust.getCnlCustType().equals(Constants.CUST_TYPE_ENTERPRISE)){
			CnlCustCompany cnlCustCompany = cnlCustCompanyDao.loadBy("custCode", cnlCust.getCustCode());
			cnlCustCompany.setUpdateTime(updateTime);
			cnlCustCompany.setUpdator(updator);
			cnlCustCompanyDao.saveOrUpdate(cnlCustCompany);
		}
	}

	
	
	/**
	 * get/set方法
	 * 
	 */
	public ICnlCustDao getCnlCustDao() {
		return cnlCustDao;
	}

	public void setCnlCustDao(ICnlCustDao cnlCustDao) {
		this.cnlCustDao = cnlCustDao;
	}

	public ICnlCustCompanyDao getCnlCustCompanyDao() {
		return cnlCustCompanyDao;
	}

	public void setCnlCustCompanyDao(ICnlCustCompanyDao cnlCustCompanyDao) {
		this.cnlCustCompanyDao = cnlCustCompanyDao;
	}

	public ICnlCustPersonalDao getCnlCustPersonalDao() {
		return cnlCustPersonalDao;
	}

	public void setCnlCustPersonalDao(ICnlCustPersonalDao cnlCustPersonalDao) {
		this.cnlCustPersonalDao = cnlCustPersonalDao;
	}




}
