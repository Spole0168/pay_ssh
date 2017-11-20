/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.corcust.biz.impl;

import java.util.UUID;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.corcust.biz.ICorCustPersonalBiz;
import com.ibs.core.module.corcust.dao.ICorCustAddrDao;
import com.ibs.core.module.corcust.dao.ICorCustDao;
import com.ibs.core.module.corcust.dao.ICorCustPersonalDao;
import com.ibs.core.module.corcust.dao.ICorCustPhonesDao;
import com.ibs.core.module.corcust.domain.CorCust;
import com.ibs.core.module.corcust.domain.CorCustPersonal;
import com.ibs.core.module.corcust.domain.CorCustPhones;
import com.ibs.core.module.corcust.dto.CorCompound;
import com.ibs.core.module.corcust.dto.CorCondition;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPersonalBizImpl extends BaseBiz implements ICorCustPersonalBiz {

	private ICorCustDao corCustDao;
	private ICorCustPersonalDao corCustPersonalDao;
	private ICorCustPhonesDao corCustPhonesDao;
	private ICorCustAddrDao corCustAddrDao;
	
	private IExcelService excelService;
	
	
	/**
	 * 使用UUID生成客户唯一编码
	 */
	public String getOnlyCode(){
		String res = UUID.randomUUID().toString().replace("-", "");
		return res;
	}

	/**
	 * 将DTO对象中的字段内容放入相对应的实体类中，传入DAO执行持久化操作
	 */
	@Override
	public void saveCorCompound(CorCompound corCompound) {
		logger.info("entering action::CorCustPersonalBizImpl.saveCorCompound()...");
		if(corCompound == null)
			return;
		//生成客户唯一编码，赋给每个关联的实体类
		String custCode = getOnlyCode();
		
		//创建核心主表
		CorCust corCust = new CorCust();
		corCust.setId(null);
		corCust.setCustCode(custCode);
		// 1:个人客户   2：公司客户
		corCust.setCustType("1");
		corCust.setLocalName(corCompound.getLocalName());
		corCust.setCountry(corCompound.getCountry());
		//用户是否已验证： 01：未通过 02：已通过 03：待审核 04：无效 
		corCust.setStatus("03");
		//corCust.setFirstName(corCompound.getFirstName());
		//corCust.setLastName(corCompound.getLastName());
		//corCust.setCertType(corCompound.getCertType());
		//corCust.setCertNum(corCompound.getCertNum());
		//corCust.setCertExpireDate(corCompound.getCertExpireDate());
		System.out.println(corCust);
		corCustDao.saveOrUpdate(corCust);
		
		//创建核心副表
		CorCustPersonal corCustPersonal = new CorCustPersonal();
		corCustPersonal.setId(null);
		corCustPersonal.setCustCode(custCode);
		corCustPersonal.setBirthday(corCompound.getBirthday());
		corCustPersonal.setGender(corCompound.getGender());
		corCustPersonal.setIsMarried(corCompound.getIsMarried());
		corCustPersonal.setCountry(corCompound.getCountry());
		corCustPersonal.setProvience(corCompound.getProvience());
		corCustPersonal.setCity(corCompound.getCity());
		corCustPersonal.setDistrict(corCompound.getDistrict());
		corCustPersonal.setAddr(corCompound.getAddr());
		corCustPersonal.setPostcode(corCompound.getPostcode());
		corCustPersonal.setHighestEdu(corCompound.getHighestEdu());
		corCustPersonal.setOccupation(corCompound.getOccupation());
		corCustPersonal.setJobTitle(corCompound.getJobTitle());
		corCustPersonal.setEmployer(corCompound.getEmployer());
		corCustPersonal.setEmail(corCompound.getEmail());
		corCustPersonal.setSpouseLocalName(corCompound.getSpouseLocalName());
		corCustPersonal.setSpousePhonenum(corCompound.getSpousePhonenum());
		System.out.println(corCustPersonal);
		corCustPersonalDao.saveOrUpdate(corCustPersonal);
		
		//客户手机号码
		CorCustPhones corCustPhones = new CorCustPhones();	
		corCustPhones.setId(null);
		corCustPhones.setCustCode(custCode);
		//1:默认电话  0：非默认电话
		corCustPhones.setIsDefault("1");
		//1:个人电话  2：工作电话  3：其他类型
		corCustPhones.setPhoneType("1");
		corCustPhones.setPhoneNum(corCompound.getPhoneNum());
		System.out.println(corCustPhones);
		corCustPhonesDao.saveOrUpdate(corCustPhones);
		
		//客户选填工作电话
		String workTel = corCompound.getWorkTel();
		if(workTel ==null || workTel == "")
			return;
		CorCustPhones corCustPhones2 = new CorCustPhones();
		corCustPhones2.setId(null);
		corCustPhones2.setCustCode(custCode);
		corCustPhones2.setIsDefault("0");
		corCustPhones2.setPhoneType("2");
		corCustPhones2.setPhoneNum(workTel);
		System.out.println(corCustPhones2);
		corCustPhonesDao.saveOrUpdate(corCustPhones2);
	}
	
	/**
	 * 根据ID查询到要修改的页面信息，回显给页面
	 * 此ID为CorCompound的ID，设定为和CorCust的ID相同
	 * 
	 */
	@Override
	public CorCompound getCorCompoundByPersonalId(String id) {
		logger.info("entering action::CorCustPersonalBizImpl.getCorCustPersonalById()...");
		String custCode = corCustDao.getCustCodeById(id);
		CorCustPersonal corCustPersonal = corCustPersonalDao.getByCustCode(custCode);
		CorCustPhones corCustPhones = corCustPhonesDao.getByCustCode(custCode, "1", "1");
		CorCustPhones corCustPhones2 = corCustPhonesDao.getByCustCode(custCode, "0", "2");
		CorCust corCust = corCustDao.getByCustCode(custCode);
		CorCompound compound = new CorCompound();
		
		if(corCust != null){
			compound.setId(corCust.getId().trim());
			compound.setCustCode(corCust.getCustCode().trim());
			compound.setLocalName(corCust.getLocalName()!=null?corCust.getLocalName().trim():null);
			compound.setFirstName(corCust.getFirstName()!=null?corCust.getFirstName().trim():null);
			compound.setLastName(corCust.getLastName()!=null?corCust.getLastName().trim():null);
			compound.setRealNameLeve(corCust.getRealNameLeve()!=null?corCust.getRealNameLeve().trim():null);
			compound.setCustLevel(corCust.getCustLevel()!=null?corCust.getCustLevel().trim():null);
			compound.setStatus(corCust.getStatus()!=null?corCust.getStatus().trim():null);
			compound.setDataSource(corCust.getDataSource()!=null?corCust.getDataSource().trim():null);
		}
		if(corCustPersonal != null){
			compound.setBirthday(corCustPersonal.getBirthday());
			compound.setGender(corCustPersonal.getGender().trim());
			compound.setIsMarried(corCustPersonal.getIsMarried().trim());
			compound.setCountry(corCustPersonal.getCountry().trim());
			compound.setProvience(corCustPersonal.getProvience().trim());
			compound.setCity(corCustPersonal.getCity().trim());
			compound.setDistrict(corCustPersonal.getDistrict().trim());
			compound.setAddr(corCustPersonal.getAddr().trim());
			compound.setPostcode(corCustPersonal.getPostcode()!=null?corCustPersonal.getPostcode().trim():null);
			compound.setHighestEdu(corCustPersonal.getHighestEdu()!=null?corCustPersonal.getHighestEdu().trim():null);
			compound.setOccupation(corCustPersonal.getOccupation()!=null?corCustPersonal.getOccupation().trim():null);
			compound.setJobTitle(corCustPersonal.getJobTitle().trim());
			compound.setEmployer(corCustPersonal.getEmployer().trim());
			compound.setEmail(corCustPersonal.getEmail()!=null?corCustPersonal.getEmail().trim():null);
			compound.setSpouseLocalName(corCustPersonal.getSpouseLocalName().trim());
			compound.setSpousePhonenum(corCustPersonal.getSpousePhonenum()!=null?corCustPersonal.getSpousePhonenum().trim():null);
		}
		if(corCustPhones != null)
			compound.setPhoneNum(corCustPhones.getPhoneNum()!=null?corCustPhones.getPhoneNum().trim():null);
		if(corCustPhones2 != null)
			compound.setWorkTel(corCustPhones2.getPhoneNum()!=null?corCustPhones2.getPhoneNum().trim():null);
		return compound;
	}
	
	/**
	 * 修改
	 * 根据custCode从数据库中查出数据信息，再进行字段更改
	 */
	@Override
	public void updateCorCompound(CorCompound corCompound) {
		logger.info("entering action::CorCustPersonalBizImpl.updateCorCompound()...");
		String custCode = corCompound.getCustCode();
		//查询custCode对应的核心主表的记录，重新赋值
		CorCust corCust = corCustDao.getByCustCode(custCode);
		corCust.setLocalName(corCompound.getLocalName());
		//corCust.setFirstName(corCompound.getFirstName());
		//corCust.setLastName(corCompound.getLastName());
		//corCust.setCertType(corCompound.getCertType());
		//corCust.setCertNum(corCompound.getCertNum());
		//corCust.setCertExpireDate(corCompound.getCertExpireDate());
		corCustDao.saveOrUpdate(corCust);
		
		//查询custCode对应的核心副表的记录，重新赋值
		CorCustPersonal corCustPersonal = corCustPersonalDao.getByCustCode(custCode);
		corCustPersonal.setBirthday(corCompound.getBirthday());
		corCustPersonal.setGender(corCompound.getGender());
		corCustPersonal.setIsMarried(corCompound.getIsMarried());
		corCustPersonal.setCountry(corCompound.getCountry());
		corCustPersonal.setProvience(corCompound.getProvience());
		corCustPersonal.setCity(corCompound.getCity());
		corCustPersonal.setDistrict(corCompound.getDistrict());
		corCustPersonal.setAddr(corCompound.getAddr());
		corCustPersonal.setPostcode(corCompound.getPostcode());
		corCustPersonal.setHighestEdu(corCompound.getHighestEdu());
		corCustPersonal.setOccupation(corCompound.getOccupation());
		corCustPersonal.setJobTitle(corCompound.getJobTitle());
		corCustPersonal.setEmployer(corCompound.getEmployer());
		corCustPersonal.setEmail(corCompound.getEmail());
		corCustPersonal.setSpouseLocalName(corCompound.getSpouseLocalName());
		corCustPersonal.setSpousePhonenum(corCompound.getSpousePhonenum());
		corCustPersonalDao.saveOrUpdate(corCustPersonal);
		
		//更改默认电话号码
		//参数2： 是否为默认，参数3： 电话类型
		CorCustPhones corCustPhones = corCustPhonesDao.getByCustCode(custCode,"1","1");
		corCustPhones.setPhoneNum(corCompound.getPhoneNum());
		corCustPhonesDao.saveOrUpdate(corCustPhones);
		
		//更改非默认工作电话号码
		//如果修改框为空，就返回；如果修改框有内容，就判断数据库是否有内容，如果数据库有内容就拿出来，如果数据库没内容就新建对象，然后保存
		//欠缺考虑如果数据库有对象，要将工作电话删除的情况
		String workTel = corCompound.getWorkTel();
		if(workTel == null || workTel == "")
			return;
		CorCustPhones corCustPhones2 = corCustPhonesDao.getByCustCode(custCode,"0","2");
		if(corCustPhones2 == null)
			corCustPhones2 = new CorCustPhones();
		corCustPhones2.setIsDefault("0");
		corCustPhones2.setPhoneType("2");
		corCustPhones2.setPhoneNum(workTel);
		corCustPhonesDao.saveOrUpdate(corCustPhones2);
	}
	
	
	/**
	 * 联合查询
	 * 
	 */
	@Override
	public IPage<CorCompound> findCorCorCompoundByPage(QueryPage queryPage, CorCondition corCondition) {
		
		return corCustPersonalDao.findCorCompoundByPage(queryPage, corCondition);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public IPage<CorCustPersonal> findCorCustPersonalByPage(QueryPage queryPage) {
		logger.info("entering action::CorCustPersonalBizImpl.findCorCustPersonalByPage()...");
		return corCustPersonalDao.findCorCustPersonalByPage(queryPage);
	}


	@Override
	public CorCustPersonal saveCorCustPersonal(CorCustPersonal corCustPersonal) {
		logger.info("entering action::CorCustPersonalBizImpl.saveCorCustPersonal()...");
		corCustPersonal.setId(null);
		corCustPersonalDao.saveOrUpdate(corCustPersonal);
		return corCustPersonal;
	}

	@Override
	public CorCustPersonal updateCorCustPersonal(CorCustPersonal corCustPersonal) {
		logger.info("entering action::CorCustPersonalBizImpl.updateCorCustPersonal()...");
		corCustPersonalDao.saveOrUpdate(corCustPersonal);
		return corCustPersonal;
	}

	@Override
	public void exportCorCustPersonal(ExportSetting exportSetting) {
		logger.info("entering action::CorCustPersonalBizImpl.exportCorCustPersonal()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<CorCustPersonal> corCustPersonalPage = (Page<CorCustPersonal>) corCustPersonalDao
				.findCorCustPersonalByPage(exportSetting);

		excelService.exportToFile(corCustPersonalPage.getRows(), exportSetting);
	}
	
	public ICorCustPersonalDao getCorCustPersonalDao() {
		logger.info("entering action::CorCustPersonalBizImpl.getCorCustPersonalDao()...");
		return corCustPersonalDao;
	}

	public void setCorCustPersonalDao(ICorCustPersonalDao corCustPersonalDao) {
		logger.info("entering action::CorCustPersonalBizImpl.setCorCustPersonalDao()...");
		this.corCustPersonalDao = corCustPersonalDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::CorCustPersonalBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::CorCustPersonalBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

	public ICorCustPhonesDao getCorCustPhonesDao() {
		return corCustPhonesDao;
	}

	public void setCorCustPhonesDao(ICorCustPhonesDao corCustPhonesDao) {
		this.corCustPhonesDao = corCustPhonesDao;
	}

	public ICorCustAddrDao getCorCustAddrDao() {
		return corCustAddrDao;
	}

	public void setCorCustAddrDao(ICorCustAddrDao corCustAddrDao) {
		this.corCustAddrDao = corCustAddrDao;
	}
	public ICorCustDao getCorCustDao() {
		return corCustDao;
	}
	
	public void setCorCustDao(ICorCustDao corCustDao) {
		this.corCustDao = corCustDao;
	}




}
